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

--10. Pengarang dari P0003-P0006
SELECT * FROM tblPengarang
WHERE kd_pengarang BETWEEN 'P0003' AND 'P0006';

SELECT * FROM tblPengarang
WHERE kd_pengarang >= 'P0003' AND kd_pengarang <= 'P0006';

--11.Pengarang dari Yogya, Solo dan Magelang
SELECT * FROM tblPengarang
WHERE kota = 'Yogya' OR kota = 'Solo' OR kota = 'Magelang';

SELECT * FROM tblPengarang
WHERE kota IN('Yogya', 'Solo', 'Magelang');

--//Concat String untuk menggabungkan String//--
'P0001-ASHADI'
SELECT CONCAT(kd_pengarang, '-', nama) FROM  tblpengarang;

--//Wildcard Character (%  _)//--
select kota from tblpengarang
where LOWER(kota) like 'b%'
group by kota;

select kota from tblpengarang
where kota like '____' group by kota;

select nama from tblpengarang
where nama like '%p%' or nama like '%P%';

--11.Pengarang bukan dari Yogya
SELECT * FROM tblPengarang
WHERE kota NOT IN ('Yogya');

SELECT * FROM tblPengarang
WHERE kota != 'Yogya';

SELECT * FROM tblPengarang
WHERE kota <> 'Yogya';

--1. Tampilkan nama lengkap, nama jabatan, tunjangan jabatan + gaji , yang gaji + tunjangan kinerja dibawah 5juta
SELECT
    CONCAT(K.nama_depan, ' ', K.nama_belakang) nama_lengkap,
    J.nama_jabatan, (J.tunjangan_jabatan + J.gaji_pokok) gaji_tunjangan
FROM tb_karyawan K
    INNER JOIN tb_pekerjaan P ON K.nip=P.nip
    INNER JOIN tb_jabatan J ON P.kode_jabatan=J.kd_jabatan
WHERE (J.tunjangan_jabatan + J.gaji_pokok) < 5000000
ORDER BY gaji_tunjangan DESC
LIMIT 1;

SELECT CONCAT(K.nama_depan, ' ', K.nama_belakang) nama_lengkap,
        J.nama_jabatan, (J.tunjangan_jabatan + J.gaji_pokok) gaji_tunjangan
FROM tb_karyawan K
    INNER JOIN tb_pekerjaan P ON K.nip=P.nip
    INNER JOIN tb_jabatan J ON P.kode_jabatan=J.kd_jabatan
WHERE
    (J.tunjangan_jabatan + J.gaji_pokok) = (
        SELECT MAX(J.tunjangan_jabatan + J.gaji_pokok) gaji_tunjangan
        FROM tb_jabatan J
        WHERE (J.tunjangan_jabatan + J.gaji_pokok) < 5000000
    )

SELECT CONCAT(K.nama_depan, ' ', K.nama_belakang) nama_lengkap,
    J.nama_jabatan, (J.tunjangan_jabatan + J.gaji_pokok) gaji_tunjangan
FROM tb_karyawan K
    INNER JOIN tb_pekerjaan P ON K.nip=P.nip
    INNER JOIN tb_jabatan J ON P.kode_jabatan=J.kd_jabatan
GROUP BY k.nama_depan, K.nama_belakang, J.nama_jabatan, J.tunjangan_jabatan, J.gaji_pokok
HAVING (J.tunjangan_jabatan + J.gaji_pokok) = (
    SELECT max(J.tunjangan_jabatan + J.gaji_pokok) gaji_tunjangan
    FROM tb_jabatan J
    WHERE (J.tunjangan_jabatan + J.gaji_pokok) < 5000000
)

--2. Tampilkan nama lengkap, jabatan, nama divisi, total gaji, pajak, gaji bersih, yg gendernya pria dan penempatan kerjanya diluar sukabumi
SELECT * FROM tb_divisi;
SELECT
    CONCAT(K.nama_depan, ' ', K.nama_belakang) nama_lengkap,
    J.nama_jabatan, D.nama_divisi,
    ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) AS total_gaji,
    ufn_pajak(ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja), 0.005) AS pajak,
    ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) - (ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) * 0.005) AS gaji_bersih
