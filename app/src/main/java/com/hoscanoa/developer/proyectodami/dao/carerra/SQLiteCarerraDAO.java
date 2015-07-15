package com.hoscanoa.developer.proyectodami.dao.carerra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Carrera;
import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

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

    @Override
    public void insertar(ArrayList<Carrera> carreras) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Carrera c : carreras){
                ContentValues values= new ContentValues();
                values.put("carreraId",c.getCarreraId());
                values.put("descripcion",c.getDescripcion());
                values.put("modalidadEstudioId",c.getModalidadEstudioId());
                database.insert("CARRERAS",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
