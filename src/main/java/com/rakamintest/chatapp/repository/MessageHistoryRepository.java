package com.rakamintest.chatapp.repository;

import com.rakamintest.chatapp.dto.GetMessageHistoryListResponse;
import com.rakamintest.chatapp.model.MessageHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageHistoryRepository extends JpaRepository<MessageHistoryModel, Integer> {
    @Query(value = "SELECT \n" +
            "message_history.id AS Id,\n" +
            "message_history.room_id AS RoomId,\n" +
            "message_history.time AS Time,\n" +
            "participant.user_two AS UserTwo\n" +
            "FROM message_history\n" +
            "JOIN room ON room.id=message_history.room_id\n" +
            "JOIN participant ON participant.id=room.participant_id\n" +
            "WHERE participant.user_one = :id OR participant.user_two = :id\n" +
            "GROUP BY message_history.room_id", nativeQuery = true)
    List<GetMessageHistoryListResponse> showRoomChat(int id);

    @Query(value = "SELECT message_history.message AS Message, message_history.time AS Time FROM message_history\n" +
            "WHERE message_history.room_id=:roomId\n" +
            "ORDER BY time DESC LIMIT 1", nativeQuery = true)
    GetMessageHistoryListResponse showCurrentMessageByRoomId(int roomId);

    @Query(value = "SELECT \n" +
            "message_history.sender_user_id AS SenderUserId,\n" +
            "message_history.message AS Message, \n" +
            "message_history.time AS Time\n" +
            "FROM participant\n" +
            "JOIN room ON room.participant_id=participant.id\n" +
            "JOIN message_history ON message_history.room_id=room.id\n" +
            "WHERE message_history.room_id = :roomId\n" +
            "ORDER BY message_history.time DESC", nativeQuery = true)
    List<GetMessageHistoryListResponse> viewMessage(int roomId);

    @Query(value = "SELECT user.user_full_name AS Name, user.phone_number AS PhoneNumber " +
            "FROM room JOIN participant ON participant.id=room.participant_id " +
            "JOIN message_history ON message_history.room_id=room.id " +
            "JOIN user ON user.id=message_history.sender_user_id " +
            "WHERE user.id != :id AND message_history.room_id = :roomId LIMIT 1", nativeQuery = true)
    GetMessageHistoryListResponse getContactName(int roomId, int id);
}
