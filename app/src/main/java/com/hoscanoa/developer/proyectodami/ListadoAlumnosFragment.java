package com.hoscanoa.developer.proyectodami;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.alumno.AlumnoDAO;
import com.hoscanoa.developer.proyectodami.util.InputFilterMinMax;

import java.util.ArrayList;


public class ListadoAlumnosFragment extends Fragment {

    Context context;
    int cicloId, cursoId, seccionId, grupoId;
    TableLayout tabla;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listado_alumnos, container, false);
        context = container.getContext();
        cicloId = getArguments().getInt("cicloId");
        cursoId = getArguments().getInt("cursoId");
        seccionId = getArguments().getInt("seccionId");
        grupoId = getArguments().getInt("grupoId");

        tabla = (TableLayout) rootView.findViewById(R.id.tblListado);
        cargarListado();
        return rootView;
    }


    public void cargarListado() {

        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        AlumnoDAO alumnoDAO = factory.getAlumnoDAO(context);

        ArrayList<Alumno> lista = alumnoDAO.listado(cicloId, cursoId, seccionId, grupoId);

        TableRow row;
        TextView txtcod, txtnom, txtEstado, txtnota;
        EditText edtNota;

        row = new TableRow(context);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        int leftMargin=10;
        int topMargin=2;
        int rightMargin=10;
        int bottomMargin=2;
        layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        row.setLayoutParams(layoutParams);

        txtcod = new TextView(context);
        txtcod.setGravity(Gravity.CENTER);
        txtcod.setTextColor(Color.parseColor("#1A237E"));

        txtnom = new TextView(context);
        txtnom.setGravity(Gravity.CENTER);
        txtnom.setTextColor(Color.parseColor("#1A237E"));

        txtEstado = new TextView(context);
        txtEstado.setGravity(Gravity.CENTER);
        txtEstado.setTextColor(Color.parseColor("#1A237E"));

        txtnota = new TextView(context);
        txtnota.setGravity(Gravity.CENTER);
        txtnota.setTextColor(Color.parseColor("#1A237E"));

        txtcod.setText("Alumno");
        txtnom.setText("Apellidos y Nombres");
        txtEstado.setText("Estado");
        txtnota.setText("Nota");
        row.addView(txtcod);
        row.addView(txtnom);
        row.addView(txtEstado);
        row.addView(txtnota);


        View v = new View(context);
        v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
        v.setBackgroundColor(Color.rgb(51, 51, 51));


        tabla.addView(row);
        tabla.addView(v);
        int i=0;

        Resources resource = context.getResources();

        for (Alumno a : lista) {
            row = new TableRow(context);
            row.setLayoutParams(layoutParams);
           if(i%2==0)
            {

                row.setBackgroundColor(resource.getColor(R.color.filaPar));
            }
            else
            {
                row.setBackgroundColor(resource.getColor(R.color.filaImpar));
            }
            i++;
            txtcod = new TextView(context);
            txtcod.setGravity(Gravity.CENTER);
            txtcod.setTextColor(Color.parseColor("#000000"));

            txtnom = new TextView(context);
            txtnom.setGravity(Gravity.CENTER);
            txtnom.setTextColor(Color.parseColor("#000000"));

            txtEstado = new TextView(context);
            txtEstado.setGravity(Gravity.CENTER);
            txtEstado.setTextColor(Color.parseColor("#000000"));

            edtNota = new EditText(context);
            edtNota.setInputType(InputType.TYPE_CLASS_NUMBER);
            edtNota.setGravity(Gravity.RIGHT);
            edtNota.setTextColor(Color.parseColor("#000000"));

            edtNota.setFilters(new InputFilter[]{new InputFilterMinMax("0","20")});

            txtcod.setText(a.getCodigo());
            txtnom.setText(a.getApellidoPaterno() + " " + a.getApellidoMaterno() + ", " + a.getNombres());
            txtEstado.setText("MATRÍCULA REGULAR");
            txtEstado.setTextSize(8);
            edtNota.setText(String.valueOf(0));
            row.addView(txtcod);
            row.addView(txtnom);
            row.addView(txtEstado);
            row.addView(edtNota);
            tabla.addView(row);
            v = new View(context);
            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
            v.setBackgroundColor(Color.rgb(51, 51, 51));
            tabla.addView(v);
        }

    }

}
