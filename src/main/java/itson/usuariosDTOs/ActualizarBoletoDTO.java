package itson.usuariosDTOs;

import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;

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

    public ActualizarBoletoDTO(String numeroControl, int codigoUsuario, Estado estado) {
        this.numeroControl = numeroControl;
        this.codigoUsuario = codigoUsuario;
        this.estado = estado;
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
