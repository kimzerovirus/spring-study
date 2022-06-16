package me.kzv.basic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.kzv.basic.service.ChatService_old;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Getter
public class ChatRoom_old {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom_old(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    // pub/sub 방식을 이용하면 구독자 관리가 알아서 되므로 웹소켓 세션 관리가 필요 없어진다.

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService_old chatService){
//        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
//            // 1. 입장시
//            sessions.add(session);
//            chatMessage.setMessage(chatMessage.getMessage()+ "님이 입장했습니다.");
//        }else if(chatMessage.getType().equals(ChatMessage.MessageType.TALK)){
//            // 입장이 아닌경우 대화 메시지 전송
//            sendMessage(chatMessage, chatService);
//        }

        switch (chatMessage.getType()){
            case ENTER: // 입장시
                sessions.add(session);
                chatMessage.setMessage(chatMessage.getMessage());
                break;

            case TALK: // 대화 전송시
                sendMessage(chatMessage, chatService);

            default:
                log.info("비정상적인 타입 발생!!!");
                break;
        }
    }

    public <T> void sendMessage(T message, ChatService_old chatService){
        // 채팅룸에 메시지가 도착하면 채팅룸의 모든 session에 메시지를 발송한다.
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
