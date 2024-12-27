
CREATE TABLE tblPengarang(
    ID SERIAL PRIMARY KEY NOT NULL,
    kd_pengarang VARCHAR(7) NOT NULL,
    Nama VARCHAR(30) NOT NULL,
    Alamat VARCHAR(80) NOT NULL,
    Kota VARCHAR(15) NOT NULL,
    Kelamin VARCHAR(1) NOT NULL
); 

CREATE TABLE tblGaji(
    ID SERIAL PRIMARY KEY NOT NULL,
    kd_pengarang VARCHAR(7) NOT NULL,
    Nama VARCHAR(30) NOT NULL,
    Gaji DECIMAL(18 , 4) NOT NULL
); 

DROP TABLE tblPengarang;
DROP TABLE tblGaji;


INSERT INTO tblPengarang(kd_pengarang, Nama, Alamat, Kota, Kelamin)
VALUES 
('P0001', 'Ashadi', 'Jl. Beo 25', 'Yogya', 'P'), 
('P0002', 'Rian',	'Jl. Solo 123',	'Yogya', 'P'),
('P0003',	'Suwadi',	'Jl. Semangka 13',	'Bandung',	'P'),
('P0004',	'Siti',	'Jl. Durian 15',	'Solo',	'W'),
('P0005',	'Amir',	'Jl. Gajah 33',	'Kudus',	'P'),
('P0006',	'Suparman', 'Jl. Harimau 25',	'Jakarta',	'P'),
('P0007',	'Jaja',	'Jl. Singa 7',	'Bandung',	'P'),
('P0008',	'Saman',	'Jl. Naga 12',	'Yogya',	'P'),
('P0009',	'Anwar',	'Jl. Tidar 6A',	'Magelang', 'P'),
('P0010',	'Fatmawati',	'Jl. Renjana 4',	'Bogor', 'W');


INSERT INTO tblGaji(kd_pengarang, Nama, Gaji) 
VALUES
('P0002',	'Rian',	600000),
('P0005',	'Amir',	700000),
('P0004',	'Siti',	500000),
('P0003',	'Suwadi',	1000000),
('P0010',	'Fatmawati',	600000),
('P0008',	'Saman',	750000);


SELECT * FROM tblPengarang;
SELECT * FROM tblGaji;


-- No.1
SELECT COUNT(kd_pengarang)
FROM tblpengarang

-- No.2
SELECT  Kelamin, COUNT(Kelamin)
FROM tblpengarang
GROUP BY Kelamin

-- No.3
SELECT  kota, COUNT(Kota)
FROM tblpengarang
GROUP BY Kota

-- No.4
SELECT  kota, COUNT(Kota)
FROM tblpengarang
GROUP BY Kota
HAVING COUNT(Kota) > 1

-- No.5
SELECT (SELECT MAX(kd_pengarang) AS High FROM tblpengarang), (SELECT MIN(kd_pengarang) AS Low FROM tblpengarang)

-- No.6
SELECT (SELECT MAX(Gaji) FROM tblGaji), (SELECT MIN(Gaji) FROM tblGaji)

-- No. 7
SELECT Gaji FROM tblGaji WHERE Gaji > 600000

-- No. 8
SELECT SUM(Gaji) AS total_gaji FROM tblGaji

-- No. 9
SELECT Kota, SUM(Gaji) as gaji_kota 
FROM tblGaji tg 
JOIN tblpengarang tp 
ON tg.kd_pengarang = tp.kd_pengarang
GROUP BY Kota

-- No. 10
SELECT *
FROM tblpengarang
WHERE kd_pengarang >= 'P0003' AND kd_pengarang <= 'P0006'

-- No. 11
SELECT *
FROM tblpengarang
WHERE kota IN ('Yogya', 'Solo', 'Magelang');

-- No. 12
SELECT *
FROM tblpengarang
WHERE kota NOT IN ('Yogya');

-- No. 13
SELECT *
FROM tblpengarang
WHERE Nama LIKE 'A%';

SELECT *
FROM tblpengarang
WHERE Nama LIKE '%i';

SELECT *
FROM tblpengarang
WHERE Nama LIKE '__a%'

SELECT *
FROM tblpengarang
WHERE Nama LIKE '%n';

-- No. 14
SELECT *
FROM tblpengarang tp
JOIN tblGaji tg
ON tp.kd_pengarang = tg.kd_pengarang

-- No. 15
SELECT tp.Kota, tg.Gaji
FROM tblpengarang tp
JOIN tblGaji tg
ON tp.kd_pengarang = tg.kd_pengarang
WHERE tg.Gaji < 1000000

-- No. 16
ALTER TABLE tblpengarang
ALTER COLUMN kelamin TYPE VARCHAR(10);

SELECT *
FROM information_schema.columns
WHERE table_name = 'tblpengarang'


-- No. 17
ALTER TABLE tblpengarang 
ADD COLUMN Gelar VARCHAR(12);

-- No. 18
UPDATE tblpengarang
SET Alamat = 'Jl. Cendrawasih 65', Kota = 'Pekanbaru'
WHERE Nama = 'Rian'

-- No. 19
CREATE VIEW vwPengarang AS
SELECT tp.kd_pengarang, tp.Nama, tp.Kota, tg.Gaji
FROM tblpengarang tp
JOIN tblGaji tg
ON tg.kd_pengarang = tp.kd_pengarang

SELECT * FROM vwPengarang