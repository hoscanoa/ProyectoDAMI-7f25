package com.hoscanoa.developer.proyectodami.beans;

import java.io.Serializable;

public class Profesor implements Serializable {

     private int profesorId;
     private String nombres;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private String email;
     private String username;
     private String password;

    @Override
    public String toString() {
        return apellidoPaterno+" "+apellidoMaterno+", "+nombres;
    }

    public Profesor(){}

    public Profesor(int profesorId, String nombres, String apellidoPaterno, String apellidoMaterno, String email, String username, String password) {
        this.profesorId = profesorId;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Profesor(int profesorId, String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.profesorId = profesorId;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


