package itson.persistencia;

import itson.entidades.Usuario;
import itson.usuariosDTOs.NuevoUsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author juanpheras
 */
public class UsuariosDAO {

    private final ManejadorConexiones manejadorConexiones;

    public UsuariosDAO(ManejadorConexiones manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }

    public Usuario registrarUsuario(NuevoUsuarioDTO usuarioDTO, int idDireccion) {
        String codigoSQL = """
            INSERT INTO usuarios(NOMBRE,APELLIDOPATERNO, APELLIDOMATERNO, CORREOELECTRONICO, CONTRASEÑAHASH,
                           FECHA_NACIMIENTO, CODIGODIRECCIONUSUARIO)
                                                  VALUES(?, ?, ? , ?,?,?, ?);
                           """;

        try {
            //establece la conexion con la base de datos
            Connection conexion = manejadorConexiones.crearConexion();
            //Construye un comando SQL
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, usuarioDTO.getNombre());
            comando.setString(2, usuarioDTO.getApellidoPaterno());
            comando.setString(3, usuarioDTO.getApellidoMaterno());
            comando.setString(4, usuarioDTO.getCorreoElectronico());
            comando.setString(5, usuarioDTO.getContraseniaHash());
            comando.setString(6, usuarioDTO.getFechaNacimiento());
            comando.setInt(7, usuarioDTO.getCodigoDireccionUsuario());

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        
    }

}
