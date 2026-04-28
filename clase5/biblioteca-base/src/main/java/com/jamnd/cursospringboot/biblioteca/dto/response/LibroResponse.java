package com.jamnd.cursospringboot.biblioteca.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LibroResponse", description = "Datos de salida de un libro")
public class LibroResponse {

    @Schema(description = "ID del libro", example = "1")
    private Long id;

    @Schema(description = "Titulo del libro", example = "Spring Boot en Accion")
    private String titulo;

    @Schema(description = "Autor del libro", example = "Craig Walls")
    private String autor;

    @Schema(description = "ISBN del libro", example = "9781617292545")
    private String isbn;

    @Schema(description = "Anio de publicacion", example = "2019")
    private Integer anio;

    @Schema(description = "Estado de disponibilidad", example = "true")
    private Boolean disponible;

    public LibroResponse(Long id, String titulo, String autor, String isbn, Integer anio, Boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anio = anio;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getAnio() {
        return anio;
    }

    public Boolean getDisponible() {
        return disponible;
    }
}
