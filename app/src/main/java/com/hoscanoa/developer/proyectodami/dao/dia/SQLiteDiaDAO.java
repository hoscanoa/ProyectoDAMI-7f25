package com.hoscanoa.developer.proyectodami.dao.dia;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.Dia;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteDiaDAO implements DiaDAO {
    private Context context;

    public SQLiteDiaDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Dia> listar() {
        return null;
    }

    @Override
    public Dia buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Dia obj) {
        return 0;
    }

    @Override
    public int editar(Dia obj) {
        return 0;
    }

    @Override
    public int eliminar(Dia obj) {
        return 0;
    }
}
