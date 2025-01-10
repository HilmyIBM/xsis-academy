package com.laqqueta.hello.Controller;

import com.laqqueta.hello.Entity.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GreetingController {

    private final AtomicInteger atomicInteger = new AtomicInteger(Integer.MAX_VALUE-50_000  );

    @GetMapping("/")
    public Greeting salam(
            @RequestParam(name = "name", defaultValue = "bapakkau") String name
    ) {
        String formatName = "Hai, %s!";
        return new Greeting(
                atomicInteger.incrementAndGet(),
                formatName.formatted(name)
        );
    }
}
