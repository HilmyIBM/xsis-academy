create table SALESPERSON (
	id serial primary key,
	name varchar(255) not null,
	BOD date not null,
	salary decimal(18,2) not null
);

create table orders (
	id serial primary key,
	order_date date not null,
	cust_id int,
	salesperson_id int not null,
		foreign key (salesperson_id) references SALESPERSON (id) on delete cascade on update cascade,
	amount decimal(18,2)
);

INSERT INTO SALESPERSON (NAME, BOD, SALARY)
VALUES 
('Abe', '9/11/1988', 140000),
('Bob', '9/11/1978', 44000),
('Chris', '9/11/1983', 40000),
('Dan', '9/11/1980', 52000),
('Ken', '9/11/1977', 115000),
('Joe', '9/11/1990', 38000);

INSERT INTO ORDERS (ORDER_DATE, CUST_ID, SALESPERSON_ID, AMOUNT)
VALUES 
('8/2/2020', 4, 2, 540),
('1/22/2021', 4, 5, 1800),
('7/14/2019', 9, 1, 460),
('1/29/2018', 7, 2, 2400),
('2/3/2021', 6, 4, 600),
('3/2/2020', 6, 4, 720),
('5/6/2021', 9, 4, 150);

-- a. Informasi nama sales yang memiliki order lebih dari 1.
select a.name from salesperson a inner join orders b on b.salesperson_id = a.id group by a.name having count(cust_id) > 1 ;

-- b. Informasi nama sales yang total amount ordernya di atas 1000.
select a.name from salesperson a inner join orders b on b.salesperson_id = a.id group by a.name having sum(b.amount) > 1000;

-- c. Informasi nama sales, umur, gaji dan total amount order yang tahun ordernya >= 2020 dan 
-- data ditampilan berurut sesuai dengan umur (ascending).
select a.name, NOW()-a.BOD as AGE, a.salary, sum(b.amount) as total_amount_order, EXTRACT(YEAR FROM b.order_date) as order_year
from salesperson a
inner join orders b on b.salesperson_id = a.id 
where EXTRACT(YEAR FROM b.order_date) >= '2020' 
group by a.name, a.BOD, a.salary,b.order_date order by AGE asc;

-- d. Carilah rata-rata total amount masing-masing sales urutkan dari hasil yg paling besar dan lebih dari 1000
select a.name, avg(b.amount) as rata_rata_total_amount from salesperson a
inner join orders b on b.salesperson_id = a.id 
group by a.name having avg(b.amount) > 1000 
order by rata_rata_total_amount desc;

-- e. perusahaan akan memberikan bonus bagi sales yang berhasil memiliki order lebih dari 2 
-- dan total order lebih dari 1000 sebanyak 30% dari salary
select a.name, count(b.salesperson_id) as total_order, sum(b.amount), (a.salary * 0.3) as bonus 
from salesperson a
inner join orders b on b.salesperson_id = a.id
group by a.name,a.salary
having count(b.salesperson_id) > 2 and sum(b.amount) > 1000;

-- f. Tampilkan data sales yang belum memiliki orderan sama sekali							
select * from salesperson where id not in (select salesperson_id from orders);
select * from salesperson a left join orders b on b.salesperson_id = a.id where b.salesperson_id is null;

-- g. Gaji sales akan dipotong jika tidak memiliki orderan,  gaji akan di potong sebanyak 2%
select a.*, 
	case 
		when count(b.salesperson_id) = 0 then sum(salary) - sum(salary * 0.02)
	end as potongan_gaji
from salesperson a 
left join orders b on b.salesperson_id = a.id
group by a.id,b.id;