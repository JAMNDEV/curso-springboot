package com.jamnd.cursospringboot.biblioteca.controller;

import com.jamnd.cursospringboot.biblioteca.dto.response.ErrorResponse;
import com.jamnd.cursospringboot.biblioteca.service.MicroservicioDemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/microservicios")
@Tag(name = "Microservicios", description = "Ejemplos de comunicacion entre servicios para clase Spring MVC")
public class MicroservicioDemoController {

    private final MicroservicioDemoService microservicioDemoService;

    public MicroservicioDemoController(MicroservicioDemoService microservicioDemoService) {
        this.microservicioDemoService = microservicioDemoService;
    }

    @GetMapping("/libros/{id}/disponibilidad")
    @Operation(
            summary = "Verificar disponibilidad remota",
            description = "Ejemplo educativo de consumo REST entre servicios usando un cliente HTTP."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta ejecutada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno inesperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Map<String, Object>> verificarDisponibilidadRemota(
            @Parameter(description = "ID del libro a consultar", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(microservicioDemoService.verificarDisponibilidadRemota(id));
    }
}
