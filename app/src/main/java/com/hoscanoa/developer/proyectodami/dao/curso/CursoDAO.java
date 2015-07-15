package com.hoscanoa.developer.proyectodami.dao.curso;

import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;


/**
 * Created by Hernan on 17/05/2015.
 */
public interface CursoDAO extends GenericDAO<Curso, Long>
{
    ArrayList<Curso> listarProfesorModalidadCiclo(int profesorId, int modalidadEstudioId, int cicloId);

    void insertar(ArrayList<Curso> cursos);
}
