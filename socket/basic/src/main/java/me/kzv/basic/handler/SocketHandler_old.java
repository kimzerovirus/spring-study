package me.kzv.basic.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.basic.dto.ChatMessage;
import me.kzv.basic.dto.ChatRoom;
import me.kzv.basic.service.ChatService_old;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class SocketHandler_old extends TextWebSocketHandler {

    /*
        1. 웹소켓 클라이언트로부터 채팅 메시지를 전달받아 채팅 메시지 객체로 변환
        2. 전달받은 메시지에 담긴 채팅방 ID로 발송 대상 채팅방 정보를 조회
        3. 해당 채팅방ㅇ체 입장해있는 모든 클라이언트들 (websocket session)에게 타입에 따른 메시지 발송
     */
/*
    private final ObjectMapper objectMapper;
    private final ChatService_old chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
//        TextMessage textMessage = new TextMessage("Welcome chatting sever~^^");
//        session.sendMessage(textMessage);

        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }
*/
}