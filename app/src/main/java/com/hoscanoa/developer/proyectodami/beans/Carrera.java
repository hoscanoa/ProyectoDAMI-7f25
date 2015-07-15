package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Carrera implements Serializable {
     private int carreraId;
     private String descripcion;

    public int getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(int carreraId) {
        this.carreraId = carreraId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


