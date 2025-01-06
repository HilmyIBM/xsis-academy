CREATE DATABASE PTXA;

CREATE TABLE biodata(
    id serial PRIMARY KEY NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(39),
    dob DATE,
    pob VARCHAR(50),
    address VARCHAR(255),
    gender VARCHAR(1)
);

CREATE TABLE employee(
    id serial PRIMARY KEY NOT NULL,
    biodata_id BIGINT,
    nip VARCHAR(5),
    status VARCHAR(10),
    join_date DATE,
    salary DECIMAL(10,0)
);

CREATE TABLE contact_person(
    id serial PRIMARY KEY,
    biodata_id BIGINT,
    type VARCHAR(5),
    contact VARCHAR(100)
);

CREATE TABLE leave(
    id serial PRIMARY KEY,
    type VARCHAR(10),
    nama VARCHAR(100)
);

CREATE TABLE employee_leave(
    id serial PRIMARY KEY,
    employee_id INTEGER,
    period VARCHAR(4),
    regular_quota INTEGER
);

CREATE TABLE leave_request(
    id serial PRIMARY KEY,
    employee_id BIGINT,
    leave_id BIGINT,
    start_date DATE,
    end_date DATE,
    reason VARCHAR(255)
);

INSERT INTO biodata(first_name,last_name,dob,pob,address,gender)
VALUES('Soraya','Rahayu','1990-12-22','Bali','Jl. Raya kuta, Bali','P'),
      ('Hanum','Danuary','1990-01-02','Bandung','JL. Berkah Ramadhan, Bandung','P'),
      ('Melati','Marcelia','1991-03-03','Jakarta','Jl. Mawar 3, Brebes','P'),
      ('Farhan','Djokrowidodo','1989-10-11','Jakarta','Jl. Bahari Raya, Solo','L');

INSERT INTO employee(biodata_id,nip,status,join_date,salary)
VALUES(1,'XA001','Permanen','2015-11-01',12000000),
      (2,'XA002','Kontrak','2017-01-02',10000000),
      (3,'XA003','Kontrak','2018-08-19',10000000);

INSERT INTO contact_person(biodata_id,type,contact)
VALUES(1,'Mail','soraya.rahayu@gmail.com'),
      (1,'Phone','085612345678'),
      (2,'Mail','hanum.danuary@gmail.com'),
      (2,'Phone','081312345678'),
      (2,'Phone','087812345678'),
      (3,'Mail','melati.marcelia@gmail.com');

INSERT INTO leave(type,nama)
VALUES('Regular','Cuti Tahunan'),
      ('Khusus','Cuti Menikah'),
      ('Khusus','Cuti Haji & Umroh'),
      ('Khusus','Melahirkan');

INSERT INTO employee_leave(employee_id,period,regular_quota)
VALUES(1,'2021',16),
      (2,'2021',12),
      (3,'2021',12);

INSERT INTO leave_request(employee_id,leave_id,start_date,end_date,reason)
VALUES(1,1,'2021-10-10','2021-10-12','Liburan'),
      (1,1,'2021-11-12','2021-11-15','Acara Keluarga'),
      (2,2,'2021-05-05','2021-05-07','Menikah'),
      (2,1,'2021-09-09','2021-09-13','Touring'),
      (2,1,'2021-12-20','2021-12-23','Acara Keluarga');

/* CREATE VIEW vw_tgl_cuti 
AS SELECT
leave_request.employee_id,
leave_request.start_date,
leave_request.end_date
FROM leave_request;  */

--No.1
SELECT CONCAT(first_name,' ',last_name) as nama, dob,pob,join_date,nip,employee.status,salary
from employee join biodata ON employee.biodata_id=biodata.id
ORDER BY join_date ASC LIMIT 1;

--No.2
SELECT nip,CONCAT(first_name,' ',last_name) as nama,start_date,end_date-start_date as lama_cuti,reason
from employee join biodata on employee.biodata_id=biodata.id
join leave_request on employee.id=leave_request.employee_id
WHERE '2021-12-22' BETWEEN start_date and end_date;

--No.3
SELECT nip,CONCAT(first_name,' ',last_name) as nama, COUNT(leave_request.leave_id) as jumlah_pengajuan
from employee join biodata on employee.biodata_id=biodata.id
join leave_request on employee.id=leave_request.employee_id
GROUP BY employee.nip,nama
HAVING COUNT(leave_request.leave_id) > 2;

