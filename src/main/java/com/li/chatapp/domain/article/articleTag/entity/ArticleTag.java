package com.li.chatapp.domain.article.articleTag.entity;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString( callSuper = true)
public class ArticleTag extends BaseEntity {
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;
}
