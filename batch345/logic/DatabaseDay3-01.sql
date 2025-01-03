create table tb_karyawan (
	id serial primary key,
	nip varchar(50) not null,
	nama_depan varchar(50) not null,
	nama_belakang varchar(50) not null,
	jenis_kelamin varchar(50) not null,
	agama varchar(50) not null,
	tempat_lahir varchar(50) not null,
	tgl_lahir date,
	alamat varchar(100) not null,
	pendidikan_terakhir varchar(50) not null,
	tgl_masuk date,
	constraint nip_unique unique (nip) 
);

create table tb_divisi(
	id serial primary key,
	kd_divisi varchar(50) not null,
	nama_divisi varchar(50) not null,
	constraint kd_divisi_unique unique(kd_divisi)
);

create table tb_jabatan (
	id serial primary key,
	kd_jabatan varchar(50) not null,
	nama_jabatan varchar(50) not null,
	gaji_pokok numeric,
	tunjangan numeric,
	constraint kd_jabatan_unique unique (kd_jabatan)
);

create table tb_pekerjaan(
	id serial primary key,
	nip varchar(50) not null,
	kode_jabatan varchar(50) not null,
	kode_divisi varchar(50) not null,
	tunjangan_kerja numeric,
	kota_penempatan varchar(50),
	constraint fk_kode_jabatan FOREIGN key (kode_jabatan) references tb_jabatan(kd_jabatan),
	constraint fk_kode_divisi FOREIGN key (kode_divisi) references tb_divisi(kd_divisi),
	constraint fk_nip FOREIGN key (nip) references tb_karyawan (nip)
);

INSERT INTO tb_karyawan (nip, nama_depan, nama_belakang, jenis_kelamin, agama, tempat_lahir, 
    tgl_lahir, alamat, pendidikan_terakhir, tgl_masuk)
VALUES
('001', 'Hamidi', 'Samsudin', 'Pria', 'Islam', 'Sukabumi', '1977-04-21', 'Jl. Sudirman No.12', 'S1 Teknik Mesin', '2015-12-07'),
('002', 'Paul', 'Christian', 'Pria', 'Kristen', 'Ambon', '1980-05-27', 'Jl. Veteran No.4', 'S1 Pendidikan Geografi', '2014-01-12'),
('003', 'Ghandi', 'Wamida', 'Wanita', 'Islam', 'Palu', '1992-01-12', 'Jl. Rambutan No.22', 'SMA Negeri 02 Palu', '2014-12-01');

INSERT INTO tb_divisi (kd_divisi, nama_divisi)
VALUES
('GD', 'Gudang'),
('HRD', 'HRD'),
('KU', 'Keuangan'),
('UM', 'Umum');

alter table tb_jabatan
	rename column tunjangan to tunjangan_jabatan; 

INSERT INTO tb_jabatan (kd_jabatan, nama_jabatan, gaji_pokok, tunjangan_jabatan)
VALUES
('MGR', 'Manager', 5500000, 1500000),
('OB', 'Office Boy', 1900000, 200000),
('ST', 'Staff', 3000000, 750000),
('WMGR', 'Wakil Manager', 4000000, 1200000);

alter table tb_pekerjaan
	rename column tunjangan_kerja to tunjangan_kinerja;

INSERT INTO tb_pekerjaan (nip, kode_jabatan, kode_divisi, tunjangan_kinerja, kota_penempatan)
VALUES
('001', 'ST', 'KU', 750000, 'Cianjur'),
('002', 'OB', 'UM', 350000, 'Sukabumi'),
('003', 'MGR', 'HRD', 1500000, 'Sukabumi');

-- 	1. Tampilkan nama lengkap, nama jabatan, tunjangan jabatan + gaji , yang gaji + tunjangan kinerja dibawah 5juta
select concat(a.nama_depan,' ',a.nama_belakang), c.nama_jabatan, sum(c.gaji_pokok + c.tunjangan_jabatan) from tb_karyawan a inner join  tb_pekerjaan b on b.nip = a.nip
inner join tb_jabatan c on c.kd_jabatan = b.kode_jabatan group by a.nama_depan,a.nama_belakang,c.nama_jabatan having sum(c.gaji_pokok + c.tunjangan_jabatan) < 5000000;

-- 	2. Tampilkan nama lengkap, jabatan, nama divisi, total gaji, pajak, gaji bersih, yg gendernya pria dan penempatan kerjanya diluar sukabumi
SELECT CONCAT(nama_depan, ' ', nama_belakang) AS nama_lengkap, 
tj.nama_jabatan, 
tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja AS gaji_tunjangan,
(tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja)* 0.05 AS pajak,
tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja - (tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja)* 0.05 AS gaji_bersih
FROM tb_karyawan tk
JOIN tb_pekerjaan tp ON tk.nip = tp.nip
JOIN tb_jabatan tj ON tj.kd_jabatan = tp.kode_jabatan
WHERE jenis_kelamin LIKE 'Pria' AND tp.kota_penempatan NOT LIKE 'Sukabumi';

