/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.ticketwizard;


import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;
import itson.presentacion.Login;
import java.util.List;

/**
 *
 * @author rauln
 */
public class TicketWizard {

    public static void main(String[] args) {
        
        
        ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
        UsuariosDAO usuariosDAO = new UsuariosDAO(manejadorConexiones);
        
        
        
        
        
        
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
    }
}
