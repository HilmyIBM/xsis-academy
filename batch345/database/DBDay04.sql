CREATE TABLE Mahasiswa (
    Nim INTEGER PRIMARY KEY NOT NULL,
    nama VARCHAR(50) NOT NULL,
    jenis_kelamin VARCHAR(1) NOT NULL,
    alamat VARCHAR(50) NOT NULL
);
CREATE TABLE Matakuliah (
    kode_mk VARCHAR(50) PRIMARY KEY NOT NULL,
    nama_mk VARCHAR(50) NOT NULL,
    sks INTEGER NOT NULL,
    semester INTEGER NOT NULL
);
CREATE TABLE ambil_mk (
    Nim INTEGER NOT NULL,
    kode_mk VARCHAR(50) NOT NULL
);

select * from  Mahasiswa;

INSERT INTO Mahasiswa (Nim, nama, jenis_kelamin, alamat)
VALUES 
	(101, 'Arif', 'L', 'Jl. Kenangan'),
	(102, 'Budi', 'L', 'Jl. Jombang'),
	(103, 'Wati', 'P', 'Jl. Surabaya'),
	(104, 'Ika', 'P', 'Jl. Jombang'),
	(105, 'Tono', 'L', 'Jl. Jakarta'),
	(106, 'Iwan', 'L', 'Jl. Bandung'),
	(107, 'Sari', 'P', 'Jl. Malang');

INSERT INTO Matakuliah (kode_mk, nama_mk, sks, semester)
VALUES 
	('PTI447', 'Praktikum Basis Data', 1, 3),
	('TIK342', 'Praktikum Basis Data', 1, 3),
	('PTI333', 'Basis Data Terdistribusi', 3, 5),
	('TIK123', 'Jaringan Komputer', 2, 5),
	('TIK333', 'Sistem Operasi', 3, 5),
	('PTI123', 'Grafika Multimedia', 3, 5),
	('PTI777', 'Sistem Informasi', 2, 3);

INSERT INTO ambil_mk (Nim, kode_mk)
VALUES 
	(101, 'PTI447'),
	(103, 'TIK333'),
	(104, 'PTI333'),
	(104, 'PTI777'),
	(111, 'PTI123'),
	(123, 'PTI999');

-- No 1
SELECT m.nama, mk.nama_mk
FROM mahasiswa m
JOIN ambil_mk am ON m.nim = am.nim 
JOIN matakuliah mk ON am.kode_mk = mk.kode_mk;

-- No 2
SELECT m.nim, m.nama, m.jenis_kelamin, m.alamat
FROM mahasiswa m
LEFT JOIN ambil_mk am ON m.nim = am.nim
WHERE am.nim IS NULL;

-- No 3
SELECT COUNT(m.jenis_kelamin) AS jml, m.jenis_kelamin
FROM mahasiswa m
LEFT JOIN ambil_mk am ON m.nim = am.nim
WHERE am.nim IS NULL
GROUP BY m.jenis_kelamin;

-- No 4
SELECT m.nim, m.nama, mk.kode_mk, mk.nama_mk
FROM mahasiswa m
INNER JOIN ambil_mk am ON m.nim = am.nim
INNER JOIN matakuliah mk on am.kode_mk = mk.kode_mk;

-- No 5
SELECT m.nim, m.nama, SUM(mk.sks) AS jml_sks
FROM ambil_mk a
INNER JOIN Mahasiswa m ON a.nim = m.nim
INNER JOIN Matakuliah mk ON a.kode_mk = mk.kode_mk
GROUP BY m.nim, m.nama
HAVING SUM(mk.sks) > 4 AND SUM(mk.sks) < 10;

-- No 6
SELECT mk.nama_mk
FROM matakuliah mk
LEFT JOIN ambil_mk am ON mk.kode_mk = am.kode_mk
LEFT JOIN mahasiswa m ON am.nim = m.nim
WHERE m.nim IS NULL
ORDER BY mk.nama_mk DESC;

CREATE DATABASE DB_Sales;

