package com.jamnd.cursospringboot.biblioteca.service;

import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @Test
    void listarPaginado_normalizaParametrosYOrdenaAscSiDireccionInvalida() {
        Libro libro = new Libro(1L, "Spring", "Autor", "978000000001", 2024, true);
        Page<Libro> pagina = new PageImpl<>(List.of(libro));
        given(libroRepository.findAll(any(Pageable.class))).willReturn(pagina);

        libroService.listarPaginado(-1, 0, "titulo", "NO_VALIDO");

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(libroRepository).findAll(pageableCaptor.capture());

        Pageable pageableUsado = pageableCaptor.getValue();
        assertEquals(0, pageableUsado.getPageNumber());
        assertEquals(1, pageableUsado.getPageSize());
        assertEquals(Sort.Direction.ASC, pageableUsado.getSort().getOrderFor("titulo").getDirection());
    }
}
