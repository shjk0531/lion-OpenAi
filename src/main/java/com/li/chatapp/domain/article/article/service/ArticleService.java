package com.li.chatapp.domain.article.article.service;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.repository.ArticleRepository;
import com.li.chatapp.domain.article.articleComment.entity.ArticleComment;
import com.li.chatapp.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article write(String title, String content) {
        Article article = Article.builder()
                .author(Member.builder().id(1L).build())
                .title(title)
                .content(content)
                .build();

        return articleRepository.save(article);
    }


    public Optional<Article> findById(long l) {
        Optional<Article> optionalArticle = articleRepository.findById(l);

        return optionalArticle;
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        articleRepository.save(article);
    }

    public void modifyComment(ArticleComment comment, String newContent) {
        comment.setContent(newContent);

    }

    public List<Article> findAll() {


        return articleRepository.findAll();
    }

    public void delete(Long id) {
        this.articleRepository.deleteById(id);
    }
}
