CREATE DATABASE "DB_Univ_XA";


--setup tb Mahasiswa
CREATE TABLE "Mahasiswa" (
    "Nim" INTEGER PRIMARY KEY NOT NULL,
    nama VARCHAR(50) NOT NULL,
    jenis_kelamin VARCHAR(50) NOT NULL,
    alamat VARCHAR(50) NOT NULL
);


INSERT INTO "Mahasiswa" ("Nim", nama, jenis_kelamin, alamat)
VALUES
(101, 'Arif', 'L', 'Jl. Kenagan'),
(102, 'Budi', 'L', 'Jl. Jombang'),
(103, 'Wati', 'P', 'Jl. Surabaya'),
(104, 'Ika', 'P', 'Jl. Jombang'),
(105, 'Tono', 'L', 'Jl. Jakarta'),
(106, 'Iwan', 'L', 'Jl. Bandung'),
(107, 'Sari', 'P', 'Jl. Malang');

SELECT * FROM "Mahasiswa";

SELECT * FROM "Mahasiswa";


--setup tb Matakuliah

CREATE TABLE "Matakuliah"(
    kode_mk VARCHAR(50) PRIMARY KEY NOT NULL,
    nama_mk VARCHAR(50) NOT NULL,
    sks INTEGER,
    semester INTEGER
);

INSERT INTO "Matakuliah" (kode_mk, nama_mk, sks, semester)
VALUES
('PTI447', 'Praktikum Basis Data', 1, 3),
('TIK342', 'Praktikum Basis Data', 1, 3),
('PTI333', 'Basis Data Terdistribusi', 3, 5),
('TIK123', 'Jaringan Komputer', 2, 5),
('TIK333', 'Sistem Operasi', 3, 5),
('PTI123', 'Grafika Multimedia', 3, 5),
('PTI777', 'Sistem Informasi', 2, 3);

SELECT * FROM "Matakuliah";


--setup tb ambil_mk
CREATE TABLE ambil_mk(
    nim INTEGER NOT NULL,
    kode_mk VARCHAR(50) NOT NULL
);

INSERT INTO ambil_mk(nim, kode_mk)
VALUES
(101, 'PTI447'),
(103, 'TIK333'),
(104, 'PTI333'),
(104, 'PTI777'),
(111, 'PTI123'),
(123, 'PTI999');

SELECT * FROM ambil_mk;