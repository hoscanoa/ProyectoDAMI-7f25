package com.hoscanoa.developer.proyectodami.servicio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hoscanoa.developer.proyectodami.beans.Alumno;
import com.hoscanoa.developer.proyectodami.beans.Ciclo;
import com.hoscanoa.developer.proyectodami.beans.Curso;
import com.hoscanoa.developer.proyectodami.beans.Evaluacion;
import com.hoscanoa.developer.proyectodami.beans.Grupo;
import com.hoscanoa.developer.proyectodami.beans.ModalidadEstudio;
import com.hoscanoa.developer.proyectodami.beans.Profesor;
import com.hoscanoa.developer.proyectodami.beans.Seccion;
import com.hoscanoa.developer.proyectodami.conexion.DbHelper;
import com.hoscanoa.developer.proyectodami.dao.Factory;
import com.hoscanoa.developer.proyectodami.dao.alumno.AlumnoDAO;
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


    public boolean verificarDatos(Context context){

        String respuesta=null;
        boolean v=false;

        Factory factory = Factory.getFactory(Factory.TIPO_SQLITE);
        ModalidadEstudioDAO modalidadEstudioDAO = factory.getModalidadEstudioDAO(context);
        ArrayList<ModalidadEstudio> lista = modalidadEstudioDAO.listar();

        System.out.println("Tamaño de modalidad "+lista.size());

        CicloDAO cicloDAO = factory.getCicloDAO(context);
        ArrayList<Ciclo> listaCiclo = cicloDAO.listar();
        System.out.println("Tamaño de ciclos "+listaCiclo.size());


        DbHelper sql=new DbHelper(context);
        SQLiteDatabase db=sql.getWritableDatabase();
        int el=(int)db.delete("MODALIDADES_ESTUDIOS",null,null);
        int el02=(int)db.delete("CICLOS",null,null);

        System.out.println("elimine de modalidad "+el);
        System.out.println("elimine de ciclo "+el02);



        ArrayList<ModalidadEstudio> lista05 = modalidadEstudioDAO.listar();

        if(lista05.size()>0){
          /*  DbHelper sql=new DbHelper(context);
            SQLiteDatabase db=sql.getWritableDatabase();
            int el=(int)db.delete("MODALIDADES_ESTUDIOS",null,null);
            respuesta+="  :  se elimina "+el+" filas";*/
            v=true;
                respuesta="tiene datos";
        }/*else{
           // respuesta="no habia datos";
            respuesta="lo de listar "+listarModalidad();
        }*/


        return v;
    }

    public boolean eliminarDatos(Context context){

        boolean ver=false;
        try {

            DbHelper sql=new DbHelper(context);
            SQLiteDatabase db=sql.getWritableDatabase();

            db.delete("ESTADOS",null,null);
            db.delete("sqlite_sequence",null,null);
            db.delete("ALUMNOS",null,null);
            db.delete("PROFESORES",null,null);
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
            ver=true;
        }catch (Exception e){
            ver=false;
            System.out.println("Error de eliminar datos");
        }

        return ver;
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
