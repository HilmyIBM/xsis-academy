CREATE DATABASE hrdatabase;

CREATE TABLE tb_karyawan(
    id BIGINT PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    nama_depan VARCHAR(50) NOT NULL,
    nama_belakang VARCHAR(50) NOT NULL,
    jenis_kelamin VARCHAR(50) NOT NULL,
    agama VARCHAR(50) NOT NULL,
    tempat_lahir VARCHAR(50) NOT NULL,
    tgl_lahir DATE,
    alamat VARCHAR(50) NOT NULL,
    pendidikan_terakhir VARCHAR(50) NOT NULL,
    tgl_masuk DATE
);

CREATE TABLE tb_divisi(
    id BIGINT PRIMARY KEY NOT NULL,
    kd_divisi VARCHAR(50) NOT NULL,
    nama_divisi VARCHAR(50) NOT NULL
);

CREATE TABLE tb_jabatan(
    id BIGINT PRIMARY KEY NOT NULL,
    kd_jabatan VARCHAR(50) NOT NULL,
    nama_jabatan VARCHAR(50) NOT NULL,
    gaji_pokok NUMERIC NOT NULL,
    tunjangan_jabatan NUMERIC NOT NULL
);

CREATE TABLE tb_pekerjaan(
    id BIGINT PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    kd_jabatan VARCHAR(50) NOT NULL,
    kd_divisi VARCHAR(50) NOT NULL,
    tunjangan_kinerja NUMERIC NOT NULL,
    kota_penempatan VARCHAR(50) NOT NULL
);

DROP TABLE tb_karyawan;
DROP TABLE tb_divisi;
DROP TABLE tb_jabatan;
DROP TABLE tb_pekerjaan;


INSERT INTO tb_karyawan (id, nip, nama_depan, nama_belakang, jenis_kelamin, agama, tempat_lahir, 
    tgl_lahir, alamat, pendidikan_terakhir, tgl_masuk)
VALUES
(1, '001', 'Hamidi', 'Samsudin', 'Pria', 'Islam', 'Sukabumi', '1977-04-21', 'Jl. Sudirman No.12', 'S1 Teknik Mesin', '2015-12-07'),
(3, '003', 'Paul', 'Christian', 'Pria', 'Kristen', 'Ambon', '1980-05-27', 'Jl. Veteran No.4', 'S1 Pendidikan Geografi', '2014-01-12'),
(2, '002', 'Ghandi', 'Wamida', 'Wanita', 'Islam', 'Palu', '1992-01-12', 'Jl. Rambutan No.22', 'SMA Negeri 02 Palu', '2014-12-01');

INSERT INTO tb_divisi (id, kd_divisi, nama_divisi)
VALUES
(1, 'GD', 'Gudang'),
(2, 'HRD', 'HRD'),
(3, 'KU', 'Keuangan'),
(4, 'UM', 'Umum');

INSERT INTO tb_jabatan (id, kd_jabatan, nama_jabatan, gaji_pokok, tunjangan_jabatan)
VALUES
(1, 'MGR', 'Manager', 5500000, 1500000),
(2, 'OB', 'Office Boy', 1900000, 200000),
(3, 'ST', 'Staff', 3000000, 750000),
(4, 'WMGR', 'Wakil Manager', 4000000, 1200000);

INSERT INTO tb_pekerjaan (id, nip, kd_jabatan, kd_divisi, tunjangan_kinerja, kota_penempatan)
VALUES
(1, '001', 'ST', 'KU', 750000, 'Cianjur'),
(2, '002', 'OB', 'UM', 350000, 'Sukabumi'),
(3, '003', 'MGR', 'HRD', 1500000, 'Sukabumi');

SELECT * FROM pg_database
SELECT * FROM tb_pekerjaan

SELECT * FROM information_schema.columns WHERE  table_name like 'tb_karyawan';

