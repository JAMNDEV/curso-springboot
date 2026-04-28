package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
