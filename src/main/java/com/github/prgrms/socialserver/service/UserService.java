package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.controller.dto.request.UserJoinRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.controller.dto.response.UserResponseDto;
import com.github.prgrms.socialserver.domain.model.User;
import com.github.prgrms.socialserver.domain.repository.UserRepository;
import com.github.prgrms.socialserver.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserJoinResponseDto join(UserJoinRequestDto userJoinRequestDto) {
        if(isEnrollEmail(userJoinRequestDto)) {
            return new UserJoinResponseDto(false, "이미 존재하는 이메일입니다.");
        }
        User newUser = UserAssembler.toEntity(userJoinRequestDto);
        userRepository.save(newUser);
        return new UserJoinResponseDto(true, "가입완료");
    }

    public boolean isEnrollEmail(UserJoinRequestDto userJoinRequestDto) {
        return userRepository.existByEmail(userJoinRequestDto.getPrincipal());
    }

    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return Collections.unmodifiableList(users.stream()
                .map(user -> UserAssembler.toDto(user))
                .collect(Collectors.toList()));
    }

    public UserResponseDto findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return UserAssembler.toDto(user.orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다.")));
    }

}
