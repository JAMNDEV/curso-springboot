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

    private static final int DIAS_PRESTAMO = 7;

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
        Libro libro = obtenerLibroDisponible(request.getLibroId());
        Alumno alumno = obtenerAlumnoHabilitado(request.getAlumnoId());

        Prestamo prestamo = construirPrestamo(libro, alumno);
        Prestamo guardado = prestamoRepository.save(prestamo);

        // Lamina 24: actualizar inventario tras crear el prestamo.
        libroService.marcarNoDisponible(libro);
        return guardado;
    }

    private Libro obtenerLibroDisponible(Long libroId) {
        // Lamina 27: validacion secuencial de existencia de libro.
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new LibroNoEncontradoException(libroId));

        if (!Boolean.TRUE.equals(libro.getDisponible())) {
            LocalDate fechaEstimada = LocalDate.now().plusDays(DIAS_PRESTAMO);
            throw new PrestamoNoPermitidoException(
                    "El libro no esta disponible. Fecha estimada de devolucion: " + fechaEstimada
            );
        }
        return libro;
    }

    private Alumno obtenerAlumnoHabilitado(Long alumnoId) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new AlumnoNoEncontradoException(alumnoId));

        if (Boolean.TRUE.equals(alumno.getDeudaPendiente())) {
            throw new PrestamoNoPermitidoException("Prestamo no permitido: el alumno tiene deudas pendientes");
        }
        return alumno;
    }

    private Prestamo construirPrestamo(Libro libro, Alumno alumno) {
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(DIAS_PRESTAMO);
        return new Prestamo(null, libro, alumno, fechaPrestamo, fechaDevolucion);
    }
}
