package com.hoscanoa.developer.proyectodami.dao.profesor;

import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;


/**
 * Created by Hernan on 17/05/2015.
 */
public interface ProfesorDAO extends GenericDAO<Profesor, Long>
{
    public Profesor logear(String username, String password);
    public ArrayList<Profesor> listarXciclo(int cicloId);

    void insertarProfesorHistorial(Profesor profesor);
}