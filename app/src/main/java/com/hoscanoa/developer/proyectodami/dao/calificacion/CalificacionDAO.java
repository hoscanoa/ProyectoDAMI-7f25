package com.hoscanoa.developer.proyectodami.dao.calificacion;

import com.hoscanoa.developer.proyectodami.beans.Calificacion;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public interface CalificacionDAO extends GenericDAO<Calificacion, Long>
{

    void insertar(ArrayList<Calificacion> calificaciones);
}
