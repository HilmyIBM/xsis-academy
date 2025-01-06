-- DB_PTXA.dbo.biodata definition

-- Drop table
-- DROP TABLE DB_PTXA.dbo.biodata;
CREATE TABLE biodata (
	id BIGSERIAL NOT NULL,
	first_name varchar(20),
	last_name varchar(30),
	dob TIMESTAMP,
	pob varchar(50),
	address varchar(255),
	gender varchar(1),
	CONSTRAINT pk_biodata PRIMARY KEY (id)
);

INSERT INTO biodata (first_name, last_name, dob, pob, address, gender) VALUES(N'Soraya', N'Rahayu', '1990-12-22 00:00:00.000', N'Bali', N'Jl. Raya Kuta, Bali', N'P');
INSERT INTO biodata (first_name, last_name, dob, pob, address, gender) VALUES(N'Hanum', N'Danuary', '1990-01-02 00:00:00.000', N'Bandung', N'Jl. Berkah Ramadhan, Bandung', N'P');
INSERT INTO biodata (first_name, last_name, dob, pob, address, gender) VALUES(N'Melati', N'Marcelia', '1991-03-03 00:00:00.000', N'Jakarta', N'Jl. Mawar 3, Breber', N'P');
INSERT INTO biodata (first_name, last_name, dob, pob, address, gender) VALUES(N'Farhan', N'Djokrowidodo', '1989-10-11 00:00:00.000', N'Jakarta', N'Jl. Bahari Raya, Solo', N'L');

select * from biodata;

-- DB_PTXA.dbo.contact_person definition

-- Drop table
-- DROP TABLE DB_PTXA.dbo.contact_person;
CREATE TABLE contact_person (
	id BIGSERIAL NOT NULL,
	biodata_id bigint NOT NULL,
	type varchar(5) NULL,
	contact varchar(100) NULL,
	CONSTRAINT pk_contact_person PRIMARY KEY (id)
);

INSERT INTO contact_person (biodata_id, type, contact) VALUES
(1, N'MAIL', N'soraya.rahayu@gmail.com'),
(1, N'PHONE', N'085612345678'),
(2, N'MAIL', N'Harum.Danuary@gmail.com'),
(2, N'PHONE', N'081312345678'),
(2, N'PHONE', N'087812345678'),
(3, N'MAIL', N'Melati.Marcelia@gmail.com');

select * from contact_person;

-- DB_PTXA.dbo.employee definition

-- Drop table
-- DROP TABLE DB_PTXA.dbo.employee;
CREATE TABLE employee (
	id BIGSERIAL NOT NULL,
	biodata_id bigint,
	nip varchar(5),
	status varchar(10),
	join_date TIMESTAMP,
	salary decimal(10,0),
	CONSTRAINT pk_employee PRIMARY KEY (id)
);

INSERT INTO employee (biodata_id, nip, status, join_date, salary)
VALUES
(1, N'XA001', N'Permanen', '2015-11-01 00:00:00.000', 12000000),
(2, N'XA002', N'Kontrak', '2017-01-02 00:00:00.000', 10000000),
(3, N'XA003', N'Kontrak', '2017-01-02 00:00:00.000', 10000000);

select * from employee;

-- DB_PTXA.dbo.employee_leave definition

-- Drop table
-- DROP TABLE DB_PTXA.dbo.employee_leave;
CREATE TABLE employee_leave (
	id BIGSERIAL NOT NULL,
	employee_id bigint NOT NULL,
	periode varchar(4),
	regular_quota int,
	CONSTRAINT pk_employee_leave PRIMARY KEY (id)
    
);


INSERT INTO employee_leave (employee_id, periode, regular_quota) VALUES
(1, N'2021', 16),
(2, N'2021', 12),
(3, N'2021', 12);

select * from employee_leave;

-- DB_PTXA.dbo.leave definition

-- Drop table
-- DROP TABLE DB_PTXA.dbo.leave;
CREATE TABLE leave (
	id BIGSERIAL NOT NULL,
	type varchar(10) NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT pk_leave PRIMARY KEY (id)
);

INSERT INTO leave (type, name) VALUES
(N'Reguler', N'Cuti Tahunan'),
(N'Khusus', N'Cuti Menikah'),
(N'Khusus', N'Cuti Haji & Umroh'),
(N'Khusus', N'Melahirkan');

select * from leave;

-- DB_PTXA.dbo.leave_request definition

-- Drop table
-- DROP TABLE DB_PTXA.dbo.leave_request;
CREATE TABLE leave_request (
	id BIGSERIAL NOT NULL,
	employee_id bigint NOT NULL,
	leave_id int NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	reason varchar(255),
	CONSTRAINT pk_leave_request PRIMARY KEY (id)
);

INSERT INTO leave_request (employee_id, leave_id, start_date, end_date, reason)
VALUES
(1, 1, '2021-10-10', '2021-10-12', N'Liburan'),
(1, 1, '2021-11-12', '2021-11-15', N'Acara Keluarga'),
(2, 2, '2021-05-05', '2021-05-07', N'Menikah'),
(2, 1, '2021-09-09', '2021-09-13', N'Touring'),
(2, 1, '2021-12-20', '2021-12-23', N'Acara Keluarga');

select * from leave_request;
