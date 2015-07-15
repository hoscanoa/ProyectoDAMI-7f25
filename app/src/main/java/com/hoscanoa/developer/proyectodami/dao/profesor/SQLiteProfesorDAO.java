package com.hoscanoa.developer.proyectodami.dao.profesor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteProfesorDAO implements ProfesorDAO {
    private Context context;

    public SQLiteProfesorDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Profesor> listar() {
        ArrayList<Profesor> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM PROFESORES ORDER BY nombres asc",null);
            Profesor obj;
            while (q.moveToNext())
            {
                obj = new Profesor();
                obj.setProfesorId(q.getInt(0));
                obj.setNombres(q.getString(1));
                obj.setApellidoPaterno(q.getString(2));
                obj.setApellidoMaterno(q.getString(3));
                obj.setEmail(q.getString(4));
                obj.setUsername(q.getString(5));
                lista.add(obj);
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }


    public ArrayList<Profesor> listarXciclo(int cicloId) {
        ArrayList<Profesor> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("select p.profesorId,p.nombres,p.apellidoPaterno,p.apellidoMaterno,p.email,p.username from PROFESORES p inner join CARGA_DOCENTE c on p.profesorId=c.profesorId where c.cicloId="+cicloId+" ORDER BY p.profesorId asc;",null);
            Profesor obj;
            while (q.moveToNext())
            {
                obj = new Profesor();
                obj.setProfesorId(q.getInt(0));
                obj.setNombres(q.getString(1));
                obj.setApellidoPaterno(q.getString(2));
                obj.setApellidoMaterno(q.getString(3));
                obj.setEmail(q.getString(4));
                obj.setUsername(q.getString(5));
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
    public void insertarProfesorHistorial(Profesor profesor) {

    }

    @Override
    public Profesor buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Profesor obj) {
        return 0;
    }

    @Override
    public int editar(Profesor obj) {
        return 0;
    }

    @Override
    public int eliminar(Profesor obj) {
        return 0;
    }

    @Override
    public Profesor logear(String username, String password) {
       Profesor obj=null;
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM PROFESORES WHERE username=? AND password=?",new String[]{username,password});

            if (q.moveToNext())
            {
                obj = new Profesor();
                obj.setProfesorId(q.getInt(0));
                obj.setNombres(q.getString(1));
                obj.setApellidoPaterno(q.getString(2));
                obj.setApellidoMaterno(q.getString(3));
                obj.setEmail(q.getString(4));
                obj.setUsername(q.getString(5));
                obj.setPassword(q.getString(6));
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
