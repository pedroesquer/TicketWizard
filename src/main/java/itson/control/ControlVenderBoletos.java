/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.control;

import itson.entidades.Boleto;
import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import itson.presentacion.ComprarBoletos;
import itson.presentacion.VenderBoletos;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rauln
 */
public class ControlVenderBoletos {
    private BoletosDAO boletosDAO;
    private VenderBoletos formVenderBoletos;

    public ControlVenderBoletos(BoletosDAO boletosDAO) {
        this.boletosDAO = boletosDAO;
        
    }
    
    public void iniciarCasoUso(){
        if (this.formVenderBoletos == null ){
            this.formVenderBoletos = new VenderBoletos(this);
        }
        this.formVenderBoletos.setVisible(true);
        this.formVenderBoletos.setLocationRelativeTo(null);
    }
    
    public void mostrarBoletos() {
        this.formVenderBoletos = new VenderBoletos(this);
    }
    
    public void actualizarBoleto(ActualizarBoletoDTO actualizarBoletoDTO) {
        Boleto boleto = this.boletosDAO.actualizarBoleto(actualizarBoletoDTO);
        this.mostrarBoletos();
    }


    public List<Boleto> consultarListaBoletos() {
        return this.boletosDAO.consultarBoletos();
    }
    
    public void procesarVentaBoletos(ManejadorConexiones manejadorConexiones, int codigoUsuario, List<String> listaBoletos) {
        try (Connection conn = manejadorConexiones.crearConexion()) {
            boletosDAO.venderBoletos(conn, codigoUsuario, listaBoletos);
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    
    
    
}
