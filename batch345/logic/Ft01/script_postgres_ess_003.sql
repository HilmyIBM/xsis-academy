DROP TABLE IF EXISTS biodata;
CREATE TABLE biodata (
	id bigint,
	first_name varchar(20), 
	last_name varchar(30), 
	dob date, 
	pob varchar(50), 
	address varchar(255), 
	marital_status boolean
);

INSERT INTO biodata VALUES (1, 'Raya', 'Indriyani', '1991-01-01', 'Bali', 'Jl. Raya Baru, Bali', false);
INSERT INTO biodata VALUES (2, 'Rere', 'Wulandari', '1992-01-02', 'Bandung', 'Jl. Berkah Ramadhan, Bandung', false);
INSERT INTO biodata VALUES (3, 'Bunga', 'Maria', '1991-03-03', 'Jakarta', 'Jl. Mawar Semerbak, Bogor', true);
INSERT INTO biodata VALUES (4, 'Natasha', 'Wulan', '1990-04-10', 'Jakarta', 'Jl. Mawar Harum, Jakarta', false);
INSERT INTO biodata VALUES (5, 'Zahra', 'Aurelia Putri', '1991-03-03', 'Jakarta', 'Jl. Mawar Semerbak, Bogor', true);
INSERT INTO biodata VALUES (6, 'Katniss', 'Everdeen', '1989-01-12', 'Jakarta', 'Jl. Bunga Melati, Jakarta', true);

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
	id bigint,
	biodata_id bigint,
	nip varchar(5), 
	status varchar(10), 
	salary decimal(10, 0)
);

INSERT INTO employee VALUES (1, 1, 'NX001', 'Permanen', 12000000);
INSERT INTO employee VALUES (2, 2, 'NX002', 'Kontrak', 15000000);
INSERT INTO employee VALUES (3, 4, 'NX003', 'Permanen', 13500000);
INSERT INTO employee VALUES (4, 5, 'NX004', 'Permanen', 12000000);
INSERT INTO employee VALUES (5, 6, 'NX005', 'Permanen', 17000000);

DROP TABLE IF EXISTS contact_person;
CREATE TABLE contact_person (
	id bigint,
	biodata_id bigint,
	type varchar(5), 
	contact varchar(100)
);

INSERT INTO contact_person VALUES (1, 1, 'MAIL', 'raya.indriyani@gmail.com');
INSERT INTO contact_person VALUES (2, 1, 'PHONE', '085612345678');
INSERT INTO contact_person VALUES (3, 2, 'MAIL', 'rere.wulandari@gmail.com');
INSERT INTO contact_person VALUES (4, 2, 'PHONE', '081312345678');
INSERT INTO contact_person VALUES (5, 2, 'PHONE', '087812345678');
INSERT INTO contact_person VALUES (6, 3, 'MAIL', 'bunga.maria@gmail.com');
INSERT INTO contact_person VALUES (7, 4, 'MAIL', 'natasha.wulan@gmail.com');
INSERT INTO contact_person VALUES (8, 5, 'MAIL', 'zahra.putri@gmail.com');
INSERT INTO contact_person VALUES (9, 6, 'MAIL', 'katniss.everdeen@gmail.com');

DROP TABLE IF EXISTS leave;
CREATE TABLE leave (
	id bigint,
	type varchar(10), 
	name varchar(100)
);

INSERT INTO leave VALUES (1, 'Regular', 'Cuti Tahunan');
INSERT INTO leave VALUES (2, 'Khusus', 'Cuti Menikah');
INSERT INTO leave VALUES (3, 'Khusus', 'Cuti Haji & Umroh');
INSERT INTO leave VALUES (4, 'Khusus', 'Melahirkan');

DROP TABLE IF EXISTS employee_leave;
CREATE TABLE employee_leave (
  id integer,
  employee_id integer,
  period varchar(4),
  regular_quota integer
);

INSERT INTO employee_leave VALUES (1, 1, '2020', 16);
INSERT INTO employee_leave VALUES (2, 2, '2020', 12);
INSERT INTO employee_leave VALUES (3, 1, '2021', 16);
INSERT INTO employee_leave VALUES (4, 2, '2021', 12);
INSERT INTO employee_leave VALUES (5, 4, '2021', 12);
INSERT INTO employee_leave VALUES (6, 5, '2021', 12);
INSERT INTO employee_leave VALUES (7, 3, '2021', 12);

DROP TABLE IF EXISTS leave_request;
CREATE TABLE leave_request (
	id bigint,
	employee_id bigint,
	leave_id bigint,
	start_date date,
	end_date date,
	reason varchar(255)
);

