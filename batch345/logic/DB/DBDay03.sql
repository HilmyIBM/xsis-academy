CREATE DATABASE dbhr;

CREATE TABLE karyawan(
    id BIGINT PRIMARY KEY NOT NULL,
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
    id BIGINT PRIMARY KEY NOT NULL,
    kd_divisi VARCHAR(50) NOT NULL,
    nama_divisi VARCHAR(50) NOT NULL
);

CREATE TABLE jabatan(
    id BIGINT PRIMARY KEY NOT NULL,
    kd_jabatan VARCHAR(50) NOT NULL,
    nama_jabatan VARCHAR(50) NOT NULL,
    gaji_pokok NUMERIC,
    tunjangan_jabatan NUMERIC
);

CREATE TABLE pekerjaan(
    id BIGINT PRIMARY KEY NOT NULL,
    nip VARCHAR(50) NOT NULL,
    kode_jabatan VARCHAR(50) NOT NULL,
    kode_divisi VARCHAR(50) NOT NULL,
    tunjangan_kinerja NUMERIC,
    kota_penenempatan VARCHAR(50)
);

INSERT INTO karyawan
VALUES(1,'001','Hamidi','Samsudin','Pria','Islam','Sukabumi','1977-02-21','Jl. sudirman No 12','S1 Teknik Mesin','2015-12-07'),
      (3,'003','Paul','Christian','Pria','Kristen','Ambon','1980-05-27','Jl. Veteran No.4','S1 Pendidikan Geografi','2014-01-12'),
      (2,'002','Ghandi','Wamida','Wanita','Islam','Palu','1992-01-12','Jl. Rambutan No.22','SMA Negeri 02 Palu','2014-12-02');

INSERT INTO divisi
VALUES(1,'GD','Gudang'),
      (2,'HRD','HRD'),
      (3,'KU','Keuangan'),
      (4,'UM','Umum');

INSERT INTO jabatan
VALUES(1,'MGR','Manager',5500000,1500000),
      (2,'OB','Office Boy',1900000,200000),
      (3,'ST','Staff',3000000,750000),
      (4,'WMGR','Wakil Manager',4000000,1200000);

INSERT INTO pekerjaan
VALUES(1,'001','ST','KU',750000,'Cianjur'),
      (2,'002','OB','UM',350000,'Sukabumi'),
      (3,'003','MGR','HRD',1500000,'Sukabumi');

CREATE FUNCTION fn_gaji(
    gapok NUMERIC,
    tunjangan_jabatan NUMERIC,
    tunjangan_kinerja NUMERIC
)
RETURNS NUMERIC AS $total$
DECLARE total NUMERIC;
BEGIN
    SELECT((gapok+tunjangan_jabatan+tunjangan_kinerja)) INTO total;
    RETURN total;
END;
$total$ LANGUAGE plpgsql;

--No. 1
SELECT CONCAT(nama_depan,' ',nama_belakang) as "Nama Lengkap",nama_jabatan,gaji_pokok+tunjangan_jabatan as gaji_tunjangan
FROM karyawan join pekerjaan on karyawan.nip=pekerjaan.nip 
join jabatan on pekerjaan.kode_jabatan=jabatan.kd_jabatan
WHERE gaji_pokok+tunjangan_kinerja < 5000000;

--No.2
SELECT CONCAT(nama_depan,' ',nama_belakang) as "Nama Lengkap",nama_jabatan,nama_divisi,
fn_gaji(gaji_pokok,tunjangan_jabatan,tunjangan_kinerja)as "Total Gaji",
fn_gaji(gaji_pokok,tunjangan_jabatan,tunjangan_kinerja)*0.05 as "Pajak", 
fn_gaji(gaji_pokok,tunjangan_jabatan,tunjangan_kinerja)-
fn_gaji(gaji_pokok,tunjangan_jabatan,tunjangan_kinerja)*0.05 as "Gaji Bersih" 
FROM karyawan join pekerjaan on karyawan.nip=pekerjaan.nip 
join jabatan on pekerjaan.kode_jabatan=jabatan.kd_jabatan
join divisi on pekerjaan.kode_divisi=divisi.kd_divisi
WHERE karyawan.jenis_kelamin='Pria' and pekerjaan.kota_penenempatan NOT IN('Sukabumi');

