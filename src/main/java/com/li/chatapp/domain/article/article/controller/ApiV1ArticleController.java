package com.li.chatapp.domain.article.article.controller;

import com.li.chatapp.domain.article.article.dto.ArticleDto;
import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.service.ArticleService;
import com.li.chatapp.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleDto> getArticles() {
        List<Article> articles = articleService.findAll();
        List<ArticleDto> articleDtoList = articles.stream()
                .map(ArticleDto::new)
                .toList();
        return articleDtoList;
    }

    @GetMapping({"/{id}"})
    private ArticleDto getArticle(@PathVariable("id") Long id) {
        Article article = articleService.findById(id).orElseGet(Article::new);
        return new ArticleDto(article);
    }

    @PostMapping
    public RsData writeArticle(@RequestBody Article article) {
        return articleService.write(article.getId(), article.getTitle(), article.getContent());
    }

    @PatchMapping({"/{id}"})
    public void updateArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        this.articleService.modify(article, article.getTitle(), article.getContent());
    }

    @DeleteMapping({"/{id}"})
    public void deleteArticle(@PathVariable("id") Long id) {
        this.articleService.delete(id);
    }
}
