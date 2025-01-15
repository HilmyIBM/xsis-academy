USE [XPOS329]

CREATE TABLE Tbl_M_Customer(
	id SERIAL NOT NULL,
	"name" varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	"password" varchar(100) NOT NULL,
	"address" text NOT NULL,
	phone varchar(15) NULL,
	role_id int NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Customer PRIMARY KEY(id)
)

ALTER TABLE [dbo].[Tbl_M_Customer] ADD  DEFAULT ((0)) FOR [is_deleted]
GO

ALTER TABLE [dbo].[Tbl_M_Customer] ADD  DEFAULT (getdate()) FOR [create_date]
GO
