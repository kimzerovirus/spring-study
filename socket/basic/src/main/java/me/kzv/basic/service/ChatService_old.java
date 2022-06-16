package me.kzv.basic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.basic.dto.ChatRoom_old;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService_old {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom_old> chatRooms;

    /*
        채팅방 조회 - 채팅방 Map에 담긴 정보를 조회
        채팅방 생성 - Random UUID로 구별ID를 가진 채팅방 객체를 생성하고 채팅방 Map에 추가.
        메시지 발송 - 지정한 WebSocket 세션에 메시지를 발송
     */

/*

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom_old> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom_old findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom_old createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom_old chatRoom = ChatRoom_old.builder()
                .roomId(randomId)
                .name(name)
                .build();

        chatRooms.put(randomId, chatRoom);

        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
*/
}
