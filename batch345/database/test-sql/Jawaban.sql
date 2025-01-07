-- 1. Tampilkan data biodata yang memiliki jabatan/posisi

SELECT
	b.id,
	b.first_name,
	b.last_name,
	b.dob,
	b.pob,
	b.address,
	b.marital_status,
	p."name" AS jabatan
FROM
	employee e
INNER JOIN biodata b ON
	e.biodata_id = b.id
INNER JOIN employee_position ep ON
	ep.employee_id = e.id
INNER JOIN "position" p ON
	p.id = ep.position_id
WHERE
	p."name" IS NOT NULL;
	

-- 2. Hitung berapa hari cuti yang sudah diambil masing-masing karyawan,

SELECT
	e.nip
FROM 
	employee e
INNER JOIN biodata b ON
	e.biodata_id = e.biodata_id
INNER JOIN employee_leave el ON
	el.id = e.id
INNER JOIN leave_request lr ON
	lr.employee_id = el.employee_id
INNER JOIN leave l ON
	l.id = lr.leave_id WHERE l."type" = 'Regular' GROUP BY (e.nip, b.first_name, b.last_name);

SELECT * FROM leave_request lr INNER JOIN leave l ON l.id = lr.leave_id LEFT JOIN employee_leave el ON e;


-- 3. Tampilkan fullname, jabatan, usia, dan jumlah anak dari masing-masing karyawan saat ini (kalau tidak ada anak tulis 0 (nol) atau "-" saja),
SELECT
	CONCAT(
		b.first_name,
		' ',
		b.last_name
	) AS "Nama Lengkap",
	p.name AS "Jabatan",
	AGE(
		now(),
		b.dob
	) AS "Usia",
	SUM(0) AS anak
FROM
	employee e
LEFT JOIN biodata b ON
	b.id = e.biodata_id
LEFT JOIN "family" f ON
	f.biodata_id = b.id
LEFT JOIN employee_position ep ON
	ep.employee_id = e.id
LEFT JOIN "position" p ON
	p.id = ep.position_id
WHERE
	b.first_name != 'Zahra'
GROUP BY
	(
		b.id,
		b.first_name,
		b.last_name,
		p."name",
		b.dob
	)
UNION ALL
SELECT
	CONCAT(
		b.first_name,
		' ',
		b.last_name
	) AS "Nama Lengkap",
	p.name AS "Jabatan",
	AGE(
		now(),
		b.dob
	) AS "Usia",
	COUNT (
		f.status = 'Anak'
	) AS anak
FROM
	employee e
LEFT JOIN biodata b ON
	b.id = e.biodata_id
LEFT JOIN "family" f ON
	f.biodata_id = b.id
LEFT JOIN employee_position ep ON
	ep.employee_id = e.id
LEFT JOIN "position" p ON
	p.id = ep.position_id
WHERE
	f.status = 'Anak'
GROUP BY
	(
		b.id,
		b.first_name,
		b.last_name,
		p."name",
		b.dob,
		f.status
	)
ORDER BY
	"Jabatan" DESC;

-- 4. Tampilkan data karyawan yang lahir di Jakarta namun tidak tinggal di Jakarta,;

SELECT
	concat(
		b.first_name,
		' ',
		b.last_name
	) AS fullname,
	e.nip,
	e.status,
	e.salary
FROM
	employee e
INNER JOIN biodata b ON
	e.biodata_id = b.id
WHERE
	b.pob = 'Jakarta'
	AND b.address NOT LIKE '%Jakarta%';

-- 5. Berapakah jumlah masing-masing karyawan kontrak dan permanen

SELECT e.status, COUNT(e.status) FROM employee e GROUP BY e.status ;

-- 6. Tampilkan nama karyawan, jenis perjalanan dinas, tanggal perjalanan dinas, dan total pengeluarannya selama perjalanan dinas tersebut,

SELECT
	concat(
		b.first_name,
		' ',
		b.last_name
	) AS "Nama Lengkap",
	tt."name" AS "Jenis Perjalanan",
	tr.start_date AS "Start Date",
	tr.end_date AS "End Date",
	SUM (ts.item_cost) AS "Total Pengeluaran"
FROM
	employee e
INNER JOIN travel_request tr ON
	e.id = tr.employee_id
INNER JOIN biodata b ON
	b.id = e.biodata_id
INNER JOIN travel_type tt ON
	tt.id = tr.travel_type_id
INNER JOIN travel_settlement ts ON
	ts.travel_request_id = tr.id
GROUP BY
	(
		b.first_name,
		b.last_name,
		tt."name",
		tr.start_date,
		tr.end_date
	);

-- 7

SELECT
	concat(
		b.first_name,
		' ',
		b.last_name
	) AS "Nama Lengkap",
	d."name" AS "Departemen"
FROM
	employee e
INNER JOIN biodata b ON
	e.biodata_id = b.id
INNER JOIN department d ON
	d.id = e.id + 1
LIMIT 2;
	
-- 8. 

SELECT
	concat(
		b.first_name,
		' ',
		b.last_name
	) AS "Nama Lengkap",
	b.marital_status AS status_pernikahan, 
	f."name" AS "Nama Pasangan"
FROM
	employee e
LEFT JOIN biodata b ON
	b.id = e.biodata_id
LEFT JOIN "family" f ON
	f.biodata_id = b.id
WHERE
	f.status = 'Suami'
GROUP BY
	b.first_name,
	b.last_name,
	b.marital_status,
	f."name" ;

-- 9. Tampilkan data biodata yang tidak memiliki posisi kepegawaian

SELECT
	b.id,
	b.first_name,
	b.last_name,
	b.dob,
	b.pob,
	b.address,
	b.marital_status
FROM
	biodata b
LEFT JOIN employee e ON
	b.id = e.biodata_id
LEFT JOIN employee_position ep ON
	ep.employee_id = e.id
LEFT JOIN "position" p ON
	p.id = ep.position_id
WHERE
	p."name" IS NULL;

-- 10. Hitung ada berapa karyawan yang lahir pada tahun 1991 dan 1992

SELECT
	COUNT(*) AS "Jumlah Karyawan"
FROM
	employee e
LEFT JOIN biodata b ON
	e.biodata_id = b.id
WHERE
	b.dob BETWEEN '1991-01-01' AND '1992-12-31';