CREATE TABLE tblPengarang
(id SERIAL PRIMARY KEY NOT NULL,
kdPengarang VARCHAR(7) NOT NULL,
nama VARCHAR(30) NOT NULL,
alamat VARCHAR(80) NOT NULL,
kota VARCHAR(15) NOT NULL,
kelamin VARCHAR(1) NOT NULL);

DROP TABLE tbgaji;
ALTER TABLE tblPengarang RENAME COLUMN kd_pengarang TO kdPengarang;


INSERT INTO tblPengarang(kdpengarang,nama,alamat,kota,kelamin)
VALUES('P0001','Ashadi','jl. Beo 25','Yogya','P'),
      ('P0002','Rian','jl. solo 123','yogya','P'),
      ('P0003','Suwadi','jl. semangka 13','Bandung','P'),
      ('P0004','Siti','jl. durian 15','Solo','W'),
      ('P0005','Amir','jl. gajah 33','Kudus','P'),
      ('P0006','Suparman','jl. harimau 25','jakarta','P'),
      ('P0007','Jaja','jl. singa 7','bandung','P'),
      ('P0008','Saman','jl. naga 12','Yogya','P'),
      ('P0009','Anwar','jl. tidar 6A','Magelang','P'),
      ('P0010','Fatmawati','Jl. renjana 4','Bogor','W');

SELECT * FROM tblPengarang;  

CREATE TABLE tblgaji(
    ID SERIAL PRIMARY KEY NOT NULL,
    kdPengarang VARCHAR(7) NOT NULL,
    nama VARCHAR(30) not NULL,
    gaji DECIMAL(18,4) NOT NULL
);
SELECT * from pg_database;
SELECT * from information_schema.TABLES WHERE table_catalog='dbpenerbit' AND table_schema='public';

INSERT INTO tblgaji (kdPengarang,nama,gaji)
VALUES('P0002','Rian',600000),
      ('P0005','Amir',700000),
      ('P0004','Siti',500000),
      ('P0003','Suwadi',1000000),
      ('P0010','Fatmawati',600000),
      ('P0008','Saman',750000);

--No.1
SELECT COUNT(nama) as jumlah_pengarang from tblPengarang;
--No.2
select count(kelamin)as jumlah,kelamin  from tblPengarang GROUP BY kelamin;
--No.3
SELECT kota,COUNT(kota) as jumlah_kota from tblPengarang GROUP BY kota;
--No.4
SELECT kota,COUNT(kota) as kota_terbanyak from tblPengarang GROUP BY kota HAVING COUNT(kota)>1;
--No.5
SELECT MIN(kdPengarang) as terkecil, MAX(kdPengarang) as terbesar from tblPengarang;
--No.6
SELECT MIN(gaji) as gaji_terkecil, MAX(gaji) as gaji_terbesar from tblgaji;
--No.7
Select nama,gaji from tblgaji where gaji > 600000;
--No.8
SELECT sum(gaji) as sum_gaji,count(gaji) as jumlah_gaji from tblgaji;
--No.9
SELECT kota,SUM(gaji) as jumlah_gaji from tblgaji join tblPengarang on tblgaji.kdpengarang=tblPengarang.kdpengarang
GROUP BY kota;
--No.10
SELECT * from tblPengarang WHERE kdpengarang BETWEEN 'P0003' and 'P0006';
--No.11
SELECT * from tblPengarang WHERE kota IN('Solo','Magelang','Yogya');
--No.12
SELECT * from tblPengarang WHERE kota not in('Yogya');
--No.13 a
SELECT * from tblPengarang WHERE nama like 'A%';
--No.13 b
SELECT * from tblPengarang WHERE nama like '%i';
--No.13 c
SELECT * from tblPengarang WHERE nama like '__a%';
--No.13 d
SELECT * from tblPengarang WHERE nama not LIKE '%n';
--No. 14
SELECT * from tblPengarang join tblgaji on tblPengarang.kdpengarang=tblgaji.kdpengarang;
--No.15
SELECT gaji,kota from tblgaji join tblPengarang on tblgaji.kdpengarang=tblPengarang.kdpengarang WHERE gaji <1000000;
--No.16
ALTER TABLE tblPengarang
ALTER COLUMN Kelamin TYPE VARCHAR(10);
--No.17
ALTER TABLE tblPengarang
ADD COLUMN gelar VARCHAR(12);
--No.18
UPDATE tblpengarang SET kota='Bandung'
WHERE nama='Jaja';
SELECT * from tblpengarang;
--No.19
CREATE VIEW vwPengarang AS SELECT
tblPengarang.kdpengarang,
tblPengarang.nama,
tblPengarang.kota,
tblgaji.gaji 
from tblPengarang
join tblgaji on tblPengarang.kdpengarang=tblgaji.kdpengarang;

SELECT * from vwPengarang;
