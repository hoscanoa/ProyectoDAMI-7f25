package com.hoscanoa.developer.proyectodami.dao.estado;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.Estado;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteEstadoDAO implements EstadoDAO {
    private Context context;

    public SQLiteEstadoDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Estado> listar() {
        return null;
    }

    @Override
    public Estado buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Estado obj) {
        return 0;
    }

    @Override
    public int editar(Estado obj) {
        return 0;
    }

    @Override
    public int eliminar(Estado obj) {
        return 0;
    }
}
