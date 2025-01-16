-- create TBL_M_Categories
create table TBL_M_Categories  (
	id serial primary key not null,
	category varchar(100) not null,
	description varchar(255) not null,
	is_deleted boolean not null default (0::boolean),
	create_by int not null,
	created_date timestamp default now(),
	update_by int,
	update_date timestamp
);

alter table TBL_M_Categories
rename column created_date to create_date;

alter table TBL_M_Categories
rename column category to category_name;

alter table TBL_M_Categories
alter column description drop not null;

-- create TBL_M_Customer
create table TBL_M_Customer(
	id serial primary key not null,
	name varchar(50) not null,
	email varchar(50) not null,
	password varchar(100) not null,
	address text not null,
	phone varchar(15) not null,
	role_id int,
	is_deleted boolean not null default (0::boolean),
	create_by int not null,
	create_date timestamp default now(),
	update_by int,
	update_date timestamp
);

-- create table TBL_M_Product
create table TBL_M_Product(
	id serial primary key not null,
	name varchar(100) not null,
	price decimal(18,0) default 0,
	stock int default 0,
	variant_id int not null,
	image text,
	is_deleted boolean not null default false,
	create_by int,
	create_date timestamp default now(),
	update_by int,
	update_date timestamp
);

-- create table TBL_M_Variant
create table TBL_M_Variant(
	id serial primary key,
	category_id int not null,
	name varchar(50) not null,
	description varchar(255),
	is_deleted boolean not null default false,
	create_by int not null,
	create_date timestamp default now(),
	update_by int,
	update_date timestamp
);

-- create table TBL_T_Order_Detail
create table TBL_T_Order_Detail(
	id serial primary key,
	order_header_id int not null,
	product_id int not null,
	qty int not null,
	price decimal(18,2) not null,
	is_deleted boolean not null default false,
	create_by int not null,
	create_date timestamp not null default now(),
	update_by int,
	update_date timestamp
);

alter table TBL_T_Order_Detail
alter column qty set default 0,
alter column price set default 0;

-- create table TBL_T_Order_Header
create table TBL_T_Order_Header(
	id serial primary key,
	trx_code varchar(100) not null,
	customer_id int not null,
	amount decimal(18,2) not null default 0,
	total_qty int not null default 0,
	is_checkout boolean not null,
	is_deleted boolean not null default false,
	create_by int not null,
	create_date timestamp not null default now(),
	update_by int,
	update_date timestamp
);