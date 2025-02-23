/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package itson.ticketwizard;

import itson.control.ControlIniciarSesion;
import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;

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
