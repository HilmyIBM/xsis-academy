CREATE TABLE SALESPERSON (
	ID SERIAL PRIMARY KEY NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	BOD DATE NOT NULL,
	SALARY DECIMAL(18,2) NOT NULL
);

CREATE TABLE ORDERS (
	ID SERIAL PRIMARY KEY NOT NULL,
	ORDER_DATE DATE NOT NULL,
	CUST_ID INTEGER NOT NULL,
	SALESPERSON_ID INTEGER NOT NULL,
	AMOUNT DECIMAL(18,2) NOT NULL,
	CONSTRAINT fk_salesperson FOREIGN KEY (SALESPERSON_ID) REFERENCES SALESPERSON (ID)
);

INSERT INTO SALESPERSON (NAME, BOD, SALARY)
VALUES
	('Abe', '1988-09-11', 140000),
	('Bob', '1978-09-11', 44000),
	('Chris', '1983-09-11', 40000),
	('Dan', '1980-09-11', 52000),
	('Ken', '1977-09-11', 115000),
	('Joe', '1990-09-11', 38000);

SELECT * FROM SALESPERSON;

INSERT INTO ORDERS (ORDER_DATE, CUST_ID, SALESPERSON_ID, AMOUNT)
VALUES
	('2020-08-02', 4, 2, 540),
	('2021-01-22', 4, 5, 1800),
	('2019-07-14', 9, 1, 460),
	('2018-01-29', 7, 2, 2400),
	('2021-02-03', 6, 4, 600),
	('2020-03-02', 6, 4, 720),
	('2021-05-06', 9, 4, 150);

SELECT * FROM ORDERS;

--a
SELECT s."name", count(o.*) AS total_order
FROM salesperson s
INNER JOIN orders o ON s.id = o.salesperson_id 
GROUP BY s."name" 
HAVING count(o.*) > 1; 

--b
SELECT s."name", sum(o.amount) AS total_amount 
FROM salesperson s 
INNER JOIN orders o ON s.id = o.salesperson_id 
GROUP BY s."name" 
HAVING sum(o.amount) > 1000; 

--c
SELECT s."name", EXTRACT(YEAR FROM AGE('2025-01-02', s."bod")) AS umur, s."salary", sum(o.amount) as total_amount
FROM salesperson s 
INNER JOIN orders o ON s.id = o.salesperson_id
WHERE EXTRACT (YEAR FROM o.order_date) >= 2020
GROUP BY s."name", s."bod", s."salary";

--d
SELECT s."name", avg(o.amount) AS avg_amount
FROM salesperson s
INNER JOIN orders o ON s.id = o.salesperson_id
GROUP BY s."name" 
ORDER BY avg_amount DESC;

--e
SELECT s."name", count(o.salesperson_id) AS total_order, sum(o.amount) AS total_amount,
CASE
	WHEN count(o.salesperson_id) > 2 AND sum(o.amount) > 1000 THEN (s.salary * 0.3)
	ELSE (0)
END AS bonus
FROM salesperson s
INNER JOIN orders o ON s.id = o.salesperson_id 
GROUP BY s."name", s.salary;

--f
SELECT s.*
FROM salesperson s 
LEFT JOIN orders o ON s.id = o.salesperson_id 
WHERE salesperson_id IS NULL;

--g
SELECT s."name", s.salary AS gaji_lama, (s.salary*0.02) AS potongan_gaji, (s.salary - (s.salary*0.02)) AS gaji_baru
FROM salesperson s
LEFT JOIN orders o ON s.id = o.salesperson_id 
WHERE salesperson_id IS NULL;