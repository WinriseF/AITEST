package com.java.aitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.stringtemplate.v4.ST;

@SpringBootApplication
@EnableAsync
public class AitestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AitestApplication.class, args);
    }

}
