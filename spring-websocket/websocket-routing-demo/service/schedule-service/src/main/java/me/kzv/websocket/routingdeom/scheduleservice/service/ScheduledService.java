package me.kzv.websocket.routingdeom.scheduleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.websocket.routingdeom.scheduleservice.websocket.ScheduleWebSocketHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduledService {
    private final ScheduleWebSocketHandler webSocketHandler;

    @Scheduled(fixedRate = 10000)
    public void fixedScheduledSendMessage() {
        log.info("scheduled send message at {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        webSocketHandler.getSessions().forEach(session -> {
            TextMessage textMessage = new TextMessage(session.getId() + "\n(Scheduled service will be sent every 15 seconds)");
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
