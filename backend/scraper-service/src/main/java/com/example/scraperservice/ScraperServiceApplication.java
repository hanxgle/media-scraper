package com.example.scraperservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ScraperServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScraperServiceApplication.class, args);
    }
}
