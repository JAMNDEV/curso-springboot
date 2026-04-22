# Ejercicio 04 - Inicializar Spring Boot (Biblioteca)

## Tema de clase que refuerza
- Introduccion a Spring Boot
- Productividad por configuracion automatica
- Primer endpoint REST para biblioteca

## Objetivo practico
Cerrar la clase creando el proyecto base de biblioteca con Java 17 y exponer una ruta inicial que devuelva JSON.

## Alcance
Solo arranque del proyecto + endpoint inicial.
No se incluyen temas avanzados (BD, JPA, seguridad, OAuth, JWT).

## Estructura incluida
- `biblioteca-base/pom.xml`
- `biblioteca-base/src/main/java/com/jamnd/cursospringboot/biblioteca/BibliotecaApplication.java`
- `biblioteca-base/src/main/java/com/jamnd/cursospringboot/biblioteca/controller/LibroController.java`
- `biblioteca-base/src/main/resources/application.properties`

## Paso a paso (IntelliJ + Java 17)
1. Abre `biblioteca-base` como proyecto Maven.
2. Espera la descarga de dependencias.
3. Ejecuta `BibliotecaApplication`.
4. Abre navegador o Postman y prueba:
   - `http://localhost:8080/api/libros`

## Como explicarlo mientras codificas
- "Spring Boot nos permite iniciar rapido sin configuracion pesada".
- "`@RestController` expone metodos como endpoints HTTP".
- "`@GetMapping` define la operacion GET".
- "Una lista de `Map` ya se serializa a JSON automaticamente".

## Resultado esperado
Respuesta JSON con libros disponibles al consumir `GET /api/libros`.

## Errores comunes a vigilar
- Puerto ocupado en `8080`.
- No esperar que Maven descargue dependencias.
- Paquete o clase principal fuera de la ruta base.

## Cierre de clase
Con este punto el grupo ya conecta:
- Fundamentos de Java.
- Logica de control.
- Concepto de API.
- Arranque real de Spring Boot para la biblioteca.
