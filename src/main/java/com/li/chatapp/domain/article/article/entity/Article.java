package com.li.chatapp.domain.article.article.entity;

import com.li.chatapp.domain.global.jpa.BaseEntity;
import com.li.chatapp.domain.member.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString( callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Article extends BaseEntity {
    @ManyToOne
    private Member member;
    private String title;
    private String content;
}
