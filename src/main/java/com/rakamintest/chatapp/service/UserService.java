package com.rakamintest.chatapp.service;

import com.rakamintest.chatapp.dto.UserRequestDto;
import com.rakamintest.chatapp.dto.UserResponseDto;
import com.rakamintest.chatapp.exception.GenericException;
import com.rakamintest.chatapp.model.UserModel;
import com.rakamintest.chatapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            if(userModel == null){
                throw new GenericException(HttpStatus.NOT_FOUND, "Phone number not found");
            }
            return UserResponseDto.builder()
                    .user(userModel)
                    .build();
        }catch (GenericException e){
            log.error("Generic exception: {}", e.getMessage());
            throw new GenericException(e.getHttpStatus(), e.getMessage());
        } catch (Exception e){
            log.error("error in login service: ", e);
            throw e;
        }
    }

    public UserResponseDto register(UserRequestDto userRequestDto) {
        try {
            var checkUser = userRepository.findByPhoneNumber(userRequestDto.getPhoneNumber());
            if (checkUser.isEmpty()) {
                var saveUser = userRepository.save(UserModel.builder()
                        .userFullName(userRequestDto.getUserFullName())
                        .phoneNumber(userRequestDto.getPhoneNumber())
                        .password(DigestUtils.md5Hex(userRequestDto.getPassword()))
                        .build());
                return UserResponseDto.builder()
                        .user(saveUser)
                        .build();
            }
            throw new GenericException(HttpStatus.BAD_REQUEST, "Phone number is already exist");

        }catch (GenericException e){
            log.error("Generic exception: {}", e.getMessage());
            throw new GenericException(e.getHttpStatus(), e.getMessage());
        } catch (Exception e){
            log.error("error in login service: ", e);
            throw e;
        }
    }
}
