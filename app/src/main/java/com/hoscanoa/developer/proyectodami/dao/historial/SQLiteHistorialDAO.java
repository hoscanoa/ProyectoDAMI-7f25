package com.hoscanoa.developer.proyectodami.dao.historial;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Historial;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

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
        ArrayList<Historial> historials = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM HISTORIAL",null);
            Historial obj;
            while (q.moveToNext())
            {
                obj = new Historial();
                obj.setHistorialId(q.getInt(0));
                obj.setProfesorId(q.getInt(1));
                historials.add(obj);
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return historials;
    }

    @Override
    public Historial buscar(int id) {

        return null;
    }


    public Historial buscarProfesor(int profesorId) {
        Historial obj=null;
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM HISTORIAL WHERE profesorId="+profesorId,null);

            if (q.moveToNext())
            {
                obj = new Historial();
                obj.setHistorialId(q.getInt(0));
                obj.setProfesorId(q.getInt(1));
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
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
