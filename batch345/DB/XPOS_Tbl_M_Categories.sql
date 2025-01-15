CREATE DATABASE xpos345;
USE [XPOS329]
DROP TABLE Tbl_M_Categories

CREATE TABLE Tbl_M_Categories(
	id SERIAL NOT NULL,
	category_name varchar(100) NOT NULL,
	"desc" text NOT NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT now(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Categories PRIMARY KEY(id)
)

INSERT INTO Tbl_M_Categories(category_name, "desc",create_by) 
VALUES
('Makanan', 'Kategori Makanan', 1),
('Minuman', 'Kategori Minuman', 2),
('Obat-obatan', 'Kategori Obat-obatan', 3);

SELECT * FROM Tbl_M_Categories