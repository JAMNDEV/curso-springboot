package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Alumno;

import java.util.Optional;

public interface AlumnoRepository {

    Optional<Alumno> findById(Long id);
}
