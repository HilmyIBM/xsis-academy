CREATE TABLE Tbl_M_Categories
(
	id SERIAL PRIMARY KEY NOT NULL,
	category_name VARCHAR(100) NOT NULL,
	description TEXT NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT NOW (),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

CREATE TABLE Tbl_M_Customer
(
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	address TEXT NOT NULL,
	phone VARCHAR(15) NULL,
	role_id INTEGER NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW (),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL,
	CONSTRAINT UQ_Tbl_M_Customer UNIQUE (email,phone)
);

CREATE TABLE Tbl_M_Product
(
	id SERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(100) NOT NULL,
	price DECIMAL(18, 0) DEFAULT 0,
	stock INTEGER DEFAULT 0,
	variant_id INTEGER NOT NULL,
	image TEXT NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW (),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

CREATE TABLE Tbl_M_Variant
(
	id SERIAL PRIMARY KEY NOT NULL,
	category_id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255) NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW (),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

CREATE TABLE Tbl_T_Order_Detail
(
	id SERIAL PRIMARY KEY NOT NULL,
	order_header_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	qty INTEGER DEFAULT 0,
	price DECIMAL(18, 2) DEFAULT 0,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW (),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

CREATE TABLE Tbl_T_Order_Header
(
	id SERIAL PRIMARY KEY NOT NULL,
	trx_code VARCHAR(100) NOT NULL,
	customer_id INTEGER NOT NULL,
	amount DECIMAL(18, 2) DEFAULT 0,
	total_qty INTEGER NOT NULL,
	is_checkout BOOLEAN DEFAULT FALSE,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW (),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL,
	CONSTRAINT UQ_Tbl_T_Order_Header UNIQUE (trx_code)
);

DROP TABLE