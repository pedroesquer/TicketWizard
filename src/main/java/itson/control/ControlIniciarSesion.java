package itson.control;

import itson.entidades.Usuario;
import itson.persistencia.UsuariosDAO;
import itson.presentacion.Login;
import itson.presentacion.Menu;
import itson.presentacion.SignUp;
import itson.usuariosDTOs.AccesoUsuarioDTO;
import itson.usuariosDTOs.NuevoUsuarioDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales & Ari Navarro
 */
public class ControlIniciarSesion {

    private UsuariosDAO usuariosDAO;
    private Login formLogin;
    private Menu formMenu;
    private SignUp formRegistro;
    private ControlActualizarBoleto controlActualizar;

    public ControlIniciarSesion(UsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

    public void iniciarFlujo() {
        this.formLogin = new Login(this);
        this.formLogin.setVisible(true);
        this.formLogin.setLocationRelativeTo(null);
    }

    public Usuario autenticarUsuario(AccesoUsuarioDTO accesoUsuarioDTO) {

        //Primero validamos que las entradsa no sean vacias
        if (validarFormulario(accesoUsuarioDTO.getCorreoElectronico(), accesoUsuarioDTO.getContrasenia())) {
            Usuario usuario = this.usuariosDAO.autenticarUsuario(accesoUsuarioDTO);
            if (usuario != null) { //Si el usuario que devuelve es null significa que las contraseñas son incorrectas
                this.mostrarFormularioMenu(); //En caso de que nos devuelva un usuario lo pasamos a menu
                this.formLogin.dispose();
            }
            return usuario;
        } else if (!validarFormatoCorreo(accesoUsuarioDTO.getCorreoElectronico())) {
            JOptionPane.showMessageDialog(null, "Ingresa un correo válido.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return null;
        } else {
            JOptionPane.showConfirmDialog(null, "No debes de dejar campos vacios", "Error", JOptionPane.INFORMATION_MESSAGE);

        }
        return null;
    }

    private void mostrarFormularioMenu() {
        this.formMenu = new Menu(this, controlActualizar);
        this.formMenu.setVisible(true);
    }

    public void mostarFormularioRegistro() {
        this.formRegistro = new SignUp(this);
        this.formRegistro.setVisible(true);
        this.formRegistro.setLocationRelativeTo(null);
        this.formLogin.dispose();
    }

    public Usuario registrarUsuario(NuevoUsuarioDTO nuevoUsuarioDTO) {

        //Se llama al metodo validar formulario para revisar que no este vacio
        if (validarFormulario(nuevoUsuarioDTO.getNombre(), nuevoUsuarioDTO.getApellidoPaterno(), nuevoUsuarioDTO.getApellidoMaterno(),
                nuevoUsuarioDTO.getContraseniaHash(), nuevoUsuarioDTO.getFechaNacimiento(), nuevoUsuarioDTO.getCalle(),
                nuevoUsuarioDTO.getNumero(), nuevoUsuarioDTO.getCiudad(), nuevoUsuarioDTO.getColonia(), nuevoUsuarioDTO.getCorreoElectronico())) {

            //Si paso la verificacion de campos vacios se procede a revisar el correo que no este registrado
            if (usuariosDAO.correoRegistrado(nuevoUsuarioDTO.getCorreoElectronico())) {
                JOptionPane.showMessageDialog(null, "El correo ya esta registrado", "Correo existente"
                        + "", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            //En caso de pasar todas las verificaciones se registra al usuario
            Usuario usuario = this.usuariosDAO.registrarUsuario(nuevoUsuarioDTO);
            return usuario;

        } else {

            JOptionPane.showMessageDialog(null, "Error al registrar usuario, verifica que: \n-No tengas campos vacios\n-"
                    + "El correo tenga el formato formato@correo.com\n-El correo no este registrado", "Error"
                    + "", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * Método de validación de los datos del formulario el cual revisa si ningun
     * campo esta vacion o en el caso de correo revisa si el formato es correcto
     *
     * @param nombre nombre
     * @param apellidoPaterno apllido paterno
     * @param apellidoMaterno apellido materno
     * @param contrasena contraseña ingresada
     * @param fechaNacimiento fecha de nacimiento ingresada
     * @param calle calle de direccion
     * @param numeroCasa numero de casa de direccion
     * @param ciudad ciudad de direccion
     * @param colonia colonia
     * @param correo correo electronico
     * @return
     */
    private boolean validarFormulario(String nombre, String apellidoPaterno, String apellidoMaterno,
            String contrasena, String fechaNacimiento, String calle,
            String numeroCasa, String ciudad,
            String colonia, String correo) {

        // Validación de campos vacíos
        if (nombre.trim().isEmpty() || apellidoPaterno.trim().isEmpty() || apellidoMaterno.trim().isEmpty()
                || contrasena.trim().isEmpty() || !validarFechaNacimiento(fechaNacimiento) || calle.trim().isEmpty()
                || numeroCasa.trim().isEmpty() || ciudad.trim().isEmpty() || colonia.trim().isEmpty()) {

            return false;
        }

        // Validación del correo. Se valida si ya esta registrado, si es el formato correcto o si esta vacio
        if (correo.trim().isEmpty() || !this.validarFormatoCorreo(correo)) {
//            JOptionPane.showMessageDialog(null, "El correo es invalido ya esta registrado, revisa que no este vacio"
//                    + " o tenga el siguiente formato: \n nombre@correo.com", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Si todo está válido, retornamos true
        return true;
    }

    // Método auxiliar para validar la fecha de nacimiento (formato yyyy-mm-dd)
    private boolean validarFechaNacimiento(String fechaNacimiento) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        formato.setLenient(false);

        try {
            formato.parse(fechaNacimiento); // Si no lanza una excepción, la fecha es válida
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validarFormatoCorreo(String correo) {
        String regexCorreo = "^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$";

        Pattern pattern = Pattern.compile(regexCorreo);
        Matcher matcher = pattern.matcher(correo);

        return matcher.matches();
    }

    private boolean validarFormulario(String correoElectronico, String contrasenia) {
        if (correoElectronico.trim().isEmpty() || contrasenia.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
