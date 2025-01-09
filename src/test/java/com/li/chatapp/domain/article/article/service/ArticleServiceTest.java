package com.li.chatapp.domain.article.article.service;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.articleComment.entity.ArticleComment;
import com.li.chatapp.domain.article.articleComment.service.ArticleCommentService;
import com.li.chatapp.domain.article.articleTag.entity.ArticleTag;
import com.li.chatapp.domain.article.articleTag.service.ArticleTagService;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.service.MemberService;
import com.li.chatapp.global.rsData.RsData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles({"test", "dev"})
@Transactional
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ArticleCommentService articleCommentService;
    @Autowired
    private ArticleTagService articleTagService;

    @DisplayName("글 쓰기")
    @Test
    void t1() {
        RsData<Article> writeRs = articleService.write(1L, "제목", "내용");
        Article article = writeRs.getData();

        assertThat(article.getId()).isGreaterThan(0L);
    }

    @DisplayName("1번 글 가져오기")
    @Test
    void t2() {
        Article article = articleService.findById(1L).get();
        assertThat(article.getTitle()).isEqualTo("title1");
    }

    @DisplayName("1번 글의 작성자의 username 은 user1 이다.")
    @Test
    void t3() {
        Article article = articleService.findById(1L).get();
        Member author = article.getAuthor();

        assertThat(author.getName()).isEqualTo("user1");
    }

    @DisplayName("1번 글의 제목을 수정한다.")
    @Test
    void t4() {
        Article article = articleService.findById(1L).get();

        articleService.modify(article, "수정된 제목", "수정된 내용");

        Article article_ = articleService.findById(1L).get();

        assertThat(article_.getTitle()).isEqualTo("수정된 제목");
    }

    @DisplayName("2번 글에 댓글들을 추가한다")
    @Test
    @Rollback(false)
    void t5() {
        Member member = memberService.findById(1L).get();
        Article article = articleService.findById(1L).get();

        article.addComment(member, "댓글입니다.");
    }

    @DisplayName("1번 글의 댓글들을 수정한다.")
    @Test
    void t6() {
        Article article = articleService.findById(1L).get();

        article.getComments().forEach(comment -> {
            articleService.modifyComment(comment, comment.getBody() + "!!");
        });
    }

    @DisplayName("1번 글의 댓글 중 마지막 것을 삭제한다.")
    @Test
    void t7() {
        Article article = articleService.findById(1L).get();

        ArticleComment lastComment = article.getComments().getLast();

        article.removeComment(lastComment);
    }

    @DisplayName("게시물 별 댓글 수 출력")
    @Test
    void t8() {
        List<Article> articles = articleService.findAll();
        articles.forEach(article -> {
            System.out.println("게시물 번호: " + article.getId());
            System.out.println("댓글 수: " + article.getComments().size());
        });
    }

    @DisplayName("1번 게시물의 태그(String)를 반환한다.")
    @Test
    void t9() {
        Article article1 = articleService.findById(1L).get();

        String tagsStr = article1.getTagsStr();

        assertThat(tagsStr).isEqualTo("#자바 #백엔드");
    }

    @DisplayName("1번 게시물 toString")
    @Test
    void t10() {
        Article article1 = articleService.findById(1L).get();

        System.out.println(article1);
    }

    @DisplayName("1번 회원이 작성한 댓글들")
    @Test
    void t11() {
        List<ArticleComment> articleComments = articleCommentService.findByAuthorId(1L);

        assertThat(articleComments.size()).isGreaterThan(0);
    }

    @DisplayName("1번 회원이 작성한 태그들")
    @Test
    void t12() {
        List<ArticleTag> articleTags = articleTagService.findByAuthorId(1L);

        assertThat(articleTags.size()).isGreaterThan(0);
    }

    @DisplayName("아이디가 user1 인 회원이 작성한 태그들")
    @Test
    void t13() {
        List<ArticleTag> articleTags = articleTagService.findByAuthorUsername("user1");

        assertThat(articleTags.size()).isGreaterThan(0);
    }
}
