package com.github.prgrms.socialserver.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UserJoinRequestDto {

    @NotBlank
    private String principal;
    @NotBlank
    private String credentials;

    public UserJoinRequestDto() {}

    public UserJoinRequestDto(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getCredentials() {
        return credentials;
    }
}
