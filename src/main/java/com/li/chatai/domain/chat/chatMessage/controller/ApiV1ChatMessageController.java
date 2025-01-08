package com.li.chatai.domain.chat.chatMessage.controller;

import com.li.chatai.domain.chat.chatMessage.entity.ChatMessage;
import com.li.chatai.domain.chat.chatMessage.service.ChatMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat/rooms")
public class ApiV1ChatMessageController {

    private final ChatMessageService chatMessageService;

    public ApiV1ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @PostMapping("/{roomId}/messages")
    public String chatMessage(@PathVariable("roomId") Long roomId) {
        return "채팅방 메시지 생성 완료";
    }

    @GetMapping("/{roomId}/messages")
    public List<ChatMessage> createChatMessages(@PathVariable("roomId") Long roomId, @RequestParam(value = "afterChatMessageId", defaultValue = "-1") Long messagedId) {

        List<ChatMessage> messages = chatMessageService.getChatRoom(roomId);

        return messages;
    }
}
