package com.rakamintest.chatapp.service;

import com.rakamintest.chatapp.dto.UserRequestDto;
import com.rakamintest.chatapp.dto.UserResponseDto;
import com.rakamintest.chatapp.model.UserModel;
import com.rakamintest.chatapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto login(UserRequestDto request){
        try {
            String password = DigestUtils.md5Hex(request.getPassword());
            log.info("pass hex: {}", password);
            UserModel userModel = userRepository.findByPhoneNumberAndPassword(request.getPhoneNumber(), password);
            if(userModel != null){
                return UserResponseDto.builder()
                        .user(userModel)
                        .build();
            }
            return null;
        }catch (Exception e){
            log.error("error in login service: ", e);
            throw e;
        }
    }
}
