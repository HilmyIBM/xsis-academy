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


-- 1. Tampilkan nama mahasiswa dan matakuliah yang diambil
