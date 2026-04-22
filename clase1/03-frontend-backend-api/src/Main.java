import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Libro {
        String titulo;
        boolean disponible;

        Libro(String titulo, boolean disponible) {
            this.titulo = titulo;
            this.disponible = disponible;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 03: Flujo API biblioteca ===");

        // Frontend: accion del usuario.
        System.out.println("[Frontend] Click en 'Ver libros disponibles'");

        // Peticion HTTP simulada.
        String endpoint = "GET /api/libros?disponible=true";
        System.out.println("[HTTP] " + endpoint);

        // Backend: procesa la peticion.
        List<Libro> respuesta = obtenerLibrosDisponibles();

        // Respuesta tipo JSON.
        String json = convertirAJson(respuesta);
        System.out.println("[Backend -> Frontend] Respuesta JSON:");
        System.out.println(json);
    }

    static List<Libro> obtenerLibrosDisponibles() {
        List<Libro> catalogo = List.of(
                new Libro("Java Basico", true),
                new Libro("Spring Boot Inicial", true),
                new Libro("Arquitectura Limpia", false)
        );

        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : catalogo) {
            if (libro.disponible) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }

    static String convertirAJson(List<Libro> libros) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            sb.append("  {\"titulo\": \"").append(libro.titulo).append("\", \"disponible\": ").append(libro.disponible).append("}");
            if (i < libros.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append("]");
        return sb.toString();
    }
}
