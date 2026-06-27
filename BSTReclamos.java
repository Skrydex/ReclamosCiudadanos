package reclamosmunicipales;

public class BSTReclamos {

    private class NodoBST {
        Reclamo reclamo;
        NodoBST izquierdo;
        NodoBST derecho;

        NodoBST(Reclamo reclamo) {
            this.reclamo = reclamo;
            this.izquierdo = null;
            this.derecho = null;
        }
    }
