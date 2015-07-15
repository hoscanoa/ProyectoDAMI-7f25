PRAGMA FOREIGN_KEYS=ON;
CREATE TABLE ESTADOS (
  estadoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  descripcion VARCHAR(45) NULL
);

CREATE TABLE MODALIDADES_ESTUDIOS(
    modalidadEstudioId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    descripcion VARCHAR(45) NOT NULL UNIQUE,
    abreviatura CHAR(2) NOT NULL UNIQUE
);

CREATE TABLE ALUMNOS (
  alumnoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  nombres VARCHAR(20) NOT NULL,
  apellidoPaterno VARCHAR(20) NOT NULL,
  apellidoMaterno VARCHAR(20) NOT NULL,
  email VARCHAR(40) NOT NULL,
  estadoId INTEGER NOT NULL REFERENCES ESTADOS(estadoId)
);

CREATE TABLE PROFESORES (
  profesorId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  nombres VARCHAR(20) NOT NULL,
  apellidoPaterno VARCHAR(20) NOT NULL,
  apellidoMaterno VARCHAR(20) NOT NULL,
  email VARCHAR(40) NOT NULL UNIQUE,
  username VARCHAR(15) NOT NULL UNIQUE,
  password VARCHAR(15) NOT NULL
);

CREATE TABLE CARRERAS (
  carreraId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  descripcion VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE CURSOS (
  cursoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  codigo CHAR(4) NOT NULL,
  descripcion VARCHAR(45) NOT NULL UNIQUE,
  modalidadEstudioId INTEGER NOT NULL REFERENCES MODALIDADES_ESTUDIOS(modalidadEstudioId)
);

CREATE TABLE CARRERAS_CURSOS (
  carreraCursoId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  carreraId INTEGER NOT NULL REFERENCES CARRERAS(carreraId),
  cursoId INTEGER NOT NULL  REFERENCES CURSOS(cursoId),
  creditos INTEGER NOT NULL
);

CREATE TABLE  TIPO_AULA (
  tipoAulaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  descripcion VARCHAR(20) NULL
);

CREATE TABLE AULAS (
  aulaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  tipoAulaId INTEGER NOT NULL REFERENCES TIPO_AULA(tipoAulaId)
);

CREATE TABLE DIAS (
  diaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  descripcion VARCHAR(15) NULL
);

CREATE TABLE SECCIONES (
  seccionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  descripcion CHAR(4) NULL
);

CREATE TABLE HORARIOS (
  horarioId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),
  profesorId INTEGER NOT NULL REFERENCES PROFESORES(profesorId),
  aulaId INTEGER NOT NULL REFERENCES AULAS(aulaId),
  horaInicio CHAR(6) NOT NULL,
  horaFin CHAR(6) NOT NULL,
  diaId INTEGER NOT NULL REFERENCES DIAS(diaId),
  seccionId INTEGER NOT NULL REFERENCES SECCIONES(seccionId),
  grupo CHAR(2) NOT NULL
);

CREATE TABLE ALUMNOS_HORARIOS (
  alumnoHorarioId INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,
  alumnoId INTEGER NOT NULL REFERENCES ALUMNOS(alumnoId),
  horarioId INTEGER NOT NULL REFERENCES HORARIOS(horarioId)
);

CREATE TABLE EVALUACIONES (
  evaluacionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  descripcion VARCHAR(45) NOT NULL
);

CREATE TABLE CURSOS_EVALUACIONES (
  cursoEvaluacionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),
  evaluacionesId INTEGER NOT NULL REFERENCES EVALUACIONES(evaluacionId),
  porcentaje INTEGER NULL
);

CREATE TABLE  CALIFICACIONES (
  calificacionId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  descripcion VARCHAR(10) NULL,
  nota INTEGER NOT NULL
);

CREATE TABLE REGISTRO_NOTAS (
  registroNotaId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  alumnoId INTEGER NOT NULL REFERENCES ALUMNOS(alumnoId),
  cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),
  evaluacionId INTEGER NOT NULL REFERENCES EVALUACIONES(evaluacionId),
  calificacionesId INTEGER NOT NULL REFERENCES CALIFICACIONES(calificacionesId)
);

