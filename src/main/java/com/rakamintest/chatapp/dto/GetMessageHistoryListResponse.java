package com.rakamintest.chatapp.dto;

import java.sql.Timestamp;

public interface GetMessageHistoryListResponse {
    int getId();
    String getMessage();
    int getRoomId();
    int getSenderUserId();
    Timestamp getTime();
    int getParticipantId();
    int getUserOne();
    int getUserTwo();
    String getPhoneNumber();
    String getName();


}
