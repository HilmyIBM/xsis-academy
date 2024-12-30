CREATE TABLE mahasiswa(
    id SERIAL PRIMARY KEY NOT NULL,
    nim VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    jenis_kelamin VARCHAR(10) NOT NULL,
    alamat VARCHAR(100) NOT NULL
);

CREATE TABLE mata_kuliah(
    id SERIAL PRIMARY KEY NOT NULL,
    kode_mk VARCHAR(50) NOT NULL,
    nama_mk VARCHAR(50) NOT NULL,
    sks NUMERIC NOT NULL,
    semester NUMERIC NOT NULL
);

CREATE TABLE ambil_mk(
    nim VARCHAR(50) NOT NULL,
    kode_mk VARCHAR(50) NOT NULL
);

-- DROP TABLE mahasiswa;
-- DROP TABLE mata_kuliah;
-- DROP TABLE ambil_mk;

INSERT INTO mahasiswa (nim, nama, jenis_kelamin, alamat)
VALUES
    ('101', 'Arif', 'L', 'Jl.Kenangan'),
    ('102', 'Budi', 'L', 'Jl.Jombang'),
    ('103', 'Wati', 'P', 'Jl.Surabaya'),
    ('104', 'Ika', 'P', 'Jl.Jombang'),
    ('105', 'Tono', 'L', 'Jl.Jakarta'),
    ('106', 'Iwan', 'L', 'Jl.Bandung'),
    ('107', 'Sari', 'P', 'Jl.Malang')
;

INSERT INTO mata_kuliah (kode_mk, nama_mk, sks, semester)
VALUES
    ('PTI447', 'Praktikum Basis Data', 1, 3),
    ('TIK342', 'Praktikum Basis Data', 1, 3),
    ('PTI333', 'Basis Data Terdistribusi', 3, 5),
    ('TIK123', 'Jaringan Komputer', 2, 5),
    ('TIK333', 'Sistem Operasi', 3, 5),
    ('PTI123', 'Grafika Multimedia', 3, 5),
    ('PTI777', 'Sistem Informasi', 2, 3)
;

INSERT INTO ambil_mk (nim, kode_mk)
VALUES
    ('101', 'PTI447'),
    ('103', 'TIK333'),
    ('104', 'PTI333'),
    ('104', 'PTI777'),
    ('111', 'PTI123'),
    ('123', 'PTI999')
;

-- SELECT * FROM mahasiswa;
-- SELECT * FROM mata_kuliah;
-- SELECT * FROM ambil_mk;

-- 1
SELECT 
    b.nama,
    c.nama_mk
FROM ambil_mk a
    JOIN mahasiswa b ON a.nim = b.nim
    JOIN mata_kuliah c ON a.kode_mk = c.kode_mk;

-- 2 
SELECT b.* FROM mahasiswa b
WHERE b.nim NOT IN (
    SELECT a.nim FROM ambil_mk a
);

-- 3
SELECT 
    COUNT(b.jenis_kelamin) AS jml,
    b.jenis_kelamin
FROM mahasiswa b
WHERE b.nim NOT IN (
    SELECT a.nim FROM ambil_mk a
)
GROUP BY b.jenis_kelamin;

-- 4
SELECT
    a.nim,
    b.nama,
    a.kode_mk,
    c.nama_mk
FROM ambil_mk a
    JOIN mahasiswa b ON a.nim = b.nim
    JOIN mata_kuliah c ON a.kode_mk = c.kode_mk;

-- 5
SELECT
    a.nim,
    b.nama,
    SUM(c.sks) AS jml_sks
FROM ambil_mk a
    JOIN mahasiswa b ON a.nim = b.nim
    JOIN mata_kuliah c ON a.kode_mk = c.kode_mk
GROUP BY a.nim, b.nama
HAVING SUM(c.sks) > 4 AND SUM(c.sks) < 10;

-- 6
SELECT c.nama_mk
FROM mata_kuliah c
LEFT JOIN ambil_mk a ON c.kode_mk = a.kode_mk
LEFT JOIN mahasiswa b ON a.nim = b.nim
WHERE b.nama IS NULL;