CREATE TABLE CICLOS (
	cicloId INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,
	descripcion VARCHAR(7) NOT NULL UNIQUE
);

CREATE TABLE GRUPOS (
	grupoId INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,
	descripcion CHAR(2) NOT NULL UNIQUE
);

CREATE TABLE CARGA_DOCENTE (
	cargaDocenteId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	cursoId INTEGER NOT NULL REFERENCES CURSOS(cursoId),
	profesorId INTEGER NOT NULL REFERENCES PROFESORES(profesorId),
	cicloId INTEGER NOT NULL REFERENCES CICLOS(cicloId),
	seccionId INTEGER NOT NULL REFERENCES SECCIONES(seccionId),
	grupoId INTEGER NOT NULL REFERENCES GRUPOS(grupoId)
);

CREATE TABLE MATRICULA (
	matriculaId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
	alumnoId INTEGER NOT NULL REFERENCES ALUMNOS(alumnoId),
	cicloId INTEGER NOT NULL REFERENCES CICLOS(cicloId),
	seccionId INTEGER NOT NULL REFERENCES SECCIONES(seccionId),
	grupoId INTEGER NOT NULL REFERENCES GRUPOS(grupoId),
	estadoId INTEGER NOT NULL REFERENCES ESTADOS(estadoId)
);