INSERT INTO leave_request VALUES (1, 1, 1, '2020-10-10', '2020-10-12', 'Liburan');
INSERT INTO leave_request VALUES (2, 1, 1, '2020-11-12', '2020-11-15', 'Acara keluarga');
INSERT INTO leave_request VALUES (3, 2, 2, '2020-05-05', '2020-05-07', 'Menikah');
INSERT INTO leave_request VALUES (4, 2, 1, '2020-09-09', '2020-09-13', 'Touring');
INSERT INTO leave_request VALUES (5, 2, 1, '2020-12-24', '2020-12-25', 'Acara keluarga');

DROP TABLE IF EXISTS travel_type;
CREATE TABLE travel_type (
	id bigint,
	name varchar(50),
	travel_fee integer
);

INSERT INTO travel_type VALUES (1, 'In Indonesia', 200000);
INSERT INTO travel_type VALUES (2, 'Out Indonesia', 350000);

DROP TABLE IF EXISTS travel_request;
CREATE TABLE travel_request (
	id bigint,
	employee_id bigint,
	travel_type_id bigint,
	start_date date,
	end_date date
);

INSERT INTO travel_request VALUES (1, 1, 1, '2020-07-07', '2020-07-08');
INSERT INTO travel_request VALUES (2, 1, 1, '2020-08-07', '2020-08-08');
INSERT INTO travel_request VALUES (3, 2, 2, '2020-04-04', '2020-04-07');

DROP TABLE IF EXISTS travel_settlement;
CREATE TABLE travel_settlement (
	id bigint,
	travel_request_id bigint,
	item_name varchar(100),
	item_cost integer
);

INSERT INTO travel_settlement VALUES (1, 1, 'Tiket travel Do-Car berangkat', 165000);
INSERT INTO travel_settlement VALUES (2, 1, 'Hotel', 750000);
INSERT INTO travel_settlement VALUES (3, 1, 'Tiket travel Do-Car pulang', 165000);
INSERT INTO travel_settlement VALUES (4, 2, 'Tiket pesawat berangkat', 750000);
INSERT INTO travel_settlement VALUES (5, 2, 'Hotel', 650000);
INSERT INTO travel_settlement VALUES (6, 2, 'Tiket pesawat pulang', 1250000);
INSERT INTO travel_settlement VALUES (7, 3, 'Tiket pesawat berangkat', 4750000);
INSERT INTO travel_settlement VALUES (8, 3, 'Hotel', 6000000);
INSERT INTO travel_settlement VALUES (9, 3, 'Tiket pesawat pulang', 4250000);

DROP TABLE IF EXISTS position;
CREATE TABLE position (
	id bigint,
	name varchar(100)
);

INSERT INTO position VALUES (1, 'Direktur');
INSERT INTO position VALUES (2, 'General Manager');
INSERT INTO position VALUES (3, 'Manager');
INSERT INTO position VALUES (4, 'Staff');

DROP TABLE IF EXISTS employee_position;
CREATE TABLE employee_position (
	id bigint,
	employee_id bigint,
	position_id bigint
);

INSERT INTO employee_position VALUES (1, 5, 1);
INSERT INTO employee_position VALUES (2, 4, 2);
INSERT INTO employee_position VALUES (3, 3, 3);
INSERT INTO employee_position VALUES (4, 2, 4);
INSERT INTO employee_position VALUES (5, 1, 4);

DROP TABLE IF EXISTS department;
CREATE TABLE department (
	id bigint,
	name varchar(100)
);

INSERT INTO department VALUES (1, 'Recruitment');
INSERT INTO department VALUES (2, 'Sales');
INSERT INTO department VALUES (3, 'Human Resource');
INSERT INTO department VALUES (4, 'General Affair');

DROP TABLE IF EXISTS family;
CREATE TABLE family (
	id bigint, 
	biodata_id bigint, 
	name varchar(100), 
	status varchar(50)
);

INSERT INTO family VALUES (1, 3, 'Azka Fadlan Zikrullah', 'Suami');
INSERT INTO family VALUES (2, 3, 'Primrose Everdeen', 'Anak');
INSERT INTO family VALUES (3, 5, 'Jaka Samudera Buana', 'Suami');
INSERT INTO family VALUES (4, 5, 'Friska Davira', 'Anak');
INSERT INTO family VALUES (5, 5, 'Harum Indah Az Zahra', 'Anak');
INSERT INTO family VALUES (6, 6, 'Adya Pratama Yuda', 'Suami');

