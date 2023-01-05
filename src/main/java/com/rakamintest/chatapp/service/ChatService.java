package com.rakamintest.chatapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakamintest.chatapp.dto.IncomingMessageResponse;
import com.rakamintest.chatapp.dto.MessageRequest;
import com.rakamintest.chatapp.dto.SendMessageRequest;
import com.rakamintest.chatapp.model.MessageHistoryModel;
import com.rakamintest.chatapp.model.ParticipantModel;
import com.rakamintest.chatapp.model.RoomModel;
import com.rakamintest.chatapp.repository.MessageHistoryRepository;
import com.rakamintest.chatapp.repository.ParticipantRepository;
import com.rakamintest.chatapp.repository.RoomRepository;
import com.rakamintest.chatapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class ChatService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageHistoryRepository messageHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private RoomRepository roomRepository;

    public String send(MessageRequest request) throws JsonProcessingException {
        try {
            Map<String, Object> map = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            // construct send message request
            map.put("senderUserId", request.getSenderUserId());
            map.put("phoneNumber", request.getPhoneNumber());
            map.put("msg", request.getMessage());
            map.put("time", currentTime);
            map.put("roomId", request.getRoomId());
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .chatData(map)
                    .build();

            jmsTemplate.convertAndSend("message", objectMapper.writeValueAsString(sendMessageRequest));
            return "delivered";
        }catch (Exception e){
            log.error("error when sending the message: ", e);
            throw e;
        }
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
            incomingMessageResponse.setPhoneNumber(sender.getPhoneNumber());

            response.add(incomingMessageResponse);
        });

        return response;
    }
}
