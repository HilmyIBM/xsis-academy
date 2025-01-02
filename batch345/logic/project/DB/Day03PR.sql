CREATE DATABASE "DB_HR";

CREATE TABLE tb_karyawan(
    id serial primary key not null,
    nip varchar(50) not null,
    nama_depan varchar(50) not null,
    nama_belakang varchar(50) not null,
    jenis_kelamin varchar(50) not null,
    agama varchar(50) not null,
    tempat_lahir varchar(50) not null,
    tgl_lahir date,
    alamat varchar(100) not null,
    pendidikan_terakhir varchar(50) not null,
    tgl_masuk date
);

INSERT INTO tb_karyawan (nip, nama_depan, nama_belakang, jenis_kelamin, agama, tempat_lahir, tgl_lahir, alamat, pendidikan_terakhir, tgl_masuk)
VALUES
    ('001', 'Hamidi', 'Samsudin', 'Pria', 'Islam', 'Sukabumi', '1997-04-21', 'Jl. Sudirman No 12', 'S1 Teknik Mesin', '2015-12-07'),
    ('003', 'Paul', 'Christian', 'Pria', 'Kristen', 'Ambon', '1980-05-27', 'Jl. Veteran No 4', 'S1 Pendidikan Geografi', '2014-01-12'),
    ('002', 'Ghandi', 'Wamida', 'Wanita', 'Islam', 'Palu', '1992-01-12', 'Jl Rambutan No 22', 'SMA Negri 02 Palu', '2014-12-01');

CREATE TABLE tb_divisi(
    id bigserial primary key not null,
    kd_divisi varchar(50) not null,
    nama_divisi varchar(50) not null
);

INSERT INTO tb_divisi (kd_divisi, nama_divisi)
VALUES
    ('GD', 'Gudang'),
    ('HRD', 'HRD'),
    ('KU', 'Keuangan'),
    ('UM', 'Umum');

CREATE TABLE tb_jabatan(
  id bigserial primary key not null,
    kd_jabatan varchar(50) not null,
    nama_jabatan varchar(50) not null ,
    gaji_pokok numeric,
    tunjangan_jabatan numeric
);

INSERT INTO tb_jabatan (kd_jabatan, nama_jabatan, gaji_pokok, tunjangan_jabatan) values
                           ('MGR', 'Manager', 5500000, 1500000),
                           ('OB', 'Office Boy', 1900000, 200000),
                           ('ST', 'STAFF', 3000000, 750000),
    ('WMGR','Wakil Jabatan', 4000000, 1200000);

CREATE TABLE tb_pekerjaan(
    id bigserial primary key not null ,
    nip varchar(50) not null,
    kode_jabatan varchar(50) not null,
    kode_divisi varchar(50) not null,
    tunjangan_kinerja numeric,
    kota_penempatan varchar(50)
);

INSERT INTO tb_pekerjaan (nip, kode_jabatan, kode_divisi, tunjangan_kinerja, kota_penempatan)
VALUES
    ('001', 'ST', 'KU', 750000, 'Cianjur'),
    ('002', 'OB','UM', 350000, 'Sukabumi'),
    ('003', 'MGR', 'HRD', 1500000, 'Sukabumi');

-------------- PR --------------

-- NO 1
SELECT
    tk.nama_depan || ' ' || tk.nama_belakang,
    tj.nama_jabatan,
    tj.gaji_pokok + tj.tunjangan_jabatan as gaji_tunjangan
FROM
    tb_pekerjaan tp
    INNER JOIN public.tb_karyawan tk on tp.nip = tk.nip
    INNER JOIN tb_jabatan tj on tp.kode_jabatan = tj.kd_jabatan
WHERE tj.gaji_pokok + tj.tunjangan_jabatan < 5000000
ORDER BY gaji_tunjangan desc;

