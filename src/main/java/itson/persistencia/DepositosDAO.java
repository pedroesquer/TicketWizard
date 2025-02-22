
package itson.persistencia;

import itson.entidades.Deposito;
import itson.usuariosDTOs.NuevoDepositoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales & Ari Navarro
 */
public class DepositosDAO {
    
    private final ManejadorConexiones manejadorConexiones;

    public DepositosDAO(ManejadorConexiones manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }
    
    public Deposito registrarDeposito(NuevoDepositoDTO nuevoDepositoDTO){
        String codigoSQL = """
            INSERT INTO depositos(FECHAHORADEPOSITO,MONTO,CODIGOUSUARIO)
                                                  VALUES(?, ?, ?);
                           """;
        
        try {
            //establece la conexion con la base de datos
            Connection conexion = manejadorConexiones.crearConexion();
            //Construye un comando SQL
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, nuevoDepositoDTO.getFechaHoraDeposito());
            comando.setFloat(2, nuevoDepositoDTO.getMonto());
            comando.setInt(3, nuevoDepositoDTO.getCodigoUsuario());
            

            // Ejecutar la inserción
            int filasAfectadas = comando.executeUpdate();

            if (filasAfectadas > 0) {
                // Si se registró correctamente, devolver el usuario
                return new Deposito(nuevoDepositoDTO.getFechaHoraDeposito(), nuevoDepositoDTO.getMonto(), nuevoDepositoDTO.getCodigoUsuario());//

            } else {
                return null;  // Si no se registró, devolver null o manejar el error de otra manera
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }
    
}
