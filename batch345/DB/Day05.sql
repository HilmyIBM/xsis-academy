CREATE DATABASE db_sales

CREATE TABLE salesperson(
    id SERIAL PRIMARY KEY NOT NULL,
    sales_name VARCHAR(50) NOT NULL,
    bod DATE NOT NULL,
    salary DECIMAL(18,2)
);

CREATE TABLE orders(
    id SERIAL PRIMARY KEY NOT NULL,
    order_date DATE NOT NULL,
    cust_id int,
    salesperson_id int,
    amount DECIMAL(18,2)
);
INSERT INTO salesperson(sales_name, bod, salary) 
VALUES
    ('Abe', '1988-11-09', 140000),
    ('Bob', '1978-11-09', 44000),
    ('Chris', '1983-11-09', 40000),
    ('Dan', '1980-11-09', 52000),
    ('Ken', '1977-11-09', 115000),
    ('Joe', '1990-11-09', 38000);

INSERT INTO orders (ORDER_DATE, CUST_ID, SALESPERSON_ID, AMOUNT) VALUES
('2020-02-08', 4, 2, 540),
('2021-01-22', 4, 5, 1800),
('2019-07-14', 9, 1, 460),
('2018-01-29', 7, 2, 2400),
('2021-02-03', 6, 4, 600),
('2020-03-02', 6, 4, 720),
('2021-05-06', 9, 4, 150);

SELECT * FROM salesperson;
SELECT * FROM orders;

-- No. a
SELECT sp.*, COUNT(o.salesperson_id) as total_order
FROM orders o
JOIN salesperson sp ON sp.id = o.salesperson_id
GROUP BY sp.id
HAVING COUNT(o.salesperson_id) > 1

-- No. b
SELECT sp.*, SUM(o.amount) as total_order_amount
FROM orders o
JOIN salesperson sp ON sp.id = o.salesperson_id
GROUP BY sp.id
HAVING SUM(o.amount) > 1000

-- No. c
SELECT 
    sp.sales_name, 
    EXTRACT(YEAR FROM AGE('2022-12-31', bod)) as umur, 
    salary, 
    SUM(o.amount) AS total_amount
FROM orders o
JOIN salesperson sp ON sp.id = o.salesperson_id
WHERE EXTRACT(YEAR FROM order_date) >= '2020'
GROUP BY sp.sales_name, sp.bod, sp.salary
ORDER BY umur ASC

-- No. d
SELECT sp.id,sp.sales_name, SUM(o.amount)/COUNT(o.amount) as avg_amount
FROM orders o
JOIN salesperson sp ON sp.id = o.salesperson_id
GROUP BY sp.id
ORDER BY avg_amount DESC

-- No. d
SELECT sp.id,sp.sales_name, AVG(o.amount) as avg_amount
FROM orders o
JOIN salesperson sp ON sp.id = o.salesperson_id
GROUP BY sp.id
ORDER BY avg_amount DESC

-- No. e
SELECT sp.id,sp.sales_name, salary, (salary*30/100) AS bonus
FROM orders o
JOIN salesperson sp ON sp.id = o.salesperson_id
GROUP BY sp.id
HAVING SUM(o.amount) > 1000 AND COUNT(o.amount) > 2

-- No. f
SELECT sp.id, sp.sales_name
FROM orders o
RIGHT JOIN salesperson sp ON sp.id = o.salesperson_id
WHERE o.salesperson_id is NULL

-- No. g
SELECT sp.id, sp.sales_name, salary, (salary - salary*2/100) as denda
FROM orders o
RIGHT JOIN salesperson sp ON sp.id = o.salesperson_id
WHERE o.salesperson_id is NULL