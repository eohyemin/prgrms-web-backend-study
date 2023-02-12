package com.github.prgrms.socialserver.controller;

import com.github.prgrms.socialserver.controller.dto.request.UserRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.controller.dto.response.UserResponseDto;
import com.github.prgrms.socialserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users/join")
    public ResponseEntity<UserJoinResponseDto> create(@RequestBody @Valid UserRequestDto userRequestDto) {

        UserJoinResponseDto userJoinResponseDto = userService.join(userRequestDto);
        if(userJoinResponseDto.isSuccess()){
            return new ResponseEntity<>(userJoinResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userJoinResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

}
