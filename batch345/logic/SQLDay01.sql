--DDL Create; Membuat object baru di database
--CREATE TABLE;
CREATE TABLE coba01 (
  id_coba INTEGER,
  nama VARCHAR(20) NOT NULL,
  deskripsi VARCHAR(255)
);

--DML Drop: Membuang object dari database
--DROP TABLE
DROP TABLE coba01;

--DROP DATABASE
--DROP DATABASE coba;

--DML Create Data

--DML Read Data

select * from coba01;

select * from pg_database;

SELECT datname FROM pg_database;

--Show all table in database
SELECT table_name FROM information_schema.tables
where table_schema='public';

--DDL ALTER: Modifikasi Object Database
--ALTER Table
ALTER TABLE coba01
  ADD PRIMARY KEY (id_coba);

--DESCRIBE a Table Schema/Structure
SELECT * FROM information_schema.COLUMNS
WHERE table_name = 'coba01';