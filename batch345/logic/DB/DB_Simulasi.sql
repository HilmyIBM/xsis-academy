CREATE DATABASE "DB_PTXA"

--create table contact_person
CREATE TABLE "Biodata" (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    dob TIMESTAMP,
    pob VARCHAR(50),
    address VARCHAR(255),
    gender VARCHAR(1)
);

INSERT INTO "Biodata" (id, first_name, last_name, dob, pob, address, gender)
    VALUES 
    (1, 'soraya', 'rahayu', '1990-12-22', 'Bali', 'Jl. Raya Kuta, Bali', 'P'),
    (2, 'hanum', 'danuary', '1990-01-02', 'Bandung', 'Jl. Berkah Ramadhan, Bandung', 'P'),
    (3, 'melati', 'marcelia', '1991-03-03', 'Jakarta', 'Jl. Mawar 3, Brebes', 'P'),
    (4, 'farhan', 'Djokrowidodo', '1989-10-11', 'Jakarta', 'Jl. Bahari Raya, Solo', 'L');

SELECT * FROM "Biodata"

DROP TABLE "Biodata"


CREATE TABLE "Employee"(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    biodata_id BIGINT REFERENCES "Biodata"(id),
    nip VARCHAR(5),
    status VARCHAR(10),
    join_date TIMESTAMP,
    salary DECIMAL(10,0)
);

INSERT INTO "Employee"(id, biodata_id, nip, status, join_date, salary)
    VALUES
    (1, 1, 'XA001', 'Permanen', '2015-11-01 00:00:00.000', 12000000),
    (2, 2, 'XA002', 'Kontrak', '2017-01-02 00:00:00.000', 10000000),
    (3, 3, 'XA003', 'Kontrak', '2018-08-19 00:00:00.000', 10000000);

SELECT * FROM "Employee";

DROP TABLE "Employee"


CREATE TABLE "Contact_Person"(
    id BIGSERIAL,
    biodata_id BIGINT REFERENCES "Biodata"(id), 
    type VARCHAR(5),
    contact VARCHAR(100)
);

INSERT INTO "Contact_Person"(id, biodata_id, type, contact)
    VALUES
    (1, 1, 'MAIL', 'soraya.rahayu@gmail.com'),
    (2, 1, 'PHONE', '085612345678'),
    (3, 2, 'MAIL', 'hanum.danuary@gmail.com'),
    (4, 2, 'PHONE', '081312345678'),
    (5, 2, 'PHONE', '087812345678'),
    (6, 3, 'MAIL', 'melati.marcelia@gmail.com');

SELECT * FROM "Contact_Person";

DROP TABLE "Contact_Person"

CREATE TABLE "Leave"(
    id BIGSERIAL,
    type VARCHAR(10),
    name VARCHAR(100)
);

INSERT INTO "Leave"(id, type, name)
    VALUES
    (1, 'Regular', 'Cuti Tahunan'),
    (2, 'Khusus', 'Cuti Menikah'),
    (3, 'Khusus', 'Cuti Haji & Umroh'),
    (4, 'Khusus', 'Melahirkan');

SELECT * FROM "Leave";

DROP TABLE "Leave"


CREATE TABLE "Employee Leave"(
    id SERIAL,
    employee_id INT REFERENCES "Employee"(id),
    period VARCHAR(4),
    regular_quota INT
);

INSERT INTO "Employee Leave"(id, employee_id, period, regular_quota)
    VALUES
    (1, 1, '2021', 16),
    (2, 2, '2021', 12),
    (3, 3, '2021', 12);

SELECT * FROM "Employee Leave"

DROP TABLE "Employee Leave"


CREATE TABLE "Leave Request"(
    id BIGSERIAL,
    employee_id BIGINT REFERENCES "Employee"(id),
    leave_id BIGINT,
    start_date DATE,
    end_date DATE,
    reason VARCHAR(255)
);

INSERT INTO "Leave Request"(id, employee_id, leave_id, start_date, end_date, reason)
    VALUES
    (1, 1, 1, '2021-10-10', '2021-10-12', 'Liburan'),
    (2, 1, 1, '2021-11-12', '2021-11-15', 'Acara Keluarga'),
    (3, 2, 2, '2021-05-05', '2021-05-07', 'Menikah'),
    (4, 2, 1, '2021-09-09', '2021-09-13', 'Touring'),
    (5, 2, 1, '2021-12-20', '2021-12-23', 'Acara Keluarga');

