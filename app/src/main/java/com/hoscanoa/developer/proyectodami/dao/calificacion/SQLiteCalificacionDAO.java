package com.hoscanoa.developer.proyectodami.dao.calificacion;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.Calificacion;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteCalificacionDAO implements CalificacionDAO {

    private Context context;

    public SQLiteCalificacionDAO(Context context)
    {
        this.context=context;
    }


    @Override
    public ArrayList<Calificacion> listar() {

        return null;
    }

    @Override
    public Calificacion buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Calificacion obj) {
        return 0;
    }

    @Override
    public int editar(Calificacion obj) {
        return 0;
    }

    @Override
    public int eliminar(Calificacion obj) {
        return 0;
    }
}
