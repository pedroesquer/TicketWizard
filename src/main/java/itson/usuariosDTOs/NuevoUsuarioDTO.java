/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.usuariosDTOs;

import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;
import java.util.Date;

/**
 *
 * @author Juan Pablo Heras
 */
public class NuevoUsuarioDTO {
    
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String correoElectronico;
    private String contraseniaHash;
    private String fechaNacimiento;
    private String ciudad;
    private String calle;
    private String colonia;
    private String numero;

    public NuevoUsuarioDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String contraseniaHash, 
            String fechaNacimiento, String ciudad, String calle, String colonia, String numero) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.contraseniaHash = contraseniaHash;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }
    
    
    /**
     * Método que llama a la entidad usuariosDAO para poder registrar al usuario
     * 
     * @param nuevoUsuarioDTO un objeto de esta clase el cual tiene sus atributos para mandarselos a la DAO y comunicarse con la BDD.
     * */
    
    public void registrarUsuario(NuevoUsuarioDTO nuevoUsuarioDTO){
        ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
        UsuariosDAO usuariosDAO = new UsuariosDAO(manejadorConexiones);
        usuariosDAO.registrarUsuario(nuevoUsuarioDTO);
    }

    
    
    

    public String getNombre() {
        return nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContraseniaHash() {
        return contraseniaHash;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getNumero() {
        return numero;
    }

    
    
    
    
}
