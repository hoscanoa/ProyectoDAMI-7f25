package com.hoscanoa.developer.proyectodami.dao.estado;

import com.hoscanoa.developer.proyectodami.beans.Estado;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public interface EstadoDAO extends GenericDAO<Estado, Long>
{

    void insertar(ArrayList<Estado> estados);
}
