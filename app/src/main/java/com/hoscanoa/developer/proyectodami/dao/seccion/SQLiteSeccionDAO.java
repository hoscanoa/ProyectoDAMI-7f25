package com.hoscanoa.developer.proyectodami.dao.seccion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteSeccionDAO implements SeccionDAO {
    private Context context;

    public SQLiteSeccionDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Seccion> listar() {
        ArrayList<Seccion> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT DISTINCT * FROM SECCIONES s inner join HORARIOS h on s.seccionId=h.seccionId where  ORDER BY descripcion asc ",null);
            Seccion obj;
            while (q.moveToNext())
            {
                obj = new Seccion();
                obj.setSeccionId(q.getInt(0));
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

    public ArrayList<Seccion> listarxCiclo_Profesor_Curso(int idCiclo,int idProfesor,int idCurso) {
        ArrayList<Seccion> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT DISTINCT s.* FROM SECCIONES s inner join HORARIOS h on s.seccionId=h.seccionId where h.cicloId="+idCiclo+" and h.profesorId="+idProfesor+" and h.cursoId="+idCurso+" ORDER BY s.seccionId asc ",null);
            Seccion obj;
            while (q.moveToNext())
            {
                obj = new Seccion();
                obj.setSeccionId(q.getInt(0));
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


    public ArrayList<Integer> listarGruposxCiclo_Profesor_Curso(int idCiclo,int idProfesor,int idCurso,int idSeccion) {
        ArrayList<Integer> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT DISTINCT grupo FROM  HORARIOS  where cicloId="+idCiclo+" and profesorId="+idProfesor+" and cursoId="+idCurso+" and seccionId="+idSeccion+" ORDER BY grupo asc ",null);

            while (q.moveToNext())
            {
                lista.add(q.getInt(0));
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public ArrayList<Seccion> listarProfesorModalidadCicloCurso(int profesorId, int modalidadEstudioId, int cicloId, int cursoId) {
        ArrayList<Seccion> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            String sql="SELECT DISTINCT S.* FROM\n" +
                    "SECCIONES S INNER JOIN CARGA_DOCENTE CD\n"+
                    "ON S.seccionId=CD.seccionId INNER JOIN CURSOS CU\n" +
                    "ON CD.cursoId=CU.cursoId INNER JOIN CARRERAS_CURSOS CC\n" +
                    "ON CU.cursoId=CC.cursoId INNER JOIN CARRERAS CA\n" +
                    "ON CC.carreraId=CA.carreraId INNER JOIN MODALIDADES_ESTUDIOS ME\n" +
                    "ON CA.modalidadEstudioId=ME.modalidadEstudioId \n" +
                    "WHERE CD.profesorId="+profesorId+" AND CA.modalidadEstudioId="+modalidadEstudioId+" AND CD.cicloId="+cicloId+" AND CU.cursoId="+cursoId+";";
            Cursor q = database.rawQuery(sql,null);
            Seccion obj;
            while (q.moveToNext())
            {
                obj = new Seccion();
                obj.setSeccionId(q.getInt(0));
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
    public Seccion buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Seccion obj) {
        return 0;
    }

    @Override
    public int editar(Seccion obj) {
        return 0;
    }

    @Override
    public int eliminar(Seccion obj) {
        return 0;
    }
}
