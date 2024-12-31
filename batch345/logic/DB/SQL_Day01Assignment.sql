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