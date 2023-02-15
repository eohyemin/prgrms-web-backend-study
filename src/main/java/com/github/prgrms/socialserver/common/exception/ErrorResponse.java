package com.github.prgrms.socialserver.common.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;

    protected ErrorResponse(){}

    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
    }

    private ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final List<FieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public FieldError(){}

        public String getField() {
            return field;
        }

        public String getValue() {
            return value;
        }

        public String getReason() {
            return reason;
        }
    }
}
