CREATE DATABASE "DB_PTXA";

CREATE TABLE biodata(
    id bigserial primary key not null ,
    frist_name varchar(20),
    last_name varchar(30),
    dob date,
    pob varchar(50),
    address varchar(255),
    gender varchar(1)
);

INSERT INTO biodata (frist_name, last_name, dob, pob, address, gender)
VALUES
    ('soraya', 'rahayu', '1990-12-22', 'Bali', 'Jl. Raya Kuta, Bali', 'P'),
    ('hanum', 'danuary', '1990-01-02', 'Bandung', 'Jl. Berkah Ramadhan, Bandung', 'P'),
    ('melati', 'marcelia', '1991-03-03', 'Jakarta', 'Jl. Mawar 3, Brebes', 'P'),
    ('farhan', 'Djokrowidodo', '1989-10-11', 'Jakarta', 'Jl. Bahari Raya, Solo', 'L');

CREATE TABLE employee(
    id bigserial primary key not null ,
    biodata_id bigint,
    nip varchar(5),
    status varchar(10),
    join_date date,
    salary decimal(18,2)
);

INSERT INTO employee (biodata_id, nip, status, join_date, salary)
VALUES
    (1, 'XA001', 'Permanen', '2015-11-01 00:00:00.000', 12000000),
    (2, 'XA002', 'Kontrak', '2017-01-02 00:00:00.000', 10000000),
    (1, 'XA003', 'Kontrak', '2018-08-19 00:00:00.000', 10000000);

CREATE TABLE contact_person(
    id bigserial primary key,
    biodata_id bigint,
    type varchar(50),
    contact varchar(100)
);

INSERT INTO contact_person (biodata_id, type, contact)
VALUES
    (1, 'MAIL', 'soraya.rahayu@gmail.com'),
    (1, 'PHONE', '085612345678'),
    (2, 'MAIL', 'hanum.danuary@gmail.com'),
    (2, 'PHONE', '081312345678'),
    (2, 'PHONE', '087812345678'),
    (3, 'MAIL', 'melati.marcelia@gmail.com');

create table leave (
    id bigserial,
    type varchar(10),
    name varchar(100)
);

insert into leave (type, name)
values
    ('Regular', 'Cuti Tahunan'),
    ('Khusus', 'Cuti Menikah'),
    ('Khusus', 'Cuti Haji & Umroh'),
    ('Khusus', 'Melahirkan');

create table employee_leave(
    id serial,
    employee_id int,
    period varchar(4),
    regular_quota int
);

insert into employee_leave (employee_id, period, regular_quota)
values
    (1, '2021', 16),
    (2, '2021', 12),
    (3, '2021', 12);

create table request(
    id bigserial,
    employee_id int,
    leave_id int,
    start_date date,
    end_date date,
    reason varchar(255)
);

insert into request(employee_id, leave_id, start_date, end_date, reason)
VALUES
    (1, 1, '2021-10-10', '2021-10-12', 'Liburan'),
    (1, 1, '2021-11-12', '2021-11-15', 'Acara Keluarga'),
    (2, 2, '2021-05-05', '2021-05-07', 'Menikah'),
    (2, 1, '2021-09-09', '2021-09-13', 'Touring'),
    (2, 1, '2021-12-20', '2021-12-23', 'Acara Keluarga');


-------------------- PR --------------------

--- NO 1

SELECT
    b.frist_name || ' ' || b.last_name as full_name,
    e.join_date
FROM
    employee e
        INNER JOIN biodata b on e.biodata_id = b.id
LIMIT 1;

--- NO 2

SELECT
    b.frist_name || ' ' || b.last_name as full_name,
    e.nip,
    r.start_date,
    extract(DAYS FROM age(r.end_date, r.start_date)) || ' ' || 'Hari' as lama_cuti,
    r.reason
FROM
    employee e
        INNER JOIN biodata b on e.biodata_id = b.id
        INNER JOIN request r on e.id = r.employee_id;


