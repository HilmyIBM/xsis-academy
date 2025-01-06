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

CREATE VIEW vw_tgl_cuti 
AS SELECT
leave_request.employee_id,
leave_request.start_date,
leave_request.end_date
FROM leave_request; 

SELECT * FROM biodata;
SELECT * FROM contact_person;
SELECT * FROM employee;
SELECT * FROM employee_leave;
SELECT * FROM leave;
SELECT * FROM leave_request;


--1. Menampilkan karyawan yang pertama kali masuk.
SELECT concat(B.first_name,' ' , B.last_name)  full_name, E.join_date
FROM biodata B
INNER JOIN employee  E on B.id = E.biodata_id
ORDER BY E.join_date
LIMIT 1;

--2. Menampilkan daftar karyawan yang saat ini sedang cuti. Daftar berisi nomor_induk, nama, tanggal_mulai, lama_cuti dan keterangan.
SELECT E.nip nomor_induk, concat(B.first_name,' ' , B.last_name)  full_name, LR.start_date tanggal_mulai, LR.end_date - LR.start_date lama_cuti, LR.reason keterangan
FROM biodata B
INNER JOIN leave_request LR  on B.id = LR.employee_id
INNER JOIN employee E on B.id = E.biodata_id
ORDER BY full_name;

--3. Menampilkan daftar karyawan yang sudah mengajukan cuti lebih dari 2 kali. Tampilkan data berisi no_induk, nama, jumlah pengajuan.
SELECT E.nip nomor_induk, concat(B.first_name,' ' , B.last_name)  full_name, count(LR.employee_id)
FROM biodata B
INNER JOIN leave_request LR  on B.id = LR.employee_id
INNER JOIN employee E on B.id = E.biodata_id
GROUP BY nomor_induk, full_name
HAVING count(LR.employee_id) > 2;

--4. Menampilkan sisa cuti tiap karyawan tahun ini, jika di ketahui jatah cuti setiap karyawan tahun ini adalah sesuaidengan quota cuti. tampilan berisi no_induk, nama, quota, cuti yg sudah di ambil dan sisa_cuti
SELECT 
E.nip nomor_induk, 
concat(B.first_name,' ' , B.last_name)  full_name,
EL.regular_quota quota_cuti, 
count(LR.employee_id) cuti_diambil,
EL.regular_quota - count(LR.employee_id) sisa_cuti
FROM biodata B
INNER JOIN leave_request LR  on B.id = LR.employee_id
INNER JOIN employee E on B.id = E.biodata_id
INNER JOIN employee_leave EL on B.id = EL.employee_id
GROUP BY nomor_induk, full_name, quota_cuti;

