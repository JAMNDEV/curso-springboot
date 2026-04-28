package com.jamnd.cursospringboot.biblioteca.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(name = "PaginaResponse", description = "Respuesta paginada con metadata de navegacion")
public class PaginaResponse<T> {

    @Schema(description = "Elementos de la pagina actual")
    private List<T> contenido;

    @Schema(description = "Indice de pagina actual (base 0)", example = "0")
    private int pagina;

    @Schema(description = "Tamano solicitado para cada pagina", example = "5")
    private int tamanio;

    @Schema(description = "Total de elementos encontrados", example = "27")
    private long totalElementos;

    @Schema(description = "Total de paginas calculadas", example = "6")
    private int totalPaginas;

    @Schema(description = "Indica si esta es la ultima pagina", example = "false")
    private boolean ultima;

    public PaginaResponse(List<T> contenido,
                          int pagina,
                          int tamanio,
                          long totalElementos,
                          int totalPaginas,
                          boolean ultima) {
        this.contenido = contenido;
        this.pagina = pagina;
        this.tamanio = tamanio;
        this.totalElementos = totalElementos;
        this.totalPaginas = totalPaginas;
        this.ultima = ultima;
    }

    public static <T> PaginaResponse<T> of(Page<T> page) {
        return new PaginaResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

    public List<T> getContenido() {
        return contenido;
    }

    public int getPagina() {
        return pagina;
    }

    public int getTamanio() {
        return tamanio;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public boolean isUltima() {
        return ultima;
    }
}
