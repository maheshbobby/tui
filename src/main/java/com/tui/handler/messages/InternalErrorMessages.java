package com.tui.handler.messages;

import org.springframework.http.HttpStatus;

import java.util.Map;


public class InternalErrorMessages extends ErrorMessages {

    private static final String INTERNAL_ERROR_FILE = "InternalErrors";

    public static Map<String, InternalErrorMessages> codeToErrorMessageMap = new InternalErrorMessagesFactory().transformErrorMessagesFileToMap(INTERNAL_ERROR_FILE);

    protected InternalErrorMessages(String code, String msg, HttpStatus status) {
        super(code, msg, status);
    }

    public static InternalErrorMessages contains(String code) {
        return codeToErrorMessageMap.get(code);
    }

}