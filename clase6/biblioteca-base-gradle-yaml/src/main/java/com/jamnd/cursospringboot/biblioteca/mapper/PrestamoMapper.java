package com.jamnd.cursospringboot.biblioteca.mapper;

import com.jamnd.cursospringboot.biblioteca.dto.response.PrestamoResponse;
import com.jamnd.cursospringboot.biblioteca.model.Prestamo;
import org.springframework.stereotype.Component;

@Component
public class PrestamoMapper {

    public PrestamoResponse toResponse(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getLibro().getId(),
                prestamo.getLibro().getTitulo(),
                prestamo.getAlumno().getId(),
                prestamo.getAlumno().getNombre() + " " + prestamo.getAlumno().getApellido(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucionEstimada()
        );
    }
}
