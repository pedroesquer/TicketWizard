package itson.control;

import itson.persistencia.UsuariosDAO;
import itson.presentacion.ActualizarPerfil;
import itson.usuariosDTOs.ActualizarUsuarioDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author juanpheras
 */
public class ControlActualizarPerfil {

    private UsuariosDAO usuariosDAO;
    private ActualizarPerfil formActualizarPerfil;

    public ControlActualizarPerfil(UsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    public void inciarFlujo() {
        this.formActualizarPerfil = new ActualizarPerfil(this);
        this.formActualizarPerfil.setVisible(true);
        this.formActualizarPerfil.setLocationRelativeTo(null);

    }

    public void actualizarPerfil(ActualizarUsuarioDTO actualizarUsuarioDTO) {
        if (this.validarFormulario(actualizarUsuarioDTO.getCalle(), actualizarUsuarioDTO.getColonia(), actualizarUsuarioDTO.getCiudad(), actualizarUsuarioDTO.getNumero())) {
            this.usuariosDAO.actualizarUsuario(actualizarUsuarioDTO);
            JOptionPane.showMessageDialog(null, "Actualizado correctamente\nNecesitaras inicar sesión nuevamente", "Exito", JOptionPane.ERROR_MESSAGE);
            System.exit(0); //Cerramos el programa para actualizar
        } else {
            JOptionPane.showMessageDialog(null, "Verifica que no haya campos vacios", "Error al actualizar ", JOptionPane.ERROR_MESSAGE);

        }
    }

    private boolean validarFormulario(String calle, String colonia, String ciudad, String numero) {
        // Validación de campos vacíos


        return !(calle.trim().isEmpty() || colonia.trim().isEmpty() || ciudad.trim().isEmpty()
                || numero.trim().isEmpty());
    }
}
