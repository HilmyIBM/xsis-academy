CREATE DATABASE univ_xa;

CREATE TABLE mahasiswa(
    id Serial PRIMARY KEY NOT NULL,
    nim INTEGER NOT NULL,
    nama VARCHAR(100) NOT NULL,
    jenis_kelamin VARCHAR(10) NOT NULL,
    alamat VARCHAR(100) NOT NULL
);

CREATE TABLE mata_kuliah(
    id Serial PRIMARY KEY NOT NULL,
    kode_mk VARCHAR(100) NOT NULL,
    nama_mk VARCHAR(100) NOT NULL,
    sks INTEGER NOT NULL,
    semester INTEGER NOT NULL
);

CREATE table ambil_mk(
    id Serial PRIMARY KEY NOT NULL,
    nim INTEGER NOT NULL,
    kode_mk VARCHAR(100) NOT NULL
);

INSERT INTO mahasiswa(nim,nama,jenis_kelamin,alamat)
VALUES (101,'Arif','L','Jl. kenangan'),
       (102,'Budi','L','Jl. Jombang'),
       (103,'Wati','P','Jl. Surabaya'),
       (104,'ika','P','Jl. Jombang'),
       (105,'Tono','L','Jl. Jakarta'),
       (106,'Iwan','L','Jl. Bandung'),
       (107,'Sari','P','Jl. Malang');

INSERT INTO mata_kuliah(kode_mk,nama_mk,sks,semester)
VALUES('PTI447','Praktikum Basis Data',1,3),
      ('TIK342','Praktikum Basis Data',1,3),
      ('PTI333','Basis Data Terdistribusi',3,5),
      ('TIK123','Jaringan Komputer',2,5),
      ('TIK333','Sistem Operasi',3,5),
      ('PTI123','Grafika Multimedia',3,5),
      ('PTI777','Sistem Informasi',2,3);

INSERT INTO ambil_mk(nim,kode_mk)
VALUES(101,'PTI447'),
      (103,'TIK333'),
      (104,'PTI333'),
      (104,'PTI777'),
      (111,'PTI123'),
      (123,'PTI999');


SELECT * from mahasiswa;
SELECT * from mata_kuliah;
SELECT * from ambil_mk;

--1  Tampilkan nama mahasiswa dan matakuliah yang diambil
SELECT M.nama, MK.nama_mk 
FROM mahasiswa M
INNER JOIN ambil_mk A ON M.nim = A.nim
INNER JOIN mata_kuliah MK ON A.kode_mk = MK.kode_mk;


--2. Tampilkan data mahasiswa yang tidak mengambil matakuliah
SELECT M.*
FROM mahasiswa M
LEFT JOIN ambil_mk A ON M.nim = A.nim
WHERE A.nim is null;


--3. Kelompokan data mahasiswa yang tidak mengambil matakuliah berdasarkan jenis kelaminnya, kemudian hitung banyaknya.


--4. Dapatkan nim dan nama mahasiswa yang mengambil matakuliah beserta kode_mk dan nama_mk yang diambilnya
SELECT M.nim, M.nama,MK.kode_mk, MK.nama_mk 
FROM mahasiswa M
INNER JOIN ambil_mk A ON M.nim = A.nim
INNER JOIN mata_kuliah MK ON A.kode_mk = MK.kode_mk;  


--5. Dapatkan nim, nama, dan total sks yang diambil oleh mahasiswa, Dimana total sksnya lebih dari 4 dan kurang dari 10.
select Mahasiswa.*  
from(
	SELECT 
		M.nim, 
		M.nama,
		SUM(MK.sks) sks
	FROM mahasiswa M
	INNER JOIN ambil_mk A ON M.nim = A.nim
	INNER JOIN mata_kuliah MK ON A.kode_mk = MK.kode_mk
	GROUP by M.nim, M.nama
) as Mahasiswa
where Mahasiswa.sks > 4 and Mahasiswa.sks < 10;

--6. Dapatkan data matakuliah yang tidak diambil oleh mahasiswa terdaftar (mahasiswa yang terdaftar adalah mahasiswa yang tercatat di tabel mahasiswa).

