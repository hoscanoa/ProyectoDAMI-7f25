package com.hoscanoa.developer.proyectodami.dao;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.dao.alumno.AlumnoDAO;
import com.hoscanoa.developer.proyectodami.dao.alumnoHorario.AlumnoHorarioDAO;
import com.hoscanoa.developer.proyectodami.dao.aula.AulaDAO;
import com.hoscanoa.developer.proyectodami.dao.calificacion.CalificacionDAO;
import com.hoscanoa.developer.proyectodami.dao.carerra.CarerraDAO;
import com.hoscanoa.developer.proyectodami.dao.cargaDocente.CargaDocenteDAO;
import com.hoscanoa.developer.proyectodami.dao.ciclo.CicloDAO;
import com.hoscanoa.developer.proyectodami.dao.curso.CursoDAO;
import com.hoscanoa.developer.proyectodami.dao.cursoEvaluacion.CursoEvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.dia.DiaDAO;
import com.hoscanoa.developer.proyectodami.dao.estado.EstadoDAO;
import com.hoscanoa.developer.proyectodami.dao.evaluacion.EvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.grupo.GrupoDAO;
import com.hoscanoa.developer.proyectodami.dao.horario.HorarioDAO;
import com.hoscanoa.developer.proyectodami.dao.matricula.MatriculaDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.ModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.dao.profesor.ProfesorDAO;
import com.hoscanoa.developer.proyectodami.dao.registroNota.RegistroNotaDAO;
import com.hoscanoa.developer.proyectodami.dao.seccion.SeccionDAO;
import com.hoscanoa.developer.proyectodami.dao.tipoAula.TipoAulaDAO;

/**
 * Created by Hernan on 17/05/2015.
 */
public abstract class Factory {
    public static final int TIPO_SQLITE = 1;

    public abstract AlumnoDAO getAlumnoDAO(Context context);
    public abstract AlumnoHorarioDAO getAlumnoHorarioDAO(Context context);
    public abstract AulaDAO getAulaDAO(Context context);
    public abstract CalificacionDAO getCalificacionDAO(Context context);
    public abstract CarerraDAO getCarerraDAO(Context context);
    public abstract CursoDAO getCursoDAO(Context context);
    public abstract CursoEvaluacionDAO getCursoEvaluacionDAO(Context context);
    public abstract DiaDAO getDiaDAO(Context context);
    public abstract EstadoDAO getEstadoDAO(Context context);
    public abstract EvaluacionDAO getEvaluacionDAO(Context context);
    public abstract HorarioDAO getHorarioDAO(Context context);
    public abstract ProfesorDAO getProfesorDAO(Context context);
    public abstract RegistroNotaDAO getRegistroNotaDAO(Context context);
    public abstract SeccionDAO getSeccionDAO(Context context);
    public abstract TipoAulaDAO getTipoAulaDAO(Context context);
    public abstract ModalidadEstudioDAO getModalidadEstudioDAO(Context context);
    public abstract CicloDAO getCicloDAO(Context context);
    public abstract CargaDocenteDAO getCargaDocenteDAO(Context context);
    public abstract MatriculaDAO getMatriculaDAO(Context context);
    public abstract GrupoDAO getGrupoDAO(Context context);

    public static Factory getFactory(int tipo) {
        switch (tipo) {
            case TIPO_SQLITE :
                return new SQLiteFactory();
            default :
                return null;
        }
    }
}
