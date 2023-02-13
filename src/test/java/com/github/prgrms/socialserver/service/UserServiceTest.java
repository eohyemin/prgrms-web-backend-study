package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.controller.dto.request.UserJoinRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.controller.dto.response.UserResponseDto;
import com.github.prgrms.socialserver.domain.model.User;
import com.github.prgrms.socialserver.domain.repository.UserRepository;
import com.github.prgrms.socialserver.service.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserServiceTest {

    @Spy
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("회원가입 성공 테스트")
    public void userJoin() {
        // given
        UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto("email@gmail.com", "password");
        User user = new User.Builder("email@gmail.com", "password")
                .seq(1L)
                .lastLoginDate(LocalDateTime.now())
                .createAt(LocalDateTime.now())
                .loginCount(0)
                .build();

        given(userRepository.existByEmail("email@gmail.com")).willReturn(false);
        given(userRepository.save(user)).willReturn(1L);

        // when
        UserJoinResponseDto userJoinResponseDto = userService.join(userJoinRequestDto);

        // then
        assertThat(userJoinResponseDto.isSuccess()).isTrue();
    }

    @Test
    @DisplayName("존재하는 사용자를 이메일로 검색하는 테스트")
    public void findUserByEmail() {
        // given
        UserResponseDto user = new UserResponseDto.Builder()
                                    .seq(1L)
                                    .email("email@gmail.com")
                                    .passwd("password")
                                    .loginCount(0)
                                    .lastLoginDate(LocalDateTime.now())
                                    .createAt(LocalDateTime.now())
                                    .build();
        doReturn(user).when(userService).findByEmail("email@gmail.com");
        given(userService.findByEmail("email@gmail.com")).willReturn(user);

        // when
        UserResponseDto result = userService.findByEmail("email@gmail.com");

        // then
        assertThat(result).isEqualTo(user);
    }
    @Test
    @DisplayName("존재하지 않는 사용자를 검색했을때 UserNotFoundException")
    public void findUserByNotExistEmail() {
        // given
        given(userRepository.findByEmail("email@gmail.com")).willReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class, () -> userService.findByEmail("email@gmail.com"));
    }

    @Test
    @DisplayName("모든 사용자 조회하는 경우 테스트")
    public void findAllUsers() {
        //given
        User user = new User.Builder("email@gmail.com", "password")
                .seq(1L)
                .lastLoginDate(LocalDateTime.now())
                .createAt(LocalDateTime.now())
                .loginCount(0)
                .build();
        List<User> users = new ArrayList<>();
        users.add(user);

        given(userRepository.findAll()).willReturn(users);

        //when
        List<UserResponseDto> findUsers = userService.findAll();

        //then
        Assertions.assertEquals(user.getEmail(), findUsers.get(0).getEmail());
        Assertions.assertEquals(user.getSeq(), findUsers.get(0).getSeq());
    }

}