package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Dia implements Serializable {

     private int diaId;
     private String descripcion;

    public int getDiaId() {
        return this.diaId;
    }
    
    public void setDiaId(int diaId) {
        this.diaId = diaId;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}


