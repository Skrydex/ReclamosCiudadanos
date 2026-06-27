package reclamosmunicipales;

public class BusquedaBinaria {

    public static Reclamo buscarPorCodigo(Reclamo[] arr, int codigo, int izquierda, int derecha) {
        if (izquierda > derecha) return null;

        int medio = (izquierda + derecha) / 2;
