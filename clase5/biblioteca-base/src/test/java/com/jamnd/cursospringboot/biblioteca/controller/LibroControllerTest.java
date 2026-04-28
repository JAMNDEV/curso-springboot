package com.jamnd.cursospringboot.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamnd.cursospringboot.biblioteca.exception.LibroNoEncontradoException;
import com.jamnd.cursospringboot.biblioteca.handler.GlobalExceptionHandler;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LibroController.class)
@Import(GlobalExceptionHandler.class)
class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LibroService libroService;

    @Test
    void listarLibros_retorna200() throws Exception {
        Libro libro = new Libro(1L, "Java Basico", "Autor Demo", "978000000001", 2020, true);
        given(libroService.listar()).willReturn(List.of(libro));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Java Basico"));
    }

    @Test
    void crearLibro_invalido_retorna400() throws Exception {
        String body = """
                {
                  "titulo": "",
                  "autor": "",
                  "isbn": "",
                  "anio": -1,
                  "disponible": null
                }
                """;

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));

        verifyNoInteractions(libroService);
    }

    @Test
    void obtenerPorId_noExiste_retorna404() throws Exception {
        given(libroService.obtenerPorId(999L)).willThrow(new LibroNoEncontradoException(999L));

        mockMvc.perform(get("/api/libros/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No se encontro el libro con id 999"));
    }
}
