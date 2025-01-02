-- No 1
-- creating the table

CREATE DATABASE "DBPenerbit";


CREATE TABLE "tblPengarang" ( 
    ID SERIAL PRIMARY KEY,
    Kd_Pengarang VARCHAR(7) NOT NULL,
    Nama VARCHAR(30) NOT NULL,
    Alamat VARCHAR(80) NOT NULL,
    Kota VARCHAR(15) NOT NULL,
    Kelamin VARCHAR(1) NOT NULL)


drop TABLE "tblGaji";

--No 2
--Inserting table values

INSERT INTO "tblPengarang" (
    Kd_Pengarang,
    Nama,
    Alamat,
    Kota,
    Kelamin)
VALUES ('P0001',
        'Ashadi',
        'Jl. Beo 25',
        'Yogya',
        'P'), ('P0002',
               'Rian',
               'Jl. Solo 123',
               'Yogya',
               'P'), ('P0003',
                      'Suwadi',
                      'Jl. Semangka 13',
                      'Bandung',
                      'P'), ('P0004',
                             'Siti',
                             'Jl. Durian 15',
                             'Solo',
                             'W'), ('P0005',
                                    'Amir',
                                    'Jl. Gajah 33',
                                    'Kudus',
                                    'P'), ('P0006',
                                           'Suparman',
                                           'Jl. Harimau 25',
                                           'Jakarta',
                                           'P'), ('P0007',
                                                  'Jaja',
                                                  'Jl. Singa 7',
                                                  'Bandung',
                                                  'P'), ('P0008',
                                                         'Saman',
                                                         'Jl. Naga 12',
                                                         'Yogya',
                                                         'P'), ('P0009',
                                                                'Anwar',
                                                                'Jl. Tidar 6A',
                                                                'Magelang',
                                                                'P'), ('P0010',
                                                                       'Fatmawati',
                                                                       'Jl. Renjana 4',
                                                                       'Bogor',
                                                                       'W');

--show data from tblPengarang

SELECT *
FROM "tblPengarang";

-- No 1
-- creating the table tblGaji

CREATE TABLE "tblGaji" ( ID SERIAL PRIMARY KEY,
                                           Kd_Pengarang VARCHAR(7) NOT NULL,
                                                                   Nama VARCHAR(30) NOT NULL,
                                                                                    Gaji DECIMAL(18,4) NOT NULL ) --No 2
--Inserting table values

INSERT INTO "tblGaji" (Kd_Pengarang,
                       Nama,
                       Gaji)
VALUES ('P0002',
        'Rian',
        60), ('P0005',
              'Amir',
              70), ('P0004',
                    'Siti',
                    50), ('P0003',
                          'Suwadi',
                          100), ('P0010',
                                 'Fatmawati',
                                 60), ('P0008',
                                       'Saman',
                                       75);

--show data from tblPengarang

SELECT *
FROM "tblGaji";

--********************************************************
--SOAL


 --1
SELECT 
    COUNT(*) as "Jumlah"
FROM "tblPengarang";

--2
SELECT COUNT(CASE WHEN kelamin = 'P' THEN 1 END) as "Jumlah Pria",  
    COUNT(CASE WHEN kelamin='W' THEN 1 END) as "Jumlah Wanita" 
FROM "tblPengarang";



--3
SELECT Kota,
    COUNT(*) AS "Jumlah_Kota"
    FROM "tblPengarang"
GROUP BY kota;

--klo mau nama doang
---SELECT nama, gaji from "tblGaji" WHERE gaji = (SELECT MIN(gaji) from "tblGaji");


--4
SELECT Kota,
       COUNT(*) AS "Jumlah_Kota"
FROM "tblPengarang"
GROUP BY kota
HAVING COUNT(*) > 1;


--5
SELECT 
    MAX(Kd_Pengarang) AS "Kd_Pengarang_Terbesar", 
    MIN(Kd_Pengarang) AS "Kd_Pengarang_Terkecil"
FROM "tblPengarang";


--6
SELECT MAX(gaji) as "Gaji tertinggi", MIN(gaji) as "Gaji terendah"
FROM "tblGaji";


--7
SELECT gaji 
FROM "tblGaji" WHERE gaji > 60;


--8
SELECT SUM(gaji) 
FROM "tblGaji";


--9
SELECT kota, SUM(gaji) 
FROM "tblGaji", "tblPengarang" 
WHERE "tblPengarang".Kd_Pengarang = "tblGaji".Kd_Pengarang 
GROUP BY kota;


