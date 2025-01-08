package com.li.chatapp.domain.global.initData;

import com.li.chatapp.domain.article.article.entity.Article;
import com.li.chatapp.domain.article.article.service.ArticleService;
import com.li.chatapp.domain.chat.chatMessage.entity.ChatMessage;
import com.li.chatapp.domain.chat.chatMessage.service.ChatMessageService;
import com.li.chatapp.domain.chat.chatRoom.entity.ChatRoom;
import com.li.chatapp.domain.chat.chatRoom.service.ChatRoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("dev")
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService, ChatMessageService chatMessageService, ArticleService articleService) {
        return args -> {
            ChatRoom chatRoom1 = chatRoomService.create("room1");
            ChatRoom chatRoom2 = chatRoomService.create("room2");
            ChatRoom chatRoom3 = chatRoomService.create("room3");

            IntStream.range(1, 100).forEach(num -> {
                            ChatMessage chatMessage = chatMessageService.send(chatRoom2, "test sender", "채팅메세지 " + num);
                    }
                    );

            Article article = articleService.write(1L, "title1", "content1").getData();

            System.out.println("This is not a production environment");
        };
    }
}
