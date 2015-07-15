package com.hoscanoa.developer.proyectodami;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.beans.ModalidadEstudio;
import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.ciclo.CicloDAO;
import com.hoscanoa.developer.proyectodami.dao.historial.HistorialDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.ModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.dao.profesor.ProfesorDAO;
import com.hoscanoa.developer.proyectodami.servicio.Servicio;


import java.util.ArrayList;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    EditText edtUsuario, edtPassword;
    Button btnEntrar;
    String username;
    String password;
    Profesor profesor;
    ArrayList<ModalidadEstudio> modalidadEstudios;
    ArrayList<Ciclo> ciclos;

    AlertDialog alerta;

    ProgressDialog progressDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario=(EditText)findViewById(R.id.edtUsuario);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        username = edtUsuario.getText().toString();
        password = edtPassword.getText().toString();
        boolean flag=true;
        if(username.isEmpty())
        {
            edtUsuario.setError("Usuario inválido");
            flag=false;
        }

        if(password.isEmpty())
        {
            edtPassword.setError("Password inválido");
            flag=false;
        }

        if(flag)
        {
            progressDialog=ProgressDialog.show(this,"Verificando Identidad","Espere por favor",true,false);
            new Logueo().execute();
        }
    }


    private class Logueo extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            ProfesorDAO profesorDAO = factory.getProfesorDAO(getBaseContext());
            ModalidadEstudioDAO modalidadEstudioDAO = factory.getModalidadEstudioDAO(getBaseContext());
            CicloDAO cicloDAO = factory.getCicloDAO(getBaseContext());
            HistorialDAO historialDAO = factory.getHistorialDAO(getBaseContext());
            try {
                username = edtUsuario.getText().toString();
                password = edtPassword.getText().toString();

                Servicio servicio = new Servicio();
                ArrayList<Object> objetos = servicio.LogearComplejo(username, password);
                if(objetos.size()==3) {
                    profesor = (Profesor) (objetos.get(0));
                    modalidadEstudios = (ArrayList<ModalidadEstudio>) (objetos.get(1));
                    ciclos = (ArrayList<Ciclo>) (objetos.get(2));

                    if(historialDAO.listar().size()>0)
                    {
                        if(historialDAO.buscar(profesor.getProfesorId())==null)
                        {
                            profesorDAO.insertarProfesorHistorial(profesor);
                            profesorDAO.insertar(profesor);
                        }
                    }
                    else
                    {
                        profesorDAO.insertarProfesorHistorial(profesor);
                        profesorDAO.insertar(profesor);
                        modalidadEstudioDAO.insertarModalidades(modalidadEstudios);
                        cicloDAO.insertarCiclos(ciclos);
                    }
                }
                else
                {
                    profesor=null;
                }
            } catch (Exception e) {
                profesor = null;
            }
            return null;
        }


        protected void onPostExecute(Void unused) {
            progressDialog.dismiss();

            if(profesor!=null)
            {
                Intent ir = new Intent(getBaseContext(),OpcionesActivity.class);
                ir.putExtra("profesor", profesor);
                startActivity(ir);
            }
            else
            {
                Toast.makeText(getBaseContext(),"Datos incorrectos",Toast.LENGTH_LONG).show();
            }
        }
    }
}
