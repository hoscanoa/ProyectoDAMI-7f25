package com.hoscanoa.developer.proyectodami.dao.modalidadEstudio;

import com.hoscanoa.developer.proyectodami.beans.ModalidadEstudio;
import com.hoscanoa.developer.proyectodami.dao.GenericDAO;

import java.util.ArrayList;

/**
 * Created by Hernan on 19/05/2015.
 */
public interface ModalidadEstudioDAO extends GenericDAO<ModalidadEstudio, Long>{
    void insertarModalidades(ArrayList<ModalidadEstudio> modalidadEstudios);
}
