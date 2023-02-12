package com.github.prgrms.socialserver.domain.model;

import java.time.LocalDateTime;

public class User {

    private Long seq;
    private String email;
    private String passwd;
    private int loginCount;
    private LocalDateTime lastLoginDate;
    private LocalDateTime createAt;

    public User() {}

    public User(Long seq, String email, String passwd, int loginCount, LocalDateTime lastLoginDate, LocalDateTime createAt) {
        this.seq = seq;
        this.email = email;
        this.passwd = passwd;
        this.loginCount = loginCount;
        this.lastLoginDate = lastLoginDate;
        this.createAt = createAt;
    }

    public User(Builder builder) {
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

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public static class Builder {
        private Long seq;
        private String email;
        private String passwd;
        private int loginCount;
        private LocalDateTime lastLoginDate;
        private LocalDateTime createAt;

        public Builder(String email, String passwd) {
            this.email = email;
            this.passwd = passwd;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
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

        public User build() {
            return new User(this);
        }

    }

}
