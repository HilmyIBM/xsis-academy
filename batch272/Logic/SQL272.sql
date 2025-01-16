CREATE TABLE position (
	id serial, 
	name varchar(100), 
	PRIMARY KEY(id)
);
INSERT INTO position (name) VALUES 
('Direktur'),
('General Manager'), 
('Manager'), 
('Staff');
SELECT * FROM position;

CREATE TABLE family (
	id serial, 
	biodata_id integer, 
	name varchar(100), 
	status varchar(50),
	PRIMARY KEY (id)
);
INSERT INTO family (biodata_id, name, status) VALUES 
(3,'Azka Fadlan Zikrullah','Suami'),
(3,'Primrose Everdeen','Anak'),
(5,'Jaka Samudera Buana','Suami'),
(5,'Friska Davira','Anak'),
(5,'Harum Indah Az Zahra','Anak'),
(6,'Adya Pratama Yuda','Suami');
SELECT * FROM family;

CREATE TABLE employee_position (
	id serial, 
	employee_id integer, 
	position_id integer, 
	PRIMARY KEY(id)
);
INSERT INTO employee_position (employee_id,position_id) VALUES (5,1),(4,2),(3,3),(2,4),(1,4);
SELECT * FROM employee_position;

CREATE TABLE travel_settlement (
	id serial, 
	travel_request_id integer, 
	item_name varchar(100), 
	item_cost integer, 
	PRIMARY KEY(id)
);
INSERT INTO travel_settlement (travel_request_id,item_name,item_cost) VALUES 
(1,'Tiket travel Do-Car berangkat',165000),
(1,'Hotel',750000),
(1,'Tiket travel Do-Car pulang',165000),
(2,'Tiket pesawat berangkat',750000),
(2,'Hotel',650000),
(2,'Tiket pesawat pulang',125000),
(3,'Tiket pesawat berangkat',4750000),
(3,'Hotel',6000000),
(2,'Tiket pesawat pulang',4250000);
SELECT * FROM travel_settlement;

CREATE TABLE travel_type (
	id serial, 
	name varchar(50), 
	travel_fee integer, 
	PRIMARY KEY(id)
);
INSERT INTO travel_type (name, travel_fee) VALUES 
('In Indonesia',200000),
('Out Indonesia',350000);
SELECT * FROM travel_type;

CREATE TABLE travel_request (
	id serial,
	employee_id integer, 
	travel_type_id integer, 
	start_date date, 
	end_date date, 
	PRIMARY KEY(id)
);
INSERT INTO travel_request (employee_id,travel_type_id,start_date,end_date) VALUES 
(1,1,'2020-07-07','2020-07-08'),
(1,1,'2020-08-07','2020-08-08'),
(2,2,'2020-04-04','2020-04-07');
SELECT * FROM travel_request;

CREATE TABLE employee (
	id serial, 
	biodata_id integer, 
	nip varchar(5), 
	status varchar(10), 
	salary numeric(10), 
	PRIMARY KEY (id)
);
INSERT INTO employee (biodata_id,nip,status,salary) VALUES 
(1,'NX001','Permanen',12000000),
(2,'NX002','Kontrak',15000000),
(4,'NX003','Permanen',13500000),
(5,'NX004','Permanen',12000000),
(6,'NX005','Permanen',17000000);
SELECT * FROM employee;

CREATE TABLE biodata (
	id serial, 
	first_name varchar(30), 
	last_name varchar(30),
	dob varchar(10), 
	pob varchar(50), 
	address varchar(255), 
	marital_status boolean, 
	PRIMARY KEY (id)
);
INSERT INTO biodata (first_name, last_name, dob, pob, address, marital_status) VALUES 
('Raya','Indriyani','1991-01-01','Bali','Jl. Raya Baru, Bali',false), 
('Rere','Wulandari','1992-01-02', 'Bandung', 'Jl. Berkah Ramadhan, Bandung', false),
('Bunga','Maria','1991-03-03','Jakarta', 'Jl. Mawar Semerbak, Bogor', true),
('Natasha','Wulan','1990-04-10','Jakarta','Jl. Mawar Harum, Jakarta', false), 
('Zahra','Aurellia Putri','1991-03-03','Jakarta','Jl. Mawar Semerbak, Bogor',true),
('Katniss','Everdeen','1989-01-12','Jakarta','Jl. Bunga Melati, Jakarta',true);
SELECT * FROM biodata;

CREATE TABLE employee_leave (
	id serial, 
	employee_id integer, 
	period varchar(4), 
	regular_quota integer);
INSERT INTO employee_leave (employee_id, period, regular_quota) VALUES 
(1,'2020',16),
(2, '2020',12),
(1,'2021',16),
(2,'2021',12),
(4,'2021',12),
(5,'2021',12),
(6,'2021',12);
SELECT * FROM employee_leave;

