package com.li.chatapp.global.initData;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.repository.ArticleRepository;
import com.li.chatapp.domain.article.article.service.ArticleService;
import com.li.chatapp.domain.chat.chatMessage.entity.ChatMessage;
import com.li.chatapp.domain.chat.chatMessage.service.ChatMessageService;
import com.li.chatapp.domain.chat.chatRoom.entity.ChatRoom;
import com.li.chatapp.domain.chat.chatRoom.service.ChatRoomService;
import com.li.chatapp.domain.member.member.entity.Member;
import com.li.chatapp.domain.member.member.repository.MemberRepository;
import com.li.chatapp.domain.member.member.service.MemberService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(
            ChatRoomService chatRoomService,
            ChatMessageService chatMessageService,
            ArticleService articleService,
            MemberService memberService,
            ArticleRepository articleRepository,
            MemberRepository memberRepository
    ) {
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                ChatRoom chatRoom1 = chatRoomService.create("room1");
                ChatRoom chatRoom2 = chatRoomService.create("room2");
                ChatRoom chatRoom3 = chatRoomService.create("room3");

                IntStream.range(1, 20).forEach(num -> {
                            ChatMessage chatMessage = chatMessageService.send(chatRoom2, "test sender", "채팅메세지 " + num);
                        }
                );

                Member member1 = memberService.join("user1", "0000").getData();
                Member member2 = memberService.join("user2", "1111").getData();
                Member member3 = memberService.join("user3", "2222").getData();

                Article article1 = articleService.write(member1.getId(), "title1", "content1").getData();
                Article article2 = articleService.write(member1.getId(), "title2", "content2").getData();

                Article article3 = articleService.write(member2.getId(), "title3", "content3").getData();
                Article article4 = articleService.write(member2.getId(), "title4", "content4").getData();

                article1.addComment(member1, "댓글1");
                article1.addComment(member1, "댓글2");

                article2.addComment(member1, "댓글3");
                article2.addComment(member1, "댓글4");
                article2.addComment(member1, "댓글5");

                article3.addComment(member1, "댓글5");
                article3.addComment(member1, "댓글6");
                article3.addComment(member1, "댓글7");
                article3.addComment(member1, "댓글8");
                article3.addComment(member1, "댓글9");
                article3.addComment(member1, "댓글10");
                article3.addComment(member1, "댓글11");
                article3.addComment(member1, "댓글12");


                System.out.println("This is not a production environment");
            }
        };
    }
}
