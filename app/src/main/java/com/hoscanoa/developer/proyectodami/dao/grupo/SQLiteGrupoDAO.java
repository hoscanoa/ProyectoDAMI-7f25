package com.hoscanoa.developer.proyectodami.dao.grupo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.CargaDocente;
import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.beans.Grupo;
import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 25/05/2015.
 */
public class SQLiteGrupoDAO implements GrupoDAO {

    private Context context;

    public SQLiteGrupoDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<Grupo> listar() {
        return null;
    }

    @Override
    public Grupo buscar(int id) {
        return null;
    }

    @Override
    public int insertar(Grupo obj) {
        return 0;
    }

    @Override
    public int editar(Grupo obj) {
        return 0;
    }

    @Override
    public int eliminar(Grupo obj) {
        return 0;
    }

    @Override
    public     ArrayList<Grupo> listarProfesorModalidadCicloCurso(int profesorId, int modalidadEstudioId, int cicloId, int cursoId, int seccionId) {
        ArrayList<Grupo> lista = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            String sql="SELECT DISTINCT G.* FROM\n" +
                    "GRUPOS G INNER JOIN CARGA_DOCENTE CD\n"+
                    "ON G.grupoId=CD.grupoId INNER JOIN CURSOS CU\n" +
                    "ON CD.cursoId=CU.cursoId INNER JOIN CARRERAS_CURSOS CC\n" +
                    "ON CU.cursoId=CC.cursoId INNER JOIN CARRERAS CA\n" +
                    "ON CC.carreraId=CA.carreraId INNER JOIN MODALIDADES_ESTUDIOS ME\n" +
                    "ON CA.modalidadEstudioId=ME.modalidadEstudioId \n" +
                    "WHERE CD.profesorId="+profesorId+" AND CA.modalidadEstudioId="+modalidadEstudioId+" AND CD.cicloId="+cicloId+" AND CU.cursoId="+cursoId+" AND CD.seccionId="+seccionId+";";
            Cursor q = database.rawQuery(sql,null);
            Grupo obj;
            while (q.moveToNext()) {
                obj = new Grupo();
                obj.setGrupoId(q.getInt(0));
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
    public void insertar(ArrayList<Grupo> grupos) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(Grupo g: grupos){
                ContentValues values= new ContentValues();
                values.put("grupoId",g.getGrupoId());
                values.put("descripcion",g.getDescripcion());
                database.insert("GRUPOS",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
