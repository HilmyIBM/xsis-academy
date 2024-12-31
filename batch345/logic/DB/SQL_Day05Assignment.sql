create table sales_person (
  id SERIAL primary key,
  sales_name VARCHAR(50) not null,
  date_of_birth DATE not null,
  salary DECIMAL(18, 2)
);

create table orders (
  id SERIAL primary key,
  order_date DATE not null,
  cust_id integer not null,
  sales_person_id INTEGER references sales_person(id),
  amount DECIMAL(18, 2)
);

insert into sales_person (sales_name, date_of_birth, salary)
  VALUES 
    ('Abe','1988-11-9',140000),
    ('Bob','1978-11-9',44000),
    ('Chris','1983-11-9',40000),
    ('Dan','1980-11-9',52000),
    ('Ken','1977-11-9',115000),
    ('Joe','1990-11-9',38000);

insert into orders (order_date, cust_id, sales_person_id, amount)
  VALUES
    ('8/2/2020',4,2,540),
    ('1/22/2021',4,5,1800),
    ('7/14/2019',9,1,460),
    ('1/29/2018',7,2,2400),
    ('2/3/2021',6,4,600),
    ('3/2/2020',6,4,720),
    ('5/6/2021',9,4,150);

select * from orders;