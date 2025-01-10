package com.li.chatapp.domain.article.article.controller;

import com.li.chatapp.domain.article.article.dto.ArticleDto;
import com.li.chatapp.domain.article.article.dto.ArticleModifyRequest;
import com.li.chatapp.domain.article.article.dto.ArticleWriteRequest;
import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.service.ArticleService;
import com.li.chatapp.global.rsData.RsData;
import jakarta.validation.Valid;
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
        Article article = articleService.findById(id).orElse(null);
        return new ArticleDto(article);
    }

    @PostMapping
    public RsData<ArticleDto> writeArticle(@RequestBody ArticleWriteRequest articleWriteRequest) {
        Article article = articleService.write(articleWriteRequest.getTitle(), articleWriteRequest.getContent());

        return RsData.of(
                "200",
                "게시글 작성에 성공하였습니다.",
                new ArticleDto(article)
        );
    }

    @PatchMapping({"/{id}"})
    public RsData<ArticleDto> updateArticle(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {
        Article article = this.articleService.findById(id).orElse(null);
        Article modifiedArticle = this.articleService.modify(article, articleModifyRequest.getTitle(), articleModifyRequest.getContent());

        return RsData.of(
                "200",
                "게시글 수정에 성공하였습니다.",
                new ArticleDto(modifiedArticle)
        );
    }

    @DeleteMapping({"/{id}"})
    public RsData<Void> deleteArticle(@PathVariable("id") Long id) {
        this.articleService.delete(id);

        return RsData.of(
                "200",
                "게시글이 삭제되었습니다.",
                null
        );
    }
}
