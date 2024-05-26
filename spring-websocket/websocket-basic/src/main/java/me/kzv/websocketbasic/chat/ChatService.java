package me.kzv.websocketbasic.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms = new ConcurrentHashMap<>();
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public List<ChatRoom> findAllRoom() {
        return chatRooms.values().stream().toList();
    }

    public ChatRoom createRoom(String name) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(roomId)
                .name(name)
                .build();
        chatRooms.put(roomId, chatRoom);

        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(message));
            session.sendMessage(textMessage);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
