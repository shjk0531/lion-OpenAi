package com.li.chatapp.domain.article.article.repository;

import com.li.chatapp.domain.article.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {

    List<Article> findByOrderByIdDesc();

    Page<Article> findByTitleContaining(String kw, Pageable pageable);

    Page<Article> findByTitleContainingOrContentContaining(String kw, String kw1, Pageable pageable);

    Page<Article> findByContentContaining(String kw, Pageable pageable);

    Page<Article> findByAuthor_nameContainingOrTitleContainingOrContentContaining(String kw, String kw1, String kw2, Pageable pageable);

    Page<Article> findByAuthor_nameContaining(String kw, Pageable pageable);
}
