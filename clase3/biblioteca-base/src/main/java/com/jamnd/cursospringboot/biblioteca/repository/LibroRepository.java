package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroRepository {

    List<Libro> findAll();

    Optional<Libro> findById(Long id);

    Libro save(Libro libro);

    boolean deleteById(Long id);
}
