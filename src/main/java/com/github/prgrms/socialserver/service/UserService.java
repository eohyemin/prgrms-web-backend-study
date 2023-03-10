package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.controller.dto.request.UserJoinRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.controller.dto.response.UserResponseDto;
import com.github.prgrms.socialserver.domain.model.User;
import com.github.prgrms.socialserver.domain.repository.UserRepository;
import com.github.prgrms.socialserver.service.exception.EmailDuplicateException;
import com.github.prgrms.socialserver.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new EmailDuplicateException(userJoinRequestDto.getPrincipal());
        }
        User newUser = UserAssembler.toEntity(userJoinRequestDto);
        userRepository.save(newUser);
        return new UserJoinResponseDto(true, "가입완료");
    }

    public boolean isEnrollEmail(UserJoinRequestDto userJoinRequestDto) {
        return userRepository.existByEmail(userJoinRequestDto.getPrincipal());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return Collections.unmodifiableList(users.stream()
                .map(user -> UserAssembler.toDto(user))
                .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return UserAssembler.toDto(user.orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다.")));
    }

}
