package com.li.chatai.domain.chat.chatMessage.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat/rooms")
public class ApiV1ChatMessageController {

    @PostMapping("/{roomId}/messages")
    public String chatMessage(@PathVariable("roomId") Long roomId) {
        return "채팅방 메시지 생성 완료";
    }

    @GetMapping("/{roomId}/messages")
    public String createChatMessages(@PathVariable("roomId") Long roomId, @RequestParam(value = "afterChatMessageId", defaultValue = "-1") Long messagedId) {
        return roomId + "번채팅방 메시지 목록 조회 완료 id : " + messagedId;
    }
}
