package com.hoscanoa.developer.proyectodami.dao.alumnoHorario;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.AlumnoHorario;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteAlumnoHorarioDAO implements AlumnoHorarioDAO {

    private Context context;

    public SQLiteAlumnoHorarioDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<AlumnoHorario> listar() {
        return null;
    }

    @Override
    public AlumnoHorario buscar(int id) {
        return null;
    }

    @Override
    public int insertar(AlumnoHorario obj) {
        return 0;
    }

    @Override
    public int editar(AlumnoHorario obj) {
        return 0;
    }

    @Override
    public int eliminar(AlumnoHorario obj) {
        return 0;
    }
}
