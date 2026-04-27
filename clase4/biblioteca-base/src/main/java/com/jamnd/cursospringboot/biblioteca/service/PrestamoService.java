package com.jamnd.cursospringboot.biblioteca.service;

import com.jamnd.cursospringboot.biblioteca.dto.request.PrestamoRequest;
import com.jamnd.cursospringboot.biblioteca.exception.AlumnoNoEncontradoException;
import com.jamnd.cursospringboot.biblioteca.exception.LibroNoEncontradoException;
import com.jamnd.cursospringboot.biblioteca.exception.PrestamoNoPermitidoException;
import com.jamnd.cursospringboot.biblioteca.model.Alumno;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.model.Prestamo;
import com.jamnd.cursospringboot.biblioteca.repository.AlumnoRepository;
import com.jamnd.cursospringboot.biblioteca.repository.LibroRepository;
import com.jamnd.cursospringboot.biblioteca.repository.PrestamoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final LibroRepository libroRepository;
    private final AlumnoRepository alumnoRepository;
    private final PrestamoRepository prestamoRepository;
    private final LibroService libroService;

    public PrestamoService(LibroRepository libroRepository,
                           AlumnoRepository alumnoRepository,
                           PrestamoRepository prestamoRepository,
                           LibroService libroService) {
        this.libroRepository = libroRepository;
        this.alumnoRepository = alumnoRepository;
        this.prestamoRepository = prestamoRepository;
        this.libroService = libroService;
    }

    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    @Transactional
    public Prestamo crearPrestamo(PrestamoRequest request) {
        // Lamina 27: validacion secuencial de existencia de libro y alumno.
        Libro libro = libroRepository.findById(request.getLibroId())
                .orElseThrow(() -> new LibroNoEncontradoException(request.getLibroId()));

        if (!Boolean.TRUE.equals(libro.getDisponible())) {
            LocalDate fechaEstimada = LocalDate.now().plusDays(7);
            throw new PrestamoNoPermitidoException(
                    "El libro no esta disponible. Fecha estimada de devolucion: " + fechaEstimada
            );
        }

        Alumno alumno = alumnoRepository.findById(request.getAlumnoId())
                .orElseThrow(() -> new AlumnoNoEncontradoException(request.getAlumnoId()));

        if (Boolean.TRUE.equals(alumno.getDeudaPendiente())) {
            throw new PrestamoNoPermitidoException("Prestamo no permitido: el alumno tiene deudas pendientes");
        }

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(7);

        Prestamo prestamo = new Prestamo(null, libro, alumno, fechaPrestamo, fechaDevolucion);
        Prestamo guardado = prestamoRepository.save(prestamo);

        // Lamina 24: actualizar inventario tras crear el prestamo.
        libroService.marcarNoDisponible(libro);

        return guardado;
    }
}