SELECT * FROM "Leave Request";


--no 1
SELECT CONCAT(B.first_name, ' ', B.last_name) AS name, E.join_date
FROM "Biodata" as B
INNER JOIN "Employee" as E ON B.id = E.biodata_id
GROUP BY  CONCAT(B.first_name, ' ', B.last_name), E.join_date
ORDER BY E.join_date ASC
LIMIT 1;


--no 2
SELECT  E.nip AS nomor_induk, CONCAT(B.first_name, ' ', B.last_name) AS nama, LR.start_date AS tanggal_mulai, EXTRACT (DAYS FROM LR.end_date)-EXTRACT (DAYS FROM LR.start_date) AS lama_cuti, LR.reason as keterangan
FROM "Biodata" as B
INNER JOIN "Employee" as E ON B.id = E.biodata_id
INNER JOIN "Leave Request" as LR ON E.id = LR.employee_id
WHERE LR.start_date <= '2021-12-22' AND LR.end_date >= '2021-12-22';


--no 3
SELECT E.nip AS nomor_induk, CONCAT(B.first_name, ' ', B.last_name) AS nama, COUNT(LR.employee_id) as jumlah_pengajuan
FROM "Biodata" as B
INNER JOIN "Employee" as E ON B.id = E.biodata_id
INNER JOIN "Leave Request" as LR ON E.id = LR.employee_id
GROUP BY  E.nip , CONCAT(B.first_name, ' ', B.last_name)
HAVING COUNT (LR.employee_id) > 2


--no 4
SELECT E.nip AS nomor_induk, CONCAT(B.first_name, ' ', B.last_name) AS nama, EL.regular_quota as quota_cuti, COUNT(LR.employee_id) as cuti_terpakai, EL.regular_quota-COUNT(LR.employee_id) as sisa_cuti
FROM "Biodata" as B
INNER JOIN "Employee" as E ON B.id = E.biodata_id
INNER JOIN "Leave Request" as LR ON E.id = LR.employee_id
INNER JOIN "Employee Leave" as EL ON E.id = EL.employee_id
GROUP BY  E.nip , CONCAT(B.first_name, ' ', B.last_name), E.id, EL.regular_quota


--no 5
SELECT E.nip AS nomor_induk, CONCAT(B.first_name, ' ', B.last_name) AS nama, EXTRACT( YEAR FROM AGE('2021-12-22', E.join_date)), 
    CASE WHEN EXTRACT( YEAR FROM AGE('2021-12-22', E.join_date))>5
    THEN E.salary * 1.5 
    ELSE 0
    END AS bonus
    , 
    CASE WHEN EXTRACT( YEAR FROM AGE('2021-12-22', E.join_date))>5
    THEN E.salary * 2.5 
    ELSE E.salary
    END AS "Total gaji (gaji+bonus)"
FROM "Biodata" as B
INNER JOIN "Employee" as E ON B.id = E.biodata_id   


--no 6
SELECT E.nip AS nomor_induk, CONCAT(B.first_name, ' ', B.last_name) AS nama, B.dob as tanggal_lahir, EXTRACT( YEAR FROM AGE('2021-12-22', B.dob)) as usia, 
    CASE WHEN EXTRACT( MONTH FROM B.dob) == EXTRACT (MONTH FROM '2021-12-22') AND EXTRACT( DAY FROM B.dob) == EXTRACT (DAY FROM '2021-12-22')
    THEN E.salary * 0.05 
    ELSE 0
    END AS bonus
    , 
    CASE WHEN EXTRACT( MONTH FROM B.dob) == EXTRACT (MONTH FROM '2021-12-22') AND EXTRACT( DAY FROM B.dob) == EXTRACT (DAY FROM '2021-12-22')
    THEN E.salary * 1.05 
    ELSE E.salary
    END AS "Total gaji (gaji+bonus)"
FROM "Biodata" as B
INNER JOIN "Employee" as E ON B.id = E.biodata_id   

