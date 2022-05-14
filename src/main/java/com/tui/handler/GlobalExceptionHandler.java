package com.tui.handler;

import com.tui.customexception.InternalException;
import com.tui.exception.CustomException;
import com.tui.exception.UserNotFoundException;
import com.tui.handler.messages.InternalErrorMessages;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * The Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String INPUT_VALIDATION_ERR = "Input validation error";
    private static final String EXCEPTION = "Exception";

    private static final String INT1000 = "INT1000";
    private static final String INT1001 = "INT1001";

    /**
     * Instantiates a new Global exception handler.
     */
    public GlobalExceptionHandler() {
        // Do nothing
    }

    @ExceptionHandler({CustomException.class, InternalException.class})
    public ResponseEntity<Object> handleException(CustomException e) {

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("status", e.getStatusCode().toString());
        responseBody.put("message", e.getErrMsg());
        return new ResponseEntity<>(responseBody, (e.getStatusCode() == null ? INTERNAL_SERVER_ERROR : e.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return handleException(new InternalException(InternalErrorMessages.contains("INT1024"), EXCEPTION, e));
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Object> handleMismatchAcceptHeaderException(HttpMediaTypeNotAcceptableException e) {

        return handleException(new InternalException(
                InternalErrorMessages.contains(INT1000), INPUT_VALIDATION_ERR, e));
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleMismatchAcceptHeaderException(UserNotFoundException e) {

        return handleException(new InternalException(
                InternalErrorMessages.contains(INT1001), INPUT_VALIDATION_ERR, e));
    }

}
