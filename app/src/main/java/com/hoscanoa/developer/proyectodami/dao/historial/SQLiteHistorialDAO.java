package com.hoscanoa.developer.proyectodami.dao.historial;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.Historial;

import java.util.ArrayList;

/**
 * Created by Hernan on 15/07/2015.
 */
public class SQLiteHistorialDAO implements HistorialDAO {

    private Context context;

    public SQLiteHistorialDAO(Context context) {
        this.context=context;
    }

    @Override
    public ArrayList<Historial> listar() {
        return null;
    }

    @Override
    public Historial buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Historial obj) {
        return 0;
    }

    @Override
    public int editar(Historial obj) {
        return 0;
    }

    @Override
    public int eliminar(Historial obj) {
        return 0;
    }
}
