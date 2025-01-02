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