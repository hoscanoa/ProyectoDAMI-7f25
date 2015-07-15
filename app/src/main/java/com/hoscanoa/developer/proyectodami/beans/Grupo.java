package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Grupo implements Serializable {

     private int grupoId;
     private String descripcion;

    @Override
    public String toString()
    {
        return descripcion;
    }

    public Grupo(){}

    public Grupo(int grupoId, String descripcion) {
        this.grupoId = grupoId;
        this.descripcion = descripcion;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


