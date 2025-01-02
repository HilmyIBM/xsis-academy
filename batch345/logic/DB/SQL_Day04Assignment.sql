create table mahasiswa (
	nim SERIAL primary key,
	nama VARCHAR(50) not null,
	jenis_kelamin VARCHAR(50) not null,
	alamat VARCHAR(100) not null 
);

select pg_get_serial_sequence('mahasiswa', 'nim');

select setval('mahasiswa_nim_seq', 100, true);

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

-- 1
SELECT 
    m.nama AS nama_mahasiswa,
    mk.nama_mk AS nama_mata_kuliah
FROM 
    ambil_mk am
INNER JOIN 
    mahasiswa m ON am.nim = m.nim
INNER JOIN 
    mata_kuliah mk ON am.kode_mk = mk.kode_mk;

-- 2
SELECT
		m.nim,
    m.nama AS nama_mahasiswa,
    m.jenis_kelamin,
    m.alamat
FROM 
    mahasiswa m
LEFT JOIN 
    ambil_mk am ON m.nim = am.nim
WHERE 
    am.nim IS NULL;

-- 3
SELECT 
    COUNT(m.nim) AS jml,
    m.jenis_kelamin
FROM 
    mahasiswa m
LEFT JOIN 
    ambil_mk am ON m.nim = am.nim
WHERE 
    am.nim IS NULL
GROUP BY 
    m.jenis_kelamin
ORDER BY
		COUNT(m.nim) DESC;

-- 4
SELECT
    M.nim, 
    M.nama AS nama_mahasiswa, 
    A.kode_mk, 
    MK.nama_mk
FROM 
    mahasiswa M
INNER JOIN 
    ambil_mk A ON M.nim = A.nim
INNER JOIN 
    mata_kuliah MK ON A.kode_mk = MK.kode_mk;

-- 5
SELECT 
    M.nim, 
    M.nama AS nama_mahasiswa, 
    SUM(MK.sks) AS total_sks
FROM 
    mahasiswa M
INNER JOIN 
    ambil_mk A ON M.nim = A.nim
INNER JOIN 
    mata_kuliah MK ON A.kode_mk = MK.kode_mk
GROUP BY 
    M.nim, M.nama
HAVING 
    SUM(MK.sks) > 4 AND SUM(MK.sks) < 10;
