package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Calificacion implements Serializable {

     private int notaId;
     private String descripcion;
     private int nota;

    public int getNotaId() {
        return notaId;
    }

    public void setNotaId(int notaId) {
        this.notaId = notaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}


