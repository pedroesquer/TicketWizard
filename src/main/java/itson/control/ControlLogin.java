
package itson.control;

import itson.presentacion.Login;

/**
 *
 * @author juanpheras
 */
public class ControlLogin {
    private Login formInicioSesion;
    private ControlRegistrarUsuario controlRegistro;
    
    public void iniciarFlujo() {
        this.formInicioSesion = new Login();
        this.formInicioSesion.setVisible(true);
        this.formInicioSesion.setLocationRelativeTo(null);
    }
  

}
