package com.li.chatapp;

// import org.springframework.ai.openai.OpenAiChatModel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/chat")
@RestController
public class ChatController {

//    private final OpenAiChatModel openAiChatModel;
//
//    public ChatController(OpenAiChatModel openAiChatModel) {
//        this.openAiChatModel = openAiChatModel;
//    }
//
//    @GetMapping("/ai")
//    public Map<String, String> chat(@RequestBody String message) {
//
//        Map<String, String> response = new HashMap<>();
//
//        String openAiResponse = openAiChatModel.call(message);
//
//        response.put("Open Ai 응답", openAiResponse);
//
//        return response;
//    }
}
