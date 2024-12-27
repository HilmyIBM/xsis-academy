CREATE DATABASE dbhr;

CREATE TABLE karyawan(
    id SERIAL PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    nama_depan VARCHAR(50) NOT NULL,
    nama_belakang VARCHAR(50) NOT NULL,
    jenis_kelamin VARCHAR(50) NOT NULL,
    agama VARCHAR(50) NOT NULL,
    tempatlahir VARCHAR(50) NOT NULL,
    tgl_lahir DATE,
    alamat VARCHAR(100) NOT NULL,
    pendidikan_terakhir VARCHAR(50) NOT NULL,
    tgl_masuk DATE
);

CREATE TABLE divisi(
    id SERIAL PRIMARY KEY NOT NULL,
    kd_divisi VARCHAR(50) NOT NULL,
    nama_divisi VARCHAR(50) NOT NULL
);

CREATE TABLE jabatan(
    id SERIAL PRIMARY KEY NOT NULL,
    kd_jabatan VARCHAR(50) NOT NULL,
    nama_jabatan VARCHAR(50) NOT NULL,
    gaji_pokok NUMERIC,
    tunjangan_jabatan NUMERIC
);

CREATE TABLE pekerjaan(
    id SERIAL PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    kode_jabatan VARCHAR(50) NOT NULL,
    kode_divisi VARCHAR(50) NOT NULL,
    tunjangan_kinerja NUMERIC,
    kota_penenempatan VARCHAR(50)
);