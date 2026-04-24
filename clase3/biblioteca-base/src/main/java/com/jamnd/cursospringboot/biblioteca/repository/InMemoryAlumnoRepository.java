package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Alumno;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAlumnoRepository implements AlumnoRepository {

    private final Map<Long, Alumno> data = new ConcurrentHashMap<>();

    public InMemoryAlumnoRepository() {
        data.put(1L, new Alumno(1L, "Andrea", false));
        data.put(2L, new Alumno(2L, "Luis", true));
        data.put(3L, new Alumno(3L, "Carlos", false));
    }

    @Override
    public Optional<Alumno> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }
}
