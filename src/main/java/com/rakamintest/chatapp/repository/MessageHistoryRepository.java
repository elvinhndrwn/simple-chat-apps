package com.rakamintest.chatapp.repository;

import com.rakamintest.chatapp.model.MessageHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageHistoryRepository extends JpaRepository<MessageHistoryModel, Integer> {

}
