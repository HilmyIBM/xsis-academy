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

--No.1
SELECT CONCAT(first_name,' ',last_name) as nama, dob,pob,join_date,nip,employee.status,salary
from employee join biodata ON employee.biodata_id=biodata.id
WHERE join_date=(SELECT join_date from employee ORDER BY join_date ASC LIMIT 1);

--No.2
SELECT nip,CONCAT(first_name,' ',last_name) as nama,start_date,end_date-start_date as lama_cuti,reason
from employee join biodata on employee.biodata_id=biodata.id
join leave_request on employee.id=leave_request.employee_id
WHERE EXTRACT(DAY from start_date) <= 22 and EXTRACT(MONTH from start_date) = 12;

--No.3


