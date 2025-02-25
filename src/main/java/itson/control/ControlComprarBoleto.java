package itson.control;

import itson.entidades.Boleto;
import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import itson.presentacion.ComprarBoletos;
import itson.presentacion.Menu;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales, Ari Raul Montoya
 */
public class ControlComprarBoleto {

    private BoletosDAO boletosDAO;
    private ComprarBoletos formComprarBoletos;
    
    public ControlComprarBoleto(BoletosDAO boletosDAO) {
        this.boletosDAO = boletosDAO;
    }

    public void iniciarCasoUso() {
        if (this.formComprarBoletos == null) { // Solo se crea una vez
            this.formComprarBoletos = new ComprarBoletos(this);
        }
        this.formComprarBoletos.setVisible(true);
        this.formComprarBoletos.setLocationRelativeTo(null);        
    }

    public void actualizarBoleto(ActualizarBoletoDTO actualizarBoletoDTO) {
        Boleto boleto = this.boletosDAO.actualizarBoleto(actualizarBoletoDTO);
        this.mostrarBoletos();
    }

    public void mostrarBoletos() {
        this.formComprarBoletos = new ComprarBoletos(this);
    }

    public List<Boleto> consultarListaBoletos() {
        return this.boletosDAO.consultarBoletos();
    }
    
    public void procesarCompraBoleto(ManejadorConexiones manejadorConexiones, int codigoUsuario, List<String> listaBoletos) {
        try (Connection conn = manejadorConexiones.crearConexion()) {
            boletosDAO.comprarBoleto(conn, codigoUsuario, listaBoletos);
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
}
