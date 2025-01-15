USE [XPOS329]
GO

/****** Object:  Table [dbo].[Tbl_T_Order_Detail]    Script Date: 09/10/2023 16:28:16 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Tbl_T_Order_Detail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_header_id] [int] NOT NULL,
	[product_id] [int] NOT NULL,
	[qty] [int] NOT NULL,
	[price] [decimal](18, 2) NOT NULL,
	[is_deleted] [bit] NOT NULL,
	[create_by] [int] NOT NULL,
	[create_date] [datetime] NOT NULL,
	[update_by] [int] NULL,
	[update_date] [datetime] NULL,
 CONSTRAINT [PK_Tbl_T_Order_Detail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Tbl_T_Order_Detail] ADD  DEFAULT ((0)) FOR [is_deleted]
GO

ALTER TABLE [dbo].[Tbl_T_Order_Detail] ADD  DEFAULT (getdate()) FOR [create_date]
GO

ALTER TABLE [dbo].[Tbl_T_Order_Detail] ADD  DEFAULT ((0)) FOR [qty]
GO

ALTER TABLE [dbo].[Tbl_T_Order_Detail] ADD  DEFAULT ((0)) FOR [price]
GO

CREATE TABLE Tbl_T_Order_Detail(
	id serial PRIMARY key NOT NULL,
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