FROM
    tb_karyawan K
    INNER JOIN tb_pekerjaan P ON K.nip=P.nip
    INNER JOIN tb_jabatan J ON P.kode_jabatan=J.kd_jabatan
    INNER JOIN tb_divisi D ON P.kode_divisi=D.kd_divisi
WHERE
    K.jenis_kelamin='Pria'
    AND P.kota_penempatan NOT IN ('Sukabumi');

-- CREATE OR REPLACE FUNCTION ufn_gaji_pajak()
-- RETURNS TABLE(
--     nama_lengkap VARCHAR(100),
--     nama_jabatan VARCHAR(100),
--     nama_divisi VARCHAR(100),
--     total_gaji NUMERIC,
--     pajak NUMERIC,
--     gaji_bersih NUMERIC
-- )
-- AS $$
-- DECLARE persen_pajak NUMERIC := 0.005;
-- BEGIN
--     RETURN QUERY SELECT
--         CONCAT(K.nama_depan, ' ', K.nama_belakang) nama_lengkap,
--         J.nama_jabatan, D.nama_divisi,
--         ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) AS total_gaji,
--         ufn_pajak(ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja), persen_pajak) AS pajak,
--         ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) - (ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) * persen_pajak) AS gaji_bersih
--     FROM
--         tb_karyawan K
--         INNER JOIN tb_pekerjaan P ON K.nip=P.nip
--         INNER JOIN tb_jabatan J ON P.kode_jabatan=J.kd_jabatan
--         INNER JOIN tb_divisi D ON P.kode_divisi=D.kd_divisi
--     WHERE
--         K.jenis_kelamin='Pria'
--         AND P.kota_penempatan NOT IN ('Sukabumi');
-- END;
-- $$
-- LANGUAGE plpgsql;
-- SELECT * FROM ufn_gaji_pajak();

CREATE FUNCTION ufn_pajak(total_gaji NUMERIC, persen_pajak NUMERIC)
RETURNS NUMERIC AS $pajak$
DECLARE pajak NUMERIC;
BEGIN
    SELECT (total_gaji * persen_pajak) INTO pajak;
    RETURN pajak;
END;
$pajak$ LANGUAGE plpgsql;

--5. Tampilkan nip, nama lengkap, nama jabatan, pendidikan terakhir, tunjangan pendidikan(2jt), dan total gaji(gapok+tjabatan+tpendidikan) dimana pendidikan akhirnya adalah S1
SELECT
    P.nip
    , CONCAT(K.nama_depan, ' ', K.nama_belakang) nama_lengkap
    , J.nama_jabatan, K.pendidikan_terakhir, 2000000 tunjangan_pendidikan
    , ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, 2000000) AS total_gaji
FROM tb_karyawan K
    INNER JOIN tb_pekerjaan P ON K.nip=P.nip
    INNER JOIN tb_jabatan J ON P.kode_jabatan=J.kd_jabatan
WHERE LOWER(K.pendidikan_terakhir) NOT LIKE 's1%';

--7. Buatlah kolom nip pada table karyawan sebagai kolom unique
ALTER TABLE tb_karyawan
    ADD CONSTRAINT uq_karyawan_nip UNIQUE(nip);

--8. buatlah kolom nip pada table karyawan sebagai index
CREATE INDEX idx_tb_karyawan ON tb_karyawan(nip);

--9. Tampilkan nama lengkap, nama belakangnya diubah menjadi huruf capital dengan kondisi nama belakang di awali dengan huruf W

--10.  Perusahaan akan memberikan bonus sebanyak 10% dari total gaji bagi karyawan yg sudah join di perusahaan diatas sama dengan 8 tahun per 2022
