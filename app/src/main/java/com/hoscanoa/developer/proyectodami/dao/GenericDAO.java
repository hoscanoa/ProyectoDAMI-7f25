package com.hoscanoa.developer.proyectodami.dao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hernan on 18/05/2015.
 */
public interface GenericDAO<T, ID extends Serializable> {
     ArrayList<T> listar();
     T buscar(int id);
     int insertar(T obj);
     int editar(T obj);
     int eliminar(T obj);
}
