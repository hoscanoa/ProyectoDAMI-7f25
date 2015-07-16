package com.hoscanoa.developer.proyectodami.dao.matricula;

import com.hoscanoa.developer.proyectodami.beans.Matricula;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 19/05/2015.
 */
public interface MatriculaDAO extends GenericDAO<Matricula, Long> {
    void insertar(ArrayList<Matricula> matriculas);

    Matricula buscar(int alumnoId, int cursoId, int cicloId, int seccionId, int grupoId);
}
