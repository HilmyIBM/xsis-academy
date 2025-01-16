CREATE TABLE Tbl_M_Order_Detail(
	id SERIAL PRIMARY KEY NOT NULL,
	order_header_id INTEGER NOT NULL,
	product_id INTEGER NOT NULL,
	qty INTEGER DEFAULT 0,
	price DECIMAL(18,2) DEFAULT 0,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

SELECT * FROM Tbl_M_Order_Detail