CREATE TABLE leave_request (
	id serial, 
	employee_id integer, 
	leave_id integer, 
	start_date date, 
	end_date date, 
	reason varchar(255), 
	PRIMARY KEY(id)
);
INSERT INTO leave_request (employee_id, leave_id, start_date, end_date, reason) VALUES 
(1,1,'2020-10-10', '2020=10-12', 'Liburan'),
(1,1,'2020-11-12','2020-11-15','Acara Keluarga'),
(2,2,'2020-05-05','2020-05-07','Menikah'),
(2,1,'2020-09-09','2020-09-13','Touring'),
(2,1,'2020-12-24','2020-12-25','Acara Keluarga');
SELECT * FROM leave_request;

CREATE TABLE leave (
	id serial, 
	type varchar(10), 
	name varchar(100), 
	PRIMARY KEY(id)
);
INSERT INTO leave (type, name) VALUES 
('Reguler', 'Cuti Tahunan'),
('Khusus', 'Cuti Menikah'),
('Khusus','Cuti Haji & Umroh'),
('Khusus','Melahirkan');
SELECT * FROM leave;

CREATE TABLE department (
	id serial,
	name varchar(100)
);
INSERT INTO department (name) VALUES 
('Recruitment'),
('Sales'),
('Human Resources'),
('General Affair');
SELECT * FROM department;

CREATE TABLE contact_person (
	id serial, 
	biodata_id integer, 
	type varchar(5), 
	contact varchar(100), 
	PRIMARY KEY(id)
);
INSERT INTO contact_person (biodata_id,type,contact) VALUES 
(1,'MAIL','raya.indriyani@gmail.com'),
(1,'PHONE','085612345678'),
(2,'MAIL','rere.wulandari@gmail.com'),
(2,'PHONE','081312345678'),
(2,'PHONE','087812345678'),
(3,'MAIL','bunga.maria@gmail.com'),
(3,'MAIL','natasha.wulan@gmail.com'),
(5,'MAIL','zahra.putri@gmail.com'),
(6,'MAIL','katniss.everdeen@gmail.com');
SELECT * FROM contact_person;

ALTER TABLE travel_request ADD CONSTRAINT travel_request_fkey FOREIGN KEY (travel_type_id) REFERENCES travel_type(id);
ALTER TABLE leave_request ADD CONSTRAINT leave_request_fkey FOREIGN KEY (leave_id) REFERENCES leave(id);
ALTER TABLE employee_position ADD CONSTRAINT employee_position_fkey FOREIGN KEY (position_id) REFERENCES position (id);
ALTER TABLE travel_settlement ADD CONSTRAINT travel_statement_fkey FOREIGN KEY (travel_request_id) REFERENCES travel_request(id);


-- =====================================================================

SELECT * FROM biodata;
SELECT first_name,last_name,dob,pob FROM biodata;
SELECT first_name,last_name,dob,pob FROM biodata ORDER BY first_name ASC;
SELECT first_name,last_name,dob,pob FROM biodata ORDER BY first_name DESC;
SELECT first_name FROM biodata WHERE first_name='Rere';
SELECT first_name FROM biodata WHERE lower(first_name) LIKE '%ra%';
SELECT first_name FROM biodata WHERE lower(first_name) LIKE 'ra%';
SELECT first_name FROM biodata WHERE lower(first_name) LIKE '%ra';
SELECT concat(first_name,' ',last_name) as nama_lengkap FROM biodata;
SELECT concat(first_name,' ',last_name), dob FROM biodata WHERE dob > '1991-00-00';

SELECT nip,status,salary FROM employee;
SELECT AVG(salary) as gaji_rata2 FROM employee WHERE status='Permanen';
SELECT AVG(salary) as gaji_rata2 FROM employee GROUP BY status;
SELECT MAX(salary) as max_gaji,status FROM employee GROUP BY status;
SELECT MIN(salary) as max_gaji,status FROM employee GROUP BY status;
SELECT COUNT(*) as jumlah FROM employee WHERE salary > 12000000;

SELECT
b.first_name,
e.salary
FROM biodata b, employee e 
WHERE b.id = e.biodata_id

SELECT
b.first_name,
e.salary
FROM biodata b
JOIN employee e ON b.id = e.biodata_id

SELECT
b.first_name,
e.salary
FROM biodata b
LEFT JOIN employee e ON b.id = e.biodata_id

SELECT
b.first_name,
e.salary
FROM biodata b
RIGHT JOIN employee e ON b.id = e.biodata_id

SELECT
b.first_name,
e.salary
FROM biodata b
FULL OUTER JOIN employee e ON b.id = e.biodata_id

