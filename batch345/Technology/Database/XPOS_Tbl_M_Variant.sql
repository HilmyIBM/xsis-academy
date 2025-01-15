USE [XPOS329]
GO

/****** Object:  Table [dbo].[Tbl_M_Variant]    Script Date: 09/10/2023 16:27:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Tbl_M_Variant](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[category_id] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
	[description] [varchar](255) NULL,
	[is_deleted] [bit] NOT NULL,
	[create_by] [int] NOT NULL,
	[create_date] [datetime] NOT NULL,
	[update_by] [int] NULL,
	[update_date] [datetime] NULL,
 CONSTRAINT [PK_Tbl_M_Variant] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Tbl_M_Variant] ADD  DEFAULT ((0)) FOR [is_deleted]
GO

ALTER TABLE [dbo].[Tbl_M_Variant] ADD  DEFAULT (getdate()) FOR [create_date]
GO

CREATE TABLE Tbl_M_Variant(
	id serial PRIMARY KEY NOT NULL,
	category_id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(255) NULL,
	is_deleted BOOLEAN DEFAULT FALSE,
	create_by INTEGER NOT NULL,
	create_date TIMESTAMP DEFAULT NOW(),
	update_by INTEGER NULL,
	update_date TIMESTAMP NULL
);