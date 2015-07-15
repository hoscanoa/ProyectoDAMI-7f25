package com.hoscanoa.developer.proyectodami.dao.registroNota;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.RegistroNota;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteRegistroNotaDAO implements RegistroNotaDAO {
    private Context context;

    public SQLiteRegistroNotaDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<RegistroNota> listar() {
        return null;
    }

    @Override
    public RegistroNota buscar(int id) {
        return null;
    }

    @Override
    public int insertar(RegistroNota obj) {
        return 0;
    }

    @Override
    public int editar(RegistroNota obj) {
        return 0;
    }

    @Override
    public int eliminar(RegistroNota obj) {
        return 0;
    }
}
