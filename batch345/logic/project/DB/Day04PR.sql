create database "DB_Sales";

create table sales_person (
    id serial primary key not null,
    name varchar(50) not null,
    bod date not null,
    salary decimal(18, 2)
);

insert into sales_person (name, bod, salary)
values
    ('Abe', '9/11/1998', 140000),
    ('Bob', '9/11/1978', 44000),
    ('Chris', '9/11/1983', 40000),
    ('Dan', '9/11/1980', 52000),
    ('Ken', '9/11/1977', 115000),
    ('Joe', '9/11/1990', 38000);

create table orders (
  id serial primary key not null,
  order_date date not null,
    cust_id integer,
    salesperson_id integer references sales_person(id),
    amount decimal(18, 2)
);

insert into orders (order_date, cust_id, salesperson_id, amount)
values
    ('8/2/2020', 4, 2, 540),
    ('1/22/2021', 4, 5, 1800),
    ('7/14/2019', 9, 1, 460),
    ('1/29/2018', 7, 2, 2400),
    ('2/3/2021', 6, 4, 600),
    ('3/2/2020', 6, 4, 720),
    ('5/6/2021', 9, 4, 150);

------------ Assignment --------------


--- a
SELECT
    sp.name,
    count(salesperson_id) as jml_order
FROM
    orders o
        INNER JOIN sales_person sp on o.salesperson_id = sp.id
GROUP BY o.salesperson_id, sp.name
HAVING count(salesperson_id) > 1;

--- b
SELECT
    sp.name,
    sum(o.amount) as jml_order_amount
FROM
    orders o
        INNER JOIN sales_person sp on o.salesperson_id = sp.id
GROUP BY o.salesperson_id, sp.name
HAVING sum(o.amount) > 1000;

--- c
SELECT
    sp.name,
    extract(YEAR from age(now(), sp.bod)) as umur,
    sp.salary,
    sum(o.amount)
FROM
    orders o
    INNER JOIN sales_person sp on sp.id = o.salesperson_id
WHERE extract(YEAR FROM o.order_date) >= '2020'
group by sp.name, sp.bod, sp.salary
order by umur asc;

--- d
SELECT
    sp.name,
    avg(o.amount) as jml_order_amount
FROM
    orders o
        INNER JOIN sales_person sp on o.salesperson_id = sp.id
GROUP BY o.salesperson_id, sp.name
order by jml_order_amount desc;

--- e
SELECT
    sp.name,
    sp.salary * 0.3 as bonus,
    sp.salary + (sp.salary * 0.3) as total_salary
FROM
    orders o
        INNER JOIN sales_person sp on o.salesperson_id = sp.id
GROUP BY o.salesperson_id, sp.name, sp.salary
HAVING sum(o.amount) > 1000 and count(salesperson_id) > 2;

--- f
SELECT
    sp.name
FROM
    orders o
        RIGHT JOIN sales_person sp on o.salesperson_id = sp.id
WHERE o.salesperson_id is null;

--- g
SELECT
    sp.name,
    sp.salary * 0.02 as potongan_gaji,
    sp.salary - (sp.salary * 0.02) as total_salary
FROM
    orders o
        RIGHT JOIN sales_person sp on o.salesperson_id = sp.id
WHERE o.salesperson_id is null;