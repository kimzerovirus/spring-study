package me.kzv.websocketredis.chat;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public static ChatRoom create(String name) {
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .name(name)
                .build();
    }
}
