package me.kzv.websocketroutingtedmo.routerservice.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.websocketroutingtedmo.routerservice.websocket.ExternalWebSocketClient;
import me.kzv.websocketroutingtedmo.routerservice.websocket.InternalWebSocketHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final ExternalWebSocketClient externalWebSocketClient;
    private final InternalWebSocketHandler internalWebSocketHandler;

    @PostMapping("/connect-external")
    public ResponseEntity<String> connectToExternal() {
        String sessionId = UUID.randomUUID().toString();
        externalWebSocketClient.connect(sessionId, new ExternalWebSocketClient.TextMessageHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) {
                log.info("External WebSocket connected for API request: {}", sessionId);
                // 연결 성공 후 추가적인 로직 처리
            }

            @Override
            public void handleTextMessage(TextMessage message) throws Exception {
                log.info("Received from external (API context - session {}): {}", sessionId, message.getPayload());
                WebSocketSession internalSession = internalWebSocketHandler.getInternalSession(sessionId);
                if (internalSession != null && internalSession.isOpen()) {
                    internalSession.sendMessage(new TextMessage("External says: " + message.getPayload()));
                }
            }

            @Override
            public void afterConnectionClosed(CloseStatus status) {
                log.info("External WebSocket closed for API request: {} with status: {}", sessionId, status);
                internalWebSocketHandler.removeInternalSession(sessionId);
            }

            @Override
            public void handleTransportError(Throwable exception) {
                log.error("Transport error on external WebSocket for API request: {}", sessionId, exception);
                internalWebSocketHandler.removeInternalSession(sessionId);
            }
        });

        return ResponseEntity.ok(sessionId);
    }
}