CREATE DATABASE xpos345;

CREATE TABLE Tbl_T_Order_Header(
	id SERIAL NOT NULL,
	trx_code varchar(100) NOT NULL,
	customer_id int NOT NULL,
	amount decimal(18, 2) DEFAULT 0,
	total_qty int DEFAULT 0,
	is_checkout BOOLEAN DEFAULT FALSE,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT now(),
    update_by int NULL,
	update_date TIMESTAMP NULL,
    CONSTRAINT PK_Tbl_T_Order_Header PRIMARY KEY (id)
);

select * from Tbl_T_Order_Header;
