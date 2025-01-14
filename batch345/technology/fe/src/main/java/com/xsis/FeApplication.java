package com.xsis;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

@SpringBootApplication
public class FeApplication {


    public static void main(String[] args) {
        SpringApplication.run(FeApplication.class, args);
    }

}
