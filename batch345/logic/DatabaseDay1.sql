SELECT
	*
FROM
	pg_database;

CREATE DATABASE DBPenerbit;

CREATE TABLE
	tblPengarang (
		ID SERIAL PRIMARY KEY,
		Kd_Pengarang VARCHAR(7) NOT NULL,
		Nama VARCHAR(30) NOT NULL,
		Alamat VARCHAR(80) NOT NULL,
		Kota VARCHAR(15) NOT NULL,
		Kelamin VARCHAR(1) NOT NULL
	);

ALTER TABLE tblPengarang ADD CONSTRAINT unique_kd_pengarang UNIQUE (Kd_Pengarang);

INSERT INTO
	tblPengarang (Kd_Pengarang, Nama, Alamat, Kota, Kelamin)
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

SELECT
	*
FROM
	tblPengarang;

CREATE TABLE
	tblGaji (
		ID serial PRIMARY KEY,
		Kd_Pengarang VARCHAR(7) NOT NULL,
		Nama VARCHAR(30) NOT NULL,
		Gaji DECIMAL(18, 4) NOT NULL
	);

INSERT INTO
	tblGaji (Kd_Pengarang, Nama, Gaji)
VALUES
	('P0002', 'Rian', 600000),
	('P0005', 'Amir', 700000),
	('P0004', 'Siti', 500000),
	('P0003', 'Suwadi', 1000000),
	('P0010', 'Fatmawati', 600000),
	('P0008', 'Saman', 750000);

SELECT
	*
FROM
	tblGaji;

ALTER TABLE tblGaji ADD CONSTRAINT fk_kd_pengarang FOREIGN KEY (Kd_Pengarang) REFERENCES tblPengarang (Kd_Pengarang) ON DELETE CASCADE ON UPDATE CASCADE;

SELECT
	*
FROM
	TBLGAJI;

create table coba1 (id serial primary key, testing varchar(10));

drop table coba1;

select * from information_schema.tables where table_catalog = 'tblpengarang' and table_schema='public';
select * from information_schema.columns where table_name = 'tblpengarang';
select * from information_schema.table_constraints where table_name = 'tblgaji';
-- Find the sequences on specific table
SELECT
    sequence_name,
    table_name,
    column_name
FROM
    information_schema.columns
    JOIN information_schema.sequences
    ON columns.column_default LIKE '%' || sequences.sequence_name || '%'
WHERE
    columns.table_name = 'tblpengarang';

-- Soal
-- (1. Hitung dan tampilkan jumlah pengarang dari table tblPengarang.)
select count(kd_pengarang) as jumlah_pengarang from tblPengarang; 

-- 2. Hitunglah berapa jumlah Pengarang Wanita dan Pria
select kelamin, count (kelamin) as jumlah_jenis_kelamin from tblPengarang group by (kelamin);

-- 3. Tampilkan record kota dan jumlah kotanya dari table tblPengarang.				
select kota, count(kota) as jumlahKota from tblPengarang group by kota;

-- 4. Tampilkan record kota diatas 1 kota dari table tblPengarang.
select kota, count(kota) as jumlahKota from tblPengarang group by kota having count(kota) > 1;

-- 5. Tampilkan Kd_Pengarang yang terbesar dan terkecil dari table tblPengarang.
select * from tblPengarang order by kd_pengarang desc;

-- 6. Tampilkan gaji tertinggi dan terendah.
select trunc(min(gaji)) as gaji_terendah, trunc(max(gaji)) as gaji_tertinggi from tblgaji;

-- 7. Tampilkan gaji diatas 600.000.
select trunc(gaji) as gajiPembulatan from tblgaji where gaji > 600000 order by gaji asc;

-- 8. Tampilkan jumlah gaji.
select sum(round(gaji,1)) from tblgaji; 

-- 9. Tampilkan jumlah gaji berdasarkan Kota
select b.kota, sum(trunc(a.gaji)) from tblgaji a inner join tblPengarang b on b.kd_pengarang = a.kd_pengarang group by b.kota;

-- 10. Tampilkan seluruh record pengarang antara P0003-P0006 dari tabel pengarang.					
select * from tblPengarang where kd_pengarang > 'P0003' and kd_pengarang < 'P0006';

-- 11. Tampilkan seluruh data yogya, solo, dan magelang dari tabel pengarang.
select a.*,trunc(b.gaji) as gaji from tblPengarang a left join tblgaji b on b.kd_pengarang = a.kd_pengarang where lower(trim(a.kota)) in ('yogya','solo','magelang') order by a.kd_pengarang;

-- 12. Tampilkan seluruh data yang bukan yogya dari tabel pengarang.				
select a.*, trunc(b.gaji) as gaji from tblPengarang a left join tblGaji b on b.kd_pengarang = a.kd_pengarang where lower(trim(a.kota)) not in ('yogya') order by a.kd_pengarang;

-- 13. Tampilkan seluruh data pengarang yang nama (terpisah):					
-- a. dimulai dengan huruf [A]					
-- b. berakhiran [i]					
-- c. huruf ketiganya [a]					
-- d. tidak berakhiran [n]					
select * from tblPengarang where trim(nama) like 'A%';
select * from tblPengarang where trim(nama) like '%i';
select * from tblPengarang where trim(nama) like '__a%';
select * from tblPengarang where trim(nama) not like '%n';

-- 14. Tampilkan seluruh data table tblPengarang dan tblGaji dengan Kd_Pengarang yang sama						
select a.*, b.* from tblPengarang a inner join tblGaji b on b.kd_pengarang = a.kd_pengarang;

-- 15. Tampilan kota yang memiliki gaji dibawah 1.000.000
select a.kota from tblPengarang a inner join tblGaji b on b.kd_pengarang = a.kd_pengarang where b.gaji<1000000;

-- 16. Ubah panjang dari tipe kelamin menjadi 10			
alter table tblPengarang
alter column kelamin type varchar(10);

-- 17. Tambahkan kolom [Gelar] dengan tipe Varchar (12) pada tabel tblPengarang
alter table tblPengarang
add column "Gelar" varchar(12);

-- 18. Ubah alamat dan kota dari Rian di table tblPengarang menjadi, Jl. Cendrawasih 65 dan Pekanbaru						
update tblPengarang
set alamat = 'Jl. Cendrawasih 65' ,kota = 'Pekanbaru' where nama = 'Rian';

-- 19. Buatlah view untuk attribute Kd_Pengarang, Nama, Kota, Gaji dengan nama vwPengarang
create view vwPengarang as select a.kd_pengarang, a.nama, a.kota, b.gaji from tblPengarang a left join tblGaji b
on b.kd_pengarang = a.kd_pengarang;

select * from vwPengarang;