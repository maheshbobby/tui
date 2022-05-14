package com.tui.customexception;

import com.tui.exception.CustomException;
import com.tui.handler.messages.InternalErrorMessages;

public class InternalException extends CustomException {
    private static final long serialVersionUID = 1L;

    public InternalException(InternalErrorMessages error, String customMsg, Exception e) {
        super(error, customMsg, e);
    }

    public InternalException(InternalErrorMessages error, String customMsg, InternalException e) {
        super(error, customMsg, e);
    }

    public InternalException(InternalErrorMessages error, String customMsg) {
        super(error, customMsg);
    }

}
