/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.usuariosDTOs;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales & Ari Navarro
 */
public class NuevoDepositoDTO {
    
    private String fechaHoraDeposito;
    private float monto;
    private Integer codigoUsuario;

    public NuevoDepositoDTO(String fechaHoraDeposito, float monto, Integer codigoUsuario) {
        this.fechaHoraDeposito = fechaHoraDeposito;
        this.monto = monto;
        this.codigoUsuario = codigoUsuario;
    }

    public String getFechaHoraDeposito() {
        return fechaHoraDeposito;
    }

    public float getMonto() {
        return monto;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }
    
    
    
     
    
}
