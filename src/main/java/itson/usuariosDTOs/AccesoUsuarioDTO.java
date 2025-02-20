package itson.usuariosDTOs;

import itson.entidades.Usuario;
import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;

/**
 *
 * @author Juan Heras, Pedro Esquer & Ari Navarro
 */
public class AccesoUsuarioDTO {
    // Atributos necesarios para el login
    private String correoElectronico;
    private String contrasenia;
    
    /**
     * Constructor para crear un objeto de acceso con las credenciales
     * @param correoElectronico el correo del usuario
     * @param contrasenia la contraseña sin procesar
     */
    public AccesoUsuarioDTO(String correoElectronico, String contrasenia) {
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }
    
    /**
     * Intenta autenticar al usuario con las credenciales proporcionadas
     * @return Usuario si la autenticación es exitosa, null si falla
     */
    public Usuario autenticarUsuario() {
        try {
            ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
            UsuariosDAO usuariosDAO = new UsuariosDAO(manejadorConexiones);
            
            // Intentar autenticar usando el DAO
            return usuariosDAO.autenticarUsuario(this.correoElectronico, this.contrasenia);
        } catch (Exception e) {
            System.err.println("Error en la autenticación: " + e.getMessage());
            return null;
        }
    }

    // Getters - No incluimos setters por seguridad
    public String getCorreoElectronico() {
        return correoElectronico;
    }
}