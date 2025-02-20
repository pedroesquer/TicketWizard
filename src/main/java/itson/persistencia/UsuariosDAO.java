package itson.persistencia;

import itson.entidades.Usuario;
import itson.usuariosDTOs.NuevoUsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author juanpheras
 */
public class UsuariosDAO {

    private final ManejadorConexiones manejadorConexiones;

    public UsuariosDAO(ManejadorConexiones manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }

    public Usuario registrarUsuario(NuevoUsuarioDTO usuarioDTO) {
        String codigoSQL = """
            INSERT INTO usuarios(NOMBRE,APELLIDOPATERNO, APELLIDOMATERNO, CORREOELECTRONICO, CONTRASEÑA_HASH,
                           FECHA_NACIMIENTO, CIUDAD, CALLE, COLONIA, NUMERO)
                                                  VALUES(?, ?, ? , ?,?,?, ?, ?, ?, ?);
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
            comando.setString(7, usuarioDTO.getCiudad());
            comando.setString(8, usuarioDTO.getCalle());
            comando.setString(9, usuarioDTO.getColonia());
            comando.setString(10, usuarioDTO.getNumero());

            // Ejecutar la inserción
            int filasAfectadas = comando.executeUpdate();

            if (filasAfectadas > 0) {
                // Si se registró correctamente, devolver el usuario
                return new Usuario(usuarioDTO.getNombre(), usuarioDTO.getApellidoPaterno(),
                        usuarioDTO.getApellidoMaterno(), usuarioDTO.getCorreoElectronico(),
                        usuarioDTO.getCiudad(), usuarioDTO.getCalle(), usuarioDTO.getColonia(), usuarioDTO.getNumero());  //

            } else {
                return null;  // Si no se registró, devolver null o manejar el error de otra manera
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }

    /**
     * Método que devuelve true si encontro coincidencias para el login
     * @param correoElectronico
     * @param contrasenia
     * @return 
     */
    public Usuario autenticarUsuario(String correoElectronico, String contrasenia) {
        String codigoSQL = """
            SELECT CODIGOUSUARIO,NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, CORREOELECTRONICO,
                   CONTRASEÑA_HASH, CIUDAD, CALLE, COLONIA, NUMERO , SALDO
            FROM usuarios 
            WHERE CORREOELECTRONICO = ?
            """;
            
        try {
            Connection conexion = manejadorConexiones.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, correoElectronico);
            
            ResultSet resultados = comando.executeQuery();
            
            if (resultados.next()) {
                String contraseniaHashAlmacenada = resultados.getString("CONTRASEÑA_HASH");
                
                // Verifica la contraseña usando el mismo método de hash que usaste en el registro
                if (verificarContrasenia(contrasenia, contraseniaHashAlmacenada)) {
                    return new Usuario(
                        resultados.getInt("CODIGOUSUARIO"),
                        resultados.getString("NOMBRE"),
                        resultados.getString("APELLIDOPATERNO"),
                        resultados.getString("APELLIDOMATERNO"),
                        resultados.getString("CORREOELECTRONICO"),
                        resultados.getString("CIUDAD"),
                        resultados.getString("CALLE"),
                        resultados.getString("COLONIA"),
                        resultados.getString("NUMERO"),
                        resultados.getFloat("SALDO")
                    );
                }
            }
            return null; // Usuario no encontrado o contraseña incorrecta
            
        } catch (SQLException ex) {
            System.err.println("Error al autenticar usuario: " + ex.getMessage());
            return null;
        }
    }

    private boolean verificarContrasenia(String contraseniaIngresada, String contraseniaHashAlmacenada) {
        // Aquí debes usar el mismo método de hash que usaste en el registro
        // Por ejemplo, si usaste BCrypt:
        return BCrypt.checkpw(contraseniaIngresada, contraseniaHashAlmacenada);
        
        // Si usaste otro método de hash, ajusta esta verificación según corresponda
    }
}
