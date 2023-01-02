package com.rakamintest.chatapp.service;

import com.rakamintest.chatapp.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public String send(MessageRequest request){
        jmsTemplate.convertAndSend("message", request);
        return "delivered";
    }
}
