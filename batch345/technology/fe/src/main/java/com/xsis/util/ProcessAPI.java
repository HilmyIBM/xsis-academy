package com.xsis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class ProcessAPI<DATA, RESP> {
    private static final Logger log = LoggerFactory.getLogger(ProcessAPI.class);
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<?> send(DATA model, Class<RESP> response, HttpMethod method, HttpStatus successStatus, String url) {
        ResponseEntity<RESP> apiResponse;

        var header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(model, header);

        apiResponse = restTemplate.exchange(url, method, httpEntity, response);
        log.info("Request from {}", model.getClass().getName());

        if (apiResponse.getStatusCode().is2xxSuccessful()) {
            log.info("Method {} return with success response code {}", method.name(), apiResponse.getStatusCode());
            return new ResponseEntity<>(apiResponse.getBody(), apiResponse.getStatusCode());
        }

        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, Objects.requireNonNull(apiResponse.getBody()).toString());
    }

}
