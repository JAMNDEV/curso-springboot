package com.jamnd.cursospringboot.biblioteca.client;

import com.jamnd.cursospringboot.biblioteca.dto.response.LibroResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LibroCatalogoRestTemplateClient implements LibroCatalogoClient {

    private static final Logger log = LoggerFactory.getLogger(LibroCatalogoRestTemplateClient.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public LibroCatalogoRestTemplateClient(RestTemplate restTemplate,
                                           @Value("${catalogo.libros.base-url:http://localhost:8080}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public boolean verificarDisponibilidad(Long libroId) {
        try {
            String endpoint = baseUrl + "/api/libros/{id}";
            ResponseEntity<LibroResponse> respuesta = restTemplate.getForEntity(endpoint, LibroResponse.class, libroId);
            LibroResponse body = respuesta.getBody();
            return body != null && Boolean.TRUE.equals(body.getDisponible());
        } catch (Exception ex) {
            log.warn("No fue posible consultar disponibilidad en catalogo externo. libroId={} detalle={}", libroId, ex.getMessage());
            return false;
        }
    }
}
