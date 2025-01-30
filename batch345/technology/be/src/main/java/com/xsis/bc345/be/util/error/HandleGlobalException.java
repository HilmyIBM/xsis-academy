package com.xsis.bc345.be.util.error;

import com.xsis.bc345.be.util.exception.PasswordMismatchException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandleGlobalException {

    private static final Logger log = LoggerFactory.getLogger(HandleGlobalException.class);

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleResponseException(ResponseStatusException e) {
        ErrorMessage err = new ErrorMessage(
                e.getStatusCode().value(),
                e.getBody().getDetail(),
                e.getClass().getSimpleName(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(err, e.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorMessage err = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleEntityNotExists(EntityExistsException ex) {
        ErrorMessage err = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handlePasswordMismatch(PasswordMismatchException ex) {
        ErrorMessage err = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleDataAccessException(DataAccessException e) {
        ErrorMessage err = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "InternalServerError",
                LocalDateTime.now()
        );

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
