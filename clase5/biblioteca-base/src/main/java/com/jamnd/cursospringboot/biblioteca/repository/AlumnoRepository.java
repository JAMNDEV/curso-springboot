package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
