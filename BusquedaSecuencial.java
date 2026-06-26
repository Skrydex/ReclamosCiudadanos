package reclamosmunicipales;

/**
 *
 * @author basti
 */

public class BusquedaSecuencial {

    public static Reclamo buscarPorCodigo(Reclamo[] arr, int codigo) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getCodigo() == codigo) {
                return arr[i];
            }
        }
        return null;
    }
}
