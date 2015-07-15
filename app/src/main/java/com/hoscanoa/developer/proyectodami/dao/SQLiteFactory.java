package com.hoscanoa.developer.proyectodami.dao;

import android.content.Context;

import com.hoscanoa.developer.proyectodami.dao.alumno.AlumnoDAO;
import com.hoscanoa.developer.proyectodami.dao.alumno.SQLiteAlumnoDAO;
import com.hoscanoa.developer.proyectodami.dao.alumnoHorario.AlumnoHorarioDAO;
import com.hoscanoa.developer.proyectodami.dao.alumnoHorario.SQLiteAlumnoHorarioDAO;
import com.hoscanoa.developer.proyectodami.dao.aula.AulaDAO;
import com.hoscanoa.developer.proyectodami.dao.aula.SQLiteAulaDAO;
import com.hoscanoa.developer.proyectodami.dao.calificacion.CalificacionDAO;
import com.hoscanoa.developer.proyectodami.dao.calificacion.SQLiteCalificacionDAO;
import com.hoscanoa.developer.proyectodami.dao.carerra.CarerraDAO;
import com.hoscanoa.developer.proyectodami.dao.carerra.SQLiteCarerraDAO;
import com.hoscanoa.developer.proyectodami.dao.cargaDocente.CargaDocenteDAO;
import com.hoscanoa.developer.proyectodami.dao.cargaDocente.SQLiteCargaDocenteDAO;
import com.hoscanoa.developer.proyectodami.dao.carreraCurso.CarreraCursoDAO;
import com.hoscanoa.developer.proyectodami.dao.carreraCurso.SQLiteCarreraCursoDAO;
import com.hoscanoa.developer.proyectodami.dao.ciclo.CicloDAO;
import com.hoscanoa.developer.proyectodami.dao.ciclo.SQLiteCicloDAO;
import com.hoscanoa.developer.proyectodami.dao.curso.CursoDAO;
import com.hoscanoa.developer.proyectodami.dao.curso.SQLiteCursoDAO;
import com.hoscanoa.developer.proyectodami.dao.cursoEvaluacion.CursoEvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.cursoEvaluacion.SQLiteCursoEvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.dia.DiaDAO;
import com.hoscanoa.developer.proyectodami.dao.dia.SQLiteDiaDAO;
import com.hoscanoa.developer.proyectodami.dao.estado.EstadoDAO;
import com.hoscanoa.developer.proyectodami.dao.estado.SQLiteEstadoDAO;
import com.hoscanoa.developer.proyectodami.dao.evaluacion.EvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.evaluacion.SQLiteEvaluacionDAO;
import com.hoscanoa.developer.proyectodami.dao.grupo.GrupoDAO;
import com.hoscanoa.developer.proyectodami.dao.grupo.SQLiteGrupoDAO;
import com.hoscanoa.developer.proyectodami.dao.historial.HistorialDAO;
import com.hoscanoa.developer.proyectodami.dao.historial.SQLiteHistorialDAO;
import com.hoscanoa.developer.proyectodami.dao.horario.HorarioDAO;
import com.hoscanoa.developer.proyectodami.dao.horario.SQLiteHorarioDAO;
import com.hoscanoa.developer.proyectodami.dao.matricula.MatriculaDAO;
import com.hoscanoa.developer.proyectodami.dao.matricula.SQLiteMatriculaDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.ModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.dao.modalidadEstudio.SQLiteModalidadEstudioDAO;
import com.hoscanoa.developer.proyectodami.dao.profesor.ProfesorDAO;
import com.hoscanoa.developer.proyectodami.dao.profesor.SQLiteProfesorDAO;
import com.hoscanoa.developer.proyectodami.dao.registroNota.RegistroNotaDAO;
import com.hoscanoa.developer.proyectodami.dao.registroNota.SQLiteRegistroNotaDAO;
import com.hoscanoa.developer.proyectodami.dao.seccion.SQLiteSeccionDAO;
import com.hoscanoa.developer.proyectodami.dao.seccion.SeccionDAO;
import com.hoscanoa.developer.proyectodami.dao.tipoAula.SQLiteTipoAulaDAO;
import com.hoscanoa.developer.proyectodami.dao.tipoAula.TipoAulaDAO;

/**
 * Created by Hernan on 17/05/2015.
 */
public class SQLiteFactory extends Factory {

    @Override
    public AlumnoDAO getAlumnoDAO(Context context) {
        return new SQLiteAlumnoDAO(context);
    }

    @Override
    public AlumnoHorarioDAO getAlumnoHorarioDAO(Context context) {
        return new SQLiteAlumnoHorarioDAO(context);
    }

    @Override
    public AulaDAO getAulaDAO(Context context) {
        return new SQLiteAulaDAO(context);
    }

    @Override
    public CalificacionDAO getCalificacionDAO(Context context) {
        return new SQLiteCalificacionDAO(context);
    }

    @Override
    public CarerraDAO getCarerraDAO(Context context) {
        return new SQLiteCarerraDAO(context);
    }

    @Override
    public CursoDAO getCursoDAO(Context context) {
        return new SQLiteCursoDAO(context);
    }

    @Override
    public CursoEvaluacionDAO getCursoEvaluacionDAO(Context context) {
        return new SQLiteCursoEvaluacionDAO(context);
    }

    @Override
    public DiaDAO getDiaDAO(Context context) {
        return new SQLiteDiaDAO(context);
    }

    @Override
    public EstadoDAO getEstadoDAO(Context context) {
        return new SQLiteEstadoDAO(context);
    }

    @Override
    public EvaluacionDAO getEvaluacionDAO(Context context) {
        return new SQLiteEvaluacionDAO(context);
    }

    @Override
    public HorarioDAO getHorarioDAO(Context context) {
        return new SQLiteHorarioDAO(context);
    }

    @Override
    public ProfesorDAO getProfesorDAO(Context context) {
        return new SQLiteProfesorDAO(context);
    }

    @Override
    public RegistroNotaDAO getRegistroNotaDAO(Context context) {
        return new SQLiteRegistroNotaDAO(context);
    }

    @Override
    public SeccionDAO getSeccionDAO(Context context) {
        return new SQLiteSeccionDAO(context);
    }

    @Override
    public TipoAulaDAO getTipoAulaDAO(Context context) {
        return new SQLiteTipoAulaDAO(context);
    }

    @Override
    public ModalidadEstudioDAO getModalidadEstudioDAO(Context context) {
        return new SQLiteModalidadEstudioDAO(context);
    }

    @Override
    public CicloDAO getCicloDAO(Context context) {
        return new SQLiteCicloDAO(context);
    }

    @Override
    public CargaDocenteDAO getCargaDocenteDAO(Context context) {
        return new SQLiteCargaDocenteDAO(context);
    }

    @Override
    public MatriculaDAO getMatriculaDAO(Context context) {
        return new SQLiteMatriculaDAO(context);
    }

    @Override
    public GrupoDAO getGrupoDAO(Context context) {
        return new SQLiteGrupoDAO(context);
    }

    @Override
    public HistorialDAO getHistorialDAO(Context context) {
        return new SQLiteHistorialDAO(context);
    }

    @Override
    public CarreraCursoDAO getCarreraCursoDAO(Context context) {
        return new SQLiteCarreraCursoDAO(context);
    }
}
