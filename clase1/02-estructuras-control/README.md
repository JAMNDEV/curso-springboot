# Ejercicio 02 - Estructuras de control

## Tema de clase que refuerza
- Condicionales `if/else`
- Ciclo `for`
- Ciclo `while`

## Objetivo practico
Recorrer un catalogo simple de libros y decidir cuales estan disponibles para prestamo.

## Archivo de trabajo
- `src/Main.java`

## Paso a paso (IntelliJ + Java 17)
1. Abre el archivo `Main.java`.
2. Ejecuta el programa base.
3. Cambia valores del arreglo `disponibles` y vuelve a ejecutar.
4. Observa como cambia la salida segun la condicion.

## Como explicarlo mientras codificas
- "`if/else` toma decisiones segun una regla booleana".
- "`for` es ideal cuando conocemos el tamano del arreglo".
- "`while` sirve cuando iteramos mientras se cumpla una condicion".
- "El objetivo no es memorizar sintaxis, sino leer la logica".

## Resultado esperado
- Listado de libros disponibles y prestados.
- Conteo total de libros disponibles.
- Recorrido adicional con `while`.

## Errores comunes a vigilar
- Desbordar indice del arreglo (`i <= length`).
- Mezclar condicion de disponibilidad al reves.
- Olvidar incrementar el indice en `while`.

## Mini reto guiado
- Contar cuantos libros NO disponibles hay.
- Mostrar solo titulos con mas de 10 caracteres.
- Pregunta de cierre: "Que estructura usarias para validar 500 libros y por que?"
