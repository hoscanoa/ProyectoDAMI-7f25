package com.hoscanoa.developer.proyectodami;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.beans.CursoEvaluacion;
import com.hoscanoa.developer.proyectodami.beans.Evaluacion;
import com.hoscanoa.developer.proyectodami.beans.Grupo;
import com.hoscanoa.developer.proyectodami.beans.ModalidadEstudio;
import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.ciclo.CicloDAO;
import com.hoscanoa.developer.proyectodami.dao.curso.CursoDAO;
import com.hoscanoa.developer.proyectodami.dao.cursoEvaluacion.CursoEvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.evaluacion.EvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.grupo.GrupoDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.ModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.dao.seccion.SeccionDAO;

import java.util.ArrayList;

public class RegistrarFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Context context;
    Spinner spnModalidad, spnCiclo, spnCurso, spnSeccion, spnGrupo, spnTipoPrueba, spnNroPrueba;
    Profesor profesor;
    Button btnMostrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registrar, container, false);

        context = container.getContext();
        Bundle args = getArguments();
        profesor = (Profesor) args.getSerializable("profesor");

        spnModalidad = (Spinner) rootView.findViewById(R.id.spnModalidadRegistrar);
        spnModalidad.setOnItemSelectedListener(this);

        spnCiclo = (Spinner) rootView.findViewById(R.id.spnCicloRegistrar);
        spnCiclo.setOnItemSelectedListener(this);

        spnCurso = (Spinner) rootView.findViewById(R.id.spnAsignaturaRegistrar);
        spnCurso.setOnItemSelectedListener(this);

        spnSeccion = (Spinner) rootView.findViewById(R.id.spnSeccionRegistrar);
        spnSeccion.setOnItemSelectedListener(this);

        spnGrupo = (Spinner) rootView.findViewById(R.id.spnGrupoRegistrar);
        spnGrupo.setOnItemSelectedListener(this);

        spnTipoPrueba = (Spinner) rootView.findViewById(R.id.spnTipoPrueba);
        spnTipoPrueba.setOnItemSelectedListener(this);

        spnNroPrueba = (Spinner) rootView.findViewById(R.id.spnNroPruebaRegistrar);
        spnNroPrueba.setOnItemSelectedListener(this);

        btnMostrar = (Button) rootView.findViewById(R.id.btnMostrar);
        btnMostrar.setOnClickListener(this);

        llenarModalidades();
        llenarCiclos();
        llenarCursos();

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == spnModalidad || parent == spnCiclo) {
            spnCurso.setAdapter(null);
            spnSeccion.setAdapter(null);
            spnGrupo.setAdapter(null);
            spnTipoPrueba.setAdapter(null);
            spnNroPrueba.setAdapter(null);
            llenarCursos();
        }
        if (parent == spnCurso) {
            spnSeccion.setAdapter(null);
            spnGrupo.setAdapter(null);
            spnTipoPrueba.setAdapter(null);
            spnNroPrueba.setAdapter(null);
            llenarSecciones();
            llenarEvaluaciones();
        }
        if (parent == spnSeccion) {
            spnGrupo.setAdapter(null);
            spnTipoPrueba.setAdapter(null);
            spnNroPrueba.setAdapter(null);
            llenarGrupos();
        }
        if (parent == spnGrupo) {
            spnTipoPrueba.setAdapter(null);
            spnNroPrueba.setAdapter(null);
            llenarEvaluaciones();
        }
        if (parent == spnTipoPrueba) {
            spnNroPrueba.setAdapter(null);
            llenarNumerosDePrueba();
        }
    }

    private void llenarCursos() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        CursoDAO cursoDAO = factory.getCursoDAO(context);
        ModalidadEstudio modalidadEstudio = (ModalidadEstudio) spnModalidad.getSelectedItem();
        Ciclo ciclo = (Ciclo) spnCiclo.getSelectedItem();

        ArrayList<Curso> lista = cursoDAO.listarProfesorModalidadCiclo(profesor.getProfesorId(), modalidadEstudio.getModalidadEstudioId(), ciclo.getCicloId());
        if (!lista.isEmpty()) {
            ArrayAdapter<Curso> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spnCurso.setAdapter(adapter);
        }
    }

    private void llenarCiclos() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        CicloDAO cicloDAO = factory.getCicloDAO(context);
        ArrayList<Ciclo> lista = cicloDAO.listar();
        if (!lista.isEmpty()) {
            ArrayAdapter<Ciclo> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spnCiclo.setAdapter(adapter);
        }
    }

    private void llenarModalidades() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        ModalidadEstudioDAO modalidadEstudioDAO = factory.getModalidadEstudioDAO(context);
        ArrayList<ModalidadEstudio> lista = modalidadEstudioDAO.listar();
        if (!lista.isEmpty()) {
            ArrayAdapter<ModalidadEstudio> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spnModalidad.setAdapter(adapter);
        }

    }

    private void llenarSecciones() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        SeccionDAO seccionDAO = factory.getSeccionDAO(context);

        ModalidadEstudio modalidadEstudio = (ModalidadEstudio) spnModalidad.getSelectedItem();
        Ciclo ciclo = (Ciclo) spnCiclo.getSelectedItem();
        Curso curso = (Curso) spnCurso.getSelectedItem();

        ArrayList<Seccion> lista = seccionDAO.listarProfesorModalidadCicloCurso(profesor.getProfesorId(), modalidadEstudio.getModalidadEstudioId(), ciclo.getCicloId(), curso.getCursoId());
        if (!lista.isEmpty()) {
            ArrayAdapter<Seccion> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spnSeccion.setAdapter(adapter);
        }

    }

    private void llenarGrupos() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        GrupoDAO grupoDAO = factory.getGrupoDAO(context);

        ModalidadEstudio modalidadEstudio = (ModalidadEstudio) spnModalidad.getSelectedItem();
        Ciclo ciclo = (Ciclo) spnCiclo.getSelectedItem();
        Curso curso = (Curso) spnCurso.getSelectedItem();
        Seccion seccion = (Seccion) spnSeccion.getSelectedItem();

        ArrayList<Grupo> lista = grupoDAO.listarProfesorModalidadCicloCurso(profesor.getProfesorId(), modalidadEstudio.getModalidadEstudioId(), ciclo.getCicloId(), curso.getCursoId(), seccion.getSeccionId());
        if (!lista.isEmpty()) {
            ArrayAdapter<Grupo> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spnGrupo.setAdapter(adapter);
        }
    }

    private void llenarEvaluaciones() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        EvaluacionDAO evaluacionDAO = factory.getEvaluacionDAO(context);

        Curso curso = (Curso) spnCurso.getSelectedItem();

        ArrayList<Evaluacion> lista = evaluacionDAO.listarCurso(curso.getCursoId());
        if (!lista.isEmpty()) {
            ArrayAdapter<Evaluacion> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spnTipoPrueba.setAdapter(adapter);
        }
    }

    private void llenarNumerosDePrueba() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        CursoEvaluacionDAO cursoEvaluacionDAO = factory.getCursoEvaluacionDAO(context);

        Curso curso = (Curso) spnCurso.getSelectedItem();
        Evaluacion evaluacion = (Evaluacion) spnTipoPrueba.getSelectedItem();

        ArrayList<CursoEvaluacion> lista = cursoEvaluacionDAO.listarCursoEvaluacion(curso.getCursoId(), evaluacion.getEvaluacionId());
        if (!lista.isEmpty()) {
            ArrayAdapter<CursoEvaluacion> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
            spnNroPrueba.setAdapter(adapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View view) {
        if (view == btnMostrar) {
            String error = "";

            if (spnCiclo.getAdapter() == null) {
                error = "Elija un ciclo";
            } else {
                if (spnCurso.getAdapter() == null) {
                    error = "Elija una asignatura";
                } else {
                    if (spnSeccion.getAdapter() == null) {
                        error = "Elija una sección";
                    } else {
                        if (spnGrupo.getAdapter() == null) {
                            error = "Elija un grupo";
                        } else {
                            if (spnTipoPrueba.getAdapter() == null) {
                                error = "Elija un tipo de prueba";
                            } else {
                                if (spnNroPrueba.getAdapter() == null) {
                                    error = "Elija un número de Prueba";
                                }
                            }
                        }
                    }
                }
            }

            if (error.isEmpty()) {
                Ciclo ciclo = (Ciclo) spnCiclo.getSelectedItem();
                Curso curso = (Curso) spnCurso.getSelectedItem();
                Seccion seccion = (Seccion) spnSeccion.getSelectedItem();
                Grupo grupo = (Grupo) spnGrupo.getSelectedItem();
                Evaluacion evaluacion = (Evaluacion) spnTipoPrueba.getSelectedItem();
                CursoEvaluacion cursoEvaluacion = (CursoEvaluacion) spnNroPrueba.getSelectedItem();


                Bundle bundle= new Bundle();
                bundle.putInt("cicloId", ciclo.getCicloId());
                bundle.putInt("cursoId",curso.getCursoId());
                bundle.putInt("seccionId", seccion.getSeccionId());
                bundle.putInt("grupoId", grupo.getGrupoId());
                bundle.putInt("evaluacionId", evaluacion.getEvaluacionId());
                bundle.putInt("numero", cursoEvaluacion.getNumero());

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ListadoAlumnosFragment laf = new ListadoAlumnosFragment();

                laf.setArguments(bundle);

                fragmentTransaction.replace(android.R.id.content, laf, "laf");

                fragmentTransaction.addToBackStack("laf");
                fragmentTransaction.commit();
            } else {
                AlertDialog alerta = new AlertDialog.Builder(getActivity()).create();
                alerta.setTitle("Error");
                alerta.setMessage(error + " para continuar");
                alerta.setIcon(R.drawable.error);
                alerta.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerta.show();
            }
        }
    }
}
