

CREATE DATABASE xpos345;

CREATE TABLE Tbl_M_Categories(
	id SERIAL NOT NULL,
	category_name varchar(100) NOT NULL,
	description text NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT now() ,
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Categories PRIMARY KEY (id)
 );

INSERT INTO Tbl_M_Categories (category_name, description, create_by)
VALUES
	('Minuman', 'Kategori kopi', 3);

SELECT * FROM Tbl_M_Categories;

DROP TABLE Tbl_M_Categories