

CREATE TABLE Tbl_T_Order_Header(
	id SERIAL NOT NULL,
	trx_code varchar(100) NOT NULL,
	customer_id int NOT NULL,
	amount decimal(18, 2) NOT NULL DEFAULT 0,
	total_qty int NOT NULL DEFAULT 0,
	is_checkout BOOLEAN NOT NULL DEFAULT FALSE,
	is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP NOT NULL DEFAULT now(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_T_Order_Header PRIMARY KEY (id)
);

DROP TABLE Tbl_T_Order_Header