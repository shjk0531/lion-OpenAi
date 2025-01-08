package com.li.chatapp.domain.chat.chatRoom.entity;

import com.li.chatapp.domain.chat.chatMessage.entity.ChatMessage;
import com.li.chatapp.domain.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@SuperBuilder
public class ChatRoom extends BaseEntity {
    private String name;
    private String password;

    @OneToMany
    private List<ChatMessage> chatMessages;
}
