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
public class IncomingMessageResponse {
    private String senderName;
    private String message;
    private int roomId;
    private String phoneNumber;
    private Timestamp time;
}
