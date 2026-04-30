package com.jamnd.cursospringboot.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bibliotecaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Biblioteca Digital - Clase 5")
                        .version("5.0.0")
                        .description("Documentacion OpenAPI para pruebas completas de endpoints de libros y prestamos. " +
                                "Incluye casos positivos y negativos con codigos 200/201/204/400/404/409/500.")
                        .contact(new Contact()
                                .name("JAMNDEV by Axity")
                                .email("jorge.martinez@axity.com")
                                .url("https://github.com/JAMNDEV"))
                        .license(new License()
                                .name("Uso interno de curso")
                                .url("https://axity.com")));
    }
}
