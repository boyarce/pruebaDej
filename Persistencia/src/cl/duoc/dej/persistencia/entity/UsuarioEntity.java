/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.persistencia.entity;

/**
 *
 * @author CETECOM
 */
public class UsuarioEntity {
    
    private long codigo;
    private String nombreUsuario;
    private String contrasenia;

    public UsuarioEntity(long codigo, String nombreUsuario, String contrasenia) {
        this.codigo = codigo;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }
    
    public UsuarioEntity() {
        this.codigo = 0;
        this.nombreUsuario = "";
        this.contrasenia = "";
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
            
}
