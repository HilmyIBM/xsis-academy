CREATE DATABASE db_sales;

CREATE TABLE sales_person(
    id Serial PRIMARY KEY NOT NULL,
    nama VARCHAR(100) NOT NULL,
    bod DATE NOT NULL,
    salary DECIMAL(18,2) NOT NULL
);

CREATE TABLE orders(
    id Serial PRIMARY KEY NOT NULL,
    order_date DATE NOT NULL,
    cust_id INTEGER NOT NULL,
    sales_person_id INTEGER NOT NULL,
    amount DECIMAL(18,2) NOT NULL
);

INSERT INTO sales_person(nama,bod,salary)
VALUES('Abe','9/11/1988',140000),
      ('Bob','9/11/1978',44000),
      ('Chris','9/11/1983',40000),
      ('Dan','9/11/1980',52000),
      ('Ken','9/11/1977',115000),
      ('Joe','9/11/1990',38000);

INSERT INTO orders(order_date,cust_id,sales_person_id,amount)
VALUES('8/2/2020',4,2,540),
      ('1/22/2021',4,5,1800),
      ('7/14/2019',9,1,460),
      ('1/29/2018',7,2,2400),
      ('2/3/2021',6,4,600),
      ('3/2/2020',6,4,720),
      ('5/6/2021',9,4,150);


--No.1
SELECT nama,COUNT(cust_id) from sales_person 
INNER JOIN orders on sales_person.id=orders.sales_person_id
GROUP BY nama
HAVING COUNT(cust_id) >1; 

--No.2
SELECT nama,SUM(amount) as total_amount from sales_person join orders on sales_person.id=orders.sales_person_id
GROUP BY nama
HAVING SUM(amount) >1000; 

--No.3
SELECT nama,'2024'-EXTRACT(YEAR from sales_person.bod) as umur,salary,SUM(amount) from sales_person join orders on sales_person.id=orders.sales_person_id
WHERE EXTRACT(YEAR from orders.order_date) >=2020
GROUP BY nama,sales_person.bod,salary
ORDER BY umur ASC;

--No.4
SELECT nama, AVG(orders.amount) from sales_person JOIN orders
on sales_person.id=orders.sales_person_id
GROUP BY nama
ORDER BY AVG(orders.amount) DESC;

--No.5
SELECT nama,COUNT(orders.sales_person_id),SUM(amount) as total_order,
CASE
WHEN COUNT(orders.sales_person_id) >2 and SUM(amount) >1000
THEN sales_person.salary*0.3
end as bonus
from sales_person
join orders on sales_person.id=orders.sales_person_id
GROUP BY nama,salary
HAVING COUNT(orders.sales_person_id) >2;

--No.6
SELECT nama,bod,salary from sales_person LEFT JOIN orders 
ON sales_person.id=orders.sales_person_id
WHERE orders.sales_person_id is NULL;

--No.7
SELECT nama,bod,salary,
CASE 
WHEN COUNT(cust_id) = 0 
THEN SUM(salary) - (SUM(salary) * 0.02)
end as Gaji_setelah_dipotong
from sales_person LEFT JOIN orders 
ON sales_person.id=orders.sales_person_id
WHERE orders.sales_person_id is NULL
GROUP BY nama,bod,salary;