-- NO 2
SELECT
    tk.nama_depan || ' ' || tk.nama_belakang as nam_lengkap,
    tj.nama_jabatan,
    td.nama_divisi,
    tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja as total_gaji,
    (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.05 as pajak,
    (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) - ((tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.05) as gaji_bersih
FROM
    tb_pekerjaan tp
        JOIN public.tb_karyawan tk on tp.nip = tk.nip
        JOIN tb_jabatan tj on tp.kode_jabatan = tj.kd_jabatan
        JOIN tb_divisi td on tp.kode_divisi = td.kd_divisi
WHERE tk.jenis_kelamin = 'Pria' AND tp.kota_penempatan != 'Sukabumi';

-- NO 3
SELECT
    tk.nip,
    tk.nama_depan || ' ' || tk.nama_belakang as nam_lengkap,
    tj.nama_jabatan,
    td.nama_divisi,
    ((tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 7) * 0.25 as bonus
FROM
    tb_pekerjaan tp
        JOIN tb_karyawan tk on tp.nip = tk.nip
        JOIN tb_jabatan tj on tp.kode_jabatan = tj.kd_jabatan
        JOIN tb_divisi td on tp.kode_divisi = td.kd_divisi;

-- NO 4
SELECT
    tk.nip,
    tk.nama_depan || ' ' || tk.nama_belakang as nam_lengkap,
    tj.nama_jabatan,
    td.nama_divisi,
    tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja as total_gaji,
    (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 0.05 as infak
FROM
    tb_pekerjaan tp
        JOIN public.tb_karyawan tk on tp.nip = tk.nip
        JOIN tb_jabatan tj on tp.kode_jabatan = tj.kd_jabatan
        JOIN tb_divisi td on tp.kode_divisi = td.kd_divisi
WHERE tj.kd_jabatan = 'MGR';

-- NO 5
SELECT
    tk.nip,
    tk.nama_depan || ' ' || tk.nama_belakang as nam_lengkap,
    tj.nama_jabatan,
    tk.pendidikan_terakhir,
    2000000 as tunjangan_pendidikan,
    tj.gaji_pokok + tj.tunjangan_jabatan + 2000000 as total_gaji
FROM
    tb_pekerjaan tp
        JOIN public.tb_karyawan tk on tp.nip = tk.nip
        JOIN tb_jabatan tj on tp.kode_jabatan = tj.kd_jabatan
WHERE tk.pendidikan_terakhir LIKE 'S1 %';

-- NO 6
SELECT
    tk.nip,
    tk.nama_depan || ' ' || tk.nama_belakang as nam_lengkap,
    tj.nama_jabatan,
    td.nama_divisi,
    CASE tj.kd_jabatan
        WHEN 'MGR' THEN total_gaji(jabatan := tj, pekerjaan := tp) * 7
        WHEN 'ST' THEN total_gaji(tj, tp) * 5
        ELSE total_gaji(tj, tp) * 2
    END bonus
FROM
    tb_pekerjaan tp
        JOIN public.tb_karyawan tk on tp.nip = tk.nip
        JOIN tb_jabatan tj on tp.kode_jabatan = tj.kd_jabatan
        JOIN tb_divisi td on tp.kode_divisi = td.kd_divisi;

-- NO 7
ALTER TABLE tb_karyawan
    ADD CONSTRAINT uq_karyawan_nip UNIQUE (nip);

SELECT * FROM pg_indexes where tablename='tb_karyawan';


-- NO 8
CREATE INDEX idx_nip
ON tb_karyawan(nip);


-- NO 9
SELECT
    tk.nama_depan || ' ' ||
    CASE
        WHEN tk.nama_belakang LIKE 'W%' THEN upper(tk.nama_belakang)
        ELSE tk.nama_belakang
    END
FROM
    tb_karyawan tk;

-- NO 10
SELECT
    tk.nama_depan || ' ' || tk.nama_belakang,
    tk.tgl_masuk,
    tj.nama_jabatan,
    td.nama_divisi,
    total_gaji(tj, tp) as total_gaji,
    total_gaji(tj, tp) * 0.1 as bonus,
    2022 - extract(YEAR FROM tk.tgl_masuk) as lama_bekerja
FROM
    tb_pekerjaan tp
        INNER JOIN public.tb_karyawan tk on tp.nip = tk.nip
        INNER JOIN tb_jabatan tj on tp.kode_jabatan = tj.kd_jabatan
        INNER JOIN tb_divisi td on tp.kode_divisi = td.kd_divisi
WHERE
    2022 - extract(YEAR FROM  tk.tgl_masuk) >= 8;

SELECT extract(YEAR FROM age('2022-12-01', tk.tgl_masuk)) from tb_karyawan tk;

CREATE FUNCTION ufn_total_gaji(gapok numeric, tunjangan_jabatan numeric, tunjangan_kinerja numeric)
RETURNS numeric as $total$
    DECLARE total numeric;
    BEGIN
        SELECT (gapok + tunjangan_jabatan, tunjangan_kinerja) INTO total;
        RETURN total;
    END
$total$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION total_gaji(jabatan tb_jabatan, pekerjaan tb_pekerjaan) RETURNS numeric
return jabatan.gaji_pokok + jabatan.tunjangan_jabatan + pekerjaan.tunjangan_kinerja;

CREATE FUNCTION add(a numeric, b numeric) returns numeric
RETURN a+b;

select add(1000000, 1000000)

