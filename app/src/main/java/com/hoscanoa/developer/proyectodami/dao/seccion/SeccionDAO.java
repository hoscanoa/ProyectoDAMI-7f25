package com.hoscanoa.developer.proyectodami.dao.seccion;


import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;


/**
 * Created by Hernan on 17/05/2015.
 */
public interface SeccionDAO extends GenericDAO<Seccion, Long>
{

    public ArrayList<Seccion> listarxCiclo_Profesor_Curso(int idCiclo, int idProfesor, int idCurso);
    public ArrayList<Integer> listarGruposxCiclo_Profesor_Curso(int idCiclo, int idProfesor, int idCurso, int idSeccion);

    ArrayList<Seccion> listarProfesorModalidadCicloCurso(int profesorId, int modalidadEstudioId, int cicloId, int cursoId);
}
