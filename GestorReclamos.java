package reclamosmunicipales;

public class GestorReclamos {

    private ListaReclamos listaReclamos;

    public GestorReclamos() {
        this.listaReclamos = new ListaReclamos();
    }

    public void registrarReclamo(Reclamo reclamo) {
        listaReclamos.agregar(reclamo);
        System.out.println("Reclamo registrado correctamente.");
    }

    public boolean modificarDescripcion(int codigo, String nuevaDescripcion) {
        Reclamo[] arr = listaReclamos.aArreglo();
        for (Reclamo r : arr) {
            if (r.getCodigo() == codigo) {
                r.setDescripcion(nuevaDescripcion);
                return true;
            }
        }
        return false;
    }

    public boolean actualizarEstado(int codigo, String nuevoEstado) {
        Reclamo[] arr = listaReclamos.aArreglo();
        for (Reclamo r : arr) {
            if (r.getCodigo() == codigo) {
                r.setEstado(nuevoEstado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarReclamo(int codigo) {
        return listaReclamos.eliminar(codigo);
    }

    public void mostrarTodos() {
        listaReclamos.mostrarTodos();
    }

    public int cantidadReclamos() {
        return listaReclamos.getCantidad();
    }
}
