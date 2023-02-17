package com.github.prgrms.socialserver.controller.dto.response;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public UserResponseDto(Builder builder) {
        this.seq = builder.seq;
        this.email = builder.email;
        this.passwd = builder.passwd;
        this.loginCount = builder.loginCount;
        this.lastLoginDate = builder.lastLoginDate;
        this.createAt = builder.createAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return loginCount == that.loginCount && Objects.equals(seq, that.seq) && Objects.equals(email, that.email) && Objects.equals(passwd, that.passwd) && Objects.equals(lastLoginDate, that.lastLoginDate) && Objects.equals(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, email, passwd, loginCount, lastLoginDate, createAt);
    }

    public static class Builder {
        private Long seq;
        private String email;
        private String passwd;
        private int loginCount;
        private LocalDateTime lastLoginDate;
        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder passwd(String passwd) {
            this.passwd = passwd;
            return this;
        }

        public Builder loginCount(int loginCount) {
            this.loginCount = loginCount;
            return this;
        }

        public Builder lastLoginDate(LocalDateTime lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public UserResponseDto build() {
            return new UserResponseDto(this);
        }

    }
}
