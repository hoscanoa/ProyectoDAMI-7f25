package com.hoscanoa.developer.proyectodami.dao.estado;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.beans.Estado;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

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

    @Override
    public void insertar(ArrayList<Estado> estados) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Estado estado: estados){
                ContentValues values= new ContentValues();
                values.put("estadoId",estado.getEstadoId());
                values.put("descripcion",estado.getDescripcion());
                database.insert("ESTADOS",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
