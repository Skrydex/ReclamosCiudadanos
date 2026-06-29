package reclamosmunicipales;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GestorReclamos gestor = new GestorReclamos();

        cargarDatosIniciales(gestor);

        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n===== SISTEMA DE GESTION DE RECLAMOS - MUNICIPALIDAD DE SAN RAFAEL =====");
            System.out.println("1.  Registrar nuevo reclamo");
            System.out.println("2.  Modificar descripcion de un reclamo");
            System.out.println("3.  Eliminar reclamo");
            System.out.println("4.  Actualizar estado de un reclamo");
            System.out.println("5.  Buscar reclamo por codigo");
            System.out.println("6.  Mostrar reclamos pendientes (Cola)");
            System.out.println("7.  Atender siguiente reclamo pendiente");
            System.out.println("8.  Mostrar historial de cambios de un reclamo (Pila)");
            System.out.println("9.  Mostrar reclamos ordenados");
            System.out.println("10. Mostrar arboles BST / AVL");
            System.out.println("11. Mostrar reclamos proximos a vencer");
            System.out.println("0.  Salir");
            System.out.print("Ingrese una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Codigo: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre del ciudadano: ");
                    String nombre = sc.nextLine();
                    System.out.print("RUT del ciudadano: ");
                    String rut = sc.nextLine();
                    System.out.print("Tipo de reclamo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Descripcion: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Prioridad (1 alta, 2 media, 3 baja): ");
                    int prioridad = sc.nextInt();
                    System.out.print("Dias de plazo para responder: ");
                    int diasPlazo = sc.nextInt();
                    sc.nextLine();

                    LocalDate hoy = LocalDate.now();
                    Reclamo nuevo = new Reclamo(codigo, nombre, rut, tipo, descripcion,
                            hoy, "Pendiente", prioridad, hoy.plusDays(diasPlazo));
                    gestor.registrarReclamo(nuevo);
                    System.out.println("Reclamo registrado correctamente.");
                    break;

                case 2:
                    System.out.print("Codigo del reclamo a modificar: ");
                    int codigoModificar = sc.nextInt();
                    sc.nextLine();
                    System.out.println("  Que desea modificar?");
                    System.out.println("  [1] Descripcion");
                    System.out.println("  [2] Nivel de prioridad (1 alta, 2 media, 3 baja)");
                    System.out.print("  Seleccione: ");
                    int opMod = sc.nextInt();
                    sc.nextLine();
                    if (opMod == 1) {
                        System.out.print("Nueva descripcion: ");
                        String nuevaDescripcion = sc.nextLine();
                        if (gestor.modificarDescripcion(codigoModificar, nuevaDescripcion)) {
                            System.out.println("Descripcion actualizada correctamente.");
                        } else {
                            System.out.println("No se encontro un reclamo con ese codigo.");
                        }
                    } else if (opMod == 2) {
                        System.out.print("Nueva prioridad (1 alta, 2 media, 3 baja): ");
                        int nuevaPrioridad = sc.nextInt();
                        sc.nextLine();
                        if (gestor.modificarPrioridad(codigoModificar, nuevaPrioridad)) {
                            System.out.println("Prioridad actualizada correctamente (Arbol AVL reestructurado).");
                        } else {
                            System.out.println("No se encontro un reclamo con ese codigo.");
                        }
                    } else {
                        System.out.println("Opcion invalida.");
                    }
                    break;

                case 3:
                    System.out.print("Codigo del reclamo a eliminar: ");
                    int codigoEliminar = sc.nextInt();
                    sc.nextLine();
                    if (gestor.eliminarReclamo(codigoEliminar)) {
                        System.out.println("Reclamo eliminado correctamente.");
                    } else {
                        System.out.println("No se encontro un reclamo con ese codigo.");
                    }
                    break;

                case 4:
                    System.out.print("Codigo del reclamo: ");
                    int codigoEstado = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo estado (Pendiente / En proceso / Resuelto): ");
                    String nuevoEstado = sc.nextLine();
                    if (gestor.actualizarEstado(codigoEstado, nuevoEstado)) {
                        System.out.println("Estado actualizado correctamente.");
                    } else {
                        System.out.println("No se encontro un reclamo con ese codigo.");
                    }
                    break;

                case 5:
                    // Submenú de búsqueda: agrupa búsqueda secuencial y binaria
                    System.out.println("  [a] Busqueda secuencial");
                    System.out.println("  [b] Busqueda binaria");
                    System.out.print("  Seleccione: ");
                    String tipoBusqueda = sc.nextLine().trim().toLowerCase();
                    System.out.print("Ingrese el codigo a buscar: ");
                    int codigoBuscar = sc.nextInt();
                    sc.nextLine();
                    if (tipoBusqueda.equals("a")) {
                        mostrarResultadoBusqueda(gestor.buscarPorCodigoSecuencial(codigoBuscar));
                    } else if (tipoBusqueda.equals("b")) {
                        mostrarResultadoBusqueda(gestor.buscarPorCodigoBinario(codigoBuscar));
                    } else {
                        System.out.println("Opcion invalida.");
                    }
                    break;

                case 6:
                    gestor.mostrarPendientes();
                    break;

                case 7:
                    Reclamo atendido = gestor.atenderSiguientePendiente();
                    if (atendido != null) {
                        System.out.println("Atendiendo reclamo: " + atendido);
                    } else {
                        System.out.println("No hay reclamos pendientes por atender.");
                    }
                    break;

                case 8:
                    System.out.print("Codigo del reclamo: ");
                    int codigoHistorial = sc.nextInt();
                    sc.nextLine();
                    gestor.mostrarHistorial(codigoHistorial);
                    break;

                case 9:
                    // Submenú de ordenamiento: agrupa todos los criterios y algoritmos
                    System.out.println("  Criterio de ordenamiento:");
                    System.out.println("  [1] Prioridad     (MergeSort - eficiente)");
                    System.out.println("  [2] Prioridad     (SelectionSort - cuadratico)");
                    System.out.println("  [3] Fecha ingreso (MergeSort)");
                    System.out.println("  [4] Fecha limite  (MergeSort)");
                    System.out.println("  [5] Tipo          (MergeSort)");
                    System.out.print("  Seleccione: ");
                    int criterio = sc.nextInt();
                    sc.nextLine();
                    switch (criterio) {
                        case 1: gestor.mostrarOrdenadosPorCriterio(MergeSort.POR_PRIORIDAD, "prioridad (MergeSort)"); break;
                        case 2: gestor.mostrarOrdenadosPorPrioridadCuadratico(); break;
                        case 3: gestor.mostrarOrdenadosPorCriterio(MergeSort.POR_FECHA_INGRESO, "fecha de ingreso"); break;
                        case 4: gestor.mostrarOrdenadosPorCriterio(MergeSort.POR_FECHA_LIMITE, "fecha limite"); break;
                        case 5: gestor.mostrarOrdenadosPorCriterio(MergeSort.POR_TIPO, "tipo de reclamo"); break;
                        default: System.out.println("Opcion invalida.");
                    }
                    break;

                case 10:
                    // Submenú de árboles: agrupa BST por código y BST por prioridad con sus recorridos
                    System.out.println("  Arbol:");
                    System.out.println("  [1] BST por codigo   - inorden");
                    System.out.println("  [2] BST por codigo   - preorden");
                    System.out.println("  [3] BST por codigo   - postorden");
                    System.out.println("  [4] AVL por prioridad - inorden");
                    System.out.println("  [5] AVL por prioridad - preorden");
                    System.out.println("  [6] AVL por prioridad - postorden");
                    System.out.print("  Seleccione: ");
                    int opArbol = sc.nextInt();
                    sc.nextLine();
                    switch (opArbol) {
                        case 1: gestor.mostrarArbolPorCodigo("inorden");    break;
                        case 2: gestor.mostrarArbolPorCodigo("preorden");   break;
                        case 3: gestor.mostrarArbolPorCodigo("postorden");  break;
                        case 4: gestor.mostrarArbolPorPrioridad("inorden");   break;
                        case 5: gestor.mostrarArbolPorPrioridad("preorden");  break;
                        case 6: gestor.mostrarArbolPorPrioridad("postorden"); break;
                        default: System.out.println("Opcion invalida.");
                    }
                    break;

                case 11:
                    System.out.print("Ingrese el margen de dias para considerar 'proximo a vencer': ");
                    int margen = sc.nextInt();
                    sc.nextLine();
                    gestor.mostrarReclamosProximosAVencer(margen);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }

        sc.close();
    }

    private static void mostrarResultadoBusqueda(Reclamo reclamo) {
        if (reclamo != null) {
            System.out.println("Reclamo encontrado: " + reclamo);
        } else {
            System.out.println("No se encontro un reclamo con ese codigo.");
        }
    }

    private static void cargarDatosIniciales(GestorReclamos gestor) {
        LocalDate hoy = LocalDate.now();

        gestor.registrarReclamo(new Reclamo(1001, "Juan Perez", "12.345.678-9", "Alumbrado publico",
                "Poste sin luz en calle Los Aromos", hoy.minusDays(5), "Pendiente", 1, hoy.plusDays(2)));

        gestor.registrarReclamo(new Reclamo(1002, "Maria Soto", "9.876.543-2", "Recoleccion de residuos",
                "No se realizo el retiro de basura hace una semana", hoy.minusDays(3), "Pendiente", 2, hoy.plusDays(10)));

        gestor.registrarReclamo(new Reclamo(1003, "Carlos Diaz", "15.222.333-4", "Seguridad",
                "Falta de vigilancia en plaza central", hoy.minusDays(1), "En proceso", 1, hoy.plusDays(1)));

        gestor.registrarReclamo(new Reclamo(1004, "Ana Rojas", "8.111.222-5", "Areas verdes",
                "Pasto sin cortar en parque municipal", hoy.minusDays(7), "Pendiente", 3, hoy.plusDays(15)));

        gestor.registrarReclamo(new Reclamo(1005, "Pedro Lopez", "11.444.555-6", "Senalizacion vial",
                "Senal de PARE caida en cruce principal", hoy.minusDays(2), "Pendiente", 1, hoy.plusDays(3)));

        gestor.registrarReclamo(new Reclamo(1006, "Laura Nunez", "7.888.999-1", "Mantencion de calles",
                "Bache profundo en avenida central", hoy.minusDays(10), "Resuelto", 2, hoy.minusDays(2)));
    }
}
