package com.li.chatai.domain.chat.chatMessage.entity;

import com.li.chatai.domain.chat.chatRoom.entity.ChatRoom;
import com.li.chatai.domain.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@SuperBuilder
public class ChatMessage extends BaseEntity {
    private String message;
    private String sender;

    @ManyToOne
    private ChatRoom chatRoom;
}
