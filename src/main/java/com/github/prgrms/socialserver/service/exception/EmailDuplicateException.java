package com.github.prgrms.socialserver.service.exception;

import com.github.prgrms.socialserver.common.exception.ErrorCode;
import com.github.prgrms.socialserver.common.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {
    public EmailDuplicateException(String email) {
        super(email, ErrorCode.EMAIL_DUPLICATION);
    }
}
