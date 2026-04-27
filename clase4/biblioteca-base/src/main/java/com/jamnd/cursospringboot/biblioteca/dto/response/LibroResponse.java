package com.jamnd.cursospringboot.biblioteca.dto.response;

public class LibroResponse {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer anio;
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
