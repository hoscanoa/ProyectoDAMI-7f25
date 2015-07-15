package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Aula implements Serializable {

     private int aulaId;
     private int tipoAulaId;

    public int getAulaId() {
        return aulaId;
    }

    public void setAulaId(int aulaId) {
        this.aulaId = aulaId;
    }

    public int getTipoAulaId() {
        return tipoAulaId;
    }

    public void setTipoAulaId(int tipoAulaId) {
        this.tipoAulaId = tipoAulaId;
    }
}


