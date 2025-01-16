-- Tabel Categories

CREATE TABLE tbl_m_categories (
  id SERIAL NOT NULL,
  category_name VARCHAR(100) NOT NULL,
  description VARCHAR NOT NULL,
  is_deleted BOOLEAN DEFAULT FALSE,
  create_by INTEGER NOT NULL,
  create_date TIMESTAMP DEFAULT now(),
  update_by INTEGER NULL,
  update_date TIMESTAMP NULL,
  CONSTRAINT pk_tbl_m_categories PRIMARY KEY(id)
);

INSERT INTO tbl_m_categories(category_name, description, create_by)
VALUES 
('Makanan', 'Kategori makanan', 1);

SELECT * FROM tbl_m_categories;

ALTER TABLE tbl_m_categories
ALTER COLUMN description
DROP NOT NULL;

-- Table Product

CREATE TABLE tbl_m_product(
  id SERIAL NOT NULL,
  name VARCHAR(100) NOT NULL,
  price DECIMAL(18, 0) DEFAULT 0,
  stock INTEGER DEFAULT 0,
  variant_id INTEGER NOT NULL,
  image VARCHAR DEFAULT 'null.png',
  is_deleted BOOLEAN DEFAULT FALSE,
  create_by INTEGER NOT NULL,
  create_date TIMESTAMP DEFAULT now(),
  update_by INTEGER NULL,
  update_date TIMESTAMP NULL,
  CONSTRAINT pk_tbl_m_product PRIMARY KEY(id)
);

DROP TABLE IF EXISTS tbl_m_product
SELECT * FROM tbl_m_product;
INSERT INTO tbl_m_product (name, price, stock, variant_id, image, is_deleted, create_by)
VALUES ('Sample Product', 15000, 50, 1, 'sample.png', FALSE, 101);


-- Tabel Variant

CREATE TABLE tbl_m_variant(
  id SERIAL NOT NULL,
  category_id INTEGER NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  is_deleted BOOLEAN DEFAULT FALSE,
  create_by INTEGER NOT NULL,
  create_date TIMESTAMP DEFAULT now(),
  update_by INTEGER NULL,
  update_date TIMESTAMP NULL,
  CONSTRAINT pk_tbl_m_variant PRIMARY KEY(id)
);

INSERT INTO tbl_m_variant (category_id, name, description, create_by, create_date) 
VALUES (1, 'Variant A', 'Description of Variant A', 101, now());
VALUES (1, 'Variant A', 'Description of Variant A', 101, now());

SELECT * FROM tbl_m_variant WHERE is_deleted = false;

-- Tabel Customer

CREATE TABLE tbl_m_customer(
	id SERIAL NOT NULL,
	name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	password varchar(100) NOT NULL,
	address varchar NOT NULL,
	phone varchar(15) NULL,
	role_id int NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Customer PRIMARY KEY(id)
)

INSERT INTO tbl_m_customer (name, email, password, address, phone, role_id, is_deleted, create_by, create_date, update_by, update_date)
VALUES 
('John Doe', 'john.doe@example.com', 'hashed_password', '123 Main St, City', '123-456-7890', 1, FALSE, 1, NOW(), NULL, NULL);

SELECT * FROM tbl_m_customer;


-- Tabel Order Header
CREATE TABLE tbl_t_order_header(
	id SERIAL PRIMARY KEY NOT NULL,
	trx_code VARCHAR(100) UNIQUE NOT NULL,
	customer_id INTEGER NOT NULL,
	amount DECIMAL(18,2) DEFAULT 0,
	total_qty INTEGER NOT NULL,
	is_checkout BOOLEAN DEFAULT FALSE,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

INSERT INTO tbl_t_order_header (trx_code, customer_id, amount, total_qty, is_checkout, is_deleted, create_by, create_date, update_by, update_date)
VALUES 
('TRX00012345', 1, 150.00, 3, FALSE, FALSE, 1, NOW(), NULL, NULL);

SELECT * FROM tbl_t_order_header;



CREATE TABLE tbl_t_order_detail(
	id SERIAL PRIMARY KEY NOT NULL,
	order_header_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	qty INTEGER DEFAULT 0,
	price DECIMAL(18,2) DEFAULT 0,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

INSERT INTO tbl_t_order_detail (order_header_id, product_id, qty, price, is_deleted, create_by, create_date, update_by, update_date)
VALUES 
(1, 101, 2, 50.00, FALSE, 1, NOW(), NULL, NULL);


DROP TABLE IF EXISTS tbl_m_order_detail;