package com.jamnd.cursospringboot.biblioteca.controller;

import com.jamnd.cursospringboot.biblioteca.dto.response.PrestamoResponse;
import com.jamnd.cursospringboot.biblioteca.exception.PrestamoNoPermitidoException;
import com.jamnd.cursospringboot.biblioteca.handler.GlobalExceptionHandler;
import com.jamnd.cursospringboot.biblioteca.mapper.PrestamoMapper;
import com.jamnd.cursospringboot.biblioteca.model.Alumno;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.model.Prestamo;
import com.jamnd.cursospringboot.biblioteca.service.PrestamoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PrestamoController.class)
@Import(GlobalExceptionHandler.class)
class PrestamoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestamoService prestamoService;

    @MockBean
    private PrestamoMapper prestamoMapper;

    @Test
    void listarPrestamos_retorna200() throws Exception {
        Libro libro = new Libro(1L, "Java Basico", "Autor Demo", "978000000001", 2020, false);
        Alumno alumno = new Alumno(1L, "Andrea", "Lopez", "andrea@demo.com", "A001", false);
        Prestamo prestamo = new Prestamo(10L, libro, alumno, LocalDate.of(2026, 4, 27), LocalDate.of(2026, 5, 4));
        PrestamoResponse response = new PrestamoResponse(10L, 1L, "Java Basico", 1L, "Andrea Lopez", LocalDate.of(2026, 4, 27), LocalDate.of(2026, 5, 4));

        given(prestamoService.listar()).willReturn(List.of(prestamo));
        given(prestamoMapper.toResponse(prestamo)).willReturn(response);

        mockMvc.perform(get("/api/prestamos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].libroId").value(1));
    }

    @Test
    void crearPrestamo_valido_retorna201() throws Exception {
        Libro libro = new Libro(1L, "Java Basico", "Autor Demo", "978000000001", 2020, false);
        Alumno alumno = new Alumno(1L, "Andrea", "Lopez", "andrea@demo.com", "A001", false);
        Prestamo prestamo = new Prestamo(10L, libro, alumno, LocalDate.of(2026, 4, 27), LocalDate.of(2026, 5, 4));
        PrestamoResponse response = new PrestamoResponse(10L, 1L, "Java Basico", 1L, "Andrea Lopez", LocalDate.of(2026, 4, 27), LocalDate.of(2026, 5, 4));

        given(prestamoService.crearPrestamo(any())).willReturn(prestamo);
        given(prestamoMapper.toResponse(prestamo)).willReturn(response);

        String body = """
                {
                  "libroId": 1,
                  "alumnoId": 1
                }
                """;

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.alumnoNombre").value("Andrea Lopez"));
    }

    @Test
    void crearPrestamo_reglaNegocio_retorna409() throws Exception {
        given(prestamoService.crearPrestamo(any()))
                .willThrow(new PrestamoNoPermitidoException("Prestamo no permitido: el alumno tiene deudas pendientes"));

        String body = """
                {
                  "libroId": 2,
                  "alumnoId": 2
                }
                """;

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409));
    }

    @Test
    void crearPrestamo_invalido_retorna400() throws Exception {
        String body = """
                {
                  "libroId": -1,
                  "alumnoId": null
                }
                """;

        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }
}
