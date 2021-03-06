package com.hoscanoa.developer.proyectodami.dao.cargaDocente;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.CargaDocente;
import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;

import java.util.ArrayList;

/**
 * Created by Hernan on 19/05/2015.
 */
public class SQLiteCargaDocenteDAO implements CargaDocenteDAO {
    private Context context;

    public SQLiteCargaDocenteDAO(Context context)
    {
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
    public void insertar(ArrayList<CargaDocente> cargaDocentes) {
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            for(CargaDocente c: cargaDocentes){
                ContentValues values= new ContentValues();
                values.put("cargaDocenteId",c.getCargaDocenteId());
                values.put("cursoId",c.getCursoId());
                values.put("profesorId",c.getProfesorId());
                values.put("cicloId",c.getCicloId());
                values.put("seccionId",c.getSeccionId());
                values.put("grupoId",c.getGrupoId());
                database.insert("CARGA_DOCENTE",null,values);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
