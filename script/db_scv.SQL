CREATE TABLE tiene_vehiculo ( 
	placa varchar(10) NOT NULL,
	ci numeric(10) NOT NULL,
	fecha_reg timestamp NOT NULL
)
;

CREATE SEQUENCE marca_marca_id_seq INCREMENT 1 START 1
;

CREATE TABLE marca ( 
	marca_id bigint DEFAULT nextval(('marca_marca_id_seq'::text)::regclass) NOT NULL,
	nombre varchar(100) NOT NULL
)
;

CREATE TABLE vehiculo_visita ( 
	placa varchar(10) NOT NULL,
	marca_id bigint,
	estado boolean NOT NULL
)
;

CREATE SEQUENCE area_area_id_seq INCREMENT 1 START 1
;

CREATE TABLE area ( 
	area_id bigint DEFAULT nextval(('area_area_id_seq'::text)::regclass) NOT NULL,
	descripcion varchar(100) NOT NULL,
	estado boolean NOT NULL
)
;

CREATE TABLE reg_salida ( 
	placa varchar(10) NOT NULL,
	tranca_id bigint NOT NULL,
	fecha_hora timestamp NOT NULL
)
;

CREATE TABLE visita ( 
	ci numeric(10) NOT NULL,
	nombre varchar(50) NOT NULL,
	apellido varchar(100) NOT NULL,
	sexo char(1) NOT NULL,
	estado boolean NOT NULL
)
;

CREATE TABLE reg_ingreso ( 
	placa varchar(10) NOT NULL,
	tranca_id bigint NOT NULL,
	fecha_hora timestamp NOT NULL
)
;

CREATE SEQUENCE tranca_tranca_id_seq INCREMENT 1 START 1
;

CREATE TABLE tranca ( 
	tranca_id bigint DEFAULT nextval(('tranca_tranca_id_seq'::text)::regclass) NOT NULL,
	area_id bigint,
	nombre varchar(100) NOT NULL,
	tipo smallint NOT NULL,
	estado boolean NOT NULL
)
;

CREATE TABLE propietario_vehiculo ( 
	placa varchar(10) NOT NULL,
	ci numeric(10) NOT NULL,
	fecha_reg timestamp NOT NULL
)
;

CREATE TABLE vehiculo ( 
	placa varchar(10) NOT NULL,
	marca_id bigint,
	rfid integer NOT NULL,
	foto bytea,
	modelo varchar(50),
	estado boolean NOT NULL
)
;

CREATE SEQUENCE telefono_telefono_id_seq INCREMENT 1 START 1
;

CREATE TABLE telefono ( 
	telefono_id bigint DEFAULT nextval(('telefono_telefono_id_seq'::text)::regclass) NOT NULL,
	ci numeric(10),
	numero numeric(15) NOT NULL
)
;

CREATE TABLE propietario ( 
	ci numeric(10) NOT NULL,
	nombre varchar(50) NOT NULL,
	apellido varchar(100) NOT NULL,
	foto bytea,
	sexo char(1) NOT NULL,
	estado boolean NOT NULL
)
;


ALTER TABLE tiene_vehiculo ADD CONSTRAINT PK_TieneVehiculo 
	PRIMARY KEY (placa, ci)
;


ALTER TABLE marca ADD CONSTRAINT PK_Marca 
	PRIMARY KEY (marca_id)
;


ALTER TABLE vehiculo_visita ADD CONSTRAINT PK_VehiculoVisita 
	PRIMARY KEY (placa)
;


ALTER TABLE area ADD CONSTRAINT PK_Area 
	PRIMARY KEY (area_id)
;


ALTER TABLE reg_salida ADD CONSTRAINT PK_RegSalida 
	PRIMARY KEY (placa, tranca_id)
;


ALTER TABLE visita ADD CONSTRAINT PK_Visita 
	PRIMARY KEY (ci)
;


ALTER TABLE reg_ingreso ADD CONSTRAINT PK_RegIngreso 
	PRIMARY KEY (placa, tranca_id)
;


ALTER TABLE tranca ADD CONSTRAINT PK_Tranca 
	PRIMARY KEY (tranca_id)
;


ALTER TABLE propietario_vehiculo ADD CONSTRAINT PK_PropietarioVehiculo 
	PRIMARY KEY (placa, ci)
;


ALTER TABLE vehiculo ADD CONSTRAINT PK_Vehiculo 
	PRIMARY KEY (placa)
;


ALTER TABLE telefono ADD CONSTRAINT PK_Telefono 
	PRIMARY KEY (telefono_id)
;


ALTER TABLE propietario ADD CONSTRAINT PK_Propietario 
	PRIMARY KEY (ci)
;




ALTER TABLE tiene_vehiculo ADD CONSTRAINT FK_tiene_vehiculo_visita 
	FOREIGN KEY (ci) REFERENCES visita (ci)
;

ALTER TABLE tiene_vehiculo ADD CONSTRAINT FK_TieneVehiculo_VehiculoVisita 
	FOREIGN KEY (placa) REFERENCES vehiculo_visita (placa)
;

ALTER TABLE vehiculo_visita ADD CONSTRAINT FK_VehiculoVisita_Marca 
	FOREIGN KEY (marca_id) REFERENCES marca (marca_id)
;

ALTER TABLE reg_salida ADD CONSTRAINT FK_reg_salida_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo (placa)
;

ALTER TABLE reg_salida ADD CONSTRAINT FK_RegSalida_Tranca 
	FOREIGN KEY (tranca_id) REFERENCES tranca (tranca_id)
;

ALTER TABLE reg_ingreso ADD CONSTRAINT FK_reg_ingreso_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo (placa)
;

ALTER TABLE reg_ingreso ADD CONSTRAINT FK_RegIngreso_Tranca 
	FOREIGN KEY (tranca_id) REFERENCES tranca (tranca_id)
;

ALTER TABLE tranca ADD CONSTRAINT FK_Tranca_Area 
	FOREIGN KEY (area_id) REFERENCES area (area_id)
;

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_propietario_vehiculo_vehiculo 
	FOREIGN KEY (placa) REFERENCES vehiculo (placa)
;

ALTER TABLE propietario_vehiculo ADD CONSTRAINT FK_PropietarioVehiculo_Propietario 
	FOREIGN KEY (ci) REFERENCES propietario (ci)
;

ALTER TABLE vehiculo ADD CONSTRAINT FK_Vehiculo_Marca 
	FOREIGN KEY (marca_id) REFERENCES marca (marca_id)
;

ALTER TABLE telefono ADD CONSTRAINT FK_Telefono_Propietario 
	FOREIGN KEY (ci) REFERENCES propietario (ci)
;
