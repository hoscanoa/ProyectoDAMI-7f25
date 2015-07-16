package com.hoscanoa.developer.proyectodami.dao.cursoEvaluacion;

import com.hoscanoa.developer.proyectodami.beans.CursoEvaluacion;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;


/**
 * Created by Hernan on 17/05/2015.
 */
public interface CursoEvaluacionDAO extends GenericDAO<CursoEvaluacion, Long>
{

    ArrayList<CursoEvaluacion> listarCursoEvaluacion(int cursoId, int evaluacionId);
    void insertar(ArrayList<CursoEvaluacion> cursoEvaluaciones);
    CursoEvaluacion buscar(int cursoId, int evaluacionId, int numero);

}