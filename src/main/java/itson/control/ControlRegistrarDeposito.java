/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.control;

import itson.entidades.Deposito;
import itson.persistencia.DepositosDAO;
import itson.presentacion.agregarSaldo;
import itson.usuariosDTOs.NuevoDepositoDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales & Ari Navarro
 */
public class ControlRegistrarDeposito {
    private DepositosDAO depositosDAO;
    
    private agregarSaldo formAgregarSaldo;

    public ControlRegistrarDeposito(DepositosDAO depositosDAO) {
        this.depositosDAO = depositosDAO;
    }
    
    public Deposito RegistrarDeposito(NuevoDepositoDTO nuevoDepositoDTO){
        if(validarCantidadDeposito(nuevoDepositoDTO.getMonto())) {
            Deposito deposito = this.depositosDAO.registrarDeposito(nuevoDepositoDTO);
            return deposito;
        }
        JOptionPane.showMessageDialog(null, "Revisa que en el monto\n-No ingresaras un texto\n-No ingresaras cantidades negativas","Error de depósito", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    
    private boolean validarCantidadDeposito(Float cantidad){
        return cantidad > 0;
    }

    
    
            
}
