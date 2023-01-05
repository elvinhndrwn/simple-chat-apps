package com.rakamintest.chatapp.listener;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakamintest.chatapp.constant.MessageStatus;
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
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.DataInput;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class ChatListener {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MessageHistoryRepository messageHistoryRepository;

    @JmsListener(destination = "message")
    public void listener(String request) throws JsonProcessingException {
        log.info("Received message from JMS: {}", request);
        storeMessage(request);
    }

    private void storeMessage(String request) throws JsonProcessingException {
        var chatData = objectMapper.readValue(request, SendMessageRequest.class).getChatData();
        var receiverMsg = userRepository.findByPhoneNumber(chatData.get("phoneNumber").toString());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        ParticipantModel participant;
        RoomModel room = null;
        if(!Objects.isNull(receiverMsg)){

            if(Objects.isNull(chatData.get("roomId"))){
                // if this is a new message
                // create participant
                participant = participantRepository.save(ParticipantModel.builder()
                        .userOne((Integer) chatData.get("senderUserId"))
                        .userTwo(receiverMsg.getId())
                        .build());

                // create a room
                try {
                    room = roomRepository.save(RoomModel.builder()
                            .participantId(participant.getId())
                            .build());
                }catch (Exception e){
                    log.error("error when create room chat: ", e);
                    participantRepository.deleteById(participant.getId());
                    throw e;
                }

                try {
                    // save to message history
                    messageHistoryRepository.save(MessageHistoryModel.builder()
                            .roomId(room.getId())
                            .message(chatData.get("msg").toString())
                            .time(currentTime)
                            .senderUserId((Integer) chatData.get("senderUserId"))
                            .messageStatus(MessageStatus.SENT)
                            .build());
                }catch (Exception e){
                    log.error("error when store message history : ", e);
                    participantRepository.deleteById(participant.getId());
                    roomRepository.deleteById(room.getId());
                    throw e;
                }

            }else{
                // save to message history
                messageHistoryRepository.save(MessageHistoryModel.builder()
                        .roomId((Integer) chatData.get("roomId"))
                        .message(chatData.get("msg").toString())
                        .time(currentTime)
                        .senderUserId((Integer) chatData.get("senderUserId"))
                        .messageStatus(MessageStatus.SENT)
                        .build());
            }



        }
        log.info("success store message history");
    }
}
