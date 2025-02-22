package itson.usuariosDTOs;

import java.time.LocalDateTime;

/**
 *
 * @author pedro
 */
public class NuevoBoletoEventoDTO {

    private String ID;
    private String asiento; //numero de fila
    private float precioOriginal; //Precio del boleto desde la boletera
    private LocalDateTime fechaHora; //Fecha hora del evento
    private String nombreEv; //Nombre evento
    private String lugar; //Lugar
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public NuevoBoletoEventoDTO(String ID, String asiento, float precioOriginal, LocalDateTime fechaHora, String nombreEv, String lugar, String tipo) {
        this.ID = ID; 
        this.asiento = asiento;
        this.precioOriginal = precioOriginal;
        this.fechaHora = fechaHora;
        this.nombreEv = nombreEv;
        this.lugar = lugar;
        this.tipo = tipo;
    }

    public NuevoBoletoEventoDTO(String ID, String asiento, float precioOriginal, LocalDateTime fechaHora, String nombreEv, String lugar) {
        this.ID = ID;
        this.asiento = asiento;
        this.precioOriginal = precioOriginal;
        this.fechaHora = fechaHora;
        this.nombreEv = nombreEv;
        this.lugar = lugar;
    }
    
    public String getID() {
        return ID;
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
    
    
    
    
}
