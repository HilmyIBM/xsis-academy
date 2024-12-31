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


