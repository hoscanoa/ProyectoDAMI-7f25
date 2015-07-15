package com.hoscanoa.developer.proyectodami;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.beans.ModalidadEstudio;
import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.ciclo.CicloDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.ModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.servicio.Servicio;

import java.util.ArrayList;

public class ImportarFragment extends Fragment implements View.OnClickListener {

    Context context;

    Profesor profesor;
    TextView lblProfesor;
    Spinner spnModalidad, spnCiclo;
    Button btnImportar;

    ProgressDialog progressDialog;

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
            int k=0;

            return null;
        }


        protected void onPostExecute(Void unused) {
            Toast.makeText(getActivity(),"Importación correcta",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

}
