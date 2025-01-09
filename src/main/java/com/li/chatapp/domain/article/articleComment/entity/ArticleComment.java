package com.li.chatapp.domain.article.articleComment.entity;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString( callSuper = true)
public class ArticleComment extends BaseEntity {
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public String getBody() {
        return content;
    }
}
