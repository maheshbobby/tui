package com.tui.handler.messages;

import org.springframework.http.HttpStatus;

public class InternalErrorMessagesFactory extends ErrorMessagesFactory {

    @Override
    public <E extends ErrorMessages> E getObj(String code, String msg, HttpStatus status) {
        return (E) new InternalErrorMessages(code, msg, status);
    }
}
