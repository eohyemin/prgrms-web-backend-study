package com.github.prgrms.socialserver.service.exception;

import com.github.prgrms.socialserver.common.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String s) {
        super(s);
    }
}
