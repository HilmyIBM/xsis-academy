-- Active: 1737930980060@@localhost@5432@xpos345
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

ALTER TABLE tbl_m_customer RENAME COLUMN id_deleted TO is_deleted;
drop table tbl_m_customer 

CREATE TABLE
Tbl_M_Customer (
id SERIAL PRIMARY KEY NOT NULL,
name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(100) NOT NULL,
address TEXT NOT NULL,
phone VARCHAR(15) NULL,
role_id INTEGER NULL,
is_deleted BOOLEAN DEFAULT FALSE,
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
('testname', 'test@example', 'testpassword', 'testaddress', 'testphone', 1, 1)

INSERT INTO Tbl_M_Categories (category_name, description, create_by) VALUES
('Makanan Cepat Saji', 'Kategori untuk makanan yang dapat disiapkan dengan cepat seperti burger dan pizza', 2),
('Makanan Ringan', 'Kategori untuk camilan seperti keripik, biskuit, dan kacang-kacangan', 2),
('Makanan Beku', 'Kategori untuk makanan beku seperti nugget, sosis, dan daging olahan', 2),
('Makanan Instan', 'Kategori untuk makanan siap saji seperti mie instan dan bubur instan', 2),
('Makanan Tradisional', 'Kategori untuk makanan khas daerah seperti rendang, gudeg, dan pempek', 2),
('Makanan Sehat', 'Kategori untuk makanan sehat seperti granola, salad, dan makanan organik', 2),
('Camilan Manis', 'Kategori untuk makanan manis seperti cokelat, permen, dan kue kering', 2),
('Camilan Asin', 'Kategori untuk makanan asin seperti kerupuk, kacang asin, dan popcorn', 2),
('Olahan Susu', 'Kategori untuk makanan berbasis susu seperti keju, yogurt, dan mentega', 2),
('Roti & Kue', 'Kategori untuk berbagai jenis roti dan kue seperti donat, bolu, dan croissant', 2);


INSERT INTO Tbl_M_Product (name, price, stock, variant_id, image, create_by)
VALUES 
('Produk A', 10000, 50, 2, NULL, 1),
('Produk B', 15000, 30, 2, NULL, 1),
('Produk C', 20000, 20, 2, NULL, 1),
('Produk D', 25000, 40, 2, NULL, 1),
('Produk E', 30000, 10, 2, NULL, 1),
('Produk F', 18000, 25, 2, NULL, 1),
('Produk G', 22000, 35, 2, NULL, 1),
('Produk H', 27000, 15, 2, NULL, 1),
('Produk I', 32000, 5, 2, NULL, 1),
('Produk J', 12000, 60, 2, NULL, 1);

INSERT INTO Tbl_M_Variant (category_id, name, description, create_by)
VALUES 


INSERT INTO Tbl_T_Order_Detail (order_header_id, product_id, qty, price, create_by)
VALUES 


INSERT INTO Tbl_T_Order_Header (trx_code, customer_id, amount, total_qty, is_checkout, create_by)
VALUES 

