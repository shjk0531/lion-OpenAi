package com.li.chatai.domain.chat.chatMessage.service;

import com.li.chatai.domain.chat.chatMessage.entity.ChatMessage;
import com.li.chatai.domain.chat.chatMessage.repository.ChatMessageRepository;
import com.li.chatai.domain.chat.chatRoom.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage send( ChatRoom chatRoom, String sender, String message) {
        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .message(message)
                .chatRoom(chatRoom)
                .build();

        chatMessageRepository.save(chatMessage);

        return chatMessage;
    }

    public List<ChatMessage> getChatRoom(Long roomId) {
        return chatMessageRepository.getAllById(roomId);
    }
}
