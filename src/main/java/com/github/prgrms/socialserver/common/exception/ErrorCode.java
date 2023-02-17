package com.github.prgrms.socialserver.common.exception;

public enum ErrorCode {

    ENTITY_NOT_FOUND(400, "Entity Not Found"),
    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    EMAIL_DUPLICATION(400, "Email is Duplicated");

    private int status;
    private final String message;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