SELECT * FROM pg_indexes
-- No. 1
SELECT CONCAT(nama_depan, ' ', nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
tj.gaji_pokok + tj.tunjangan_jabatan AS gaji_tunjangan
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
WHERE tj.gaji_pokok + tj.tunjangan_jabatan < 5000000;

-- No. 2
SELECT CONCAT(nama_depan, ' ', nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja AS gaji_tunjangan,
(tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja)* 0.05 AS pajak,
tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja - (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja)* 0.05 AS gaji_bersih
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
WHERE jenis_kelamin LIKE 'Pria' AND tp.kota_penempatan NOT LIKE 'Sukabumi';

-- No. 3
SELECT tk.nip,
CONCAT(nama_depan, ' ', nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
td.nama_divisi,
(tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.25 *7 AS bonus
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
JOIN tb_divisi td On td.kd_divisi = tp.kd_divisi
ORDER BY tk.id

-- No. 4
SELECT tk.nip,
CONCAT(nama_depan, ' ', nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
td.nama_divisi,
tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja AS gaji_tunjangan,
(tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja)* 0.05 AS infak
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
JOIN tb_divisi td On td.kd_divisi = tp.kd_divisi
WHERE tj.kd_jabatan LIKE 'MGR';

-- No. 5
SELECT tk.nip,
CONCAT(nama_depan, ' ', nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
tk.pendidikan_terakhir,
2000000 as tunjangan_pendidikan,
2000000 + tj.gaji_pokok + tj.tunjangan_jabatan
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
WHERE tk.pendidikan_terakhir LIKE 'S1%';

-- No. 6
SELECT tk.nip,
CONCAT(tk.nama_depan, ' ', tk.nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
td.nama_divisi,
CASE
    WHEN tj.kd_jabatan = 'MGR' THEN (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.25 *7
    WHEN tj.kd_jabatan = 'ST' THEN (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.25 *5
ELSE 
    (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.25 *2
END AS bonus
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
JOIN tb_divisi td On td.kd_divisi = tp.kd_divisi
ORDER BY tk.nip;

SELECT tk.nip,
CONCAT(tk.nama_depan, ' ', tk.nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
td.nama_divisi,
CASE tj.kd_jabatan
    WHEN 'MGR' THEN (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.25 *7
    WHEN 'ST' THEN (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.25 *5
ELSE 
    (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.25 *2
END AS bonus
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
JOIN tb_divisi td On td.kd_divisi = tp.kd_divisi
ORDER BY tk.nip;

-- No. 7
ALTER TABLE tb_karyawan
ADD CONSTRAINT nip UNIQUE(nip);

-- No. 8
CREATE INDEX idx_nip ON tb_karyawan(nip);

-- No. 9
SELECT CONCAT(nama_depan,' ', UPPER(nama_belakang))
FROM tb_karyawan
WHERE nama_belakang LIKE 'W%';

-- No. 10
SELECT 
    CONCAT(tk.nama_depan, ' ', tk.nama_belakang) AS nama_lengkap, 
    tgl_masuk,
    tj.nama_jabatan,
    td.nama_divisi,
    (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) AS total_gaji,
    (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja)/10 AS bonus,
    EXTRACT(YEAR FROM AGE('2022-12-30',tgl_masuk)) AS lama_bekerja
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kd_jabatan
JOIN tb_divisi td On td.kd_divisi = tp.kd_divisi
WHERE EXTRACT(YEAR FROM AGE('2022-12-30', tgl_masuk)) >= 8;


CREATE FUNCTION fn_total_gaji(gapok NUMERIC, tunjangan_jabatan NUMERIC, tunjangan_kinerja NUMERIC)
RETURNS NUMERIC AS $total$
DECLARE total NUMERIC;
BEGIN
    SELECT (gapok + tunjangan_jabatan + tunjangan_kinerja) INTO total;
    RETURN total;
END;
$total$ LANGUAGE plpgsql;

SELECT  fn_total_gaji(1000000, 500000, 100000)