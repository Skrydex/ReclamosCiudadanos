package reclamosmunicipales;

/**
 *
 * @author ale
 */
public class MergeSort {

    // Criterios disponibles para ordenar
    public static final int POR_PRIORIDAD = 1;
    public static final int POR_FECHA_INGRESO = 2;
    public static final int POR_FECHA_LIMITE = 3;
    public static final int POR_TIPO = 4;
    public static final int POR_CODIGO = 5;

    // Metodo principal: divide el arreglo en mitades recursivamente (Divide and Conquer)
    // Complejidad: O(n log n) en todos los casos
    public static void mergeSort(Reclamo[] arr, int izquierda, int derecha, int criterio){
        if(izquierda < derecha){
            int medio = (izquierda + derecha) / 2;

            // Divide
            mergeSort(arr, izquierda, medio, criterio);
            mergeSort(arr, medio + 1, derecha, criterio);

            // Conquista (combina las dos mitades ya ordenadas)
            combinar(arr, izquierda, medio, derecha, criterio);
        }
    }

    private static boolean vaPrimero(Reclamo a, Reclamo b, int criterio){
        switch(criterio){
            case POR_PRIORIDAD:
                return a.getPrioridad() <= b.getPrioridad();
            case POR_FECHA_INGRESO:
                return !a.getFechaIngreso().isAfter(b.getFechaIngreso());
            case POR_FECHA_LIMITE:
                return !a.getFechaLimite().isAfter(b.getFechaLimite());
            case POR_TIPO:
                return a.getTipoReclamo().compareTo(b.getTipoReclamo()) <= 0;
            case POR_CODIGO:
                return a.getCodigo() <= b.getCodigo();
            default:
                return true;
        }
    }

    private static void combinar(Reclamo[] arr, int izquierda, int medio, int derecha, int criterio){
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        Reclamo[] mitadIzq = new Reclamo[n1];
        Reclamo[] mitadDer = new Reclamo[n2];

        for(int i = 0; i < n1; i++){
            mitadIzq[i] = arr[izquierda + i];
        }
        for(int j = 0; j < n2; j++){
            mitadDer[j] = arr[medio + 1 + j];
        }

        int i = 0, j = 0;
        int k = izquierda;

        while(i < n1 && j < n2){
            if(vaPrimero(mitadIzq[i], mitadDer[j], criterio)){
                arr[k] = mitadIzq[i];
                i++;
            } else {
                arr[k] = mitadDer[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            arr[k] = mitadIzq[i];
            i++;
            k++;
        }

        while(j < n2){
            arr[k] = mitadDer[j];
            j++;
            k++;
        }
    }
}

