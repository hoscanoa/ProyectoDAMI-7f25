package com.hoscanoa.developer.proyectodami.dao.cursoEvaluacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.CursoEvaluacion;
import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteCursoEvaluacionDAO implements CursoEvaluacionDAO {
    private Context context;

    public SQLiteCursoEvaluacionDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<CursoEvaluacion> listar() {
        return null;
    }

    @Override
    public CursoEvaluacion buscar(int id) {
        return null;
    }

    @Override
    public int insertar(CursoEvaluacion obj) {
        return 0;
    }

    @Override
    public int editar(CursoEvaluacion obj) {
        return 0;
    }

    @Override
    public int eliminar(CursoEvaluacion obj) {
        return 0;
    }

    @Override
    public ArrayList<CursoEvaluacion> listarCursoEvaluacion(int cursoId, int evaluacionId) {
        ArrayList<CursoEvaluacion> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            String sql="SELECT DISTINCT CE.* FROM\n" +
                    "CURSOS_EVALUACIONES CE INNER JOIN CURSOS C\n" +
                    "ON CE.cursoId=C.cursoId \n" +
                    "WHERE C.cursoId="+cursoId+" AND CE.evaluacionId="+evaluacionId+";";
            Cursor q = database.rawQuery(sql,null);
            CursoEvaluacion obj;
            while (q.moveToNext())
            {
                obj = new CursoEvaluacion();
                obj.setCursoEvaluacionId(q.getInt(0));
                obj.setCursoId(q.getInt(1));
                obj.setEvaluacionId(q.getInt(2));
                obj.setNumero(q.getInt(3));
                obj.setPorcentaje(q.getInt(4));
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
    public void insertar(ArrayList<CursoEvaluacion> cursoEvaluaciones) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(CursoEvaluacion ce : cursoEvaluaciones){
                ContentValues values= new ContentValues();
                values.put("cursoEvaluacionId",ce.getCursoEvaluacionId());
                values.put("cursoId",ce.getCursoId());
                values.put("evaluacionId",ce.getEvaluacionId());
                values.put("numero", ce.getNumero());
                values.put("porcentaje", ce.getPorcentaje());
                database.insert("CURSOS_EVALUACIONES",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
