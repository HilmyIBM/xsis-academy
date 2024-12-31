CREATE DATABASE db_univ_xa

CREATE TABLE tb_mahasiswa(
    pk_nim VARCHAR(50) PRIMARY KEY NOT NULL,
    nama VARCHAR(100) NOT NULL,
    j_kelamin VARCHAR(2) NOT NULL,
    alamat VARCHAR(100) NOT NULL
);

CREATE TABLE tb_matakuliah(
    pk_kode_mk VARCHAR(50) PRIMARY KEY NOT NULL,
    nama_mk VARCHAR(100) NOT NULL,
    sks NUMERIC NOT NULL,
    semester NUMERIC NOT NULL
);

CREATE TABLE tb_ambil_mk(
    nim VARCHAR(50) NOT NULL,
    kode_mk VARCHAR(50) NOT NULL
    -- PRIMARY KEY(nim, kode_mk),
    -- FOREIGN KEY(nim) REFERENCES tb_mahasiswa(pk_nim) ON DELETE CASCADE,
    -- FOREIGN KEY(kode_mk) REFERENCES tb_matakuliah(pk_kode_mk) ON DELETE CASCADE
);
DROP TABLE tb_mahasiswa;
DROP TABLE tb_matakuliah;
DROP TABLE tb_ambil_mk;
SELECT * FROM tb_mahasiswa;
SELECT * FROM tb_matakuliah;
SELECT * FROM tb_ambil_mk;
INSERT INTO tb_mahasiswa(pk_nim, nama, j_kelamin, alamat)
VALUES
('101', 'Arif', 'L' , 'Jl. Kenangan'),
('102', 'Budi', 'L' , 'Jl. Jombang'),
('103', 'Wati', 'p' , 'Jl. Surabaya'),
('104', 'Ika', 'P' , 'Jl. Jombang'),
('105', 'Tono', 'L' , 'Jl. jakarta'),
('106', 'Iwan', 'L' , 'Jl. Bandung'),
('107', 'Sari', 'P' , 'Jl. Malang');

INSERT INTO tb_matakuliah(pk_kode_mk, nama_mk, sks, semester) 
VALUES
('PTI447', 'Pratikum Basis Data', 1, 3),
('TIK342', 'Pratikum Basis Data', 1, 3),
('PTI333', 'Basis Data Terdistribusi', 3, 5),
('TIK123', 'Jaringan Komputer', 2, 5),
('TIK333', 'Sistem Operasi', 3, 5),
('PTI123', 'Grafika Multimedia', 3, 5),
('PTI777', 'Sistem Informasi', 2, 3);

INSERT INTO tb_ambil_mk(nim, kode_mk)
VALUES
('101','PTI447'),
('103','TIK333'),
('104','PTI333'),
('104','PTI777'),
('111','PTI123'),
('123','PTI999');

-- No. 1
SELECT  nama, nama_mk
FROM tb_ambil_mk tam
JOIN tb_matakuliah tmk ON tam.kode_mk = tmk.pk_kode_mk
JOIN tb_mahasiswa tm ON tm.pk_nim = tam.nim
ORDER BY nim

-- No. 2
SELECT tm.pk_nim, nama, j_kelamin, alamat
FROM tb_ambil_mk tam
RIGHT JOIN tb_mahasiswa tm ON tm.pk_nim = tam.nim
WHERE tam.nim IS NULL

-- No. 3
SELECT COUNT(j_kelamin) AS jml, j_kelamin
FROM tb_ambil_mk tam
RIGHT JOIN tb_mahasiswa tm ON tm.pk_nim = tam.nim
WHERE tam.nim IS NULL
GROUP BY j_kelamin
ORDER BY COUNT(j_kelamin) DESC

-- No. 4
SELECT tm.pk_nim, nama, tmk.pk_kode_mk, nama_mk
FROM tb_ambil_mk tam
JOIN tb_matakuliah tmk ON tam.kode_mk = tmk.pk_kode_mk
JOIN tb_mahasiswa tm ON tm.pk_nim = tam.nim

-- No. 5
SELECT tm.pk_nim, nama, SUM(sks)
FROM tb_ambil_mk tam
JOIN tb_matakuliah tmk ON tam.kode_mk = tmk.pk_kode_mk
JOIN tb_mahasiswa tm ON tm.pk_nim = tam.nim
GROUP BY tm.pk_nim
HAVING SUM(sks) > 4 AND SUM(sks) < 10

-- No. 6
SELECT nama_mk
FROM tb_ambil_mk tam
RIGHT JOIN tb_matakuliah tmk ON tmk.pk_kode_mk = tam.kode_mk
LEFT JOIN tb_mahasiswa tm ON tm.pk_nim = tam.nim
WHERE tam.kode_mk IS NULL OR tm.pk_nim IS NULL
ORDER BY sks ASC