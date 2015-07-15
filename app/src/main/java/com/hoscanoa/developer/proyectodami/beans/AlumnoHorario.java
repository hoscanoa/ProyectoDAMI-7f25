package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class AlumnoHorario implements Serializable {

     private int alumnoHorarioId;
     private int alumnoId;
     private int horarioId;
     private int estadoId;

    public int getAlumnoHorarioId() {
        return alumnoHorarioId;
    }

    public void setAlumnoHorarioId(int alumnoHorarioId) {
        this.alumnoHorarioId = alumnoHorarioId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }
}


