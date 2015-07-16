package com.hoscanoa.developer.proyectodami.dao.matricula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
import com.hoscanoa.developer.proyectodami.beans.Estado;
import com.hoscanoa.developer.proyectodami.beans.Matricula;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 19/05/2015.
 */
public class SQLiteMatriculaDAO implements MatriculaDAO {
    private Context context;

    public SQLiteMatriculaDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Matricula> listar() {
        return null;
    }

    @Override
    public Matricula buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Matricula obj) {
        return 0;
    }

    @Override
    public int editar(Matricula obj) {
        return 0;
    }

    @Override
    public int eliminar(Matricula obj) {
        return 0;
    }

    @Override
    public void insertar(ArrayList<Matricula> matriculas) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Matricula matricula: matriculas){
                ContentValues values= new ContentValues();
                values.put("matriculaId",matricula.getMatriculaId());
                values.put("alumnoId",matricula.getAlumnoId());
                values.put("cicloId",matricula.getCicloId());
                values.put("cursoId",matricula.getCursoId());
                values.put("estadoId",matricula.getEstadoId());
                values.put("grupoId",matricula.getGrupoId());
                values.put("seccionId", matricula.getSeccionId());
                try {
                    database.insert("MATRICULA",null,values);
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Matricula buscar(int alumnoId, int cursoId, int cicloId, int seccionId, int grupoId) {
        Matricula obj=null;
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT matriculaId FROM MATRICULA " +
                    "where alumnoId=? AND cursoId=? AND cicloId=? AND seccionId=? AND " +
                    "grupoId=?",new String[]{String.valueOf(alumnoId), String.valueOf(cursoId), String.valueOf(cicloId), String.valueOf(seccionId), String.valueOf(grupoId)});

            if (q.moveToNext())
            {
                obj = new Matricula();
                obj.setMatriculaId(q.getInt(0));
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