--No.3
SELECT karyawan.nip,CONCAT(nama_depan,' ',nama_belakang) as nama_lengkap,nama_jabatan,nama_divisi,
(0.25*(gaji_pokok+tunjangan_jabatan+tunjangan_kinerja)*7) as bonus
FROM karyawan join pekerjaan on karyawan.nip=pekerjaan.nip 
join jabatan on pekerjaan.kode_jabatan=jabatan.kd_jabatan
join divisi on pekerjaan.kode_divisi=divisi.kd_divisi;

--No.4
SELECT karyawan.nip,CONCAT(nama_depan,' ',nama_belakang) as nama_lengkap,nama_jabatan,nama_divisi,
(gaji_pokok+tunjangan_jabatan+tunjangan_kinerja) as total_gaji,
(gaji_pokok+tunjangan_jabatan+tunjangan_kinerja)*0.05 as infak
FROM karyawan join pekerjaan on karyawan.nip=pekerjaan.nip 
join jabatan on pekerjaan.kode_jabatan=jabatan.kd_jabatan
join divisi on pekerjaan.kode_divisi=divisi.kd_divisi
WHERE pekerjaan.kode_jabatan='MGR';

--No.5
SELECT karyawan.nip,CONCAT(nama_depan,' ',nama_belakang) as nama_lengkap, nama_jabatan,pendidikan_terakhir,
'2000000'as tunjangan_pendidikan,(gaji_pokok+tunjangan_jabatan) + 2000000 as total_gaji
FROM karyawan join pekerjaan on karyawan.nip=pekerjaan.nip 
join jabatan on pekerjaan.kode_jabatan=jabatan.kd_jabatan
WHERE pendidikan_terakhir LIKE 'S1%';

--No.6
SELECT karyawan.nip,CONCAT(nama_depan,' ',nama_belakang) as nama_lengkap,nama_jabatan,nama_divisi,
CASE
    WHEN jabatan.kd_jabatan ='MGR' THEN 0.25*(gaji_pokok+tunjangan_jabatan+tunjangan_kinerja)*7
    WHEN jabatan.kd_jabatan ='ST' THEN 0.25*(gaji_pokok+tunjangan_jabatan+tunjangan_kinerja)*5 
    ELSE 0.25*(gaji_pokok+tunjangan_jabatan+tunjangan_kinerja)*2
end as bonus
FROM karyawan join pekerjaan on karyawan.nip=pekerjaan.nip 
join jabatan on pekerjaan.kode_jabatan=jabatan.kd_jabatan
join divisi on pekerjaan.kode_divisi=divisi.kd_divisi;

--No.7 
ALTER TABLE karyawan
ADD UNIQUE(nip);

--No.8
CREATE INDEX nip_idx
on karyawan(nip);

--No.9 
SELECT CONCAT(nama_depan,' ',UPPER(nama_belakang))as nama_lengkap FROM karyawan
WHERE nama_belakang LIKE 'W%';

--No.10
SELECT CONCAT(nama_depan,' ',nama_belakang) as nama_lengkap,tgl_masuk,nama_jabatan,
(gaji_pokok+tunjangan_jabatan+tunjangan_kinerja) as total_gaji,
CASE
WHEN '2022'-Extract(year from tgl_masuk) >= 8 THEN (gaji_pokok+tunjangan_jabatan+tunjangan_kinerja)*0.10
end as bonus
,'2022'-Extract(year from tgl_masuk) as lama_bekerja
FROM karyawan join pekerjaan on karyawan.nip=pekerjaan.nip 
join jabatan on pekerjaan.kode_jabatan=jabatan.kd_jabatan
join divisi on pekerjaan.kode_divisi=divisi.kd_divisi
WHERE '2022'-Extract(year from tgl_masuk) >=8;