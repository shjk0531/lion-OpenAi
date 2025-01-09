package com.li.chatapp.domain.article.article.entity;

import com.li.chatapp.domain.article.articleComment.entity.ArticleComment;
import com.li.chatapp.domain.article.articleTag.entity.ArticleTag;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleTag> tags = new ArrayList<>();

    public void addComment(Member member, String content) {
        ArticleComment articleComment = ArticleComment.builder().member(member).article(this).content(content).build();

        comments.add(articleComment);
    }

    public void removeComment(ArticleComment comment) {
        comments.remove(comment);
    }

    public void addTag(String... tags) {
        for (String tag : tags) {
            ArticleTag articleTag = ArticleTag.builder().tag(tag).article(this).build();
            this.tags.add(articleTag);
        }
    }

    public String getTagsStr() {
        String tagsStr = tags
                .stream()
                .map(ArticleTag::getTag)
                .collect(Collectors.joining(" #"));

        if (tagsStr.isEmpty()) return "";

        return "#" + tagsStr;
    }
}
