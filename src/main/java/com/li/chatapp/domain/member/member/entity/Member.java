package com.li.chatapp.domain.member.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.li.chatapp.global.jpa.BaseEntity;
import jakarta.persistence.Column;
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
    @Column(unique = true)
    private String name;
    @JsonIgnore
    private String password;

}
