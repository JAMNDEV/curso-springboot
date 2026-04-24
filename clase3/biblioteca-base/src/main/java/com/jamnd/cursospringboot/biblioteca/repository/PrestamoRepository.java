package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Prestamo;

import java.util.List;

public interface PrestamoRepository {

    List<Prestamo> findAll();

    Prestamo save(Prestamo prestamo);
}
