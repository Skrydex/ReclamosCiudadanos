package reclamosmunicipales;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class GestorReclamos {

    private ListaReclamos listaReclamos;
    private ColaReclamos colaPendientes;
    private BSTReclamos arbolPorCodigo;
    private AVLReclamos arbolPorPrioridad;
    private HashMap<Integer, PilaHistorial> historialPorReclamo;

    public GestorReclamos() {
        this.listaReclamos        = new ListaReclamos();
        this.colaPendientes       = new ColaReclamos();
        this.arbolPorCodigo       = new BSTReclamos(false);
        this.arbolPorPrioridad    = new AVLReclamos();
        this.historialPorReclamo  = new HashMap<>();
    }

    // registra un nuevo reclamo
    public void registrarReclamo(Reclamo reclamo) {
        listaReclamos.agregar(reclamo);
        arbolPorCodigo.insertar(reclamo);
        arbolPorPrioridad.insertar(reclamo);
        colaPendientes.encolar(reclamo);

        PilaHistorial historial = new PilaHistorial();
        historial.apilar("Reclamo creado con estado: " + reclamo.getEstado());
        historialPorReclamo.put(reclamo.getCodigo(), historial);
    }

    // modifica descripcion de un reclamo
    public boolean modificarDescripcion(int codigo, String nuevaDescripcion) {
        Reclamo reclamo = arbolPorCodigo.buscarPorCodigo(codigo);
        if (reclamo == null) return false;
        reclamo.setDescripcion(nuevaDescripcion);
        registrarCambio(codigo, "Descripcion actualizada");
        return true;
    }

    // modifica prioridad de un reclamo y reestructura el arbol AVL
    public boolean modificarPrioridad(int codigo, int nuevaPrioridad) {
        Reclamo reclamo = arbolPorCodigo.buscarPorCodigo(codigo);
        if (reclamo == null) return false;
        int prioridadAnterior = reclamo.getPrioridad();
        arbolPorPrioridad.eliminar(codigo);
        reclamo.setPrioridad(nuevaPrioridad);
        arbolPorPrioridad.insertar(reclamo);
        registrarCambio(codigo, "Prioridad cambiada de " + prioridadAnterior + " a " + nuevaPrioridad);
        return true;
    }

    // actualiza estado del reclamo
    public boolean actualizarEstado(int codigo, String nuevoEstado) {
        Reclamo reclamo = arbolPorCodigo.buscarPorCodigo(codigo);
        if (reclamo == null) return false;
        String estadoAnterior = reclamo.getEstado();
        reclamo.setEstado(nuevoEstado);
        registrarCambio(codigo, "Estado cambiado de '" + estadoAnterior + "' a '" + nuevoEstado + "'");
        return true;
    }

    // elimina reclamo
    public boolean eliminarReclamo(int codigo) {
        Reclamo reclamo = arbolPorCodigo.buscarPorCodigo(codigo);
        if (reclamo == null) return false;
        listaReclamos.eliminar(codigo);
        arbolPorCodigo.eliminar(codigo);
        arbolPorPrioridad.eliminar(codigo);
        registrarCambio(codigo, "Reclamo eliminado del sistema");
        return true;
    }

    private void registrarCambio(int codigo, String descripcionCambio) {
        PilaHistorial historial = historialPorReclamo.get(codigo);
        if (historial != null) historial.apilar(descripcionCambio);
    }

    public void mostrarHistorial(int codigo) {
        PilaHistorial historial = historialPorReclamo.get(codigo);
        if (historial == null) {
            System.out.println("No existe historial para ese reclamo.");
            return;
        }
        historial.mostrarHistorial();
    }

    // atiende el reclamo pendiente
    public Reclamo atenderSiguientePendiente() {
        Reclamo reclamo = colaPendientes.desencolar();
        if (reclamo != null) actualizarEstado(reclamo.getCodigo(), "En proceso");
        return reclamo;
    }

    public void mostrarPendientes() {
        colaPendientes.mostrarPendientes();
    }

    // busqueda secuencial
    public Reclamo buscarPorCodigoSecuencial(int codigo) {
        return BusquedaSecuencial.buscarPorCodigo(listaReclamos.aArreglo(), codigo);
    }

    // busqueda binaria
    public Reclamo buscarPorCodigoBinario(int codigo) {
        Reclamo[] arr = listaReclamos.aArreglo();
        MergeSort.mergeSort(arr, 0, arr.length - 1, MergeSort.POR_CODIGO);
        return BusquedaBinaria.buscarPorCodigo(arr, codigo, 0, arr.length - 1);
    }

    // ordena mostrando por criterio usando MergeSort
    public void mostrarOrdenadosPorCriterio(int criterio, String nombreCriterio) {
        Reclamo[] arr = listaReclamos.aArreglo();
        MergeSort.mergeSort(arr, 0, arr.length - 1, criterio);
        System.out.println("Reclamos ordenados por " + nombreCriterio + ":");
        for (Reclamo r : arr) System.out.println(r);
    }

    // ordena por prioridad usando SelectionSort
    public void mostrarOrdenadosPorPrioridadCuadratico() {
        Reclamo[] arr = listaReclamos.aArreglo();
        SelectionSort.ordenarPorPrioridad(arr);
        System.out.println("Reclamos ordenados por prioridad (SelectionSort):");
        for (Reclamo r : arr) System.out.println(r);
    }

    // recorre por BST y por codigo
    public void mostrarArbolPorCodigo(String recorrido) {
        System.out.println("--- BST por codigo (" + recorrido + ") ---");
        switch (recorrido) {
            case "inorden":   arbolPorCodigo.mostrarInorden();   break;
            case "preorden":  arbolPorCodigo.mostrarPreorden();  break;
            case "postorden": arbolPorCodigo.mostrarPostorden(); break;
        }
    }

    // recorre por AVL y por prioridad
    public void mostrarArbolPorPrioridad(String recorrido) {
        System.out.println("--- AVL por prioridad (" + recorrido + ") ---");
        switch (recorrido) {
            case "inorden":   arbolPorPrioridad.mostrarInorden();   break;
            case "preorden":  arbolPorPrioridad.mostrarPreorden();  break;
            case "postorden": arbolPorPrioridad.mostrarPostorden(); break;
        }
    }

    // detecta reclamos a vencer en su fecha limite
    public void mostrarReclamosProximosAVencer(int diasMargen) {
        Reclamo[] arr = listaReclamos.aArreglo();
        LocalDate hoy = LocalDate.now();
        boolean hayUrgentes = false;

        System.out.println("Reclamos proximos a vencer (<= " + diasMargen + " dias) y no resueltos:");
        for (Reclamo r : arr) {
            if (r.getEstado().equalsIgnoreCase("Resuelto")) continue;
            long diasRestantes = ChronoUnit.DAYS.between(hoy, r.getFechaLimite());
            if (diasRestantes <= diasMargen) {
                System.out.println(r + " | Dias restantes: " + diasRestantes);
                hayUrgentes = true;
            }
        }
        if (!hayUrgentes) System.out.println("No hay reclamos proximos a vencer.");
    }

    public int cantidadReclamos() {
        return listaReclamos.getCantidad();
    }
}
