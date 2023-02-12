package com.github.prgrms.socialserver.controller;

import com.github.prgrms.socialserver.controller.dto.request.UserJoinRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.controller.dto.response.UserResponseDto;
import com.github.prgrms.socialserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users/join")
    public ResponseEntity<UserJoinResponseDto> create(@RequestBody @Valid UserJoinRequestDto userJoinRequestDto) {
        UserJoinResponseDto userJoinResponseDto = userService.join(userJoinRequestDto);
        if(userJoinResponseDto.isSuccess()){
            return new ResponseEntity<>(userJoinResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userJoinResponseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> userResponseDtos = userService.findAll();
        return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/api/users/{email}")
    public ResponseEntity<UserResponseDto> getOneByEmail(@PathVariable String email) {
        UserResponseDto userResponseDto = userService.findByEmail(email);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
