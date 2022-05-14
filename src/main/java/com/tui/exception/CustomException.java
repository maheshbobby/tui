package com.tui.exception;

import com.tui.handler.messages.ErrorMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


public class CustomException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomException.class);

    /**
     * created
     */
    private static final long serialVersionUID = 1L;
    private static final String STATUS_CODE = "Status code:";

    private final String customErrorMsg;
    private final String errMsg;

    private final String errCode;
    private final String errMsgDscr;

    private final Exception originalException;
    private final HttpStatus statusCode;

    protected CustomException(ErrorMessages error, String customMsg, CustomException e) {
        super(e);
        LOGGER.warn("Invalid InternalOBException usage, please refrain from re-wrapping core exception.");
        this.originalException = e;
        this.customErrorMsg = e.getCustomErrorMsg();
        this.errCode = e.getErrCode();
        this.errMsgDscr = e.getErrMsgDescr();
        this.errMsg = e.getErrMsg();
        this.statusCode = e.getStatusCode();
    }

    protected CustomException(ErrorMessages error, String customMsg, Exception e) {
        super(e);
        this.customErrorMsg = customMsg;
        this.errCode = error.getCode();
        this.errMsgDscr = error.getMsg();
        this.errMsg = error.getMsg();
        this.originalException = e;
        this.statusCode = error.getStatus();
    }

    protected CustomException(ErrorMessages error, String customMsg) {
        super(customMsg);
        this.customErrorMsg = customMsg;
        this.errCode = error.getCode();
        this.errMsgDscr = error.getMsg();
        this.errMsg = error.getMsg();
        this.statusCode = error.getStatus();
        this.originalException = null;
    }

    public String getCustomErrorMsg() {
        return customErrorMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public Exception getOriginalException() {
        return originalException;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsgDescr() {
        return errMsgDscr;
    }
}
