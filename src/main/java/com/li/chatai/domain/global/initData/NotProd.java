package com.li.chatai.domain.global.initData;

import com.li.chatai.domain.chat.chatMessage.entity.ChatMessage;
import com.li.chatai.domain.chat.chatMessage.service.ChatMessageService;
import com.li.chatai.domain.chat.chatRoom.entity.ChatRoom;
import com.li.chatai.domain.chat.chatRoom.service.ChatRoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("dev")
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService, ChatMessageService chatMessageService) {
        return args -> {
            ChatRoom chatRoom1 = chatRoomService.create("test1");
            ChatRoom chatRoom2 = chatRoomService.create("test2");
            ChatRoom chatRoom3 = chatRoomService.create("test3");

            IntStream.range(1, 100).forEach(num -> {
                            ChatMessage chatMessage = chatMessageService.send(chatRoom2, "test sender", String.valueOf(num));
                    }
                    );

            System.out.println("Not Prod");
        };
    }
}
