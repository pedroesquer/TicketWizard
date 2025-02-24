package itson.persistencia;

import itson.entidades.Boleto;
import itson.entidades.Transaccion;
import itson.entidades.Usuario;
import itson.usuariosDTOs.SesionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.AEADBadTagException;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales & Ari Navarro
 */
public class TransaccionesDAO {

    private final ManejadorConexiones manejadorConexiones;

    Usuario usuarioActual = SesionDTO.getInstancia().getUsuarioActual();

    public TransaccionesDAO(ManejadorConexiones manejadorConexiones) {
        this.manejadorConexiones = manejadorConexiones;
    }

    public List<Transaccion> consultarTransacciones() {
        String codigoSQL = """
                    SELECT TR.CODIGOTRANSACCION as 'codigoTransaccion', IF(RE.CODIGOREVENDEDOR = ?, "Venta", IF(TR.CODIGOCOMPRADOR = ?, "Compra", "")) AS TRANSACCION,
                    TR.MONTO as 'monto', TR.FECHAHORA as 'fechaHora'
                    FROM TRANSACCIONES TR
                    LEFT JOIN REVENTAS RE
                    ON RE.CODIGOTRANSACCION = TR.CODIGOTRANSACCION
                    LEFT JOIN COMPRAS CO
                    ON CO.CODIGOTRANSACCION = TR.CODIGOTRANSACCION ;
                    """;
        List<Transaccion> listaTransacciones = new LinkedList<>();
        try {
            Connection conexion = this.manejadorConexiones.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);

            int codigoUsuario = SesionDTO.getInstancia().getUsuarioActual().getCodigoUsuario();
            comando.setInt(1, codigoUsuario);
            comando.setInt(2, codigoUsuario);

            ResultSet resultadosConsulta = comando.executeQuery();

            while (resultadosConsulta.next()) {
                int codigoTransaccion = resultadosConsulta.getInt("codigoTransaccion");
                String tipoTransaccion = resultadosConsulta.getString("Transaccion");
                float monto = resultadosConsulta.getFloat("monto");
                String fechaHora = resultadosConsulta.getString("fechaHora");
                Transaccion transaccion = new Transaccion(codigoTransaccion, monto, fechaHora, tipoTransaccion);
                listaTransacciones.add(transaccion);

            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar transacciones: " + ex.getMessage());

        }
        return listaTransacciones;
    }

}
