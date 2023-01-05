package com.rakamintest.chatapp.controller;

import com.rakamintest.chatapp.dto.UserRequestDto;
import com.rakamintest.chatapp.dto.UserResponseDto;
import com.rakamintest.chatapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserResponseDto login(@RequestBody UserRequestDto userRequestDto){
        return userService.login(userRequestDto);
    }
}
