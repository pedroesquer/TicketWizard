/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.usuariosDTOs;

/**
 *
 * @author juanpheras
 */
public class ActualizarUsuarioDTO {
        private String calle; 
    private String colonia; 
    private String numero; 
    private String ciudad;
    private Integer codigoUsuario;

    public ActualizarUsuarioDTO(String calle, String colonia, String numero, String ciudad, Integer codigoUsuario) {
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoUsuario = codigoUsuario;
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

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

}
