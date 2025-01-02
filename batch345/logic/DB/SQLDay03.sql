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


SELECT * FROM karyawan;
SELECT * FROM divisi;
SELECT * FROM jabatan;
SELECT * FROM pekerjaan;

--membuat fungsi hitung gaji
CREATE FUNCTION ufn_total_gaji(gapok NUMERIC, tunjangan_jabatan NUMERIC, tunjangan_kinerja NUMERIC)
RETURNS NUMERIC AS $total$
DECLARE
    total NUMERIC;
BEGIN
    SELECT (gapok + tunjangan_jabatan + tunjangan_kinerja) INTO total;
    RETURN total;
END;
$total$ LANGUAGE plpgsql;

DROP FUNCTION ufn_total_gaji(NUMERIC, NUMERIC, NUMERIC);

--membuat fungsi pajak dan gaji bersih
CREATE FUNCTION ufn_pajak_gaji_bersih(total_gaji NUMERIC, besar_pajak NUMERIC)
RETURNS TABLE(
    pajak NUMERIC,
    gaji_bersih NUMERIC
) AS $$
BEGIN
    return query
    SELECT
        total_gaji * besar_pajak pajak,
        total_gaji - (total_gaji*besar_pajak) gaji_bersih; 
END;
$$ LANGUAGE plpgsql;

DROP FUNCTION ufn_total_gaji(NUMERIC, NUMERIC, NUMERIC);


--No 1
SELECT 
    CONCAT(K.nama_depan, K.nama_belakang) nama_lengkap,
    J.nama_jabatan, 
    J.gaji_pokok + J.tunjangan_jabatan as gaji_tunjangan
FROM karyawan K
    INNER JOIN pekerjaan P ON K.nip = P.nip
    INNER JOIN jabatan J ON P.kode_jabatan = J.kd_jabatan
WHERE J.gaji_pokok + J.tunjangan_jabatan < 5000000;



--2. Tampilkan nama lengkap, jabatan, nama divisi, total gaji, pajak, gaji bersih, yg gendernya pria dan penempatan kerjanya diluar sukabumi

SELECT 
    CONCAT(K.nama_depan, K.nama_belakang), 
    J.nama_jabatan, 
    D.nama_divisi,
    ufn_total_gaji(P.tunjangan_kinerja, J.gaji_pokok, J.tunjangan_jabatan) total_gaji,
    pajak.pajak,
    ufn_total_gaji(P.tunjangan_kinerja, J.gaji_pokok, J.tunjangan_jabatan) - ufn_total_gaji(P.tunjangan_kinerja, J.gaji_pokok, J.tunjangan_jabatan)*0.05 gaji_bersih
FROM karyawan K 
    INNER JOIN pekerjaan P ON K.nip = P.nip
    INNER JOIN jabatan J ON P.kode_jabatan = J.kd_jabatan
    INNER JOIN divisi D ON P.kode_divisi = D.kd_divisi,
    ufn_pajak_gaji_bersih(ufn_total_gaji(P.tunjangan_kinerja, J.gaji_pokok, J.tunjangan_jabatan), 0.05) pajak
WHERE Lower(K.jenis_kelamin) = 'pria' and P.kota_penenempatan != 'Sukabumi';

--3. Tampilkan nip, nama lengkap, jabatan, nama divisi, bonus (bonus=25% dari total gaji(gaji pokok+tunjangan_jabatan+tunajangan_kinerja * 7)

SELECT
    K.nip,
    CONCAT(K.nama_depan, K.nama_belakang) nama_lengkap,
    J.nama_jabatan,
    D.nama_divisi,
    ufn_total_gaji(P.tunjangan_kinerja, J.gaji_pokok, J.tunjangan_jabatan)*7/4 bonus
FROM karyawan K 
    INNER JOIN pekerjaan P ON K.nip = P.nip
    INNER JOIN jabatan J ON P.kode_jabatan = J.kd_jabatan
    INNER JOIN divisi D ON P.kode_divisi = D.kd_divisi;


--3. Tampilkan nip, nama lengkap, jabatan, nama divisi, bonus (bonus=25% dari total gaji(gaji pokok+tunjangan_jabatan+tunajangan_kinerja * 7)

SELECT
    K.nip,
    CONCAT(K.nama_depan, K.nama_belakang) nama_lengkap,
    J.nama_jabatan,
    D.nama_divisi,
    ufn_total_gaji(P.tunjangan_kinerja, J.gaji_pokok, J.tunjangan_jabatan)*7/4 bonus
FROM karyawan K 
    INNER JOIN pekerjaan P ON K.nip = P.nip
    INNER JOIN jabatan J ON P.kode_jabatan = J.kd_jabatan
    INNER JOIN divisi D ON P.kode_divisi = D.kd_divisi;


