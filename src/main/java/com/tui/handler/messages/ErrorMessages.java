package com.tui.handler.messages;

import org.springframework.http.HttpStatus;


public class ErrorMessages {

    private String code;
    private String msg;
    private HttpStatus status;

    protected ErrorMessages(String code, String msg, HttpStatus status) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}