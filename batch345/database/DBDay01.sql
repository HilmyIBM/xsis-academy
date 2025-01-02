CREATE TABLE tblPengarang (
    ID SERIAL PRIMARY KEY NOT NULL,
    Kd_Pengarang VARCHAR(7) NOT NULL,
    Nama VARCHAR(30) NOT NULL,
    Alamat VARCHAR(80) NOT NULL,
    Kota VARCHAR(15) NOT NULL,
    Kelamin VARCHAR(1) NOT NULL
);

INSERT INTO tblPengarang (Kd_Pengarang, Nama, Alamat, Kota, Kelamin)
VALUES 
    ('P0001', 'Ashadi', 'Jl. Beo 25', 'Yogya', 'P'),
    ('P0002', 'Rian', 'Jl. Solo 123', 'Yogya', 'P'),
    ('P0003', 'Suwadi', 'Jl. Semangka 13', 'Bandung', 'P'),
    ('P0004', 'Siti', 'Jl. Durian 15', 'Solo', 'W'),
    ('P0005', 'Amir', 'Jl. Gajah 33', 'Kudus', 'P'),
    ('P0006', 'Suparman', 'Jl. Harimau 25', 'Jakarta', 'P'),
    ('P0007', 'Jaja', 'Jl. Singa 7', 'Bandung', 'P'),
    ('P0008', 'Saman', 'Jl. Naga 12', 'Yogya', 'P'),
    ('P0009', 'Anwar', 'Jl. Tidar 6A', 'Magelang', 'P'),
    ('P0010', 'Fatmawati', 'Jl. Renjana 4', 'Bogor', 'W')
;


SELECT * FROM tblPengarang;

CREATE TABLE tblGaji (
    ID SERIAL PRIMARY KEY NOT NULL,
    Kd_Pengarang VARCHAR(7) NOT NULL,
    Nama VARCHAR(30) NOT NULL,
    Gaji DECIMAL(18,4) NOT NULL
);

INSERT INTO tblGaji (Kd_Pengarang, Nama, Gaji)
VALUES 
    ('P0002', 'Rian', '600000'),
    ('P0005', 'Amir', '700000'),
    ('P0004', 'Siti', '500000'),
    ('P0003', 'Suwadi', '1000000'),
    ('P0010', 'Fatmawati', '600000'),
    ('P0008', 'Saman', '750000')
;

SELECT * FROM tblGaji;

-- No 1
SELECT COUNT(*) AS jumlah_pengarang FROM tblPengarang;

-- No 2
SELECT
    (SELECT COUNT(*) FROM tblPengarang WHERE Kelamin='W') AS pengarang_wanita, 
    (SELECT COUNT(*) FROM tblPengarang WHERE Kelamin='P') AS pengarang_pria;

SELECT Kelamin, COUNT(*) AS jumlah_pengarang
FROM tblPengarang
GROUP BY Kelamin;

-- No 3
SELECT Kota, COUNT(*) AS jumlah_pengarang
FROM tblPengarang
GROUP BY Kota;

-- No 4
SELECT Kota, COUNT(*) AS jumlah_pengarang
FROM tblPengarang
GROUP BY Kota
HAVING COUNT(*) > 1;

-- No 5
SELECT
    (SELECT MAX(Kd_Pengarang) from tblPengarang),
    (SELECT MIN(Kd_Pengarang) from tblPengarang);

-- No 6
SELECT
    (SELECT MAX(Gaji) from tblGaji) AS gaji_tertinggi,
    (SELECT MIN(Gaji) from tblGaji) AS gaji_terendah;

-- No 7
SELECT Gaji from tblGaji
WHERE Gaji > 600000;

-- No 8
SELECT SUM(Gaji) FROM tblGaji;

-- No 9
SELECT Kota, SUM(Gaji)
FROM tblPengarang
JOIN tblGaji ON tblPengarang.Kd_Pengarang = tblGaji.Kd_Pengarang
GROUP BY Kota;

-- No 10
SELECT * from tblPengarang
WHERE Kd_Pengarang BETWEEN 'P0003' AND 'P0006';

-- No 11
SELECT * FROM tblPengarang
WHERE Kota IN ('Yogya', 'Solo', 'Magelang');

-- No 12
SELECT * FROM tblPengarang
WHERE Kota NOT IN ('Yogya');

-- No 13
-- a
SELECT * FROM tblPengarang
WHERE Nama LIKE 'A%';

-- b
SELECT * FROM tblPengarang
WHERE Nama LIKE '%i';

-- c
SELECT * FROM tblPengarang
WHERE Nama LIKE '__a%';

-- d
SELECT * FROM tblPengarang
WHERE Nama NOT LIKE '%n';

-- No 14
SELECT * FROM tblPengarang tp
INNER JOIN tblGaji tg ON tp.Kd_Pengarang = tg.Kd_Pengarang;

-- No 15
SELECT DISTINCT tp.Kota FROM tblPengarang tp
INNER JOIN tblGaji tg ON tp.Kd_Pengarang = tg.Kd_Pengarang
WHERE tg.Gaji < 1000000;

-- No 16
ALTER TABLE tblPengarang
    ALTER COLUMN Kelamin TYPE VARCHAR(10);

-- No 17
ALTER TABLE tblPengarang
ADD COLUMN Gelar VARCHAR(12);

-- No 18
UPDATE tblPengarang
SET
    Alamat='Jl. Cendrawasih 65',
    Kota='Pekanbaru'
WHERE id = 2;

-- No 19
CREATE VIEW vwPengarang AS
SELECT
    tp.Kd_Pengarang,
    tp.Nama,
    tp.Kota,
    tg.Gaji
FROM tblPengarang tp
JOIN tblGaji tg ON tp.Kd_Pengarang = tg.Kd_Pengarang;

SELECT * FROM vwPengarang;

DROP TABLE tblPengarang;

SELECT * FROM pg_database;

SELECT * FROM information_schema.TABLES
WHERE table_catalog='dbpenerbit' AND table_schema='public';

SELECT * FROM information_schema.columns
WHERE column_name = 'gelar';

SELECT * FROM information_schema.columns
WHERE table_name = 'tblpengarang';

CREATE DATABASE DB_Entertainer;