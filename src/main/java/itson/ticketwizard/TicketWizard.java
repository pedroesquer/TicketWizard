/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package itson.ticketwizard;

import itson.presentacion.Login;

/**
 *
 * @author rauln
 */
public class TicketWizard {

    public static void main(String[] args) {
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null); 
    }
}
