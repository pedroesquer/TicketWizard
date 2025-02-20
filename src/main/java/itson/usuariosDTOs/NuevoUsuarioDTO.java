/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.usuariosDTOs;

import java.util.Date;

/**
 *
 * @author juanpheras
 */
public class NuevoUsuarioDTO {
    
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String correoElectronico;
    private String contraseniaHash;
    private String fechaNacimiento;
    private int codigoDireccionUsuario;

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

    public int getCodigoDireccionUsuario() {
        return codigoDireccionUsuario;
    }
    
    
    
}
