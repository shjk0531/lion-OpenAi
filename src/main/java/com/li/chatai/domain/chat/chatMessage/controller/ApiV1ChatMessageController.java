package com.li.chatai.domain.chat.chatMessage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/chat/rooms")
public class ApiV1ChatMessageController {

    @GetMapping("/{roomId}/messages")
    public String chatMessage() {
        return "chatRooms";
    }

    @PostMapping("/{roomId}/messages")
    public String createChatMessages() {
        return "createChatRoom";
    }
}
