package com.hoscanoa.developer.proyectodami.dao.grupo;

import com.hoscanoa.developer.proyectodami.beans.Grupo;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 25/05/2015.
 */
public interface GrupoDAO  extends GenericDAO<Grupo, Long>{
      ArrayList<Grupo> listarProfesorModalidadCicloCurso(int profesorId, int modalidadEstudioId, int cicloId, int cursoId, int seccionId);

      void insertar(ArrayList<Grupo> grupos);
}
