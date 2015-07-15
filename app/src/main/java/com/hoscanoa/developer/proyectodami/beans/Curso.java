package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Curso implements Serializable {
     private int cursoId;
    private String codigo;
     private String descripcion;

    @Override
    public String toString() {
        return codigo+" - "+descripcion.toUpperCase();
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}


