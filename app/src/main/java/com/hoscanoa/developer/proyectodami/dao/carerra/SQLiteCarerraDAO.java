package com.hoscanoa.developer.proyectodami.dao.carerra;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.Carrera;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteCarerraDAO implements CarerraDAO {

    private Context context;

    public SQLiteCarerraDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Carrera> listar() {
        return null;
    }

    @Override
    public Carrera buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Carrera obj) {
        return 0;
    }

    @Override
    public int editar(Carrera obj) {
        return 0;
    }

    @Override
    public int eliminar(Carrera obj) {
        return 0;
    }
}
