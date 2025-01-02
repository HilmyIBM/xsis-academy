create table tb_karyawan (
	id SERIAL primary key,
	nip VARCHAR(50) not null,
	nama_depan VARCHAR(50) not null,
	nama_belakang VARCHAR(50) not null,
	jenis_kelamin VARCHAR(50) not null,
	agama VARCHAR(50) not null,
	tempat_lahir VARCHAR(50) not null,
	tgl_lahir DATE,
	alamat VARCHAR(100) not null,
	pendidikan_terakhir VARCHAR(50) not null,
	tgl_masuk DATE
);

insert into tb_karyawan (nip, nama_depan, nama_belakang, jenis_kelamin, agama, tempat_lahir, tgl_lahir, alamat, pendidikan_terakhir, tgl_masuk)
	values
		('001', 'Hamidi', 'Samsudin', 'Pria', 'Islam', 'Sukabumi', '1977-04-21', 'Jl. Sudirman No. 12', 'S1 Teknik Mesin', '2015-12-07'),
		('002', 'Ghandi', 'Wamida', 'Wanita', 'Islam', 'Palu', '1992-01-12', 'Jl. Rambutan No. 22', 'SMA Negri 02 Palu', '2014-12-01'),
		('003', 'Paul', 'Christian', 'Pria', 'Kristen', 'Ambon', '1980-05-27', 'Jl. Veteran No. 4', 'S1 Pendidikan Geografi', '2014-01-12');

create table tb_divisi (
	id SERIAL primary key,
	kd_divisi VARCHAR(50) not null unique,
	nama_divisi VARCHAR(50) not null
);

insert into tb_divisi (kd_divisi, nama_divisi)
	values
		('GD', 'Gudang'),
		('HRD', 'HRD'),
		('KU', 'Keuangan'),
		('UM', 'Umum');


create table tb_jabatan (
	id SERIAL primary key,
	kd_jabatan VARCHAR(50) not null unique,
	nama_jabatan VARCHAR(50) not null,
	gaji_pokok INTEGER not null,
	tunjangan_jabatan INTEGER not null
);

insert into tb_jabatan (kd_jabatan, nama_jabatan, gaji_pokok, tunjangan_jabatan)
	values
		('MGR', 'Manager', 5500000, 1500000),
		('OB', 'Office Boy', 1900000, 200000),
		('ST', 'Staff', 3000000, 750000),
		('WMGR', 'Wakil Manager', 4000000, 1200000);

create table tb_pekerjaan (
	id SERIAL primary key,
	nip VARCHAR(50) not null,
	kd_jabatan VARCHAR(50) references tb_jabatan(kd_jabatan),
	kd_divisi VARCHAR(50) references tb_divisi(kd_divisi),
	tunjangan_kinerja INTEGER not null,
	kota_penempatan VARCHAR(50) not null
);

insert into tb_pekerjaan (nip, kd_jabatan, kd_divisi, tunjangan_kinerja, kota_penempatan)
	values
		('001', 'ST', 'KU', '750000', 'Cianjur'),
		('002', 'OB', 'UM', '350000', 'Sukabumi'),
		('003', 'MGR', 'HRD', '1500000', 'Sukabumi');

select * from tb_karyawan;
select * from tb_divisi;
select * from tb_jabatan;
select * from tb_pekerjaan;

drop table if exists tb_karyawan, tb_divisi, tb_jabatan, tb_pekerjaan;


-- 1
SELECT 
    CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap,
    J.nama_jabatan,
    J.gaji_pokok + J.tunjangan_jabatan AS gaji_tunjangan
FROM 
    tb_karyawan K
JOIN 
    tb_pekerjaan P ON K.nip = P.nip
JOIN 
    tb_jabatan J ON P.kd_jabatan = J.kd_jabatan
WHERE 
    J.gaji_pokok + P.tunjangan_kinerja < 5000000;

-- 2
SELECT 
    CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap,
    J.nama_jabatan,
    D.nama_divisi,
    J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja AS total_gaji,
    (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 0.05 AS pajak,
    (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) - ((J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 0.05) AS gaji_bersih
FROM 
    tb_karyawan K
INNER JOIN 
    tb_pekerjaan P ON K.nip = P.nip
INNER JOIN 
    tb_jabatan J ON P.kd_jabatan = J.kd_jabatan
INNER JOIN 
    tb_divisi D ON P.kd_divisi = D.kd_divisi
WHERE 
    K.jenis_kelamin = 'Pria'
    AND P.kota_penempatan != 'Sukabumi';

-- 3
SELECT 
    K.nip,
    CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap,
    J.nama_jabatan,
    D.nama_divisi,
    0.25 * ((J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 7) AS bonus
FROM 
    tb_karyawan K
INNER JOIN 
    tb_pekerjaan P ON K.nip = P.nip
INNER JOIN 
    tb_jabatan J ON P.kd_jabatan = J.kd_jabatan
INNER JOIN 
    tb_divisi D ON P.kd_divisi = D.kd_divisi;


-- 4
SELECT 
    CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap,
		J.nama_jabatan,
		D.nama_divisi,
    (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) AS total_gaji,
    0.05 * (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) AS infak
FROM 
    tb_karyawan K
INNER JOIN 
    tb_pekerjaan P ON K.nip = P.nip
INNER JOIN 
    tb_jabatan J ON P.kd_jabatan = J.kd_jabatan
INNER JOIN
		tb_divisi D ON P.kd_divisi = D.kd_divisi
WHERE 
    J.kd_jabatan = 'MGR';

-- 5
SELECT 
    CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap,
    J.nama_jabatan,
    K.pendidikan_terakhir,
    2000000 AS tunjangan_pendidikan,
    J.gaji_pokok + J.tunjangan_jabatan + 2000000 AS total_gaji
FROM tb_karyawan K
INNER JOIN tb_pekerjaan P ON K.nip = P.nip
INNER JOIN tb_jabatan J ON P.kd_jabatan = J.kd_jabatan
WHERE LOWER(K.pendidikan_terakhir) LIKE 's1%';

-- 6
SELECT 
    P.nip,
    CONCAT(K.nama_depan, ' ', K.nama_belakang) AS nama_lengkap,
    J.nama_jabatan,
    D.nama_divisi,
    CASE 
        WHEN J.nama_jabatan = 'Manager' THEN 0.25 * (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 7
        WHEN J.nama_jabatan = 'Staff' THEN 0.25 * (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 5
        ELSE 0.25 * (J.gaji_pokok + J.tunjangan_jabatan + P.tunjangan_kinerja) * 2
    END AS bonus
FROM tb_karyawan K
INNER JOIN tb_pekerjaan P ON K.nip = P.nip
INNER JOIN tb_jabatan J ON P.kd_jabatan = J.kd_jabatan
INNER JOIN tb_divisi D ON P.kd_divisi = D.kd_divisi;

-- 7
ALTER TABLE tb_karyawan
ADD CONSTRAINT unique_nip UNIQUE (nip);

-- 8
CREATE INDEX idx_nip ON tb_karyawan(nip);

-- 9
SELECT 
    CONCAT(nama_depan, ' ', 
           CASE 
               WHEN nama_belakang LIKE 'W%' THEN UPPER(nama_belakang)
               ELSE nama_belakang
           END
    ) AS nama_lengkap
FROM tb_karyawan;	


