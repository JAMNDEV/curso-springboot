package com.jamnd.cursospringboot.biblioteca.controller;

import com.jamnd.cursospringboot.biblioteca.dto.request.PrestamoRequest;
import com.jamnd.cursospringboot.biblioteca.dto.response.ErrorResponse;
import com.jamnd.cursospringboot.biblioteca.dto.response.PrestamoResponse;
import com.jamnd.cursospringboot.biblioteca.model.Prestamo;
import com.jamnd.cursospringboot.biblioteca.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/prestamos")
@Tag(name = "Prestamos", description = "Registro y consulta de prestamos con validaciones de negocio")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    @Operation(
            summary = "Listar prestamos",
            description = "Recupera todos los prestamos registrados con detalle de libro y alumno."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prestamos recuperados correctamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PrestamoResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno inesperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<PrestamoResponse>> listar() {
        List<PrestamoResponse> response = prestamoService.listar()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(
            summary = "Crear prestamo",
            description = "Registra un prestamo validando existencia de libro/alumno, disponibilidad y reglas de deuda."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Payload para registro de prestamo",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PrestamoRequest.class),
                    examples = {
                            @ExampleObject(name = "Valido", value = "{\"libroId\":1,\"alumnoId\":1}"),
                            @ExampleObject(name = "InvalidoBeanValidation", value = "{\"libroId\":-1,\"alumnoId\":null}"),
                            @ExampleObject(name = "ConflictoNegocio", value = "{\"libroId\":2,\"alumnoId\":2}")
                    })
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prestamo creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PrestamoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Libro o alumno no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "RecursoNoEncontrado",
                                    value = "{\"status\":404,\"error\":\"Not Found\",\"message\":\"No se encontro el libro con id 999\",\"path\":\"/api/prestamos\"}"
                            ))),
            @ApiResponse(responseCode = "409", description = "Conflicto por regla de negocio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "PrestamoNoPermitido",
                                    value = "{\"status\":409,\"error\":\"Conflict\",\"message\":\"Prestamo no permitido: el alumno tiene deudas pendientes\",\"path\":\"/api/prestamos\"}"
                            ))),
            @ApiResponse(responseCode = "500", description = "Error interno inesperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<PrestamoResponse> crear(@Valid @RequestBody PrestamoRequest request) {
        Prestamo creado = prestamoService.crearPrestamo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(creado));
    }

    private PrestamoResponse toResponse(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getLibro().getId(),
                prestamo.getLibro().getTitulo(),
                prestamo.getAlumno().getId(),
                prestamo.getAlumno().getNombre() + " " + prestamo.getAlumno().getApellido(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucionEstimada()
        );
    }
}
