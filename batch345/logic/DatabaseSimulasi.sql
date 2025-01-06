create table biodata(
	id bigserial primary key,
	firstname varchar(20),
	lastname varchar(20),
	dob date,
	pob varchar(50),
	address varchar(255),
	gender varchar(1)
);

insert into biodata(firstname, lastname,dob,pob,address,gender)
values 
('soraya','rahayu','1990-12-22','Bali','JL Raya Kuta, Bali','P'),
('hanum','danuary','1990-01-02','Bandung','JL Berkah Ramadhan, Bandung','P'),
('melati','marcelia','1991-03-03','Jakarta','JL Mawar 3, Brebes','P'),
('farhan','Djokrowidodo','1989-10-11','Jakarta','JL Bahari Raya, Solo','L');

create table employee (
	id bigserial primary key,
	biodata_id bigint,
	nip varchar(5),
	status varchar(10),
	join_date timestamp,
	salary decimal(10,0)
);

insert into employee(biodata_id,nip,status,join_date,salary)
values
(1,'XA001','Permanen','2015-11-01 00:00:00.000', 12000000),
(2,'XA002','Kontrak','2017-01-02 00:00:00.000', 10000000),
(3,'XA003','Kontrak','2018-08-19 00:00:00.000', 10000000);

create table contact_person(
	id bigserial primary key,
	biodata_id bigserial,
	type varchar(5),
	contact varchar(100)
);

insert into contact_person(biodata_id,type,contact)
values
(1,'MAIL','soraya.rahayu@gmail.com'),
(1,'PHONE','085612345678'),
(2,'MAIL','hanum.danuary@gmail.com'),
(2,'PHONE','081312345678'),
(2,'PHONE','087812345678'),
(3,'MAIL','melati.marcelia@gmail.com');

create table leave(
	id bigserial primary key,
	type varchar(10),
	name varchar(100)
)

insert into leave(type,name)
values
('Regular','Cuti Tahunan'),
('Khusus', 'Cuti Menikah'),
('Khusus','Cuti Haji & Umroh'),
('Khusus','Melahirkan');

create table employee_leave(
	id serial primary key,
	employee_id int,
	period varchar(4),
	regular_quota int
);

insert into employee_leave(employee_id,period,regular_quota)
values
(1,'2021',16),
(2,'2021',12),
(3,'2021',12);

create table leave_request(
	id bigserial primary key,
	employee_id bigint,
	leave_id bigint,
	start_date date,
	end_date date,
	reason varchar(255)
);

insert into leave_request(
	employee_id,
	leave_id,
	start_date,
	end_date,
	reason
) values
(1,1,'2021-10-10','2021-10-12','Liburan'),
(1,1,'2021-11-12','2021-11-15','Acara keluarga'),
(2,2,'2021-05-05','2021-05-07','Menikah'),
(2,1,'2021-09-09','2021-09-13','Touring'),
(2,1,'2021-10-20','2021-12-23','Acara Keluarga');

-- 1. Menampilkan karyawan yang pertama kali masuk.
select a.*, b.nip from biodata a inner join employee b on b.biodata_id = a.id;

-- 2. Menampilkan daftar karyawan yang saat ini sedang cuti. 
-- Daftar berisi nomor_induk, nama, tanggal_mulai, lama_cuti dan keterangan.
select a.nip,concat(b.firstname,' ',b.lastname) as nama, c.start_date as tanggal_mulai,
ABS((c.start_date - c.end_date) + 1) as lama_cuti, c.reason
from employee a
inner join biodata b on b.id = a.biodata_id
inner join leave_request c on c.employee_id = b.id;

-- 3. Menampilkan daftar karyawan yang sudah mengajukan cuti lebih dari 2 kali. 
-- Tampilkan data berisi no_induk, nama, jumlah pengajuan.
select a.nip, concat(b.firstname, ' ',b.lastname) as nama, count(c.employee_id) as jumlah_pengajuan
from employee a
inner join biodata b on b.id = a.biodata_id
inner join leave_request c on c.employee_id = b.id
group by a.nip,nama
having count(c.employee_id) > 2;

-- 4. Menampilkan sisa cuti tiap karyawan tahun ini, jika di ketahui jatah cuti setiap karyawan tahun ini adalah sesuaidengan quota cuti. 
-- tampilan berisi no_induk, nama, quota, cuti yg sudah di ambil dan sisa_cuti
select a.nip, concat(b.firstname, ' ',b.lastname) as nama, c.regular_quota, (16-c.regular_quota) as cuti_yang_diambil
from employee a
inner join biodata b on b.id = a.biodata_id
inner join employee_leave c on c.employee_id = b.id;

-- 5. Perusahaan akan meberikan bonus bagi karyawan yang sudah bekerja lebih dari 5 tahun sebanyak 1.5 kali gaji. 
-- Tampilan No induk, Fullname, Berapa lama bekerja, Bonus, Total Gaji(gaji + bonus)													