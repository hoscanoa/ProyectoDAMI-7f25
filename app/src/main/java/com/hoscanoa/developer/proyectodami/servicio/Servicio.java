package com.hoscanoa.developer.proyectodami.servicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
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
import com.hoscanoa.developer.proyectodami.beans.Matricula;
import com.hoscanoa.developer.proyectodami.beans.ModalidadEstudio;
import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.beans.RegistroNota;
import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.alumno.AlumnoDAO;
import com.hoscanoa.developer.proyectodami.dao.cargaDocente.CargaDocenteDAO;
import com.hoscanoa.developer.proyectodami.dao.ciclo.CicloDAO;
import com.hoscanoa.developer.proyectodami.dao.curso.CursoDAO;
import com.hoscanoa.developer.proyectodami.dao.evaluacion.EvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.grupo.GrupoDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.ModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.dao.seccion.SeccionDAO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XT6136 on 13/07/2015.
 */
public class Servicio {

    public Profesor Logear(String usuario,String contraseña){

        String mensaje = null;
        Profesor profesor = null;
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/logeo/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("username", usuario));
            p.add(new BasicNameValuePair("password", contraseña));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();


            String respuestaStr = EntityUtils.toString(resp.getEntity());
            JSONObject respuestaJSON = new JSONObject(respuestaStr);

            int error=  respuestaJSON.length();
            if(error==1){
            }else{

                int id=(int)respuestaJSON.get("profesorId");

                profesor= new Profesor();
                profesor.setProfesorId(id);
                profesor.setNombres(respuestaJSON.getString("nombres"));
                profesor.setApellidoPaterno(respuestaJSON.getString("apellidoPaterno"));
                profesor.setApellidoMaterno(respuestaJSON.getString("apellidoMaterno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profesor;

    }

   public  ArrayList<Object> LogearComplejo(String username, String password)
    {
        ArrayList<Object> objetos = new ArrayList<Object>();

        Profesor profesor=null;
        ArrayList<ModalidadEstudio> modalidadEstudios = new ArrayList<ModalidadEstudio>();
        ArrayList<Ciclo> ciclos = new  ArrayList<Ciclo>();

        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/logeoComplejo/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("username", username));
            p.add(new BasicNameValuePair("password", password));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();


            String respuestaStr = EntityUtils.toString(resp.getEntity());
            JSONObject respuestaJSON = new JSONObject(respuestaStr);

            String mensaje = respuestaJSON.getString("mensaje");

            if(mensaje.equals("exito"))
            {
                JSONObject profesorJson = respuestaJSON.getJSONObject("profesor");

                profesor = new Profesor(profesorJson.getInt("profesorId"),profesorJson.getString("nombres"),profesorJson.getString("apellidoPaterno"),profesorJson.getString("apellidoMaterno"));

                objetos.add(profesor);

                JSONArray arreglo = respuestaJSON.getJSONArray("modalidadEstudios");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject m = arreglo.getJSONObject(i);
                    ModalidadEstudio modalidadEstudio = new ModalidadEstudio(m.getInt("modalidadEstudioId"),m.getString("descripcion"),m.getString("abreviatura"));
                    modalidadEstudios.add(modalidadEstudio);
                }
                objetos.add(modalidadEstudios);

                arreglo = respuestaJSON.getJSONArray("ciclos");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject c = arreglo.getJSONObject(i);
                    Ciclo ciclo = new Ciclo(c.getInt("cicloId"),c.getString("descripcion"));
                    ciclos.add(ciclo);
                }
                objetos.add(ciclos);
            }
            else
            {
                objetos.add(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objetos;
    }


    public ArrayList<Object> Importar(int profesorId, int modalidadEstudioId, int cicloId)
    {
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


        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/importacion/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("profesorId", String.valueOf(profesorId)));
            p.add(new BasicNameValuePair("modalidadEstudioId", String.valueOf(modalidadEstudioId)));
            p.add(new BasicNameValuePair("cicloId", String.valueOf(cicloId)));


            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();


            String respuestaStr = EntityUtils.toString(resp.getEntity());
            JSONObject respuestaJSON = new JSONObject(respuestaStr);

            String mensaje = respuestaJSON.getString("mensaje");

            if(mensaje.equals("exito"))
            {
                //CALIFICACIONES
                JSONArray arreglo = respuestaJSON.getJSONArray("Calificaciones");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject g = arreglo.getJSONObject(i);
                    Calificacion calificacion = new Calificacion(g.getInt("calificacionId"),g.getString("descripcion"), g.getInt("nota"));
                    calificaciones.add(calificacion);
                }

                //ESTADOS
                arreglo = respuestaJSON.getJSONArray("Estados");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject g = arreglo.getJSONObject(i);
                    Estado estado = new Estado(g.getInt("estadoId"),g.getString("descripcion"));
                    estados.add(estado);
                }

