package com.java.aitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class AitestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AitestApplication.class, args);
    }

}
