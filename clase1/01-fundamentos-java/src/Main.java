public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 01: Fundamentos Java ===");

        // Comentario guia: Java corre sobre la JVM sin depender del sistema operativo.
        String vmName = System.getProperty("java.vm.name");
        String javaVersion = System.getProperty("java.version");

        System.out.println("JVM en uso: " + vmName);
        System.out.println("Version de Java: " + javaVersion);

        // Tipos de datos aplicados a una biblioteca.
        String tituloLibro = "Clean Code";
        String codigoIsbn = "9780132350884";
        int anioPublicacion = 2008;
        double precio = 499.90;
        boolean disponiblePrestamo = true;
        char nivelLectura = 'A';

        System.out.println("Titulo: " + tituloLibro);
        System.out.println("ISBN: " + codigoIsbn);
        System.out.println("Anio: " + anioPublicacion);
        System.out.println("Precio: " + precio);
        System.out.println("Disponible: " + disponiblePrestamo);
        System.out.println("Nivel: " + nivelLectura);

        // Buena practica: constante con final.
        final String MENSAJE_CURSO = "Base solida de Java para pasar a Spring Boot";
        System.out.println(MENSAJE_CURSO);
    }
}
