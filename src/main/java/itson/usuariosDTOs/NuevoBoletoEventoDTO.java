package itson.usuariosDTOs;

import java.time.LocalDateTime;

/**
 *
 * @author pedro
 */
public class NuevoBoletoEventoDTO {

    private String numeroControl;
    private String asiento; //numero de fila
    private float precioOriginal; //Precio del boleto desde la boletera
    private LocalDateTime fechaHora; //Fecha hora del evento
    private String nombreEv; //Nombre evento
    private String lugar; //Lugar
    private String fechaLimiteEvento;
    private String tipoCompra;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public NuevoBoletoEventoDTO(String numeroControl, String asiento, float precioOriginal, LocalDateTime fechaHora, String nombreEv, String lugar, String tipoCompra, String estado) {
        this.numeroControl = numeroControl;
        this.asiento = asiento;
        this.precioOriginal = precioOriginal;
        this.fechaHora = fechaHora;
        this.nombreEv = nombreEv;
        this.lugar = lugar;
        this.tipoCompra = tipoCompra;
        this.estado = estado;
    }
            

    
    public NuevoBoletoEventoDTO(String numeroControl, String asiento, float precioOriginal, LocalDateTime fechaHora, String nombreEv, String lugar, String tipoCompra) {
        this.numeroControl = numeroControl; 
        this.asiento = asiento;
        this.precioOriginal = precioOriginal;
        this.fechaHora = fechaHora;
        this.nombreEv = nombreEv;
        this.lugar = lugar;
        this.tipoCompra = tipoCompra;
    }

    public NuevoBoletoEventoDTO(String numeroControl, String asiento, float precioOriginal, LocalDateTime fechaHora, String nombreEv, String lugar) {
        this.numeroControl = numeroControl;
        this.asiento = asiento;
        this.precioOriginal = precioOriginal;
        this.fechaHora = fechaHora;
        this.nombreEv = nombreEv;
        this.lugar = lugar;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public String getAsiento() {
        return asiento;
    }

    public float getPrecioOriginal() {
        return precioOriginal;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getNombreEv() {
        return nombreEv;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFechaLimiteEvento() {
        return fechaLimiteEvento;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }
    

    
    
    
    
}
