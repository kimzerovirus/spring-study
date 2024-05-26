package me.kzv.websocketbasic.chat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
