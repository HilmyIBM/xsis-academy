package com.xsis.hello.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.hello.entity.HelloWorld;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;

// Buat API
@RestController
public class HelloController {
    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s!";

    @GetMapping("/hello")
    public HelloWorld hello(@RequestParam(value = "nama", defaultValue = "Dunia") String nama) {
        return new HelloWorld(counter.incrementAndGet(), String.format(template, nama));
    }
}
