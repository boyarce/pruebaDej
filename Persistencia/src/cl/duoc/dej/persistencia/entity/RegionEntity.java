/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.dej.persistencia.entity;

import java.io.Serializable;

/**
 *
 * @author CETECOM
 */
public class RegionEntity implements Serializable {
    
    private int codigo;
    private String nombre;
    private String descripcion;

    public RegionEntity(int codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }    
    
    public RegionEntity() {
        this.codigo = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
        
}
