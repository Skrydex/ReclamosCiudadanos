package reclamosmunicipales;

public class PilaHistorial {

    private class NodoHistorial {
        String cambio;
        NodoHistorial siguiente;

        NodoHistorial(String cambio) {
            this.cambio = cambio;
            this.siguiente = null;
        }
    }

    private NodoHistorial tope;
    private int cantidad;

    public PilaHistorial() {
        this.tope = null;
        this.cantidad = 0;
    }

    public void apilar(String cambio) {
        NodoHistorial nuevo = new NodoHistorial(cambio);
        nuevo.siguiente = tope;
        tope = nuevo;
        cantidad++;
    }

    public String desapilar() {
        if (tope == null) return null;
        String cambio = tope.cambio;
        tope = tope.siguiente;
        cantidad--;
        return cambio;
    }
