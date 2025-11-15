package com.angeldevs.eventquoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EventQuoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventQuoteServiceApplication.class, args);
    }

}