insert into ESTADOS values
(1, 'MATRICULA REGULAR'),
(2, 'RETIRO TEMPORAL'),
(3, 'DESAPROBADO POR INASISTENCIA');
insert into MODALIDADES_ESTUDIOS values
(1, 'CARRERAS TÉCNICAS','AC'),
(2, 'PROGRAMA DE ADELANTOS','AD'),
(3, 'DIPLOMADOS CIBERTEC','DC');
INSERT INTO PROFESORES VALUES
(1,'Chris','Reyes','Powell','pcpowell0@cibertec.edu.pe','cpowell0','abc123'),
(2,'Maria','Boyd','Berry','pmberry1@cibertec.edu.pe','mberry1','FzsZ54d'),
(3,'Jimmy','Roberts','Cruz','pjcruz2@cibertec.edu.pe','jcruz2','XKeeVx'),
(4,'Bruce','Porter','Mills','pbmills3@cibertec.edu.pe','bmills3','agiU4VP'),
(5,'Judy','Mcdonald','Gomez','pjgomez4@cibertec.edu.pe','jgomez4','6lgdUEY'),
(6,'Catherine','Rose','Perkins','pcperkins5@cibertec.edu.pe','cperkins5','LmV9ASAdkG'),
(7,'Earl','Hawkins','Sanchez','pesanchez6@cibertec.edu.pe','esanchez6','398dIPt'),
(8,'Frances','Harris','Sims','pfsims7@cibertec.edu.pe','fsims7','OYy56z'),
(9,'Kelly','Pierce','George','pkgeorge8@cibertec.edu.pe','kgeorge8','f1q5YPEeW'),
(10,'Jean','Freeman','Washington','pjwashington9@cibertec.edu.pe','jwashington9','AGWlTc0nkI'),
(11,'Nicole','Lawrence','Gordon','pngordona@cibertec.edu.pe','ngordona','KGEZWKkW'),
(12,'Stephen','Lane','Burns','psburnsb@cibertec.edu.pe','sburnsb','OKIQh6omIyM'),
(13,'John','Burns','Pierce','pjpiercec@cibertec.edu.pe','jpiercec','C7ylQk1EEgA'),
(14,'Kenneth','Gutierrez','Freeman','pkfreemand@cibertec.edu.pe','kfreemand','pZGwCsook7o'),
(15,'Terry','Bowman','Diaz','ptdiaze@cibertec.edu.pe','tdiaze','WWU3XtUWPjb'),
(16,'Stephanie','Boyd','Mason','psmasonf@cibertec.edu.pe','smasonf','Nhljh64j8r'),
(17,'Evelyn','Ferguson','Pierce','pepierceg@cibertec.edu.pe','epierceg','agzmHNYu'),
(18,'Ashley','Robertson','Freeman','pafreemanh@cibertec.edu.pe','afreemanh','NNrswp'),
(19,'Nancy','Bowman','Rodriguez','pnrodriguezi@cibertec.edu.pe','nrodriguezi','d7cBtbC'),
(20,'Catherine','Owens','Fisher','pcfisherj@cibertec.edu.pe','cfisherj','LrARaoB7l7');
insert into ALUMNOS values
(1, 'Phyllis', 'Gray', 'Webb', 'pwebb0@webmd.com',1),
(2, 'Kimberly', 'Reed', 'Coleman', 'kcoleman1@zdnet.com',1),
(3, 'Virginia', 'George', 'Pierce', 'vpierce2@buzzfeed.com',1),
(4, 'Judy', 'Rivera', 'Williamson', 'jwilliamson3@zdnet.com',1),
(5, 'Ruth', 'Reynolds', 'Day', 'rday4@toplist.cz',1),
(6, 'Heather', 'Ryan', 'Warren', 'hwarren5@imageshack.us',1),
(7, 'Margaret', 'Hansen', 'Montgomery', 'mmontgomery6@salon.com',1),
(8, 'Linda', 'West', 'Wheeler', 'lwheeler7@cnn.com',1),
(9, 'Ruth', 'Murphy', 'Stephens', 'rstephens8@zdnet.com',1),
(10, 'Phyllis', 'Hughes', 'Harvey', 'pharvey9@ehow.com',1),
(11, 'Janice', 'Duncan', 'Harrison', 'jharrisona@shutterfly.com',1),
(12, 'Jacqueline', 'Clark', 'Chavez', 'jchavezb@youtube.com',1),
(13, 'Gloria', 'George', 'Smith', 'gsmithc@reference.com',1),
(14, 'Diana', 'Torres', 'Lopez', 'dlopezd@ifeng.com',1),
(15, 'Shirley', 'Austin', 'Fernandez', 'sfernandeze@altervista.org',1),
(16, 'Susan', 'Lawrence', 'Tucker', 'stuckerf@ning.com',1),
(17, 'Virginia', 'Moreno', 'Simmons', 'vsimmonsg@craigslist.org',1),
(18, 'Betty', 'Hill', 'Diaz', 'bdiazh@unblog.fr',1),
(19, 'Judy', 'Ortiz', 'Stevens', 'jstevensi@archive.org',1),
(20, 'Rebecca', 'Washington', 'Robertson', 'rrobertsonj@ycombinator.com',1),
(21, 'Lillian', 'Banks', 'Moore', 'lmoorek@newyorker.com',1),
(22, 'Louise', 'Sanders', 'Kennedy', 'lkennedyl@smh.com.au',1),
(23, 'Brenda', 'Robertson', 'Spencer', 'bspencerm@cbslocal.com',1),
(24, 'Julie', 'Bishop', 'Wagner', 'jwagnern@360.cn',1),
(25, 'Mildred', 'Johnston', 'Taylor', 'mtayloro@freewebs.com',1),
(26, 'Anna', 'Nichols', 'Wilson', 'awilsonp@newsvine.com',1),
(27, 'Virginia', 'Myers', 'Spencer', 'vspencerq@parallels.com',1),
(28, 'Kelly', 'Hart', 'Roberts', 'krobertsr@seesaa.net',1),
(29, 'Carolyn', 'Burke', 'Lewis', 'clewiss@arstechnica.com',1),
(30, 'Ashley', 'Young', 'Payne', 'apaynet@deviantart.com',1),
(31, 'Amanda', 'Hicks', 'James', 'ajamesu@berkeley.edu',1),
(32, 'Diana', 'White', 'Morris', 'dmorrisv@liveinternet.ru',1),
(33, 'Dorothy', 'James', 'Vasquez', 'dvasquezw@mac.com',1),
(34, 'Dorothy', 'Graham', 'Fields', 'dfieldsx@nifty.com',1),
(35, 'Anne', 'Martin', 'Fisher', 'afishery@quantcast.com',1),
(36, 'Phyllis', 'Long', 'Burke', 'pburkez@smugmug.com',1),
(37, 'Linda', 'Fuller', 'Campbell', 'lcampbell10@deliciousdays.com',1),
(38, 'Tammy', 'Vasquez', 'Hughes', 'thughes11@etsy.com',1),
(39, 'Julie', 'Sims', 'Hamilton', 'jhamilton12@unesco.org',1),
(40, 'Marilyn', 'Brown', 'Daniels', 'mdaniels13@sbwire.com',1),
(41, 'Janice', 'Hansen', 'Hansen', 'jhansen14@blogtalkradio.com',1),
(42, 'Christina', 'Lopez', 'Sanchez', 'csanchez15@weebly.com',1),
(43, 'Ruby', 'Gardner', 'Martin', 'rmartin16@xing.com',1),
(44, 'Paula', 'Crawford', 'Nguyen', 'pnguyen17@oracle.com',1),
(45, 'Karen', 'Wright', 'Oliver', 'koliver18@google.com.br',1),
(46, 'Carolyn', 'Dunn', 'Foster', 'cfoster19@tmall.com',1),
(47, 'Joyce', 'Gomez', 'Gibson', 'jgibson1a@geocities.jp',1),
(48, 'Joan', 'Oliver', 'White', 'jwhite1b@nsw.gov.au',1),
(49, 'Rachel', 'Andrews', 'Rogers', 'rrogers1c@bloomberg.com',1),
(50, 'Diana', 'Rodriguez', 'Frazier', 'dfrazier1d@usgs.gov',1),
(51, 'Martha', 'Collins', 'Collins', 'mcollins1e@dagondesign.com',1),
(52, 'Catherine', 'Lane', 'Rogers', 'crogers1f@qq.com',1),
(53, 'Carol', 'Wallace', 'Carpenter', 'ccarpenter1g@hhs.gov',1),
(54, 'Laura', 'Cooper', 'Wilson', 'lwilson1h@comcast.net',1),
(55, 'Gloria', 'Hudson', 'Williamson', 'gwilliamson1i@rambler.ru',1),
(56, 'Katherine', 'Ramirez', 'Moore', 'kmoore1j@soundcloud.com',1),
(57, 'Sharon', 'Martin', 'Bell', 'sbell1k@arizona.edu',1),
(58, 'Wanda', 'Clark', 'Ferguson', 'wferguson1l@reference.com',1),
(59, 'Martha', 'Anderson', 'Johnston', 'mjohnston1m@salon.com',1),
(60, 'Amy', 'Owens', 'Gardner', 'agardner1n@miibeian.gov.cn',1),
(61, 'Melissa', 'Holmes', 'Jordan', 'mjordan1o@wufoo.com',1),
(62, 'Cynthia', 'Perry', 'Greene', 'cgreene1p@trellian.com',1),
(63, 'Jacqueline', 'Long', 'Boyd', 'jboyd1q@skyrock.com',1),
(64, 'Lori', 'Lynch', 'Green', 'lgreen1r@liveinternet.ru',1),
(65, 'Kimberly', 'Hughes', 'Moreno', 'kmoreno1s@bloglines.com',1),
(66, 'Anne', 'Washington', 'Medina', 'amedina1t@nbcnews.com',1),
(67, 'Bonnie', 'Gardner', 'James', 'bjames1u@amazonaws.com',1),
(68, 'Sharon', 'Perry', 'Ray', 'sray1v@flickr.com',1),
(69, 'Julie', 'Nguyen', 'Wilson', 'jwilson1w@wikimedia.org',1),
(70, 'Joan', 'Jenkins', 'Bailey', 'jbailey1x@va.gov',1),
(71, 'Angela', 'Webb', 'Kelly', 'akelly1y@va.gov',1),
(72, 'Nancy', 'Castillo', 'Hamilton', 'nhamilton1z@ifeng.com',1),
(73, 'Cheryl', 'Edwards', 'Adams', 'cadams20@huffingtonpost.com',1),
(74, 'Jessica', 'Ellis', 'Nelson', 'jnelson21@usatoday.com',1),
(75, 'Jane', 'Clark', 'Young', 'jyoung22@123-reg.co.uk',1),
(76, 'Wanda', 'Johnson', 'Moore', 'wmoore23@webmd.com',1),
(77, 'Robin', 'Hawkins', 'Tucker', 'rtucker24@amazon.co.jp',1),
(78, 'Christine', 'Owens', 'Garrett', 'cgarrett25@mail.ru',1),
(79, 'Kathy', 'Nguyen', 'Richardson', 'krichardson26@microsoft.com',1),
(80, 'Gloria', 'Gordon', 'Murphy', 'gmurphy27@hc360.com',1),
(81, 'Alice', 'Rogers', 'Oliver', 'aoliver28@mac.com',1),
(82, 'Christine', 'Robinson', 'Bradley', 'cbradley29@skype.com',1),
(83, 'Jane', 'Brown', 'Reed', 'jreed2a@mozilla.org',1),
(84, 'Tina', 'Hart', 'Nichols', 'tnichols2b@army.mil',1),
(85, 'Julie', 'Elliott', 'Stephens', 'jstephens2c@techcrunch.com',1),
(86, 'Elizabeth', 'Garrett', 'Lopez', 'elopez2d@t-online.de',1),
(87, 'Lisa', 'Mills', 'Black', 'lblack2e@studiopress.com',1),
(88, 'Jennifer', 'Nichols', 'Hughes', 'jhughes2f@bluehost.com',1),
(89, 'Heather', 'Gutierrez', 'Hunt', 'hhunt2g@cloudflare.com',1),
(90, 'Rose', 'White', 'Ramos', 'rramos2h@examiner.com',1),
(91, 'Beverly', 'Olson', 'Freeman', 'bfreeman2i@elegantthemes.com',1),
(92, 'Cynthia', 'Greene', 'Carpenter', 'ccarpenter2j@google.cn',1),
(93, 'Katherine', 'Green', 'Wallace', 'kwallace2k@rakuten.co.jp',1),
(94, 'Susan', 'Perry', 'Reyes', 'sreyes2l@latimes.com',1),
(95, 'Ann', 'Holmes', 'Kelley', 'akelley2m@wisc.edu',1),
(96, 'Judith', 'Peterson', 'Gonzales', 'jgonzales2n@lycos.com',1),
(97, 'Robin', 'Torres', 'Dean', 'rdean2o@shop-pro.jp',1),
(98, 'Sarah', 'Rose', 'Larson', 'slarson2p@google.fr',1),
(99, 'Laura', 'Stanley', 'Alvarez', 'lalvarez2q@telegraph.co.uk',1),
(100, 'Louise', 'Graham', 'Medina', 'lmedina2r@yahoo.com',1);
insert into DIAS values
(1, 'Lunes'),
(2, 'Martes'),
(3, 'Miércoles'),
(4, 'Jueves'),
(5, 'Viernes'),
(6, 'Sábado'),
(7, 'Domingo');
insert into CURSOS values
(1,'0267','Base de Datos Avanzado ii',1),
(2,'0557','Desarrollo de Aplicaciones Móviles i',1),
(3,'0778','Desarrollo para Entorno Web',1),
(4,'0772','Fundamentos de Calidad de Software',1),
(5,'1352','Inglés Profesional I',1),
(6,'0720','Organización y Constitución de Empresas',1),
(7,'0779','Proyecto de Investigación',1),
(8,'1369','Ética Profesional',1);
insert into CARRERAS values
(1, 'ADMINISTRACIÓN BANCARIA TR'),
(2, 'ADMINISTRACIÓN DE NEGOCIOS INTERNACIONALES TR'),
(3, 'ADMINISTRACIÓN DE RECURSOS HUMANOS TR'),
(4, 'ADMINISTRACIÓN TR'),
(5, 'ADMINISTRACIÓN DE SERVICIOS TURÁSTICOS TR'),
(6, 'ADMINISTRACIÓN Y SISTEMAS TR'),
(7, 'COMPUTACIÓN E INFORMÁTICA TR'),
(8,'CONTABILIDAD TR'),
(9,'DISEÑO DE INTERIORES TR'),
(10,'DISEÑO GRÁFICO TR'),
(11,'INDUSTRIAL Y SISTEMAS TR'),
(12,'MARKETING TR'),
(13,'REDES Y COMUNICACIONES TR');
INSERT INTO TIPO_AULA VALUES
(1,'TEORIA'),
(3,'PRÁCTICA/TALLER'),
(2,'LABORATORIO');