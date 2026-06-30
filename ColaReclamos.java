package reclamosmunicipales;

/**
 *
 * @author basti
 */
public class ColaReclamos {

    private class NodoCola {
        Reclamo reclamo;
        NodoCola siguiente;

        NodoCola(Reclamo reclamo){
            this.reclamo = reclamo;
            this.siguiente = null;
        }
    }

    private NodoCola frente;
    private NodoCola fondo;
    private int cantidad;

    public ColaReclamos(){
        this.frente = null;
        this.fondo = null;
        this.cantidad = 0;
    }

    // encolamiento
    public void encolar(Reclamo reclamo){
        NodoCola nuevo = new NodoCola(reclamo);

        if(frente == null){
            frente = nuevo;
            fondo = nuevo;
        } else {
            fondo.siguiente = nuevo;
            fondo = nuevo;
        }
        cantidad++;
    }

    // desencolamiento
    public Reclamo desencolar(){
        if(frente == null){
            return null;
        }

        Reclamo reclamo = frente.reclamo;
        frente = frente.siguiente;

        if(frente == null){
            fondo = null;
        }

        cantidad--;
        return reclamo;
    }

    public boolean estaVacia(){
        return frente == null;
    }

    public void mostrarPendientes(){
        if(frente == null){
            System.out.println("No hay reclamos pendientes en la cola.");
            return;
        }

        NodoCola actual = frente;
        int posicion = 1;
        while(actual != null){
            System.out.println(posicion + ". " + actual.reclamo);
            actual = actual.siguiente;
            posicion++;
        }
    }

    public int getCantidad(){
        return cantidad;
    }
}
