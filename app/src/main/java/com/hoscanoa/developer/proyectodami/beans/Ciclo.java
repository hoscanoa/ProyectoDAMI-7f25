package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

/**
 * Created by Hernan on 19/05/2015.
 */
public class Ciclo implements Serializable {
    private int cicloId;
    private String descripcion;

    @Override
    public String toString()
    {
        return descripcion;
    }

    public Ciclo(){}

    public Ciclo(int cicloId, String descripcion) {
        this.cicloId = cicloId;
        this.descripcion = descripcion;
    }

    public int getCicloId() {
        return cicloId;
    }

    public void setCicloId(int cicloId) {
        this.cicloId = cicloId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
