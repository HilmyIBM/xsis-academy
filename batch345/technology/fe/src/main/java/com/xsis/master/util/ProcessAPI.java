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
public class ProcessAPI<T> {

    private String API_URL;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate = new RestTemplate();


    public void setAPIURL(String API_URL) {
        this.API_URL = API_URL;
    }

    public ResponseEntity<?> send(T model, Class<T> response, HttpMethod method, HttpStatus successStatus) {
        ResponseEntity<T> apiResponse;

        try {
            var header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);

            var httpEntity = new HttpEntity<>(model, header);

            apiResponse = restTemplate.exchange(this.API_URL, method, httpEntity, response);

            if (apiResponse.getStatusCode() == successStatus) {
                log.info("Request Code: {}", apiResponse.getStatusCode());
                return new ResponseEntity<>(apiResponse.getBody(), successStatus);
            }


            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Objects.requireNonNull(apiResponse.getBody()).toString());

        } catch (HttpClientErrorException e) {
            ErrorModel err = e.getResponseBodyAs(ErrorModel.class);
            log.error("Client Error: {}" ,Objects.requireNonNull(err));
            return new ResponseEntity<>(err, HttpStatus.valueOf(err.getStatus()));
        } catch (Exception ex) {
            log.error("Error: {}", ex.getMessage());
            log.error("Cause: {}", ex.getCause().toString());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
