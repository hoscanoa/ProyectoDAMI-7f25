package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class CursoEvaluacion implements Serializable {
     private int cursoEvaluacionId;
     private int cursoId;
     private int evaluacionId;
     private int numero;
     private int porcentaje;

    @Override
    public String toString()
    {
        return ""+numero;
    }

    public int getCursoEvaluacionId() {
        return cursoEvaluacionId;
    }

    public void setCursoEvaluacionId(int cursoEvaluacionId) {
        this.cursoEvaluacionId = cursoEvaluacionId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(int evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}


