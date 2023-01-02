package com.rakamintest.chatapp.listener;

import com.rakamintest.chatapp.dto.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatListener {

    @JmsListener(destination = "message")
    public void listener(MessageRequest request) {
        log.info("Received message Notification from JMS: {}", request);
    }
}
