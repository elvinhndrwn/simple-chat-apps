package com.rakamintest.chatapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rakamintest.chatapp.dto.MessageRequest;
import com.rakamintest.chatapp.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/send")
    public String send() throws JsonProcessingException {
        return chatService.send(MessageRequest.builder()
                        .message("hello")
                .build());
    }
}
