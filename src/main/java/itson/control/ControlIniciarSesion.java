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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
        if (this.formLogin == null) { // Solo se crea una vez
            this.formLogin = new Login(this);
        }
        this.formLogin.setVisible(true);
        this.formLogin.setLocationRelativeTo(null);
    }

    public Usuario autenticarUsuario(AccesoUsuarioDTO accesoUsuarioDTO) {
        // Primero validamos que las entradas no sean vacías
        if (validarFormulario(accesoUsuarioDTO.getCorreoElectronico(), accesoUsuarioDTO.getContrasenia())) {
            Usuario usuario = this.usuariosDAO.autenticarUsuario(accesoUsuarioDTO);
            if (usuario != null) { // Si el usuario es válido
                this.mostrarMenu();
                this.formLogin.setVisible(false); // Ocultamos el Login
            }
            return usuario;
        } else if (!validarFormatoCorreo(accesoUsuarioDTO.getCorreoElectronico())) {
            JOptionPane.showMessageDialog(null, "Ingresa un correo válido.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return null;
        } else {
            JOptionPane.showMessageDialog(null, "No debes dejar campos vacíos", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }


    public void mostrarMenu() {
        if (this.formMenu == null) { // Solo se crea una vez
            this.formMenu = new Menu();
        }
        this.formMenu.setVisible(true);
        this.formMenu.setLocationRelativeTo(null);
    }

    public void mostarFormularioRegistro() {
        // Se crea una nueva instancia siempre que se abre el registro
        this.formRegistro = new SignUp(this);
        this.formRegistro.setVisible(true);
        this.formRegistro.setLocationRelativeTo(formLogin);
        this.formLogin.setVisible(false); // Ocultamos el Login
    }

    public void mostrarLogin() {
        this.formLogin.setVisible(true);
        if (this.formRegistro != null) {
            this.formRegistro.dispose(); // Cerramos el registro para liberar memoria
            this.formRegistro = null;
        }
    }

    public Usuario registrarUsuario(NuevoUsuarioDTO nuevoUsuarioDTO) {
        // Se llama al método para revisar que no haya campos vacíos
        if (validarFormulario(nuevoUsuarioDTO.getNombre(), nuevoUsuarioDTO.getApellidoPaterno(), nuevoUsuarioDTO.getApellidoMaterno(),
                nuevoUsuarioDTO.getContraseniaHash(), nuevoUsuarioDTO.getFechaNacimiento(), nuevoUsuarioDTO.getCalle(),
                nuevoUsuarioDTO.getNumero(), nuevoUsuarioDTO.getCiudad(), nuevoUsuarioDTO.getColonia(), nuevoUsuarioDTO.getCorreoElectronico())) {

            // Verificamos que el correo no esté registrado
            if (usuariosDAO.correoRegistrado(nuevoUsuarioDTO.getCorreoElectronico())) {
                JOptionPane.showMessageDialog(null, "El correo ya está registrado", "Correo existente", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Si pasa todas las verificaciones, registramos al usuario
            Usuario usuario = this.usuariosDAO.registrarUsuario(nuevoUsuarioDTO);
            return usuario;

        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar usuario, verifica que: \n- No tengas campos vacíos\n-"
                    + " El correo tenga el formato formato@correo.com\n- El correo no esté registrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

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

        // Validación del correo
        if (correo.trim().isEmpty() || !this.validarFormatoCorreo(correo)) {
            return false;
        }
        return true;
    }

    private boolean validarFechaNacimiento(String fechaNacimiento) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        formato.setLenient(false);//Desactiva la posibilidad de que en caso de recibir por ejemplo un mes 13 lo tome como enero. O asi con el dia

        try {
            Date fechaNacimientoDate = formato.parse(fechaNacimiento); //Analiza la cadena fecha nacimiento, . Si la fecha está en el formato esperado y es válida, la fecha será parseada correctamente.
            
            LocalDate fechaNacimientoLocal = new java.sql.Date(fechaNacimientoDate.getTime()).toLocalDate();
            LocalDate fechaActual = LocalDate.now();
            // Verificar si la fecha de nacimiento no es en el futuro
            // Obtiene la fecha actual
            return !fechaNacimientoLocal.isAfter(fechaActual);
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean validarFormatoCorreo(String correo) {
        //Creamos una expresion regular
        String regexCorreo = "^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$";
        //Utilizamos patten y matcher para ver si nuestro correo concuerda con la regex
        Pattern pattern = Pattern.compile(regexCorreo);
        Matcher matcher = pattern.matcher(correo);

        return matcher.matches();
    }

    /**Metodo que verifica que en el login no se dejen campos vacios
     * 
     * @param correoElectronico el correo electronico que recibe el usuairo
     * @param contrasenia contrasenia que el usuario ingresa
     * @return True if the form is right, false if not. 
     */
    private boolean validarFormulario(String correoElectronico, String contrasenia) {
        return !(correoElectronico.trim().isEmpty() || contrasenia.trim().isEmpty());
    }
}
