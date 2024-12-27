--BUAT TABEL tblPengarang 
CREATE TABLE tbl_pengarang (
  id SERIAL PRIMARY KEY,
  kd_pengarang VARCHAR(7) NOT NULL,
  nama VARCHAR(30) NOT NULL,
  alamat VARCHAR(80) NOT NULL,
  kota VARCHAR(15) NOT NULL,
  kelamin VARCHAR(1) NOT NULL
);

--INSERT VALUE INTO tbl_pengarang
INSERT INTO tbl_pengarang (kd_pengarang, nama, alamat, kota, kelamin)
  VALUES
    ('P0001', 'Ashadi', 'Jl. Beo 25', 'Yogya', 'P'),
    ('P0002', 'Rian', 'Jl. Solo 123', 'Yogya', 'P'),
    ('P0003', 'Suwadi', 'Jl. Semangka 13', 'Bandung', 'P'),
    ('P0004', 'Siti', 'Jl. Durian 15', 'Solo', 'W'),
    ('P0005', 'Amir', 'Jl. Gajah 33', 'Kudus', 'P'),
    ('P0006', 'Suparman', 'Jl. Harimau 25', 'Jakarta', 'P'),
    ('P0007', 'Jaja', 'Jl. Singa 7', 'Bandung', 'P'),
    ('P0008', 'Saman', 'Jl. Naga 12', 'Yogya', 'P'),
    ('P0009', 'Anwar', 'Jl. Tidar 6A', 'Magelang', 'P'),
    ('P0010', 'Fatmawati', 'Jl. Renjana 4', 'Bogor', 'W');

CREATE TABLE tbl_gaji (
  id SERIAL PRIMARY KEY,
  kd_pengarang VARCHAR(7) NOT NULL,
  nama VARCHAR(30) NOT NULL,
  gaji DECIMAL(18, 4)
);

INSERT INTO tbl_gaji (kd_pengarang, nama, gaji)
  VALUES 
  ('P0002', 'Rian', 600000),
  ('P0005', 'Amir', 700000),
  ('P0004', 'Siti', 500000),
  ('P0003', 'Suwadi', 1000000),
  ('P0010', 'Fatmawati', 600000),
  ('P0008', 'Saman', 750000);

-- TAMPIL KESELURUHAN TABEL
SELECT * FROM tbl_pengarang;
SELECT * FROM tbl_gaji;

-- HITUNG JUMLAH PENGARANG PADA TABEL
SELECT COUNT(*) AS jumlah_pengarang FROM tbl_pengarang;


-- HITUNG JUMLAH WANITA DAN PRIA
SELECT
    COUNT(CASE WHEN kelamin = 'W' THEN 1 END) AS Jumlah_wanita,
    COUNT(CASE WHEN kelamin = 'P' THEN 1 END) AS Jumlah_pria
FROM tbl_pengarang;

-- MEMILIH KOLOM KOTA LALU MENJUMLAHKAN SEMUANYA BERDASARKAN NAMA KOTANYA
SELECT kota, COUNT(*) AS jumlah_kota FROM tbl_pengarang
GROUP BY kota;

-- MEMILIH KOLOM KOTA LALU MENJUMLAHKANNYA DAN MENAMPILKAN YANG MEMILIKI JUMLAH > 1
SELECT kota, COUNT(*) AS jumlah_kota FROM tbl_pengarang
GROUP BY kota HAVING COUNT(*) > 1;

-- MENAMPILKAN kd_pengarang YANG TERBESAR DAN TERKECIL
SELECT 
  MAX(kd_pengarang) AS max_kd_pengarang,
  MIN(kd_pengarang) AS min_kd_pengarang
FROM tbl_pengarang;

-- MENAMPILKAN GAJI TERBESAR DAN GAJI TERKECIL
SELECT
  MAX(gaji) as gaji_tertinggi,
  MIN(gaji) as gaji_terendah
FROM tbl_gaji;

-- MENAMPILKAN GAJI DIATAS 600000
SELECT gaji FROM tbl_gaji WHERE gaji > 600000;

-- MENAMPILKAN JUMLAH GAJI
SELECT SUM(gaji) FROM tbl_gaji;

