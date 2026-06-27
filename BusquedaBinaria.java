package reclamosmunicipales;

public class BusquedaBinaria {

    public static Reclamo buscarPorCodigo(Reclamo[] arr, int codigo, int izquierda, int derecha) {
        if (izquierda > derecha) return null;

        int medio = (izquierda + derecha) / 2;

        if (arr[medio].getCodigo() == codigo) {
            return arr[medio];
        } else if (arr[medio].getCodigo() > codigo) {
            return buscarPorCodigo(arr, codigo, izquierda, medio - 1);
        } else {
            return buscarPorCodigo(arr, codigo, medio + 1, derecha);
        }
    }
}
