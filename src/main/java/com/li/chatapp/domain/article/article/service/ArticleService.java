package com.li.chatapp.domain.article.article.service;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.repository.ArticleRepository;
import com.li.chatapp.domain.article.articleComment.entity.ArticleComment;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public RsData<Article> write(Long memberId, String title, String content) {
        Article article = Article.builder()
                .author(Member.builder().id(memberId).build())
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);

        return RsData.of("200", "글 작성 성공", article);
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

    public Page<Article> search(List<String> kwTypes, String kw, Pageable pageable) {
        if (kwTypes.contains("authorUsername") && kwTypes.contains("title") && kwTypes.contains("content")) {
            return articleRepository.findByAuthor_nameContainingOrTitleContainingOrContentContaining(kw, kw, kw, pageable);
        } else if (kwTypes.contains("title") && kwTypes.contains("content")) {
            return articleRepository.findByTitleContainingOrContentContaining(kw, kw, pageable);
        } else if (kwTypes.contains("title")) {
            return articleRepository.findByTitleContaining(kw, pageable);
        } else if (kwTypes.contains("content")) {
            return articleRepository.findByContentContaining(kw, pageable);
        } else if (kwTypes.contains("authorUsername")) {
            return articleRepository.findByAuthor_nameContaining(kw, pageable);
        }

        return articleRepository.findAll(pageable);
    }
}
