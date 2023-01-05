package com.rakamintest.chatapp.repository;

import com.rakamintest.chatapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findById(int id);
    Optional<UserModel> findByPhoneNumber(String phoneNumber);
    UserModel findByPhoneNumberAndPassword(String phoneNumber, String pw);
}
