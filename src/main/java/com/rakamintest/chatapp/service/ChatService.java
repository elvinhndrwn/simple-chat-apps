package com.rakamintest.chatapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakamintest.chatapp.dto.IncomingMessageResponse;
import com.rakamintest.chatapp.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public String send(MessageRequest request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        jmsTemplate.convertAndSend("message", objectMapper.writeValueAsString(request));
        return "delivered";
    }


}
