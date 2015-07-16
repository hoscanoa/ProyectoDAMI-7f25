package com.hoscanoa.developer.proyectodami.dao.registroNota;

import com.hoscanoa.developer.proyectodami.beans.RegistroNota;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

/**
 * Created by Hernan on 17/05/2015.
 */
public interface RegistroNotaDAO extends GenericDAO<RegistroNota, Long>
{

    RegistroNota buscar(int matriculaId, int evaluacionId);
}
