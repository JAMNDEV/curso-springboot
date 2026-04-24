package com.jamnd.cursospringboot.biblioteca.repository;

import com.jamnd.cursospringboot.biblioteca.model.Prestamo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPrestamoRepository implements PrestamoRepository {

    private final Map<Long, Prestamo> data = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            prestamo.setId(sequence.incrementAndGet());
        }
        data.put(prestamo.getId(), prestamo);
        return prestamo;
    }
}
