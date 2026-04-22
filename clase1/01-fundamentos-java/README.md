# Ejercicio 01 - Fundamentos de Java

## Tema de clase que refuerza
- Que es Java
- JVM, JDK, JRE
- Tipos de datos
- Variables con nombres claros (camelCase)

## Objetivo practico
Crear un programa sencillo de biblioteca para mostrar tipos de datos y reforzar por que Java es multiplataforma.

## Archivo de trabajo
- `src/Main.java`

## Paso a paso (IntelliJ + Java 17)
1. Crea un proyecto Java 17.
2. Reemplaza el contenido de `Main.java` por el de este ejercicio.
3. Ejecuta el programa.
4. Cambia valores de ejemplo (titulo, ISBN, precio, disponibilidad).
5. Ejecuta de nuevo y comenta el cambio observado.

## Como explicarlo mientras codificas
- "La JVM ejecuta el bytecode en distintos sistemas operativos".
- "JDK es para desarrollar; JRE es para ejecutar".
- "El tipo define que dato puede guardar una variable".
- "Un buen nombre de variable reduce errores y mejora mantenimiento".

## Resultado esperado
En consola deben verse:
- Datos del entorno Java (JVM y version).
- Datos de un libro (String, int, double, boolean, char).
- Uso de una constante con `final`.

## Errores comunes a vigilar
- Declarar variable sin tipo correcto.
- Usar nombres poco descriptivos (`x`, `a1`, `dato2`).
- Confundir cadena numerica con numero (`"2008"` vs `2008`).

## Mini reto guiado
- Agrega `final double IVA = 0.16;`
- Calcula `precioFinal`.
- Imprime el total y explica por que `final` ayuda a evitar cambios accidentales.
