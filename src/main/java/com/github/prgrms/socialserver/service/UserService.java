package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.controller.dto.request.UserRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.controller.dto.response.UserResponseDto;
import com.github.prgrms.socialserver.domain.model.User;
import com.github.prgrms.socialserver.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserJoinResponseDto join(UserRequestDto userRequestDto) {
        if(isEnrollEmail(userRequestDto)) {
            return new UserJoinResponseDto(false, "이미 존재하는 이메일입니다.");
        }
        User newUser = userRequestDto.toEntity();
        userRepository.save(newUser);
        return new UserJoinResponseDto(true, "가입완료");
    }

    public boolean isEnrollEmail(UserRequestDto userRequestDto) {
        return userRepository.existByEmail(userRequestDto.getEmail());
    }

}
