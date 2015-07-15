package com.hoscanoa.developer.proyectodami.dao.alumno;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 17/05/2015.
 */
public interface AlumnoDAO extends GenericDAO<Alumno, Long>
{
    ArrayList<Alumno> listado(int idCiclo,int idCurso,int idSeccion,int numGrupo);
}
