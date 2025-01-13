CREATE TABLE sales_person(
    ID SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    bod date,
    salary DECIMAL(18, 2)
);

CREATE TABLE orders(
    ID SERIAL PRIMARY KEY NOT NULL,
    order_date date,
    cust_id NUMERIC,
    sales_person_id NUMERIC,
    amount DECIMAL(18, 2)
);

INSERT INTO sales_person (name, bod, salary)
VALUES
    ('Abe',	'9/11/1988', 140000),
    ('Bob',	'9/11/1978', 44000),
    ('Chris', '9/11/1983', 40000),
    ('Dan',	'9/11/1980', 52000),
    ('Ken',	'9/11/1970', 115000),
    ('Joe',	'9/11/1990', 38000)
;

INSERT INTO orders (order_date, cust_id, sales_person_id, amount)
VALUES
    ('8/2/2020', 4, 2, 540),
    ('1/22/2021', 4, 5, 1800),
    ('7/14/2019', 9, 1, 460),
    ('1/29/2018', 7, 2, 2400),
    ('2/3/2021', 6, 4, 600),
    ('3/2/2020', 6, 4, 720),
    ('5/6/2021', 9, 4, 150)
;

-- 1
SELECT sp.name
FROM orders o 
    JOIN sales_person sp ON o.sales_person_id = sp.id 
GROUP BY sp.name
HAVING COUNT(o.sales_person_id) > 1;

-- 2
SELECT sp.name
FROM orders o 
    JOIN sales_person sp ON o.sales_person_id = sp.id 
GROUP BY sp.name
HAVING SUM(o.amount) > 1000;

-- 3
SELECT 
    sp.name,
    EXTRACT(YEAR FROM AGE('2024-12-31', sp.bod)) AS umur,
    sp.salary,
    SUM(o.amount) AS total_order
FROM orders o 
    JOIN sales_person sp ON o.sales_person_id = sp.id 
WHERE extract(year from o.order_date) >= '2020'
GROUP BY sp.name, sp.bod, sp.salary
ORDER BY umur ASC;

-- 4
select 
	name, avg(o.amount) as rata_rata
from orders o 
	join sales_person sp on o.sales_person_id = sp.id 
group by name
order by avg(o.amount) desc;

-- 5
select
	sp.name,
	sp.salary,
	case 
		when count(o.sales_person_id) > 2 and sum(o.amount) > 1000 
			then sp.salary * 0.30
		else 0
	end bonus
from orders o 
	inner join sales_person sp on o.sales_person_id = sp.id 
group by sp.name, sp.salary;

-- 6
select sp.*
from orders o 
	right join sales_person sp on o.sales_person_id = sp.id 
where o.sales_person_id is null;

-- 7
select 
    sp.*,
    case
        when sp.id in (
            select sp.id
            from orders o 
	            right join sales_person sp on o.sales_person_id = sp.id 
            where o.sales_person_id is null
        ) then salary * 0.02
        else 0
    end potong_gaji
from orders o 
	full outer join sales_person sp on o.sales_person_id = sp.id
group by sp.id
order by sp.id;



	





