package com.jamnd.cursospringboot.biblioteca.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(name = "LibroRequest", description = "Datos de entrada para crear o actualizar un libro")
public class LibroRequest {

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 120, message = "El titulo no debe superar 120 caracteres")
    @Schema(description = "Titulo del libro", example = "Spring Boot en Accion", requiredMode = Schema.RequiredMode.REQUIRED)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 100, message = "El autor no debe superar 100 caracteres")
    @Schema(description = "Autor del libro", example = "Craig Walls", requiredMode = Schema.RequiredMode.REQUIRED)
    private String autor;

    @NotBlank(message = "El ISBN es obligatorio")
    @Schema(description = "ISBN unico del libro", example = "9781617292545", requiredMode = Schema.RequiredMode.REQUIRED)
    private String isbn;

    @NotNull(message = "El anio es obligatorio")
    @Positive(message = "El anio debe ser positivo")
    @Schema(description = "Anio de publicacion", example = "2019", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer anio;

    @NotNull(message = "El estado disponible es obligatorio")
    @Schema(description = "Indica si el libro esta disponible para prestamo", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean disponible;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
