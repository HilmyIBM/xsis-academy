USE [XPOS329]

CREATE TABLE Tbl_M_Variant(
	id SERIAL NOT NULL,
	category_id int NOT NULL,
	name varchar(50) NOT NULL,
	description text NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Variant PRIMARY KEY(id)
)
INSERT INTO tbl_m_variant(category_id,name, description, create_by) 
VALUES
(1, 'Coklat', 'ini coklattt', 1),
(3, 'asdasdasd', 'ini asdasdas', 2),
(2, 'Biru', 'ini biru', 1)
DROP TABLE tbl_m_variant
SELECT * FROM Tbl_M_Variant
