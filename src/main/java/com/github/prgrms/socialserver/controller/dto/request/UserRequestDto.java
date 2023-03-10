package com.github.prgrms.socialserver.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UserRequestDto {
    private Long seq;
    @NotBlank
    private String email;
    @NotBlank
    private String passwd;
    private int loginCount;
    private LocalDateTime lastLoginDate;
    private LocalDateTime createAt;

    public UserRequestDto() {}

    public UserRequestDto(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public UserRequestDto(Long seq, String email, String passwd, int loginCount, LocalDateTime lastLoginDate, LocalDateTime createAt) {
        this.seq = seq;
        this.email = email;
        this.passwd = passwd;
        this.loginCount = loginCount;
        this.lastLoginDate = lastLoginDate;
        this.createAt = createAt;
    }

    public Long getSeq() {
        return seq;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

}
