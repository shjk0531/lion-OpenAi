package com.li.chatai.domain.chat.chatRoom.service;

import com.li.chatai.domain.chat.chatRoom.entity.ChatRoom;
import com.li.chatai.domain.chat.chatRoom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom create(String name) {

        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();

        chatRoomRepository.save(chatRoom);

        return chatRoom;
    }

    public List<ChatRoom> getAll() {

        return chatRoomRepository.findAll();
    }
}
