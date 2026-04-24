package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryLibroRepository implements LibroRepository {

    private final Map<Long, Libro> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public InMemoryLibroRepository() {
        save(new Libro(null, "Java Basico", "Autor Demo", "978000000001", 2020, true));
        save(new Libro(null, "Spring Boot Inicial", "Autor Demo", "978000000002", 2022, true));
        save(new Libro(null, "Patrones Backend", "Autor Demo", "978000000003", 2023, true));
    }

    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(sequence.incrementAndGet());
        }
        data.put(libro.getId(), libro);
        return libro;
    }

    @Override
    public boolean deleteById(Long id) {
        return data.remove(id) != null;
    }
}
