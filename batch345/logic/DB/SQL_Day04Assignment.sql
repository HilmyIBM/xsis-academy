create table mahasiswa (
	nim SERIAL primary key,
	nama VARCHAR(50) not null,
	jenis_kelamin VARCHAR(50) not null,
	alamat VARCHAR(100) not null 
);

select pg_get_serial_sequence('mahasiswa', 'nim');

select setval('mahasiswa_nim_seq', 100, false);

--select currval('mahasiswa_nim_seq');

create table mata_kuliah (
	kode_mk VARCHAR(10) PRIMARY KEY,
	nama_mk VARCHAR(50) not null,
	sks INTEGER not null,
	semester INTEGER not null
);

create table ambil_mk (
	id SERIAL primary key,
	nim INTEGER not null,
	kode_mk VARCHAR(10) not null
);

insert into mahasiswa (nama, jenis_kelamin, alamat)
	VALUES
		('Arif','L','Jl. Kenangan'),
		('Budi','L','Jl. Jombang'),
		('Wati','P','Jl. Surabaya'),
		('Ika','P','Jl. Jombang'),
		('Tono','L','Jl. Jakarta'),
		('Iwan','L','Jl. Bandung'),
		('Sari','P','Jl. Malang');

insert into mata_kuliah(kode_mk, nama_mk, sks, semester)
	VALUES
		('PTI447','Praktikum Basis Data',1,3),
		('TIK342','Praktikum Basis Data',1,3),
		('PTI333','Basis Data Terdistribusi',3,5),
		('TIK123','Jaringan Komputer',2,5),
		('TIK333','Sistem Operasi',3,5),
		('PTI123','Grafika Multimedia',3,5),
		('PTI777','Sistem Informasi',2,3);

insert into ambil_mk(nim, kode_mk)
	VALUES
		(101,'PTI447'),
		(103,'TIK333'),
		(104,'PTI333'),
		(104,'PTI777'),
		(111,'PTI123'),
		(123,'PTI999');

select * from mahasiswa;
select * from mata_kuliah;
select * from ambil_mk;

drop table if exists mahasiswa, mata_kuliah, ambil_mk;