package com.rakamintest.chatapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rakamintest.chatapp.dto.MessageRequest;
import com.rakamintest.chatapp.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public String send(@RequestBody MessageRequest request) throws JsonProcessingException {
        return chatService.send(request);
    }
}
