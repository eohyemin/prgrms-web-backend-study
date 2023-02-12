package com.github.prgrms.socialserver.controller.dto.response;

import com.github.prgrms.socialserver.controller.dto.request.UserRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class UserResponseDto {

    private Long seq;
    private String email;
    private String passwd;
    private int loginCount;
    private LocalDateTime lastLoginDate;
    private LocalDateTime createAt;

    public UserResponseDto() {}

    public UserResponseDto(Long seq, String email, String passwd, int loginCount, LocalDateTime lastLoginDate, LocalDateTime createAt) {
        this.seq = seq;
        this.email = email;
        this.passwd = passwd;
        this.loginCount = loginCount;
        this.lastLoginDate = lastLoginDate;
        this.createAt = createAt;
    }
}
