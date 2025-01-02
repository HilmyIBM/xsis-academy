CREATE DATABASE "DB_HR";

-- setup tb_karyawan


CREATE TABLE tb_karyawan(
    id BIGINT PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    nama_depan VARCHAR(50) NOT NULL,
    nama_belakang VARCHAR(50) NOT NULL,
    jenis_kelamin VARCHAR(50) NOT NULL,
    agama VARCHAR(50) NOT NULL,
    tempat_lahir VARCHAR(50) NOT NULL,
    tgl_lahir DATE,
    alamat VARCHAR(100) NOT NULL,
    pendidikan_terakhir VARCHAR(50) NOT NULL,
    tgl_masuk DATE
);

SELECT * FROM tb_karyawan;


INSERT INTO tb_karyawan(id, nip, nama_depan, nama_belakang, jenis_kelamin, agama, tempat_lahir, tgl_lahir, alamat, pendidikan_terakhir, tgl_masuk)
VALUES
(1, '001', 'Hamidi', 'Samsudin', 'Pria', 'Islam', 'Sukabumi', '1977-04-21', 'Jl. Sudirman No.12', 'S1 Teknik Mesin', '2015-12-07'),
(3, '003', 'Paul', 'Christian', 'Pria', 'Kristen', 'Ambon', '1980-05-27', 'Jl. Veteran No.4', 'S1 Pendidikan Geografi', '2014-01-12'),
(2, '002', 'Ghandi', 'Wamida', 'Wanita', 'Islam', 'Palu', '1992-01-12', 'Jl. Rambutan No.22', 'SMA Negeri 02 Palu', '2014-12-01');

drop TABLE tb_karyawan;


--setup tb_divisi

CREATE TABLE tb_divisi(
    id BIGINT PRIMARY KEY NOT NULL,
    kd_divisi VARCHAR(50) NOT NULL,
    nama_divisi VARCHAR(50) NOT NULL
);

INSERT INTO tb_divisi(id, kd_divisi, nama_divisi)
VALUES
(1, 'GD', 'Gudang'),
(2, 'HRD', 'HRD'),
(3, 'KU', 'Keuangan'),
(4, 'UM', 'Umum');

SELECT * from tb_divisi;

--setup tb_jabatan

CREATE TABLE tb_jabatan(
    id BIGINT PRIMARY KEY NOT NULL,
    kd_jabatan VARCHAR(50) NOT NULL,
    nama_jabatan VARCHAR(50) NOT NULL,
    gaji_pokok NUMERIC,
    tunjangan_jabatan NUMERIC
);

INSERT INTO tb_jabatan(id, kd_jabatan, nama_jabatan, gaji_pokok, tunjangan_jabatan)
VALUES
(1, 'MGR', 'Manager', 5500000, 1500000),
(2, 'OB', 'Office Boy', 1900000, 200000),
(3, 'ST', 'Staff', 3000000, 750000),
(4, 'WMGR', 'Wakil Manager', 4000000, 1200000);

SELECT * FROM tb_jabatan;


--Setup tb_pekerjaan

CREATE TABLE tb_pekerjaan(
    id BIGINT PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    kode_jabatan VARCHAR(50) NOT NULL,
    kode_divisi VARCHAR(50) NOT NULL,
    tunjangan_kinerja NUMERIC,
    kota_penempatan VARCHAR(50)
);

INSERT INTO tb_pekerjaan(id, nip, kode_jabatan, kode_divisi, tunjangan_kinerja, kota_penempatan)
VALUES
(1, '001', 'ST', 'KU', 750000, 'Cianjur'),
(2, '002', 'OB', 'UM', 350000, 'Sukabumi'),
(3, '003', 'MGR', 'HRD', 1500000, 'Sukabumi');


SELECT * FROM tb_pekerjaan;


--no 1
SELECT CONCAT(K.nama_depan,' ', K.nama_belakang) as nama_lengkap, J.nama_jabatan, (J.gaji_pokok+J.tunjangan_jabatan) AS gaji_tunjangan
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
WHERE (J.gaji_pokok+J.tunjangan_jabatan) < 5000000;



--misal mau tunjukkin yang paling tinggi aja
--kalo pake limit
SELECT CONCAT(K.nama_depan,' ', K.nama_belakang) as nama_lengkap, J.nama_jabatan, (J.gaji_pokok+J.tunjangan_jabatan) AS gaji_tunjangan
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
WHERE 
(J.gaji_pokok+J.tunjangan_jabatan) < 5000000
ORDER BY J.gaji_pokok+J.tunjangan_jabatan DESC
LIMIT 1;

--kalo pake max
SELECT CONCAT(K.nama_depan,' ', K.nama_belakang) as nama_lengkap, J.nama_jabatan, (J.gaji_pokok+J.tunjangan_jabatan) AS gaji_tunjangan
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
WHERE (J.gaji_pokok+J.tunjangan_jabatan) = (
    SELECT MAX(J.gaji_pokok+J.tunjangan_jabatan) AS gaji_tunjangan
    FROM tb_karyawan AS K
    INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
    WHERE (J.gaji_pokok+J.tunjangan_jabatan)< 5000000
);

