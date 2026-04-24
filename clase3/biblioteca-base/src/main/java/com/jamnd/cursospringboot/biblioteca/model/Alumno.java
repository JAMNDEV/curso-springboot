package com.jamnd.cursospringboot.biblioteca.model;

public class Alumno {

    private Long id;
    private String nombre;
    private Boolean deudaPendiente;

    public Alumno() {
    }

    public Alumno(Long id, String nombre, Boolean deudaPendiente) {
        this.id = id;
        this.nombre = nombre;
        this.deudaPendiente = deudaPendiente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getDeudaPendiente() {
        return deudaPendiente;
    }

    public void setDeudaPendiente(Boolean deudaPendiente) {
        this.deudaPendiente = deudaPendiente;
    }
}
