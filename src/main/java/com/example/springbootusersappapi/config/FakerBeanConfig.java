package com.example.springbootusersappapi.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerBeanConfig {

    // Inyectando faker, para poder acceder de forma global
    @Bean
    public Faker getFaker() {
        return new Faker();
    }

}
