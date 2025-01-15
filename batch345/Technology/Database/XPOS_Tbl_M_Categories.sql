USE [XPOS329]
GO

/****** Object:  Table [dbo].[Tbl_M_Categories]    Script Date: 09/10/2023 16:23:42 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Tbl_M_Categories](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[category_name] [varchar](100) NOT NULL,
	[description] [varchar](max) NOT NULL,
	[is_deleted] [bit] NOT NULL,
	[create_by] [int] NOT NULL,
	[create_date] [datetime] NOT NULL,
	[update_by] [int] NULL,
	[update_date] [datetime] NULL,
 CONSTRAINT [PK_Tbl_M_Categories] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[Tbl_M_Categories] ADD  DEFAULT ((0)) FOR [is_deleted]
GO

ALTER TABLE [dbo].[Tbl_M_Categories] ADD  DEFAULT (GETDATE()) FOR [create_date]
GO


CREATE TABLE Tbl_M_Categories(
	id SERIAL PRIMARY KEY NOT NULL,
	category_name VARCHAR(100) NOT NULL,
	description VARCHAR NOT NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT now(),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);

INSERT INTO Tbl_M_Categories(category_name,description,create_by)
VALUES 	('Makanan','Kategori Makanan',1);

SELECT * FROM Tbl_M_Categories;

ALTER TABLE Tbl_M_Categories
ALTER COLUMN description SET NULL;