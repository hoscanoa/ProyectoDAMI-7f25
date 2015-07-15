package com.hoscanoa.developer.proyectodami.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hernan on 18/05/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "damiDb.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GRUPOS);
        db.execSQL(CREATE_TABLE_ESTADOS);
        db.execSQL(CREATE_TABLE_MODALIDADES_ESTUDIOS);
        db.execSQL(CREATE_TABLE_ALUMNOS);
        db.execSQL(CREATE_TABLE_PROFESORES);
        db.execSQL(CREATE_TABLE_CARRERAS);
        db.execSQL(CREATE_TABLE_CURSOS);
        db.execSQL(CREATE_TABLE_CARRERAS_CURSOS);
        db.execSQL(CREATE_TABLE_TIPO_AULA);
        db.execSQL(CREATE_TABLE_AULAS);
        db.execSQL(CREATE_TABLE_DIAS);
        db.execSQL(CREATE_TABLE_SECCIONES);
        db.execSQL(CREATE_TABLE_HORARIOS);
        db.execSQL(CREATE_TABLE_ALUMNOS_HORARIOS);
        db.execSQL(CREATE_TABLE_EVALUACIONES);
        db.execSQL(CREATE_TABLE_CURSOS_EVALUACIONES);
        db.execSQL(CREATE_TABLE_CALIFICACIONES);
        db.execSQL(CREATE_TABLE_REGISTRO_NOTAS);

        db.execSQL(CREATE_TABLE_CICLOS);
        db.execSQL(CREATE_TABLE_CARGA_DOCENTE);
        db.execSQL(CREATE_TABLE_MATRICULA);
            db.execSQL(CREATE_TABLE_HISTORIAL);

        db.execSQL(INSERTS_GRUPOS);
        db.execSQL(INSERTS_ESTADOS);
        db.execSQL(INSERTS_MODALIDADES_ESTUDIOS);
        db.execSQL(INSERTS_ALUMNOS);
        db.execSQL(INSERTS_PROFESORES);
        db.execSQL(INSERTS_DIAS);
        db.execSQL(INSERTS_CURSOS);
        db.execSQL(INSERTS_CARRERAS);
        db.execSQL(INSERTS_TIPO_AULA);
        db.execSQL(INSERTS_SECCIONES);
        db.execSQL(INSERTS_EVALUACIONES);
        db.execSQL(INSERTS_CARRERAS_CURSOS);
        db.execSQL(INSERTS_CARGA_DOCENTE);
        db.execSQL(INSERTS_HORARIO);
        db.execSQL(INSERTS_CURSOS_EVALUACIONES);
        db.execSQL(INSERTS_MATRICULA1);
        db.execSQL(INSERTS_MATRICULA2);
        db.execSQL(INSERTS_MATRICULA3);
        db.execSQL(INSERTS_MATRICULA4);

        for (int i = 2014; i <= 2030; i++) {
            db.execSQL("INSERT INTO CICLOS(descripcion) VALUES(?),(?);", new String[]{"" + i + "-I", "" + i + "-II"});
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(DROP_DATABASE);
        //onCreate(db);
    }

    private static final String DROP_DATABASE = "drop table ESTADOS;\n" +
            "drop table MODALIDADES_ESTUDIOS;\n" +
            "drop table sqlite_sequence;\n" +
            "drop table ALUMNOS;\n" +
            "drop table PROFESORES;\n" +
            "drop table CARRERAS;\n" +
            "drop table CURSOS;\n" +
            "drop table CARRERAS_CURSOS;\n" +
            "drop table TIPO_AULA;\n" +
            "drop table AULAS;\n" +
            "drop table DIAS;\n" +
            "drop table SECCIONES;\n" +
            "drop table HORARIOS;\n" +
            "drop table ALUMNOS_HORARIOS;\n" +
            "drop table EVALUACIONES;\n" +
            "drop table CURSOS_EVALUACIONES;\n" +
            "drop table CALIFICACIONES;\n" +
            "drop table REGISTRO_NOTAS;";

    private static final String CREATE_PRAGMA = "";

    private static final String CREATE_TABLE_GRUPOS = "CREATE TABLE GRUPOS (\n" +
            "grupoId INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,\n" +
            "descripcion CHAR(2) NOT NULL UNIQUE\n" +
            ");";

    private static final String CREATE_TABLE_ESTADOS = "CREATE TABLE ESTADOS (\n" +
            "  estadoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  descripcion VARCHAR(45) NULL\n" +
            ");";

    private static final String CREATE_TABLE_MODALIDADES_ESTUDIOS = "CREATE TABLE MODALIDADES_ESTUDIOS(\n" +
            "    modalidadEstudioId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "    descripcion VARCHAR(45) NOT NULL UNIQUE,\n" +
            "    abreviatura CHAR(2) NOT NULL UNIQUE\n" +
            ");";

    private static final String CREATE_TABLE_ALUMNOS = CREATE_PRAGMA + " " + "CREATE TABLE ALUMNOS (\n" +
            "  alumnoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  codigo CHAR(10) NOT NULL UNIQUE,\n" +
            "  nombres VARCHAR(20) NOT NULL,\n" +
            "  apellidoPaterno VARCHAR(20) NOT NULL,\n" +
            "  apellidoMaterno VARCHAR(20) NOT NULL,\n" +
            "  email CHAR(26) NOT NULL,\n" +
            "  estadoId INTEGER NOT NULL REFERENCES ESTADOS(estadoId)\n" +
            ");";

    private static final String CREATE_TABLE_PROFESORES = "CREATE TABLE PROFESORES (\n" +
            "  profesorId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  nombres VARCHAR(20) NOT NULL,\n" +
            "  apellidoPaterno VARCHAR(20) NOT NULL,\n" +
            "  apellidoMaterno VARCHAR(20) NOT NULL,\n" +
            "  email VARCHAR(40) NOT NULL UNIQUE,\n" +
            "  username VARCHAR(15) NOT NULL UNIQUE,\n" +
            "  password VARCHAR(15) NOT NULL\n" +
            ");";

    private static final String CREATE_TABLE_CARRERAS = "CREATE TABLE CARRERAS (\n" +
            "  carreraId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  descripcion VARCHAR(45) NOT NULL UNIQUE,\n" +
            "  modalidadEstudioId INTEGER NOT NULL REFERENCES MODALIDADES_ESTUDIOS(modalidadEstudioId)\n" +
            ");";

    private static final String CREATE_TABLE_CURSOS = CREATE_PRAGMA + " " + "CREATE TABLE CURSOS (\n" +
            "  cursoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  codigo CHAR(4) NOT NULL,\n" +
            "  descripcion VARCHAR(45) NOT NULL UNIQUE\n" +
            ");";

    private static final String CREATE_TABLE_CARRERAS_CURSOS = CREATE_PRAGMA + " " + "CREATE TABLE CARRERAS_CURSOS (\n" +
            "  carreraCursoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  carreraId INTEGER NOT NULL REFERENCES CARRERAS(carreraId),\n" +
            "  cursoId INTEGER NOT NULL  REFERENCES CURSOS(cursoId),\n" +
            "  creditos INTEGER NOT NULL\n" +
            ");";

    private static final String CREATE_TABLE_TIPO_AULA = "CREATE TABLE  TIPO_AULA (\n" +
            "  tipoAulaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  descripcion VARCHAR(20) NULL\n" +
            ");";

    private static final String CREATE_TABLE_AULAS = CREATE_PRAGMA + " " + "CREATE TABLE AULAS (\n" +
            "  aulaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  tipoAulaId INTEGER NOT NULL REFERENCES TIPO_AULA(tipoAulaId)\n" +
            ");";

    private static final String CREATE_TABLE_DIAS = "CREATE TABLE DIAS (\n" +
            "  diaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  descripcion VARCHAR(15) NULL\n" +
            ");";

    private static final String CREATE_TABLE_SECCIONES = "CREATE TABLE SECCIONES (\n" +
            "  seccionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  descripcion CHAR(4) NULL\n" +
            ");";

    private static final String CREATE_TABLE_CICLOS = "CREATE TABLE CICLOS (\n" +
            "\tcicloId INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,\n" +
            "\tdescripcion VARCHAR(7) NOT NULL UNIQUE\n" +
            ");";

    private static final String CREATE_TABLE_HORARIOS = CREATE_PRAGMA + " " + "CREATE TABLE HORARIOS (\n" +
            "  horarioId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),\n" +
            "  profesorId INTEGER NOT NULL REFERENCES PROFESORES(profesorId),\n" +
            "  aulaId INTEGER NOT NULL REFERENCES AULAS(aulaId),\n" +
            "  horaInicio CHAR(6) NOT NULL,\n" +
            "  horaFin CHAR(6) NOT NULL,\n" +
            "  diaId INTEGER NOT NULL REFERENCES DIAS(diaId),\n" +
            "  seccionId INTEGER NOT NULL REFERENCES SECCIONES(seccionId),\n" +
            "  grupo CHAR(2) NOT NULL,\n" +
            "  cicloId INTEGER NOT NULL REFERENCES CICLOS(cicloId)\n" +
            ");";

    private static final String CREATE_TABLE_ALUMNOS_HORARIOS = CREATE_PRAGMA + " " + "CREATE TABLE ALUMNOS_HORARIOS (\n" +
            "  alumnoHorarioId INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  alumnoId INTEGER NOT NULL REFERENCES ALUMNOS(alumnoId),\n" +
            "  horarioId INTEGER NOT NULL REFERENCES HORARIOS(horarioId)\n" +
            ");";

    private static final String CREATE_TABLE_EVALUACIONES = "CREATE TABLE EVALUACIONES (\n" +
            "  evaluacionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  descripcion VARCHAR(45) NOT NULL\n" +
            ");";

    private static final String CREATE_TABLE_CURSOS_EVALUACIONES = CREATE_PRAGMA + " " + "CREATE TABLE CURSOS_EVALUACIONES (\n" +
            "  cursoEvaluacionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),\n" +
            "  evaluacionId INTEGER NOT NULL REFERENCES EVALUACIONES(evaluacionId),\n" +
            "  numero INTEGER NOT NULL,\n" +
            "  porcentaje INTEGER NOT NULL\n" +
            ");";

    private static final String CREATE_TABLE_CALIFICACIONES = "CREATE TABLE CALIFICACIONES (\n" +
            "  calificacionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  descripcion VARCHAR(10) NULL,\n" +
            "  nota INTEGER NOT NULL\n" +
            ");";

    private static final String CREATE_TABLE_REGISTRO_NOTAS = CREATE_PRAGMA + " " + "CREATE TABLE REGISTRO_NOTAS (\n" +
            "  registroNotaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "  alumnoId INTEGER NOT NULL REFERENCES ALUMNOS(alumnoId),\n" +
            "  cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),\n" +
            "  evaluacionId INTEGER NOT NULL REFERENCES EVALUACIONES(evaluacionId),\n" +
            "  calificacionesId INTEGER NOT NULL REFERENCES CALIFICACIONES(calificacionesId)\n" +
            ");";

    private static final String CREATE_TABLE_CARGA_DOCENTE = "CREATE TABLE CARGA_DOCENTE (\n" +
            "cargaDocenteId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),\n" +
            "profesorId INTEGER NOT NULL REFERENCES PROFESORES(profesorId),\n" +
            "cicloId INTEGER NOT NULL REFERENCES CICLOS(cicloId),\n" +
            "seccionId INTEGER NOT NULL REFERENCES SECCIONES(seccionId),\n" +
            "grupoId INTEGER NOT NULL REFERENCES GRUPOS(grupoId)\n" +
            ");";

    private static final String CREATE_TABLE_MATRICULA = "CREATE TABLE MATRICULA (\n" +
            "matriculaId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "alumnoId INTEGER NOT NULL REFERENCES ALUMNOS(alumnoId),\n" +
            "cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId), \n" +
            "grupoId INTEGER NOT NULL REFERENCES GRUPOS(grupoId), \n" +
            "cicloId INTEGER NOT NULL REFERENCES CICLOS(cicloId),\n" +
            "seccionId INTEGER NOT NULL REFERENCES SECCIONES(seccionId),\n" +
            "estadoId INTEGER NOT NULL REFERENCES ESTADOS(estadoId)\n" +
            ");";

    private static final String CREATE_TABLE_HISTORIAL = "CREATE TABLE HISTORIAL (\n" +
            "historialId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "profesorId INTEGER NOT NULL REFERENCES PROFESORES(profesorId),\n" +
            ");";


    private static final String INSERTS_GRUPOS = "insert into GRUPOS values\n" +
            "(1, '01'),\n" +
            "(2, '02')\n" +
            ";";

    private static final String INSERTS_ESTADOS = "insert into ESTADOS values\n" +
            "(1, 'MATRICULA REGULAR'),\n" +
            "(2, 'RETIRO TEMPORAL'),\n" +
            "(3, 'DESAPROBADO POR INASISTENCIA');";

    private static final String INSERTS_MODALIDADES_ESTUDIOS = "insert into MODALIDADES_ESTUDIOS values\n" +
            "(1, 'CARRERAS TÉCNICAS','AC'),\n" +
            "(2, 'PROGRAMA DE ADELANTOS','AD'),\n" +
            "(3, 'DIPLOMADOS CIBERTEC','DC');";

    private static final String INSERTS_PROFESORES = "INSERT INTO PROFESORES VALUES\n" +
            "(1,'Chris','Reyes','Powell','pcpowell0@cibertec.edu.pe','cpowell','123'),\n" +
            "(2,'Maria','Boyd','Berry','pmberry1@cibertec.edu.pe','mberry1','FzsZ54d'),\n" +
            "(3,'Jimmy','Roberts','Cruz','pjcruz2@cibertec.edu.pe','jcruz2','XKeeVx'),\n" +
            "(4,'Bruce','Porter','Mills','pbmills3@cibertec.edu.pe','bmills3','agiU4VP'),\n" +
            "(5,'Judy','Mcdonald','Gomez','pjgomez4@cibertec.edu.pe','jgomez4','6lgdUEY'),\n" +
            "(6,'Catherine','Rose','Perkins','pcperkins5@cibertec.edu.pe','cperkins5','LmV9ASAdkG'),\n" +
            "(7,'Earl','Hawkins','Sanchez','pesanchez6@cibertec.edu.pe','esanchez6','398dIPt'),\n" +
            "(8,'Frances','Harris','Sims','pfsims7@cibertec.edu.pe','fsims7','OYy56z'),\n" +
            "(9,'Kelly','Pierce','George','pkgeorge8@cibertec.edu.pe','kgeorge8','f1q5YPEeW'),\n" +
            "(10,'Jean','Freeman','Washington','pjwashington9@cibertec.edu.pe','jwashington9','AGWlTc0nkI'),\n" +
            "(11,'Nicole','Lawrence','Gordon','pngordona@cibertec.edu.pe','ngordona','KGEZWKkW'),\n" +
            "(12,'Stephen','Lane','Burns','psburnsb@cibertec.edu.pe','sburnsb','OKIQh6omIyM'),\n" +
            "(13,'John','Burns','Pierce','pjpiercec@cibertec.edu.pe','jpiercec','C7ylQk1EEgA'),\n" +
            "(14,'Kenneth','Gutierrez','Freeman','pkfreemand@cibertec.edu.pe','kfreemand','pZGwCsook7o'),\n" +
            "(15,'Terry','Bowman','Diaz','ptdiaze@cibertec.edu.pe','tdiaze','WWU3XtUWPjb'),\n" +
            "(16,'Stephanie','Boyd','Mason','psmasonf@cibertec.edu.pe','smasonf','Nhljh64j8r'),\n" +
            "(17,'Evelyn','Ferguson','Pierce','pepierceg@cibertec.edu.pe','epierceg','agzmHNYu'),\n" +
            "(18,'Ashley','Robertson','Freeman','pafreemanh@cibertec.edu.pe','afreemanh','NNrswp'),\n" +
            "(19,'Nancy','Bowman','Rodriguez','pnrodriguezi@cibertec.edu.pe','nrodriguezi','d7cBtbC'),\n" +
            "(20,'Catherine','Owens','Fisher','pcfisherj@cibertec.edu.pe','cfisherj','LrARaoB7l7');";

    private static final String INSERTS_ALUMNOS = "INSERT INTO ALUMNOS VALUES(1,'i201202089','Denise','Greene','Riley','i201202089@cibertec.edu.pe',1),\n" +
            "(2,'i201319324','Irene','Fowler','Stevens','i201319324@cibertec.edu.pe',1),\n" +
            "(3,'i201335298','Jessica','Carpenter','Cooper','i201335298@cibertec.edu.pe',1),\n" +
            "(4,'i201285209','Ruth','Black','Allen','i201285209@cibertec.edu.pe',1),\n" +
            "(5,'i201221843','Martha','Riley','Cruz','i201221843@cibertec.edu.pe',1),\n" +
            "(6,'i201279505','Debra','Wood','Sullivan','i201279505@cibertec.edu.pe',1),\n" +
            "(7,'i201340354','Dorothy','Palmer','Hayes','i201340354@cibertec.edu.pe',1),\n" +
            "(8,'i201263082','Nancy','Owens','Coleman','i201263082@cibertec.edu.pe',1),\n" +
            "(9,'i201290463','Jacqueline','Perry','Richardson','i201290463@cibertec.edu.pe',1),\n" +
            "(10,'i201236590','Dorothy','Reyes','Ford','i201236590@cibertec.edu.pe',1),\n" +
            "(11,'i201384668','Mildred','West','Ortiz','i201384668@cibertec.edu.pe',1),\n" +
            "(12,'i201281081','Alice','Ruiz','Harvey','i201281081@cibertec.edu.pe',1),\n" +
            "(13,'i201213442','Elizabeth','Kelley','Chavez','i201213442@cibertec.edu.pe',1),\n" +
            "(14,'i201216648','Dorothy','Holmes','Burke','i201216648@cibertec.edu.pe',1),\n" +
            "(15,'i201273827','Evelyn','Flores','Bishop','i201273827@cibertec.edu.pe',1),\n" +
            "(16,'i201297944','Nicole','Oliver','Collins','i201297944@cibertec.edu.pe',1),\n" +
            "(17,'i201325771','Christine','Hall','Miller','i201325771@cibertec.edu.pe',1),\n" +
            "(18,'i201358633','Anne','Perry','Cook','i201358633@cibertec.edu.pe',1),\n" +
            "(19,'i201330943','Heather','Bishop','James','i201330943@cibertec.edu.pe',1),\n" +
            "(20,'i201245154','Evelyn','Marshall','Olson','i201245154@cibertec.edu.pe',1),\n" +
            "(21,'i201332863','Christine','Hunt','Foster','i201332863@cibertec.edu.pe',1),\n" +
            "(22,'i201219903','Judy','Williams','Johnson','i201219903@cibertec.edu.pe',1),\n" +
            "(23,'i201334475','Gloria','Porter','Bell','i201334475@cibertec.edu.pe',1),\n" +
            "(24,'i201316600','Kathy','Hill','Rose','i201316600@cibertec.edu.pe',1),\n" +
            "(25,'i201396717','Joyce','Frazier','Webb','i201396717@cibertec.edu.pe',1),\n" +
            "(26,'i201256745','Diana','Riley','Mccoy','i201256745@cibertec.edu.pe',1),\n" +
            "(27,'i201397485','Mildred','Ruiz','Hansen','i201397485@cibertec.edu.pe',1),\n" +
            "(28,'i201299434','Jennifer','Allen','Hudson','i201299434@cibertec.edu.pe',1),\n" +
            "(29,'i201343335','Debra','Cooper','Garrett','i201343335@cibertec.edu.pe',1),\n" +
            "(30,'i201259274','Cynthia','Sullivan','Palmer','i201259274@cibertec.edu.pe',1),\n" +
            "(31,'i201243831','Anne','Marshall','Jordan','i201243831@cibertec.edu.pe',1),\n" +
            "(32,'i201324292','Paula','Ross','Butler','i201324292@cibertec.edu.pe',1),\n" +
            "(33,'i201201100','Linda','Long','Thompson','i201201100@cibertec.edu.pe',1),\n" +
            "(34,'i201270664','Lillian','Gordon','Grant','i201270664@cibertec.edu.pe',1),\n" +
            "(35,'i201339537','Evelyn','Wood','Freeman','i201339537@cibertec.edu.pe',1),\n" +
            "(36,'i201319003','Amy','Hunter','Porter','i201319003@cibertec.edu.pe',1),\n" +
            "(37,'i201382741','Phyllis','Evans','Hill','i201382741@cibertec.edu.pe',1),\n" +
            "(38,'i201220231','Sandra','White','Martinez','i201220231@cibertec.edu.pe',1),\n" +
            "(39,'i201253148','Mildred','Patterson','James','i201253148@cibertec.edu.pe',1),\n" +
            "(40,'i201232370','Kimberly','Crawford','Wagner','i201232370@cibertec.edu.pe',1),\n" +
            "(41,'i201360827','Catherine','Brooks','Woods','i201360827@cibertec.edu.pe',1),\n" +
            "(42,'i201379269','Ann','Sanders','Fernandez','i201379269@cibertec.edu.pe',1),\n" +
            "(43,'i201298189','Katherine','Morris','Roberts','i201298189@cibertec.edu.pe',1),\n" +
            "(44,'i201344279','Kelly','Harvey','Bowman','i201344279@cibertec.edu.pe',1),\n" +
            "(45,'i201207187','Shirley','Carroll','Alexander','i201207187@cibertec.edu.pe',1),\n" +
            "(46,'i201387560','Sharon','Graham','Alvarez','i201387560@cibertec.edu.pe',1),\n" +
            "(47,'i201247030','Marie','George','Gibson','i201247030@cibertec.edu.pe',1),\n" +
            "(48,'i201399442','Diana','Bennett','Dixon','i201399442@cibertec.edu.pe',1),\n" +
            "(49,'i201240467','Rachel','Jenkins','Hernandez','i201240467@cibertec.edu.pe',1),\n" +
            "(50,'i201329116','Maria','Larson','Morrison','i201329116@cibertec.edu.pe',1),\n" +
            "(51,'i201242819','Rachel','Alexander','Clark','i201242819@cibertec.edu.pe',1),\n" +
            "(52,'i201368273','Brenda','Burton','Dixon','i201368273@cibertec.edu.pe',1),\n" +
            "(53,'i201363948','Michelle','Chapman','Austin','i201363948@cibertec.edu.pe',1),\n" +
            "(54,'i201393636','Karen','Henderson','Peterson','i201393636@cibertec.edu.pe',1),\n" +
            "(55,'i201287617','Judith','Cruz','Hayes','i201287617@cibertec.edu.pe',1),\n" +
            "(56,'i201222489','Deborah','Burton','Cox','i201222489@cibertec.edu.pe',1),\n" +
            "(57,'i201228117','Andrea','Fuller','Hunter','i201228117@cibertec.edu.pe',1),\n" +
            "(58,'i201398069','Virginia','Little','Gordon','i201398069@cibertec.edu.pe',1),\n" +
            "(59,'i201335772','Paula','Coleman','Russell','i201335772@cibertec.edu.pe',1),\n" +
            "(60,'i201337277','Kathy','Chavez','Moore','i201337277@cibertec.edu.pe',1),\n" +
            "(61,'i201302741','Denise','Hudson','Freeman','i201302741@cibertec.edu.pe',1),\n" +
            "(62,'i201292581','Virginia','Hamilton','Stewart','i201292581@cibertec.edu.pe',1),\n" +
            "(63,'i201386532','Julia','Henry','Miller','i201386532@cibertec.edu.pe',1),\n" +
            "(64,'i201392063','Susan','Edwards','Fisher','i201392063@cibertec.edu.pe',1),\n" +
            "(65,'i201377797','Sandra','Franklin','Palmer','i201377797@cibertec.edu.pe',1),\n" +
            "(66,'i201260755','Martha','Jones','Armstrong','i201260755@cibertec.edu.pe',1),\n" +
            "(67,'i201354346','Doris','Larson','Gonzales','i201354346@cibertec.edu.pe',1),\n" +
            "(68,'i201293411','Virginia','Baker','Willis','i201293411@cibertec.edu.pe',1),\n" +
            "(69,'i201217586','Cynthia','Gilbert','Taylor','i201217586@cibertec.edu.pe',1),\n" +
            "(70,'i201229796','Amy','Dean','Barnes','i201229796@cibertec.edu.pe',1),\n" +
            "(71,'i201318112','Amy','Matthews','Spencer','i201318112@cibertec.edu.pe',1),\n" +
            "(72,'i201396542','Jessica','Palmer','Fields','i201396542@cibertec.edu.pe',1),\n" +
            "(73,'i201272804','Alice','Butler','Mason','i201272804@cibertec.edu.pe',1),\n" +
            "(74,'i201271228','Marilyn','Larson','Watkins','i201271228@cibertec.edu.pe',1),\n" +
            "(75,'i201395762','Julie','Olson','Austin','i201395762@cibertec.edu.pe',1),\n" +
            "(76,'i201294598','Gloria','Johnson','White','i201294598@cibertec.edu.pe',1),\n" +
            "(77,'i201308929','Kimberly','Hill','Lee','i201308929@cibertec.edu.pe',1),\n" +
            "(78,'i201311446','Emily','Hudson','Welch','i201311446@cibertec.edu.pe',1),\n" +
            "(79,'i201252976','Cheryl','Fox','Graham','i201252976@cibertec.edu.pe',1),\n" +
            "(80,'i201255497','Jessica','Woods','Perkins','i201255497@cibertec.edu.pe',1),\n" +
            "(81,'i201318055','Carolyn','Gibson','Harvey','i201318055@cibertec.edu.pe',1),\n" +
            "(82,'i201223378','Ashley','Hudson','Rogers','i201223378@cibertec.edu.pe',1),\n" +
            "(83,'i201277751','Tammy','Wheeler','Coleman','i201277751@cibertec.edu.pe',1),\n" +
            "(84,'i201270570','Debra','Sims','Gutierrez','i201270570@cibertec.edu.pe',1),\n" +
            "(85,'i201297150','Beverly','Frazier','Banks','i201297150@cibertec.edu.pe',1),\n" +
            "(86,'i201315202','Rebecca','Cruz','Cooper','i201315202@cibertec.edu.pe',1),\n" +
            "(87,'i201329664','Lillian','Ryan','Hayes','i201329664@cibertec.edu.pe',1),\n" +
            "(88,'i201307238','Bonnie','Castillo','Collins','i201307238@cibertec.edu.pe',1),\n" +
            "(89,'i201318575','Helen','Larson','Freeman','i201318575@cibertec.edu.pe',1),\n" +
            "(90,'i201232281','Shirley','Hernandez','Burke','i201232281@cibertec.edu.pe',1),\n" +
            "(91,'i201391753','Joan','Fisher','Hansen','i201391753@cibertec.edu.pe',1),\n" +
            "(92,'i201226375','Joyce','Hanson','Sanders','i201226375@cibertec.edu.pe',1),\n" +
            "(93,'i201289113','Jennifer','Robinson','Kelly','i201289113@cibertec.edu.pe',1),\n" +
            "(94,'i201267592','Elizabeth','Ramos','Turner','i201267592@cibertec.edu.pe',1),\n" +
            "(95,'i201322246','Patricia','Tucker','Anderson','i201322246@cibertec.edu.pe',1),\n" +
            "(96,'i201338802','Sara','Diaz','Armstrong','i201338802@cibertec.edu.pe',1),\n" +
            "(97,'i201391142','Karen','Bradley','Johnston','i201391142@cibertec.edu.pe',1),\n" +
            "(98,'i201282062','Laura','Washington','Harper','i201282062@cibertec.edu.pe',1),\n" +
            "(99,'i201343867','Rachel','Burton','Crawford','i201343867@cibertec.edu.pe',1),\n" +
            "(100,'i201374131','Anna','Freeman','Ramirez','i201374131@cibertec.edu.pe',1),\n" +
            "(101,'i201361657','Denise','Duncan','Elliott','i201361657@cibertec.edu.pe',1),\n" +
            "(102,'i201345690','Paula','Evans','Reid','i201345690@cibertec.edu.pe',1),\n" +
            "(103,'i201341214','Amy','Weaver','Chapman','i201341214@cibertec.edu.pe',1),\n" +
            "(104,'i201393258','Doris','Evans','Coleman','i201393258@cibertec.edu.pe',1),\n" +
            "(105,'i201317635','Virginia','Sanchez','Ramos','i201317635@cibertec.edu.pe',1),\n" +
            "(106,'i201284045','Martha','Hunter','Diaz','i201284045@cibertec.edu.pe',1),\n" +
            "(107,'i201355318','Annie','Vasquez','Bradley','i201355318@cibertec.edu.pe',1),\n" +
            "(108,'i201303302','Joyce','Nelson','Reed','i201303302@cibertec.edu.pe',1),\n" +
            "(109,'i201373367','Anna','Morales','Owens','i201373367@cibertec.edu.pe',1),\n" +
            "(110,'i201243376','Judy','Simmons','Cook','i201243376@cibertec.edu.pe',1),\n" +
            "(111,'i201238652','Patricia','Burns','Thompson','i201238652@cibertec.edu.pe',1),\n" +
            "(112,'i201345208','Cynthia','Sims','Lopez','i201345208@cibertec.edu.pe',1),\n" +
            "(113,'i201260315','Doris','Bishop','Morris','i201260315@cibertec.edu.pe',1),\n" +
            "(114,'i201276987','Stephanie','Bailey','Evans','i201276987@cibertec.edu.pe',1),\n" +
            "(115,'i201316916','Barbara','Butler','Wells','i201316916@cibertec.edu.pe',1),\n" +
            "(116,'i201344385','Nancy','Palmer','Franklin','i201344385@cibertec.edu.pe',1),\n" +
            "(117,'i201311054','Carolyn','Kelley','James','i201311054@cibertec.edu.pe',1),\n" +
            "(118,'i201388695','Maria','Burke','Hunter','i201388695@cibertec.edu.pe',1),\n" +
            "(119,'i201398766','Linda','Walker','Price','i201398766@cibertec.edu.pe',1),\n" +
            "(120,'i201305425','Diane','Andrews','Hall','i201305425@cibertec.edu.pe',1),\n" +
            "(121,'i201279776','Annie','Andrews','Peterson','i201279776@cibertec.edu.pe',1),\n" +
            "(122,'i201368853','Jennifer','Wheeler','Evans','i201368853@cibertec.edu.pe',1),\n" +
            "(123,'i201303212','Angela','Thomas','Morris','i201303212@cibertec.edu.pe',1),\n" +
            "(124,'i201390688','Katherine','Snyder','Marshall','i201390688@cibertec.edu.pe',1),\n" +
            "(125,'i201396759','Norma','Stevens','Cole','i201396759@cibertec.edu.pe',1),\n" +
            "(126,'i201371718','Elizabeth','Gonzales','Harper','i201371718@cibertec.edu.pe',1),\n" +
            "(127,'i201201363','Frances','Lee','Cole','i201201363@cibertec.edu.pe',1),\n" +
            "(128,'i201351727','Ann','Cunningham','Washington','i201351727@cibertec.edu.pe',1),\n" +
            "(129,'i201227240','Shirley','Stephens','Little','i201227240@cibertec.edu.pe',1),\n" +
            "(130,'i201241220','Shirley','Stone','Brown','i201241220@cibertec.edu.pe',1),\n" +
            "(131,'i201296720','Catherine','Roberts','Mendoza','i201296720@cibertec.edu.pe',1),\n" +
            "(132,'i201247399','Brenda','Montgomery','Gomez','i201247399@cibertec.edu.pe',1),\n" +
            "(133,'i201245880','Brenda','Gilbert','Moreno','i201245880@cibertec.edu.pe',1),\n" +
            "(134,'i201201532','Rachel','Simpson','Rose','i201201532@cibertec.edu.pe',1),\n" +
            "(135,'i201398420','Heather','Fowler','Martinez','i201398420@cibertec.edu.pe',1),\n" +
            "(136,'i201262991','Pamela','Harrison','Banks','i201262991@cibertec.edu.pe',1),\n" +
            "(137,'i201377561','Margaret','Richardson','Boyd','i201377561@cibertec.edu.pe',1),\n" +
            "(138,'i201308130','Lillian','Russell','Graham','i201308130@cibertec.edu.pe',1),\n" +
            "(139,'i201396434','Marilyn','Perkins','James','i201396434@cibertec.edu.pe',1),\n" +
            "(140,'i201326012','Norma','Henry','Mccoy','i201326012@cibertec.edu.pe',1),\n" +
            "(141,'i201229582','Patricia','Hudson','Stanley','i201229582@cibertec.edu.pe',1),\n" +
            "(142,'i201226259','Sarah','Carter','Sanchez','i201226259@cibertec.edu.pe',1),\n" +
            "(143,'i201202036','Sandra','Hughes','Matthews','i201202036@cibertec.edu.pe',1),\n" +
            "(144,'i201372556','Kathryn','Graham','Young','i201372556@cibertec.edu.pe',1),\n" +
            "(145,'i201390914','Lori','Hayes','Sanchez','i201390914@cibertec.edu.pe',1),\n" +
            "(146,'i201386744','Cheryl','Carroll','Kim','i201386744@cibertec.edu.pe',1),\n" +
            "(147,'i201340280','Bonnie','Perkins','Cruz','i201340280@cibertec.edu.pe',1),\n" +
            "(148,'i201252586','Beverly','Daniels','Grant','i201252586@cibertec.edu.pe',1),\n" +
            "(149,'i201374136','Mildred','Morris','Sullivan','i201374136@cibertec.edu.pe',1),\n" +
            "(150,'i201243463','Brenda','Moreno','Reid','i201243463@cibertec.edu.pe',1),\n" +
            "(151,'i201302119','Kathryn','Perez','Kennedy','i201302119@cibertec.edu.pe',1),\n" +
            "(152,'i201353319','Lillian','Henderson','Washington','i201353319@cibertec.edu.pe',1),\n" +
            "(153,'i201286326','Beverly','Burke','Perez','i201286326@cibertec.edu.pe',1),\n" +
            "(154,'i201356796','Stephanie','Evans','Simmons','i201356796@cibertec.edu.pe',1),\n" +
            "(155,'i201372584','Elizabeth','Flores','Williamson','i201372584@cibertec.edu.pe',1),\n" +
            "(156,'i201286453','Paula','Ray','Wagner','i201286453@cibertec.edu.pe',1),\n" +
            "(157,'i201217886','Teresa','Stevens','Howell','i201217886@cibertec.edu.pe',1),\n" +
            "(158,'i201343743','Phyllis','Gray','Gordon','i201343743@cibertec.edu.pe',1),\n" +
            "(159,'i201275973','Laura','Mills','Reid','i201275973@cibertec.edu.pe',1),\n" +
            "(160,'i201271171','Judy','Carroll','Clark','i201271171@cibertec.edu.pe',1),\n" +
            "(161,'i201385500','Deborah','Moreno','Ruiz','i201385500@cibertec.edu.pe',1),\n" +
            "(162,'i201339579','Betty','Barnes','Mills','i201339579@cibertec.edu.pe',1),\n" +
            "(163,'i201213095','Sharon','Smith','Myers','i201213095@cibertec.edu.pe',1),\n" +
            "(164,'i201277010','Anne','Cruz','Jordan','i201277010@cibertec.edu.pe',1),\n" +
            "(165,'i201295150','Rachel','Johnston','Schmidt','i201295150@cibertec.edu.pe',1),\n" +
            "(166,'i201339659','Nancy','Fernandez','Flores','i201339659@cibertec.edu.pe',1),\n" +
            "(167,'i201270168','Sarah','Ferguson','Phillips','i201270168@cibertec.edu.pe',1),\n" +
            "(168,'i201222074','Jane','Hughes','Stewart','i201222074@cibertec.edu.pe',1),\n" +
            "(169,'i201288819','Lori','Jacobs','Bryant','i201288819@cibertec.edu.pe',1),\n" +
            "(170,'i201201878','Bonnie','Willis','Riley','i201201878@cibertec.edu.pe',1),\n" +
            "(171,'i201346221','Beverly','Lawson','Wells','i201346221@cibertec.edu.pe',1),\n" +
            "(172,'i201396399','Barbara','Castillo','Castillo','i201396399@cibertec.edu.pe',1),\n" +
            "(173,'i201274149','Irene','Brown','Ruiz','i201274149@cibertec.edu.pe',1),\n" +
            "(174,'i201360977','Ashley','Holmes','Fields','i201360977@cibertec.edu.pe',1),\n" +
            "(175,'i201312892','Theresa','Knight','Morales','i201312892@cibertec.edu.pe',1),\n" +
            "(176,'i201258662','Catherine','Hansen','King','i201258662@cibertec.edu.pe',1),\n" +
            "(177,'i201209670','Betty','Lane','Stevens','i201209670@cibertec.edu.pe',1),\n" +
            "(178,'i201247626','Betty','Mcdonald','Ellis','i201247626@cibertec.edu.pe',1),\n" +
            "(179,'i201335573','Ashley','Lawson','Matthews','i201335573@cibertec.edu.pe',1),\n" +
            "(180,'i201393333','Jacqueline','Stone','Jenkins','i201393333@cibertec.edu.pe',1),\n" +
            "(181,'i201216548','Diane','Carroll','Riley','i201216548@cibertec.edu.pe',1),\n" +
            "(182,'i201312156','Wanda','Bennett','Patterson','i201312156@cibertec.edu.pe',1),\n" +
            "(183,'i201331206','Bonnie','Duncan','Franklin','i201331206@cibertec.edu.pe',1),\n" +
            "(184,'i201270592','Beverly','Brooks','Wagner','i201270592@cibertec.edu.pe',1),\n" +
            "(185,'i201332873','Angela','Wagner','Kelley','i201332873@cibertec.edu.pe',1),\n" +
            "(186,'i201315814','Theresa','Gonzalez','Medina','i201315814@cibertec.edu.pe',1),\n" +
            "(187,'i201394408','Teresa','Montgomery','Grant','i201394408@cibertec.edu.pe',1),\n" +
            "(188,'i201352654','Denise','Crawford','Butler','i201352654@cibertec.edu.pe',1),\n" +
            "(189,'i201372643','Ashley','Schmidt','Brooks','i201372643@cibertec.edu.pe',1),\n" +
            "(190,'i201278876','Ruth','Stephens','Robinson','i201278876@cibertec.edu.pe',1),\n" +
            "(191,'i201272849','Louise','Snyder','Cunningham','i201272849@cibertec.edu.pe',1),\n" +
            "(192,'i201331265','Carolyn','Phillips','Lee','i201331265@cibertec.edu.pe',1),\n" +
            "(193,'i201237310','Anne','Scott','Reed','i201237310@cibertec.edu.pe',1),\n" +
            "(194,'i201220027','Brenda','Shaw','Powell','i201220027@cibertec.edu.pe',1),\n" +
            "(195,'i201381516','Dorothy','Young','Austin','i201381516@cibertec.edu.pe',1),\n" +
            "(196,'i201345229','Rose','Duncan','George','i201345229@cibertec.edu.pe',1),\n" +
            "(197,'i201312842','Maria','Matthews','Lee','i201312842@cibertec.edu.pe',1),\n" +
            "(198,'i201374928','Kathryn','Mendoza','Wilson','i201374928@cibertec.edu.pe',1),\n" +
            "(199,'i201280783','Pamela','Nelson','Moore','i201280783@cibertec.edu.pe',1),\n" +
            "(200,'i201258214','Diana','Wright','Ruiz','i201258214@cibertec.edu.pe',1);\n";

    private static final String INSERTS_DIAS = "insert into DIAS values\n" +
            "(1, 'Lunes'),\n" +
            "(2, 'Martes'),\n" +
            "(3, 'Miércoles'),\n" +
            "(4, 'Jueves'),\n" +
            "(5, 'Viernes'),\n" +
            "(6, 'Sábado'),\n" +
            "(7, 'Domingo');";

    private static final String INSERTS_CURSOS = "insert into CURSOS values\n" +
            "(1,'0267','Base de Datos Avanzado ii'),\n" +
            "(2,'0557','Desarrollo de Aplicaciones Móviles I'),\n" +
            "(3,'0778','Desarrollo para Entorno Web'),\n" +
            "(4,'0772','Fundamentos de Calidad de Software'),\n" +
            "(5,'1352','Inglés Profesional I'),\n" +
            "(6,'0720','Organización y Constitución de Empresas'),\n" +
            "(7,'0779','Proyecto de Investigación'),\n" +
            "(8,'1369','Ética Profesional');";

    private static final String INSERTS_CARRERAS = "insert into CARRERAS values\n" +
            "(1, 'ADMINISTRACIÓN BANCARIA TR','1'),\n" +
            "(2, 'ADMINISTRACIÓN DE NEGOCIOS INTERNACIONALES TR','1'),\n" +
            "(3, 'ADMINISTRACIÓN DE RECURSOS HUMANOS TR','1'),\n" +
            "(4, 'ADMINISTRACIÓN TR','1'),\n" +
            "(5, 'ADMINISTRACIÓN DE SERVICIOS TURÃSTICOS TR','1'),\n" +
            "(6, 'ADMINISTRACIÓN Y SISTEMAS TR','1'),\n" +
            "(7, 'COMPUTACIÓN E INFORMÁTICA TR','1'),\n" +
            "(8,'CONTABILIDAD TR','1'),\n" +
            "(9,'DISEÑO DE INTERIORES TR','1'),\n" +
            "(10,'DISEÑO GRÁFICO TR','1'),\n" +
            "(11,'INDUSTRIAL Y SISTEMAS TR','1'),\n" +
            "(12,'MARKETING TR','1'),\n" +
            "(13,'REDES Y COMUNICACIONES TR','1');";

    private static final String INSERTS_TIPO_AULA = "INSERT INTO TIPO_AULA VALUES\n" +
            "(1,'TEORIA'),\n" +
            "(3,'PRÁCTICA/TALLER'),\n" +
            "(2,'LABORATORIO');";

    private static final String INSERTS_SECCIONES = "INSERT INTO SECCIONES VALUES\n" +
            "(1,'T5AN'),\n" +
            "(2,'T5BN'),\n" +
            "(3,'T5CN'),\n" +
            "(4,'T5DN');";
            /*"(5,'D5AN'),\n" +
            "(6,'D5BN'),\n" +
            "(7,'D5DN'),;";
           "(8,'T6AN'),\n" +
            "(9,'T6BN'),\n" +
            "(10,'T6CN'),\n" +
            "(11,'C4AN'),\n" +
            "(12,'D4AN'),\n" +
            "(13,'D4BN'),\n" +
            "(14,'G5AN'),\n" +
            "(15,'G5BN'),\n" +
            "(16,'G5CN'),\n" +
            "(17,'G5DN');";*/

    private static final String INSERTS_EVALUACIONES = "INSERT INTO EVALUACIONES VALUES\n" +
            "(1,'EVALUACION LABORATORIO'),\n" +
            "(2,'NOTA ACTITUDINAL'),\n" +
            "(3,'AVANCE TÉCNICO'),\n" +
            "(4,'AVANCE DE DESARROLLO'),\n" +
            "(5,'SUSTENTACIÓN PROYECTO'),\n" +
            "(6,'EXAMEN TEORICO'),\n" +
            "(7,'EVALUACIÓN VIRTUAL'),\n" +
            "(8,'EXAMEN FINAL'),\n" +
            "(9,'EXAMEN FINAL DE LABORATORIO'),\n" +
            "(10,'DESARROLLO PROGRESO EN LA PLATAFORMA'),\n" +
            "(11,'UNIDADES VIRTUALES');\n";

    private static final String INSERTS_CARRERAS_CURSOS = "insert into CARRERAS_CURSOS values\n" +
            "(1,7,1,5),\n" +
            "(2,7,2,5),\n" +
            "(3,7,3,5),\n" +
            "(4,7,4,5),\n" +
            "(5,7,5,5),\n" +
            "(6,7,6,5),\n" +
            "(7,7,7,5),\n" +
            "(8,7,8,5);";


    private static final String INSERTS_CARGA_DOCENTE = "insert into CARGA_DOCENTE values\n" +
            "(1,1,1,1,1,1),\n" +
            "(2,1,1,1,1,2),\n" +
            "(3,1,1,1,2,1),\n" +
            //"(4,1,1,1,2,2),\n" +
            "(5,2,1,1,1,1),\n" +
            "(6,2,1,1,1,2),\n" +
            //"(7,2,1,1,2,1),\n" +
            "(8,3,1,1,2,2),\n" +
            "(9,3,1,1,2,1),\n" +
            //"(10,4,1,1,2,2),\n" +
            "(11,4,1,1,2,1),\n" +
            "(12,5,2,1,3,2),\n" +
            "(13,5,2,1,3,1),\n" +
            "(14,6,2,2,4,1),\n" +
            "(15,6,2,2,2,1),\n" +
            "(16,7,2,2,3,1),\n" +
            "(17,7,2,2,4,2),\n" +
            "(18,1,3,2,1,1),\n" +
            "(19,2,3,2,2,1),\n" +
            "(20,3,3,2,3,2),\n" +
            "(21,4,4,3,1,1),\n" +
            "(22,5,4,3,2,1),\n" +
            "(23,6,4,3,3,2),\n" +
            "(24,7,4,3,4,2),\n" +
            "(25,8,5,3,3,1);";


    private static final String INSERTS_HORARIO = "insert into HORARIOS values\n" +
            "(1,1,1,1,'08:23:19','10:23:19',2,3,1,2014),\n" +
            "(2,1,2,1,'08:23:19','10:23:19',2,3,1,2014),\n" +
            "(3,3,1,1,'18:23:19','19:23:19',2,3,1,2014),\n" +
            "(4,4,1,1,'08:23:19','10:23:19',2,3,1,2014),\n" +
            "(5,5,2,1,'08:23:19','10:23:19',2,3,1,2014),\n" +
            "(6,6,2,2,'15:23:19','19:23:19',2,3,1,2014),\n" +
            "(7,7,2,2,'14:23:19','18:23:19',2,3,1,2014),\n" +
            "(8,1,3,2,'08:23:19','10:23:19',2,3,1,2014),\n" +
            "(9,2,3,2,'10:23:19','18:23:19',2,3,1,2014),\n" +
            "(10,3,3,2,'16:23:19','20:23:19',2,3,1,2014),\n" +
            "(11,4,4,3,'08:23:19','10:23:19',2,3,1,2014),\n" +
            "(12,5,4,3,'11:23:19','14:23:19',2,3,1,2014),\n" +
            "(13,6,4,3,'10:23:19','12:23:19',2,3,1,2014),\n" +
            "(14,7,4,3,'09:23:19','10:23:19',2,3,1,2014),\n" +
            "(15,8,5,3,'08:23:19','10:23:19',2,3,1,2014);";


    private static final String INSERTS_CURSOS_EVALUACIONES = "INSERT INTO CURSOS_EVALUACIONES VALUES " +
            " (1,1,1,1,15),\n" +
            " (2,1,1,2,20),\n" +
            " (3,1,1,3,25),\n" +
            " (4,1,2,1,10),\n" +
            " (5,1,8,1,30),\n" +
            " (6,2,1,1,8),\n" +
            " (7,2,1,2,10),\n" +
            " (8,2,1,3,12),\n" +
            " (9,2,3,1,15),\n" +
            " (10,2,2,1,10),\n" +
            " (11,2,5,1,20),\n" +
            " (12,2,9,1,15),\n" +
            " (13,3,1,1,12),\n" +
            " (14,3,1,2,15),\n" +
            " (15,3,1,3,18),\n" +
            " (16,3,3,1,10),\n" +
            " (17,3,2,1,10),\n" +
            " (18,3,5,1,15),\n" +
            " (20,3,9,1,20),\n" +
            " (21,4,6,1,8),\n" +
            " (22,4,6,2,10),\n" +
            " (23,4,6,3,12),\n" +
            " (24,4,6,4,15),\n" +
            " (25,4,2,1,10),\n" +
            " (26,4,5,1,20),\n" +
            " (27,4,8,1,25),\n" +
            " (28,5,6,1,8),\n" +
            " (29,5,6,2,10),\n" +
            " (30,5,6,3,12),\n" +
            " (31,5,5,1,15),\n" +
            " (32,5,4,1,10),\n" +
            " (33,5,10,1,10),\n" +
            " (34,5,2,1,10),\n" +
            " (35,5,8,1,25),\n" +
            " (36,6,6,1,20),\n" +
            " (37,6,6,2,20),\n" +
            " (38,6,7,1,20),\n" +
            " (39,6,2,1,10),\n" +
            " (40,6,8,1,30),\n" +
            " (41,7,1,1,12),\n" +
            " (42,7,1,2,15),\n" +
            " (43,7,1,3,18),\n" +
            " (44,7,3,1,10),\n" +
            " (45,7,2,1,10),\n" +
            " (46,7,5,1,15),\n" +
            " (47,7,9,1,20),\n" +
            " (48,8,11,1,15),\n" +
            " (49,8,11,2,15),\n" +
            " (50,8,11,3,15),\n" +
            " (51,8,11,4,15),\n" +
            " (52,8,8,1,30),\n" +
            " (53,8,2,1,10);";

    private static final String INSERTS_MATRICULA1 = "INSERT INTO MATRICULA VALUES(1,1,1,1,1,3,1),\n" +
            "(2,1,2,1,1,4,1),\n" +
            "(3,1,3,1,1,1,1),\n" +
            "(4,1,4,2,1,1,1),\n" +
            "(5,1,5,2,1,2,1),\n" +
            "(6,1,6,2,1,2,1),\n" +
            "(7,1,7,1,1,1,1),\n" +
            "(8,1,8,1,1,2,1),\n" +
            "(9,2,1,2,1,3,1),\n" +
            "(10,2,2,1,1,3,1),\n" +
            "(11,2,3,1,1,3,1),\n" +
            "(12,2,4,2,1,2,1),\n" +
            "(13,2,5,1,1,3,1),\n" +
            "(14,2,6,1,1,4,1),\n" +
            "(15,2,7,1,1,1,1),\n" +
            "(16,2,8,1,1,2,1),\n" +
            "(17,3,1,1,1,2,1),\n" +
            "(18,3,2,1,1,3,1),\n" +
            "(19,3,3,2,1,3,1),\n" +
            "(20,3,4,1,1,1,1),\n" +
            "(21,3,5,2,1,1,1),\n" +
            "(22,3,6,2,1,2,1),\n" +
            "(23,3,7,2,1,2,1),\n" +
            "(24,3,8,2,1,4,1),\n" +
            "(25,4,1,1,1,3,1),\n" +
            "(26,4,2,1,1,2,1),\n" +
            "(27,4,3,2,1,3,1),\n" +
            "(28,4,4,2,1,1,1),\n" +
            "(29,4,5,1,1,1,1),\n" +
            "(30,4,6,1,1,1,1),\n" +
            "(31,4,7,1,1,2,1),\n" +
            "(32,4,8,2,1,2,1),\n" +
            "(33,5,1,2,1,4,1),\n" +
            "(34,5,2,1,1,1,1),\n" +
            "(35,5,3,2,1,3,1),\n" +
            "(36,5,4,1,1,4,1),\n" +
            "(37,5,5,2,1,3,1),\n" +
            "(38,5,6,2,1,1,1),\n" +
            "(39,5,7,2,1,4,1),\n" +
            "(40,5,8,2,1,3,1),\n" +
            "(41,6,1,1,1,4,1),\n" +
            "(42,6,2,1,1,2,1),\n" +
            "(43,6,3,2,1,4,1),\n" +
            "(44,6,4,2,1,4,1),\n" +
            "(45,6,5,2,1,4,1),\n" +
            "(46,6,6,2,1,4,1),\n" +
            "(47,6,7,1,1,2,1),\n" +
            "(48,6,8,1,1,1,1),\n" +
            "(49,7,1,2,1,4,1),\n" +
            "(50,7,2,1,1,3,1),\n" +
            "(51,7,3,1,1,1,1),\n" +
            "(52,7,4,2,1,3,1),\n" +
            "(53,7,5,1,1,3,1),\n" +
            "(54,7,6,2,1,3,1),\n" +
            "(55,7,7,2,1,3,1),\n" +
            "(56,7,8,1,1,1,1),\n" +
            "(57,8,1,2,1,3,1),\n" +
            "(58,8,2,1,1,4,1),\n" +
            "(59,8,3,1,1,1,1),\n" +
            "(60,8,4,2,1,1,1),\n" +
            "(61,8,5,2,1,4,1),\n" +
            "(62,8,6,1,1,4,1),\n" +
            "(63,8,7,2,1,2,1),\n" +
            "(64,8,8,2,1,3,1),\n" +
            "(65,9,1,1,1,3,1),\n" +
            "(66,9,2,1,1,2,1),\n" +
            "(67,9,3,2,1,1,1),\n" +
            "(68,9,4,1,1,1,1),\n" +
            "(69,9,5,1,1,3,1),\n" +
            "(70,9,6,1,1,4,1),\n" +
            "(71,9,7,2,1,4,1),\n" +
            "(72,9,8,1,1,4,1),\n" +
            "(73,10,1,2,1,1,1),\n" +
            "(74,10,2,1,1,1,1),\n" +
            "(75,10,3,2,1,1,1),\n" +
            "(76,10,4,2,1,1,1),\n" +
            "(77,10,5,1,1,2,1),\n" +
            "(78,10,6,1,1,3,1),\n" +
            "(79,10,7,2,1,4,1),\n" +
            "(80,10,8,1,1,3,1),\n" +
            "(81,11,1,2,1,3,1),\n" +
            "(82,11,2,1,1,2,1),\n" +
            "(83,11,3,1,1,2,1),\n" +
            "(84,11,4,2,1,4,1),\n" +
            "(85,11,5,2,1,3,1),\n" +
            "(86,11,6,2,1,3,1),\n" +
            "(87,11,7,1,1,2,1),\n" +
            "(88,11,8,1,1,3,1),\n" +
            "(89,12,1,2,1,1,1),\n" +
            "(90,12,2,2,1,4,1),\n" +
            "(91,12,3,2,1,3,1),\n" +
            "(92,12,4,1,1,4,1),\n" +
            "(93,12,5,1,1,4,1),\n" +
            "(94,12,6,2,1,1,1),\n" +
            "(95,12,7,1,1,1,1),\n" +
            "(96,12,8,1,1,3,1),\n" +
            "(97,13,1,1,1,4,1),\n" +
            "(98,13,2,2,1,4,1),\n" +
            "(99,13,3,2,1,3,1),\n" +
            "(100,13,4,1,1,3,1),\n" +
            "(101,13,5,2,1,3,1),\n" +
            "(102,13,6,1,1,2,1),\n" +
            "(103,13,7,2,1,2,1),\n" +
            "(104,13,8,2,1,1,1),\n" +
            "(105,14,1,2,1,2,1),\n" +
            "(106,14,2,1,1,1,1),\n" +
            "(107,14,3,1,1,1,1),\n" +
            "(108,14,4,1,1,1,1),\n" +
            "(109,14,5,2,1,4,1),\n" +
            "(110,14,6,1,1,4,1),\n" +
            "(111,14,7,1,1,3,1),\n" +
            "(112,14,8,2,1,3,1),\n" +
            "(113,15,1,1,1,3,1),\n" +
            "(114,15,2,1,1,1,1),\n" +
            "(115,15,3,1,1,2,1),\n" +
            "(116,15,4,2,1,3,1),\n" +
            "(117,15,5,1,1,4,1),\n" +
            "(118,15,6,2,1,1,1),\n" +
            "(119,15,7,1,1,2,1),\n" +
            "(120,15,8,2,1,2,1),\n" +
            "(121,16,1,1,1,3,1),\n" +
            "(122,16,2,2,1,2,1),\n" +
            "(123,16,3,1,1,4,1),\n" +
            "(124,16,4,2,1,1,1),\n" +
            "(125,16,5,2,1,1,1),\n" +
            "(126,16,6,2,1,2,1),\n" +
            "(127,16,7,1,1,3,1),\n" +
            "(128,16,8,2,1,1,1),\n" +
            "(129,17,1,1,1,2,1),\n" +
            "(130,17,2,1,1,4,1),\n" +
            "(131,17,3,1,1,4,1),\n" +
            "(132,17,4,1,1,2,1),\n" +
            "(133,17,5,2,1,2,1),\n" +
            "(134,17,6,2,1,2,1),\n" +
            "(135,17,7,2,1,4,1),\n" +
            "(136,17,8,2,1,2,1),\n" +
            "(137,18,1,2,1,1,1),\n" +
            "(138,18,2,1,1,1,1),\n" +
            "(139,18,3,1,1,4,1),\n" +
            "(140,18,4,2,1,3,1),\n" +
            "(141,18,5,1,1,2,1),\n" +
            "(142,18,6,2,1,4,1),\n" +
            "(143,18,7,2,1,4,1),\n" +
            "(144,18,8,1,1,1,1),\n" +
            "(145,19,1,1,1,1,1),\n" +
            "(146,19,2,1,1,4,1),\n" +
            "(147,19,3,1,1,2,1),\n" +
            "(148,19,4,1,1,4,1),\n" +
            "(149,19,5,2,1,3,1),\n" +
            "(150,19,6,1,1,4,1),\n" +
            "(151,19,7,1,1,1,1),\n" +
            "(152,19,8,2,1,1,1),\n" +
            "(153,20,1,2,1,3,1),\n" +
            "(154,20,2,1,1,1,1),\n" +
            "(155,20,3,1,1,2,1),\n" +
            "(156,20,4,2,1,1,1),\n" +
            "(157,20,5,1,1,1,1),\n" +
            "(158,20,6,2,1,3,1),\n" +
            "(159,20,7,1,1,4,1),\n" +
            "(160,20,8,2,1,2,1),\n" +
            "(161,21,1,1,1,1,1),\n" +
            "(162,21,2,2,1,3,1),\n" +
            "(163,21,3,1,1,3,1),\n" +
            "(164,21,4,2,1,2,1),\n" +
            "(165,21,5,2,1,3,1),\n" +
            "(166,21,6,1,1,4,1),\n" +
            "(167,21,7,1,1,1,1),\n" +
            "(168,21,8,2,1,2,1),\n" +
            "(169,22,1,1,1,2,1),\n" +
            "(170,22,2,2,1,2,1),\n" +
            "(171,22,3,1,1,4,1),\n" +
            "(172,22,4,2,1,2,1),\n" +
            "(173,22,5,2,1,3,1),\n" +
            "(174,22,6,2,1,1,1),\n" +
            "(175,22,7,1,1,1,1),\n" +
            "(176,22,8,1,1,1,1),\n" +
            "(177,23,1,1,1,1,1),\n" +
            "(178,23,2,1,1,1,1),\n" +
            "(179,23,3,2,1,2,1),\n" +
            "(180,23,4,1,1,3,1),\n" +
            "(181,23,5,2,1,3,1),\n" +
            "(182,23,6,2,1,4,1),\n" +
            "(183,23,7,2,1,1,1),\n" +
            "(184,23,8,1,1,2,1),\n" +
            "(185,24,1,2,1,3,1),\n" +
            "(186,24,2,2,1,3,1),\n" +
            "(187,24,3,2,1,3,1),\n" +
            "(188,24,4,2,1,2,1),\n" +
            "(189,24,5,1,1,4,1),\n" +
            "(190,24,6,1,1,3,1),\n" +
            "(191,24,7,1,1,1,1),\n" +
            "(192,24,8,1,1,4,1),\n" +
            "(193,25,1,2,1,2,1),\n" +
            "(194,25,2,2,1,3,1),\n" +
            "(195,25,3,1,1,1,1),\n" +
            "(196,25,4,2,1,2,1),\n" +
            "(197,25,5,1,1,2,1),\n" +
            "(198,25,6,1,1,1,1),\n" +
            "(199,25,7,2,1,2,1),\n" +
            "(200,25,8,2,1,4,1),\n" +
            "(201,26,1,2,1,4,1),\n" +
            "(202,26,2,1,1,4,1),\n" +
            "(203,26,3,2,1,3,1),\n" +
            "(204,26,4,2,1,1,1),\n" +
            "(205,26,5,2,1,4,1),\n" +
            "(206,26,6,1,1,3,1),\n" +
            "(207,26,7,2,1,1,1),\n" +
            "(208,26,8,1,1,3,1),\n" +
            "(209,27,1,2,1,4,1),\n" +
            "(210,27,2,2,1,3,1),\n" +
            "(211,27,3,2,1,4,1),\n" +
            "(212,27,4,2,1,4,1),\n" +
            "(213,27,5,2,1,3,1),\n" +
            "(214,27,6,2,1,4,1),\n" +
            "(215,27,7,1,1,4,1),\n" +
            "(216,27,8,2,1,2,1),\n" +
            "(217,28,1,2,1,1,1),\n" +
            "(218,28,2,2,1,2,1),\n" +
            "(219,28,3,2,1,2,1),\n" +
            "(220,28,4,2,1,2,1),\n" +
            "(221,28,5,2,1,1,1),\n" +
            "(222,28,6,1,1,1,1),\n" +
            "(223,28,7,2,1,3,1),\n" +
            "(224,28,8,1,1,3,1),\n" +
            "(225,29,1,1,1,2,1),\n" +
            "(226,29,2,2,1,2,1),\n" +
            "(227,29,3,2,1,3,1),\n" +
            "(228,29,4,1,1,4,1),\n" +
            "(229,29,5,2,1,3,1),\n" +
            "(230,29,6,2,1,2,1),\n" +
            "(231,29,7,1,1,3,1),\n" +
            "(232,29,8,1,1,3,1),\n" +
            "(233,30,1,1,1,3,1),\n" +
            "(234,30,2,2,1,3,1),\n" +
            "(235,30,3,2,1,3,1),\n" +
            "(236,30,4,2,1,4,1),\n" +
            "(237,30,5,1,1,1,1),\n" +
            "(238,30,6,2,1,1,1),\n" +
            "(239,30,7,2,1,3,1),\n" +
            "(240,30,8,1,1,1,1),\n" +
            "(241,31,1,2,1,2,1),\n" +
            "(242,31,2,2,1,4,1),\n" +
            "(243,31,3,1,1,2,1),\n" +
            "(244,31,4,2,1,2,1),\n" +
            "(245,31,5,1,1,2,1),\n" +
            "(246,31,6,1,1,2,1),\n" +
            "(247,31,7,1,1,4,1),\n" +
            "(248,31,8,1,1,3,1),\n" +
            "(249,32,1,2,1,4,1),\n" +
            "(250,32,2,1,1,4,1),\n" +
            "(251,32,3,2,1,2,1),\n" +
            "(252,32,4,2,1,2,1),\n" +
            "(253,32,5,1,1,2,1),\n" +
            "(254,32,6,1,1,3,1),\n" +
            "(255,32,7,2,1,3,1),\n" +
            "(256,32,8,1,1,3,1),\n" +
            "(257,33,1,2,1,4,1),\n" +
            "(258,33,2,1,1,3,1),\n" +
            "(259,33,3,1,1,3,1),\n" +
            "(260,33,4,1,1,1,1),\n" +
            "(261,33,5,1,1,4,1),\n" +
            "(262,33,6,1,1,4,1),\n" +
            "(263,33,7,1,1,1,1),\n" +
            "(264,33,8,1,1,1,1),\n" +
            "(265,34,1,1,1,4,1),\n" +
            "(266,34,2,2,1,3,1),\n" +
            "(267,34,3,2,1,2,1),\n" +
            "(268,34,4,2,1,4,1),\n" +
            "(269,34,5,1,1,3,1),\n" +
            "(270,34,6,2,1,1,1),\n" +
            "(271,34,7,1,1,4,1),\n" +
            "(272,34,8,1,1,2,1),\n" +
            "(273,35,1,2,1,1,1),\n" +
            "(274,35,2,1,1,1,1),\n" +
            "(275,35,3,2,1,3,1),\n" +
            "(276,35,4,1,1,1,1),\n" +
            "(277,35,5,2,1,3,1),\n" +
            "(278,35,6,1,1,3,1),\n" +
            "(279,35,7,1,1,3,1),\n" +
            "(280,35,8,2,1,1,1),\n" +
            "(281,36,1,1,1,4,1),\n" +
            "(282,36,2,2,1,3,1),\n" +
            "(283,36,3,2,1,1,1),\n" +
            "(284,36,4,1,1,2,1),\n" +
            "(285,36,5,1,1,3,1),\n" +
            "(286,36,6,1,1,3,1),\n" +
            "(287,36,7,2,1,4,1),\n" +
            "(288,36,8,1,1,3,1),\n" +
            "(289,37,1,2,1,4,1),\n" +
            "(290,37,2,1,1,3,1),\n" +
            "(291,37,3,1,1,2,1),\n" +
            "(292,37,4,2,1,2,1),\n" +
            "(293,37,5,2,1,3,1),\n" +
            "(294,37,6,1,1,4,1),\n" +
            "(295,37,7,2,1,1,1),\n" +
            "(296,37,8,1,1,3,1),\n" +
            "(297,38,1,2,1,3,1),\n" +
            "(298,38,2,2,1,3,1),\n" +
            "(299,38,3,2,1,1,1),\n" +
            "(300,38,4,2,1,1,1),\n" +
            "(301,38,5,2,1,1,1),\n" +
            "(302,38,6,1,1,3,1),\n" +
            "(303,38,7,2,1,1,1),\n" +
            "(304,38,8,2,1,3,1),\n" +
            "(305,39,1,2,1,4,1),\n" +
            "(306,39,2,1,1,3,1),\n" +
            "(307,39,3,2,1,2,1),\n" +
            "(308,39,4,1,1,4,1),\n" +
            "(309,39,5,2,1,3,1),\n" +
            "(310,39,6,2,1,1,1),\n" +
            "(311,39,7,2,1,3,1),\n" +
            "(312,39,8,1,1,1,1),\n" +
            "(313,40,1,1,1,2,1),\n" +
            "(314,40,2,1,1,3,1),\n" +
            "(315,40,3,2,1,4,1),\n" +
            "(316,40,4,1,1,1,1),\n" +
            "(317,40,5,2,1,1,1),\n" +
            "(318,40,6,1,1,1,1),\n" +
            "(319,40,7,2,1,4,1),\n" +
            "(320,40,8,2,1,2,1),\n" +
            "(321,41,1,1,1,3,1),\n" +
            "(322,41,2,1,1,1,1),\n" +
            "(323,41,3,2,1,4,1),\n" +
            "(324,41,4,1,1,4,1),\n" +
            "(325,41,5,2,1,2,1),\n" +
            "(326,41,6,2,1,1,1),\n" +
            "(327,41,7,2,1,2,1),\n" +
            "(328,41,8,2,1,1,1),\n" +
            "(329,42,1,1,1,1,1),\n" +
            "(330,42,2,2,1,1,1),\n" +
            "(331,42,3,1,1,3,1),\n" +
            "(332,42,4,1,1,2,1),\n" +
            "(333,42,5,2,1,4,1),\n" +
            "(334,42,6,2,1,2,1),\n" +
            "(335,42,7,2,1,1,1),\n" +
            "(336,42,8,2,1,4,1),\n" +
            "(337,43,1,1,1,1,1),\n" +
            "(338,43,2,1,1,3,1),\n" +
            "(339,43,3,2,1,3,1),\n" +
            "(340,43,4,2,1,4,1),\n" +
            "(341,43,5,2,1,4,1),\n" +
            "(342,43,6,2,1,4,1),\n" +
            "(343,43,7,1,1,1,1),\n" +
            "(344,43,8,1,1,3,1),\n" +
            "(345,44,1,1,1,4,1),\n" +
            "(346,44,2,1,1,2,1),\n" +
            "(347,44,3,2,1,3,1),\n" +
            "(348,44,4,2,1,4,1),\n" +
            "(349,44,5,2,1,2,1),\n" +
            "(350,44,6,1,1,4,1),\n" +
            "(351,44,7,2,1,3,1),\n" +
            "(352,44,8,2,1,4,1),\n" +
            "(353,45,1,1,1,1,1),\n" +
            "(354,45,2,2,1,1,1),\n" +
            "(355,45,3,2,1,1,1),\n" +
            "(356,45,4,1,1,4,1),\n" +
            "(357,45,5,2,1,2,1),\n" +
            "(358,45,6,1,1,2,1),\n" +
            "(359,45,7,1,1,2,1),\n" +
            "(360,45,8,1,1,4,1),\n" +
            "(361,46,1,1,1,4,1),\n" +
            "(362,46,2,1,1,1,1),\n" +
            "(363,46,3,2,1,1,1),\n" +
            "(364,46,4,2,1,2,1),\n" +
            "(365,46,5,2,1,4,1),\n" +
            "(366,46,6,2,1,1,1),\n" +
            "(367,46,7,2,1,3,1),\n" +
            "(368,46,8,2,1,4,1),\n" +
            "(369,47,1,1,1,1,1),\n" +
            "(370,47,2,2,1,4,1),\n" +
            "(371,47,3,1,1,3,1),\n" +
            "(372,47,4,1,1,4,1),\n" +
            "(373,47,5,1,1,4,1),\n" +
            "(374,47,6,1,1,4,1),\n" +
            "(375,47,7,1,1,4,1),\n" +
            "(376,47,8,1,1,4,1),\n" +
            "(377,48,1,2,1,4,1),\n" +
            "(378,48,2,2,1,3,1),\n" +
            "(379,48,3,1,1,4,1),\n" +
            "(380,48,4,2,1,1,1),\n" +
            "(381,48,5,2,1,3,1),\n" +
            "(382,48,6,2,1,4,1),\n" +
            "(383,48,7,1,1,4,1),\n" +
            "(384,48,8,2,1,4,1),\n" +
            "(385,49,1,2,1,1,1),\n" +
            "(386,49,2,2,1,2,1),\n" +
            "(387,49,3,1,1,2,1),\n" +
            "(388,49,4,2,1,2,1),\n" +
            "(389,49,5,1,1,1,1),\n" +
            "(390,49,6,1,1,4,1),\n" +
            "(391,49,7,2,1,3,1),\n" +
            "(392,49,8,2,1,2,1),\n" +
            "(393,50,1,2,1,4,1),\n" +
            "(394,50,2,1,1,2,1),\n" +
            "(395,50,3,2,1,1,1),\n" +
            "(396,50,4,1,1,1,1),\n" +
            "(397,50,5,1,1,2,1),\n" +
            "(398,50,6,2,1,1,1),\n" +
            "(399,50,7,1,1,4,1);";

    private static final String INSERTS_MATRICULA2 = "INSERT INTO MATRICULA VALUES(400,50,8,2,1,1,1),\n" +
            "(401,51,1,1,1,3,1),\n" +
            "(402,51,2,1,1,4,1),\n" +
            "(403,51,3,2,1,4,1),\n" +
            "(404,51,4,2,1,4,1),\n" +
            "(405,51,5,1,1,1,1),\n" +
            "(406,51,6,1,1,2,1),\n" +
            "(407,51,7,1,1,4,1),\n" +
            "(408,51,8,2,1,2,1),\n" +
            "(409,52,1,1,1,1,1),\n" +
            "(410,52,2,1,1,4,1),\n" +
            "(411,52,3,2,1,3,1),\n" +
            "(412,52,4,2,1,3,1),\n" +
            "(413,52,5,1,1,2,1),\n" +
            "(414,52,6,2,1,1,1),\n" +
            "(415,52,7,2,1,2,1),\n" +
            "(416,52,8,2,1,1,1),\n" +
            "(417,53,1,2,1,3,1),\n" +
            "(418,53,2,2,1,2,1),\n" +
            "(419,53,3,2,1,4,1),\n" +
            "(420,53,4,1,1,2,1),\n" +
            "(421,53,5,1,1,4,1),\n" +
            "(422,53,6,1,1,2,1),\n" +
            "(423,53,7,1,1,4,1),\n" +
            "(424,53,8,2,1,4,1),\n" +
            "(425,54,1,1,1,2,1),\n" +
            "(426,54,2,1,1,4,1),\n" +
            "(427,54,3,1,1,1,1),\n" +
            "(428,54,4,2,1,1,1),\n" +
            "(429,54,5,1,1,4,1),\n" +
            "(430,54,6,2,1,4,1),\n" +
            "(431,54,7,1,1,4,1),\n" +
            "(432,54,8,1,1,2,1),\n" +
            "(433,55,1,2,1,4,1),\n" +
            "(434,55,2,1,1,3,1),\n" +
            "(435,55,3,2,1,4,1),\n" +
            "(436,55,4,2,1,3,1),\n" +
            "(437,55,5,1,1,2,1),\n" +
            "(438,55,6,1,1,1,1),\n" +
            "(439,55,7,2,1,1,1),\n" +
            "(440,55,8,2,1,2,1),\n" +
            "(441,56,1,2,1,4,1),\n" +
            "(442,56,2,1,1,2,1),\n" +
            "(443,56,3,2,1,4,1),\n" +
            "(444,56,4,1,1,2,1),\n" +
            "(445,56,5,2,1,4,1),\n" +
            "(446,56,6,2,1,4,1),\n" +
            "(447,56,7,2,1,3,1),\n" +
            "(448,56,8,2,1,1,1),\n" +
            "(449,57,1,2,1,3,1),\n" +
            "(450,57,2,2,1,2,1),\n" +
            "(451,57,3,1,1,4,1),\n" +
            "(452,57,4,2,1,3,1),\n" +
            "(453,57,5,2,1,2,1),\n" +
            "(454,57,6,1,1,4,1),\n" +
            "(455,57,7,2,1,1,1),\n" +
            "(456,57,8,2,1,1,1),\n" +
            "(457,58,1,1,1,1,1),\n" +
            "(458,58,2,1,1,4,1),\n" +
            "(459,58,3,2,1,1,1),\n" +
            "(460,58,4,2,1,1,1),\n" +
            "(461,58,5,2,1,2,1),\n" +
            "(462,58,6,1,1,4,1),\n" +
            "(463,58,7,2,1,2,1),\n" +
            "(464,58,8,2,1,4,1),\n" +
            "(465,59,1,1,1,3,1),\n" +
            "(466,59,2,2,1,4,1),\n" +
            "(467,59,3,2,1,2,1),\n" +
            "(468,59,4,2,1,4,1),\n" +
            "(469,59,5,1,1,1,1),\n" +
            "(470,59,6,2,1,3,1),\n" +
            "(471,59,7,1,1,1,1),\n" +
            "(472,59,8,2,1,1,1),\n" +
            "(473,60,1,1,1,4,1),\n" +
            "(474,60,2,1,1,2,1),\n" +
            "(475,60,3,2,1,1,1),\n" +
            "(476,60,4,1,1,1,1),\n" +
            "(477,60,5,1,1,2,1),\n" +
            "(478,60,6,2,1,3,1),\n" +
            "(479,60,7,1,1,2,1),\n" +
            "(480,60,8,2,1,2,1),\n" +
            "(481,61,1,1,1,1,1),\n" +
            "(482,61,2,2,1,2,1),\n" +
            "(483,61,3,2,1,4,1),\n" +
            "(484,61,4,2,1,2,1),\n" +
            "(485,61,5,1,1,1,1),\n" +
            "(486,61,6,1,1,4,1),\n" +
            "(487,61,7,1,1,4,1),\n" +
            "(488,61,8,1,1,4,1),\n" +
            "(489,62,1,2,1,2,1),\n" +
            "(490,62,2,1,1,2,1),\n" +
            "(491,62,3,1,1,4,1),\n" +
            "(492,62,4,2,1,1,1),\n" +
            "(493,62,5,1,1,3,1),\n" +
            "(494,62,6,1,1,1,1),\n" +
            "(495,62,7,1,1,1,1),\n" +
            "(496,62,8,2,1,4,1),\n" +
            "(497,63,1,1,1,2,1),\n" +
            "(498,63,2,2,1,1,1),\n" +
            "(499,63,3,1,1,3,1),\n" +
            "(500,63,4,1,1,3,1),\n" +
            "(501,63,5,1,1,3,1),\n" +
            "(502,63,6,1,1,3,1),\n" +
            "(503,63,7,1,1,3,1),\n" +
            "(504,63,8,1,1,1,1),\n" +
            "(505,64,1,1,1,3,1),\n" +
            "(506,64,2,2,1,2,1),\n" +
            "(507,64,3,1,1,4,1),\n" +
            "(508,64,4,2,1,3,1),\n" +
            "(509,64,5,1,1,4,1),\n" +
            "(510,64,6,2,1,4,1),\n" +
            "(511,64,7,2,1,3,1),\n" +
            "(512,64,8,1,1,1,1),\n" +
            "(513,65,1,1,1,3,1),\n" +
            "(514,65,2,1,1,2,1),\n" +
            "(515,65,3,1,1,4,1),\n" +
            "(516,65,4,1,1,2,1),\n" +
            "(517,65,5,2,1,4,1),\n" +
            "(518,65,6,1,1,3,1),\n" +
            "(519,65,7,1,1,3,1),\n" +
            "(520,65,8,2,1,2,1),\n" +
            "(521,66,1,2,1,3,1),\n" +
            "(522,66,2,2,1,3,1),\n" +
            "(523,66,3,1,1,4,1),\n" +
            "(524,66,4,2,1,2,1),\n" +
            "(525,66,5,1,1,4,1),\n" +
            "(526,66,6,2,1,2,1),\n" +
            "(527,66,7,2,1,3,1),\n" +
            "(528,66,8,1,1,3,1),\n" +
            "(529,67,1,2,1,4,1),\n" +
            "(530,67,2,1,1,4,1),\n" +
            "(531,67,3,2,1,3,1),\n" +
            "(532,67,4,1,1,1,1),\n" +
            "(533,67,5,2,1,4,1),\n" +
            "(534,67,6,2,1,3,1),\n" +
            "(535,67,7,2,1,2,1),\n" +
            "(536,67,8,1,1,2,1),\n" +
            "(537,68,1,1,1,3,1),\n" +
            "(538,68,2,2,1,1,1),\n" +
            "(539,68,3,2,1,3,1),\n" +
            "(540,68,4,2,1,1,1),\n" +
            "(541,68,5,1,1,3,1),\n" +
            "(542,68,6,1,1,2,1),\n" +
            "(543,68,7,2,1,4,1),\n" +
            "(544,68,8,1,1,4,1),\n" +
            "(545,69,1,2,1,2,1),\n" +
            "(546,69,2,1,1,4,1),\n" +
            "(547,69,3,2,1,4,1),\n" +
            "(548,69,4,1,1,1,1),\n" +
            "(549,69,5,2,1,1,1),\n" +
            "(550,69,6,1,1,1,1),\n" +
            "(551,69,7,1,1,2,1),\n" +
            "(552,69,8,1,1,1,1),\n" +
            "(553,70,1,2,1,2,1),\n" +
            "(554,70,2,2,1,1,1),\n" +
            "(555,70,3,2,1,2,1),\n" +
            "(556,70,4,2,1,3,1),\n" +
            "(557,70,5,2,1,2,1),\n" +
            "(558,70,6,1,1,2,1),\n" +
            "(559,70,7,2,1,4,1),\n" +
            "(560,70,8,1,1,1,1),\n" +
            "(561,71,1,1,1,3,1),\n" +
            "(562,71,2,2,1,1,1),\n" +
            "(563,71,3,2,1,3,1),\n" +
            "(564,71,4,2,1,4,1),\n" +
            "(565,71,5,1,1,1,1),\n" +
            "(566,71,6,2,1,2,1),\n" +
            "(567,71,7,2,1,1,1),\n" +
            "(568,71,8,1,1,2,1),\n" +
            "(569,72,1,2,1,4,1),\n" +
            "(570,72,2,2,1,2,1),\n" +
            "(571,72,3,2,1,2,1),\n" +
            "(572,72,4,2,1,2,1),\n" +
            "(573,72,5,1,1,2,1),\n" +
            "(574,72,6,2,1,2,1),\n" +
            "(575,72,7,2,1,3,1),\n" +
            "(576,72,8,2,1,1,1),\n" +
            "(577,73,1,2,1,4,1),\n" +
            "(578,73,2,2,1,1,1),\n" +
            "(579,73,3,1,1,2,1),\n" +
            "(580,73,4,2,1,2,1),\n" +
            "(581,73,5,2,1,1,1),\n" +
            "(582,73,6,1,1,3,1),\n" +
            "(583,73,7,1,1,4,1),\n" +
            "(584,73,8,1,1,4,1),\n" +
            "(585,74,1,2,1,3,1),\n" +
            "(586,74,2,2,1,4,1),\n" +
            "(587,74,3,1,1,4,1),\n" +
            "(588,74,4,2,1,4,1),\n" +
            "(589,74,5,2,1,4,1),\n" +
            "(590,74,6,1,1,1,1),\n" +
            "(591,74,7,2,1,3,1),\n" +
            "(592,74,8,2,1,4,1),\n" +
            "(593,75,1,2,1,3,1),\n" +
            "(594,75,2,2,1,1,1),\n" +
            "(595,75,3,2,1,2,1),\n" +
            "(596,75,4,2,1,2,1),\n" +
            "(597,75,5,1,1,1,1),\n" +
            "(598,75,6,1,1,3,1),\n" +
            "(599,75,7,1,1,4,1),\n" +
            "(600,75,8,2,1,3,1),\n" +
            "(601,76,1,1,1,3,1),\n" +
            "(602,76,2,1,1,3,1),\n" +
            "(603,76,3,2,1,3,1),\n" +
            "(604,76,4,2,1,1,1),\n" +
            "(605,76,5,1,1,1,1),\n" +
            "(606,76,6,2,1,2,1),\n" +
            "(607,76,7,2,1,4,1),\n" +
            "(608,76,8,2,1,3,1),\n" +
            "(609,77,1,2,1,3,1),\n" +
            "(610,77,2,1,1,4,1),\n" +
            "(611,77,3,1,1,1,1),\n" +
            "(612,77,4,2,1,1,1),\n" +
            "(613,77,5,2,1,1,1),\n" +
            "(614,77,6,2,1,3,1),\n" +
            "(615,77,7,2,1,1,1),\n" +
            "(616,77,8,1,1,1,1),\n" +
            "(617,78,1,1,1,4,1),\n" +
            "(618,78,2,1,1,2,1),\n" +
            "(619,78,3,1,1,1,1),\n" +
            "(620,78,4,2,1,2,1),\n" +
            "(621,78,5,2,1,3,1),\n" +
            "(622,78,6,1,1,1,1),\n" +
            "(623,78,7,2,1,1,1),\n" +
            "(624,78,8,1,1,1,1),\n" +
            "(625,79,1,2,1,3,1),\n" +
            "(626,79,2,2,1,1,1),\n" +
            "(627,79,3,1,1,1,1),\n" +
            "(628,79,4,1,1,4,1),\n" +
            "(629,79,5,1,1,2,1),\n" +
            "(630,79,6,2,1,4,1),\n" +
            "(631,79,7,2,1,3,1),\n" +
            "(632,79,8,2,1,1,1),\n" +
            "(633,80,1,1,1,3,1),\n" +
            "(634,80,2,2,1,2,1),\n" +
            "(635,80,3,1,1,2,1),\n" +
            "(636,80,4,2,1,4,1),\n" +
            "(637,80,5,1,1,1,1),\n" +
            "(638,80,6,2,1,4,1),\n" +
            "(639,80,7,2,1,2,1),\n" +
            "(640,80,8,1,1,1,1),\n" +
            "(641,81,1,2,1,1,1),\n" +
            "(642,81,2,2,1,2,1),\n" +
            "(643,81,3,2,1,2,1),\n" +
            "(644,81,4,1,1,1,1),\n" +
            "(645,81,5,1,1,3,1),\n" +
            "(646,81,6,2,1,1,1),\n" +
            "(647,81,7,1,1,3,1),\n" +
            "(648,81,8,1,1,2,1),\n" +
            "(649,82,1,2,1,3,1),\n" +
            "(650,82,2,2,1,2,1),\n" +
            "(651,82,3,1,1,3,1),\n" +
            "(652,82,4,2,1,1,1),\n" +
            "(653,82,5,1,1,4,1),\n" +
            "(654,82,6,1,1,4,1),\n" +
            "(655,82,7,2,1,4,1),\n" +
            "(656,82,8,2,1,2,1),\n" +
            "(657,83,1,2,1,2,1),\n" +
            "(658,83,2,1,1,3,1),\n" +
            "(659,83,3,2,1,1,1),\n" +
            "(660,83,4,1,1,1,1),\n" +
            "(661,83,5,2,1,4,1),\n" +
            "(662,83,6,2,1,3,1),\n" +
            "(663,83,7,2,1,3,1),\n" +
            "(664,83,8,1,1,2,1),\n" +
            "(665,84,1,1,1,4,1),\n" +
            "(666,84,2,1,1,3,1),\n" +
            "(667,84,3,1,1,4,1),\n" +
            "(668,84,4,2,1,3,1),\n" +
            "(669,84,5,2,1,4,1),\n" +
            "(670,84,6,2,1,3,1),\n" +
            "(671,84,7,2,1,3,1),\n" +
            "(672,84,8,2,1,2,1),\n" +
            "(673,85,1,2,1,1,1),\n" +
            "(674,85,2,1,1,4,1),\n" +
            "(675,85,3,1,1,4,1),\n" +
            "(676,85,4,1,1,3,1),\n" +
            "(677,85,5,2,1,3,1),\n" +
            "(678,85,6,1,1,3,1),\n" +
            "(679,85,7,2,1,4,1),\n" +
            "(680,85,8,1,1,1,1),\n" +
            "(681,86,1,2,1,1,1),\n" +
            "(682,86,2,1,1,4,1),\n" +
            "(683,86,3,2,1,2,1),\n" +
            "(684,86,4,1,1,4,1),\n" +
            "(685,86,5,2,1,3,1),\n" +
            "(686,86,6,2,1,3,1),\n" +
            "(687,86,7,2,1,4,1),\n" +
            "(688,86,8,1,1,3,1),\n" +
            "(689,87,1,1,1,3,1),\n" +
            "(690,87,2,1,1,4,1),\n" +
            "(691,87,3,1,1,1,1),\n" +
            "(692,87,4,2,1,1,1),\n" +
            "(693,87,5,1,1,4,1),\n" +
            "(694,87,6,2,1,1,1),\n" +
            "(695,87,7,2,1,1,1),\n" +
            "(696,87,8,1,1,1,1),\n" +
            "(697,88,1,1,1,1,1),\n" +
            "(698,88,2,1,1,2,1),\n" +
            "(699,88,3,1,1,2,1),\n" +
            "(700,88,4,2,1,4,1),\n" +
            "(701,88,5,2,1,3,1),\n" +
            "(702,88,6,2,1,1,1),\n" +
            "(703,88,7,2,1,4,1),\n" +
            "(704,88,8,1,1,4,1),\n" +
            "(705,89,1,2,1,3,1),\n" +
            "(706,89,2,2,1,1,1),\n" +
            "(707,89,3,1,1,4,1),\n" +
            "(708,89,4,1,1,2,1),\n" +
            "(709,89,5,1,1,1,1),\n" +
            "(710,89,6,2,1,2,1),\n" +
            "(711,89,7,2,1,2,1),\n" +
            "(712,89,8,2,1,2,1),\n" +
            "(713,90,1,1,1,1,1),\n" +
            "(714,90,2,1,1,3,1),\n" +
            "(715,90,3,1,1,2,1),\n" +
            "(716,90,4,1,1,2,1),\n" +
            "(717,90,5,1,1,2,1),\n" +
            "(718,90,6,2,1,4,1),\n" +
            "(719,90,7,2,1,2,1),\n" +
            "(720,90,8,2,1,4,1),\n" +
            "(721,91,1,2,1,2,1),\n" +
            "(722,91,2,1,1,2,1),\n" +
            "(723,91,3,2,1,2,1),\n" +
            "(724,91,4,1,1,1,1),\n" +
            "(725,91,5,1,1,1,1),\n" +
            "(726,91,6,1,1,4,1),\n" +
            "(727,91,7,1,1,4,1),\n" +
            "(728,91,8,2,1,4,1),\n" +
            "(729,92,1,1,1,2,1),\n" +
            "(730,92,2,2,1,2,1),\n" +
            "(731,92,3,1,1,3,1),\n" +
            "(732,92,4,2,1,2,1),\n" +
            "(733,92,5,2,1,1,1),\n" +
            "(734,92,6,1,1,3,1),\n" +
            "(735,92,7,1,1,3,1),\n" +
            "(736,92,8,1,1,4,1),\n" +
            "(737,93,1,1,1,1,1),\n" +
            "(738,93,2,1,1,1,1),\n" +
            "(739,93,3,1,1,4,1),\n" +
            "(740,93,4,2,1,2,1),\n" +
            "(741,93,5,1,1,2,1),\n" +
            "(742,93,6,1,1,3,1),\n" +
            "(743,93,7,2,1,3,1),\n" +
            "(744,93,8,1,1,4,1),\n" +
            "(745,94,1,2,1,3,1),\n" +
            "(746,94,2,2,1,3,1),\n" +
            "(747,94,3,1,1,2,1),\n" +
            "(748,94,4,2,1,3,1),\n" +
            "(749,94,5,2,1,1,1),\n" +
            "(750,94,6,1,1,3,1),\n" +
            "(751,94,7,2,1,4,1),\n" +
            "(752,94,8,1,1,3,1),\n" +
            "(753,95,1,1,1,1,1),\n" +
            "(754,95,2,1,1,2,1),\n" +
            "(755,95,3,2,1,2,1),\n" +
            "(756,95,4,1,1,4,1),\n" +
            "(757,95,5,1,1,2,1),\n" +
            "(758,95,6,1,1,2,1),\n" +
            "(759,95,7,1,1,3,1),\n" +
            "(760,95,8,1,1,4,1),\n" +
            "(761,96,1,2,1,3,1),\n" +
            "(762,96,2,2,1,1,1),\n" +
            "(763,96,3,1,1,3,1),\n" +
            "(764,96,4,1,1,3,1),\n" +
            "(765,96,5,2,1,3,1),\n" +
            "(766,96,6,1,1,2,1),\n" +
            "(767,96,7,2,1,3,1),\n" +
            "(768,96,8,1,1,1,1),\n" +
            "(769,97,1,2,1,1,1),\n" +
            "(770,97,2,2,1,3,1),\n" +
            "(771,97,3,2,1,4,1),\n" +
            "(772,97,4,2,1,1,1),\n" +
            "(773,97,5,1,1,2,1),\n" +
            "(774,97,6,1,1,3,1),\n" +
            "(775,97,7,1,1,4,1),\n" +
            "(776,97,8,2,1,3,1),\n" +
            "(777,98,1,2,1,1,1),\n" +
            "(778,98,2,1,1,4,1),\n" +
            "(779,98,3,2,1,2,1),\n" +
            "(780,98,4,1,1,3,1),\n" +
            "(781,98,5,1,1,3,1),\n" +
            "(782,98,6,1,1,3,1),\n" +
            "(783,98,7,1,1,4,1),\n" +
            "(784,98,8,2,1,1,1),\n" +
            "(785,99,1,2,1,1,1),\n" +
            "(786,99,2,1,1,2,1),\n" +
            "(787,99,3,2,1,2,1),\n" +
            "(788,99,4,2,1,2,1),\n" +
            "(789,99,5,2,1,4,1),\n" +
            "(790,99,6,1,1,1,1),\n" +
            "(791,99,7,2,1,1,1),\n" +
            "(792,99,8,1,1,2,1),\n" +
            "(793,100,1,2,1,1,1),\n" +
            "(794,100,2,2,1,4,1),\n" +
            "(795,100,3,1,1,3,1),\n" +
            "(796,100,4,1,1,2,1),\n" +
            "(797,100,5,2,1,4,1),\n" +
            "(798,100,6,1,1,3,1),\n" +
            "(799,100,7,2,1,3,1);";

    private static final String INSERTS_MATRICULA3 = "INSERT INTO MATRICULA VALUES(800,100,8,2,1,2,1),\n" +
            "(801,101,1,1,1,2,1),\n" +
            "(802,101,2,2,1,3,1),\n" +
            "(803,101,3,1,1,2,1),\n" +
            "(804,101,4,2,1,4,1),\n" +
            "(805,101,5,2,1,3,1),\n" +
            "(806,101,6,2,1,2,1),\n" +
            "(807,101,7,2,1,4,1),\n" +
            "(808,101,8,2,1,1,1),\n" +
            "(809,102,1,2,1,2,1),\n" +
            "(810,102,2,2,1,2,1),\n" +
            "(811,102,3,2,1,3,1),\n" +
            "(812,102,4,2,1,2,1),\n" +
            "(813,102,5,2,1,4,1),\n" +
            "(814,102,6,2,1,1,1),\n" +
            "(815,102,7,2,1,1,1),\n" +
            "(816,102,8,1,1,1,1),\n" +
            "(817,103,1,2,1,2,1),\n" +
            "(818,103,2,1,1,2,1),\n" +
            "(819,103,3,1,1,3,1),\n" +
            "(820,103,4,2,1,2,1),\n" +
            "(821,103,5,2,1,2,1),\n" +
            "(822,103,6,1,1,1,1),\n" +
            "(823,103,7,1,1,1,1),\n" +
            "(824,103,8,1,1,3,1),\n" +
            "(825,104,1,2,1,4,1),\n" +
            "(826,104,2,1,1,3,1),\n" +
            "(827,104,3,2,1,4,1),\n" +
            "(828,104,4,2,1,2,1),\n" +
            "(829,104,5,2,1,1,1),\n" +
            "(830,104,6,2,1,4,1),\n" +
            "(831,104,7,1,1,2,1),\n" +
            "(832,104,8,1,1,1,1),\n" +
            "(833,105,1,2,1,3,1),\n" +
            "(834,105,2,1,1,2,1),\n" +
            "(835,105,3,1,1,3,1),\n" +
            "(836,105,4,1,1,2,1),\n" +
            "(837,105,5,2,1,1,1),\n" +
            "(838,105,6,1,1,2,1),\n" +
            "(839,105,7,1,1,4,1),\n" +
            "(840,105,8,1,1,3,1),\n" +
            "(841,106,1,1,1,3,1),\n" +
            "(842,106,2,2,1,3,1),\n" +
            "(843,106,3,2,1,2,1),\n" +
            "(844,106,4,2,1,1,1),\n" +
            "(845,106,5,1,1,3,1),\n" +
            "(846,106,6,2,1,2,1),\n" +
            "(847,106,7,1,1,3,1),\n" +
            "(848,106,8,1,1,1,1),\n" +
            "(849,107,1,2,1,3,1),\n" +
            "(850,107,2,1,1,2,1),\n" +
            "(851,107,3,1,1,2,1),\n" +
            "(852,107,4,2,1,2,1),\n" +
            "(853,107,5,2,1,3,1),\n" +
            "(854,107,6,1,1,4,1),\n" +
            "(855,107,7,1,1,1,1),\n" +
            "(856,107,8,1,1,1,1),\n" +
            "(857,108,1,1,1,4,1),\n" +
            "(858,108,2,2,1,1,1),\n" +
            "(859,108,3,1,1,3,1),\n" +
            "(860,108,4,2,1,3,1),\n" +
            "(861,108,5,1,1,2,1),\n" +
            "(862,108,6,2,1,1,1),\n" +
            "(863,108,7,2,1,4,1),\n" +
            "(864,108,8,2,1,3,1),\n" +
            "(865,109,1,2,1,3,1),\n" +
            "(866,109,2,2,1,1,1),\n" +
            "(867,109,3,2,1,3,1),\n" +
            "(868,109,4,2,1,1,1),\n" +
            "(869,109,5,2,1,4,1),\n" +
            "(870,109,6,1,1,2,1),\n" +
            "(871,109,7,1,1,1,1),\n" +
            "(872,109,8,2,1,4,1),\n" +
            "(873,110,1,2,1,4,1),\n" +
            "(874,110,2,1,1,4,1),\n" +
            "(875,110,3,1,1,3,1),\n" +
            "(876,110,4,1,1,4,1),\n" +
            "(877,110,5,2,1,2,1),\n" +
            "(878,110,6,2,1,1,1),\n" +
            "(879,110,7,1,1,3,1),\n" +
            "(880,110,8,2,1,2,1),\n" +
            "(881,111,1,1,1,1,1),\n" +
            "(882,111,2,1,1,4,1),\n" +
            "(883,111,3,2,1,3,1),\n" +
            "(884,111,4,2,1,4,1),\n" +
            "(885,111,5,1,1,4,1),\n" +
            "(886,111,6,2,1,3,1),\n" +
            "(887,111,7,2,1,4,1),\n" +
            "(888,111,8,2,1,4,1),\n" +
            "(889,112,1,1,1,3,1),\n" +
            "(890,112,2,2,1,2,1),\n" +
            "(891,112,3,2,1,3,1),\n" +
            "(892,112,4,2,1,3,1),\n" +
            "(893,112,5,1,1,3,1),\n" +
            "(894,112,6,1,1,1,1),\n" +
            "(895,112,7,2,1,1,1),\n" +
            "(896,112,8,1,1,1,1),\n" +
            "(897,113,1,1,1,3,1),\n" +
            "(898,113,2,2,1,4,1),\n" +
            "(899,113,3,1,1,1,1),\n" +
            "(900,113,4,1,1,4,1),\n" +
            "(901,113,5,2,1,2,1),\n" +
            "(902,113,6,1,1,2,1),\n" +
            "(903,113,7,2,1,2,1),\n" +
            "(904,113,8,2,1,4,1),\n" +
            "(905,114,1,2,1,3,1),\n" +
            "(906,114,2,2,1,1,1),\n" +
            "(907,114,3,1,1,4,1),\n" +
            "(908,114,4,1,1,1,1),\n" +
            "(909,114,5,2,1,3,1),\n" +
            "(910,114,6,1,1,2,1),\n" +
            "(911,114,7,1,1,3,1),\n" +
            "(912,114,8,2,1,4,1),\n" +
            "(913,115,1,1,1,2,1),\n" +
            "(914,115,2,1,1,2,1),\n" +
            "(915,115,3,1,1,1,1),\n" +
            "(916,115,4,1,1,1,1),\n" +
            "(917,115,5,1,1,1,1),\n" +
            "(918,115,6,2,1,3,1),\n" +
            "(919,115,7,1,1,3,1),\n" +
            "(920,115,8,2,1,2,1),\n" +
            "(921,116,1,1,1,3,1),\n" +
            "(922,116,2,2,1,3,1),\n" +
            "(923,116,3,2,1,3,1),\n" +
            "(924,116,4,2,1,3,1),\n" +
            "(925,116,5,2,1,4,1),\n" +
            "(926,116,6,2,1,4,1),\n" +
            "(927,116,7,1,1,4,1),\n" +
            "(928,116,8,1,1,1,1),\n" +
            "(929,117,1,2,1,4,1),\n" +
            "(930,117,2,2,1,3,1),\n" +
            "(931,117,3,1,1,3,1),\n" +
            "(932,117,4,1,1,1,1),\n" +
            "(933,117,5,1,1,3,1),\n" +
            "(934,117,6,2,1,3,1),\n" +
            "(935,117,7,1,1,1,1),\n" +
            "(936,117,8,2,1,2,1),\n" +
            "(937,118,1,1,1,4,1),\n" +
            "(938,118,2,2,1,3,1),\n" +
            "(939,118,3,2,1,3,1),\n" +
            "(940,118,4,1,1,3,1),\n" +
            "(941,118,5,1,1,3,1),\n" +
            "(942,118,6,2,1,1,1),\n" +
            "(943,118,7,2,1,2,1),\n" +
            "(944,118,8,1,1,4,1),\n" +
            "(945,119,1,1,1,1,1),\n" +
            "(946,119,2,2,1,3,1),\n" +
            "(947,119,3,2,1,4,1),\n" +
            "(948,119,4,1,1,4,1),\n" +
            "(949,119,5,2,1,4,1),\n" +
            "(950,119,6,2,1,1,1),\n" +
            "(951,119,7,2,1,1,1),\n" +
            "(952,119,8,1,1,2,1),\n" +
            "(953,120,1,2,1,4,1),\n" +
            "(954,120,2,2,1,3,1),\n" +
            "(955,120,3,1,1,2,1),\n" +
            "(956,120,4,2,1,3,1),\n" +
            "(957,120,5,1,1,4,1),\n" +
            "(958,120,6,1,1,4,1),\n" +
            "(959,120,7,1,1,4,1),\n" +
            "(960,120,8,2,1,4,1),\n" +
            "(961,121,1,1,1,1,1),\n" +
            "(962,121,2,1,1,4,1),\n" +
            "(963,121,3,1,1,3,1),\n" +
            "(964,121,4,1,1,4,1),\n" +
            "(965,121,5,2,1,4,1),\n" +
            "(966,121,6,2,1,1,1),\n" +
            "(967,121,7,2,1,2,1),\n" +
            "(968,121,8,2,1,1,1),\n" +
            "(969,122,1,1,1,3,1),\n" +
            "(970,122,2,1,1,1,1),\n" +
            "(971,122,3,2,1,1,1),\n" +
            "(972,122,4,2,1,4,1),\n" +
            "(973,122,5,2,1,4,1),\n" +
            "(974,122,6,1,1,1,1),\n" +
            "(975,122,7,2,1,3,1),\n" +
            "(976,122,8,1,1,4,1),\n" +
            "(977,123,1,2,1,1,1),\n" +
            "(978,123,2,2,1,4,1),\n" +
            "(979,123,3,1,1,1,1),\n" +
            "(980,123,4,2,1,2,1),\n" +
            "(981,123,5,2,1,4,1),\n" +
            "(982,123,6,1,1,3,1),\n" +
            "(983,123,7,1,1,3,1),\n" +
            "(984,123,8,2,1,4,1),\n" +
            "(985,124,1,2,1,2,1),\n" +
            "(986,124,2,1,1,2,1),\n" +
            "(987,124,3,1,1,4,1),\n" +
            "(988,124,4,1,1,2,1),\n" +
            "(989,124,5,1,1,4,1),\n" +
            "(990,124,6,1,1,1,1),\n" +
            "(991,124,7,1,1,2,1),\n" +
            "(992,124,8,1,1,4,1),\n" +
            "(993,125,1,2,1,4,1),\n" +
            "(994,125,2,1,1,4,1),\n" +
            "(995,125,3,1,1,4,1),\n" +
            "(996,125,4,1,1,2,1),\n" +
            "(997,125,5,2,1,2,1),\n" +
            "(998,125,6,2,1,3,1),\n" +
            "(999,125,7,2,1,1,1),\n" +
            "(1000,125,8,2,1,2,1),\n" +
            "(1001,126,1,2,1,2,1),\n" +
            "(1002,126,2,1,1,4,1),\n" +
            "(1003,126,3,2,1,2,1),\n" +
            "(1004,126,4,2,1,3,1),\n" +
            "(1005,126,5,2,1,2,1),\n" +
            "(1006,126,6,2,1,3,1),\n" +
            "(1007,126,7,2,1,2,1),\n" +
            "(1008,126,8,2,1,4,1),\n" +
            "(1009,127,1,2,1,1,1),\n" +
            "(1010,127,2,2,1,4,1),\n" +
            "(1011,127,3,1,1,1,1),\n" +
            "(1012,127,4,1,1,3,1),\n" +
            "(1013,127,5,2,1,3,1),\n" +
            "(1014,127,6,2,1,1,1),\n" +
            "(1015,127,7,1,1,2,1),\n" +
            "(1016,127,8,2,1,1,1),\n" +
            "(1017,128,1,2,1,3,1),\n" +
            "(1018,128,2,1,1,4,1),\n" +
            "(1019,128,3,2,1,1,1),\n" +
            "(1020,128,4,1,1,4,1),\n" +
            "(1021,128,5,2,1,1,1),\n" +
            "(1022,128,6,1,1,4,1),\n" +
            "(1023,128,7,2,1,2,1),\n" +
            "(1024,128,8,1,1,3,1),\n" +
            "(1025,129,1,1,1,4,1),\n" +
            "(1026,129,2,1,1,4,1),\n" +
            "(1027,129,3,1,1,2,1),\n" +
            "(1028,129,4,1,1,3,1),\n" +
            "(1029,129,5,1,1,4,1),\n" +
            "(1030,129,6,2,1,2,1),\n" +
            "(1031,129,7,2,1,3,1),\n" +
            "(1032,129,8,2,1,1,1),\n" +
            "(1033,130,1,2,1,4,1),\n" +
            "(1034,130,2,1,1,2,1),\n" +
            "(1035,130,3,1,1,4,1),\n" +
            "(1036,130,4,1,1,1,1),\n" +
            "(1037,130,5,2,1,1,1),\n" +
            "(1038,130,6,1,1,1,1),\n" +
            "(1039,130,7,2,1,1,1),\n" +
            "(1040,130,8,2,1,4,1),\n" +
            "(1041,131,1,1,1,3,1),\n" +
            "(1042,131,2,2,1,4,1),\n" +
            "(1043,131,3,1,1,3,1),\n" +
            "(1044,131,4,1,1,2,1),\n" +
            "(1045,131,5,2,1,4,1),\n" +
            "(1046,131,6,1,1,4,1),\n" +
            "(1047,131,7,2,1,3,1),\n" +
            "(1048,131,8,2,1,1,1),\n" +
            "(1049,132,1,1,1,3,1),\n" +
            "(1050,132,2,1,1,2,1),\n" +
            "(1051,132,3,2,1,1,1),\n" +
            "(1052,132,4,2,1,1,1),\n" +
            "(1053,132,5,2,1,3,1),\n" +
            "(1054,132,6,2,1,2,1),\n" +
            "(1055,132,7,1,1,1,1),\n" +
            "(1056,132,8,2,1,4,1),\n" +
            "(1057,133,1,2,1,2,1),\n" +
            "(1058,133,2,2,1,4,1),\n" +
            "(1059,133,3,1,1,3,1),\n" +
            "(1060,133,4,2,1,4,1),\n" +
            "(1061,133,5,1,1,4,1),\n" +
            "(1062,133,6,2,1,2,1),\n" +
            "(1063,133,7,1,1,1,1),\n" +
            "(1064,133,8,2,1,3,1),\n" +
            "(1065,134,1,1,1,4,1),\n" +
            "(1066,134,2,2,1,3,1),\n" +
            "(1067,134,3,2,1,4,1),\n" +
            "(1068,134,4,2,1,3,1),\n" +
            "(1069,134,5,1,1,1,1),\n" +
            "(1070,134,6,1,1,2,1),\n" +
            "(1071,134,7,2,1,2,1),\n" +
            "(1072,134,8,2,1,2,1),\n" +
            "(1073,135,1,2,1,3,1),\n" +
            "(1074,135,2,2,1,3,1),\n" +
            "(1075,135,3,2,1,3,1),\n" +
            "(1076,135,4,2,1,2,1),\n" +
            "(1077,135,5,1,1,2,1),\n" +
            "(1078,135,6,2,1,3,1),\n" +
            "(1079,135,7,1,1,1,1),\n" +
            "(1080,135,8,2,1,1,1),\n" +
            "(1081,136,1,1,1,1,1),\n" +
            "(1082,136,2,2,1,3,1),\n" +
            "(1083,136,3,1,1,2,1),\n" +
            "(1084,136,4,2,1,3,1),\n" +
            "(1085,136,5,2,1,3,1),\n" +
            "(1086,136,6,1,1,2,1),\n" +
            "(1087,136,7,2,1,2,1),\n" +
            "(1088,136,8,2,1,3,1),\n" +
            "(1089,137,1,1,1,1,1),\n" +
            "(1090,137,2,2,1,3,1),\n" +
            "(1091,137,3,2,1,2,1),\n" +
            "(1092,137,4,1,1,2,1),\n" +
            "(1093,137,5,1,1,4,1),\n" +
            "(1094,137,6,2,1,4,1),\n" +
            "(1095,137,7,2,1,4,1),\n" +
            "(1096,137,8,1,1,1,1),\n" +
            "(1097,138,1,2,1,3,1),\n" +
            "(1098,138,2,1,1,4,1),\n" +
            "(1099,138,3,2,1,4,1),\n" +
            "(1100,138,4,2,1,2,1),\n" +
            "(1101,138,5,2,1,2,1),\n" +
            "(1102,138,6,2,1,4,1),\n" +
            "(1103,138,7,2,1,2,1),\n" +
            "(1104,138,8,1,1,2,1),\n" +
            "(1105,139,1,2,1,3,1),\n" +
            "(1106,139,2,2,1,4,1),\n" +
            "(1107,139,3,1,1,3,1),\n" +
            "(1108,139,4,2,1,2,1),\n" +
            "(1109,139,5,1,1,4,1),\n" +
            "(1110,139,6,1,1,2,1),\n" +
            "(1111,139,7,1,1,1,1),\n" +
            "(1112,139,8,1,1,3,1),\n" +
            "(1113,140,1,2,1,1,1),\n" +
            "(1114,140,2,1,1,4,1),\n" +
            "(1115,140,3,2,1,4,1),\n" +
            "(1116,140,4,2,1,4,1),\n" +
            "(1117,140,5,2,1,2,1),\n" +
            "(1118,140,6,1,1,1,1),\n" +
            "(1119,140,7,2,1,3,1),\n" +
            "(1120,140,8,2,1,1,1),\n" +
            "(1121,141,1,2,1,1,1),\n" +
            "(1122,141,2,2,1,2,1),\n" +
            "(1123,141,3,1,1,2,1),\n" +
            "(1124,141,4,2,1,4,1),\n" +
            "(1125,141,5,1,1,3,1),\n" +
            "(1126,141,6,1,1,1,1),\n" +
            "(1127,141,7,2,1,2,1),\n" +
            "(1128,141,8,1,1,4,1),\n" +
            "(1129,142,1,1,1,4,1),\n" +
            "(1130,142,2,2,1,4,1),\n" +
            "(1131,142,3,2,1,3,1),\n" +
            "(1132,142,4,1,1,1,1),\n" +
            "(1133,142,5,2,1,3,1),\n" +
            "(1134,142,6,2,1,3,1),\n" +
            "(1135,142,7,1,1,3,1),\n" +
            "(1136,142,8,2,1,2,1),\n" +
            "(1137,143,1,2,1,3,1),\n" +
            "(1138,143,2,1,1,1,1),\n" +
            "(1139,143,3,1,1,3,1),\n" +
            "(1140,143,4,2,1,3,1),\n" +
            "(1141,143,5,1,1,4,1),\n" +
            "(1142,143,6,1,1,1,1),\n" +
            "(1143,143,7,2,1,3,1),\n" +
            "(1144,143,8,1,1,4,1),\n" +
            "(1145,144,1,2,1,3,1),\n" +
            "(1146,144,2,1,1,4,1),\n" +
            "(1147,144,3,2,1,3,1),\n" +
            "(1148,144,4,1,1,4,1),\n" +
            "(1149,144,5,2,1,1,1),\n" +
            "(1150,144,6,2,1,1,1),\n" +
            "(1151,144,7,2,1,1,1),\n" +
            "(1152,144,8,1,1,1,1),\n" +
            "(1153,145,1,1,1,2,1),\n" +
            "(1154,145,2,1,1,1,1),\n" +
            "(1155,145,3,1,1,3,1),\n" +
            "(1156,145,4,2,1,2,1),\n" +
            "(1157,145,5,1,1,3,1),\n" +
            "(1158,145,6,1,1,3,1),\n" +
            "(1159,145,7,2,1,4,1),\n" +
            "(1160,145,8,1,1,1,1),\n" +
            "(1161,146,1,2,1,1,1),\n" +
            "(1162,146,2,2,1,1,1),\n" +
            "(1163,146,3,1,1,3,1),\n" +
            "(1164,146,4,1,1,4,1),\n" +
            "(1165,146,5,1,1,1,1),\n" +
            "(1166,146,6,1,1,3,1),\n" +
            "(1167,146,7,1,1,4,1),\n" +
            "(1168,146,8,2,1,1,1),\n" +
            "(1169,147,1,1,1,1,1),\n" +
            "(1170,147,2,1,1,2,1),\n" +
            "(1171,147,3,1,1,1,1),\n" +
            "(1172,147,4,2,1,2,1),\n" +
            "(1173,147,5,2,1,2,1),\n" +
            "(1174,147,6,1,1,4,1),\n" +
            "(1175,147,7,2,1,3,1),\n" +
            "(1176,147,8,2,1,3,1),\n" +
            "(1177,148,1,2,1,2,1),\n" +
            "(1178,148,2,2,1,3,1),\n" +
            "(1179,148,3,1,1,2,1),\n" +
            "(1180,148,4,1,1,4,1),\n" +
            "(1181,148,5,2,1,2,1),\n" +
            "(1182,148,6,2,1,1,1),\n" +
            "(1183,148,7,1,1,3,1),\n" +
            "(1184,148,8,2,1,3,1),\n" +
            "(1185,149,1,1,1,2,1),\n" +
            "(1186,149,2,2,1,3,1),\n" +
            "(1187,149,3,2,1,4,1),\n" +
            "(1188,149,4,2,1,3,1),\n" +
            "(1189,149,5,2,1,3,1),\n" +
            "(1190,149,6,1,1,3,1),\n" +
            "(1191,149,7,2,1,1,1),\n" +
            "(1192,149,8,2,1,2,1),\n" +
            "(1193,150,1,1,1,3,1),\n" +
            "(1194,150,2,2,1,3,1),\n" +
            "(1195,150,3,2,1,4,1),\n" +
            "(1196,150,4,1,1,2,1),\n" +
            "(1197,150,5,1,1,1,1),\n" +
            "(1198,150,6,1,1,3,1),\n" +
            "(1199,150,7,1,1,1,1);";

    private static final String INSERTS_MATRICULA4 = "INSERT INTO MATRICULA VALUES(1200,150,8,2,1,1,1),\n" +
            "(1201,151,1,1,1,1,1),\n" +
            "(1202,151,2,2,1,4,1),\n" +
            "(1203,151,3,2,1,2,1),\n" +
            "(1204,151,4,1,1,3,1),\n" +
            "(1205,151,5,2,1,2,1),\n" +
            "(1206,151,6,2,1,1,1),\n" +
            "(1207,151,7,2,1,4,1),\n" +
            "(1208,151,8,2,1,1,1),\n" +
            "(1209,152,1,1,1,1,1),\n" +
            "(1210,152,2,1,1,4,1),\n" +
            "(1211,152,3,2,1,4,1),\n" +
            "(1212,152,4,2,1,2,1),\n" +
            "(1213,152,5,2,1,1,1),\n" +
            "(1214,152,6,2,1,1,1),\n" +
            "(1215,152,7,1,1,3,1),\n" +
            "(1216,152,8,1,1,3,1),\n" +
            "(1217,153,1,1,1,2,1),\n" +
            "(1218,153,2,2,1,3,1),\n" +
            "(1219,153,3,1,1,4,1),\n" +
            "(1220,153,4,1,1,2,1),\n" +
            "(1221,153,5,1,1,2,1),\n" +
            "(1222,153,6,1,1,2,1),\n" +
            "(1223,153,7,2,1,2,1),\n" +
            "(1224,153,8,1,1,4,1),\n" +
            "(1225,154,1,2,1,2,1),\n" +
            "(1226,154,2,2,1,4,1),\n" +
            "(1227,154,3,2,1,3,1),\n" +
            "(1228,154,4,2,1,4,1),\n" +
            "(1229,154,5,2,1,1,1),\n" +
            "(1230,154,6,1,1,4,1),\n" +
            "(1231,154,7,1,1,4,1),\n" +
            "(1232,154,8,2,1,1,1),\n" +
            "(1233,155,1,1,1,2,1),\n" +
            "(1234,155,2,2,1,2,1),\n" +
            "(1235,155,3,2,1,3,1),\n" +
            "(1236,155,4,1,1,3,1),\n" +
            "(1237,155,5,1,1,1,1),\n" +
            "(1238,155,6,2,1,3,1),\n" +
            "(1239,155,7,2,1,2,1),\n" +
            "(1240,155,8,1,1,1,1),\n" +
            "(1241,156,1,1,1,3,1),\n" +
            "(1242,156,2,1,1,3,1),\n" +
            "(1243,156,3,1,1,3,1),\n" +
            "(1244,156,4,1,1,1,1),\n" +
            "(1245,156,5,2,1,2,1),\n" +
            "(1246,156,6,2,1,1,1),\n" +
            "(1247,156,7,2,1,4,1),\n" +
            "(1248,156,8,1,1,1,1),\n" +
            "(1249,157,1,1,1,3,1),\n" +
            "(1250,157,2,1,1,3,1),\n" +
            "(1251,157,3,2,1,1,1),\n" +
            "(1252,157,4,1,1,2,1),\n" +
            "(1253,157,5,2,1,1,1),\n" +
            "(1254,157,6,2,1,1,1),\n" +
            "(1255,157,7,2,1,3,1),\n" +
            "(1256,157,8,2,1,3,1),\n" +
            "(1257,158,1,1,1,3,1),\n" +
            "(1258,158,2,1,1,1,1),\n" +
            "(1259,158,3,1,1,2,1),\n" +
            "(1260,158,4,2,1,4,1),\n" +
            "(1261,158,5,2,1,4,1),\n" +
            "(1262,158,6,1,1,4,1),\n" +
            "(1263,158,7,2,1,1,1),\n" +
            "(1264,158,8,2,1,1,1),\n" +
            "(1265,159,1,1,1,1,1),\n" +
            "(1266,159,2,2,1,1,1),\n" +
            "(1267,159,3,1,1,3,1),\n" +
            "(1268,159,4,1,1,4,1),\n" +
            "(1269,159,5,1,1,1,1),\n" +
            "(1270,159,6,2,1,4,1),\n" +
            "(1271,159,7,2,1,4,1),\n" +
            "(1272,159,8,2,1,4,1),\n" +
            "(1273,160,1,1,1,3,1),\n" +
            "(1274,160,2,1,1,4,1),\n" +
            "(1275,160,3,2,1,2,1),\n" +
            "(1276,160,4,1,1,1,1),\n" +
            "(1277,160,5,2,1,3,1),\n" +
            "(1278,160,6,1,1,1,1),\n" +
            "(1279,160,7,2,1,3,1),\n" +
            "(1280,160,8,2,1,3,1),\n" +
            "(1281,161,1,1,1,3,1),\n" +
            "(1282,161,2,1,1,1,1),\n" +
            "(1283,161,3,1,1,1,1),\n" +
            "(1284,161,4,1,1,2,1),\n" +
            "(1285,161,5,1,1,1,1),\n" +
            "(1286,161,6,1,1,1,1),\n" +
            "(1287,161,7,1,1,4,1),\n" +
            "(1288,161,8,1,1,1,1),\n" +
            "(1289,162,1,2,1,1,1),\n" +
            "(1290,162,2,1,1,2,1),\n" +
            "(1291,162,3,1,1,1,1),\n" +
            "(1292,162,4,1,1,1,1),\n" +
            "(1293,162,5,2,1,4,1),\n" +
            "(1294,162,6,2,1,1,1),\n" +
            "(1295,162,7,2,1,2,1),\n" +
            "(1296,162,8,2,1,2,1),\n" +
            "(1297,163,1,1,1,1,1),\n" +
            "(1298,163,2,1,1,2,1),\n" +
            "(1299,163,3,1,1,2,1),\n" +
            "(1300,163,4,1,1,2,1),\n" +
            "(1301,163,5,1,1,3,1),\n" +
            "(1302,163,6,2,1,2,1),\n" +
            "(1303,163,7,1,1,1,1),\n" +
            "(1304,163,8,1,1,3,1),\n" +
            "(1305,164,1,1,1,2,1),\n" +
            "(1306,164,2,1,1,2,1),\n" +
            "(1307,164,3,2,1,2,1),\n" +
            "(1308,164,4,2,1,4,1),\n" +
            "(1309,164,5,1,1,2,1),\n" +
            "(1310,164,6,1,1,3,1),\n" +
            "(1311,164,7,2,1,1,1),\n" +
            "(1312,164,8,2,1,4,1),\n" +
            "(1313,165,1,2,1,3,1),\n" +
            "(1314,165,2,1,1,4,1),\n" +
            "(1315,165,3,2,1,4,1),\n" +
            "(1316,165,4,2,1,4,1),\n" +
            "(1317,165,5,2,1,3,1),\n" +
            "(1318,165,6,2,1,3,1),\n" +
            "(1319,165,7,1,1,2,1),\n" +
            "(1320,165,8,1,1,4,1),\n" +
            "(1321,166,1,2,1,1,1),\n" +
            "(1322,166,2,2,1,4,1),\n" +
            "(1323,166,3,1,1,2,1),\n" +
            "(1324,166,4,1,1,3,1),\n" +
            "(1325,166,5,1,1,3,1),\n" +
            "(1326,166,6,2,1,3,1),\n" +
            "(1327,166,7,1,1,2,1),\n" +
            "(1328,166,8,1,1,4,1),\n" +
            "(1329,167,1,1,1,4,1),\n" +
            "(1330,167,2,1,1,1,1),\n" +
            "(1331,167,3,1,1,1,1),\n" +
            "(1332,167,4,1,1,4,1),\n" +
            "(1333,167,5,1,1,3,1),\n" +
            "(1334,167,6,1,1,1,1),\n" +
            "(1335,167,7,1,1,4,1),\n" +
            "(1336,167,8,2,1,2,1),\n" +
            "(1337,168,1,1,1,1,1),\n" +
            "(1338,168,2,1,1,3,1),\n" +
            "(1339,168,3,2,1,1,1),\n" +
            "(1340,168,4,1,1,2,1),\n" +
            "(1341,168,5,1,1,3,1),\n" +
            "(1342,168,6,2,1,2,1),\n" +
            "(1343,168,7,1,1,2,1),\n" +
            "(1344,168,8,1,1,2,1),\n" +
            "(1345,169,1,2,1,2,1),\n" +
            "(1346,169,2,1,1,3,1),\n" +
            "(1347,169,3,1,1,3,1),\n" +
            "(1348,169,4,2,1,1,1),\n" +
            "(1349,169,5,2,1,2,1),\n" +
            "(1350,169,6,2,1,2,1),\n" +
            "(1351,169,7,2,1,2,1),\n" +
            "(1352,169,8,1,1,4,1),\n" +
            "(1353,170,1,2,1,4,1),\n" +
            "(1354,170,2,2,1,4,1),\n" +
            "(1355,170,3,1,1,1,1),\n" +
            "(1356,170,4,2,1,1,1),\n" +
            "(1357,170,5,2,1,4,1),\n" +
            "(1358,170,6,1,1,3,1),\n" +
            "(1359,170,7,1,1,2,1),\n" +
            "(1360,170,8,2,1,3,1),\n" +
            "(1361,171,1,1,1,4,1),\n" +
            "(1362,171,2,2,1,4,1),\n" +
            "(1363,171,3,2,1,2,1),\n" +
            "(1364,171,4,1,1,3,1),\n" +
            "(1365,171,5,1,1,3,1),\n" +
            "(1366,171,6,2,1,4,1),\n" +
            "(1367,171,7,1,1,2,1),\n" +
            "(1368,171,8,1,1,2,1),\n" +
            "(1369,172,1,1,1,4,1),\n" +
            "(1370,172,2,2,1,1,1),\n" +
            "(1371,172,3,2,1,1,1),\n" +
            "(1372,172,4,1,1,4,1),\n" +
            "(1373,172,5,1,1,3,1),\n" +
            "(1374,172,6,1,1,4,1),\n" +
            "(1375,172,7,2,1,4,1),\n" +
            "(1376,172,8,1,1,1,1),\n" +
            "(1377,173,1,1,1,1,1),\n" +
            "(1378,173,2,1,1,4,1),\n" +
            "(1379,173,3,2,1,3,1),\n" +
            "(1380,173,4,1,1,4,1),\n" +
            "(1381,173,5,2,1,4,1),\n" +
            "(1382,173,6,2,1,4,1),\n" +
            "(1383,173,7,2,1,2,1),\n" +
            "(1384,173,8,1,1,4,1),\n" +
            "(1385,174,1,1,1,3,1),\n" +
            "(1386,174,2,1,1,3,1),\n" +
            "(1387,174,3,1,1,1,1),\n" +
            "(1388,174,4,1,1,4,1),\n" +
            "(1389,174,5,1,1,3,1),\n" +
            "(1390,174,6,1,1,1,1),\n" +
            "(1391,174,7,2,1,1,1),\n" +
            "(1392,174,8,1,1,3,1),\n" +
            "(1393,175,1,2,1,3,1),\n" +
            "(1394,175,2,2,1,1,1),\n" +
            "(1395,175,3,1,1,1,1),\n" +
            "(1396,175,4,2,1,1,1),\n" +
            "(1397,175,5,1,1,4,1),\n" +
            "(1398,175,6,2,1,1,1),\n" +
            "(1399,175,7,2,1,1,1),\n" +
            "(1400,175,8,2,1,3,1),\n" +
            "(1401,176,1,2,1,2,1),\n" +
            "(1402,176,2,1,1,1,1),\n" +
            "(1403,176,3,1,1,3,1),\n" +
            "(1404,176,4,2,1,3,1),\n" +
            "(1405,176,5,2,1,2,1),\n" +
            "(1406,176,6,1,1,3,1),\n" +
            "(1407,176,7,1,1,4,1),\n" +
            "(1408,176,8,1,1,1,1),\n" +
            "(1409,177,1,2,1,1,1),\n" +
            "(1410,177,2,2,1,2,1),\n" +
            "(1411,177,3,2,1,1,1),\n" +
            "(1412,177,4,2,1,2,1),\n" +
            "(1413,177,5,1,1,1,1),\n" +
            "(1414,177,6,1,1,4,1),\n" +
            "(1415,177,7,1,1,4,1),\n" +
            "(1416,177,8,1,1,1,1),\n" +
            "(1417,178,1,2,1,3,1),\n" +
            "(1418,178,2,1,1,3,1),\n" +
            "(1419,178,3,2,1,4,1),\n" +
            "(1420,178,4,1,1,3,1),\n" +
            "(1421,178,5,2,1,1,1),\n" +
            "(1422,178,6,2,1,4,1),\n" +
            "(1423,178,7,1,1,1,1),\n" +
            "(1424,178,8,2,1,2,1),\n" +
            "(1425,179,1,1,1,3,1),\n" +
            "(1426,179,2,1,1,3,1),\n" +
            "(1427,179,3,1,1,3,1),\n" +
            "(1428,179,4,2,1,4,1),\n" +
            "(1429,179,5,2,1,1,1),\n" +
            "(1430,179,6,1,1,3,1),\n" +
            "(1431,179,7,1,1,1,1),\n" +
            "(1432,179,8,1,1,1,1),\n" +
            "(1433,180,1,1,1,4,1),\n" +
            "(1434,180,2,2,1,2,1),\n" +
            "(1435,180,3,1,1,1,1),\n" +
            "(1436,180,4,1,1,1,1),\n" +
            "(1437,180,5,1,1,1,1),\n" +
            "(1438,180,6,1,1,4,1),\n" +
            "(1439,180,7,1,1,4,1),\n" +
            "(1440,180,8,2,1,2,1),\n" +
            "(1441,181,1,1,1,2,1),\n" +
            "(1442,181,2,2,1,4,1),\n" +
            "(1443,181,3,1,1,2,1),\n" +
            "(1444,181,4,1,1,2,1),\n" +
            "(1445,181,5,2,1,4,1),\n" +
            "(1446,181,6,2,1,2,1),\n" +
            "(1447,181,7,1,1,2,1),\n" +
            "(1448,181,8,2,1,3,1),\n" +
            "(1449,182,1,1,1,4,1),\n" +
            "(1450,182,2,2,1,4,1),\n" +
            "(1451,182,3,2,1,4,1),\n" +
            "(1452,182,4,1,1,3,1),\n" +
            "(1453,182,5,1,1,4,1),\n" +
            "(1454,182,6,1,1,3,1),\n" +
            "(1455,182,7,2,1,3,1),\n" +
            "(1456,182,8,2,1,1,1),\n" +
            "(1457,183,1,2,1,3,1),\n" +
            "(1458,183,2,2,1,1,1),\n" +
            "(1459,183,3,1,1,2,1),\n" +
            "(1460,183,4,1,1,2,1),\n" +
            "(1461,183,5,2,1,3,1),\n" +
            "(1462,183,6,2,1,1,1),\n" +
            "(1463,183,7,1,1,4,1),\n" +
            "(1464,183,8,2,1,2,1),\n" +
            "(1465,184,1,2,1,1,1),\n" +
            "(1466,184,2,1,1,4,1),\n" +
            "(1467,184,3,2,1,4,1),\n" +
            "(1468,184,4,1,1,3,1),\n" +
            "(1469,184,5,1,1,1,1),\n" +
            "(1470,184,6,2,1,1,1),\n" +
            "(1471,184,7,1,1,4,1),\n" +
            "(1472,184,8,1,1,1,1),\n" +
            "(1473,185,1,2,1,2,1),\n" +
            "(1474,185,2,2,1,2,1),\n" +
            "(1475,185,3,1,1,1,1),\n" +
            "(1476,185,4,1,1,3,1),\n" +
            "(1477,185,5,2,1,1,1),\n" +
            "(1478,185,6,2,1,2,1),\n" +
            "(1479,185,7,1,1,1,1),\n" +
            "(1480,185,8,2,1,2,1),\n" +
            "(1481,186,1,2,1,1,1),\n" +
            "(1482,186,2,1,1,3,1),\n" +
            "(1483,186,3,2,1,4,1),\n" +
            "(1484,186,4,2,1,2,1),\n" +
            "(1485,186,5,2,1,4,1),\n" +
            "(1486,186,6,2,1,1,1),\n" +
            "(1487,186,7,2,1,1,1),\n" +
            "(1488,186,8,2,1,4,1),\n" +
            "(1489,187,1,1,1,2,1),\n" +
            "(1490,187,2,1,1,2,1),\n" +
            "(1491,187,3,2,1,2,1),\n" +
            "(1492,187,4,2,1,4,1),\n" +
            "(1493,187,5,1,1,2,1),\n" +
            "(1494,187,6,1,1,4,1),\n" +
            "(1495,187,7,2,1,2,1),\n" +
            "(1496,187,8,2,1,1,1),\n" +
            "(1497,188,1,1,1,3,1),\n" +
            "(1498,188,2,2,1,3,1),\n" +
            "(1499,188,3,1,1,3,1),\n" +
            "(1500,188,4,2,1,3,1),\n" +
            "(1501,188,5,1,1,2,1),\n" +
            "(1502,188,6,1,1,2,1),\n" +
            "(1503,188,7,2,1,4,1),\n" +
            "(1504,188,8,2,1,3,1),\n" +
            "(1505,189,1,1,1,3,1),\n" +
            "(1506,189,2,1,1,2,1),\n" +
            "(1507,189,3,2,1,4,1),\n" +
            "(1508,189,4,1,1,1,1),\n" +
            "(1509,189,5,2,1,4,1),\n" +
            "(1510,189,6,2,1,4,1),\n" +
            "(1511,189,7,1,1,3,1),\n" +
            "(1512,189,8,1,1,3,1),\n" +
            "(1513,190,1,2,1,2,1),\n" +
            "(1514,190,2,1,1,1,1),\n" +
            "(1515,190,3,2,1,3,1),\n" +
            "(1516,190,4,2,1,2,1),\n" +
            "(1517,190,5,2,1,1,1),\n" +
            "(1518,190,6,1,1,4,1),\n" +
            "(1519,190,7,2,1,3,1),\n" +
            "(1520,190,8,1,1,2,1),\n" +
            "(1521,191,1,1,1,3,1),\n" +
            "(1522,191,2,1,1,4,1),\n" +
            "(1523,191,3,2,1,4,1),\n" +
            "(1524,191,4,2,1,2,1),\n" +
            "(1525,191,5,1,1,1,1),\n" +
            "(1526,191,6,2,1,2,1),\n" +
            "(1527,191,7,1,1,2,1),\n" +
            "(1528,191,8,1,1,1,1),\n" +
            "(1529,192,1,1,1,4,1),\n" +
            "(1530,192,2,2,1,1,1),\n" +
            "(1531,192,3,1,1,4,1),\n" +
            "(1532,192,4,1,1,2,1),\n" +
            "(1533,192,5,2,1,2,1),\n" +
            "(1534,192,6,2,1,2,1),\n" +
            "(1535,192,7,2,1,1,1),\n" +
            "(1536,192,8,1,1,2,1),\n" +
            "(1537,193,1,2,1,4,1),\n" +
            "(1538,193,2,2,1,3,1),\n" +
            "(1539,193,3,2,1,2,1),\n" +
            "(1540,193,4,2,1,3,1),\n" +
            "(1541,193,5,2,1,3,1),\n" +
            "(1542,193,6,1,1,2,1),\n" +
            "(1543,193,7,1,1,1,1),\n" +
            "(1544,193,8,2,1,4,1),\n" +
            "(1545,194,1,1,1,3,1),\n" +
            "(1546,194,2,1,1,1,1),\n" +
            "(1547,194,3,2,1,2,1),\n" +
            "(1548,194,4,1,1,1,1),\n" +
            "(1549,194,5,1,1,1,1),\n" +
            "(1550,194,6,2,1,1,1),\n" +
            "(1551,194,7,1,1,1,1),\n" +
            "(1552,194,8,2,1,3,1),\n" +
            "(1553,195,1,1,1,1,1),\n" +
            "(1554,195,2,2,1,4,1),\n" +
            "(1555,195,3,1,1,3,1),\n" +
            "(1556,195,4,2,1,4,1),\n" +
            "(1557,195,5,2,1,3,1),\n" +
            "(1558,195,6,1,1,3,1),\n" +
            "(1559,195,7,2,1,3,1),\n" +
            "(1560,195,8,2,1,2,1),\n" +
            "(1561,196,1,2,1,3,1),\n" +
            "(1562,196,2,1,1,2,1),\n" +
            "(1563,196,3,2,1,1,1),\n" +
            "(1564,196,4,1,1,1,1),\n" +
            "(1565,196,5,1,1,4,1),\n" +
            "(1566,196,6,1,1,1,1),\n" +
            "(1567,196,7,1,1,3,1),\n" +
            "(1568,196,8,1,1,4,1),\n" +
            "(1569,197,1,1,1,2,1),\n" +
            "(1570,197,2,2,1,3,1),\n" +
            "(1571,197,3,1,1,2,1),\n" +
            "(1572,197,4,1,1,2,1),\n" +
            "(1573,197,5,2,1,4,1),\n" +
            "(1574,197,6,1,1,2,1),\n" +
            "(1575,197,7,2,1,1,1),\n" +
            "(1576,197,8,2,1,1,1),\n" +
            "(1577,198,1,2,1,1,1),\n" +
            "(1578,198,2,2,1,4,1),\n" +
            "(1579,198,3,1,1,4,1),\n" +
            "(1580,198,4,2,1,3,1),\n" +
            "(1581,198,5,2,1,3,1),\n" +
            "(1582,198,6,1,1,4,1),\n" +
            "(1583,198,7,2,1,1,1),\n" +
            "(1584,198,8,1,1,1,1),\n" +
            "(1585,199,1,2,1,1,1),\n" +
            "(1586,199,2,2,1,1,1),\n" +
            "(1587,199,3,2,1,1,1),\n" +
            "(1588,199,4,2,1,1,1),\n" +
            "(1589,199,5,2,1,1,1),\n" +
            "(1590,199,6,2,1,3,1),\n" +
            "(1591,199,7,2,1,4,1),\n" +
            "(1592,199,8,1,1,3,1),\n" +
            "(1593,200,1,2,1,3,1),\n" +
            "(1594,200,2,1,1,3,1),\n" +
            "(1595,200,3,2,1,3,1),\n" +
            "(1596,200,4,2,1,2,1),\n" +
            "(1597,200,5,1,1,3,1),\n" +
            "(1598,200,6,1,1,3,1),\n" +
            "(1599,200,7,2,1,3,1),\n" +
            "(1600,200,8,2,1,1,1);\n";

}
