package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class TipoAula  implements Serializable {

     private int tipoAulaId;
     private String descripcion;

    public int getTipoAulaId() {
        return tipoAulaId;
    }

    public void setTipoAulaId(int tipoAulaId) {
        this.tipoAulaId = tipoAulaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


