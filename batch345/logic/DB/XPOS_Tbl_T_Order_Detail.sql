CREATE DATABASE xpos345;

CREATE TABLE Tbl_T_Order_Detail(
	id SERIAL NOT NULL,
	order_header_id int NOT NULL,
	product_id int NOT NULL,
	qty int DEFAULT 0,
	price decimal(18, 2) DEFAULT 0,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT now(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
    CONSTRAINT PK_Tbl_T_Order_Detail PRIMARY KEY (id)
);

select * from Tbl_T_Order_Detail;
