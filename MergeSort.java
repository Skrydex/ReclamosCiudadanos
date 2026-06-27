package reclamosmunicipales;

/**
 *
 * @author basti
 */
public class MergeSort {

    public static final int POR_PRIORIDAD = 1;
    public static final int POR_FECHA_INGRESO = 2;
    public static final int POR_FECHA_LIMITE = 3;
    public static final int POR_TIPO = 4;
    public static final int POR_CODIGO = 5;

    // se divide el arreglo en mitades recursivamente
    public static void mergeSort(Reclamo[] arr, int izquierda, int derecha, int criterio){
        if(izquierda < derecha){
            int medio = (izquierda + derecha) / 2;

            // divide
            mergeSort(arr, izquierda, medio, criterio);
            mergeSort(arr, medio + 1, derecha, criterio);

            // combina las dos mitades ordenadas
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
