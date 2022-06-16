package me.kzv.basic.config;


import lombok.RequiredArgsConstructor;
import me.kzv.basic.handler.SocketHandler_old;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {
    private final SocketHandler_old socketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
