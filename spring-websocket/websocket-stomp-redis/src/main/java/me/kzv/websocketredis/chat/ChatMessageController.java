package me.kzv.websocketredis.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

/**
 * https://docs.spring.io/spring-framework/reference/web/websocket/stomp/handle-annotations.html
 *
 * Applications can use annotated @Controller classes to handle messages from clients.
 * Such classes can declare @MessageMapping, @SubscribeMapping, and @ExceptionHandler methods
 */
@Controller
@RequiredArgsConstructor
public class ChatMessageController {
    private final SimpMessageSendingOperations messagingTemplate;

    // publisher 역할 - subscriber 는 프론트단에서 stomp 라이브러리를 이용하여 subscriber 주소를 바라보고 있으면 된다.
    /**
     * 클라이언트에서는 prefix를 붙여서 /pub/chat/message로 발행 요청을 하면 Controller가 해당 메시지를 받아 처리한다.
     * 메시지가 발행되면 /sub/chat/room/{roomId} 로 메시지를 send 하는 것을 볼 수있는데 클라이언트에서는 해당 주소를
     * 구독하고 있다가 메시지가 전달되면 화면에 출력하면 된다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }

        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
