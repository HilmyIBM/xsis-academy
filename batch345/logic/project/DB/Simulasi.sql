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