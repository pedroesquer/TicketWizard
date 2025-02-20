package itson.persistencia;

import itson.entidades.Boleto;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

    public List<Boleto> consultarBoletos() {
        String codigoSQL = """
                           SELECT numeroControl, 
                           asiento, 
                           fila, 
                           numeroSerie, 
                           precioOriginal, 
                           estado, 
                           codigoUsuario, 
                           codigoEvento 
                           FROM BOLETOS;
                           """;
        List<Boleto> ListaBoletos = new LinkedList<>();
        try {
            Connection conexion = this.manejadorConexiones.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            ResultSet resultadosConsulta = comando.executeQuery();

            while (resultadosConsulta.next()) {
                String numeroControl = resultadosConsulta.getString("numeroControl");
                int asiento = resultadosConsulta.getInt("asiento");
                String fila = resultadosConsulta.getString("fila");
                String numeroSerie = resultadosConsulta.getString("numeroSerie");;
                float precioOriginal = resultadosConsulta.getFloat("precioOriginal");
                String estado = resultadosConsulta.getString("estado");
                int codigoUsuario = resultadosConsulta.getInt("codigoUsuario");
                int codigoEvento = resultadosConsulta.getInt("codigoEvento");
                Boleto boleto = new Boleto(numeroControl, asiento, fila, numeroSerie, precioOriginal, estado, codigoUsuario, codigoEvento);
                ListaBoletos.add(boleto);

            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar los boletos: " + ex.getMessage());

        }
        return ListaBoletos;
    }
}
