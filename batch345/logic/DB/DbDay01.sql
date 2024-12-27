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
SELECT *
FROM "tblPengarang";

--2
SELECT *
FROM "tblPengarang"
WHERE kelamin='P'
    OR kelamin ='W' 


--3
SELECT Kota,
    COUNT(*) AS "Jumlah_Kota"
    FROM "tblPengarang"
GROUP BY kota;


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
SELECT gaji FROM "tblGaji" WHERE gaji > 60;


--8
SELECT SUM(gaji) FROM "tblGaji";


--9
SELECT SUM(gaji), kota FROM "tblGaji", "tblPengarang" WHERE "tblPengarang".Kd_Pengarang = "tblGaji".Kd_Pengarang GROUP BY kota;


--10
SELECT * FROM "tblPengarang" WHERE Kd_Pengarang >= 'P0003' AND Kd_Pengarang <= 'P0006';

--11
SELECT *  FROM "tblPengarang" WHERE kota = 'Solo' OR kota = 'Yogya' OR kota = 'Magelang';


--12
SELECT *  FROM "tblPengarang" WHERE kota != 'Solo';


--13
--a
SELECT * FROM "tblPengarang" WHERE  nama LIKE 'A%';

--b
SELECT * FROM "tblPengarang" WHERE  nama LIKE '%i';

--c
SELECT * FROM "tblPengarang" WHERE  nama LIKE '__%a';

--d
SELECT * FROM "tblPengarang" WHERE  nama NOT LIKE '%n';


--14
SELECT p.*,g.gaji FROM "tblPengarang" AS p, "tblGaji" AS g WHERE g.Kd_Pengarang = p.Kd_Pengarang;


--15
SELECT p.kota, g.gaji FROM "tblPengarang" AS p, "tblGaji" AS g WHERE g.Kd_Pengarang = p.Kd_Pengarang AND g.gaji >= 100;


