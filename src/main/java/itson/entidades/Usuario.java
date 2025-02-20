package itson.entidades;

import java.util.Objects;

/**
 *
 * @author juanpheras
 */
public class Usuario {

    private Integer codigoUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correoElectronico;
    private String contraseñaHash;
    private String fechaNacimiento;
    private float saldo;
    private String ciudad;
    private String calle;
    private String colonia;
    private String numero;

    public Usuario(int codigoUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String fechaNacimiento, String ciudad, String calle, String colonia, float saldo) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
        this.ciudad = ciudad;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public Usuario(Integer codigoUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String contraseñaHash, String fechaNacimiento, float saldo, String ciudad, String calle, String colonia, String numero) {
        this.codigoUsuario = codigoUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.contraseñaHash = contraseñaHash;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
        this.ciudad = ciudad;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    
    
    
    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String contraseñaHash, String fechaNacimiento, String ciudad, String calle) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = correoElectronico;
        this.contraseñaHash = contraseñaHash;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseñaHash() {
        return contraseñaHash;
    }

    public void setContraseñaHash(String contraseñaHash) {
        this.contraseñaHash = contraseñaHash;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.codigoUsuario);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.apellidoPaterno);
        hash = 97 * hash + Objects.hashCode(this.apellidoMaterno);
        hash = 97 * hash + Objects.hashCode(this.correoElectronico);
        hash = 97 * hash + Objects.hashCode(this.contraseñaHash);
        hash = 97 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 97 * hash + Float.floatToIntBits(this.saldo);
        hash = 97 * hash + Objects.hashCode(this.ciudad);
        hash = 97 * hash + Objects.hashCode(this.calle);
        hash = 97 * hash + Objects.hashCode(this.colonia);
        hash = 97 * hash + Objects.hashCode(this.numero);
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
        final Usuario other = (Usuario) obj;
        if (Float.floatToIntBits(this.saldo) != Float.floatToIntBits(other.saldo)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidoPaterno, other.apellidoPaterno)) {
            return false;
        }
        if (!Objects.equals(this.apellidoMaterno, other.apellidoMaterno)) {
            return false;
        }
        if (!Objects.equals(this.correoElectronico, other.correoElectronico)) {
            return false;
        }
        if (!Objects.equals(this.contraseñaHash, other.contraseñaHash)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.colonia, other.colonia)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return Objects.equals(this.codigoUsuario, other.codigoUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", correoElectronico=" + correoElectronico + ", contrase\u00f1aHash=" + contraseñaHash + ", fechaNacimiento=" + fechaNacimiento + ", ciudad=" + ciudad + ", calle=" + calle + ", colonia=" + colonia + ", numero=" + numero + '}';
    }

}
