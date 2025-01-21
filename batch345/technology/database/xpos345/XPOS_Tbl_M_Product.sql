-- create table TBL_M_Product
drop table if exists tbl_m_product;
create table TBL_M_Product(
	id serial primary key not null,
	name varchar(100) not null,
	price decimal(18,0) default 0,
	stock int default 0,
	variant_id int not null,
	image text,
	is_deleted boolean not null default false,
	create_by int,
	create_date timestamp default now(),
	update_by int,
	update_date timestamp
);

INSERT INTO Tbl_M_Product (name, variant_id, create_by)
VALUES
	('Es Krim Coklat', 2, 1)
;

SELECT * FROM Tbl_M_Product;



-- USE [XPOS329]
-- GO

-- /****** Object:  Table [dbo].[Tbl_M_Product]    Script Date: 09/10/2023 16:26:47 ******/
-- SET ANSI_NULLS ON
-- GO

-- SET QUOTED_IDENTIFIER ON
-- GO

-- CREATE TABLE [dbo].[Tbl_M_Product](
-- 	[id] [int] IDENTITY(1,1) NOT NULL,
-- 	[name] [varchar](100) NOT NULL,
-- 	[price] [decimal](18, 0) NULL,
-- 	[stock] [int] NULL,
-- 	[variant_id] [int] NOT NULL,
-- 	[image] [varchar](max) NULL,
-- 	[is_deleted] [bit] NOT NULL,
-- 	[create_by] [int] NULL,
-- 	[create_date] [datetime] NULL,
-- 	[update_by] [int] NULL,
-- 	[update_date] [datetime] NULL,
--  CONSTRAINT [PK_Tbl_M_Product] PRIMARY KEY CLUSTERED 
-- (
-- 	[id] ASC
-- )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
-- ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
-- GO

-- ALTER TABLE [dbo].[Tbl_M_Product] ADD  DEFAULT ((0)) FOR [price]
-- GO

-- ALTER TABLE [dbo].[Tbl_M_Product] ADD  DEFAULT ((0)) FOR [stock]
-- GO

-- ALTER TABLE [dbo].[Tbl_M_Product] ADD  DEFAULT ((0)) FOR [is_deleted]
-- GO

-- ALTER TABLE [dbo].[Tbl_M_Product] ADD  DEFAULT (getdate()) FOR [create_date]
-- GO


