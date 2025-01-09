package com.li.chatapp.domain.article.articleTag.service;

import com.li.chatapp.domain.article.articleTag.entity.ArticleTag;
import com.li.chatapp.domain.article.articleTag.repository.ArticleTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleTagService {

    private final ArticleTagRepository articleTagRepository;

    public List<ArticleTag> findByAuthorId(long authorId) {
        return articleTagRepository.findAllByArticle_authorId(authorId);
    }

    public List<ArticleTag> findByAuthorUsername(String userName) {
        return articleTagRepository.findAllByArticle_authorName(userName);
    }
}
