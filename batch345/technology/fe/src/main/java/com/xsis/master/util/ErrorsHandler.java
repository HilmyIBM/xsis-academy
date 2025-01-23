package com.xsis.master.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ErrorsHandler {

    private static final Log log = LogFactory.getLog(ErrorsHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorModel> handleHttpClientErr(HttpClientErrorException ex) {
        return new ResponseEntity<>(ex.getResponseBodyAs(ErrorModel.class), ex.getStatusCode());
    }

    @ExceptionHandler
    public void handleRuntimeExc(RuntimeException ex) {
        log.error(ex.getMessage());
    }

    @ExceptionHandler
    public void handleGeneralException(Exception ex) {
        log.error(ex.getMessage());
    }

}
