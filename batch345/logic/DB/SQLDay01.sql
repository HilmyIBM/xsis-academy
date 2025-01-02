create database DBPenerbit;
create table tblPengarang (
	"ID" serial primary key not null,
	"Kd_Pengarang" varchar(7) not null,
	"Nama" varchar(30) not null,
	"Alamat" varchar(80) not null,
	"Kota" varchar(15) not null,
	"Kelamin" varchar(1)
);

drop table tblPengarang;

alter table tblpengarang 
rename "Kelmin" to "Kelamin";

insert into tblPengarang ("Kd_Pengarang", "Nama", "Alamat", "Kota", "Kelamin")
values ('P0001', 'Ashadi', 'Jl.Beo 25', 'Yogya', 'P'),
 ('P0002', 'Ryan', 'Jl.Solo 123', 'Yogya', 'P'),
 ('P0003', 'Swandi', 'Jl.Semangka 13', 'Bandung', 'P'),
 ('P0004', 'Siti', 'Jl.Durian 15', 'Solo', 'W'),
 ('P0005', 'Amir', 'Jl.Gajah 33', 'Kudus', 'P'),
 ('P0006', 'Suparman', 'Jl.Harimau 25', 'Jakarta', 'P'),
 ('P0007', 'Jaja', 'Jl.Singa 7', 'Bandung', 'P'),
 ('P0008', 'Saman', 'Jl.Naga 12', 'Yogya', 'P'),
 ('P0009', 'Anwar', 'Jl.Tidar 6A', 'Magelang', 'P'),
 ('P0010', 'Fatmawati', 'Jl.Renjana 4', 'Bogor', 'W');


create table tblGaji (
	"ID" serial primary key not null,
	"Kd_Pengarang" varchar(7) not null,
	"Nama" varchar(30) not null,
	"Gaji" Decimal(18, 4) not null
);


insert into tblgaji ("Kd_Pengarang", "Nama", "Gaji")
values
 ('P0002', 'Ryan', 600000),
 ('P0005', 'Amir', 700000),
 ('P0004', 'Siti', 500000),
 ('P0003', 'Swandi', 1000000),
 ('P0010', 'Fatmawati', 600000),
 ('P0008', 'Saman', 750000);

select * from tblgaji;

--count jumlah tabel
--aggregate function (Count, min, max, sum, avg)
select count(*) from tblgaji;
select * from tblpengarang;

ALTER TABLE tblpengarang
	alter COLUMN kelamin TYPE varchar not null;