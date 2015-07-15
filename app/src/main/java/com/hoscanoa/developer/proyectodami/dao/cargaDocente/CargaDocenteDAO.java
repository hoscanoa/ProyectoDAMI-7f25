package com.hoscanoa.developer.proyectodami.dao.cargaDocente;

import com.hoscanoa.developer.proyectodami.beans.CargaDocente;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 19/05/2015.
 */
public interface CargaDocenteDAO extends GenericDAO<CargaDocente, Long> {
    void insertar(ArrayList<CargaDocente> cargaDocentes);
}
