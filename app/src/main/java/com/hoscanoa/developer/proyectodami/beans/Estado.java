package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Estado implements Serializable {

     private int estadoId;
     private String descricpion;

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getDescricpion() {
        return descricpion;
    }

    public void setDescricpion(String descricpion) {
        this.descricpion = descricpion;
    }
}


