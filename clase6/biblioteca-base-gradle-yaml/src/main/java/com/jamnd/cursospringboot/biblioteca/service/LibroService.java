package com.jamnd.cursospringboot.biblioteca.service;

import com.jamnd.cursospringboot.biblioteca.exception.LibroNoEncontradoException;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.repository.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private static final int TAMANIO_MINIMO_PAGINA = 1;

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    public Page<Libro> listarPaginado(int pagina, int tamanio, String ordenarPor, String direccion) {
        Pageable pageable = construirPaginacion(pagina, tamanio, ordenarPor, direccion);
        return libroRepository.findAll(pageable);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> listarDisponibles() {
        return libroRepository.findDisponibles();
    }

    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException(id));
    }

    public Libro crear(Libro libro) {
        libro.setId(null);
        return libroRepository.save(libro);
    }

    public Libro actualizar(Long id, Libro libroEntrada) {
        Libro libroPersistido = obtenerPorId(id);
        copiarCamposEditables(libroPersistido, libroEntrada);
        return libroRepository.save(libroPersistido);
    }

    public void eliminar(Long id) {
        Libro libro = obtenerPorId(id);
        libroRepository.delete(libro);
    }

    public void marcarNoDisponible(Libro libro) {
        libro.setDisponible(false);
        libroRepository.save(libro);
    }

    private Pageable construirPaginacion(int pagina, int tamanio, String ordenarPor, String direccion) {
        int paginaSegura = Math.max(0, pagina);
        int tamanioSeguro = Math.max(TAMANIO_MINIMO_PAGINA, tamanio);
        Sort.Direction sentido = Sort.Direction.fromOptionalString(direccion).orElse(Sort.Direction.ASC);
        return PageRequest.of(paginaSegura, tamanioSeguro, Sort.by(sentido, ordenarPor));
    }

    private void copiarCamposEditables(Libro destino, Libro origen) {
        destino.setTitulo(origen.getTitulo());
        destino.setAutor(origen.getAutor());
        destino.setIsbn(origen.getIsbn());
        destino.setAnio(origen.getAnio());
        destino.setDisponible(origen.getDisponible());
    }
}
