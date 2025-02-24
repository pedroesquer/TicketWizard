
package itson.entidades;

import java.util.Objects;

/**
 *
 * @author juanpheras
 */
public class Transaccion {
    
    private Integer codigoTransaccion;
    private float monto;
    private String fechaHora;
    private Integer codigoComprador;
    private Integer codigoVendedor;
    private String transaccion;

    
    /**
     * Crea una transaccion cuando ya se obtuvo el tipo 
     * @param codigoTransaccion identificador de la transaccion
     * @param monto monto total de la transaccion
     * @param fechaHora fecha y hora de la transacción
     * @param transaccion si es compra o venta
     */
    public Transaccion(Integer codigoTransaccion, float monto, String fechaHora, String transaccion) {
        this.codigoTransaccion = codigoTransaccion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.transaccion = transaccion;
    }

    
    
     /**
     * Constructor que simula una transaccion de tipo compra de boletera directa ya que no hay un codigoVendedor
     * @param codigoTransaccion identificador
     * @param monto  monto total de la transaccion
     * @param fechaHora fecha y hora de la transacción
     * @param codigoComprador codigo del usuario que  realiza la compra
     * @param transaccion
     */
    public Transaccion(Integer codigoTransaccion, float monto, String fechaHora, Integer codigoComprador, String transaccion) {
        this.codigoTransaccion = codigoTransaccion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.codigoComprador = codigoComprador;
        this.transaccion = transaccion;
    }
    
    /**
     * Constructor que simula una transaccion de tipo reventa ya que recibe el codigoVendedor
     * @param codigoTransaccion identificador
     * @param monto  monto total de la transaccion
     * @param fechaHora fecha y hora de la transacción
     * @param codigoComprador codigo del usuario que  realiza la compra
     * @param codigoVendedor  codigo del usuario que vende el/los boletos
     * @param transaccion 
     */
    public Transaccion(Integer codigoTransaccion, float monto, String fechaHora, Integer codigoComprador, Integer codigoVendedor, String transaccion) {
        this.codigoTransaccion = codigoTransaccion;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.codigoComprador = codigoComprador;
        this.codigoVendedor = codigoVendedor;
        this.transaccion = transaccion;
    }

    public Integer getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(Integer codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getCodigoComprador() {
        return codigoComprador;
    }

    public void setCodigoComprador(Integer codigoComprador) {
        this.codigoComprador = codigoComprador;
    }

    public Integer getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(Integer codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.codigoTransaccion);
        hash = 53 * hash + Float.floatToIntBits(this.monto);
        hash = 53 * hash + Objects.hashCode(this.fechaHora);
        hash = 53 * hash + Objects.hashCode(this.codigoComprador);
        hash = 53 * hash + Objects.hashCode(this.codigoVendedor);
        hash = 53 * hash + Objects.hashCode(this.transaccion);
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
        final Transaccion other = (Transaccion) obj;
        if (Float.floatToIntBits(this.monto) != Float.floatToIntBits(other.monto)) {
            return false;
        }
        if (!Objects.equals(this.fechaHora, other.fechaHora)) {
            return false;
        }
        if (!Objects.equals(this.transaccion, other.transaccion)) {
            return false;
        }
        if (!Objects.equals(this.codigoTransaccion, other.codigoTransaccion)) {
            return false;
        }
        if (!Objects.equals(this.codigoComprador, other.codigoComprador)) {
            return false;
        }
        return Objects.equals(this.codigoVendedor, other.codigoVendedor);
    }

    @Override
    public String toString() {
        return "Transaccion{" + "codigoTransaccion=" + codigoTransaccion + ", monto=" + monto + ", fechaHora=" + fechaHora + ", codigoComprador=" + codigoComprador + ", codigoVendedor=" + codigoVendedor + ", transaccion=" + transaccion + '}';
    }
    
    

}