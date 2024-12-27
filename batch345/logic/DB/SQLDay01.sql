--DDL Create; Membuat Object baru di Database--
--CREATE Database
CREATE DATABASE coba;

--CREATE Table
CREATE TABLE coba01 (
    id INTEGER,
    nama VARCHAR(20),
    deckripsi VARCHAR(255)
);

CREATE TABLE "cobaPertama" (
    id INTEGER,
    nama VARCHAR(20),
    deckripsi VARCHAR(255)
);

CREATE TABLE pelanggan (
    id SERIAL,
    nama VARCHAR(20) NOT NULL,
    alamat VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    CONSTRAINT pk_pelanggan PRIMARY KEY (id)
);

--DDL Drop: Membuang Object dari Database
--DROP Table
DROP TABLE coba01;
DROP TABLE "cobaPertama";

--DROP DATABASE
DROP DATABASE coba;

--DDL ALTER: Modifikasi Object Database
--ALTER Table ADD Primary Key Constraints
ALTER TABLE coba01
    ADD PRIMARY KEY (id);

ALTER TABLE "cobaPertama"
    ADD CONSTRAINT pk_cobapertama
    PRIMARY KEY (id);

--ALTER Table ADD a New Column
ALTER TABLE "cobaPertama"
    ADD COLUMN id_coba INTEGER;

--ALTER Table ADD Foreign Key
ALTER TABLE "cobaPertama"
    ADD CONSTRAINT "FK_coba01_cobaPertama"
    FOREIGN KEY (id_coba)
    REFERENCES coba01 (id);

--ALTER Table ADD Index
CREATE INDEX idx_coba01 ON coba01(nama);

--ALTER Table RENAME Column
ALTER TABLE coba01
    RENAME COLUMN deckripsi TO deskripsi;

--ALTER Table Sequence Number
ALTER SEQUENCE pelanggan_id_seq RESTART WITH 2;


--DML--
--DML Create Data
INSERT INTO coba01
VALUES
(1, 'Coba 01', 'Coba yang ke-01');

INSERT INTO pelanggan
VALUES
(1, 'Pelanggan 01', 'Alamat si Pelanggan 01', 'pelanggan01@email.net');

INSERT INTO pelanggan
(nama, alamat, email)
VALUES
('Pelanggan 02', 'Alamat si Pelanggan 02', 'pelanggan02@email.net');


--DML Read Data
select * from coba01;
SELECT * FROM "cobaPertama";
select * from pelanggan;

--Show all Database available
select * from pg_database;

--Show all Table available in a database
select * from information_schema.TABLES
where table_catalog='coba' and table_schema='public';

select * from information_schema.TABLES
where table_catalog='coba' and table_name like '%index%';

--List all Database Activity
select * from pg_stat_activity;

--Kill Database session
SELECT pg_terminate_backend(pid)
from pg_stat_activity where pid='151';


--DESCRIBE a Table Schema / Structure
SELECT *
FROM information_schema.columns
WHERE table_name = 'coba01';
SELECT *
FROM information_schema.columns
WHERE table_name = 'cobaPertama';
SELECT *
FROM information_schema.columns
WHERE table_name = 'pelanggan';

--LIST Table Constraints
SELECT *
FROM information_schema.table_constraints
WHERE table_name='coba01';
SELECT *
FROM information_schema.table_constraints
WHERE table_name='cobaPertama';
SELECT *
FROM information_schema.table_constraints
WHERE table_name = 'pelanggan';

--Check table Index
select * from pg_indexes where tablename='coba01';
select * from pg_indexes where tablename='cobaPertama';
select * from pg_indexes where tablename='pelanggan';

--Check Table Sequence
SELECT *
FROM information_schema.sequences;
