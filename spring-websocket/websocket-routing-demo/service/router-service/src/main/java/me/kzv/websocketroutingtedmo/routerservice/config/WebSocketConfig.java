package me.kzv.websocketroutingtedmo.routerservice.config;

import lombok.RequiredArgsConstructor;
import me.kzv.websocketroutingtedmo.routerservice.websocket.InternalWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final InternalWebSocketHandler internalWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(internalWebSocketHandler, "/ws-relay").setAllowedOrigins("*");
    }
}
