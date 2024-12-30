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