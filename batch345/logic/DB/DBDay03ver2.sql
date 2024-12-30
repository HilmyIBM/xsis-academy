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
    
--No. 1
SELECT nama,nama_mk from mahasiswa join ambil_mk on mahasiswa.nim=ambil_mk.nim
JOIN mata_kuliah on ambil_mk.kode_mk=mata_kuliah.kode_mk;

--No.2
SELECT mahasiswa.nim,nama,jenis_kelamin,alamat from mahasiswa left join ambil_mk on mahasiswa.nim=ambil_mk.nim
left JOIN mata_kuliah on ambil_mk.kode_mk=mata_kuliah.kode_mk WHERE ambil_mk.nim is NULL;

--No.3
SELECT COUNT(jenis_kelamin) as jumlah,jenis_kelamin from mahasiswa left join ambil_mk on mahasiswa.nim=ambil_mk.nim
left JOIN mata_kuliah on ambil_mk.kode_mk=mata_kuliah.kode_mk WHERE ambil_mk.nim is NULL GROUP BY jenis_kelamin;

--No.4
SELECT mahasiswa.nim,nama,mata_kuliah.kode_mk,nama_mk from mahasiswa join ambil_mk on mahasiswa.nim=ambil_mk.nim
JOIN mata_kuliah on ambil_mk.kode_mk=mata_kuliah.kode_mk;

--No.5
SELECT mahasiswa.nim,nama,SUM(sks) from mahasiswa join ambil_mk on mahasiswa.nim=ambil_mk.nim
JOIN mata_kuliah on ambil_mk.kode_mk=mata_kuliah.kode_mk GROUP BY nama,mahasiswa.nim 
HAVING SUM(sks) > 4 and  SUM(sks) <10;

--No.6
SELECT nama_mk from mata_kuliah left join ambil_mk on mata_kuliah.kode_mk=ambil_mk.kode_mk
left JOIN mahasiswa on ambil_mk.nim=mahasiswa.nim WHERE mahasiswa.nim is NULL;



