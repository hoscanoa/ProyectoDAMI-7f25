package com.hoscanoa.developer.proyectodami.dao.ciclo;

import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 19/05/2015.
 */
public interface CicloDAO extends GenericDAO<Ciclo, Long> {
    void insertarCiclos(ArrayList<Ciclo> ciclos);
}
