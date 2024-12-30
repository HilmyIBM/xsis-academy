CREATE TABLE tb_karyawan(
    id BIGINT PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    nama_depan VARCHAR(50) NOT NULL,
    nama_belakang VARCHAR(50) NOT NULL,
    jenis_kelamin VARCHAR(50) NOT NULL,
    agama VARCHAR(50) NOT NULL,
    tempat_tinggal VARCHAR(50) NOT NULL,
    tgl_lahir DATE,
    alamat VARCHAR(100) NOT NULL,
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

-- DROP TABLE tb_karyawan;
-- DROP TABLE tb_divisi;
-- DROP TABLE tb_jabatan;
-- DROP TABLE tb_pekerjaan;


INSERT INTO tb_karyawan (id, nip, nama_depan, nama_belakang, jenis_kelamin, agama, tempat_tinggal, 
    tgl_lahir, alamat, pendidikan_terakhir, tgl_masuk)
VALUES
(1, '001', 'Hamidi', 'Samsudin', 'Pria', 'Islam', 'Sukabumi', '1977-04-21', 'Jl. Sudirman No.12', 'S1 Teknik Mesin', '2015-12-07'),
(2, '002', 'Ghandi', 'Wamida', 'Wanita', 'Islam', 'Palu', '1992-01-12', 'Jl. Rambutan No.22', 'SMA Negeri 02 Palu', '2014-12-01'),
(3, '003', 'Paul', 'Christian', 'Pria', 'Kristen', 'Ambon', '1980-05-27', 'Jl. Veteran No.4', 'S1 Pendidikan Geografi', '2014-01-12');

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

SELECT * from tb_karyawan;
SELECT * from tb_divisi;
SELECT * from tb_jabatan;
SELECT * from tb_pekerjaan;

-- 1
SELECT 
    CONCAT (b.nama_depan, ' ', b.nama_belakang) AS nama_lengkap,
    c.nama_jabatan,
    c.tunjangan_jabatan + c.gaji_pokok AS gaji_tunjangan
FROM tb_pekerjaan a
    JOIN tb_karyawan b ON a.nip = b.nip
    JOIN tb_jabatan c on a.kd_jabatan = c.kd_jabatan
WHERE c.tunjangan_jabatan + c.gaji_pokok < 5000000

-- 2
SELECT 
    CONCAT (b.nama_depan, ' ', b.nama_belakang) AS nama_lengkap,
    c.nama_jabatan AS nama_jabatan,
    d.nama_divisi AS nama_divisi,
    c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja AS total_gaji,
    (c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 0.05 AS pajak,
    (c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) - ((c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 0.05) AS gaji_bersih
FROM tb_pekerjaan a
    JOIN tb_karyawan b ON a.nip = b.nip
    JOIN tb_jabatan c ON a.kd_jabatan = c.kd_jabatan
    JOIN tb_divisi d ON a.kd_divisi = d.kd_divisi
WHERE b.jenis_kelamin = 'Pria' AND a.kota_penempatan != 'Sukabumi';

-- 3
SELECT 
    a.nip AS nip,
    CONCAT (b.nama_depan, ' ', b.nama_belakang) AS nama_lengkap,
    c.nama_jabatan AS nama_jabatan,
    d.nama_divisi AS nama_divisi,
    ((c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 7) * 0.25 AS bonus
FROM tb_pekerjaan a
    JOIN tb_karyawan b ON a.nip = b.nip
    JOIN tb_jabatan c ON a.kd_jabatan = c.kd_jabatan
    JOIN tb_divisi d ON a.kd_divisi = d.kd_divisi
ORDER BY a.nip ASC;

-- 4
SELECT 
    a.nip AS nip,
    CONCAT (b.nama_depan, ' ', b.nama_belakang) AS nama_lengkap,
    c.nama_jabatan AS nama_jabatan,
    d.nama_divisi AS nama_divisi,
    c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja AS total_gaji,
    (c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 0.05 AS infak
FROM tb_pekerjaan a
    JOIN tb_karyawan b ON a.nip = b.nip
    JOIN tb_jabatan c ON a.kd_jabatan = c.kd_jabatan
    JOIN tb_divisi d ON a.kd_divisi = d.kd_divisi
WHERE a.kd_jabatan = 'MGR';

-- 5
SELECT 
    a.nip AS nip,
    CONCAT (b.nama_depan, ' ', b.nama_belakang) AS nama_lengkap,
    c.nama_jabatan AS nama_jabatan,
    b.pendidikan_terakhir AS pendidikan_terakhir,
    2000000 AS tunjangan_pendidikan,
    (c.tunjangan_jabatan + c.gaji_pokok + 2000000) AS total_gaji
FROM tb_pekerjaan a
    JOIN tb_karyawan b ON a.nip = b.nip
    JOIN tb_jabatan c ON a.kd_jabatan = c.kd_jabatan
    JOIN tb_divisi d ON a.kd_divisi = d.kd_divisi
WHERE b.pendidikan_terakhir LIKE 'S1%';

-- 6
SELECT 
    a.nip AS nip,
    CONCAT (b.nama_depan, ' ', b.nama_belakang) AS nama_lengkap,
    c.nama_jabatan AS nama_jabatan,
    d.nama_divisi AS nama_divisi,
    CASE 
        WHEN a.kd_jabatan = 'MGR' THEN ((c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 7) * 0.25
        WHEN a.kd_jabatan = 'ST' THEN ((c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 5) * 0.25
        ELSE ((c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 2) * 0.25
    END AS bonus
FROM tb_pekerjaan a
    JOIN tb_karyawan b ON a.nip = b.nip
    JOIN tb_jabatan c ON a.kd_jabatan = c.kd_jabatan
    JOIN tb_divisi d ON a.kd_divisi = d.kd_divisi
ORDER BY a.nip ASC;

-- 7
ALTER TABLE tb_karyawan
    ADD CONSTRAINT unique_nip UNIQUE(nip);

-- 8
CREATE INDEX idx_nip ON tb_karyawan(nip);

-- 9
SELECT
    CONCAT (nama_depan, ' ',
        CASE
            WHEN nama_belakang LIKE 'W%' THEN UPPER(nama_belakang)
            ELSE nama_belakang
        END
    ) AS nama_lengkap
FROM tb_karyawan;

-- 10
SELECT
    CONCAT(b.nama_depan, ' ', b.nama_belakang) AS nama_lengkap,
    b.tgl_masuk,
    c.nama_jabatan,
    d.nama_divisi,
    (c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) AS total_gaji,
    CASE
        WHEN EXTRACT(YEAR FROM AGE('2022-12-31', b.tgl_masuk)) >= 8
        THEN (c.tunjangan_jabatan + c.gaji_pokok + a.tunjangan_kinerja) * 0.1
        ELSE 0
    END AS bonus,
    EXTRACT(YEAR FROM AGE('2022-12-31', b.tgl_masuk))  AS lama_bekerja
FROM tb_pekerjaan a
JOIN tb_karyawan b ON a.nip = b.nip
JOIN tb_jabatan c ON a.kd_jabatan = c.kd_jabatan
JOIN tb_divisi d ON a.kd_divisi = d.kd_divisi
WHERE EXTRACT(YEAR FROM AGE('2022-12-31', b.tgl_masuk)) >= 8
ORDER BY a.nip ASC;