--subquery bisa di kolom, where

--no 2
SELECT CONCAT(K.nama_depan,' ', K.nama_belakang) as nama_lengkap, J.nama_jabatan ,D.nama_divisi, (J.gaji_pokok+J.tunjangan_jabatan+P.tunjangan_kinerja) AS "total gaji", ((J.gaji_pokok+J.tunjangan_jabatan+P.tunjangan_kinerja)*0.05) AS "pajak", ((J.gaji_pokok+J.tunjangan_jabatan+P.tunjangan_kinerja)*0.95) AS "gaji bersih"
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
INNER JOIN tb_divisi AS D ON p.kode_divisi = D.kd_divisi
WHERE P.kota_penempatan != 'Sukabumi' AND K.jenis_kelamin = 'Pria';


--no 3
SELECT K.nip, CONCAT(K.nama_depan,' ', K.nama_belakang) as nama_lengkap, J.nama_jabatan ,D.nama_divisi, ((J.gaji_pokok+J.tunjangan_jabatan+P.tunjangan_kinerja)*7*0.25) AS "bonus"
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
INNER JOIN tb_divisi AS D ON p.kode_divisi = D.kd_divisi;

--no 4
SELECT K.nip, CONCAT(K.nama_depan,' ', K.nama_belakang) as nama_lengkap, J.nama_jabatan ,D.nama_divisi, (J.gaji_pokok+J.tunjangan_jabatan+P.tunjangan_kinerja) AS "total_gaji", (J.gaji_pokok+J.tunjangan_jabatan+P.tunjangan_kinerja)*0.05 AS "infak"
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
INNER JOIN tb_divisi AS D ON p.kode_divisi = D.kd_divisi
WHERE J.kd_jabatan = 'MGR';


--no 5
SELECT K.nip, CONCAT(K.nama_depan,' ', K.nama_belakang) as nama_lengkap, J.nama_jabatan,  K.pendidikan_terakhir, 2000000 AS "tunjangan_pendidikan", (J.gaji_pokok+J.tunjangan_jabatan+2000000) AS "total_gaji"
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
WHERE Upper(K.pendidikan_terakhir) like 'S1%';


--function: process input into an output
CREATE FUNCTION ufn_total_gaji(gapok NUMERIC, tunjangan_jabatan NUMERIC, tunjangan_kinerja NUMERIC) 
--ufn: user function, bukan bawaan
RETURNS NUMERIC as $total$
DECLARE total NUMERIC;
BEGIN
    SELECT(gapok + tunjangan_jabatan + tunjangan_kinerja) INTO total;
    RETURN (total);
END;
$total$ LANGUAGE plpgsql

--no 6
SELECT K.nip, CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap, J.nama_jabatan, D.nama_divisi, 
    CASE 
        WHEN J.kd_jabatan = 'MGR' THEN (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 7 * 0.25
        WHEN J.kd_jabatan = 'ST' THEN (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 5 * 0.25
        ELSE (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 2 * 0.25
    END AS bonus
FROM 
tb_karyawan AS K
INNER JOIN 
tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN 
tb_jabatan AS J ON P.kode_jabatan = J.kd_jabatan
INNER JOIN 
tb_divisi AS D ON P.kode_divisi = D.kd_divisi;


--no 7
ALTER TABLE tb_karyawan
ADD CONSTRAINT nip_unique UNIQUE (nip);

--no 8
CREATE INDEX idx_nip ON tb_karyawan(nip);

--no 9
SELECT K.nip, CONCAT(K.nama_depan,' ', 
    CASE 
        WHEN K.nama_belakang LIKE 'W%' THEN UPPER(K.nama_belakang)
        ELSE K.nama_belakang
    END
) as nama_lengkap, J.nama_jabatan ,D.nama_divisi
FROM tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON p.kode_jabatan = J.kd_jabatan
INNER JOIN tb_divisi AS D ON p.kode_divisi = D.kd_divisi;

--no 10
SELECT K.nip, CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap, K.tgl_masuk, J.nama_jabatan, D.nama_divisi, (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) AS total_gaji,
    CASE 
        WHEN EXTRACT(YEAR FROM AGE(DATE '2022-12-31', K.tgl_masuk)) >= 8 THEN 
            (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 0.10
        ELSE 
            0
    END AS bonus,
    EXTRACT(YEAR FROM AGE(DATE '2022-12-31', K.tgl_masuk)) AS lama_bekerja
FROM 
    tb_karyawan AS K
INNER JOIN tb_pekerjaan AS P ON K.nip = P.nip
INNER JOIN tb_jabatan AS J ON P.kode_jabatan = J.kd_jabatan
INNER JOIN tb_divisi AS D ON P.kode_divisi = D.kd_divisi
WHERE EXTRACT(YEAR FROM AGE(DATE '2022-12-31', K.tgl_masuk)) >= 8;