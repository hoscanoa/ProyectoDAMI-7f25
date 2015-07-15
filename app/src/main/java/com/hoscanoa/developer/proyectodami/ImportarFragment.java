package com.hoscanoa.developer.proyectodami;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hoscanoa.developer.proyectodami.beans.Calificacion;
import com.hoscanoa.developer.proyectodami.beans.CargaDocente;
import com.hoscanoa.developer.proyectodami.beans.Carrera;
import com.hoscanoa.developer.proyectodami.beans.CarreraCurso;
import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.beans.CursoEvaluacion;
import com.hoscanoa.developer.proyectodami.beans.Estado;
import com.hoscanoa.developer.proyectodami.beans.Evaluacion;
import com.hoscanoa.developer.proyectodami.beans.Grupo;
import com.hoscanoa.developer.proyectodami.beans.ModalidadEstudio;
import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.calificacion.CalificacionDAO;
import com.hoscanoa.developer.proyectodami.dao.carerra.CarerraDAO;
import com.hoscanoa.developer.proyectodami.dao.cargaDocente.CargaDocenteDAO;
import com.hoscanoa.developer.proyectodami.dao.carreraCurso.CarreraCursoDAO;
import com.hoscanoa.developer.proyectodami.dao.ciclo.CicloDAO;
import com.hoscanoa.developer.proyectodami.dao.curso.CursoDAO;
import com.hoscanoa.developer.proyectodami.dao.cursoEvaluacion.CursoEvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.estado.EstadoDAO;
import com.hoscanoa.developer.proyectodami.dao.evaluacion.EvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.grupo.GrupoDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.ModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.dao.seccion.SeccionDAO;
import com.hoscanoa.developer.proyectodami.servicio.Servicio;

import java.util.ArrayList;

public class ImportarFragment extends Fragment implements View.OnClickListener {

    Context context;

    Profesor profesor;
    TextView lblProfesor;
    Spinner spnModalidad, spnCiclo;
    Button btnImportar;

    ProgressDialog progressDialog;

    ArrayList<Object> objetos = new ArrayList<Object>();

    ArrayList<Grupo> grupos = new  ArrayList<Grupo>();
    ArrayList<Seccion> secciones = new  ArrayList<Seccion>();
    ArrayList<Curso> cursos = new  ArrayList<Curso>();
    ArrayList<Evaluacion> evaluaciones = new  ArrayList<Evaluacion>();
    ArrayList<CursoEvaluacion> cursoEvaluaciones = new  ArrayList<CursoEvaluacion>();
    ArrayList<CargaDocente> cargaDocentes = new  ArrayList<CargaDocente>();
    ArrayList<Carrera> carreras = new  ArrayList<Carrera>();
    ArrayList<CarreraCurso> carreraCursos = new  ArrayList<CarreraCurso>();
    ArrayList<Calificacion> calificaciones = new  ArrayList<Calificacion>();
    ArrayList<Estado> estados = new  ArrayList<Estado>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_importar, container, false);

        context=container.getContext();
        Bundle  args = getArguments();
        profesor = (Profesor)args.getSerializable("profesor");

        lblProfesor = (TextView)rootView.findViewById(R.id.lblProfesorImportar);
        lblProfesor.setText(profesor.getNombres()+" "+profesor.getApellidoPaterno());

        spnModalidad=(Spinner)rootView.findViewById(R.id.spnModalidad);
        llenarModalidades();

        spnCiclo=(Spinner)rootView.findViewById(R.id.spnCicloImportar);
        llenarCiclos();


        //Typeface myCustomFont =Typeface.createFromAsset(getAssets(),"fonts/fontawesome.ttf");

        btnImportar = (Button)rootView.findViewById(R.id.btnImportarImportar);
        //btnImportar.setTypeface(myCustomFont);
        btnImportar.setOnClickListener(this);

        return rootView;
    }

    private void llenarCiclos() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        CicloDAO cicloDAO = factory.getCicloDAO(context);
        ArrayList<Ciclo> lista = cicloDAO.listar();

        ArrayAdapter<Ciclo> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,lista);
        spnCiclo.setAdapter(adapter);
    }

    

    private void llenarModalidades() {
        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        ModalidadEstudioDAO modalidadEstudioDAO = factory.getModalidadEstudioDAO(context);
        ArrayList<ModalidadEstudio> lista = modalidadEstudioDAO.listar();

        ArrayAdapter<ModalidadEstudio> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,lista);
        spnModalidad.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        progressDialog = new ProgressDialog(getActivity());
        new Importar().execute();
    }


    private class Importar extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            Servicio servicio = new Servicio();
            ModalidadEstudio modalidadEstudio = (ModalidadEstudio) spnModalidad.getSelectedItem();
            Ciclo ciclo = (Ciclo) spnCiclo.getSelectedItem();
            objetos=servicio.Importar(profesor.getProfesorId(),modalidadEstudio.getModalidadEstudioId(), ciclo.getCicloId());

            if(objetos!=null)
            {
                Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
                CursoDAO cursoDAO = factory.getCursoDAO(context);
                SeccionDAO seccionDAO = factory.getSeccionDAO(context);
                GrupoDAO grupoDAO = factory.getGrupoDAO(context);
                CargaDocenteDAO cargaDocenteDAO = factory.getCargaDocenteDAO(context);
                EvaluacionDAO evaluacionDAO = factory.getEvaluacionDAO(context);
                CursoEvaluacionDAO cursoEvaluacionDAO = factory.getCursoEvaluacionDAO(context);
                CarerraDAO carerraDAO = factory.getCarerraDAO(context);
                CarreraCursoDAO carreraCursoDAO=factory.getCarreraCursoDAO(context);
                CalificacionDAO calificacionDAO = factory.getCalificacionDAO(context);
                EstadoDAO estadoDAO = factory.getEstadoDAO(context);

                grupos = (ArrayList<Grupo>) objetos.get(0);
                secciones = (ArrayList<Seccion>)objetos.get(1);
                cursos = (ArrayList<Curso>)objetos.get(2);
                evaluaciones =(ArrayList<Evaluacion>)objetos.get(3);
                cargaDocentes =(ArrayList<CargaDocente>)objetos.get(4);
                cursoEvaluaciones = (ArrayList<CursoEvaluacion>)objetos.get(5);
                carreras= (ArrayList<Carrera>)objetos.get(6);
                carreraCursos= (ArrayList<CarreraCurso>)objetos.get(7);
                calificaciones = (ArrayList<Calificacion>)objetos.get(8);
                estados = (ArrayList<Estado>)objetos.get(9);

                grupoDAO.insertar(grupos);
                seccionDAO.insertar(secciones);
                cursoDAO.insertar(cursos);
                evaluacionDAO.insertar(evaluaciones);
                cargaDocenteDAO.insertar(cargaDocentes);
                cursoEvaluacionDAO.insertar(cursoEvaluaciones);
                carerraDAO.insertar(carreras);
                carreraCursoDAO.insertar(carreraCursos);
                calificacionDAO.insertar(calificaciones);
                estadoDAO.insertar(estados);
            }

            return null;
        }


        protected void onPostExecute(Void unused) {

            if(objetos!=null)
            {
                Toast.makeText(context,"Importación correcta",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context,"Importación incorrecta",Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }
    }

}
