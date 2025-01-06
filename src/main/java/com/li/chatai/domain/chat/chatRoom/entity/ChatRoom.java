package com.li.chatai.domain.chat.chatRoom.entity;

import com.li.chatai.domain.chat.chatMessage.entity.ChatMessage;
import com.li.chatai.domain.global.jpa.BaseEntity;
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
    private String roomName;
    private String roomPassword;

    @OneToMany
    private List<ChatMessage> chatMessages;
}
