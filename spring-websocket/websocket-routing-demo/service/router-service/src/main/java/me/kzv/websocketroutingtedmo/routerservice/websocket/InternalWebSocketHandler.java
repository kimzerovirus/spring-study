package me.kzv.websocketroutingtedmo.routerservice.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class InternalWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> internalSessions = new ConcurrentHashMap<>();
    private final Map<String, String> sessionMapping = new ConcurrentHashMap<>(); // 내부 세션 ID -> 외부 세션 ID
    private final ExternalWebSocketClient externalWebSocketClient;

    public InternalWebSocketHandler(ExternalWebSocketClient externalWebSocketClient) {
        this.externalWebSocketClient = externalWebSocketClient;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        log.info("Internal WebSocket connection established: {}", sessionId);
        // 프론트엔드 연결 시 외부 연결 ID를 어떻게 매핑할 것인가?
        // API 응답으로 받은 sessionId를 쿼리 파라미터나 다른 방식으로 전달받아야 함
        String externalSessionId = extractExternalSessionId(session);
        if (externalSessionId != null && externalWebSocketClient.isConnected(externalSessionId)) {
            internalSessions.put(sessionId, session);
            sessionMapping.put(sessionId, externalSessionId);
            log.info("Mapped internal session {} to external session {}", sessionId, externalSessionId);
        } else {
            log.warn("No active external session found for internal session {}. Closing.", sessionId);
            session.close();
        }
    }

    private String extractExternalSessionId(WebSocketSession session) {
        // 클라이언트가 연결 시점에 외부 세션 ID를 전달하는 방식에 따라 구현
        // 예: ws://localhost:8080/ws-relay?externalId=some-uuid
        return Optional.ofNullable(session.getUri())
                .map(uri -> uri.getQuery().replace("externalId=", ""))
                .orElse(null);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String internalSessionId = session.getId();
        String externalSessionId = sessionMapping.get(internalSessionId);
        if (externalSessionId != null) {
            log.info("Received from internal (session {}): {}", internalSessionId, message.getPayload());
            externalWebSocketClient.sendMessage(externalSessionId, message.getPayload());
        } else {
            log.warn("No external session mapped for internal session: {}", internalSessionId);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String internalSessionId = session.getId();
        String externalSessionId = sessionMapping.get(internalSessionId);
        log.info("Internal WebSocket connection closed: {} with status: {}", internalSessionId, status);
        internalSessions.remove(internalSessionId);
        sessionMapping.remove(internalSessionId);
        if (externalSessionId != null && externalWebSocketClient.isConnected(externalSessionId)) {
            // 내부 연결이 끊어지면 외부 연결을 끊는다
            externalWebSocketClient.disconnect(externalSessionId);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String internalSessionId = session.getId();
        log.error("Transport error on internal WebSocket: {}", internalSessionId, exception);
        internalSessions.remove(internalSessionId);
        sessionMapping.remove(internalSessionId);
    }

    public WebSocketSession getInternalSession(String externalSessionId) {
        for (Map.Entry<String, String> entry : sessionMapping.entrySet()) {
            if (entry.getValue().equals(externalSessionId)) {
                return internalSessions.get(entry.getKey());
            }
        }
        return null;
    }

    public void removeInternalSession(String externalSessionId) {
        sessionMapping.entrySet().removeIf(entry -> {
            if (entry.getValue().equals(externalSessionId)) {
                internalSessions.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }
}