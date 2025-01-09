SELECT * FROM biodata;
SELECT * FROM employee;
SELECT * FROM contact_person;
SELECT * FROM leave;
SELECT * FROM employee_leave;
SELECT * FROM leave_request;
SELECT * FROM travel_type;
SELECT * FROM travel_request;
SELECT * FROM travel_settlement;
SELECT * FROM position;
SELECT * FROM employee_position;
SELECT * FROM department;
SELECT * FROM family;

-- 1 Kelar
SELECT B.*
FROM biodata B
INNER JOIN employee E ON B.id = E.biodata_id
INNER JOIN employee_position EP ON E.id = EP.employee_id
INNER JOIN position P ON EP.position_id = P.id
ORDER BY id;




-- 2 Kelar (Out of duration)
-- Hitung berapa hari cuti yang sudah diambil masing-masing karyawan,
-- Out NIP, First Name, Cuti Diambil
SELECT E.NIP, B.first_name, COUNT(LR.*) as cuti_diambil
FROM biodata B
INNER JOIN employee E ON B.id = E.biodata_id
INNER JOIN leave_request LR ON E.id = LR.employee_id
GROUP BY E.NIP, E.id, B.first_name
ORDER BY E.id;

--3
--Tampilkan fullname, jabatan, usia, dan jumlah anak dari masing-masing karyawan saat ini (kalau tidak ada anak tulis 0 (nol) atau "-" saja),

SELECT 
  CONCAT(B.first_name + " " + B.last_name) as nama_lengkap,
  P.name as jabatan,
  AGE(EXTRACT YEAR FROM(B.dob)) as usia
FROM biodata B
INNER JOIN (
  SELECT F.id, B.id
  FROM biodata B
  INNER JOIN family as F ON B.id = F.biodata_id
)

-- 4 Kelar
SELECT
  CONCAT(B.first_name, ' ', B.last_name) as nama_lengkap,
  E.nip,
  E.status,
  E.salary
FROM biodata B
INNER JOIN employee E ON B.id = E.biodata_id
WHERE B.pob = 'Jakarta' AND B.address NOT LIKE '%Jakarta';


-- 5 Kelar
SELECT status, COUNT(status) as jumlah_karyawan
FROM employee
GROUP BY status;


-- 6
-- Tampilkan nama karyawan, jenis perjalanan dinas, tanggal perjalanan dinas, dan total pengeluarannya selama perjalanan dinas tersebut,
-- Nama Lengkap, Jenis Perjalanan (In Indonesia..), Start Date, End Date, Total Pengeluaran


SELECT 
   CONCAT(B.first_name, ' ', B.last_name) as nama_lengkap,
   TT.name,
   TR.start_date,
   TR.end_date
FROM biodata B 
INNER JOIN employee E ON B.id = E.biodata_id
INNER JOIN travel_request TR ON E.id = TR.employee_id
INNER JOIN travel_type TT ON TR.travel_type_id = TT.id
GROUP BY TR.id, E.id, B.first_name, B.last_name, TT.name, TR.start_date, TR.end_date
ORDER BY E.id;

-- 7 
-- Tampilkan data seperti berikut,
-- NAMA LENGKAP   --   DEPARTMENT --
-- Raya Indriyani        Sales    --
-- Rere Wulandari        Human Resource --


ALTER TABLE employee_position
ADD COLUMN department_id INTEGER;


SELECT
FROM biodata B
INNER JOIN employee E ON B.id = E.biodata_id
INNER JOIN 

-- 8
-- Buatlah query untuk menampilkan nama lengkap karyawan, status pernikahan, nama pasangan(suami/istri) yang sudah memiliki anak
SELECT 
  CONCAT(B.first_name, ' ', B.last_name) as nama_lengkap,
  B.marital_status,
  F.name
FROM biodata B
INNER JOIN family F ON B.id = F.biodata_id
GROUP BY B.first_name, B.last_name, B.marital_status, F.name, F.status;
HAVING B.marital_status <> '0' AND F.status NOT 'Anak';

-- 9 Kelar
SELECT B.*
FROM biodata B
LEFT JOIN employee E ON B.id = E.biodata_id
WHERE E.id IS NULL;

-- 10 Kelar

SELECT COUNT(B.*) as jumlah_karyawan
FROM biodata B
LEFT JOIN employee E ON B.id = E.biodata_id
WHERE E.id IS NOT NULL AND EXTRACT(YEAR FROM(B.dob)) >= 1991 AND EXTRACT(YEAR FROM(B.dob)) <= 1992;





