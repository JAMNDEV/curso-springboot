package com.jamnd.cursospringboot.biblioteca.service;

import com.jamnd.cursospringboot.biblioteca.dto.request.PrestamoRequest;
import com.jamnd.cursospringboot.biblioteca.exception.PrestamoNoPermitidoException;
import com.jamnd.cursospringboot.biblioteca.model.Alumno;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.model.Prestamo;
import com.jamnd.cursospringboot.biblioteca.repository.AlumnoRepository;
import com.jamnd.cursospringboot.biblioteca.repository.LibroRepository;
import com.jamnd.cursospringboot.biblioteca.repository.PrestamoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private PrestamoRepository prestamoRepository;

    @Mock
    private LibroService libroService;

    @InjectMocks
    private PrestamoService prestamoService;

    @Test
    void crearPrestamo_valido_guardaYActualizaInventario() {
        Libro libro = new Libro(1L, "Java Basico", "Autor Demo", "978000000001", 2020, true);
        Alumno alumno = new Alumno(1L, "Andrea", "Lopez", "andrea@demo.com", "A001", false);

        PrestamoRequest request = new PrestamoRequest();
        request.setLibroId(1L);
        request.setAlumnoId(1L);

        given(libroRepository.findById(1L)).willReturn(Optional.of(libro));
        given(alumnoRepository.findById(1L)).willReturn(Optional.of(alumno));
        given(prestamoRepository.save(any(Prestamo.class))).willAnswer(invocation -> {
            Prestamo p = invocation.getArgument(0);
            p.setId(10L);
            return p;
        });

        Prestamo resultado = prestamoService.crearPrestamo(request);

        assertEquals(10L, resultado.getId());
        verify(prestamoRepository).save(any(Prestamo.class));
        verify(libroService).marcarNoDisponible(libro);
    }

    @Test
    void crearPrestamo_alumnoConDeuda_lanzaConflicto() {
        Libro libro = new Libro(2L, "Spring Boot Inicial", "Autor Demo", "978000000002", 2022, true);
        Alumno alumno = new Alumno(2L, "Luis", "Martinez", "luis@demo.com", "A002", true);

        PrestamoRequest request = new PrestamoRequest();
        request.setLibroId(2L);
        request.setAlumnoId(2L);

        given(libroRepository.findById(2L)).willReturn(Optional.of(libro));
        given(alumnoRepository.findById(2L)).willReturn(Optional.of(alumno));

        assertThrows(PrestamoNoPermitidoException.class, () -> prestamoService.crearPrestamo(request));
        verify(prestamoRepository, never()).save(any());
        verify(libroService, never()).marcarNoDisponible(any());
    }
}
