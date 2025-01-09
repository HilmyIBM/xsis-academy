package com.xsis.hello.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.hello.enttiy.Salam;

@RestController
public class SalamController {
    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s!"; 

    @GetMapping("/salam")
    public Salam salam(
        @RequestParam(value = "nama",  defaultValue = "Manusia") String nama
    ){
        return new Salam(
            counter.incrementAndGet(), 
            String.format(template, nama)
        );
    }
}