--10
SELECT * FROM "tblPengarang" 
WHERE Kd_Pengarang BETWEEN 'P0003' AND  'P0006';

--11
SELECT *  FROM "tblPengarang" 
WHERE kota = 'Solo' OR kota = 'Yogya' OR kota = 'Magelang';

--cara lain
SELECT *  FROM "tblPengarang" 
WHERE kota IN ('Solo', 'Yogya', 'Magelang');


--12
SELECT *  FROM "tblPengarang" 
WHERE kota != 'Solo';


--13
--a
SELECT * FROM "tblPengarang" 
WHERE  nama LIKE 'A%';

--b
SELECT * FROM "tblPengarang" 
WHERE  nama LIKE '%i';

--c
SELECT * FROM "tblPengarang" 
WHERE  nama LIKE '___a';

--d
SELECT * FROM "tblPengarang" 
WHERE  nama NOT LIKE '%n';


--14
SELECT p.*,g.gaji 
FROM "tblPengarang" AS p, "tblGaji" AS g 
WHERE g.Kd_Pengarang = p.Kd_Pengarang;

-- cara kalo pake JOIN
SELECT * 
FROM "tblPengarang" AS p JOIN "tblGaji" AS g 
ON g.Kd_Pengarang = p.Kd_Pengarang;



--15
SELECT p.kota, g.gaji 
FROM "tblPengarang" AS p, "tblGaji" AS g 
WHERE g.Kd_Pengarang = p.Kd_Pengarang AND g.gaji >= 100;


--16
ALTER TABLE "tblPengarang" 
ALTER COLUMN kelamin TYPE VARCHAR(10); --wajib ada type


--17
ALTER TABLE "tblPengarang"
ADD COLUMN Gelar VARCHAR(12);
SELECT * FROM "tblPengarang";


--18
UPDATE "tblPengarang"
SET alamat = 'Jl. Cendrawasih 65', kota = 'Pekanbaru'
WHERE nama = 'Rian'; --kalo gaada where nanti keubah satu tabel

SELECT * FROM "tblPengarang";


--19
CREATE VIEW pengarang AS --isi dalem view dimulai dengan AS, wajib
SELECT p.Kd_Pengarang, p.nama, p.kota, g.gaji 
FROM "tblPengarang" AS p, "tblGaji" AS g WHERE p.Kd_Pengarang = g.Kd_Pengarang;

SELECT * FROM pengarang;


--cara gabung string
SELECT CONCAT(Kd_Pengarang, '-', Nama)
FROM "tblPengarang";

--IN bisa untuk subquery
SELECT *
FROM "tblPengarang"
WHERE Kota IN (
    SELECT kota -- cuman bisa sesuai tabel, jangan * (all)
    FROM "tblPengarang"
    WHERE Kota like 'B%'
);


--cari sesuai jumlah karakter
SELECT kota
FROM "tblPengarang"
WHERE kota like '____' --4 karakter jadi 4 underscore
GROUP BY kota;


--membuat kolom menjadi NULLABLE
ALTER TABLE "tblPengarang"
ALTER COLUMN alamat DROP NOT NULL;

--membuat kolom NOT NULL
ALTER TABLE "tblPengarang"
ALTER COLUMN alamat SET NOT NULL;

--liat property column tblPengarang
SELECT * FROM information_schema.columns
WHERE table_name = 'tblPengarang';



--UNION ukuran tabel harus sama persis, data type harus sama

SELECT Kd_Pengarang, gaji, 'Staff' as status from "tblGaji" where gaji < 60
UNION
SELECT Kd_Pengarang, gaji, 'Supervisor' as status from "tblGaji" where gaji >= 60 AND gaji < 70
UNION 
SELECT Kd_Pengarang, gaji, 'Manager' as status from "tblGaji" where gaji > 70;


--DATE TIME
CREATE TABLE timestamp_demo (
    ts TIMESTAMP,
    tstz TIMESTAMPTZ
);

SELECT * FROM timestamp_demo

SHOW TIMEZONE;
SET TIMEZONE = 'Asia/Jakarta';
SET TIMEZONE = 'America/Los_Angeles';

Select NOW();
Select NOW() :: DATE; --klo mau tanggal aj

SELECT TO_CHAR(now()::date, 'dd/mm/yyyy');
SELECT TO_CHAR(now()::date, 'dd mon yyyy');

select current_timestamp;


--Check DB Current Timezones
SELECT * FROM pg_timezone_names WHERE abbrev = current_setting('TIMEZONE');



