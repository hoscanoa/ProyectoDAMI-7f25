package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

/**
 * Created by Hernan on 19/05/2015.
 */
public class CargaDocente implements Serializable {
    private int cargaDocenteId;
    private int cursoId;
    private int profesorId;
    private int cicloId;
    private int seccionId;
    private int grupoId;

    public CargaDocente(){}

    public CargaDocente(int cargaDocenteId, int cursoId, int profesorId, int cicloId, int seccionId, int grupoId) {
        this.cargaDocenteId = cargaDocenteId;
        this.cursoId = cursoId;
        this.profesorId = profesorId;
        this.cicloId = cicloId;
        this.seccionId = seccionId;
        this.grupoId = grupoId;
    }

    public int getCargaDocenteId() {
        return cargaDocenteId;
    }

    public void setCargaDocenteId(int cargaDocenteId) {
        this.cargaDocenteId = cargaDocenteId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
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

    public int getGrupoId() {
        return grupoId;
    }
    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }
}
