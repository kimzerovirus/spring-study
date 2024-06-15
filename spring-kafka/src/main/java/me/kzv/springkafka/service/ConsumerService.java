package me.kzv.springkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.kzv.springkafka.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {
    private static final String TOPIC_NAME = "topic1";

    final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = TOPIC_NAME)
    public void listenMessage(String message) {
        log.info(">>>\n>>> consumer\n>>>");

        try {
            var messageDto = objectMapper.readValue(message, MessageDto.class);
            log.info(messageDto.toString());
        } catch (JsonProcessingException e) {
            log.info(message);
        }
    }
}
