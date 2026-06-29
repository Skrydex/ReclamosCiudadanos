Sistema de Gestión de Reclamos Ciudadanos - Municipalidad de San Rafael

Requisitos

- Tener Java JDK 17 o superior instalado.
- Usar Herramientas como NetBeans, Eclipse para su uso.

Como ejecutar:

Opción 1: Desde NetBeans

1. Abrir el proyecto en NetBeans.
2. Ejecutar la clase "Main.java".

Opción 2: Desde terminal

Sino funciona la opción 1:

1. Abrir una terminal dentro de la carpeta src.
2. Compilar todas las clases:

"javac reclamosmunicipales/*.java"

3. Ejecutar el programa:

"java reclamosmunicipales.Main"

Uso del programa:

Al iniciar, el sistema carga automáticamente 6 reclamos de ejemplo y te mostrará un menú interactivo por consola:

1. Registrar nuevo reclamo
2. Modificar descripción de un reclamo
3. Eliminar reclamo
4. Actualizar estado de un reclamo
5. Buscar reclamo por código (búsqueda secuencial)
6. Mostrar reclamos pendientes (Cola FIFO)
7. Atender siguiente reclamo pendiente
8. Mostrar historial de cambios de un reclamo (Pila)
9. Mostrar reclamos ordenados
10. Mostrar árbol BST / AVL
11. Mostrar reclamos próximos a vencer
0. Salir

Se elige una opción ingresando el número correspondiente y presionando Enter en el teclado. Algunas opciones pedirán datos adicionales por ejemplo, el código de un reclamo o la cantidad de días de margen para la alerta de vencimiento.

Ejemplo de uso rápido para probar el codigo:

1. Elegir la opción "15" para ver los reclamos de ejemplo ordenados por código.
2. Elegir la opción "5" o "6", ingresar el código "1001" para buscarlo.
3. Elegir la opción "21", ingresar "3" para ver los reclamos que vencen en los próximos 3 días.
4. Elegir "0" para salir.

Autores

Grupo 09 - Alexander Sandoval, Martin Castillo y Bastian Benavidez
