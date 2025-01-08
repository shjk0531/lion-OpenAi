package com.li.chatai.domain.chat.chatRoom.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.li.chatai.domain.chat.chatRoom.entity.ChatRoom;
import com.li.chatai.domain.chat.chatRoom.service.ChatRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/rooms")
public class ApiV1ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ApiV1ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping()
    public List<ChatRoom> chatRooms() {

        List<ChatRoom> chatRooms = chatRoomService.getAll();

        return chatRooms;
    }

    @GetMapping("/{roomId}")
    public String chatRoomDetail(@PathVariable("roomId") Long roomId) {
        return roomId + "번 채팅방 조회 완료";
    }

    @PostMapping()
    public ChatRoom createChatRoom(@RequestBody JsonNode name) {

        ChatRoom chatRoom = chatRoomService.create(name.get("name").asText());
        return chatRoom;
    }


}
