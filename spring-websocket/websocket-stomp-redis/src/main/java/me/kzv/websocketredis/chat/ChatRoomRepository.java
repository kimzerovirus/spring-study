package me.kzv.websocketredis.chat;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ChatRoomRepository {
    // 여기서는 Map으로 관리하지만 실제 서비스라면 DB 등을 통해 채팅방 정보를 관리해야 할 것이다.
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
