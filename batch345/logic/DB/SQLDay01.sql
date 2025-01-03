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

--DML Truncate TABLE
TRUNCATE TABLE coba_bak; 
SELECT * from coba_bak;

--Show all Database available
select * from pg_database;

--Show all Table available in a database
select * from information_schema.TABLES
where table_catalog='coba' and table_schema='public';

select * from information_schema.TABLES
where table_catalog='coba' and table_name like '%index%';

select * from information_schema.TABLES
where table_catalog='dbpenerbit' and table_schema='public';

select * from information_schema.TABLES
where table_catalog='northwind' and table_schema='public';

select * from information_schema.TABLES
where table_catalog='db_hr' and table_name='tb_karyawan';

select * from information_schema.TABLES
where table_catalog='db_hr' and table_name like '%idx%';

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
SELECT *
FROM information_schema.columns
WHERE table_name = 'tblpengarang'
SELECT *
FROM information_schema.columns
WHERE table_name = 'employees'

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
SELECT *
FROM information_schema.table_constraints
WHERE table_name = 'tb_karyawan';

--Check table Index
select * from pg_indexes where tablename='coba01';
select * from pg_indexes where tablename='cobaPertama';
select * from pg_indexes where tablename='pelanggan';
select * from pg_indexes where tablename='tb_karyawan';

--Check Table Sequence
SELECT *
FROM information_schema.sequences;

--Check DB Current TimeZone
SELECT * FROM pg_timezone_names WHERE abbrev = current_setting('TIMEZONE')

--Pengarang dengan kota yg berawalan B
SELECT * FROM tblPengarang
WHERE kota IN (
    SELECT kota FROM tblPengarang
    where kota like 'B%' group by kota
);

--13a. Pengarang namanya dimulai A
SELECT nama FROM tblpengarang
where nama like 'A%';

--13a. Pengarang namanya dimulai A
SELECT nama FROM tblpengarang
where nama like '%i';

--14. tblPengarang dan Tbl gaji dengan kd_pengarang yg sama
SELECT *
FROM tblpengarang INNER JOIN tblgaji
    ON tblpengarang.kd_pengarang = tblgaji.kd_pengarang;

SELECT p.kd_pengarang, p.kelamin
FROM tblpengarang as p INNER JOIN tblgaji as g
    ON p.kd_pengarang = g.kd_pengarang;

--16. Ubah panjang dari tipe kelamin menjadi 10
ALTER TABLE tblpengarang
    ALTER COLUMN kelamin TYPE VARCHAR(10);

--Membuat Kolom menjadi NULLABLE
ALTER TABLE tblpengarang
    ALTER COLUMN alamat DROP NOT NULL;

--17. Tambahkan kolom [Gelar] dengan tipe Varchar (12) pada tabel tblPengarang
ALTER TABLE tblpengarang
    ADD COLUMN gelar VARCHAR(12);

--18. Ubah alamat dan kota dari Rian di table tblPengarang menjadi, Jl. Cendrawasih 65 dan Pekanbaru
SELECT * FROM tblpengarang WHERE nama='Rian';

UPDATE tblpengarang
SET alamat='Jl. Cendrawasih 65', kota='Pekanbaru'
WHERE id=2;

--19. Buatlah view untuk attribute Kd_Pengarang, Nama, Kota, Gaji dengan nama vwPengarang
create view vw_pengarang
AS
    select p.kd_pengarang, p.nama, p.kota, g.gaji
    from tblpengarang as p inner join tblgaji as g
        on p.kd_pengarang = g.kd_pengarang;

select * from vw_pengarang vp right join tblgaji g
    on vp.kd_pengarang = g.kd_pengarang;

select * from vw_pengarang vp where vp.kota='Yogya';

--//FUNCTION : Process Input(s) into Output(s)
CREATE FUNCTION ufn_total_gaji(gapok NUMERIC, tunjangan_jabatan NUMERIC, tunjangan_kinerja NUMERIC)
RETURNS NUMERIC AS $total$
DECLARE total NUMERIC;
BEGIN
    SELECT (gapok + tunjangan_jabatan + tunjangan_kinerja) INTO total; 
    RETURN total;
END;
$total$ LANGUAGE plpgsql;

SELECT ufn_total_gaji(1000000, 500000, 100000) total_gaji;
