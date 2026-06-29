package reclamosmunicipales;

public class PilaHistorial {
    // Clase interna del nodo, mantiene el proyecto compacto
    private class NodoHistorial {
        String cambio;
        NodoHistorial siguiente;

        NodoHistorial(String cambio){
            this.cambio = cambio;
            this.siguiente = null;
        }
    }

    private NodoHistorial tope;
    private int cantidad;

    public PilaHistorial(){
        this.tope = null;
        this.cantidad = 0;
    }

    // Push: se agrega el cambio mas reciente arriba de la pila, O(1)
    public void apilar(String cambio){
        NodoHistorial nuevo = new NodoHistorial(cambio);
        nuevo.siguiente = tope;
        tope = nuevo;
        cantidad++;
    }

    // Pop: se retira el ultimo cambio realizado (LIFO), O(1)
    public String desapilar(){
        if(tope == null){
            return null;
        }

        String cambio = tope.cambio;
        tope = tope.siguiente;
        cantidad--;
        return cambio;
    }

    public boolean estaVacia(){
        return tope == null;
    }

    // Muestra el historial completo, del cambio mas reciente al mas antiguo
    public void mostrarHistorial(){
        if(tope == null){
            System.out.println("No hay cambios registrados para este reclamo.");
            return;
        }

        NodoHistorial actual = tope;
        while(actual != null){
            System.out.println("- " + actual.cambio);
            actual = actual.siguiente;
        }
    }

    public int getCantidad(){
        return cantidad;
    }
}
