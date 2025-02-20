/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.ticketwizard;


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
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa contra1");
        String contra1 = sc.nextLine();
        String contraseniaHasheada1 = BCrypt.hashpw(contra1, BCrypt.gensalt(12));
        System.out.println(contraseniaHasheada1);
        
        System.out.println("Ingresa contra2");
        String contra2 = sc.nextLine();
        String contraseniaHasheada2 = BCrypt.hashpw(contra2, BCrypt.gensalt(12));
        System.out.println(contraseniaHasheada2);
        System.out.println(BCrypt.checkpw(contra2, contraseniaHasheada1));
        
        
        ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
        UsuariosDAO usuariosDAO = new UsuariosDAO(manejadorConexiones);
        
        
        
        
        
        
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
    }
}
