package com.jamnd.cursospringboot.biblioteca.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "PrestamoRequest", description = "Datos de entrada para registrar un prestamo")
public class PrestamoRequest {

    @NotNull(message = "El libroId es obligatorio")
    @Positive(message = "El libroId debe ser positivo")
    @Schema(description = "ID del libro a prestar", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long libroId;

    @NotNull(message = "El alumnoId es obligatorio")
    @Positive(message = "El alumnoId debe ser positivo")
    @Schema(description = "ID del alumno que solicita el prestamo", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
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
