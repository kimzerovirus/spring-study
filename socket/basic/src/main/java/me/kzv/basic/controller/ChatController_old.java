package me.kzv.basic.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.basic.dto.ChatRoom_old;
import me.kzv.basic.service.ChatService_old;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController_old {
/*
    private final ChatService_old chatService;

    @PostMapping
    public ChatRoom_old createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom_old> findAllRoom(){
        return chatService.findAllRoom();
    }

 */
}
