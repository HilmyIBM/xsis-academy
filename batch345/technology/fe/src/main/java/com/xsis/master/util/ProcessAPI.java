package com.xsis.master.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Component
public class ProcessAPI<DATA, RESP> {
    private static final Logger log = LoggerFactory.getLogger(ProcessAPI.class);
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> send(DATA model, Class<RESP> response, HttpMethod method, HttpStatus successStatus, String url) {
        ResponseEntity<RESP> apiResponse;

        try {
            var header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            var httpEntity = new HttpEntity<>(model, header);

            apiResponse = restTemplate.exchange(url, method, httpEntity, response);

            if (apiResponse.getStatusCode().is2xxSuccessful()) {
                log.info("Request from {}", model.getClass().getName());
                log.info("Method {} return with success response code {}", method.name(), apiResponse.getStatusCode());
                return new ResponseEntity<>(apiResponse.getBody(), apiResponse.getStatusCode());
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(apiResponse.getBody()).toString());

        } catch (HttpClientErrorException e) {
            ErrorModel err = e.getResponseBodyAs(ErrorModel.class);
            log.error("Client Error: {}" ,Objects.requireNonNull(err));
            return new ResponseEntity<>(err, HttpStatus.valueOf(err.getStatus()));
        } catch (Exception ex) {
            log.error("Error: {}", ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
