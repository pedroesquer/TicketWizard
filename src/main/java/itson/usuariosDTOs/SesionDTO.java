package itson.usuariosDTOs;

import itson.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import observador.Observer;

public class SesionDTO {
    private static SesionDTO instancia;
    private Usuario usuarioActual;
    private List<Observer> observadores;

    private SesionDTO() {
        observadores = new ArrayList<>();
    }

    public static SesionDTO getInstancia() {
        if (instancia == null) {
            instancia = new SesionDTO();
        }
        return instancia;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        notificarObservadores();
    }

    // Métodos para Observer
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }

    private void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.actualizar();
        }
    }
}
