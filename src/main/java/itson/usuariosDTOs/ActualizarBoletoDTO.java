package itson.usuariosDTOs;

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
    
    private String numeroSerie;
    private int codigoUsuario;
    private Estado estado;

    public ActualizarBoletoDTO(String numeroSerie, int codigoUsuario, Estado estado) {
        this.numeroSerie = numeroSerie;
        this.codigoUsuario = codigoUsuario;
        this.estado = estado;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public Estado getEstado() {
        return estado;
    }
    
    
    
    

    
    
}
