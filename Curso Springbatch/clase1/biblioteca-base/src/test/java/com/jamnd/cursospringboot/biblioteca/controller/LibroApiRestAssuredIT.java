package com.jamnd.cursospringboot.biblioteca.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibroApiRestAssuredIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void crearLibro_yConsultarListado_retornaFlujoCompleto() {
        String payload = """
                {
                  "titulo": "Spring MVC Profesional",
                  "autor": "Jorge Martinez",
                  "isbn": "9786070000016",
                  "anio": 2026,
                  "disponible": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/api/libros")
        .then()
                .statusCode(201)
                .body("autor", equalTo("Jorge Martinez"));

        given()
        .when()
                .get("/api/libros")
        .then()
                .statusCode(200);
    }
}
