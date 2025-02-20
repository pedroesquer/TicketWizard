package itson.persistencia;

import itson.entidades.Boleto;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import itson.usuariosDTOs.NuevoBoletoEventoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    public Boleto actualizarBoleto(ActualizarBoletoDTO boletoDTOActualizado) {
        String codigoSQL = """
                UPDATE BOLETOS SET codigoUsuario = ?, estado = ? WHERE numeroControl = ?;
            """;

        try {
            //Establece la conexión con el server de BD
            Connection conexion = manejadorConexiones.crearConexion();
            //Construye un comando que se podrá ejecutar posteriormente
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, boletoDTOActualizado.getCodigoUsuario());
            comando.setString(2, boletoDTOActualizado.getEstado().name());
            comando.setString(3, boletoDTOActualizado.getNumeroControl());
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
    
        public List<NuevoBoletoEventoDTO> consultarBoletosEventos() {
        String codigoSQL = """
SELECT numeroControl as Id, ev.fechaHora as 'Fecha', ev.nombre as 'Evento', concat(asiento, fila) as 'Asiento', precioOriginal, concat(recinto, ', ', ciudad) as 'Lugar', bo.numeroserie, 
                           		CASE 
                                   WHEN codigoUsuario IS NULL THEN 'Boletera'
                                   ELSE 'Reventa'
                               END AS Tipo 
                           FROM BOLETOS AS BO INNER JOIN EVENTOS AS EV ON BO.CODIGOEVENTO = EV.CODIGOEVENTO WHERE BO.ESTADO = "Disponible";                           
                           """;
        List<NuevoBoletoEventoDTO> ListaBoletos = new LinkedList<>();
        try {
            Connection conexion = this.manejadorConexiones.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            ResultSet resultadosConsulta = comando.executeQuery();

            while (resultadosConsulta.next()) {
                String Id = resultadosConsulta.getString("Id");
                LocalDateTime fecha = resultadosConsulta.getTimestamp("Fecha").toLocalDateTime();
                String evento = resultadosConsulta.getString("Evento");
                String asiento = resultadosConsulta.getString("Asiento");
                float precioOriginal = resultadosConsulta.getFloat("precioOriginal");
                String lugar = resultadosConsulta.getString("lugar");
                String tipo = resultadosConsulta.getString("Tipo");
                NuevoBoletoEventoDTO boletoEvento = new NuevoBoletoEventoDTO(Id,asiento, precioOriginal, fecha, evento, lugar, tipo);
                ListaBoletos.add(boletoEvento);

            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar los boletos: " + ex.getMessage());

        }
        return ListaBoletos;
    }
}
