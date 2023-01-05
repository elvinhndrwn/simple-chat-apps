package com.rakamintest.chatapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rakamintest.chatapp.dto.IncomingMessageResponse;
import com.rakamintest.chatapp.dto.MessageRequest;
import com.rakamintest.chatapp.dto.ViewMessageResponse;
import com.rakamintest.chatapp.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public String send(@RequestBody MessageRequest request) throws JsonProcessingException {
        return chatService.send(request);
    }

    @GetMapping("/show-incoming-message/{id}")
    public List<IncomingMessageResponse> showIncomingMessageList(@PathVariable(required = true) int id){
        return chatService.showIncomingMessageList(id);
    }

    @GetMapping("/view-message/{id}/{roomId}")
    public ViewMessageResponse viewMessage(@PathVariable int id, @PathVariable int roomId){
        return chatService.viewMessage(id, roomId);
    }
}
