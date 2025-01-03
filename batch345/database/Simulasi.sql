CREATE TABLE biodata (
	id BIGINT PRIMARY KEY NOT NULL,
	first_name VARCHAR(20),
	last_name VARCHAR(30),
	dob DATE,
	pob VARCHAR(50),
	address VARCHAR(255),
	gender VARCHAR(1)
);


CREATE TABLE employee (
	id BIGINT PRIMARY KEY NOT NULL,
	biodata_id BIGINT,
	nip VARCHAR(5),
	status VARCHAR(10),
	join_date TIMESTAMP,
	salary DECIMAL(10,0),
	CONSTRAINT fk_employee_biodata FOREIGN KEY (biodata_id) REFERENCES biodata (id)
);


CREATE TABLE contact_person (
	id BIGINT PRIMARY KEY,
	biodata_id BIGINT,
	type VARCHAR(5),
	contact VARCHAR(100),
	CONSTRAINT fk_cp_biodata FOREIGN KEY (biodata_id) REFERENCES biodata (id)
);

CREATE TABLE leave (
	id BIGINT PRIMARY KEY,
	type VARCHAR(10),
	name VARCHAR(100)
);

CREATE TABLE employee_leave (
	id INTEGER PRIMARY KEY,
	employee_id INTEGER,
	period VARCHAR(4),
	regular_quota INTEGER,
	CONSTRAINT fk_eleave_employee FOREIGN KEY (employee_id) REFERENCES employee (id)
);

CREATE TABLE leave_request (
	id BIGINT PRIMARY KEY,
	employee_id BIGINT,
	leave_id BIGINT,
	start_date DATE,
	end_date DATE,
	reason VARCHAR(255),
	CONSTRAINT fk_lrequest_employee FOREIGN KEY (employee_id) REFERENCES employee (id),
	CONSTRAINT fk_lrequest_leave FOREIGN KEY (leave_id) REFERENCES leave (id)
);

INSERT INTO biodata (id, first_name, last_name, dob, pob, address, gender)
VALUES
(1,'soraya','rahayu','1990-12-22','Bali','Jl. Raya Kuta, Bali','P'),
(2,'hanum','danuary','1990-01-02','Bandung','Jl. Berkah Ramadhan, Bandung','P'),
(3,'melati','marcelia','1991-03-03','Jakarta','Jl. Mawar 3, Brebes','P'),
(4,'farhan','Djokrowidodo','1989-10-11','Jakarta','Jl. Bahari Raya, Solo','L');

INSERT INTO employee (id, biodata_id, nip, status, join_date, salary)
VALUES
(1,1,'XA001','Permanen','2015-11-01',12000000),
(2,2,'XA002','Permanen','2017-01-02',10000000),
(3,4,'XA003','Permanen','2018-08-19',10000000);

INSERT INTO contact_person (id, biodata_id, type, contact)
VALUES
	(1, 1, 'MAIL', 'soraya.rahayu@gmail.com'),
	(2, 1, 'PHONE', '085612345678'),
	(3, 2, 'MAIL', 'hanum.danuary@gmail.com'),
	(4, 2, 'PHONE', '081312345678'),
	(5, 2, 'PHONE', '087812345678'),
	(6, 3, 'MAIL', 'melati.marcelia.com');

INSERT INTO leave (id, type, name)
VALUES 
(1, 'Regular', 'Cuti Tahunan'),
(2, 'Khusus', 'Cuti Menikah'),
(3, 'Khusus', 'Cuti Haji & Umroh'),
(4, 'Khusus', 'Cuti Melahirkan');

INSERT INTO employee_leave (id, employee_id, period, regular_quota)
VALUES
	(1, 1, '2021', 16),
	(2, 2, '2021', 12),
	(3, 3, '2021', 12);

INSERT INTO leave_request (id, employee_id, leave_id, start_date, end_date, reason)
VALUES
	(1, 1, 1, '2021-10-10', '2021-10-12', 'Liburan'),
	(2, 1, 1, '2021-11-12', '2021-11-15', 'Acara Keluarga'),
	(3, 2, 2, '2021-05-05', '2021-05-07', 'Menikah'),
	(4, 2, 1, '2021-09-09', '2021-09-13', 'Touring'),
	(5, 2, 1, '2021-12-20', '2021-12-23', 'Acara Keluarga');

-- No 1
SELECT e.*
FROM employee e
ORDER BY e.join_date
LIMIT 1;

-- No 2 berisi nomor_induk, nama, tanggal_mulai, lama_cuti dan keterangan.
SELECT e.nip, CONCAT(b.first_name,' ',b.last_name) AS nama_lengkap, e.join_date, lr.reason
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id
INNER JOIN leave_request lr ON b.id = lr.employee_id
WHERE;
