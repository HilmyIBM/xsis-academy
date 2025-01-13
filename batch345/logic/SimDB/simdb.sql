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
    (2, 1, 'PHONE', '085612345678'),
    (3, 2, 'MAIL', 'hanum.danuary@gmail.com'),
    (4, 2, 'PHONE', '081312345678'),
    (5, 2, 'PHONE', '087812345678'),
    (6, 3, 'MAIL', 'melati.marcelia@gmail.com')
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
    -- (6, 2, 1, '2021-12-20', '2021-12-23', 'Touring')
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
    e.join_date
FROM employee e 
    INNER JOIN biodata b ON e.biodata_id = b.id
ORDER BY e.join_date LIMIT 1;

-- 2
SELECT
    e.nip,
    CONCAT(b.first_name, ' ', b.last_name) full_name,
    lr.start_date,
    lr.end_date - lr.start_date AS lama_cuti,
    lr.reason
FROM leave_request lr 
    INNER JOIN employee e ON lr.employee_id = e.id
    INNER JOIN biodata b ON e.biodata_id = b.id
WHERE DATE('2021-12-22') BETWEEN lr.start_date AND lr.end_date;

-- 3
SELECT
    e.nip,
    CONCAT(b.first_name, ' ', b.last_name) full_name,
    COUNT(lr.employee_id) as jumlah_pengajuan
FROM leave_request lr 
    INNER JOIN employee e ON lr.employee_id = e.id
    INNER JOIN biodata b ON e.biodata_id = b.id
GROUP BY e.nip, b.first_name, b.last_name, lr.employee_id
HAVING COUNT(lr.employee_id) > 2;

-- 4
SELECT
    e.nip,
    CONCAT(b.first_name, ' ', b.last_name) full_name,
    el.regular_quota,
    SUM(CASE
            WHEN lr.leave_id = 1 
                THEN EXTRACT(DAY FROM AGE(lr.end_date, lr.start_date)) + 1
            ELSE 0
        END) AS cuti_dipakai,
    el.regular_quota - SUM(
            CASE
                WHEN lr.leave_id = 1 
                    THEN EXTRACT(DAY FROM AGE(lr.end_date, lr.start_date)) + 1
                ELSE 0
            END) AS sisa_cuti
FROM employee e
    LEFT JOIN biodata b ON e.biodata_id = b.id
    LEFT JOIN employee_leave el ON e.id = el.employee_id
    LEFT JOIN leave_request lr ON e.id = lr.employee_id
GROUP BY 
    e.nip,
    b.first_name,
    b.last_name,
    el.regular_quota
;

-- 5
SELECT
    e.nip,
    CONCAT(b.first_name, ' ', b.last_name) full_name,
    EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) AS lama_bekerja,
    CASE
        WHEN EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) > 5
            THEN e.salary * 1.5
        ELSE 0
    END bonus,
    e.salary + 
        CASE
            WHEN EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) > 5
                THEN e.salary * 1.5
            ELSE 0
        END total_gaji
FROM employee e
    LEFT JOIN biodata b ON e.biodata_id = b.id;

-- 6
SELECT
    e.nip AS no_induk,
    CONCAT(b.first_name, ' ', b.last_name) AS nama_lengkap,
    b.dob AS tgl_lahir,
    EXTRACT(YEAR FROM AGE(b.dob)) AS usia,
    CASE
        WHEN EXTRACT(MONTH FROM b.dob) = EXTRACT(MONTH FROM DATE('2021-12-22')) 
             AND EXTRACT(DAY FROM b.dob) = EXTRACT(DAY FROM DATE('2021-12-22'))
        THEN e.salary * 0.05
        ELSE 0
    END AS bonus,
    e.salary + 
        CASE
            WHEN EXTRACT(MONTH FROM b.dob) = EXTRACT(MONTH FROM DATE('2021-12-22')) 
                 AND EXTRACT(DAY FROM b.dob) = EXTRACT(DAY FROM DATE('2021-12-22'))
            THEN e.salary * 0.05
            ELSE 0
        END AS total_gaji
FROM employee e
    LEFT JOIN biodata b ON e.biodata_id = b.id;

-- 7
SELECT 
    e.nip AS no_induk,
    CONCAT(b.first_name, ' ', b.last_name) AS nama_lengkap,
    b.dob AS tgl_lahir,
    EXTRACT(YEAR FROM AGE(b.dob)) AS usia
FROM employee e
    LEFT JOIN biodata b ON e.biodata_id = b.id
ORDER BY usia;

-- 8
SELECT
    e.nip,
    CONCAT(b.first_name, ' ', b.last_name) AS nama_lengkap
FROM employee e
    LEFT JOIN biodata b ON e.biodata_id = b.id
    LEFT JOIN leave_request lr ON lr.employee_id = e.id
WHERE lr.employee_id IS NULL;

-- 9
SELECT
    CONCAT(b.first_name, ' ', b.last_name) full_name,
    l.type,
    (lr.end_date - lr.start_date) + 1 AS lama_cuti,
    cp.contact
FROM leave_request lr 
    INNER JOIN employee e ON lr.employee_id = e.id
    INNER JOIN biodata b ON e.biodata_id = b.id
    INNER JOIN contact_person cp ON e.biodata_id = cp.biodata_id
    INNER JOIN leave l on lr.leave_id = l.id
WHERE DATE('2021-12-22') BETWEEN start_date AND end_date
    AND cp.type = 'PHONE';

-- 10
SELECT
    CONCAT(b.first_name, ' ', b.last_name) full_name
FROM employee e
    RIGHT JOIN biodata b ON e.biodata_id = b.id
WHERE e.biodata_id is NULL;

-- 11
CREATE VIEW vw_biodata AS
SELECT 
    CONCAT(b.first_name, ' ', b.last_name) full_name,
    CONCAT(b.dob, ',', b.pob) tgl_lahir_dan_tempat,
    e.status,
    e.salary
FROM employee e
    INNER JOIN biodata b ON e.biodata_id = b.id;

SELECT * FROM vw_biodata;

-- 12
-- SELECT
--     reason,
--     MAX(COUNT(*) AS jumlah_pengajuan)
-- FROM leave_request
-- GROUP BY reason LIMIT 1;

SELECT 
    reason, 
    COUNT(*) AS jumlah_pengajuan
FROM leave_request
GROUP BY reason
HAVING COUNT(*) = (
    SELECT COUNT(*) 
    FROM leave_request 
    GROUP BY reason 
    ORDER BY COUNT(*) DESC 
    LIMIT 1
);

    








