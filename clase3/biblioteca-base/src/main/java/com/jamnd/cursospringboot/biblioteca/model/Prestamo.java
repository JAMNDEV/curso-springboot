package com.jamnd.cursospringboot.biblioteca.model;

import java.time.LocalDate;

public class Prestamo {

    private Long id;
    private Long libroId;
    private Long alumnoId;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEstimada;

    public Prestamo() {
    }

    public Prestamo(Long id, Long libroId, Long alumnoId, LocalDate fechaPrestamo, LocalDate fechaDevolucionEstimada) {
        this.id = id;
        this.libroId = libroId;
        this.alumnoId = alumnoId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEstimada() {
        return fechaDevolucionEstimada;
    }

    public void setFechaDevolucionEstimada(LocalDate fechaDevolucionEstimada) {
        this.fechaDevolucionEstimada = fechaDevolucionEstimada;
    }
}
