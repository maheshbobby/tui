package com.tui.exception;

import com.tui.handler.messages.ErrorMessages;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {

    /**
     * created
     */
    private static final long serialVersionUID = 1L;


    public UserNotFoundException(String customMsg) {
        super(customMsg);
    }


}
