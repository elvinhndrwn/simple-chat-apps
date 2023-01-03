package com.rakamintest.chatapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakamintest.chatapp.dto.IncomingMessageResponse;
import com.rakamintest.chatapp.dto.MessageRequest;
import com.rakamintest.chatapp.repository.MessageHistoryRepository;
import com.rakamintest.chatapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ChatService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageHistoryRepository messageHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    public String send(MessageRequest request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        jmsTemplate.convertAndSend("message", objectMapper.writeValueAsString(request));
        return "delivered";
    }


    public List<IncomingMessageResponse> showIncomingMessageList(int id) {
        List<IncomingMessageResponse> response = new ArrayList<>();
        var messages = messageHistoryRepository.showRoomChat(id);
        messages.forEach(msg -> {
            IncomingMessageResponse incomingMessageResponse = new IncomingMessageResponse();
            log.info("message id: {}", msg.getId());

            // get full name
            var sender = userRepository.findById(msg.getUserTwo());
            var currentMsg = messageHistoryRepository.showCurrentMessageByRoomId(msg.getRoomId());
            incomingMessageResponse.setRoomId(msg.getRoomId());
            incomingMessageResponse.setSenderName(sender.getUserFullName());
            incomingMessageResponse.setMessage(currentMsg.getMessage());

            response.add(incomingMessageResponse);
        });

        return response;
    }
}
