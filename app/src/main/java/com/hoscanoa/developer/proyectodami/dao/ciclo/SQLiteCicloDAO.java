package com.hoscanoa.developer.proyectodami.dao.ciclo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 19/05/2015.
 */
public class SQLiteCicloDAO implements CicloDAO {

    private Context context;

    public SQLiteCicloDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Ciclo> listar() {
        ArrayList<Ciclo> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT DISTINCT * FROM CICLOS ORDER BY descripcion ASC",null);
            Ciclo obj;
            while (q.moveToNext())
            {
                obj = new Ciclo();
                obj.setCicloId(q.getInt(0));
                obj.setDescripcion(q.getString(1));
                lista.add(obj);
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public Ciclo buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Ciclo obj) {
        return 0;
    }

    @Override
    public int editar(Ciclo obj) {
        return 0;
    }

    @Override
    public int eliminar(Ciclo obj) {
        return 0;
    }

    @Override
    public void insertarCiclos(ArrayList<Ciclo> ciclos) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Ciclo ciclo:ciclos){
                ContentValues values= new ContentValues();
                values.put("cicloId",ciclo.getCicloId());
                values.put("descripcion",ciclo.getDescripcion());
                database.insert("CICLOS",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
