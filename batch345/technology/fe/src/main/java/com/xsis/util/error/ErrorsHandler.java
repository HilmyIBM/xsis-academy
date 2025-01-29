package com.xsis.util.error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@ControllerAdvice
public class ErrorsHandler {

    private static final Log log = LogFactory.getLog(ErrorsHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleHttpClientErr(HttpClientErrorException ex) {
        ErrorMessage err = ex.getResponseBodyAs(ErrorMessage.class);

        log.trace("============ HttpClientError =============");
        log.trace(Objects.requireNonNull(err).toString());
        log.trace("========= End of HttpClientError =========");

        return new ResponseEntity<>(err, ex.getStatusCode());
    }

    @ExceptionHandler
    public void handleRuntimeExc(RuntimeException ex) {
        log.trace("======= RuntimeException ========");
        log.trace(ex.getMessage());
        log.trace("==== End of RuntimeException ====");
    }

    @ExceptionHandler
    public void handleGeneralException(Exception ex) {
        log.trace("======== General Exception =======");
        log.trace(ex.getMessage());
        log.trace("==== End of General Exception ====");
    }

}
