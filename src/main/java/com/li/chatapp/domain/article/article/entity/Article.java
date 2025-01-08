package com.li.chatapp.domain.article.article.entity;

import com.li.chatapp.domain.article.articleComment.entity.ArticleComment;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.global.jpa.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Article extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne
    private Member author;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleComment> comments = new ArrayList<>();

    public void addComment(Member member, String content) {
        ArticleComment articleComment = ArticleComment.builder().member(member).article(this).content(content).build();

        comments.add(articleComment);
    }
}