--No.4
SELECT employee.nip,CONCAT(first_name,' ',last_name) as nama,
employee_leave.regular_quota as "Quota Regular",
CASE
WHEN SUM(leave_request.end_date-leave_request.start_date) is NULL
THEN '0'
ELSE SUM(leave_request.end_date-leave_request.start_date)
END as "Cuti yg di ambil", 
CASE
    WHEN employee_leave.regular_quota-SUM(leave_request.end_date-leave_request.start_date) is NULL
    THEN employee_leave.regular_quota
    ELSE employee_leave.regular_quota-SUM(leave_request.end_date-leave_request.start_date)
end as "Sisa Cuti"
FROM employee 
LEFT join biodata on employee.biodata_id=biodata.id
LEFT join leave_request on employee.id=leave_request.employee_id
LEFT join  employee_leave on employee.id=employee_leave.employee_id
--WHERE leave_request.leave_id IS NULL 
GROUP BY employee.nip,nama,employee_leave.regular_quota;

--No.5
SELECT employee.nip,
CONCAT(first_name,' ',last_name) as "Full Name", 
2022-EXTRACT(YEAR from employee.join_date) as lama_bekerja,
CASE
    WHEN 2022-EXTRACT(YEAR from employee.join_date) > 5 THEN (1.5 * salary)
    ELSE 0
END as bonus,
(CASE
WHEN 2022-EXTRACT(YEAR from employee.join_date) > 5 THEN (1.5 * salary)
ELSE 0
END)+employee.salary as total_gaji
FROM employee 
join biodata on employee.biodata_id = biodata.id;

--No.6
SELECT nip,
CONCAT(first_name,' ',last_name) as nama,
dob,
2022 - EXTRACT(YEAR from dob) as usia,
CASE
    WHEN EXTRACT(MONTH from dob) = 12 and EXTRACT(DAY from dob) = 22
    THEN (0.05 * salary)
    ELSE 0
END as bonus,
(CASE
    WHEN EXTRACT(MONTH from dob) = 12 and EXTRACT(DAY from dob) = 22
    THEN (0.05 * salary)
    ELSE 0
END) + salary as total_gaji
FROM employee 
join biodata on employee.biodata_id = biodata.id;

--No.7
SELECT nip,
CONCAT(first_name,' ',last_name) as nama,
dob as "Tgl Lahir",
2022 - EXTRACT(YEAR from dob) as "Umur"
FROM employee 
join biodata on employee.biodata_id = biodata.id
ORDER BY "Umur";

--No.8
SELECT employee.nip,
CONCAT(first_name,' ',last_name) as nama,
COUNT(leave_request.leave_id) as cuti_yg_diambil
FROM employee  
LEFT join biodata on employee.biodata_id=biodata.id
LEFT join leave_request on employee.id=leave_request.employee_id
LEFT join  employee_leave on employee.id=employee_leave.employee_id
WHERE leave_request.leave_id IS NULL 
GROUP BY employee.nip,nama,employee_leave.regular_quota;

--No.9
SELECT CONCAT(first_name,' ',last_name) as "Nama Lengkap", 
leave.type as "Jenis Cuti",
leave_request.end_date - leave_request.start_date as "Durasi Cuti", 
contact_person.contact,
leave_request.reason
FROM biodata JOIN employee on biodata.id=employee.biodata_id
JOIN contact_person on biodata.id=contact_person.biodata_id
JOIN leave_request on employee.id=leave_request.employee_id
JOIN leave ON leave_request.leave_id=leave.id
WHERE '2021-12-22' BETWEEN start_date and end_date 
AND contact_person.type='Phone';

--No.10
SELECT biodata.* 
from biodata 
LEFT JOIN employee on biodata.id=employee.biodata_id
WHERE biodata_id is NULL;

--No.11
CREATE VIEW vw_data AS SELECT
CONCAT(biodata.first_name,' ',biodata.last_name) as "Nama Lengkap",
biodata.dob,
biodata.pob,
employee.status,
employee.salary
FROM biodata
INNER JOIN employee on biodata.id=employee.biodata_id;

--No.12
SELECT COUNT(leave_request.leave_id) as jumlah_cuti,leave.type,leave.nama
FROM leave_request 
INNER JOIN leave on leave_request.leave_id=leave.id
GROUP BY leave.type,leave.nama
ORDER BY jumlah_cuti DESC LIMIT 1;

