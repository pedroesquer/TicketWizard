package itson.control;

import itson.entidades.Boleto;
import itson.persistencia.BoletosDAO;
import itson.presentacion.ComprarBoletos;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import java.util.List;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales, Ari Raul Montoya
 */
public class ControlActualizarBoleto {

    private BoletosDAO boletosDAO;
    private ComprarBoletos formComprarBoletos;
    private ControlIniciarSesion controlSesion;

    public ControlActualizarBoleto(BoletosDAO boletosDAO, ComprarBoletos formComprarBoletos) {
        this.boletosDAO = boletosDAO;
        this.formComprarBoletos = formComprarBoletos;
    }

    public void iniciarCasoUso() {
        this.formComprarBoletos = new ComprarBoletos(controlSesion, this);
    }

    public void actualizarBoleto(ActualizarBoletoDTO actualizarBoletoDTO) {
        Boleto boleto = this.boletosDAO.actualizarBoleto(actualizarBoletoDTO);
        this.mostrarBoletos();
    }

    public void mostrarBoletos() {
        this.formComprarBoletos = new ComprarBoletos(controlSesion, this);
    }

    public List<Boleto> consultarListaArtistas() {
        return this.boletosDAO.consultarBoletos();
    }
    
}
