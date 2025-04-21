package me.kzv.websocketroutingtedmo.routerservice.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ExternalWebSocketClient {

    @Value("${external-websocket}")
    private String externalWebSocketUrl;

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void connect(String sessionId, TextMessageHandler handler) {
        WebSocketClient client = new StandardWebSocketClient();
        try {
            // doHandshake spring6에서 deprecated
            WebSocketSession session = client.execute(new TextWebSocketHandler() {
                @Override
                public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                    log.info("External WebSocket connection established for session: {}", sessionId);
                    sessions.put(sessionId, session);
                    if (handler != null) {
                        handler.afterConnectionEstablished(session);
                    }
                }

                @Override
                protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                    log.info("Received message from external WebSocket for session {}: {}", sessionId, message.getPayload());
                    if (handler != null) {
                        handler.handleTextMessage(message);
                    }
                }

                @Override
                public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
                    log.info("External WebSocket connection closed for session: {} with status: {}", sessionId, status);
                    sessions.remove(sessionId);
                    if (handler != null) {
                        handler.afterConnectionClosed(status);
                    }
                }

                @Override
                public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
                    log.error("Transport error on external WebSocket for session: {}", sessionId, exception);
                    if (handler != null) {
                        handler.handleTransportError(exception);
                    }
                }
            }, externalWebSocketUrl).get();
        } catch (Exception e) {
            log.error("Failed to connect to external WebSocket: {}", externalWebSocketUrl, e);
        }
    }

    public void sendMessage(String sessionId, String message) {
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
                log.info("Sent message to external WebSocket for session {}: {}", sessionId, message);
            } catch (Exception e) {
                log.error("Failed to send message to external WebSocket for session: {}", sessionId, e);
            }
        } else {
            log.warn("No active external WebSocket session found for ID: {}", sessionId);
        }
    }

    public void disconnect(String sessionId) {
        WebSocketSession session = sessions.get(sessionId);
        if (session != null && session.isOpen()) {
            try {
                session.close();
                log.info("Disconnected from external WebSocket for session: {}", sessionId);
                sessions.remove(sessionId);
            } catch (Exception e) {
                log.error("Error while disconnecting from external WebSocket for session: {}", sessionId, e);
            }
        } else {
            log.warn("No active external WebSocket session found for ID: {}", sessionId);
        }
    }

    public boolean isConnected(String sessionId) {
        WebSocketSession session = sessions.get(sessionId);
        return session != null && session.isOpen();
    }

    public WebSocketSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public interface TextMessageHandler {
        void afterConnectionEstablished(WebSocketSession session) throws Exception;
        void handleTextMessage(TextMessage message) throws Exception;
        void afterConnectionClosed(CloseStatus status) throws Exception;
        void handleTransportError(Throwable exception) throws Exception;
    }
}