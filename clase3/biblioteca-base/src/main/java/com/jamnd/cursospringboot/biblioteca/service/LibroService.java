package com.jamnd.cursospringboot.biblioteca.service;

import com.jamnd.cursospringboot.biblioteca.exception.LibroNoEncontradoException;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
               .orElseThrow(() -> new LibroNoEncontradoException(id));


    }

    public Libro crear(Libro libro) {
        libro.setId(null);
        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, Libro libro) {
        Libro actual = obtenerPorId(id);
        actual.setTitulo(libro.getTitulo());
        actual.setAutor(libro.getAutor());
        actual.setIsbn(libro.getIsbn());
        actual.setAnio(libro.getAnio());
        actual.setDisponible(libro.getDisponible());
        return libroRepository.save(actual);
    }

    public void eliminar(Long id) {
        boolean eliminado = libroRepository.deleteById(id);
        if (!eliminado) {
            throw new LibroNoEncontradoException(id);
        }
    }

    public void marcarNoDisponible(Long id) {
        Libro libro = obtenerPorId(id);
        libro.setDisponible(false);
        libroRepository.save(libro);
    }
}
