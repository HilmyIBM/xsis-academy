USE [XPOS329]
GO

/****** Object:  Table [dbo].[Tbl_M_Variant]    Script Date: 09/10/2023 16:27:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE Tbl_M_Variant(
	id SERIAL NOT NULL,
	category_id int NOT NULL,
	"name" varchar(50) NOT NULL,
	"desc" varchar(255) NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Variant PRIMARY KEY(id)
)

select * from  tbl_m_variant tmv ;

INSERT INTO tbl_m_variant 
("name" ,category_id ,"desc" , create_by)
VALUES
('Makanan 2',2 ,'Kategori Makanan 2', 2);





