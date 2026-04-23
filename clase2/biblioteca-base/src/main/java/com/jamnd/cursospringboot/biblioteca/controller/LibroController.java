package com.jamnd.cursospringboot.biblioteca.controller;

import com.jamnd.cursospringboot.biblioteca.model.Libro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    // Clase 2
    private final Map<Long, Libro> catalogo = new ConcurrentHashMap<>();
    private final AtomicLong secuenciaId = new AtomicLong(0);

    public LibroController() {
        guardarNuevo(new Libro(null, "Java Basico", "Autor Demo", "978000000001", 2020, true));
        guardarNuevo(new Libro(null, "Spring Boot Inicial", "Autor Demo", "978000000002", 2022, true));
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        // Tema HTTP GET (pagina 16): lectura de recursos sin modificar datos.
        return ResponseEntity.ok(new ArrayList<>(catalogo.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        // Tema @PathVariable (pagina 18): lectura de variables en la URL.
        Libro libro = catalogo.get(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        // Tema @RequestBody y JSON (paginas 17 y 18): entrada de datos desde el body.
        if (libro.getTitulo() == null || libro.getTitulo().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Libro creado = guardarNuevo(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        // Tema PUT (pagina 16): actualizacion completa de un recurso existente.
        if (!catalogo.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        Libro actualizado = new Libro(
                id,
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getAnio(),
                libro.getDisponible()
        );
        catalogo.put(id, actualizado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        // Tema codigos de estado (pagina 15): 204 cuando elimina, 404 cuando no existe.
        Libro eliminado = catalogo.remove(id);
        if (eliminado != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private Libro guardarNuevo(Libro libro) {
        Long nuevoId = secuenciaId.incrementAndGet();
        Libro nuevo = new Libro(
                nuevoId,
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getAnio(),
                libro.getDisponible()
        );
        catalogo.put(nuevoId, nuevo);
        return nuevo;
    }
}
