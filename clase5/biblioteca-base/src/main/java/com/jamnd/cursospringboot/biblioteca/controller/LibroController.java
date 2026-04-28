package com.jamnd.cursospringboot.biblioteca.controller;

import com.jamnd.cursospringboot.biblioteca.dto.request.LibroRequest;
import com.jamnd.cursospringboot.biblioteca.dto.response.ErrorResponse;
import com.jamnd.cursospringboot.biblioteca.dto.response.LibroResponse;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Operaciones CRUD y consultas sobre el catalogo de libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    @Operation(
            summary = "Listar libros",
            description = "Obtiene el listado completo de libros registrados en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado recuperado correctamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LibroResponse.class)))),
            @ApiResponse(responseCode = "500", description = "Error interno inesperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<LibroResponse>> listarLibros() {
        List<LibroResponse> response = libroService.listar()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Lamina 32: Query method por nombre de metodo.
    @GetMapping("/buscar")
    @Operation(
            summary = "Buscar por titulo",
            description = "Busca libros por coincidencia parcial de titulo ignorando mayusculas/minusculas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busqueda ejecutada correctamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LibroResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Parametro titulo invalido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<List<LibroResponse>> buscarPorTitulo(
            @Parameter(description = "Texto parcial a buscar en el titulo", example = "spring", required = true)
            @RequestParam String titulo) {
        List<LibroResponse> response = libroService.buscarPorTitulo(titulo)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Lamina 32: ejemplo con @Query en repository.
    @GetMapping("/disponibles")
    @Operation(
            summary = "Listar disponibles",
            description = "Retorna solo libros con estado disponible=true usando consulta @Query en repository."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de disponibles recuperado",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LibroResponse.class))))
    })
    public ResponseEntity<List<LibroResponse>> listarDisponibles() {
        List<LibroResponse> response = libroService.listarDisponibles()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener libro por ID",
            description = "Recupera el detalle de un libro existente por su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibroResponse.class))),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(
                                    name = "LibroNoEncontrado",
                                    value = "{\"status\":404,\"error\":\"Not Found\",\"message\":\"No se encontro el libro con id 999\",\"path\":\"/api/libros/999\"}"
                            )))
    })
    public ResponseEntity<LibroResponse> obtenerPorId(
            @Parameter(description = "ID del libro", example = "1", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(toResponse(libroService.obtenerPorId(id)));
    }

    @PostMapping
    @Operation(
            summary = "Crear libro",
            description = "Registra un nuevo libro en el catalogo. Requiere datos validos segun Bean Validation."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Payload de creacion de libro",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LibroRequest.class),
                    examples = {
                            @ExampleObject(name = "Valido", value = "{\"titulo\":\"Arquitectura Limpia\",\"autor\":\"Robert C. Martin\",\"isbn\":\"9780134494166\",\"anio\":2017,\"disponible\":true}"),
                            @ExampleObject(name = "Invalido", value = "{\"titulo\":\"\",\"autor\":\"\",\"isbn\":\"\",\"anio\":-1,\"disponible\":null}")
                    })
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Libro creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibroResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflicto de datos (ejemplo: ISBN duplicado)",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno inesperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<LibroResponse> crear(@Valid @RequestBody LibroRequest libroRequest) {
        Libro creado = libroService.crear(toEntity(libroRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(creado));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar libro",
            description = "Actualiza completamente los datos de un libro existente por ID."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Payload de actualizacion de libro",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LibroRequest.class))
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LibroResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Conflicto de datos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<LibroResponse> actualizar(
            @Parameter(description = "ID del libro a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody LibroRequest libroRequest) {
        Libro actualizado = libroService.actualizar(id, toEntity(libroRequest));
        return ResponseEntity.ok(toResponse(actualizado));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar libro",
            description = "Elimina un libro existente por ID del catalogo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Libro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno inesperado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del libro a eliminar", example = "3", required = true)
            @PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private LibroResponse toResponse(Libro libro) {
        return new LibroResponse(
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIsbn(),
                libro.getAnio(),
                libro.getDisponible()
        );
    }

    private Libro toEntity(LibroRequest request) {
        return new Libro(
                null,
                request.getTitulo(),
                request.getAutor(),
                request.getIsbn(),
                request.getAnio(),
                request.getDisponible()
        );
    }
}
