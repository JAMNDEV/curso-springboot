package com.jamnd.cursospringboot.biblioteca.exception;

public class LibroNoEncontradoException extends RuntimeException {

    public LibroNoEncontradoException(Long id) {
        super("No se encontro el libro con id " + id);
    }
}
