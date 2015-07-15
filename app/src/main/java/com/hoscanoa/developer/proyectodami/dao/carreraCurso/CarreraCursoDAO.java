package com.hoscanoa.developer.proyectodami.dao.carreraCurso;

import android.util.Log;

import com.hoscanoa.developer.proyectodami.beans.CargaDocente;
import com.hoscanoa.developer.proyectodami.beans.CarreraCurso;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 15/07/2015.
 */
public interface CarreraCursoDAO extends GenericDAO<CargaDocente, Long> {
    void insertar(ArrayList<CarreraCurso> carreraCursos);
}
