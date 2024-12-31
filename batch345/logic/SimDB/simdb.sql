create table biodata(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    dob date,
    pob VARCHAR(50),
    address VARCHAR(255),
    gender VARCHAR(1)
);

CREATE TABLE employee(
    id BIGINT PRIMARY KEY,
    biodata_id BIGINT,
    nip VARCHAR(5),
    status VARCHAR(10),
    join_date date,
    salary DECIMAL(10, 0)
);

CREATE TABLE contact_person(
    id BIGINT PRIMARY KEY,
    biodata_id BIGINT,
    type VARCHAR(5),
    contact VARCHAR(100)
);

CREATE TABLE leave(
    id BIGINT PRIMARY KEY,
    type VARCHAR(10),
    name VARCHAR(100)
);

CREATE TABLE employee_leave(
    id INTEGER PRIMARY KEY,
    employee_id INTEGER,
    period VARCHAR(4),
    regular_quota INTEGER
);

CREATE TABLE leave_request(
    id BIGINT PRIMARY KEY,
    employee_id BIGINT,
    leave_id BIGINT,
    start_date date,
    end_date date,
    reason VARCHAR(255)
);

DROP TABLE biodata;
DROP TABLE employee;
DROP TABLE contact_person;
DROP TABLE leave;
DROP TABLE employee_leave;
DROP TABLE leave_request;

INSERT INTO biodata(id, first_name, last_name, dob, pob, address, gender)
VALUES
    (1, 'soraya', 'rahayu', '1990-12-22', 'Bali', 'Jl. Raya Kuta, Bali', 'P'),
    (2, 'hanum', 'danuary', '1990-01-02', 'Bandung', 'Jl. Berkah Ramadhan, Bandung', 'P'),
    (3, 'melati', 'marcelia', '1990-03-03', 'Jakarta', 'Jl. Mawar 3, Brebes', 'P'),
    (4, 'farhan', 'djokrowidodo', '1990-10-11', 'Jakarta', 'Jl. Bahari Raya, Solo', 'L')
;

INSERT INTO employee (id, biodata_id, nip, status, join_date, salary)
VALUES
    (1, 1, 'XA001', 'Permanen', '2015-11-01 00:00:00.000', 12000000),
    (2, 2, 'XA002', 'Kontrak', '2017-01-02 00:00:00.000', 10000000),
    (3, 3, 'XA003', 'Kontrak', '2018-08-19 00:00:00.000', 10000000)
;

INSERT INTO contact_person(id, biodata_id, type, contact)
VALUES
    (1, 1, 'MAIL', 'soraya.rahayu@gmail.com'),
    (2, 2, 'PHONE', '085612345678'),
    (3, 3, 'MAIL', 'hanum.danuary@gmail.com'),
    (4, 4, 'PHONE', '081312345678'),
    (5, 5, 'PHONE', '087812345678'),
    (6, 6, 'MAIL', 'melati.marcelia@gmail.com')
;

INSERT INTO leave(id, type, name)
VALUES
    (1, 'Regular', 'Cuti Tahunan'),
    (2, 'Khusus', 'Cuti Menikah'),
    (3, 'Khusus', 'Cuti Haji & Umroh'),
    (4, 'Khusus', 'Melahirkan')
;

INSERT INTO employee_leave(id, employee_id, period, regular_quota)
VALUES
    (1, 1, '2021', 16),
    (2, 2, '2021', 12),
    (3, 3, '2021', 12)
;

INSERT INTO leave_request(id, employee_id, leave_id, start_date, end_date, reason)
VALUES
    (1, 1, 1, '2021-10-10', '2021-10-12', 'Liburan'),
    (2, 1, 1, '2021-11-12', '2021-11-15', 'Acara keluarga'),
    (3, 2, 2, '2021-05-05', '2021-05-07', 'Menikah'),
    (4, 2, 1, '2021-09-09', '2021-09-13', 'Touring'),
    (5, 2, 1, '2021-12-20', '2021-12-23', 'Acara keluarga')
;

SELECT * FROM biodata;
SELECT * FROM employee;
SELECT * FROM contact_person;
SELECT * FROM leave;
SELECT * FROM employee_leave;
SELECT * FROM leave_request;

-- 1
SELECT 
    CONCAT(b.first_name, ' ', b.last_name) full_name,
    a.join_date
FROM employee a 
    INNER JOIN biodata b ON a.biodata_id = b.id
ORDER BY a.join_date LIMIT 1;

-- 2
SELECT
    b.nip,
    CONCAT(c.first_name, ' ', c.last_name) full_name,
    a.start_date,
    a.end_date - a.start_date AS lama_cuti,
    a.reason
FROM leave_request a 
    INNER JOIN employee b ON a.employee_id = b.id
    INNER JOIN biodata c ON b.biodata_id = c.id
WHERE DATE('2021-12-22') BETWEEN a.start_date AND a.end_date;

-- 3
SELECT
    b.nip,
    CONCAT(c.first_name, ' ', c.last_name) full_name,
    COUNT(a.employee_id) as jumlah_pengajuan
FROM leave_request a 
    INNER JOIN employee b ON a.employee_id = b.id
    INNER JOIN biodata c ON b.biodata_id = c.id
GROUP BY b.nip, c.first_name, c.last_name, a.employee_id
HAVING COUNT(a.employee_id) > 2;

-- 4
SELECT
    b.nip,
    CONCAT(c.first_name, ' ', c.last_name) full_name,
    d.regular_quota,
    CASE
        WHEN a.employee_id IN (
            SELECT employee_id
            FROM leave_request
            GROUP BY employee_id
        ) THEN d.regular_quota - COUNT(a.employee_id)
        ELSE d.regular_quota 
    END sisa_cuti
FROM leave_request a 
    INNER JOIN employee b ON a.employee_id = b.id
    INNER JOIN biodata c ON b.biodata_id = c.id
    INNER JOIN employee_leave d ON a.leave_id = d.id
;



