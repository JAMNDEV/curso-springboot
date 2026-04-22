package com.jamnd.cursospringboot.biblioteca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @GetMapping
    public List<Map<String, Object>> obtenerLibrosDisponibles() {
        // Comentario guia: respuesta simple para mostrar flujo API -> JSON.
        return List.of(
                Map.of("titulo", "Java Basico", "disponible", true),
                Map.of("titulo", "Spring Boot Inicial", "disponible", true)
        );
    }
}
