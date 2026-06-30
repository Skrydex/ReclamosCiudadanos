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

    private NodoBST raiz;
    private boolean ordenarPorPrioridad; // true = por prioridad (AVL) y false = por codigo (BST)

    public BSTReclamos(boolean ordenarPorPrioridad) {
        this.raiz = null;
        this.ordenarPorPrioridad = ordenarPorPrioridad;
    }

    private long obtenerClave(Reclamo reclamo) {
        if (ordenarPorPrioridad) {
            return (long) reclamo.getPrioridad() * 1000000L + reclamo.getCodigo();
        } else {
            return reclamo.getCodigo();
        }
    }

    // insercion recursiva
    public void insertar(Reclamo reclamo) {
        raiz = insertarRecursivo(raiz, reclamo);
    }

    private NodoBST insertarRecursivo(NodoBST nodo, Reclamo reclamo) {
        if (nodo == null) return new NodoBST(reclamo);

        long claveNueva   = obtenerClave(reclamo);
        long claveActual  = obtenerClave(nodo.reclamo);

        if (claveNueva < claveActual) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, reclamo);
        } else if (claveNueva > claveActual) {
            nodo.derecho = insertarRecursivo(nodo.derecho, reclamo);
        }
        return nodo;
    }

    // busqueda por codigo
    public Reclamo buscarPorCodigo(int codigo) {
        return buscarRecursivo(raiz, codigo);
    }

    private Reclamo buscarRecursivo(NodoBST nodo, int codigo) {
        if (nodo == null) return null;
        if (codigo == nodo.reclamo.getCodigo()) return nodo.reclamo;
        if (codigo < nodo.reclamo.getCodigo())  return buscarRecursivo(nodo.izquierdo, codigo);
        return buscarRecursivo(nodo.derecho, codigo);
    }

    // eliminacion recursiva por codigo
    public void eliminar(int codigo) {
        raiz = eliminarRecursivo(raiz, codigo);
    }

    private NodoBST eliminarRecursivo(NodoBST nodo, int codigo) {
        if (nodo == null) return null;

        long claveBuscada = ordenarPorPrioridad
                ? buscarClavePorCodigo(raiz, codigo)
                : codigo;
        long claveActual = obtenerClave(nodo.reclamo);

        if (claveBuscada < claveActual) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, codigo);
        } else if (claveBuscada > claveActual) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, codigo);
        } else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho   == null) return nodo.izquierdo;

            NodoBST sucesor = obtenerMinimo(nodo.derecho);
            nodo.reclamo = sucesor.reclamo;
            nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.reclamo.getCodigo());
        }
        return nodo;
    }

    // busca la clave mientras mientras recorre todo el arbol por codigo
    private long buscarClavePorCodigo(NodoBST nodo, int codigo) {
        if (nodo == null) return -1;
        if (nodo.reclamo.getCodigo() == codigo) return obtenerClave(nodo.reclamo);
        long izq = buscarClavePorCodigo(nodo.izquierdo, codigo);
        return izq != -1 ? izq : buscarClavePorCodigo(nodo.derecho, codigo);
    }

    private NodoBST obtenerMinimo(NodoBST nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    // inorden: izquierdo-derecho 
    public void mostrarInorden() {
        System.out.println("Recorrido inorden:");
        inordenRecursivo(raiz);
    }

    private void inordenRecursivo(NodoBST nodo) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo);
            System.out.println(nodo.reclamo);
            inordenRecursivo(nodo.derecho);
        }
    }

    // preorden: izquierdo-derecho
    public void mostrarPreorden() {
        System.out.println("Recorrido preorden:");
        preordenRecursivo(raiz);
    }

    private void preordenRecursivo(NodoBST nodo) {
        if (nodo != null) {
            System.out.println(nodo.reclamo);
            preordenRecursivo(nodo.izquierdo);
            preordenRecursivo(nodo.derecho);
        }
    }

    // postorden: izquierdo-derecho
    public void mostrarPostorden() {
        System.out.println("Recorrido postorden:");
        postordenRecursivo(raiz);
    }

    private void postordenRecursivo(NodoBST nodo) {
        if (nodo != null) {
            postordenRecursivo(nodo.izquierdo);
            postordenRecursivo(nodo.derecho);
            System.out.println(nodo.reclamo);
        }
    }
}
