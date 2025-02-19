package itson.entidades;

import java.util.Objects;

/**
 *
 * @author Juan Pablo Heras, Ari Montoya & Pedro Esquer
 */
public class Boleto {
    
    private String numeroControl; //Numero interno de control
    private Integer numeroAsiento; //Numero de asiento
    private String fila; //numero de fila
    private String numeroSerie; //Numero de serie el cual es generado cada que el boleto se cambia de usuario
    private float precioOriginal; //Precio del boleto desde la boletera
    private String estado; //Disponibilidad del boleto
    private Integer codigoUsuario; //codigo de usuario al cual le pertenece el boleto
    private Integer codigoEvento; //codigo al cual Evento le pertenece el boleto

    
    /**
     * Constructor el cual recibe todos los atributos menos los que dependen de que el boleto lo tenga un usuario.
     * @param numeroControl Numero interno del control.
     * @param numeroAsiento El numero de asiento que ira acompañado del atributo fila.
     * @param fila Numero de fila donde se ubica el asiento.
     * @param precioOriginal Precio al cual se adquirió el boleto desde la boletera directa.
     * @param estado    Disponible, Vendido o Pendiente de pago.
     * @param codigoEvento El evento al cual pertenece el boleto. 
     */
    public Boleto(String numeroControl, Integer numeroAsiento, String fila, float precioOriginal, String estado, Integer codigoEvento) {
        this.numeroControl = numeroControl;
        this.numeroAsiento = numeroAsiento;
        this.fila = fila;
        this.precioOriginal = precioOriginal;
        this.estado = estado;
        this.codigoEvento = codigoEvento;
    }
    
    
    /**
     * Constructor que recibe todos los atributos excepto el numeroControl, el codigoUsuario y el numeroSerie
     * @param numeroAsiento  El numero de asiento que ira acompañado del atributo fila.
     * @param fila Numero de fila donde se ubica el asiento.
     * @param precioOriginal  Precio al cual se adquirió el boleto desde la boletera directa.
     * @param estado Disponible, Vendido o Pendiente de pago.
     * @param codigoEvento El evento al cual pertenece el boleto. 
     */
    
    public Boleto(Integer numeroAsiento, String fila, float precioOriginal, String estado, Integer codigoEvento) {
        this.numeroAsiento = numeroAsiento;
        this.fila = fila;
        this.precioOriginal = precioOriginal;
        this.estado = estado;
        this.codigoEvento = codigoEvento;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public Integer getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(Integer numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public float getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(float precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Integer getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(Integer codigoEvento) {
        this.codigoEvento = codigoEvento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.numeroControl);
        hash = 17 * hash + Objects.hashCode(this.numeroAsiento);
        hash = 17 * hash + Objects.hashCode(this.fila);
        hash = 17 * hash + Objects.hashCode(this.numeroSerie);
        hash = 17 * hash + Float.floatToIntBits(this.precioOriginal);
        hash = 17 * hash + Objects.hashCode(this.estado);
        hash = 17 * hash + Objects.hashCode(this.codigoUsuario);
        hash = 17 * hash + Objects.hashCode(this.codigoEvento);
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
        final Boleto other = (Boleto) obj;
        if (Float.floatToIntBits(this.precioOriginal) != Float.floatToIntBits(other.precioOriginal)) {
            return false;
        }
        if (!Objects.equals(this.numeroControl, other.numeroControl)) {
            return false;
        }
        if (!Objects.equals(this.fila, other.fila)) {
            return false;
        }
        if (!Objects.equals(this.numeroSerie, other.numeroSerie)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.numeroAsiento, other.numeroAsiento)) {
            return false;
        }
        if (!Objects.equals(this.codigoUsuario, other.codigoUsuario)) {
            return false;
        }
        return Objects.equals(this.codigoEvento, other.codigoEvento);
    }

    @Override
    public String toString() {
        return "Boleto{" + "numeroControl=" + numeroControl + ", numeroAsiento=" + numeroAsiento + ", fila=" + fila + ", numeroSerie=" + numeroSerie + ", precioOriginal=" + precioOriginal + ", estado=" + estado + ", codigoUsuario=" + codigoUsuario + ", codigoEvento=" + codigoEvento + '}';
    }
    
    
    
    
    
    
    
    
    
}
