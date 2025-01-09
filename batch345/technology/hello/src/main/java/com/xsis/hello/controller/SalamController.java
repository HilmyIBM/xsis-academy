package com.xsis.hello.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.hello.entity.Salam;

@RestController
public class SalamController {
    private final AtomicLong counter = new AtomicLong();
    private static final String template = "Hello, %s"; 

    @GetMapping("/")//home
    public Salam salam(@RequestParam(value = "nama" ,defaultValue = "Dunia") String nama) { //kalo banyak (@RequestParam String nama, @RequestParam String kota)
    //public Salam salam(@RequestParam(defaultValue = "Dunia") String nama) value ke grey karena value dan nama variable sama, jadi bisa ga dikasih value

        return new Salam(counter.incrementAndGet(), String.format(template, nama));
    }
}
