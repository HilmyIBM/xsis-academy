CREATE DATABASE xpos345;


DROP TABLE Tbl_M_Categories;
DROP TABLE Tbl_M_Variant;
DROP TABLE Tbl_M_Customer;
DROP TABLE Tbl_M_Product;
DROP TABLE Tbl_T_Order_Detail;
DROP TABLE Tbl_T_Order_Header;

CREATE TABLE
Tbl_M_Categories (
id SERIAL PRIMARY KEY NOT NULL,
category_name VARCHAR(100) NOT NULL,
description TEXT NULL,
is_deleted BOOLEAN DEFAULT FALSE,
create_by int NOT NULL,
create_date TIMESTAMP DEFAULT NOW(),
update_by INTEGER NULL,
update_date TIMESTAMP NULL
);

CREATE TABLE
Tbl_M_Customer (
id SERIAL PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(100) NOT NULL,
address TEXT NOT NULL,
phone VARCHAR(15) NULL,
role_id INTEGER NULL,
id_deleted BOOLEAN DEFAULT FALSE,
create_by INTEGER NOT NULL,
create_date TIMESTAMP DEFAULT NOW(),
update_by INTEGER NULL,
update_date TIMESTAMP NULL,
CONSTRAINT UQ_Tbl_M_Customer UNIQUE (email,phone)
);

CREATE TABLE
Tbl_M_Product (
id SERIAL PRIMARY KEY NOT NULL,
name VARCHAR(100) NOT NULL,
price DECIMAL(18, 0) DEFAULT 0,
stock INTEGER DEFAULT 0,
variant_id INTEGER NOT NULL,
image TEXT NULL,
is_deleted BOOLEAN DEFAULT FALSE,
create_by INTEGER NOT NULL,
create_date TIMESTAMP DEFAULT NOW(),
update_by INTEGER NULL,
update_date TIMESTAMP NULL
);

CREATE TABLE
Tbl_M_Variant (
id SERIAL PRIMARY KEY NOT NULL,
category_id INTEGER NOT NULL,
name VARCHAR(50) NOT NULL,
description VARCHAR(255) NULL,
is_deleted BOOLEAN DEFAULT FALSE,
create_by INTEGER NOT NULL,
create_date TIMESTAMP DEFAULT NOW(),
update_by INTEGER NULL,
update_date TIMESTAMP NULL
);

CREATE TABLE
Tbl_T_Order_Detail (
id SERIAL PRIMARY KEY NOT NULL,
order_header_id INTEGER NOT NULL,
product_id INTEGER NOT NULL,
qty INTEGER DEFAULT 0,
price DECIMAL(18, 2) DEFAULT 0,
is_deleted BOOLEAN DEFAULT FALSE,
create_by INTEGER NOT NULL,
create_date TIMESTAMP DEFAULT NOW(),
update_by INTEGER NULL,
update_date TIMESTAMP NULL
);

CREATE TABLE
Tbl_T_Order_Header (
id SERIAL PRIMARY KEY NOT NULL,
trx_code VARCHAR(100) NOT NULL,
customer_id INTEGER NOT NULL,
amount DECIMAL(18, 2) DEFAULT 0,
total_qty INTEGER NOT NULL,
is_checkout BOOLEAN DEFAULT FALSE,
is_deleted BOOLEAN DEFAULT FALSE,
create_by INTEGER NOT NULL,
create_date TIMESTAMP DEFAULT NOW(),
update_by INTEGER NULL,
update_date TIMESTAMP NULL,
CONSTRAINT UQ_Tbl_T_Order_Header UNIQUE (trx_code)
);


INSERT INTO Tbl_M_Categories (category_name, description, create_by)
VALUES 
('Minuman', 'Kategori untuk semua jenis minuman', 1),
('Makanan Pembuka', 'Kategori untuk makanan ringan dan cemilan', 1),
('Hidangan Utama', 'Kategori untuk makanan utama', 2),
('Pencuci Mulut', 'Kategori untuk hidangan manis penutup makan', 2),
('Pelengkap', 'Kategori untuk makanan pendamping hidangan utama', 3);

INSERT INTO Tbl_M_Customer (name, email, password, address, phone, role_id, create_by)
VALUES 

INSERT INTO Tbl_M_Product (name, price, stock, variant_id, image, create_by)
VALUES 

INSERT INTO Tbl_M_Variant (category_id, name, description, create_by)
VALUES 


INSERT INTO Tbl_T_Order_Detail (order_header_id, product_id, qty, price, create_by)
VALUES 


INSERT INTO Tbl_T_Order_Header (trx_code, customer_id, amount, total_qty, is_checkout, create_by)
VALUES 

