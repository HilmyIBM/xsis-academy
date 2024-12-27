--DDL Create table
CREATE TABLE coba01 (
    id INTEGER,
    nama VARCHAR(20) NOT NULL,
    deckripsi VARCHAR(255)
);

CREATE TABLE "cobaPertama" (
    id INTEGER,
    nama VARCHAR(20) NOT NULL,
    deckripsi VARCHAR(255)
)

CREATE TABLE pelanggan(
    id SERIAL,
    nama VARCHAR(20),
    alamat varchar(255),
    email VARCHAR(255) UNIQUE,
    CONSTRAINT pk_pelanggan PRIMARY KEY (id)
);

--Create Database
CREATE DATABASE coba;


--DML Read data
SELECT * FROM coba01;
SELECT * FROM information_schema.TABLES where table_name='coba01';
SELECT * FROM information_schema.TABLES;
SELECT * FROM pg_stat_activity;

SELECT * FROM

--Show All Available Database
SELECT * FROM pg_database;


--Read columns schema from table (describe table schema)
SELECT * FROM information_schema.columns WHERE table_name = 'coba01';
SELECT * FROM information_schema.table_constraints WHERE table_name = 'cobaPertama';

SELECT * FROM information_schema.table_constraints WHERE table_name = 'pelanggan';

SELECT * FROM information_schema.TABLES WHERE table_catalog = 'coba' and table_name like '%index'

--DML Drop table
DROP TABLE coba01;

--DROP Database
DROP DATABASE coba;

--Kill Database Session
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE pid='175';


--DDL Alter Table
ALTER TABLE coba01
    ADD PRIMARY KEY (id);

ALTER TABLE "cobaPertama"
    ADD COLUMN id_coba INTEGER;

ALTER TABLE "cobaPertama"
    ADD CONSTRAINT "FK_coba01_cobaPertama"
    FOREIGN KEY (id_coba)
    REFERENCES coba01 (id);

ALTER TABLE "cobaPertama"
    ADD CONSTRAINT pk_cobapertama
    PRIMARY KEY(id);


ALTER TABLE coba01
    RENAME COLUMN deckripsi to deskripsi;


--Buat index
CREATE INDEX idx_coba01
ON coba01 (nama);