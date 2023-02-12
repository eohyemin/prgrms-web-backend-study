package com.github.prgrms.socialserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prgrms.socialserver.controller.dto.request.UserRequestDto;
import com.github.prgrms.socialserver.controller.dto.response.UserJoinResponseDto;
import com.github.prgrms.socialserver.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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

        // when & then
        String jsonData = "{\n\"email\":\"eohyemin@gmail.com\",\n\"passwd\":\"123456\"\n}";
        mockMvc.perform(post("/api/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("중복 이메일인 경우 회원가입 실패")
    void createFailTestByDuplicateEmail() throws Exception {

        // given
        userService.join(new UserRequestDto("eohyemin@gmail.com", "123456"));
        UserRequestDto userRequestDto = new UserRequestDto("eohyemin@gmail.com", "123456");
        given(userService.join(any()))
                .willReturn(new UserJoinResponseDto(false, "이미 존재하는 이메일입니다."));

        // when & then
        String jsonData = objectMapper.writeValueAsString(userRequestDto);
        mockMvc.perform(post("/api/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isBadRequest());

    }
}