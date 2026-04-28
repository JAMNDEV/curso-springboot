package com.jamnd.cursospringboot.biblioteca.config;

import com.jamnd.cursospringboot.biblioteca.model.Alumno;
import com.jamnd.cursospringboot.biblioteca.model.Libro;
import com.jamnd.cursospringboot.biblioteca.repository.AlumnoRepository;
import com.jamnd.cursospringboot.biblioteca.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(LibroRepository libroRepository, AlumnoRepository alumnoRepository) {
        return args -> {
            if (libroRepository.count() == 0) {
                libroRepository.save(new Libro(null, "Java Basico", "Autor Demo", "978000000001", 2020, true));
                libroRepository.save(new Libro(null, "Spring Boot Inicial", "Autor Demo", "978000000002", 2022, true));
                libroRepository.save(new Libro(null, "Persistencia JPA", "Autor Demo", "978000000003", 2024, true));
            }

            if (alumnoRepository.count() == 0) {
                alumnoRepository.save(new Alumno(null, "Andrea", "Lopez", "andrea@demo.com", "A001", false));
                alumnoRepository.save(new Alumno(null, "Luis", "Martinez", "luis@demo.com", "A002", true));
                alumnoRepository.save(new Alumno(null, "Carlos", "Ruiz", "carlos@demo.com", "A003", false));
            }
        };
    }
}
