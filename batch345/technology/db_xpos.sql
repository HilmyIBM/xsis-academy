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
  price DECIMAL(18, 0) NULL,
  stock INTEGER NULL,
  variant_id INTEGER NOT NULL,
  image VARCHAR NULL,
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
