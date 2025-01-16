CREATE DATABASE xpos345;

CREATE TABLE Tbl_M_Variant(
	id serial NOT NULL,
	category_id int NOT NULL,
	name varchar(50) NOT NULL,
	description varchar(255) NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT now(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
    CONSTRAINT PK_Tbl_M_Variant PRIMARY KEY (id)
);

select * from Tbl_M_Variant;
