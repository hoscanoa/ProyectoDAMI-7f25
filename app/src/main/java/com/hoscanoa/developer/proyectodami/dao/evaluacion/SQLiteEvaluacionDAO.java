package com.hoscanoa.developer.proyectodami.dao.evaluacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.beans.Evaluacion;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteEvaluacionDAO implements EvaluacionDAO {
    private Context context;

    public SQLiteEvaluacionDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Evaluacion> listar() {
        ArrayList<Evaluacion> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM EVALUACIONES ORDER BY evaluacionId asc",null);
            Evaluacion obj;
            while (q.moveToNext())
            {
                obj = new Evaluacion();
                obj.setEvaluacionId(q.getInt(0));
                obj.setDescripcion(q.getString(1));
                lista.add(obj);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Evaluacion> listarXCurso(int idCurso) {
        ArrayList<Evaluacion> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT e.evaluacionId,e.descripcion FROM EVALUACIONES e inner join CURSOS_EVALUACIONES c on e.evaluacionId=c.evaluacionId where c.cursoId="+idCurso+" ORDER BY e.evaluacionId asc",null);
            Evaluacion obj;
            while (q.moveToNext())
            {
                obj = new Evaluacion();
                obj.setEvaluacionId(q.getInt(0));
                obj.setDescripcion(q.getString(1));
                lista.add(obj);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public ArrayList<Evaluacion> listarCurso(int cursoId) {
        ArrayList<Evaluacion> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            String sql="SELECT DISTINCT E.* FROM\n" +
                    "EVALUACIONES E INNER JOIN CURSOS_EVALUACIONES CE\n"+
                    "ON E.evaluacionId=CE.evaluacionId INNER JOIN CURSOS C\n" +
                    "ON CE.cursoId=C.cursoId \n" +
                    "WHERE C.cursoId="+cursoId+";";
            Cursor q = database.rawQuery(sql,null);
            Evaluacion obj;
            while (q.moveToNext())
            {
                obj = new Evaluacion();
                obj.setEvaluacionId(q.getInt(0));
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
    public void insertar(ArrayList<Evaluacion> evaluaciones) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Evaluacion e:evaluaciones){
                ContentValues values= new ContentValues();
                values.put("evaluacionId",e.getEvaluacionId());
                values.put("descripcion",e.getDescripcion());
                database.insert("EVALUACIONES",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Evaluacion buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Evaluacion obj) {
        return 0;
    }

    @Override
    public int editar(Evaluacion obj) {
        return 0;
    }

    @Override
    public int eliminar(Evaluacion obj) {
        return 0;
    }
}
