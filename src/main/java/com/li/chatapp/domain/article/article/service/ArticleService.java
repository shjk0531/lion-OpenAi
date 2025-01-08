package com.li.chatapp.domain.article.article.service;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.repository.ArticleRepository;
import com.li.chatapp.domain.global.rsData.RsData;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.repository.MemberRepository;
import com.li.chatapp.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public RsData<Article> write(Long memberId, String title, String content) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isEmpty()) {
            return RsData.of("404", "존재하지 않는 회원 ID입니다.", null);
        }

        Member member = optionalMember.get();

        Article article = Article.builder()
                .member(member)
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);

        return RsData.of("200", "글 작성 완료", article);
    }

    public RsData<Article> getArticle(Long articleId) {

    }
}
