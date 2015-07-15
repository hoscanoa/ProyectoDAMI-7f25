package com.hoscanoa.developer.proyectodami.dao.evaluacion;

import com.hoscanoa.developer.proyectodami.beans.Evaluacion;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public interface EvaluacionDAO extends GenericDAO<Evaluacion, Long>
{
    ArrayList<Evaluacion> listarXCurso(int idCurso);

    ArrayList<Evaluacion> listarCurso(int cursoId);
}
