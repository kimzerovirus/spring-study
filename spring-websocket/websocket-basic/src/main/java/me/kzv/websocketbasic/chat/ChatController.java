package me.kzv.websocketbasic.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 채팅방의 생성 및 조회
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom(){
        return chatService.findAllRoom();
    }

    @GetMapping("/{roomId}")
    public ChatRoom findByRoomId(@PathVariable String roomId){
        return chatService.findRoomById(roomId);
    }
}
