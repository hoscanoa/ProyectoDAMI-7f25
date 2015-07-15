package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Estado implements Serializable {

     private int estadoId;
     private String descripcion;

    public Estado(){

    }
    public Estado(int estadoId, String descripcion) {
        this.estadoId = estadoId;
        this.descripcion = descripcion;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