-- 3. Tampilkan nip, nama lengkap, jabatan, nama divisi, bonus (bonus=25% dari total gaji(gaji pokok+tunjangan_jabatan+tunajangan_kinerja * 7)													
select tk.nip, concat(tk.nama_depan,' ',tk.nama_belakang) as nama_lengkap, tj.nama_jabatan, td.nama_divisi, (tp.tunjangan_kinerja + tj.gaji_pokok + tj.tunjangan_jabatan) * 0.25 * 7 as bonus from tb_karyawan tk
inner join tb_pekerjaan tp on tp.nip = tk.nip
inner join tb_jabatan tj on tj.kd_jabatan = tp.kode_jabatan
inner join tb_divisi td on td.kd_divisi = tp.kode_divisi order by tk.nip;

-- 4. Tampilkan nama lengkap, total gaji, infak(5%*total gaji) yang mempunyai jabatan MGR
select concat(tk.nama_depan,' ',tk.nama_belakang) as nama_lengkap, sum(tj.gaji_pokok+tj.tunjangan_jabatan) as gaji, sum(tj.gaji_pokok+tj.tunjangan_jabatan) * 0.05 as infak 
from tb_karyawan tk 
inner join tb_pekerjaan tp on tp.nip = tk.nip
inner join tb_jabatan tj on tj.kd_jabatan = tp.kode_jabatan
inner join tb_divisi td on td.kd_divisi = tp.kode_divisi
where td.nama_divisi = 'MGR' group by tk.nama_depan,tk.nama_belakang;

-- 5. Tampilkan nama lengkap, nama jabatan, pendidikan terakhir, 
-- tunjangan pendidikan(2jt), dan total gaji(gapok+tjabatan+tpendidikan) dimana pendidikan akhirnya adalah S1
select tp.nip, concat(tk.nama_depan,' ',tk.nama_belakang) as nama_lengkap, tj.nama_jabatan, tk.pendidikan_terakhir, 2000000 as tunjangan_pendidikan, (2000000 + tj.gaji_pokok+tj.tunjangan_jabatan) as total_gaji
from tb_karyawan tk
inner join tb_pekerjaan tp on tp.nip = tk.nip
inner join tb_jabatan tj on tj.kd_jabatan = tp.kode_jabatan
where tk.pendidikan_terakhir like 'S1%'
group by tk.nama_depan, tk.nama_belakang, tj.nama_jabatan,tk.pendidikan_terakhir,tj.gaji_pokok,tj.tunjangan_jabatan,tp.nip;

-- 6. Tampilkan nip, nama lengkap, jabatan, nama divisi, bonus
-- MGR = (bonus=25% dari total gaji*7) ,						
-- ST = (bonus=25% dari total gaji*5) ,						
-- other = (bonus=25% dari total gaji*2) ,						
select tk.nip, concat(tk.nama_depan,' ',tk.nama_belakang) as nama_lengkap, td.nama_divisi,
	CASE td.nama_divisi
		when 'MGR' then 0.25*(tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 7
		when 'ST' then 0.25*(tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 5
		else 0.25*(tj.gaji_pokok + tj.tunjangan_jabatan + tp.tunjangan_kinerja) * 2
	END as total_bonus from tb_karyawan tk
inner join tb_pekerjaan tp on tp.nip = tk.nip
inner join tb_divisi td on td.kd_divisi = tp.kode_divisi
inner join tb_jabatan tj on tj.kd_jabatan = tp.kode_jabatan;

-- 7. Buatlah kolom nip pada table karyawan sebagai kolom unique
alter table tb_karyawan
add constraint unique_nip unique (nip);

-- 8. buatlah kolom nip pada table karyawan sebagai index
create index nip_index on tb_karyawan (nip);

-- 9. Tampilkan nama lengkap, nama belakangnya diubah menjadi huruf capital dengan kondisi nama belakang di awali dengan huruf W
select 
	case 
	when nama_belakang like 'W%' then upper(concat(nama_depan, ' ', nama_belakang))
	else concat(nama_depan, ' ', nama_belakang)
	end as nama_lengkap
from tb_karyawan;

-- 10.  Perusahaan akan memberikan bonus sebanyak 10% dari total gaji bagi karyawan yg sudah join 
-- di perusahaan diatas sama dengan 8 tahun per 2022													
-- Tampilkan nip, nama lengkap, jabatan, nama divisi, total gaji , bonus, lama bekerja													
-- (total gaji = Gaji_pokok+Tunjangan_jabatan+Tunjangan_kinerja )													
