package me.kzv.websocketredis.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/room")
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping
    public List<ChatRoom> findAllRoom(){
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    @GetMapping("/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