SELECT
b.first_name,
e.salary
FROM biodata b
FULL JOIN employee e ON b.id = e.biodata_id

-- SUB QUERY ++++++++++++++++++++++++++++++++
SELECT
b.first_name,
(SELECT salary FROM employee WHERE biodata_id=b.id) as salary
FROM biodata b

SELECT 
b.first_name,
e.status,
p.name,
e.salary
FROM employee_position ep
LEFT JOIN employee e ON e.id=ep.employee_id
LEFT JOIN biodata b ON b.id=e.biodata_id
LEFT JOIN position p ON p.id=ep.position_id

SELECT 
b.first_name,
e.status,
p.name,
e.salary,
el.period,
el.regular_quota
FROM employee_position ep
LEFT JOIN employee e ON e.id=ep.employee_id
LEFT JOIN biodata b ON b.id=e.biodata_id
LEFT JOIN employee_leave el ON e.id=el.employee_id AND el.period='2021'
LEFT JOIN position p ON p.id=ep.position_id
WHERE el.regular_quota IS NOT NULL
--GROUP BY el.period, b.first_name, e.status, p.name, e.salary, el.regular_quota

SELECT 
b.first_name,
e.status,
p.name,
e.salary,
el.period,
el.regular_quota
FROM employee_position ep
LEFT JOIN employee e ON e.id=ep.employee_id
LEFT JOIN biodata b ON b.id=e.biodata_id
LEFT JOIN employee_leave el ON e.id=el.employee_id
LEFT JOIN position p ON p.id=ep.position_id
GROUP BY el.period, b.first_name, e.status, p.name, e.salary, el.regular_quota
ORDER BY b.first_name



SELECT item_name, item_cost, 'Biaya Transportasi' as keterangan 
FROM travel_settlement WHERE item_name LIKE 'Tiket%'
UNION ALL
SELECT item_name, item_cost, 'Biaya Akomodasi' as keterangan 
FROM travel_settlement WHERE item_name LIKE 'Hotel%';

CREATE OR REPLACE VIEW view_settlement AS
SELECT item_name, item_cost, 'Biaya Transportasi' as keterangan 
FROM travel_settlement WHERE item_name LIKE 'Tiket%'
UNION ALL
SELECT item_name, item_cost, 'Biaya Akomodasi' as keterangan 
FROM travel_settlement WHERE item_name LIKE 'Hotel%';

SELECT item_name, cast(item_cost as money), keterangan FROM view_settlement;
SELECT * FROM view_settlement WHERE item_cost < 400000;

CREATE OR REPLACE VIEW view_bonus AS
SELECT 
*,
CASE WHEN status = 'Permanen' THEN 10
	 WHEN status = 'Kontrak' THEN 15
END bonus,
CASE WHEN status = 'Permanen' THEN (salary*10/100)+salary
	 WHEN status = 'Kontrak' THEN (salary*15/100)+salary
END total
FROM employee;

SELECT (5*5),'test' as coltest;
SELECT md5('password');

SELECT * FROM view_bonus;
SELECT COALESCE(1, 2, null, 3, 55, 77, null);
SELECT COALESCE(NULL, NULL, NULL, 3, 8, 12);

CREATE TABLE items (
	ID serial PRIMARY KEY,
	product VARCHAR (100) NOT NULL,
	price NUMERIC NOT NULL,
	discount NUMERIC
);

INSERT INTO items (product, price, discount)
VALUES
	('A', 1000 ,10),
	('B', 1500 ,20),
	('C', 800 ,5),
	('D', 500, NULL);

SELECT
	product,
	(price - discount) AS net_price
FROM
	items;
	
SELECT
	product,
	(price - COALESCE(discount, 0)) AS net_price
FROM
	items;
	
SELECT
	product,
	(
		price - CASE
		WHEN discount IS NULL THEN
			0
		ELSE
			discount
		END
	) AS net_price
FROM
	items;
	
SELECT product, price FROM items WHERE price BETWEEN 1000 AND 2000;
SELECT product, price FROM items WHERE price NOT BETWEEN 1000 AND 2000;

SELECT product, price FROM items WHERE price IN(1000, 1500);
SELECT product, price FROM items WHERE price NOT IN(1000, 1500);
SELECT product, price FROM items WHERE price IN(SELECT price FROM items);

SELECT DISTINCT(status),salary FROM employee;
SELECT status,salary FROM employee GROUP BY status,salary;

SELECT status, COUNT(*) as jumlah FROM employee GROUP BY status;
SELECT status, COUNT(*) as jumlah FROM employee GROUP BY status 
HAVING(COUNT(*) > 1);

CREATE INDEX employee_nip_index ON employee(nip);
SELECT * FROM employee;
EXPLAIN SELECT * FROM employee WHERE nip='NX001';

