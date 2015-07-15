package com.hoscanoa.developer.proyectodami.dao.calificacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Calificacion;
import com.hoscanoa.developer.proyectodami.beans.Estado;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

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

    @Override
    public void insertar(ArrayList<Calificacion> calificaciones) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Calificacion calificacion:calificaciones){
                ContentValues values= new ContentValues();
                values.put("calificacionId",calificacion.getCalificacionId());
                values.put("descripcion",calificacion.getDescripcion());
                values.put("nota",calificacion.getNota());
                database.insert("CALIFICACIONES",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
