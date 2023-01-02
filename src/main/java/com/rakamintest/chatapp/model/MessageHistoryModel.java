package com.rakamintest.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sender_user_id")
    private int senderUserId;

    @Column(name = "receiver_user_id")
    private int receiverUserId;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "message")
    private String message;
}
