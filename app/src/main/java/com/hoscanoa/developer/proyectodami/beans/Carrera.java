package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Carrera implements Serializable {
     private int carreraId;
     private String descripcion;
     private int modalidadEstudioId;

    public Carrera(){}

    public Carrera(int carreraId, String descripcion, int modalidadEstudioId) {
        this.carreraId = carreraId;
        this.descripcion = descripcion;
        this.modalidadEstudioId = modalidadEstudioId;
    }

    public int getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(int carreraId) {
        this.carreraId = carreraId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getModalidadEstudioId() {
        return modalidadEstudioId;
    }

    public void setModalidadEstudioId(int modalidadEstudioId) {
        this.modalidadEstudioId = modalidadEstudioId;
    }
}


