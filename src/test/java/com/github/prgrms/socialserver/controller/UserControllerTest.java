package com.github.prgrms.socialserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prgrms.socialserver.controller.dto.request.UserJoinRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @DisplayName("회원가입 성공")
    void createSuccessTest() throws Exception {
        // given
        given(userService.join(any()))
                .willReturn(new UserJoinResponseDto(true, "가입완료"));

        UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto("email@gmail.com", "123456");
        String jsonData = objectMapper.writeValueAsString(userJoinRequestDto);

        // when & then
        mockMvc.perform(post("/api/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("중복 이메일인 경우 회원가입 실패")
    void createFailTestByDuplicateEmail() throws Exception {

        // given
        userService.join(new UserJoinRequestDto("email@gmail.com", "123456"));
        UserJoinRequestDto userJoinRequestDto = new UserJoinRequestDto("email@gmail.com", "123456");
        given(userService.join(any()))
                .willReturn(new UserJoinResponseDto(false, "이미 존재하는 이메일입니다."));

        // when & then
        String jsonData = objectMapper.writeValueAsString(userJoinRequestDto);
        mockMvc.perform(post("/api/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isBadRequest());

    }
}