                //GRUPOS
                arreglo = respuestaJSON.getJSONArray("Grupos");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject g = arreglo.getJSONObject(i);
                    Grupo grupo = new Grupo(g.getInt("grupoId"),g.getString("descripcion"));
                    grupos.add(grupo);
                }

                //SECCIONES
                arreglo = respuestaJSON.getJSONArray("Secciones");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject s = arreglo.getJSONObject(i);
                    Seccion seccion = new Seccion(s.getInt("seccionId"),s.getString("descripcion"));
                    secciones.add(seccion);
                }


                //EVALUACIONES
                arreglo = respuestaJSON.getJSONArray("Evaluaciones");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject e = arreglo.getJSONObject(i);
                    Evaluacion evaluacion = new Evaluacion(e.getInt("evaluacionId"),e.getString("descripcion"));
                    evaluaciones.add(evaluacion);
                }

                //CARRERAS
                arreglo = respuestaJSON.getJSONArray("Carreras");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject ca = arreglo.getJSONObject(i);
                    Carrera carrera = new Carrera(ca.getInt("carreraId"),ca.getString("descripcion"),ca.getInt("modalidadEstudioId"));
                    carreras.add(carrera);
                }

                //CARRERASCURSOS
                arreglo = respuestaJSON.getJSONArray("CarrerasCursos");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject cc = arreglo.getJSONObject(i);
                    CarreraCurso carreraCurso = new CarreraCurso(cc.getInt("carreraCursoId"),cc.getInt("carreraId"),cc.getInt("cursoId"),cc.getInt("creditos"));
                    carreraCursos.add(carreraCurso);
                }


                //CARGADOCENTE
                arreglo = respuestaJSON.getJSONArray("CargaDocente");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject cd = arreglo.getJSONObject(i);
                    CargaDocente cargaDocente = new CargaDocente(cd.getInt("cargaDocenteId"),cd.getInt("cursoId"),cd.getInt("profesorId"),cd.getInt("cicloId"),cd.getInt("seccionId"),cd.getInt("grupoId"));
                    cargaDocentes.add(cargaDocente);
                }



                //CURSOS
                arreglo = respuestaJSON.getJSONArray("Cursos");
                for(int i=0;i<arreglo.length();i++)
                {
                    JSONObject c = arreglo.getJSONObject(i);
                    Curso curso = new Curso(c.getInt("cursoId"),c.getString("codigo"),c.getString("descripcion"));
                    cursos.add(curso);

                    JSONArray arreglo2 =c.getJSONArray("CursoEvaluaciones");
                    for(int j=0;j<arreglo2.length();j++) {
                        JSONObject ce = arreglo2.getJSONObject(j);
                        CursoEvaluacion cursoEvaluacion = new CursoEvaluacion(ce.getInt("cursoEvaluacionId"),
                                ce.getInt("cursoId"), ce.getInt("evaluacionId"), ce.getInt("numero"), ce.getInt("porcentaje"));
                        cursoEvaluaciones.add(cursoEvaluacion);
                    }
                }
                objetos.add(grupos);
                objetos.add(secciones);
                objetos.add(cursos);
                objetos.add(evaluaciones);
                objetos.add(cargaDocentes);
                objetos.add(cursoEvaluaciones);
                objetos.add(carreras);
                objetos.add(carreraCursos);
                objetos.add(calificaciones);
                objetos.add(estados);
            }
            else
            {
                objetos=null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objetos;
    }



    public ArrayList<Object> importarAlumnos(int cicloId, int cursoId, int seccionId, int grupoId) {
        ArrayList<Object> objetos = new  ArrayList<Object>();

        ArrayList<Alumno> alumnos = new  ArrayList<Alumno>();
        ArrayList<Matricula> matriculas = new  ArrayList<Matricula>();

        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarAlumnosPorCicloCursoSeccionGrupo/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("cicloId", String.valueOf(cicloId)));
            p.add(new BasicNameValuePair("cursoId", String.valueOf(cursoId)));
            p.add(new BasicNameValuePair("seccionId", String.valueOf(seccionId)));
            p.add(new BasicNameValuePair("grupoId", String.valueOf(grupoId)));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();

            String respuestaStr = EntityUtils.toString(resp.getEntity());
            JSONObject respuestaJSON = new JSONObject(respuestaStr);

            String mensaje = respuestaJSON.getString("mensaje");

            if(mensaje.equals("exito")) {
                JSONArray arreglo = respuestaJSON.getJSONArray("Alumnos");
                for (int i = 0; i < arreglo.length(); i++) {
                    JSONObject g = arreglo.getJSONObject(i);
                    Alumno alumno = new Alumno(g.getInt("alumnoId"), g.getString("codigo"),
                            g.getString("nombres"), g.getString("apellidoPaterno"),
                            g.getString("apellidoMaterno"), g.getString("email"),
                            g.getInt("estadoId"));
                    alumnos.add(alumno);
                }
                objetos.add(alumnos);
                arreglo = respuestaJSON.getJSONArray("Matriculas");
                for (int i = 0; i < arreglo.length(); i++) {
                    JSONObject g = arreglo.getJSONObject(i);
                    Matricula matricula = new Matricula(g.getInt("matriculaId"), g.getInt("alumnoId"),
                            g.getInt("cicloId"), g.getInt("cursoId"),
                            g.getInt("estadoId"), g.getInt("grupoId"),g.getInt("seccionId") );
                    matriculas.add(matricula);
                }
                objetos.add(matriculas);
            }
            else
            {
                objetos=null;
            }
        } catch (Exception e) {
            objetos=null;
            e.printStackTrace();
        }
        return objetos;

    }

    public void exportar(ArrayList<RegistroNota> registroNotas) {
        for(RegistroNota rn : registroNotas)
        {
            try {
                String posturl = "http://proyectodami-hoscanoa.rhcloud.com/grabarNotaAlumnoPorCursoEvaluacionNumero/";
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(posturl);

                List<NameValuePair> p = new ArrayList<>();
                p.add(new BasicNameValuePair("matriculaId", String.valueOf(rn.getMatriculaId())));
                p.add(new BasicNameValuePair("cursoEvaluacionId", String.valueOf(rn.getCursoEvaluacionId())));
                p.add(new BasicNameValuePair("calificacionId", String.valueOf(rn.getCalificacionId())));

                httppost.setEntity(new UrlEncodedFormEntity(p));
                HttpResponse resp = httpclient.execute(httppost);
                HttpEntity ent = resp.getEntity();

                String respuestaStr = EntityUtils.toString(resp.getEntity());
                JSONObject respuestaJSON = new JSONObject(respuestaStr);

                String mensaje = respuestaJSON.getString("mensaje");
                android.util.Log.i("mensaje", mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }



    public void eliminarDatos(Context context){
        try{
            DbHelper sql=new DbHelper(context);
            SQLiteDatabase db=sql.getWritableDatabase();
            db.delete("ESTADOS",null,null);
            db.delete("sqlite_sequence",null,null);
            db.delete("ALUMNOS",null,null);
            db.delete("CARRERAS_CURSOS",null,null);
            db.delete("CARRERAS",null,null);
            db.delete("CURSOS",null,null);
            db.delete("TIPO_AULA",null,null);
            db.delete("AULAS",null,null);
            db.delete("DIAS",null,null);
            db.delete("SECCIONES",null,null);
            db.delete("HORARIOS",null,null);
            db.delete("ALUMNOS_HORARIOS",null,null);
            db.delete("EVALUACIONES",null,null);
            db.delete("CURSOS_EVALUACIONES",null,null);
            db.delete("CALIFICACIONES",null,null);
            db.delete("REGISTRO_NOTAS",null,null);

        }catch (Exception e){
            System.out.println("Error de eliminar datos"+e);
        }

    }

/*
    public String  listarModalidad(Context context){
        String areglo="no hay nada   - ";
        ArrayList<ModalidadEstudio>  modalidadEstudios= new ArrayList<ModalidadEstudio>();
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarModalidadesEstudio/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuestaStr = EntityUtils.toString(resp.getEntity());

            JSONArray arreglo= new JSONArray(respuestaStr);
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            int r=0;

            ModalidadEstudioDAO modalidadEstudioDAO = factory.getModalidadEstudioDAO(context);

           for(int i=0;i<arreglo.length();i++)
            {
            String id,nombre,abr;
            JSONObject p = arreglo.getJSONObject(i);

            id=p.getString("pk");
            JSONObject fi=p.getJSONObject("fields");
            nombre=fi.getString("descripcion");
            abr=fi.getString("abreviatura");

              ModalidadEstudio m=  new ModalidadEstudio(Integer.parseInt(id),nombre,abr);
            //modalidadEstudios.add(new ModalidadEstudio(Integer.parseInt(id),nombre,abr));.






               r= modalidadEstudioDAO.insertar(m);

            }
        } catch (Exception e) {
            System.out.println("Error en lista modalidad "+e);
        }
        return areglo;
    }
*/
/*
    //traer ciclos
    public String  listarCiclos(Context context){
        String areglo="no hay nada   - ";
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarCiclos/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuestaStr = EntityUtils.toString(resp.getEntity());

            JSONArray arreglo= new JSONArray(respuestaStr);
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            int r=0;

            CicloDAO cicloDAO0= factory.getCicloDAO(context);

            for(int i=0;i<arreglo.length();i++)
            {
                String id,nombre,abr;
                JSONObject p = arreglo.getJSONObject(i);

                id=p.getString("pk");
                JSONObject fi=p.getJSONObject("fields");
                nombre=fi.getString("descripcion");


                Ciclo m=  new Ciclo(Integer.parseInt(id),nombre);

                r=cicloDAO0 .insertar(m);

            }
        } catch (Exception e) {
            System.out.println("Error al listar Ciclos "+e);
        }
        return areglo;
    }
*/
/*
    //traer cursos
    public String  listarCursos(Context context,String idModalidad,String  idCiclo, String idPro){
        String areglo="no hay nada   - ";
        int r=0;
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarCursosPorModalidadCicloProfesor/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            System.out.println("Modalidad  "+idModalidad+"  Ciclo  "+idCiclo+"  Profesor "+idPro);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("modalidadEstudioId", idModalidad));
            p.add(new BasicNameValuePair("cicloId", idCiclo));
            p.add(new BasicNameValuePair("profesorId", idPro));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuestaStr = EntityUtils.toString(resp.getEntity());

            JSONArray arreglo= new JSONArray(respuestaStr);
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);



            CursoDAO cursoDAO= factory.getCursoDAO(context);

            for(int i=0;i<arreglo.length();i++)
            {
                String id,nombre,cod;
                JSONObject p02 = arreglo.getJSONObject(i);

                id=p02.getString("pk");
                JSONObject fi=p02.getJSONObject("fields");
                nombre=fi.getString("descripcion");
                cod=fi.getString("codigo");


                Curso m=  new Curso(Integer.parseInt(id),cod,nombre);
                areglo+=m.toString();
                r+=cursoDAO.insertar(m);

            }
        } catch (Exception e) {
            System.out.println("Error al listar Cursos "+e);
        }
        return areglo+" cantidad "+r;
    }
*/
    /*
    //traer secciones
    public String  listarSecciones(Context context,String idModalidad,String  idCiclo, String idPro,String idCurso){
        String areglo="no hay nada   - ";
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarSeccionesPorModalidadCicloProfesorCurso/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("modalidadEstudioId", idModalidad));
            p.add(new BasicNameValuePair("cicloId", idCiclo));
            p.add(new BasicNameValuePair("profesorId", idPro));
            p.add(new BasicNameValuePair("cursoId", idCurso));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuestaStr = EntityUtils.toString(resp.getEntity());

            JSONArray arreglo= new JSONArray(respuestaStr);
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            int r=0;

            SeccionDAO seccionDAO= factory.getSeccionDAO(context);

            for(int i=0;i<arreglo.length();i++)
            {
                String id,nombre;
                JSONObject p02 = arreglo.getJSONObject(i);

                id=p02.getString("pk");
                JSONObject fi=p02.getJSONObject("fields");
                nombre=fi.getString("descripcion");


                Seccion m=  new Seccion(Integer.parseInt(id),nombre);

                r=seccionDAO.insertar(m);

            }
        } catch (Exception e) {
            System.out.println("Error al listar secciones "+e);
        }
        return areglo;
    }
*/
/*
    //traer grupos
    public String  listarGrupos(Context context,String idModalidad,String  idCiclo, String idPro,String idCurso,String seccionID){
        String areglo="no hay nada   - ";
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarGruposPorModalidadCicloProfesorCursoSeccion/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("modalidadEstudioId", idModalidad));
            p.add(new BasicNameValuePair("cicloId", idCiclo));
            p.add(new BasicNameValuePair("profesorId", idPro));
            p.add(new BasicNameValuePair("cursoId", idCurso));
            p.add(new BasicNameValuePair("seccionId", seccionID));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuestaStr = EntityUtils.toString(resp.getEntity());

            JSONArray arreglo= new JSONArray(respuestaStr);
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            int r=0;

            GrupoDAO grupoDAO= factory.getGrupoDAO(context);

            for(int i=0;i<arreglo.length();i++)
            {
                String id,nombre;
                JSONObject p02 = arreglo.getJSONObject(i);

                id=p02.getString("pk");
                JSONObject fi=p02.getJSONObject("fields");
                nombre=fi.getString("descripcion");


                Grupo m=  new Grupo(Integer.parseInt(id),nombre);

                r=grupoDAO.insertar(m);

            }
        } catch (Exception e) {
            System.out.println("Error al listar grupo "+e);
        }
        return areglo;
    }
*/
/*

    //traer listaTipoPrueba
    public String  listaTipoPrueba(Context context,String idCurso){
        String areglo="no hay nada   - ";
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarTipoDePruebasPorCurso/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("cursoId", idCurso));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuestaStr = EntityUtils.toString(resp.getEntity());

            JSONArray arreglo= new JSONArray(respuestaStr);
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            int r=0;

            EvaluacionDAO evaluacionDAO= factory.getEvaluacionDAO(context);

            for(int i=0;i<arreglo.length();i++)
            {
                String id,nombre;
                JSONObject p02 = arreglo.getJSONObject(i);

                id=p02.getString("pk");
                JSONObject fi=p02.getJSONObject("fields");
                nombre=fi.getString("descripcion");


                Evaluacion m=  new Evaluacion(Integer.parseInt(id),nombre);

                r=evaluacionDAO.insertar(m);

            }
        } catch (Exception e) {
            System.out.println("Error al listar grupo "+e);
        }
        return areglo;
    }
/*
*
* FALTA EL NUMERO DE PRUEBAS
*
* */


/*
    //traer listaTipoPrueba
    public String  listaAlumnos(Context context,String idCurso,String idSeccion,String idGrupo){
        String areglo="no hay nada   - ";
        try {
            String posturl = "http://proyectodami-hoscanoa.rhcloud.com/listarTipoDePruebasPorCurso/";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(posturl);

            List<NameValuePair> p = new ArrayList<>();
            p.add(new BasicNameValuePair("cursoId", idCurso));
            p.add(new BasicNameValuePair("seccionId", idSeccion));
            p.add(new BasicNameValuePair("grupoId", idGrupo));

            httppost.setEntity(new UrlEncodedFormEntity(p));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String respuestaStr = EntityUtils.toString(resp.getEntity());

            JSONArray arreglo= new JSONArray(respuestaStr);
            Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
            int r=0;

            AlumnoDAO alumnoDAO= factory.getAlumnoDAO(context);
            String id,nombre,aPaterno,aMaterno,codigo,estado,email;

            for(int i=0;i<arreglo.length();i++)
            {

                JSONObject p02 = arreglo.getJSONObject(i);

                id=p02.getString("pk");
                JSONObject fi=p02.getJSONObject("fields");
                nombre=fi.getString("nombres");
                aPaterno=fi.getString("apellidoPaterno");
                aMaterno=fi.getString("apellidoMaterno");
                codigo=fi.getString("codigo");
                estado=fi.getString("estado");
                email=fi.getString("email");




                Alumno m=  new Alumno(Integer.parseInt(id),codigo,nombre,aPaterno,aMaterno,email,Integer.parseInt(estado));

                r=alumnoDAO.insertar(m);

            }
        } catch (Exception e) {
            System.out.println("Error al listar grupo "+e);
        }
        return areglo;
    }
*/
}
