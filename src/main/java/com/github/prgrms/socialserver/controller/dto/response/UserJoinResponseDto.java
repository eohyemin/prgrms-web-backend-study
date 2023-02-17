package com.github.prgrms.socialserver.controller.dto.response;

public class UserJoinResponseDto {

    private boolean success;
    private String message;

    public UserJoinResponseDto(){}

    public UserJoinResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
