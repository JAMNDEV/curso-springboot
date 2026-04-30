package com.jamnd.cursospringboot.biblioteca.controller;

import com.jamnd.cursospringboot.biblioteca.dto.response.LibroResponse;
import com.jamnd.cursospringboot.biblioteca.exception.LibroNoEncontradoException;
import com.jamnd.cursospringboot.biblioteca.handler.GlobalExceptionHandler;
import com.jamnd.cursospringboot.biblioteca.mapper.LibroMapper;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.service.LibroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    @MockBean
    private LibroService libroService;

    @MockBean
    private LibroMapper libroMapper;

    @Test
    void listarLibros_retorna200() throws Exception {
        Libro libro = new Libro(1L, "Java Basico", "Autor Demo", "978000000001", 2020, true);
        LibroResponse response = new LibroResponse(1L, "Java Basico", "Autor Demo", "978000000001", 2020, true);

        given(libroService.listar()).willReturn(List.of(libro));
        given(libroMapper.toResponse(libro)).willReturn(response);

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Java Basico"));
    }

    @Test
    void listarPaginado_retorna200ConMetadata() throws Exception {
        Libro libro1 = new Libro(1L, "Spring Inicial", "Autor A", "978000000001", 2021, true);
        Libro libro2 = new Libro(2L, "Java Avanzado", "Autor B", "978000000002", 2022, true);

        LibroResponse response1 = new LibroResponse(1L, "Spring Inicial", "Autor A", "978000000001", 2021, true);
        LibroResponse response2 = new LibroResponse(2L, "Java Avanzado", "Autor B", "978000000002", 2022, true);

        given(libroService.listarPaginado(0, 2, "titulo", "ASC"))
                .willReturn(new PageImpl<>(List.of(libro1, libro2), PageRequest.of(0, 2), 5));
        given(libroMapper.toResponse(libro1)).willReturn(response1);
        given(libroMapper.toResponse(libro2)).willReturn(response2);

        mockMvc.perform(get("/api/libros/paginado")
                        .param("pagina", "0")
                        .param("tamanio", "2")
                        .param("ordenarPor", "titulo")
                        .param("direccion", "ASC"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenido[0].id").value(1))
                .andExpect(jsonPath("$.contenido[1].id").value(2))
                .andExpect(jsonPath("$.pagina").value(0))
                .andExpect(jsonPath("$.tamanio").value(2))
                .andExpect(jsonPath("$.totalElementos").value(5));
    }

    @Test
    void filtrar_porAutorYDisponibilidad_retorna200() throws Exception {
        Libro libro = new Libro(3L, "Arquitectura de Microservicios", "Martin", "978000000003", 2024, true);
        LibroResponse response = new LibroResponse(3L, "Arquitectura de Microservicios", "Martin", "978000000003", 2024, true);

        given(libroService.filtrarPorAutorYDisponibilidad("martin", true)).willReturn(List.of(libro));
        given(libroMapper.toResponse(libro)).willReturn(response);

        mockMvc.perform(get("/api/libros/filtrar")
                        .param("autor", "martin")
                        .param("disponible", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].autor").value("Martin"))
                .andExpect(jsonPath("$[0].disponible").value(true));
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

        verifyNoInteractions(libroService, libroMapper);
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
