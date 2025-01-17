USE [XPOS329]

CREATE TABLE Tbl_M_Product(
	id SERIAL NOT NULL,
	name varchar(100) NOT NULL,
	price decimal(18, 0) DEFAULT 0,
	stock int DEFAULT 0,
	variant_id int NOT NULL,
	image text NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 	CONSTRAINT PK_Tbl_M_Product PRIMARY KEY(id)
)
INSERT INTO Tbl_M_Product(name, price,stock, variant_id, create_by) 
VALUES
('Chabu', 5000, 200, 1, 2),
('Hidro koko', 14000, 1000, 2, 2),
('Tora', 10000, 50, 1, 3)
SELECT * FROM Tbl_M_Product
DROP TABLE Tbl_M_Product