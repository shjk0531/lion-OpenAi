package com.li.chatapp.domain.article.article.entity;

import com.li.chatapp.global.jpa.BaseEntity;
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
    private String title;
    private String content;
    @ManyToOne
    private Member author;
}
