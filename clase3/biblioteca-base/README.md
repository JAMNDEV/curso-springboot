# biblioteca-base - Clase 3

Proyecto evolutivo de biblioteca para clase 3: Services + Validaciones.

## Qué cambia respecto a clase 2
- Se agrega arquitectura de tres capas: Controller, Service, Repository.
- Se incorporan validaciones de entrada con Bean Validation.
- Se incorporan validaciones de negocio para prestamos.
- Se agregan excepciones personalizadas y manejo global de errores.

## Endpoints principales
- `GET /api/libros`
- `GET /api/libros/{id}`
- `POST /api/libros`
- `PUT /api/libros/{id}`
- `DELETE /api/libros/{id}`
- `GET /api/prestamos`
- `POST /api/prestamos`

## Error JSON estandar
```json
{
  "timestamp": "2026-04-23T11:40:00",
  "status": 409,
  "error": "Conflict",
  "message": "Prestamo no permitido: el alumno tiene deudas pendientes",
  "path": "/api/prestamos"
}
```
