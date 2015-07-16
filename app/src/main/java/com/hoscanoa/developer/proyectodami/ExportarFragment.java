package com.hoscanoa.developer.proyectodami;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
import com.hoscanoa.developer.proyectodami.beans.Matricula;
import com.hoscanoa.developer.proyectodami.beans.RegistroNota;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.alumno.AlumnoDAO;
import com.hoscanoa.developer.proyectodami.dao.matricula.MatriculaDAO;
import com.hoscanoa.developer.proyectodami.dao.registroNota.RegistroNotaDAO;
import com.hoscanoa.developer.proyectodami.servicio.Servicio;

import java.util.ArrayList;


public class ExportarFragment extends Fragment implements View.OnClickListener {

    Context context;
    Button btnExportar;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exportar, container, false);
        context = container.getContext();

        btnExportar = (Button)rootView.findViewById(R.id.btnExportarDatos);
        btnExportar.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v==btnExportar)
        {
            progressDialog = new ProgressDialog(context);
            new Exportar().execute();
        }
    }

    private class Exportar extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            RegistroNotaDAO registroNotaDAO = factory.getRegistroNotaDAO(context);

            ArrayList<RegistroNota> registroNotas = registroNotaDAO.listar();

            Servicio servicio = new Servicio();
            servicio.exportar(registroNotas);
            return null;
        }

        protected void onPostExecute(Void unused) {

            cargarListado();
            progressDialog.dismiss();
        }
    }

}
