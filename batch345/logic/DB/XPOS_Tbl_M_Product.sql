

CREATE TABLE Tbl_M_Product(
	id SERIAL NOT NULL,
	name varchar(100) NOT NULL,
	price decimal(18, 0) NULL DEFAULT 0,
	stock int NULL DEFAULT 0,
	variant_id int NOT NULL,
	image TEXT NULL,
	is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP NULL DEFAULT now(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Product PRIMARY KEY (id)
);

DROP TABLE Tbl_M_Product


