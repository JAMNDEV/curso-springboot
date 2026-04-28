package com.jamnd.cursospringboot.biblioteca.exception;

public class AlumnoNoEncontradoException extends RuntimeException {

    public AlumnoNoEncontradoException(Long id) {
        super("No se encontro el alumno con id " + id);
    }
}
