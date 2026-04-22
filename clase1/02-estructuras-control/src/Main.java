public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 02: Estructuras de control ===");

        String[] titulos = {
                "Java Basico",
                "Spring Boot Inicial",
                "Patrones Backend",
                "APIs Practicas"
        };

        boolean[] disponibles = {true, false, true, true};

        int totalDisponibles = 0;

        // Comentario guia: for para recorrer colecciones.
        for (int i = 0; i < titulos.length; i++) {
            if (disponibles[i]) {
                System.out.println("Disponible -> " + titulos[i]);
                totalDisponibles++;
            } else {
                System.out.println("Prestado   -> " + titulos[i]);
            }
        }

        System.out.println("Total disponibles: " + totalDisponibles);

        // Comentario guia: while para control por condicion.
        int indice = 0;
        while (indice < titulos.length) {
            System.out.println("Revision rapida #" + (indice + 1) + ": " + titulos[indice]);
            indice++;
        }
    }
}
