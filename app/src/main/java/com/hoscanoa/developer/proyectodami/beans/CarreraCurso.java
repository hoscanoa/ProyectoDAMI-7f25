package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class CarreraCurso implements Serializable {

     private int carreraCursoId;
     private int carreraId;
     private int cursoId;
     private int creditos;

    public CarreraCurso(){}

    public CarreraCurso(int carreraCursoId, int carreraId, int cursoId, int creditos) {
        this.carreraCursoId = carreraCursoId;
        this.carreraId = carreraId;
        this.cursoId = cursoId;
        this.creditos = creditos;
    }

    public int getCarreraCursoId() {
        return carreraCursoId;
    }

    public void setCarreraCursoId(int carreraCursoId) {
        this.carreraCursoId = carreraCursoId;
    }

    public int getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(int carreraId) {
        this.carreraId = carreraId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
}


