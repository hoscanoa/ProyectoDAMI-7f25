package com.hoscanoa.developer.proyectodami.dao.horario;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.beans.Horario;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteHorarioDAO implements HorarioDAO {
    private Context context;

    public SQLiteHorarioDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Horario> listar() {
        return null;
    }

    @Override
    public Horario buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Horario obj) {
        return 0;
    }

    @Override
    public int editar(Horario obj) {
        return 0;
    }

    @Override
    public int eliminar(Horario obj) {
        return 0;
    }
}
