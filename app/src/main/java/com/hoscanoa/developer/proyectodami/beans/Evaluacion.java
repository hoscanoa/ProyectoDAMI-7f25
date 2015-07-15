package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Evaluacion implements Serializable {
     private int evaluacionId;
     private String descripcion;

    @Override
    public String toString() {
        return descripcion;
    }

    public Evaluacion(){}

    public Evaluacion(int evaluacionId, String descripcion) {
        this.evaluacionId = evaluacionId;
        this.descripcion = descripcion;
    }

    public int getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(int evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


