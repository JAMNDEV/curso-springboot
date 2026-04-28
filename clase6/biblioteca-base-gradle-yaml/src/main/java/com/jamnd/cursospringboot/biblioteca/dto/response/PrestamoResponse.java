package com.jamnd.cursospringboot.biblioteca.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "PrestamoResponse", description = "Datos de salida de un prestamo")
public class PrestamoResponse {

    @Schema(description = "ID del prestamo", example = "10")
    private Long id;

    @Schema(description = "ID del libro prestado", example = "1")
    private Long libroId;

    @Schema(description = "Titulo del libro prestado", example = "Java Basico")
    private String libroTitulo;

    @Schema(description = "ID del alumno", example = "1")
    private Long alumnoId;

    @Schema(description = "Nombre completo del alumno", example = "Andrea Lopez")
    private String alumnoNombre;

    @Schema(description = "Fecha en que se registra el prestamo", example = "2026-04-27")
    private LocalDate fechaPrestamo;

    @Schema(description = "Fecha estimada para devolucion", example = "2026-05-04")
    private LocalDate fechaDevolucionEstimada;

    public PrestamoResponse(Long id,
                            Long libroId,
                            String libroTitulo,
                            Long alumnoId,
                            String alumnoNombre,
                            LocalDate fechaPrestamo,
                            LocalDate fechaDevolucionEstimada) {
        this.id = id;
        this.libroId = libroId;
        this.libroTitulo = libroTitulo;
        this.alumnoId = alumnoId;
        this.alumnoNombre = alumnoNombre;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
    }

    public Long getId() {
        return id;
    }

    public Long getLibroId() {
        return libroId;
    }

    public String getLibroTitulo() {
        return libroTitulo;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public String getAlumnoNombre() {
        return alumnoNombre;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEstimada() {
        return fechaDevolucionEstimada;
    }
}
