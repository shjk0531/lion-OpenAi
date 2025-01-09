package com.li.chatapp.domain.article.articleComment.service;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.repository.ArticleRepository;
import com.li.chatapp.domain.article.articleComment.entity.ArticleComment;
import com.li.chatapp.domain.article.articleComment.repository.ArticleCommentRepository;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleCommentService {
    private final ArticleCommentRepository commentRepository;
    private final MemberService memberService;
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public void saveComment(Member member, Article article, String content) {
        ArticleComment comment = ArticleComment.builder().member(member).article(article).content(content).build();
        commentRepository.save(comment);
    }

    public List<ArticleComment> findByAuthorId(long authorId) {
      return articleCommentRepository.findAllByMemberId(authorId);
    }
}
