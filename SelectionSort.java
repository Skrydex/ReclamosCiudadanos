package reclamosmunicipales;

public class SelectionSort {

    public static void ordenarPorPrioridad(Reclamo[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int indiceMin = i;
            for (int j = i + 1; j < n; j++) {
