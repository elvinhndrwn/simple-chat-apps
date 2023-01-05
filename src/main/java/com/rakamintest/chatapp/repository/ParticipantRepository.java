package com.rakamintest.chatapp.repository;

import com.rakamintest.chatapp.model.ParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantModel, Integer> {
}
