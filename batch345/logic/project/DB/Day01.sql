-- DDL Create: membuat object baru di database / schema

-- create database
CREATE DATABASE coba;

-- create table
create table coba01 (
    id_coba INTEGER,
    nama varchar(20),
    deskripsi varchar(255)
);

-- DLL Alter: Memodifikasi table/database
-- create PK
alter table coba01
    ADD PRIMARY KEY (id_coba);

alter table coba01
    alter column id_coba SET NOT NULL ;


select * from coba01;

-- DDL drop: membuat object dari database;
-- drop database
drop database coba;

-- drop table
drop table coba01;

-- Show current database table schema
select * from information_schema.tables where table_schema='public';
-- show databases access / activity
select * from pg_stat_activity where datid IS NOT NULL;
-- describe table information
select column_name, data_type, character_maximum_length, column_default, is_nullable, constraint_type
from INFORMATION_SCHEMA.COLUMNS as col
    INNER JOIN information_schema.table_constraints as cons on col.table_name = cons.table_name
where col.table_name = 'coba01' group by constraint_type




select table_name, constraint_type
from information_schema.table_constraints where table_name = 'coba01' and constraint_type != 'CHECK';

CREATE DATABASE test;

DROP TABLE t1;

CREATE TABLE t1(
    id SERIAL PRIMARY KEY NOT NULL,
    name varchar(12) not null,
    addr varchar(24) not null,
    nik varchar(12) not null
);

INSERT INTO t1 (name, addr, nik)
VALUES
    ('asd1', 'asd1', '2223'),
    ('asd2', 'asd2', '1245'),
    ('asd3', 'asd3', '3333'),
    ('as4', 'asd4', '4444');

SELECT t1.name, t1.addr into t1_b from t1;

