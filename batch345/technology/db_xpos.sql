DROP TABLE IF EXISTS tbl_t_order_detail;
DROP TABLE IF EXISTS tbl_t_order_header;
DROP TABLE IF EXISTS tbl_m_customer;
DROP TABLE IF EXISTS tbl_m_product;
DROP TABLE IF EXISTS tbl_m_variant;
DROP TABLE IF EXISTS tbl_m_categories;

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
);


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

-- Insert Categories
INSERT INTO tbl_m_categories (category_name, description, create_by) VALUES
('Beverages', 'Drinks including tea, coffee, and juices', 1),
('Snacks', 'Light snacks like chips and nuts', 1),
('Desserts', 'Sweet dishes like cakes and ice creams', 1),
('Main Course', 'Hearty meals like pasta and rice dishes', 1);

-- Insert Variants
INSERT INTO tbl_m_variant (category_id, name, description, create_by) VALUES
(1, 'Cold Drinks', 'Chilled drinks such as iced tea and cold coffee', 1),
(1, 'Hot Drinks', 'Hot beverages like tea and espresso', 1),
(2, 'Salty Snacks', 'Savory snacks like chips and pretzels', 1),
(2, 'Sweet Snacks', 'Sweet snacks like cookies and candy', 1),
(3, 'Cakes', 'Various cakes including chocolate and vanilla', 1),
(3, 'Ice Creams', 'Different flavors of ice cream', 1),
(4, 'Pasta', 'Italian pasta dishes', 1),
(4, 'Rice Dishes', 'Meals based on rice such as biryani', 1);

-- Insert Products
INSERT INTO tbl_m_product (name, price, stock, variant_id, image, create_by) VALUES
('Iced Tea', 15000, 100, 1, 'iced_tea.png', 1),
('Espresso', 20000, 50, 2, 'espresso.png', 1),
('Potato Chips', 10000, 200, 3, 'potato_chips.png', 1),
('Chocolate Cookies', 12000, 150, 4, 'chocolate_cookies.png', 1),
('Cheesecake', 30000, 80, 5, 'cheesecake.png', 1),
('Vanilla Ice Cream', 25000, 60, 6, 'vanilla_ice_cream.png', 1),
('Spaghetti Bolognese', 45000, 40, 7, 'spaghetti_bolognese.png', 1),
('Chicken Biryani', 50000, 30, 8, 'chicken_biryani.png', 1);

-- Insert Customers
INSERT INTO tbl_m_customer (name, email, password, address, phone, role_id, create_by) VALUES
('John Doe', 'john.doe@example.com', 'password123', '123 Main St', '081234567890', 2, 1),
('Jane Smith', 'jane.smith@example.com', 'securepass', '456 Elm St', '081298765432', 2, 1);

-- Insert Order Header
INSERT INTO tbl_t_order_header (trx_code, customer_id, amount, total_qty, is_checkout, create_by) VALUES
('ORD001', 1, 85000, 2, TRUE, 1),
('ORD002', 2, 45000, 1, TRUE, 1);

-- Insert Order Detail
INSERT INTO tbl_t_order_detail (order_header_id, product_id, qty, price, create_by) VALUES
(1, 1, 2, 15000, 1), -- 2 Iced Tea
(1, 7, 1, 45000, 1), -- 1 Spaghetti Bolognese
(2, 5, 1, 45000, 2); -- 1 Cheesecake

