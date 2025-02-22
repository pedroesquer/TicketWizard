/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.entidades;

import java.util.Objects;

/**
 *
 * @author Juan Pablo Heras, Pedro Morales & Ari Navarro
 */
public class Deposito {
    
    private Integer codigoDeposito;
    private String fechaHoraDeposito; 
    private float monto;
    private Integer codigoUsuario;

    public Deposito(Integer codigoDeposito, String fechaHoraDeposito, float monto, Integer codigoUsuario) {
        this.codigoDeposito = codigoDeposito;
        this.fechaHoraDeposito = fechaHoraDeposito;
        this.monto = monto;
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * Constructor de deposito que recibe todo excepto 
     * @param fechaHoraDeposito La fecha y hora del deposito
     * @param monto El monto del deposito
     * @param codigoUsuario codigoUsuario al cual le pertenece ese deposito
     */
    public Deposito(String fechaHoraDeposito, float monto, Integer codigoUsuario) {
        this.fechaHoraDeposito = fechaHoraDeposito;
        this.monto = monto;
        this.codigoUsuario = codigoUsuario;
    }

    public Integer getCodigoDeposito() {
        return codigoDeposito;
    }

    public void setCodigoDeposito(Integer codigoDeposito) {
        this.codigoDeposito = codigoDeposito;
    }

    public String getFechaHoraDeposito() {
        return fechaHoraDeposito;
    }

    public void setFechaHoraDeposito(String fechaHoraDeposito) {
        this.fechaHoraDeposito = fechaHoraDeposito;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.codigoDeposito);
        hash = 29 * hash + Objects.hashCode(this.fechaHoraDeposito);
        hash = 29 * hash + Float.floatToIntBits(this.monto);
        hash = 29 * hash + Objects.hashCode(this.codigoUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Deposito other = (Deposito) obj;
        if (Float.floatToIntBits(this.monto) != Float.floatToIntBits(other.monto)) {
            return false;
        }
        if (!Objects.equals(this.fechaHoraDeposito, other.fechaHoraDeposito)) {
            return false;
        }
        if (!Objects.equals(this.codigoDeposito, other.codigoDeposito)) {
            return false;
        }
        return Objects.equals(this.codigoUsuario, other.codigoUsuario);
    }

    @Override
    public String toString() {
        return "Deposito{" + "codigoDeposito=" + codigoDeposito + ", fechaHoraDeposito=" + fechaHoraDeposito + ", monto=" + monto + ", codigoUsuario=" + codigoUsuario + '}';
    }
    
    
    
    
    
    
    
}
