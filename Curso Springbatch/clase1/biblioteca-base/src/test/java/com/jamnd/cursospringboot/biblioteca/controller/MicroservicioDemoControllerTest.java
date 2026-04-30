package com.jamnd.cursospringboot.biblioteca.controller;

import com.jamnd.cursospringboot.biblioteca.service.MicroservicioDemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MicroservicioDemoController.class)
class MicroservicioDemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MicroservicioDemoService microservicioDemoService;

    @Test
    void verificarDisponibilidadRemota_retorna200() throws Exception {
        given(microservicioDemoService.verificarDisponibilidadRemota(1L))
                .willReturn(Map.of(
                        "libroId", 1L,
                        "disponible", true,
                        "tipoComunicacion", "REST Client (RestTemplate)",
                        "siguientePasoClase", "Migrar esta interfaz a Feign Client"
                ));

        mockMvc.perform(get("/api/microservicios/libros/1/disponibilidad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libroId").value(1))
                .andExpect(jsonPath("$.disponible").value(true));
    }
}
