package itson.entidades;

import java.util.Date;
import java.util.Objects;

/**
 * Clase que representa un Boleto en el sistema.
 * Incluye información sobre el asiento, precio, estado, usuario y evento.
 */
public class Boleto {
    
    public enum Estado {
        Disponible, Vendido, PendienteDePago;
        
        public static Estado fromString(String estadoStr) {
            switch (estadoStr) {
                case "Disponible":
                    return Disponible;
                case "Vendido":
                    return Vendido;
                case "Pendiente de pago":
                    return PendienteDePago;
                default:
                    throw new IllegalArgumentException("Estado desconocido: " + estadoStr);
            }
        }
    }
    
    private String numeroControl;
    private Integer numeroAsiento;
    private String fila;
    private String numeroSerie;
    private float precioOriginal;
    private Estado estado; // Ahora es enum
    private Integer codigoUsuario;
    private Integer codigoEvento;
    private float precioActual;
    private Date fechaLimite;

    public Boleto(String numeroControl, Integer numeroAsiento, String fila, String numeroSerie, float precioOriginal, Estado estado, Integer codigoUsuario, Integer codigoEvento) {
        this.numeroControl = numeroControl;
        this.numeroAsiento = numeroAsiento;
        this.fila = fila;
        this.numeroSerie = numeroSerie;
        this.precioOriginal = precioOriginal;
        this.estado = estado;
        this.codigoUsuario = codigoUsuario;
        this.codigoEvento = codigoEvento;
    }

    public Boleto(String numeroControl, Estado estado, float precioActual, Date fechaLimite) {
        this.numeroControl = numeroControl;
        this.estado = estado;
        this.precioActual = precioActual;
        this.fechaLimite = fechaLimite;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public float getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(float precioActual) {
        this.precioActual = precioActual;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
    
    

    @Override
    public int hashCode() {
        return Objects.hash(numeroControl, numeroAsiento, fila, numeroSerie, precioOriginal, estado, codigoUsuario, codigoEvento, precioActual, fechaLimite);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Boleto other = (Boleto) obj;
        return Float.compare(other.precioOriginal, precioOriginal) == 0 &&
                Float.compare(other.precioActual, precioActual) == 0 &&
                Objects.equals(numeroControl, other.numeroControl) &&
                Objects.equals(numeroAsiento, other.numeroAsiento) &&
                Objects.equals(fila, other.fila) &&
                Objects.equals(numeroSerie, other.numeroSerie) &&
                estado == other.estado &&
                Objects.equals(codigoUsuario, other.codigoUsuario) &&
                Objects.equals(codigoEvento, other.codigoEvento) &&
                Objects.equals(fechaLimite, other.fechaLimite);
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "numeroControl='" + numeroControl + '\'' +
                ", numeroAsiento=" + numeroAsiento +
                ", fila='" + fila + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", precioOriginal=" + precioOriginal +
                ", estado=" + estado +
                ", codigoUsuario=" + codigoUsuario +
                ", codigoEvento=" + codigoEvento +
                ", precioActual=" + precioActual +
                ", fechaLimite=" + fechaLimite +
                '}';
    }
}
