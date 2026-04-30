package com.jamnd.cursospringboot.biblioteca.mapper;

import com.jamnd.cursospringboot.biblioteca.dto.request.LibroRequest;
import com.jamnd.cursospringboot.biblioteca.dto.response.LibroResponse;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {

    public LibroResponse toResponse(Libro libro) {
        return new LibroResponse(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getAnio(),
                libro.getDisponible()
        );
    }

    public Libro toEntity(LibroRequest request) {
        return new Libro(
                null,
                request.getTitulo(),
                request.getAutor(),
                request.getIsbn(),
                request.getAnio(),
                request.getDisponible()
        );
    }
}
