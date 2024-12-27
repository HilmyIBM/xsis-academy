create table tb_karyawan (
	id serial primary key,
	nip varchar(50) not null,
	nama_depan varchar(50) not null,
	nama_belakang varchar(50) not null,
	jenis_kelamin varchar(50) not null,
	agama varchar(50) not null,
	tempat_lahir varchar(50) not null,
	tgl_lahir date,
	alamat varchar(100) not null,
	pendidikan_terakhir varchar(50) not null,
	tgl_masuk date,
	constraint nip_unique unique (nip) 
);

create table tb_divisi(
	id serial primary key,
	kd_divisi varchar(50) not null,
	nama_divisi varchar(50) not null,
	constraint kd_divisi_unique unique(kd_divisi)
);

create table tb_jabatan (
	id serial primary key,
	kd_jabatan varchar(50) not null,
	nama_jabatan varchar(50) not null,
	gaji_pokok numeric,
	tunjangan numeric,
	constraint kd_jabatan_unique unique (kd_jabatan)
);

create table tb_pekerjaan(
	id serial primary key,
	nip varchar(50) not null,
	kode_jabatan varchar(50) not null,
	kode_divisi varchar(50) not null,
	tunjangan_kerja numeric,
	kota_penempatan varchar(50),
	constraint fk_kode_jabatan FOREIGN key (kode_jabatan) references tb_jabatan(kd_jabatan),
	constraint fk_kode_divisi FOREIGN key (kode_divisi) references tb_divisi(kd_divisi),
	constraint fk_nip FOREIGN key (nip) references tb_karyawan (nip)
);

INSERT INTO tb_karyawan (nip, nama_depan, nama_belakang, jenis_kelamin, agama, tempat_lahir, 
    tgl_lahir, alamat, pendidikan_terakhir, tgl_masuk)
VALUES
('001', 'Hamidi', 'Samsudin', 'Pria', 'Islam', 'Sukabumi', '1977-04-21', 'Jl. Sudirman No.12', 'S1 Teknik Mesin', '2015-12-07'),
('002', 'Paul', 'Christian', 'Pria', 'Kristen', 'Ambon', '1980-05-27', 'Jl. Veteran No.4', 'S1 Pendidikan Geografi', '2014-01-12'),
('003', 'Ghandi', 'Wamida', 'Wanita', 'Islam', 'Palu', '1992-01-12', 'Jl. Rambutan No.22', 'SMA Negeri 02 Palu', '2014-12-01');

INSERT INTO tb_divisi (kd_divisi, nama_divisi)
VALUES
('GD', 'Gudang'),
('HRD', 'HRD'),
('KU', 'Keuangan'),
('UM', 'Umum');

alter table tb_jabatan
	rename column tunjangan to tunjangan_jabatan; 

INSERT INTO tb_jabatan (kd_jabatan, nama_jabatan, gaji_pokok, tunjangan_jabatan)
VALUES
('MGR', 'Manager', 5500000, 1500000),
('OB', 'Office Boy', 1900000, 200000),
('ST', 'Staff', 3000000, 750000),
('WMGR', 'Wakil Manager', 4000000, 1200000);

alter table tb_pekerjaan
	rename column tunjangan_kerja to tunjangan_kinerja;

INSERT INTO tb_pekerjaan (nip, kode_jabatan, kode_divisi, tunjangan_kinerja, kota_penempatan)
VALUES
('001', 'ST', 'KU', 750000, 'Cianjur'),
('002', 'OB', 'UM', 350000, 'Sukabumi'),
('003', 'MGR', 'HRD', 1500000, 'Sukabumi');