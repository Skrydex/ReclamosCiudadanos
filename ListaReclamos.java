package reclamosmunicipales;

/**
 *
 * @author ale
 */
public class ListaReclamos {

    // Clase interna del nodo
    private class NodoReclamo {
        Reclamo reclamo;
        NodoReclamo siguiente;

        NodoReclamo(Reclamo reclamo){
            this.reclamo = reclamo;
            this.siguiente = null;
        }
    }

    private NodoReclamo cabeza;
    private int cantidad;

    public ListaReclamos(){
        this.cabeza = null;
        this.cantidad = 0;
    }

    // Insercion al final, O(n) ya que recorremos hasta el ultimo nodo
    public void agregar(Reclamo reclamo){
        NodoReclamo nuevo = new NodoReclamo(reclamo);

        if(cabeza == null){
            cabeza = nuevo;
        } else {
            NodoReclamo actual = cabeza;
            while(actual.siguiente != null){
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        cantidad++;
    }

    // Eliminacion por codigo, O(n) ya que hay que recorrer la lista
    public boolean eliminar(int codigo){
        if(cabeza == null){
            return false;
        }

        if(cabeza.reclamo.getCodigo() == codigo){
            cabeza = cabeza.siguiente;
            cantidad--;
            return true;
        }

        NodoReclamo actual = cabeza;
        while(actual.siguiente != null){
            if(actual.siguiente.reclamo.getCodigo() == codigo){
                actual.siguiente = actual.siguiente.siguiente;
                cantidad--;
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    // Convierte la lista enlazada a arreglo, para poder ordenar y buscar facilmente
    public Reclamo[] aArreglo(){
        Reclamo[] arreglo = new Reclamo[cantidad];
        NodoReclamo actual = cabeza;
        int i = 0;

        while(actual != null){
            arreglo[i] = actual.reclamo;
            actual = actual.siguiente;
            i++;
        }

        return arreglo;
    }

    public void mostrarTodos(){
        if(cabeza == null){
            System.out.println("No hay reclamos registrados.");
            return;
        }

        NodoReclamo actual = cabeza;
        while(actual != null){
            System.out.println(actual.reclamo);
            actual = actual.siguiente;
        }
    }

    public int getCantidad(){
        return cantidad;
    }
}

