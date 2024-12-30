-- SELECT * FROM pg_database;

CREATE DATABASE db_penerbit;

CREATE TABLE tblPengarang(
    ID SERIAL PRIMARY KEY NOT NULL,
    kd_pengarang VARCHAR(7) NOT NULL,
    nama VARCHAR(30) NOT NULL,
    alamat VARCHAR(80) NOT NULL,
    kota VARCHAR(15) NOT NULL,
    kelamin VARCHAR(1) NOT NULL
);

SELECT * FROM tblPengarang;

INSERT INTO tblPengarang (kd_pengarang, nama, alamat, kota, kelamin)
VALUES 
    -- ('P0001', 'Ashadi', 'Jl. Beo 25', 'Yogya', 'P'),
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

CREATE TABLE tblGaji(
    ID SERIAL PRIMARY KEY NOT NULL,
    kd_pengarang VARCHAR(7) NOT NULL,
    nama VARCHAR(30) NOT NULL,
    gaji DECIMAL(18, 4) NOT NULL
);

SELECT * FROM information_schema.columns WHERE table_name = 'tblgaji';
SELECT * FROM information_schema.table_constraints WHERE table_name = 'tblgaji';

INSERT INTO tblGaji (kd_pengarang, nama, gaji)
VALUES
    ('P0002', 'Rian', 600000),
    ('P0005', 'Amir', 700000),
    ('P0004', 'Siti', 500000),
    ('P0003', 'Suwadi', 1000000),
    ('P0010', 'Fatmawati', 600000),
    ('P0008', 'Saman', 750000)
;

SELECT * FROM tblGaji;

-- 1
SELECT COUNT(*) FROM tblPengarang;

-- 2
SELECT kelamin, COUNT(*) AS jumlahKelamin FROM tblPengarang GROUP BY kelamin;

-- 3
SELECT kota, COUNT(*) AS jumlahKota FROM tblPengarang GROUP BY kota;

-- 4
SELECT kota, COUNT(*) AS jumlahKota 
FROM tblPengarang 
GROUP BY kota
HAVING COUNT(*) > 1;

-- 5
SELECT kd_pengarang, nama
FROM tblPengarang 
ORDER BY kd_pengarang DESC;

SELECT 'min' AS label, MIN(kd_pengarang) AS kd_pengarang FROM tblPengarang
UNION
SELECT 'max' AS label, MAX(kd_pengarang) AS kd_pengarang FROM tblPengarang;

-- 6
-- SELECT MIN(gaji) AS MIN, MAX(gaji) AS MAX
-- FROM tblGaji;
SELECT 'min' AS label, MIN(gaji) AS gaji FROM tblGaji
UNION
SELECT 'max' AS label, MAX(gaji) AS gaji FROM tblGaji;

-- 7
SELECT nama, gaji 
FROM tblGaji
WHERE gaji > 600000;

-- 8
SELECT SUM(gaji) AS jumlahGaji FROM tblGaji;

-- 9
SELECT kota, COUNT(*) as count, SUM(gaji) as jumlahGaji
FROM tblPengarang a JOIN tblGaji b
ON a.kd_pengarang = b.kd_pengarang
GROUP BY kota;

-- 10
SELECT * FROM tblPengarang
WHERE kd_pengarang BETWEEN 'P0003' AND 'P0006';

-- 11
SELECT * FROM tblPengarang
WHERE kota = 'Yogya' OR kota = 'Solo' OR kota = 'Magelang';

-- 12
SELECT * FROM tblPengarang
WHERE NOT kota = 'Yogya';

-- 13
-- a
SELECT * FROM tblPengarang
where nama LIKE 'A%';
-- b
SELECT * FROM tblPengarang
where nama LIKE '%i';
-- c
SELECT * FROM tblPengarang
where nama  NOT LIKE '%n';

-- 14
SELECT a.* FROM tblPengarang a
JOIN tblGaji b ON a.kd_pengarang = b.kd_pengarang;

-- 15
SELECT kota
FROM tblPengarang a JOIN tblGaji b
ON a.kd_pengarang = b.kd_pengarang
WHERE gaji < 1000000;

-- 16
ALTER TABLE tblPengarang
    ALTER COLUMN kelamin SET DATA TYPE VARCHAR(10);

-- 17
ALTER TABLE tblPengarang 
    ADD COLUMN gelar VARCHAR(12);

-- 18
UPDATE tblPengarang a
    SET
        alamat = 'Jl. Cendrawasih 65',
        kota = 'Pekanbaru'
    WHERE nama = 'Rian';

-- 19
CREATE VIEW vwPengarang AS
    SELECT a.kd_pengarang, a.nama, a.kota, b.gaji
    FROM tblPengarang a JOIN tblGaji b 
    ON a.kd_pengarang = b.kd_pengarang;

SELECT * FROM vwPengarang;


