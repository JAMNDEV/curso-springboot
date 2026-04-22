# Ejercicio 03 - Frontend, Backend y API

## Tema de clase que refuerza
- Diferencia entre frontend y backend
- API como contrato de comunicacion
- Flujo: solicitud HTTP y respuesta JSON

## Objetivo practico
Simular una peticion de biblioteca para entender el camino completo de los datos sin usar framework todavia.

## Archivo de trabajo
- `src/Main.java`

## Paso a paso (IntelliJ + Java 17)
1. Ejecuta el programa.
2. Lee la consola en orden: frontend -> HTTP -> backend -> JSON.
3. Cambia el catalogo de libros y valida el JSON final.
4. Explica al grupo que parte seria cliente y que parte seria servidor.

## Como explicarlo mientras codificas
- "Frontend inicia la accion del usuario".
- "HTTP es el canal de comunicacion".
- "Backend aplica reglas y filtra datos".
- "JSON es el formato de intercambio".
- "La API define reglas claras para ambos lados".

## Resultado esperado
Se imprime una simulacion clara de:
- Evento en frontend.
- Endpoint solicitado (`GET /api/libros?disponible=true`).
- Lista JSON de libros disponibles.

## Errores comunes a vigilar
- Confundir API con base de datos.
- Mezclar responsabilidad de frontend y backend.
- Construir JSON sin cuidar comas o llaves.

## Mini reto guiado
- Agrega campo `autor` al modelo `Libro`.
- Incluye ese campo en el JSON.
- Pregunta de cierre: "Por que decimos que la API es un contrato?"
