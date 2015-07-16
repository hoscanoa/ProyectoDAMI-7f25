package com.hoscanoa.developer.proyectodami.dao.alumno;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
import com.hoscanoa.developer.proyectodami.beans.Calificacion;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteAlumnoDAO implements AlumnoDAO {
    private Context context;

    public SQLiteAlumnoDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Alumno> listar() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM ALUMNOS",null);
            Alumno obj;
            while (q.moveToNext())
            {
                obj = new Alumno();
                obj.setAlumnoId(q.getInt(0));
                obj.setNombres(q.getString(1));
                obj.setApellidoPaterno(q.getString(2));
                obj.setApellidoMaterno(q.getString(3));
                obj.setEmail(q.getString(4));
                obj.setEstadoId(5);
                alumnos.add(obj);
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public Alumno buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Alumno obj) {
        return 0;
    }

    @Override
    public int editar(Alumno obj) {
        return 0;
    }

    @Override
    public int eliminar(Alumno obj) {
        return 0;
    }

    @Override
    public ArrayList<Alumno> listado(int idCiclo, int idCurso, int idSeccion, int numGrupo) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT a.*\n" +
                    "FROM matricula m inner join alumnos a\n" +
                    "on m.alumnoId=a.alumnoId inner join estados e\n" +
                    "on e.estadoId=m.estadoId \n" +
                    "where m.cicloId=? and m.cursoId=? and m.seccionId=? and m.grupoId=? order by a.apellidoPaterno",new String[]{String.valueOf(idCiclo),String.valueOf(idCurso),String.valueOf(idSeccion),String.valueOf(numGrupo)});
            Alumno obj;
            while (q.moveToNext())
            {
                obj = new Alumno();
                obj.setAlumnoId(q.getInt(0));
                obj.setCodigo(q.getString(1));
                obj.setNombres(q.getString(2));
                obj.setApellidoPaterno(q.getString(3));
                obj.setApellidoMaterno(q.getString(4));
                obj.setEmail(q.getString(5));
                obj.setEstadoId(q.getInt(6));
                alumnos.add(obj);
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void insertar(ArrayList<Alumno> alumnos) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Alumno alumno : alumnos){
                ContentValues values= new ContentValues();
                values.put("alumnoId",alumno.getAlumnoId());
                values.put("codigo",alumno.getCodigo());
                values.put("nombres",alumno.getNombres());
                values.put("apellidoPaterno",alumno.getApellidoPaterno());
                values.put("apellidoMaterno",alumno.getApellidoMaterno());
                values.put("email",alumno.getEmail());
                values.put("estadoId", alumno.getEstadoId());
                try {
                    database.insert("ALUMNOS", null, values);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }

    @Override
    public Alumno buscar(String codigo) {
        Alumno obj=null;
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM ALUMNOS where codigo=?",new String[]{codigo});

            if (q.moveToNext())
            {
                obj = new Alumno();
                obj.setAlumnoId(q.getInt(0));
                obj.setCodigo(q.getString(1));
                obj.setNombres(q.getString(2));
                obj.setApellidoPaterno(q.getString(3));
                obj.setApellidoMaterno(q.getString(4));
                obj.setEmail(q.getString(5));
                obj.setEstadoId(q.getInt(6));
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