SELECT * from biodata;
SELECT * from contact_person;
SELECT * from department;
SELECT * from employee;
SELECT * from employee_leave;
SELECT * from employee_position;
SELECT * from family;
SELECT * from leave;
SELECT * from leave_request;
SELECT * from position;
SELECT * from travel_request;
SELECT * from travel_settlement;
SELECT * from travel_type;

SELECT CONCAT(biodata.first_name,' ',biodata.last_name) as name,
employee_leave.regular_quota,
employee_leave.regular_quota - sum(leave_request.end_date-leave_request.start_date) as sisa_cuti
FROM biodata right JOIN employee on biodata.id=employee.biodata_id
full outer join employee_leave on employee.id=employee_leave.employee_id
full outer join leave_request on employee.id=leave_request.employee_id
full outer join leave on leave_request.leave_id = leave.id
GROUP BY biodata.first_name,biodata.last_name,employee_leave.regular_quota;


--no.1
select biodata.* from biodata
inner join employee on biodata.id = employee.biodata_id
inner join employee_position on employee.id=employee_position.employee_id
inner join position on employee_position.position_id=position.id
where employee_position.position_id is not null
order by biodata.id;

--no.2
select employee.nip, biodata.first_name,
CASE 
when employee_leave.regular_quota-sum(leave_request.end_date-leave_request.start_date) >0
	then employee_leave.regular_quota-sum(leave_request.end_date-leave_request.start_date)
	else 0
end as "Cuti Diambil"
from biodata left join employee on biodata.id=employee.biodata_id
left  join employee_leave on employee.id=employee_leave.employee_id
left  join leave_request on employee.id=leave_request.employee_id
group by employee.nip,biodata.first_name,employee_leave.regular_quota
order by employee.nip;

--no.3
select CONCAT(biodata.first_name,' ',biodata.last_name) as "nama lengkap",
position.name,extract(year from AGE(biodata.dob)) as "usia",
case 
when count(family.status) >0 then count(family.status)
else 0
end as "Jumlah Anak"
from biodata left join employee on biodata.id=employee.biodata_id
full outer join family on biodata.id=family.biodata_id
right join employee_position on employee.id= employee_position.employee_id
right join position on employee_position.position_id=position.id 
group by biodata.first_name,biodata.last_name,position.name,biodata.dob
order by "usia";

--no.4
select concat(biodata.first_name,' ',biodata.last_name)as "Fullname",employee.nip,
employee.status,employee.salary
from biodata inner join employee on biodata.id=employee.biodata_id
where biodata.pob='Jakarta' and biodata.address not like('%Jakarta');

--no.5
select employee.status,count(*) as "Total" from employee
group by employee.status;

--no.6
select concat(biodata.first_name,' ',biodata.last_name)as "Fullname",
travel_type.name as "Jenis Pekerjaan", 
travel_request.start_date,travel_request.end_date,
travel_type.travel_fee*(sum(travel_request.end_date-travel_request.start_date)) as"total pengeluaran"
from biodata inner join employee on biodata.id=employee.biodata_id
inner join travel_request on employee.id=travel_request.employee_id
inner join travel_type on travel_request.travel_type_id=travel_type.id
group by biodata.first_name,biodata.last_name,travel_type.name,travel_request.start_date,travel_request.end_date,
travel_type.travel_fee
order by "Fullname";

--no.7
--dengan menambah table
create table temp_posisi(
	name varchar(250) not null,
	departemen varchar(250) not null
);

insert into temp_posisi
values('Raya Indriyani','Sales'),
      ('Rere Wulandari','Human Resource');

select * from temp_posisi;

-- dengan update table
update biodata 
set id=2
where biodata.first_name='Raya';

update biodata 
set id=3
where biodata.first_name='Rere';

update biodata 
set id=7
where biodata.first_name='Bunga';

select concat(biodata.first_name,' ',biodata.last_name) as "Nama Lengkap",
department.name as "Department"
from biodata inner join department on biodata.id=department.id
where biodata.first_name='Raya' or biodata.first_name='Rere';

--no.8
select concat(biodata.first_name,' ',biodata.last_name) as "Nama Lengkap",
case when biodata.marital_status=False then 'belum'
else 'sudah'
end as "Status Pernikahan",
case 
when family.name is null then 'Belum Menikah'
else family.name
end as name,
case
when family.status is null then 'Belum menikah'
else family.status
end as "Status"
from biodata inner join family on biodata.id=family.biodata_id
where family.status='Anak';

--No.9
select biodata.* from biodata left join employee on 
biodata.id=employee.biodata_id
where employee.id is null;

--No.10
select count(*)as "Jumlah Karyawan"
from biodata where extract(YEAR from dob) between 1991 and 1992;
