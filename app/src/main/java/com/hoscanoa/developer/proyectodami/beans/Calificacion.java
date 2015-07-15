package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Calificacion implements Serializable {

     private int calificacionId;
     private String descripcion;
     private int nota;

    public Calificacion(){}

    public Calificacion(int calificacionId, String descripcion, int nota) {
        this.calificacionId = calificacionId;
        this.descripcion = descripcion;
        this.nota = nota;
    }

    public int getCalificacionId() {
        return calificacionId;
    }

    public void setCalificacionId(int calificacionId) {
        this.calificacionId = calificacionId;
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


