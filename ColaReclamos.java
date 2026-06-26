package reclamosmunicipales;

public class ColaReclamos {

    private class NodoCola {
        Reclamo reclamo;
        NodoCola siguiente;

        NodoCola(Reclamo reclamo) {
            this.reclamo = reclamo;
            this.siguiente = null;
        }
    }

    private NodoCola frente;
    private NodoCola fondo;
    private int cantidad;

    public ColaReclamos() {
        this.frente = null;
        this.fondo = null;
        this.cantidad = 0;
    }
