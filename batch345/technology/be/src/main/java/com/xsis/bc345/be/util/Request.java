package com.xsis.bc345.be.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Request {

    public ResponseEntity<?> process(Optional<?> opt, HttpStatus success, HttpStatus fail) {
        try {
            if (opt.isEmpty())
                return new ResponseEntity<>(fail);

            return new ResponseEntity<>(opt.get(), success);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ErrorMessage("Error23", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
