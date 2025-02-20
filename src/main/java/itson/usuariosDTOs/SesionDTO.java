/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.usuariosDTOs;

import itson.entidades.Usuario;

/**
 *
 * @author rauln
 */
public class SesionDTO {
    private static SesionDTO instancia;
    private Usuario usuarioActual;
    
    // Constructor privado para Singleton
    private SesionDTO() {
        this.usuarioActual = null;
    }
    
    /**
     * Obtiene la instancia única de SesionDTO
     * @return instancia de SesionDTO
     */
    public static SesionDTO getInstancia() {
        if (instancia == null) {
            instancia = new SesionDTO();
        }
        return instancia;
    }
    
    /**
     * Inicia una nueva sesión con el usuario especificado
     * @param usuario El usuario que inició sesión
     */
    public void iniciarSesion(Usuario usuario) {
        this.usuarioActual = usuario;
    }
    
    /**
     * Cierra la sesión actual
     */
    public void cerrarSesion() {
        this.usuarioActual = null;
    }
    
    /**
     * Obtiene el usuario que tiene la sesión activa
     * @return Usuario actual o null si no hay sesión activa
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    /**
     * Verifica si hay una sesión activa
     * @return true si hay un usuario con sesión activa, false en caso contrario
     */
    public boolean haySesionActiva() {
        return usuarioActual != null;
    }
}
