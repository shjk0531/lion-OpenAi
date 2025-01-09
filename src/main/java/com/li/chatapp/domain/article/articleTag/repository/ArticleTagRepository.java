package com.li.chatapp.domain.article.articleTag.repository;

import com.li.chatapp.domain.article.articleTag.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {
    List<ArticleTag> findAllByArticle_authorId(long authorId);

    List<ArticleTag> findAllByArticle_authorName(String userName);
}
