package com.jamnd.cursospringboot.biblioteca.service;

import com.jamnd.cursospringboot.biblioteca.client.LibroCatalogoClient;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MicroservicioDemoService {

    private final LibroCatalogoClient libroCatalogoClient;

    public MicroservicioDemoService(LibroCatalogoClient libroCatalogoClient) {
        this.libroCatalogoClient = libroCatalogoClient;
    }

    public Map<String, Object> verificarDisponibilidadRemota(Long libroId) {
        boolean disponible = libroCatalogoClient.verificarDisponibilidad(libroId);

        Map<String, Object> resultado = new LinkedHashMap<>();
        resultado.put("libroId", libroId);
        resultado.put("disponible", disponible);
        resultado.put("tipoComunicacion", "REST Client (RestTemplate)");
        resultado.put("siguientePasoClase", "Migrar esta interfaz a Feign Client");
        return resultado;
    }
}
