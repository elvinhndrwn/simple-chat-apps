package com.rakamintest.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    private Integer senderUserId;
    private String phoneNumber;
    private String message;
    private Integer roomId;
}
