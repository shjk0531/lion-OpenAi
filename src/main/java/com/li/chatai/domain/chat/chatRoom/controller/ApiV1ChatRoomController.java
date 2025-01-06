package com.li.chatai.domain.chat.chatRoom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/chat/rooms")
public class ApiV1ChatRoomController {

    @GetMapping()
    public String chatRooms() {
        return "chatRooms";
    }

    @GetMapping("/{roomId}")
    public String chatRoomDetail() {
        return "chatRoomDetail";
    }

    @PostMapping("")
    public String createChatRoom() {
        return "createChatRoom";
    }
}
