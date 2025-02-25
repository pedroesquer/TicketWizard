package itson.usuariosDTOs;

import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;
import java.util.Date;

/**
 *
 * @author pedro
 */
public class ActualizarBoletoDTO {

    public enum Estado {
        Disponible,
        Vendido,
        Pendiente
    }

    private String numeroControl;
    private int codigoUsuario;
    private Estado estado;
    private float precioActual;
    private Date fechaLimite;

    public ActualizarBoletoDTO(String numeroControl, int codigoUsuario, Estado estado) {
        this.numeroControl = numeroControl;
        this.codigoUsuario = codigoUsuario;
        this.estado = estado;
    }

    public ActualizarBoletoDTO(String numeroControl, int codigoUsuario, Estado estado, float precioActual, Date fechaLimite) {
        this.numeroControl = numeroControl;
        this.codigoUsuario = codigoUsuario;
        this.estado = estado;
        this.precioActual = precioActual;
        this.fechaLimite = fechaLimite;
    }

    public float getPrecioActual() {
        return precioActual;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }
    
    

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getNumeroControl() {
        return numeroControl;
    }



}
