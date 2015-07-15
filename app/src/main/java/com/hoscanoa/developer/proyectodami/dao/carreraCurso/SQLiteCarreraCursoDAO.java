package com.hoscanoa.developer.proyectodami.dao.carreraCurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.CargaDocente;
import com.hoscanoa.developer.proyectodami.beans.Carrera;
import com.hoscanoa.developer.proyectodami.beans.CarreraCurso;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 15/07/2015.
 */
public class SQLiteCarreraCursoDAO implements CarreraCursoDAO {
    private Context context;

    public SQLiteCarreraCursoDAO(Context context) {
        this.context=context;
    }

    @Override
    public ArrayList<CargaDocente> listar() {
        return null;
    }

    @Override
    public CargaDocente buscar(int id) {
        return null;
    }

    @Override
    public int insertar(CargaDocente obj) {
        return 0;
    }

    @Override
    public int editar(CargaDocente obj) {
        return 0;
    }

    @Override
    public int eliminar(CargaDocente obj) {
        return 0;
    }

    @Override
    public void insertar(ArrayList<CarreraCurso> carreraCursos) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(CarreraCurso c : carreraCursos){
                ContentValues values= new ContentValues();
                values.put("carreraCursoId",c.getCarreraCursoId());
                values.put("carreraId",c.getCarreraId());
                values.put("cursoId",c.getCursoId());
                values.put("creditos",c.getCreditos());
                database.insert("CARRERAS_CURSOS",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
