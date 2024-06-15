package me.kzv.springkafka.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.springkafka.dto.MessageDto;
import me.kzv.springkafka.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProducerController {
    private final ProducerService producerService;

    @GetMapping("/publish/{message}")
    public ResponseEntity<String> publish(@PathVariable String message) {
        producerService.sendString(message);
        return ResponseEntity.ok("publish message" + message);
    }

    @GetMapping("/publish-cb/{message}")
    public ResponseEntity<String> publishAfter(@PathVariable String message) {
        producerService.callbackAfterSendString(message);
        return ResponseEntity.ok("publish message" + message);
    }

    @GetMapping("/publish-json/{message}")
    public ResponseEntity<MessageDto> publishMessageDto(@PathVariable String message) {
        MessageDto messageDto = producerService.sendJson(message);
        return ResponseEntity.ok(messageDto);
    }
}
