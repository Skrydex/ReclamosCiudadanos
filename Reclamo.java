package reclamosmunicipales;

import java.time.LocalDate;

/**
 *
 * @author basti
 */
public class Reclamo {

    private int codigo;
    private String nombreCiudadano;
    private String rutCiudadano;
    private String tipoReclamo;
    private String descripcion;
    private LocalDate fechaIngreso;
    private String estado;
    private int prioridad;
    private LocalDate fechaLimite;

    public Reclamo(int codigo, String nombreCiudadano, String rutCiudadano, String tipoReclamo,
                   String descripcion, LocalDate fechaIngreso, String estado, int prioridad,
                   LocalDate fechaLimite){
        this.codigo = codigo;
        this.nombreCiudadano = nombreCiudadano;
        this.rutCiudadano = rutCiudadano;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getNombreCiudadano(){
        return nombreCiudadano;
    }

    public String getRutCiudadano(){
        return rutCiudadano;
    }

    public String getTipoReclamo(){
        return tipoReclamo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public LocalDate getFechaIngreso(){
        return fechaIngreso;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public int getPrioridad(){
        return prioridad;
    }

    public void setPrioridad(int prioridad){
        this.prioridad = prioridad;
    }

    public LocalDate getFechaLimite(){
        return fechaLimite;
    }

    @Override
    public String toString(){
        return "Codigo: " + codigo + " | Ciudadano: " + nombreCiudadano + " | RUT: " + rutCiudadano
                + " | Tipo: " + tipoReclamo + " | Estado: " + estado + " | Prioridad: " + prioridad
                + " | Ingreso: " + fechaIngreso + " | Limite: " + fechaLimite;
    }
}
