select 'Xsis Academy' as name;
select '2024-12-12' as date;
select NOW() as tgl_sekarang;
SELECT md5('P@ssw0rd') as pass;

select CONCAT('Xsis', ' ', 'Academy') as name2;


create DATABASE DBPenerbit;

create TABLE tblPengarang (
    id SERIAL primary key NOT NULL,
    kd_pengarang varchar(7) NOT NULL,
    Nama varchar(30) NOT NULL,
    Alamat varchar(80) NOT NULL,
    Kota varchar(15) NOT NULL,
    Kelamin varchar(1) NOT NULL
);

SELECT * FROM tblPengarang;

INSERT INTO tblPengarang
    (Kd_Pengarang, Nama, Alamat, Kota, Kelamin)
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
    ('P0010', 'Fatmawati', 'Jl. Renjana 4', 'Bogor', 'W');

DROP TABLE
    IF EXISTS tblGaji;

CREATE TABLE tblGaji (
    ID SERIAL PRIMARY KEY NOT NULL,
    Kd_Pengarang varchar(7) NOT NULL,
    Nama varchar(30) NOT NULL,
    Gaji DECIMAL(18,4) NOT NULL
);

INSERT INTO tblGaji
(Kd_Pengarang, Nama, Gaji)
VALUES
    ('P0002', 'Rian',       600000),
    ('P0005', 'Amir',       700000),
    ('P0004', 'Siti',       500000),
    ('P0003', 'Suwadi',     1000000),
    ('P0010', 'Fatmawati',  600000),
    ('P0008', 'Saman',      750000);

INSERT INTO tblGaji
(Kd_Pengarang, Nama, Gaji)
VALUES
('P0011', 'Siti',       1500000);

SELECT * FROM tblGaji;

--//Aggregate Functions (COUNT, MIN, MAX, SUM, AVG)//--
SELECT * FROM tblGaji  where GAJI>700000;
--Count existing Table Rows
SELECT COUNT(GAJI) FROM tblGaji where GAJI>700000;
--Least Value MIN()
SELECT nama, MIN(Gaji), MAX(Gaji) FROM tblGaji
GROUP BY nama;
--Total Value SUM()
SELECT nama, SUM(Gaji) FROM tblGaji
GROUP BY nama;
--Average Value AVG()
SELECT nama, AVG(Gaji) FROM tblGaji
GROUP BY nama;

SELECT nama, MIN(Gaji) as minimum, MAX(Gaji) as maksimum, SUM(Gaji) as total, AVG(Gaji) as rata2
FROM tblGaji
GROUP BY nama;

SELECT NAMA FROM TBLGAJI GROUP BY NAMA;

--//CASE-WHEN-ELSE//--
SELECT
    nama,
    gaji,
    CASE WHEN gaji >= 1500000 THEN 'Manager'
        WHEN gaji >= 1000000 THEN 'Supervisor'
        ELSE 'Staff'
    END AS status
FROM tblGaji
WHERE gaji>=1000000;

--1. Total Pengarang
Select COUNT(*) AS jumlah_pengarang FROM tblPengarang;

--2. Total pengarang Wanita / Pria
SELECT
    CASE WHEN Kelamin = 'W' THEN 'Wanita'
        ELSE 'Pria'
    END AS jenis_kelamin,
    COUNT(*) AS jumlah_pengarang
FROM tblPengarang
GROUP BY Kelamin;

--3. Kota, Jumlah Kota
SELECT Kota, COUNT(*) as jumlah_pengarang
FROM tblPengarang
GROUP BY Kota
ORDER BY Kota;

select * from tblPengarang order by kota DESC;

--4. Kota, Jumlah Pengarang yg lebih dari 1
SELECT Kota, COUNT(*) as jumlah_pengarang
FROM tblPengarang
GROUP BY Kota
HAVING COUNT(*) > 1
ORDER BY Kota;

--5. Kd_Pengarang terbesar dan terkecil
SELECT
    (SELECT MAX(Kd_Pengarang) FROM tblPengarang) AS high,
    (SELECT MIN(Kd_Pengarang) FROM tblPengarang) AS low;

SELECT
    (SELECT Kd_Pengarang FROM tblPengarang ORDER BY kd_pengarang DESC LIMIT 1) AS high,
    (SELECT Kd_Pengarang FROM tblPengarang ORDER BY kd_pengarang ASC  LIMIT 1) AS low;

SELECT MAX(kd_pengarang), MIN(kd_pengarang) FROM tblPengarang;

--6. Gaji Tertinggi dan Terendah
SELECT MIN(gaji) AS gaji_terkecil, MAX(gaji) AS gaji_terbesar
FROM tblGaji;

SELECT NAMA, MIN(gaji) AS gaji_terkecil, MAX(gaji) AS gaji_terbesar
FROM tblGaji
GROUP BY Nama;

SELECT NAMA, gaji
FROM tblGaji
WHERE GAJI = (
    SELECT MIN(gaji) AS gaji_terkecil
    FROM tblGaji
);

SELECT NAMA, gaji
FROM tblGaji
GROUP BY nama, gaji
HAVING GAJI = (
    SELECT MIN(gaji) AS gaji_terkecil
    FROM tblGaji
);

--7. gaji > 600000
SELECT * FROM tblGaji WHERE gaji > 600000;

--8. Jumlah Gaji
SELECT SUM(gaji) AS total_gaji FROM tblGaji;

--9. Jumlah Gaji per Kota
SELECT kota, COUNT(*) COUNT, SUM(gaji) jml_gaji
FROM tblPengarang AS A INNER JOIN tblGaji AS B
    ON A.kd_pengarang=B.kd_pengarang
GROUP BY Kota;

SELECT kota, COUNT(*) COUNT, SUM(gaji) jml_gaji
FROM tblPengarang AS A, tblGaji AS B
WHERE A.kd_pengarang=B.kd_pengarang
GROUP BY Kota;

select P.nama , G.gaji
from tblpengarang P left join tblgaji G
	on P.kd_pengarang = G.kd_pengarang
where G.gaji is null;

select P.nama , G.gaji 
from tblpengarang P right join tblgaji G
	on P.kd_pengarang = G.kd_pengarang;

select P.nama , G.gaji 
from tblpengarang P full outer join tblgaji G
	on P.kd_pengarang = G.kd_pengarang
where P.kd_pengarang is null or G.kd_pengarang is null;