--- NO 3
SELECT
    e.nip,
    b.frist_name || ' ' || b.last_name as full_name,
    count(r.reason) as jumlah_pengajuan
FROM
    employee e
        INNER JOIN biodata b on e.biodata_id = b.id
        INNER JOIN request r on e.id = r.employee_id
GROUP BY e.nip, b.frist_name, b.last_name
HAVING count(r.reason) > 2;

--- NO 4
SELECT
    e.nip,
    b.frist_name || ' ' || b.last_name as full_name,
    count(r.reason) || ' ' || 'Hari' as jumlah_cuti_yang_diambil,
    el.regular_quota - count(r.reason) || ' ' || 'Hari' as sisa_cuti
FROM
    employee e
        INNER JOIN biodata b on e.biodata_id = b.id
        INNER JOIN request r on e.id = r.employee_id
        INNER JOIN employee_leave el on e.id = el.employee_id
GROUP BY e.nip, b.frist_name, b.last_name, el.regular_quota;

--- NO 5
SELECT
    e.nip,
    b.frist_name || ' ' || b.last_name as full_name,
    extract(YEAR from age(now(), e.join_date)) as lama_bekerja,
    e.salary * 1.5 as bonus,
    (e.salary * 1.5) + e.salary as total_gaji
FROM
    employee e
        INNER JOIN biodata b on e.biodata_id = b.id
WHERE extract(YEAR from age(now(), e.join_date)) > 5;

--- NO 6
SELECT
    e.nip,
    b.frist_name || ' ' || b.last_name as full_name,
    b.dob as tanggal_lahir,
    extract(YEAR from age(now(), b.dob)) as usia,
    CASE WHEN b.dob = now() THEN e.salary * 0.05
    ELSE 0 END bonus,
    CASE WHEN b.dob = now() THEN (e.salary * 0.05) + e.salary
         ELSE e.salary END total_gaji
FROM
    employee e
        INNER JOIN biodata b on e.biodata_id = b.id;

--- NO 7
SELECT
    e.nip,
    b.frist_name || ' ' || b.last_name as full_name,
    b.dob as tanggal_lahir,
    extract(YEAR from age(now(), b.dob)) as usia
FROM
    employee e
        INNER JOIN biodata b on e.biodata_id = b.id
ORDER BY usia ASC;

--- NO 8
SELECT
    b.frist_name || ' ' || b.last_name as full_name
FROM
    employee e INNER JOIN biodata b on b.id = e.biodata_id
    LEFT JOIN request r on e.id = r.employee_id
WHERE r.id IS NULL;

--- NO 9
SELECT
    b.frist_name || ' ' || b.last_name as full_name,
    l.type,
    r.reason,
    extract(DAYS FROM age(r.end_date, r.start_date)) || ' ' || 'Hari' as lama_cuti,
    string_agg(cp.contact, ', ') as cp
--     cp.contact
FROM
    biodata b INNER JOIN contact_person cp on b.id = cp.biodata_id
    INNER JOIN employee e on b.id = e.biodata_id
    INNER JOIN request r on e.id = r.employee_id
    INNER JOIN leave l on r.leave_id = l.id
WHERE cp.type = 'PHONE'
GROUP BY full_name, l.type, lama_cuti, r.reason
ORDER BY full_name asc;

--- NO 10
SELECT
    b.frist_name || ' ' || b.last_name as full_name
FROM
    employee e RIGHT JOIN biodata b on e.biodata_id = b.id
WHERE e.id IS NULL;

--- NO 11
CREATE VIEW vw_employee AS
SELECT
    b.frist_name || ' ' || b.last_name as full_name,
    b.dob,
    b.pob,
    e.status,
    e.salary
FROM
    employee e INNER JOIN biodata b on e.biodata_id = b.id;

SELECT * FROM vw_employee;

--- NO 12
SELECT max(reason_count), freq. reason
FROM (SELECT COUNT(reason) AS reason_count, reason FROM request
         group by reason) freq
group by freq.reason, reason_count
having reason_count = max(reason_count);


-- HAVING count(reason) > 1