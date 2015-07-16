package com.hoscanoa.developer.proyectodami.dao.registroNota;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
import com.hoscanoa.developer.proyectodami.beans.Estado;
import com.hoscanoa.developer.proyectodami.beans.RegistroNota;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;
import com.hoscanoa.developer.proyectodami.dao.Factory;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteRegistroNotaDAO implements RegistroNotaDAO {
    private Context context;

    public SQLiteRegistroNotaDAO(Context context)
    {
        this.context=context;
    }

    @Override
    public ArrayList<RegistroNota> listar() {
        ArrayList<RegistroNota> registroNotas = new ArrayList<>();
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor q = database.rawQuery("SELECT * FROM REGISTRO_NOTAS",null);
            RegistroNota obj;
            while (q.moveToNext())
            {
                obj = new RegistroNota();
                obj.setRegistroNotaId(q.getInt(0));
                obj.setMatriculaId(q.getInt(1));
                obj.setCursoEvaluacionId(q.getInt(2));
                obj.setCalificacionId(q.getInt(3));
                registroNotas.add(obj);
            }
            q.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return registroNotas;
    }

    @Override
    public RegistroNota buscar(int id) {
        return null;
    }

    @Override
    public int insertar(RegistroNota obj) {
        int r=0;
        try {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase database = helper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("matriculaId",obj.getMatriculaId());
            values.put("cursoEvaluacionId",obj.getCursoEvaluacionId());
            values.put("calificacionId",obj.getCalificacionId());
            try {
                r=(int)database.insert("REGISTRO_NOTAS", null, values);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
        return r;
    }

    @Override
    public int editar(RegistroNota obj) {
        return 0;
    }

    @Override
    public int eliminar(RegistroNota obj) {
        return 0;
    }

}
