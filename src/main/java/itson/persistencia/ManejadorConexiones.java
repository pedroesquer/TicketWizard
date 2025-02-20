/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class ManejadorConexiones {
            //Información para conectarme a la bd
        private final String cadenaConexion = "jdbc:mysql://localhost/TICKETWIZARD";
        private final String usuario = "root";
        //Colocar su contraseña de la BDD
        private final String contrasenia = "qtrmxpkr28esc";
        
        public Connection crearConexion() throws SQLException{
            Connection conexion = DriverManager.getConnection(
            cadenaConexion, usuario, contrasenia);
            return conexion;
        }
}
