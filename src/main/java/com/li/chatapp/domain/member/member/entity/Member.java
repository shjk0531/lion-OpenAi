package com.li.chatapp.domain.member.member.entity;

import com.li.chatapp.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString( callSuper = true)
public class Member extends BaseEntity {
    private String name;
    private String password;

}
