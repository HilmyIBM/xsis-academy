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

select count(*) from tbl_gaji where gaji>700000; 


--nomer 10
select * from tbl_pengarang where kd_pengarang between 'P0003' and 'P0006';

--nomer 11
select * from tbl_pengarang where kota in ('Yogya', 'Solo', 'Magelang');

--nomer 12
select * from tbl_pengarang where kota <> 'Yogya';

--nomer 13
SELECT *
FROM tbl_pengarang
WHERE nama LIKE 'A%';

SELECT *
FROM tbl_pengarang
WHERE nama LIKE '%i';

SELECT *
FROM tbl_pengarang
WHERE nama LIKE '__a%';

SELECT *
FROM tbl_pengarang
WHERE nama NOT LIKE '%n';



-- 1. Menambahkan Kolom Baru
ALTER TABLE Customer ADD email VARCHAR(100);  -- Menambahkan kolom email

-- 2. Menambah Beberapa Kolom Baru dalam Satu Perintah
ALTER TABLE Customer ADD phone_number VARCHAR(15), address TEXT;

-- 3. Menghapus Kolom
ALTER TABLE Customer DROP COLUMN address;  -- Menghapus kolom address

-- 4. Mengubah Nama Kolom
ALTER TABLE Customer RENAME COLUMN phone_number TO contact_number;  -- Mengubah nama kolom phone_number menjadi contact_number

-- 5. Mengubah Tipe Data Kolom
-- Mengubah tipe data kolom phone_number (yang sudah diganti namanya menjadi contact_number) dari VARCHAR(15) menjadi VARCHAR(20)
ALTER TABLE Customer MODIFY contact_number VARCHAR(20);  -- MySQL example
-- ALTER TABLE Customer ALTER COLUMN contact_number TYPE VARCHAR(20);  -- PostgreSQL example

-- 6. Menambahkan Primary Key
ALTER TABLE Customer ADD PRIMARY KEY (id_customer);  -- Menambahkan PRIMARY KEY pada kolom id_customer

-- 7. Menambahkan Foreign Key
ALTER TABLE Customer ADD CONSTRAINT fk_customer_order FOREIGN KEY (order_id) REFERENCES Orders (id_order);  -- Menambahkan FOREIGN KEY yang mengacu pada id_order di tabel Orders

-- 8. Mengubah Nama Tabel
ALTER TABLE Customer RENAME TO Clients;  -- Mengubah nama tabel Customer menjadi Clients

-- 9. Menambahkan Nilai Default pada Kolom
ALTER TABLE Clients ALTER COLUMN balance SET DEFAULT 0;  -- Menambahkan nilai default 0 pada kolom balance

-- 10. Menghapus Nilai Default pada Kolom
ALTER TABLE Clients ALTER COLUMN balance DROP DEFAULT;  -- Menghapus nilai default dari kolom balance

-- 11. Menghapus Primary Key
ALTER TABLE Clients DROP PRIMARY KEY;  -- Menghapus PRIMARY KEY

-- 12. Menghapus Foreign Key
ALTER TABLE Clients DROP CONSTRAINT fk_customer_order;  -- Menghapus FOREIGN KEY constraint



-- PostgreSQL DATE Cheatsheet

-- 1. Ekstraksi Komponen dari Tanggal
-- Ekstrak Tahun
SELECT EXTRACT(YEAR FROM CURRENT_DATE) AS year;

-- Ekstrak Bulan
SELECT EXTRACT(MONTH FROM CURRENT_DATE) AS month;

-- Ekstrak Hari
SELECT EXTRACT(DAY FROM CURRENT_DATE) AS day;

-- Ekstrak Hari dalam Minggu
SELECT EXTRACT(DOW FROM CURRENT_DATE) AS day_of_week;

-- Ekstrak Hari dalam Tahun
SELECT EXTRACT(DOY FROM CURRENT_DATE) AS day_of_year;

-- 2. Format Tanggal
-- Format ke 'YYYY-MM-DD'
SELECT TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') AS formatted_date;

-- Format ke 'DD Month YYYY'
SELECT TO_CHAR(CURRENT_DATE, 'DD Month YYYY') AS formatted_date_with_text;

-- 3. Operasi Aritmatika pada Tanggal
-- Tambah Hari
SELECT CURRENT_DATE + INTERVAL '10 days' AS date_plus_10_days;

-- Kurangi Hari
SELECT CURRENT_DATE - INTERVAL '10 days' AS date_minus_10_days;

-- Selisih Tanggal
SELECT CURRENT_DATE - DATE '2025-01-01' AS days_difference;
SELECT AGE(DATE '2025-01-01', CURRENT_DATE) AS age_difference;

-- 4. Manipulasi Tanggal
-- Set Tanggal ke Awal Bulan
SELECT DATE_TRUNC('month', CURRENT_DATE) AS start_of_month;

-- Set Tanggal ke Awal Tahun
SELECT DATE_TRUNC('year', CURRENT_DATE) AS start_of_year;

-- 5. Filter Berdasarkan Tanggal
-- Filter Tanggal Spesifik
SELECT * FROM table_name WHERE date_column = '2025-01-08';

-- Filter Rentang Tanggal
SELECT * FROM table_name 
WHERE date_column BETWEEN '2025-01-01' AND '2025-01-31';

-- Filter Lebih Baru atau Lebih Lama
SELECT * FROM table_name WHERE date_column > '2025-01-01';
SELECT * FROM table_name WHERE date_column < '2025-01-01';

-- 6. Konversi ke Tanggal
-- Konversi dari String ke DATE
SELECT TO_DATE('08-01-2025', 'DD-MM-YYYY') AS converted_date;

-- Konversi dari Timestamp ke DATE
SELECT DATE(TIMESTAMP '2025-01-08 15:30:00') AS date_from_timestamp;

-- 7. Operasi dengan Time Zone
-- Current Date dengan Time Zone
SELECT CURRENT_DATE AT TIME ZONE 'UTC' AS date_utc;

-- Konversi Time Zone
SELECT TIMESTAMP '2025-01-08 15:30:00' AT TIME ZONE 'Asia/Jakarta' AS jakarta_time;

-- 8. Informasi Tambahan
-- Apakah Tahun Kabisat
SELECT EXTRACT(YEAR FROM CURRENT_DATE) % 4 = 0 AS is_leap_year;

-- Hari dalam Bulan
SELECT DATE_PART('days', DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '1 month' - INTERVAL '1 day') AS days_in_month;
