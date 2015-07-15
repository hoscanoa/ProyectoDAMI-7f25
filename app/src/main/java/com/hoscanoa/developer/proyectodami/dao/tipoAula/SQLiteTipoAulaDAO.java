package com.hoscanoa.developer.proyectodami.dao.tipoAula;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.TipoAula;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteTipoAulaDAO implements TipoAulaDAO {
    private Context context;

    public SQLiteTipoAulaDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<TipoAula> listar() {
        return null;
    }

    @Override
    public TipoAula buscar(int id) {
        return null;
    }

    @Override
    public int insertar(TipoAula obj) {
        return 0;
    }

    @Override
    public int editar(TipoAula obj) {
        return 0;
    }

    @Override
    public int eliminar(TipoAula obj) {
        return 0;
    }
}
