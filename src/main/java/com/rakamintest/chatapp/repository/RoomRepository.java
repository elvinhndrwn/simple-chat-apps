package com.rakamintest.chatapp.repository;

import com.rakamintest.chatapp.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Integer> {
}
