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

-- No 2. berisi nomor_induk, nama, tanggal_mulai, lama_cuti dan keterangan.
SELECT e.nip, CONCAT(b.first_name,' ',b.last_name) AS nama_lengkap, e.join_date, EXTRACT(DAY FROM AGE(lr.end_date, lr.start_date)) AS lama_cuti, lr.reason
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id
INNER JOIN leave_request lr ON e.id = lr.employee_id
WHERE lr.start_date <= '2021-12-22' AND lr.end_date >= '2021-12-22';

--No 3. Menampilkan daftar karyawan yang sudah mengajukan cuti lebih dari 2 kali. Tampilkan data berisi no_induk, nama, jumlah pengajuan .
SELECT e.nip, CONCAT(b.first_name,' ', b.last_name) AS nama_lengkap, COUNT(lr.employee_id) AS jumlah_pengajuan_cuti
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id 
INNER JOIN leave_request lr ON e.id =lr.employee_id
GROUP BY e.nip, b.first_name, b.last_name
HAVING count(lr.employee_id) > 2;

--No 4. Menampilkan sisa cuti tiap karyawan tahun ini, jika di ketahui jatah cuti setiap karyawan tahun ini adalah sesuaidengan quota cuti. tampilan berisi no_induk, nama, quota, cuti yg sudah di ambil dan sisa_cuti														
SELECT e.nip, CONCAT(b.first_name,' ', b.last_name) AS nama_lengkap, el.regular_quota AS quota,
COALESCE(SUM(EXTRACT(DAY FROM AGE(lr.end_date + 1, lr.start_date))),0) AS cuti_diambil, (el.regular_quota-COALESCE(SUM(EXTRACT(DAY FROM AGE(lr.end_date + 1, lr.start_date))),0)) AS sisa_cuti
FROM employee_leave el 
INNER JOIN employee e ON el.employee_id = e.id 
INNER JOIN biodata b ON e.biodata_id = b.id
LEFT JOIN leave_request lr ON e.id = lr.employee_id AND EXTRACT(YEAR FROM lr.start_date) = '2021'
WHERE el.period = '2021'
GROUP BY e.nip, b.first_name, b.last_name, el.regular_quota
ORDER BY e.nip;

--No 5. Perusahaan akan meberikan bonus bagi karyawan yang sudah bekerja lebih dari 5 tahun sebanyak 1.5 kali gaji. Tampilan No induk, Fullname, Berapa lama bekerja, Bonus, Total Gaji(gaji + bonus)
SELECT e.nip AS no_induk, concat(b.first_name,' ', b.last_name) AS full_name, EXTRACT(YEAR FROM AGE('2021-12-22',e.join_date)) AS lama_kerja,
CASE
	WHEN EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) > 5 THEN (e.salary*1.5)
	ELSE 0
END AS bonus, e.salary + CASE 
	WHEN EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) > 5 THEN (e.salary*1.5)
	ELSE 0
END AS total_gaji
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id;

SELECT 
    e.nip AS no_induk, 
    CONCAT(b.first_name, ' ', b.last_name) AS full_name, 
    EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) AS lama_kerja,
    COALESCE(NULLIF((EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) > 5)::int * (e.salary * 1.5), 0), 0) AS bonus,
    e.salary + 
    COALESCE(NULLIF((EXTRACT(YEAR FROM AGE('2021-12-22', e.join_date)) > 5)::int * (e.salary * 1.5), 0), 0) AS total_gaji
FROM 
    employee e
INNER JOIN 
    biodata b ON e.biodata_id = b.id;

--No 6. Tampilkan nip, nama_lengkap, jika karyawan ada yg berulang tahun di hari ini akan diberikan hadiah bonus sebanyak 5% dari gaji jika tidak ulang tahun maka bonus 0 dan total gaji . Tampilkan No Induk, nama, Tgl lahir , Usia, Bonus, Total Gaji
SELECT e.nip, CONCAT(b.first_name, ' ', b.last_name) AS full_name, EXTRACT(YEAR FROM AGE('2021-12-22',b.dob)) AS usia,
CASE 
	WHEN EXTRACT(DAY FROM AGE('2021-12-22',b.dob)) = 0 AND EXTRACT(MONTH FROM AGE('2021-12-22',b.dob)) = 0 THEN (e.salary*0.5)
	ELSE 0
END AS bonus, e.salary + CASE 
	WHEN EXTRACT(DAY FROM AGE('2021-12-22',b.dob)) = 0 AND EXTRACT(MONTH FROM AGE('2021-12-22',b.dob)) = 0 THEN (e.salary*0.5)
	ELSE 0
END AS total_gaji 
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id;

--No 7. Tampilkan No Induk, nama, Tgl lahir , Usia. Urutkan biodata dari yg paling muda sampai yg tua
SELECT e.nip AS no_induk, CONCAT(b.first_name, ' ', b.last_name) AS full_name, b.dob AS tgl_lahir, EXTRACT(YEAR FROM AGE('2021-12-22',b.dob)) AS usia
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id
ORDER BY usia DESC;

--No 8. Tampikan Karyawan yg belum pernah Cuti
SELECT e.nip, CONCAT(b.first_name, ' ', b.last_name) AS full_name, e.status, e.join_date, e.salary
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id 
LEFT JOIN leave_request lr ON e.id = lr.employee_id
WHERE lr.id IS NULL;

--No 9. Tampikan Nama Lengkap, Jenis Cuti, Durasi Cuti, dan no telp yang sedang cuti
SELECT CONCAT(b.first_name, ' ', b.last_name) AS full_name,  l."type" AS jenis_cuti, EXTRACT (DAY FROM AGE(lr.end_date, lr.start_date)) AS durasi_cuti, cp.contact
FROM employee e
INNER JOIN biodata b ON e.biodata_id = b.id
INNER JOIN leave_request lr ON e.id = lr.employee_id 
INNER JOIN leave l ON lr.leave_id = l.id
INNER JOIN contact_person cp ON b.id = cp.biodata_id
WHERE lr.start_date <= '2021-12-22' AND lr.end_date >= '2021-12-22' AND cp.type='PHONE';

--No 10. Tampilkan nama-nama pelamar yang tidak diterima sebagai karyawan
SELECT b.*
FROM biodata b
LEFT JOIN employee e ON b.id = e.biodata_id
WHERE e.id IS NULL;

--No 11. buatlah sebuah view yg menampilkan data nama lengkap, tgl lahir dan tmpat lahir , status, dan salary
CREATE VIEW vwProfil AS
SELECT CONCAT(b.first_name, ' ', b.last_name) AS full_name, b.dob AS tgl_lahir, b.pob AS tmpt_lahir, e.status, e.salary
FROM biodata b
INNER JOIN employee e ON b.id = e.biodata_id;

SELECT * FROM vwProfil;

--No 12. Tampilkan  alasan cuti yang paling sering diajukan
SELECT lr.reason, COUNT(lr.reason) AS frekuensi
FROM leave_request lr
GROUP BY lr.reason
ORDER BY frekuensi DESC
LIMIT 1;
