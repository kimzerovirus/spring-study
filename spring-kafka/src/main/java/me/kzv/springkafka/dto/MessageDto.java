package me.kzv.springkafka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageDto {
    private String uuid;
    private String content;

    public static MessageDto create(String content) {
        return new MessageDto(UUID.randomUUID().toString(), content);
    }
}
