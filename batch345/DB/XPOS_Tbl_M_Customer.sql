USE [XPOS329]

CREATE TABLE Tbl_M_Customer(
	id SERIAL NOT NULL,
	name varchar(50) NOT NULL, --
	email varchar(50) NOT NULL, -- 
	password varchar(100) NOT NULL, --
	address text NOT NULL, --
	phone varchar(15) NULL, --
	role_id int NULL, --
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL, --
	create_date TIMESTAMP DEFAULT NOW(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 	CONSTRAINT PK_Tbl_M_Customer PRIMARY KEY(id),
  	CONSTRAINT UQ_Tbl_M_Customer UNIQUE(email,phone)
)
DROP TABLE Tbl_M_Customer

SELECT * FROM Tbl_M_Customer

INSERT INTO Tbl_M_Customer(name, email, password, address,phone, role_id, create_by) 
VALUES
('abed', 'abed@gmail.com', 'abed', 'jl.asdsads no.32', '081318811642', 1, 2),
('silva', 'silva@gmail.com', 'silva', 'jl.zxcvbnm no.1212', '021318811642', 2, 1)