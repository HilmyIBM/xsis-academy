CREATE TABLE Tbl_T_Order_Header(
	id SERIAL NOT NULL,
	trx_code VARCHAR(100) NOT NULL,
	customer_id INTEGER NOT NULL,
	amount DECIMAL(18,2) DEFAULT 0,
	total_qty INTEGER NOT NULL,
	is_checkout BOOLEAN DEFAULT FALSE,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL,
	CONSTRAINT PK_Tbl_T_Order_Header PRIMARY KEY(id),
	CONSTRAINT UQ_Tbl_T_Order_Header UNIQUE(trx_code)
);

INSERT INTO Tbl_T_Order_Header(trx_code, customer_id, amount, total_qty, create_by)
VALUES
('TRX001', 1, 1500.00, 3, 1),
('TRX002', 2, 3200.50, 5, 2),
('TRX003', 3, 2750.50, 4, 1),
('TRX004', 4, 4300.00, 7, 3),
('TRX005', 5, 1250.00, 2, 2);

DROP TABLE tbl_T_order_header
SELECT * FROM Tbl_T_Order_Header