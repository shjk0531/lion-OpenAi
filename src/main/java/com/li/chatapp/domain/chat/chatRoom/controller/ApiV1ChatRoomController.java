package com.li.chatapp.domain.chat.chatRoom.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.li.chatapp.domain.chat.chatRoom.entity.ChatRoom;
import com.li.chatapp.domain.chat.chatRoom.service.ChatRoomService;
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
    public ChatRoom chatRoomDetail(@PathVariable("roomId") Long roomId) {
        ChatRoom chatRoom = chatRoomService.getChatRoom(roomId);
        return chatRoom;

    }

    @PostMapping()
    public ChatRoom createChatRoom(@RequestBody JsonNode name) {

        ChatRoom chatRoom = chatRoomService.create(name.get("name").asText());
        return chatRoom;
    }


}
