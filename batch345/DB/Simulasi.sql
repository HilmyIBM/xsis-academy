CREATE DATABASE db_ptxa

CREATE TABLE tb_biodata(
    id BIGINT PRIMARY KEY NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    dob DATE,
    pob VARCHAR(50),
    alamat VARCHAR(255),
    gender VARCHAR(1)
);

CREATE TABLE tb_employee(
    id BIGINT PRIMARY KEY NOT NULL,
    biodata_id BIGINT,
    nip VARCHAR(5),
    "status" VARCHAR(10),
    join_date DATE,
    salary DECIMAL(10,0)
);

CREATE TABLE tb_cp(
    id BIGINT,
    biodata_id BIGINT,
    tipe VARCHAR(5),
    contact VARCHAR(100)
);

CREATE TABLE tb_leave(
    id BIGINT,
    tipe VARCHAR(10),
    nama VARCHAR(100)
)

CREATE TABLE tb_employee_leave(
    id INTEGER,
    employee_id INTEGER,
    periode VARCHAR(4),
    regular_quota INTEGER
)

CREATE TABLE tb_leave_req(
    id BIGINT,
    employee_id INTEGER,
    leave_id BIGINT,
    tgl_mulai DATE,
    tgl_selesai DATE,
    reason VARCHAR(255)
)

INSERT INTO tb_biodata(id, first_name, last_name, dob, pob, alamat, gender) VALUES
    (1, 'soraya', 'rahayu', '1990-12-22', 'Bali', 'Jl. Raya Kuta, Bali', 'P'),
    (2, 'hanum', 'danuary', '1990-01-02', 'Bandung', 'Jl. Berkah Ramadhan, Bandung, Bali', 'P'),
    (3, 'melati', 'marcelia', '1991-03-03', 'Jakarta', 'Jl. Mawar 3, Brebes', 'P'),
    (4, 'farhan', 'Djokrowidodo', '1989-10-11', 'Jakarta', 'Jl. Bahari Raya, Solo', 'L');

INSERT INTO tb_employee(id, biodata_id, nip, "status", join_date, salary) VALUES
    (1,1, 'XA001', 'Permanen', '2015-11-01 00:00:00.000', 12000000),
    (2,2, 'XA002', 'Kontrak', '2017-01-02 00:00:00.000', 10000000),
    (3,3, 'XA003', 'Kontrak', '2018-08-19 00:00:00.000', 10000000);

INSERT INTO tb_cp(id, biodata_id, tipe, contact) VALUES
    (1, 1, 'MAIL', 'soraya.rahayu@gmail.com'),
    (2, 1, 'PHONE', '085612345678'),
    (3, 2, 'MAIL', 'hanum.danuary@gmail.com'),
    (4, 2, 'PHONE', '081312345678'),
    (5, 2, 'PHONE', '087812345678'),
    (6, 3, 'MAIL', 'melati.marcelia@gmail.com');

INSERT INTO tb_leave(id, tipe, nama) VALUES
    (1, 'Regular', 'Cuti Tahunan'),
    (2, 'Khusus', 'Cuti Menikah'),
    (3, 'Khusus', 'Cuti Haji & Umroh'),
    (4, 'Khusus', 'Melahirkan');

INSERT INTO tb_employee_leave(id, employee_id, periode, regular_quota) VALUES
    (1,1,'2021', 16),
    (2,2,'2021', 12),
    (3,3,'2021', 12);

INSERT INTO tb_leave_req(id, employee_id, leave_id, tgl_mulai, tgl_selesai, reason) VALUES
    (1,1,1, '2021-10-10', '2021-10-12', 'Liburan'),
    (2,1,1, '2021-11-12', '2021-11-15', 'Acara Keluarga'),
    (3,2,2, '2021-05-05', '2021-05-07', 'Menikah'),
    (4,2,1, '2021-09-09', '2021-09-13', 'Touring'),
    (5,2,1, '2021-12-20', '2021-12-23', 'Acara Keluarga');

-- No. 1
SELECT MIN(join_date)
FROM tb_employee

-- No. 2
SELECT te.nip,
CONCAT(first_name, ' ', last_name),
tgl_mulai,
CONCAT(EXTRACT(DAY FROM AGE(tgl_selesai , tgl_mulai))+1, ' Hari') AS lama_cuti
FROM tb_employee te
JOIN tb_biodata tb ON tb.id = te.biodata_id
JOIN tb_leave_req tlr ON te.id = tlr.employee_id
WHERE tgl_selesai > '2021-12-22'

-- No. 3
SELECT te.nip,
CONCAT(first_name, ' ', last_name) as full_name,
COUNT(tlr.employee_id)
FROM tb_employee te
JOIN tb_biodata tb ON tb.id = te.biodata_id
JOIN tb_leave_req tlr ON te.id = tlr.employee_id
GROUP BY te.nip, full_name
HAVING COUNT(tlr.employee_id) > 2

