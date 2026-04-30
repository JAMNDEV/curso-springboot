package com.jamnd.cursospringboot.biblioteca.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "Respuesta estandar de error para toda la API")
public class ErrorResponse {

    @Schema(description = "Marca de tiempo de la ocurrencia del error", example = "2026-04-27T12:00:00")
    private LocalDateTime timestamp;

    @Schema(description = "Codigo HTTP de la respuesta", example = "409")
    private Integer status;

    @Schema(description = "Descripcion corta del error HTTP", example = "Conflict")
    private String error;

    @Schema(description = "Mensaje funcional del error", example = "Prestamo no permitido: el alumno tiene deudas pendientes")
    private String message;

    @Schema(description = "Ruta donde ocurrio el error", example = "/api/prestamos")
    private String path;

    public ErrorResponse(LocalDateTime timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
