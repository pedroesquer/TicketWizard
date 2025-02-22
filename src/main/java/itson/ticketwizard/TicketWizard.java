/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package itson.ticketwizard;

import itson.control.ControlActualizarBoleto;
import itson.control.ControlIniciarSesion;
import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;
import itson.presentacion.Login;
import java.util.List;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author rauln
 */
public class TicketWizard {

    public static void main(String[] args) {

        ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
        UsuariosDAO usuariosDAO = new UsuariosDAO(manejadorConexiones);
        ControlIniciarSesion control = new ControlIniciarSesion(usuariosDAO);
        control.iniciarFlujo();

    }
}
