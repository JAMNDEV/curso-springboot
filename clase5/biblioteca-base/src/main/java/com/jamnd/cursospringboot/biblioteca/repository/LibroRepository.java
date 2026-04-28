package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    @Query("select l from Libro l where l.disponible = true")
    List<Libro> findDisponibles();
}
