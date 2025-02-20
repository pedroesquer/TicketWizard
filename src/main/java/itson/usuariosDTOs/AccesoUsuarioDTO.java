
package itson.usuariosDTOs;

import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;

/**
 *
 * @author Juan Heras, Pedro Esquer & Ari Navarro
 */
public class AccesoUsuarioDTO {
    
     private String correoElectronico;
     private String contraseniaPlana;

    public AccesoUsuarioDTO(String correoElectronico, String contraseniaHasheada) {
        this.correoElectronico = correoElectronico;
        this.contraseniaPlana = contraseniaHasheada;
    }
    
     public boolean accederUsuario(AccesoUsuarioDTO accesoUsuarioDTO){
        ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
        UsuariosDAO usuariosDAO = new UsuariosDAO(manejadorConexiones);
        return usuariosDAO.accederUsuario(accesoUsuarioDTO);
    }


    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContraseniaPlana() {
        return contraseniaPlana;
    }
     
     
    
}
