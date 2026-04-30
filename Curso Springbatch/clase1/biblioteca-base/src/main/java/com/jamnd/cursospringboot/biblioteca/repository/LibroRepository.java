package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByAutorContainingIgnoreCase(String autor);

    List<Libro> findByDisponible(Boolean disponible);

    List<Libro> findByAutorContainingIgnoreCaseAndDisponible(String autor, Boolean disponible);

    @Query("select l from Libro l where l.disponible = true")
    List<Libro> findDisponibles();
}
