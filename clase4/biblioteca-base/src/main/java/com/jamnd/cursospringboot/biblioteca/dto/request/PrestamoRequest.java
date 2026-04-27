package com.jamnd.cursospringboot.biblioteca.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PrestamoRequest {

    @NotNull(message = "El libroId es obligatorio")
    @Positive(message = "El libroId debe ser positivo")
    private Long libroId;

    @NotNull(message = "El alumnoId es obligatorio")
    @Positive(message = "El alumnoId debe ser positivo")
    private Long alumnoId;

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }
}
