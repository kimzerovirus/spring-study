package me.kzv.basic.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.basic.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {
    // publisher 구현

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage chatMessage) {

        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType()))
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/cht/room/" + chatMessage.getRoomId(), chatMessage);
    }
}


