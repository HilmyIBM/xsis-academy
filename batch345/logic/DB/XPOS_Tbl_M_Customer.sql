CREATE DATABASE xpos345;

CREATE TABLE Tbl_M_Customer(
	id serial NOT NULL,
	name varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	password varchar(100) NOT NULL,
	address TEXT NOT NULL,
	phone varchar(15) NULL,
	role_id int NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT now(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
	CONSTRAINT PK_Tbl_M_Customer PRIMARY KEY (id)
);

SELECT * from Tbl_M_Customer;
