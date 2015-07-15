package com.hoscanoa.developer.proyectodami.dao.curso;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteCursoDAO implements CursoDAO {

    private Context context;

    public SQLiteCursoDAO(Context context)
    {
        this.context=context;
    }


    @Override
    public ArrayList<Curso> listar() {
        ArrayList<Curso> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM CURSOS ORDER BY codigo asc",null);
            Curso obj;
            while (q.moveToNext())
            {
                obj = new Curso();
                obj.setCursoId(q.getInt(0));
                obj.setCodigo(q.getString(1));
                obj.setDescripcion(q.getString(2));
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
    public ArrayList<Curso> listarProfesorModalidadCiclo(int profesorId, int modalidadEstudioId, int cicloId) {
        ArrayList<Curso> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            String sql="SELECT DISTINCT CU.* FROM\n" +
                    "CARRERAS CA INNER JOIN CARRERAS_CURSOS CC\n" +
                    "ON CA.carreraId=CC.carreraId INNER JOIN CURSOS CU\n" +
                    "ON CC.cursoId=CU.cursoId INNER JOIN CARGA_DOCENTE CD\n" +
                    "ON CU.cursoId=CD.cursoId\n" +
                    "WHERE CD.profesorId="+profesorId+" AND CA.modalidadEstudioId="+modalidadEstudioId+" AND CD.cicloId="+cicloId+";";
            Cursor q = database.rawQuery(sql,null);
            Curso obj;
            while (q.moveToNext())
            {
                obj = new Curso();
                obj.setCursoId(q.getInt(0));
                obj.setCodigo(q.getString(1));
                obj.setDescripcion(q.getString(2));
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
    public Curso buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Curso obj) {
        return 0;
    }

    @Override
    public int editar(Curso obj) {
        return 0;
    }

    @Override
    public int eliminar(Curso obj) {
        return 0;
    }
}
