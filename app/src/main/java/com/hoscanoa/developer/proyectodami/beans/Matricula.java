package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

/**
 * Created by Hernan on 19/05/2015.
 */
public class Matricula implements Serializable {
    private int matriculaId;
    private int alumnoId;
    private int cursoId;
    private int grupoId;
    private int cicloId;
    private int seccionId;
    private int estadoId;

    public int getMatriculaId() {
        return matriculaId;
    }
    public void setMatriculaId(int matriculaId) {
        this.matriculaId = matriculaId;
    }
    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getCicloId() {
        return cicloId;
    }

    public void setCicloId(int cicloId) {
        this.cicloId = cicloId;
    }

    public int getSeccionId() {
        return seccionId;
    }

    public void setSeccionId(int seccionId) {
        this.seccionId = seccionId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }
}
