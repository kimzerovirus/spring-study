package me.kzv.springkafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.springkafka.dto.MessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProducerService {
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final KafkaTemplate<String, MessageDto> jsonKafkaTemplate;
    private static final String TOPIC_NAME = "topic1";

    public void sendString(String message) {
        stringKafkaTemplate.send(TOPIC_NAME, message);
    }

    public MessageDto sendJson(String message) {
        MessageDto messageDto = MessageDto.create(message);
        jsonKafkaTemplate.send(TOPIC_NAME, messageDto);
        return messageDto;
    }

    public void callbackAfterSendString(String message) {
        CompletableFuture<SendResult<String, String>> future = stringKafkaTemplate.send(TOPIC_NAME, message);

        future.whenComplete(
                (result, ex) -> {
                    if (ex == null) {
                        log.info("send message={" + message + "}" + ", offset={" + result.getRecordMetadata().offset() + "}");
                    } else {
                        log.error("fail send message={" + message + "}" + ", due to={" + ex.getLocalizedMessage() + "}");
                    }
                });
    }
}
