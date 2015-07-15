package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class RegistroNota implements Serializable {

     private int registroNotaId;
     private int alumnoId;
     private int cursoEvaluacionId;
     private int calificacionId;

    public int getRegistroNotaId() {
        return registroNotaId;
    }

    public void setRegistroNotaId(int registroNotaId) {
        this.registroNotaId = registroNotaId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
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


