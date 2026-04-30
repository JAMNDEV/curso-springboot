package com.jamnd.cursospringboot.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        // Lamina 12: base para consumir servicios externos con RestTemplate.
        return new RestTemplate();
    }
}
