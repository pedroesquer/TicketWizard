
package itson.control;

import itson.entidades.Usuario;
import itson.persistencia.UsuariosDAO;
import itson.presentacion.Login;
import itson.presentacion.Menu;
import itson.presentacion.SignUp;
import itson.usuariosDTOs.NuevoUsuarioDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author Juana
 */
public class ControlRegistrarUsuario {
    
    private UsuariosDAO usuariosDAO;
    private SignUp formRegistroUsuario;
    private Menu formMenuPrincipal;
    private Login formInicioSesion;
    private ControlLogin controlLogin;

    public ControlRegistrarUsuario(UsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }
    
    
    
    public void iniciarFlujo() {
        this.formRegistroUsuario = new SignUp(this);
        this.formRegistroUsuario.setVisible(true);
    }
    
    public void registrarUsuario(NuevoUsuarioDTO nuevoUsuarioDTO) {
        //Validar datos 
        Usuario usuario  = this.usuariosDAO.registrarUsuario(nuevoUsuarioDTO);
        this.mostrarMensajeUsuarioRegistrado();
        this.mostrarFormularioCatalogoArtistas();
        this.formRegistroUsuario.dispose();
        
    }
    
    private void mostrarMensajeUsuarioRegistrado() {
        JOptionPane.showMessageDialog(formRegistroUsuario, "Registro con exito", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);

    }

    private void mostrarFormularioCatalogoArtistas() {
        this.formMenuPrincipal = new Menu();
        this.formMenuPrincipal.setVisible(true);
        
    }
}
