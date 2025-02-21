package itson.control;

import itson.entidades.Usuario;
import itson.persistencia.UsuariosDAO;
import itson.presentacion.Login;
import itson.presentacion.Menu;
import itson.usuariosDTOs.AccesoUsuarioDTO;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales & Ari Navarro
 */
public class ControlIniciarSesion {

    private UsuariosDAO usuariosDAO;
    private Login formLogin;
    private Menu formMenu;

    public ControlIniciarSesion(UsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    public void iniciarFlujo() {
        this.formLogin = new Login(this);
        this.formLogin.setVisible(true);
        this.formLogin.setLocationRelativeTo(null);
    }

    public Usuario autenticarUsuario(AccesoUsuarioDTO accesoUsuarioDTO) {
        Usuario usuario = this.usuariosDAO.autenticarUsuario(accesoUsuarioDTO);
        this.mostrarFormularioMenu();
        this.formLogin.dispose();
        return usuario;
    }

    private void mostrarFormularioMenu() {
        this.formMenu = new Menu(this);
        this.formMenu.setVisible(true);

    }

}
