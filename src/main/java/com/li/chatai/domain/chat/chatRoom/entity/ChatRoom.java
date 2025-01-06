package com.li.chatai.domain.chat.chatRoom.entity;

import com.li.chatai.domain.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@SuperBuilder
public class ChatRoom extends BaseEntity {
    private String roomName;
    private String roomDescription;
    private String roomCreator;
    private String roomPassword;
    private String roomStatus;
    private String roomType;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
}
