package itson.persistencia;

import itson.entidades.Boleto;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class BoletosDAO {

    private final ManejadorConexiones manejadorConexiones;

    public BoletosDAO(ManejadorConexiones manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }

    public Boleto actualizar(ActualizarBoletoDTO boletoDTOActualizado) {
        String codigoSQL = """
                UPDATE BOLETOS SET numeroSerie = ?, codigoUsuario = ?, estado = ? WHERE numeroControl = ?;
            """;

        try {
            //Establece la conexión con el server de BD
            Connection conexion = manejadorConexiones.crearConexion();
            //Construye un comando que se podrá ejecutar posteriormente
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setString(1, boletoDTOActualizado.getNumeroSerie());
            comando.setInt(2, boletoDTOActualizado.getCodigoUsuario());
            comando.setString(3, boletoDTOActualizado.getEstado().name());
            int filasAfectadas = comando.executeUpdate();
            System.out.println("Se actualizó el boleto");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
