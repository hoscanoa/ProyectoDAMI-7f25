package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Seccion implements Serializable {

     private int seccionId;
     private String descripcion;

    @Override
    public String toString() {
        return descripcion;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


