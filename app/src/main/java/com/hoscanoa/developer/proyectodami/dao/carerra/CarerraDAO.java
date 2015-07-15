package com.hoscanoa.developer.proyectodami.dao.carerra;

import com.hoscanoa.developer.proyectodami.beans.Carrera;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public interface CarerraDAO extends GenericDAO<Carrera, Long>
{

    void insertar(ArrayList<Carrera> carreras);
}