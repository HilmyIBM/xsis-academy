create table mahasiswa (
	nim SERIAL primary key,
	nama VARCHAR(50) not null,
	jenis_kelamin VARCHAR(50) not null,
	alamat VARCHAR(100) not null 
);

select pg_get_serial_sequence('mahasiswa', 'nim');

select setval('mahasiswa_nim_seq', 100, true);

select currval('mahasiswa_nim_seq');

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

alter table ambil_mk
add constraint fk_nim foreign key (nim) references mahasiswa(nim),
add constraint fk_kode_mk foreign key (kode_mk) references mata_kuliah(kode_mk);

-- 1. Tampilkan nama mahasiswa dan matakuliah yang diambil
SELECT 
    m.nama AS nama_mahasiswa,
    mk.nama_mk AS nama_mata_kuliah
FROM 
    ambil_mk am
INNER JOIN 
    mahasiswa m ON am.nim = m.nim
INNER JOIN 
    mata_kuliah mk ON am.kode_mk = mk.kode_mk;

-- 2. Tampilkan data mahasiswa yang tidak mengambil matakuliah	
select m.* from mahasiswa m left join ambil_mk mk on mk.nim = m.nim where mk.nim is null;
select m.* from mahasiswa m where m.nim not in (select nim from ambil_mk);

-- 3. Kelompokan data mahasiswa yang tidak mengambil matakuliah berdasarkan jenis kelaminnya, kemudian hitung banyaknya.													
select count(m.jenis_kelamin) as jumlah, m.jenis_kelamin from mahasiswa m 
left join ambil_mk mk on mk.nim = m.nim 
where mk.nim is null group by m.jenis_kelamin order by count(m.jenis_kelamin) desc;

select count(m.jenis_kelamin) as jumlah, m.jenis_kelamin from mahasiswa m
where m.nim not in (select nim from ambil_mk) group by m.jenis_kelamin
order by count(m.jenis_kelamin) desc;

-- 4. Dapatkan nim dan nama mahasiswa yang mengambil matakuliah beserta kode_mk dan nama_mk yang diambilnya
select m.nim, m.nama, mk.kode_mk, mk.nama_mk from mahasiswa m
inner join ambil_mk amk on amk.nim = m.nim
inner join mata_kuliah mk on mk.kode_mk = amk.kode_mk;

-- 5. Dapatkan nim, nama, dan total sks yang diambil oleh mahasiswa, Dimana total sksnya lebih dari 4 dan kurang dari 10.
select m.nim, m.nama, sum(mk.sks) as jumlah_sks from mahasiswa m
inner join ambil_mk amk on amk.nim = m.nim
inner join mata_kuliah mk on mk.kode_mk = amk.kode_mk 
group by m.nim, m.nama
having sum(mk.sks) > 4 and sum(mk.sks) < 10;

-- 6. Dapatkan data matakuliah yang tidak diambil oleh mahasiswa terdaftar 
-- (mahasiswa yang terdaftar adalah mahasiswa yang tercatat di tabel mahasiswa).
SELECT mk.nama_mk
FROM mata_kuliah mk
LEFT JOIN ambil_mk amk ON amk.kode_mk = mk.kode_mk
WHERE amk.kode_mk IS NULL;