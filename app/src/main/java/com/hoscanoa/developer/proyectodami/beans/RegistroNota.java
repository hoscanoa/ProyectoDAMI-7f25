package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class RegistroNota implements Serializable {

     private int registroNotaId;
     private int matriculaId;
     private int cursoEvaluacionId;
     private int calificacionId;

    public RegistroNota(){}

    public RegistroNota( int matriculaId, int cursoEvaluacionId, int calificacionId) {
        this.matriculaId = matriculaId;
        this.cursoEvaluacionId = cursoEvaluacionId;
        this.calificacionId = calificacionId;
    }

    public int getRegistroNotaId() {
        return registroNotaId;
    }

    public void setRegistroNotaId(int registroNotaId) {
        this.registroNotaId = registroNotaId;
    }

    public int getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(int matriculaId) {
        this.matriculaId = matriculaId;
    }

    public int getCursoEvaluacionId() {
        return cursoEvaluacionId;
    }

    public void setCursoEvaluacionId(int cursoEvaluacionId) {
        this.cursoEvaluacionId = cursoEvaluacionId;
    }

    public int getCalificacionId() {
        return calificacionId;
    }

    public void setCalificacionId(int calificacionId) {
        this.calificacionId = calificacionId;
    }
}


