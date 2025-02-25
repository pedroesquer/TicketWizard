package itson.persistencia;

import itson.entidades.Boleto;
import itson.entidades.Usuario;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import itson.usuariosDTOs.NuevoBoletoEventoDTO;
import itson.usuariosDTOs.SesionDTO;
import java.sql.CallableStatement;
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
    Usuario usuarioActual = SesionDTO.getInstancia().getUsuarioActual();

    public BoletosDAO(ManejadorConexiones manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }

    public Boleto actualizarBoleto(ActualizarBoletoDTO boletoDTOActualizado) {
        String codigoSQL = """
            UPDATE boletos 
            SET codigoUsuario = ?, estado = ? 
            WHERE numeroControl = ? 
              AND numeroControl IN (
                SELECT bt.codigoBoleto
                FROM boletosTransacciones bt
                JOIN transacciones t ON bt.codigoTransaccion = t.codigoTransaccion
                WHERE t.estado = 'Completado'
              );            """;

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
                String numeroSerie = resultadosConsulta.getString("numeroSerie");
                float precioOriginal = resultadosConsulta.getFloat("precioOriginal");
                String estadoStr = resultadosConsulta.getString("estado");
                Boleto.Estado estado = Boleto.Estado.fromString(estadoStr); // Convertir a enum
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
SELECT numeroControl as Id, ev.fechaHora as 'Fecha', ev.nombre as 'Evento', concat(asiento, fila) as 'Asiento', precioOriginal, concat(recinto, ', ', ciudad) as 'Lugar', bo.tipoCompra
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
                String tipoCompra = resultadosConsulta.getString("tipoCompra");
                NuevoBoletoEventoDTO boletoEvento = new NuevoBoletoEventoDTO(Id, asiento, precioOriginal, fecha, evento, lugar, tipoCompra);
                ListaBoletos.add(boletoEvento);

            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar los boletos: " + ex.getMessage());

        }
        return ListaBoletos;
    }

    public static void comprarBoleto(Connection conn, int codigoUsuario, List<String> listaBoletos) {
        String storedProcedure = "{CALL comprarBoleto(?, ?)}";
        String boletosConcatenados = String.join(",", listaBoletos);

        try (CallableStatement cs = conn.prepareCall(storedProcedure)) {
            cs.setInt(1, codigoUsuario);
            cs.setString(2, boletosConcatenados);
            cs.executeUpdate();
            System.out.println("Transacción de compra completada.");
        } catch (SQLException e) {
            System.out.println("Error ejecutando el stored procedure.");
            e.printStackTrace();
        }
    }

    public void venderBoletos(Connection conn, int codigoUsuario, List<ActualizarBoletoDTO> listaBoletosDTO) throws SQLException {
        String call = "{CALL venderBoleto(?, ?, ?, ?)}"; // Se esperan 4 parámetros
        try (CallableStatement cs = conn.prepareCall(call)) {
            for (ActualizarBoletoDTO boletoDTO : listaBoletosDTO) {
                String numeroControl = boletoDTO.getNumeroControl(); 
                float precioActual = boletoDTO.getPrecioActual();
                java.sql.Date fechaLimiteVenta = new java.sql.Date(boletoDTO.getFechaLimite().getTime());

                // Se envían los 4 parámetros
                cs.setString(1, numeroControl);
                cs.setInt(2, codigoUsuario);
                cs.setFloat(3, precioActual);
                cs.setDate(4, fechaLimiteVenta);
                cs.executeUpdate();
            }
        }
    }


    public List<NuevoBoletoEventoDTO> consultarMisBoletos() {
        int usuarioActual = this.usuarioActual.getCodigoUsuario();
        String codigoSQL = """
        SELECT  bo.numeroControl as Id, 
                ev.fechaHora as 'Fecha', 
                ev.nombre as 'Evento', 
                concat(asiento, fila) as 'Asiento', 
                bo.precioOriginal, 
                concat(recinto, ', ', ciudad) as 'Lugar', 
                bo.numeroserie,
                bo.fechalimiteventa,
                bo.tipoCompra
            FROM boletos bo
            INNER JOIN eventos ev
            ON ev.codigoEvento = bo.codigoEvento
            WHERE codigoUsuario = ?;     
                           """;
        List<NuevoBoletoEventoDTO> ListaBoletos = new LinkedList<>();
        try {
            Connection conexion = this.manejadorConexiones.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            comando.setInt(1, usuarioActual);
            ResultSet resultadosConsulta = comando.executeQuery();

            while (resultadosConsulta.next()) {
                String Id = resultadosConsulta.getString("Id");
                LocalDateTime fecha = resultadosConsulta.getTimestamp("Fecha").toLocalDateTime();
                String evento = resultadosConsulta.getString("Evento");
                String asiento = resultadosConsulta.getString("Asiento");
                float precioOriginal = resultadosConsulta.getFloat("precioOriginal");
                String lugar = resultadosConsulta.getString("lugar");
                String tipoCompra = resultadosConsulta.getString("tipoCompra");
                NuevoBoletoEventoDTO boletoEvento = new NuevoBoletoEventoDTO(Id, asiento, precioOriginal, fecha, evento, lugar, tipoCompra);
                ListaBoletos.add(boletoEvento);

            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar los boletos: " + ex.getMessage());

        }
        return ListaBoletos;
    }

    public List<NuevoBoletoEventoDTO> consultarBoletosApartados() {
        String codigoSQL = """
        SELECT BO.NUMEROCONTROL AS 'Id',
            CONCAT(ASIENTO,FILA) AS 'Asiento',
            EV.FECHAHORA AS 'Fecha',
            EV.NOMBRE AS 'Evento',
            BO.PRECIOACTUAL AS 'PrecioActual',
            concat(recinto, ', ', ciudad) AS 'Lugar',
            BO.TIPOCOMPRA AS 'TipoCompra', 
            IF(BO.ESTADO = 'Pendiente de pago', 'Apartado','No apartado') AS 'Estado'
            FROM BOLETOS AS BO INNER JOIN EVENTOS AS EV ON BO.CODIGOEVENTO = EV.CODIGOEVENTO 
        WHERE BO.ESTADO = "Pendiente de Pago";
        """;
        List<NuevoBoletoEventoDTO> ListaBoletos = new LinkedList<>();
        try {
            Connection conexion = this.manejadorConexiones.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            ResultSet resultadosConsulta = comando.executeQuery();

            while (resultadosConsulta.next()) {
                String numeroControl = resultadosConsulta.getString("Id");
                LocalDateTime fecha = resultadosConsulta.getTimestamp("Fecha").toLocalDateTime();
                String evento = resultadosConsulta.getString("Evento");
                String asiento = resultadosConsulta.getString("Asiento");
                float precioActual = resultadosConsulta.getFloat("PrecioActual");
                String lugar = resultadosConsulta.getString("lugar");
                String tipoCompra = resultadosConsulta.getString("tipoCompra");
                String estado = resultadosConsulta.getString("Estado");
                NuevoBoletoEventoDTO boletoEvento = new NuevoBoletoEventoDTO(numeroControl, asiento, precioActual, fecha, evento, lugar, tipoCompra, estado);
                ListaBoletos.add(boletoEvento);

            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar los boletos: " + ex.getMessage());

        }
        return ListaBoletos;
    }

    public List<Boleto> actualizarBoletoVenta(ActualizarBoletoDTO boletoDTOActualizado) {
        String codigoSQL = """
    UPDATE boletos 
    SET codigoUsuario = ?, 
        estado = ?, 
        precioActual = ?, 
        fechaLimiteVenta = ? 
    WHERE numeroControl = ? 
      AND estado = 'Disponible';
    """;

        try (Connection conexion = manejadorConexiones.crearConexion(); PreparedStatement comando = conexion.prepareStatement(codigoSQL)) {

            comando.setInt(1, boletoDTOActualizado.getCodigoUsuario());
            comando.setString(2, boletoDTOActualizado.getEstado().name());
            comando.setFloat(3, boletoDTOActualizado.getPrecioActual());
            comando.setDate(4, boletoDTOActualizado.getFechaLimite() != null
                    ? new java.sql.Date(boletoDTOActualizado.getFechaLimite().getTime()) : null);
            comando.setString(5, boletoDTOActualizado.getNumeroControl());

            int filasAfectadas = comando.executeUpdate();
            System.out.println("Se actualizó el boleto, filas afectadas: " + filasAfectadas);

            if (filasAfectadas > 0) {
                List<Boleto> boletosActualizados = new LinkedList<>();
                Boleto boletoActualizado = new Boleto(
                        boletoDTOActualizado.getNumeroControl(),
                        Boleto.Estado.valueOf(boletoDTOActualizado.getEstado().name()),
                        boletoDTOActualizado.getPrecioActual(),
                        boletoDTOActualizado.getFechaLimite()
                );
                boletosActualizados.add(boletoActualizado);
                return boletosActualizados;
            }
        } catch (SQLException ex) {
            System.err.println("Error actualizando boleto para venta: " + ex.getMessage());
        }
        return new LinkedList<>(); // Devolver una lista vacía en caso de error
    }

}
