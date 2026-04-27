package com.jamnd.cursospringboot.biblioteca.dto.response;

import java.time.LocalDate;

public class PrestamoResponse {

    private Long id;
    private Long libroId;
    private String libroTitulo;
    private Long alumnoId;
    private String alumnoNombre;
    private LocalDate fechaPrestamo;
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