-- No. 4
SELECT nip, 
CONCAT(first_name, ' ', last_name) AS full_name,
tel.regular_quota,
tc.lama_cuti,
(regular_quota - tc.lama_cuti) AS sisa_cuti
FROM tb_employee te
JOIN (
    SELECT employee_id, SUM(EXTRACT(DAY FROM AGE(tgl_selesai,tgl_mulai))+1) as lama_cuti
    FROM tb_leave_req
    WHERE leave_id = 1
    GROUP BY employee_id) 
AS tc ON tc.employee_id = te.id
JOIN tb_employee_leave tel ON tel.employee_id = te.id
JOIN tb_biodata tb ON tb.id = te.biodata_id

SELECT employee_id, SUM(EXTRACT(DAY FROM AGE(tgl_selesai,tgl_mulai))) as lama_cuti
FROM tb_leave_req
WHERE leave_id = 1
GROUP BY employee_id

-- No. 5
SELECT nip, 
CONCAT(first_name, ' ', last_name) as full_name, 
EXTRACT(YEAR FROM AGE('2021-12-22', join_date)), 
(salary*1.5) as Bonus,
(salary + salary*1.5) as Total_gaji
FROM tb_employee te
JOIN tb_biodata tb ON tb.id = te.biodata_id
WHERE EXTRACT(YEAR FROM AGE('2021-12-22', join_date)) > 5;

-- No. 6
SELECT nip,
CONCAT(first_name, ' ', last_name) as full_name, 
dob,
CONCAT(EXTRACT(YEAR FROM AGE('2021-12-22', dob)), ' Tahun') as umur,
CASE
    WHEN EXTRACT(DAY FROM dob) = 22 AND EXTRACT(MONTH FROM dob) = 12
    THEN 0.05*salary
    ELSE 0
END AS Bonus,
CASE
    WHEN EXTRACT(DAY FROM dob) = 22 AND EXTRACT(MONTH FROM dob) = 12
    THEN 0.05*salary + salary
    ELSE salary
END AS total_salary
FROM tb_employee te
JOIN tb_biodata tb ON tb.id = te.biodata_id

-- No. 7
SELECT nip, 
CONCAT(first_name, ' ', last_name) as full_name, 
dob,
CONCAT(EXTRACT(YEAR FROM AGE('2021-12-22', dob)), ' Tahun') as umur
FROM tb_biodata tb
LEFT JOIN tb_employee te ON te.biodata_id = tb.id
ORDER BY tb.dob DESC

-- No. 8
SELECT nip, 
CONCAT(first_name, ' ', last_name) as full_name
FROM tb_employee te
LEFT JOIN tb_leave_req tlq ON tlq.employee_id = te.id
JOIN tb_biodata tb ON te.biodata_id = tb.id
WHERE tlq.id IS NULL

-- No. 9
SELECT CONCAT(first_name, ' ', last_name) AS nama_lengkap,
tl.tipe,
CONCAT(EXTRACT(DAY FROM AGE(tgl_selesai , tgl_mulai))+1, ' Hari') AS lama_cuti,
tc.contact
FROM tb_employee te
JOIN (SELECT biodata_id, contact FROM tb_cp WHERE tipe LIKE 'PHONE')  AS tc ON tc.biodata_id = te.biodata_id
JOIN tb_biodata tb ON tb.id = te.biodata_id
JOIN tb_leave_req tlr ON te.id = tlr.employee_id
JOIN tb_leave tl ON tl.id = tlr.leave_id
WHERE tgl_selesai > '2021-12-22'
GROUP BY nama_lengkap, tl.tipe, lama_cuti, tc.contact

SELECT biodata_id, contact FROM tb_cp WHERE tipe LIKE 'PHONE'

-- No. 10
SELECT CONCAT(first_name, ' ', last_name) AS nama_lengkap
FROM tb_biodata tb 
LEFT JOIN tb_employee te ON te.biodata_id = tb.id
WHERE te.biodata_id IS NULL

-- No. 11
CREATE VIEW view_employee_data AS
SELECT CONCAT(first_name, ' ', last_name) AS nama_lengkap,
dob,
pob,
"status",
salary
FROM tb_employee te
JOIN tb_biodata tb ON te.biodata_id = tb.id

SELECT * FROM view_employee_data

-- No. 12
SELECT reason, COUNT(reason)
FROM tb_leave_req tlr
GROUP BY reason
ORDER BY COUNT(reason) DESC
LIMIT 1


SELECT reason, COUNT(reason)
FROM tb_leave_req tlr
GROUP BY reason
HAVING MAX(SELECT COUNT(reason) FROM tb_leave_req GROUP BY reason) = COUNT(reason)


SELECT tlr.leave_id, MAX(tlr.leave_id)
FROM tb_leave_req tlr
GROUP BY tlr.leave_id