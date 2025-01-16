-- create table TBL_T_Order_Header
create table TBL_T_Order_Header(
	id serial primary key,
	trx_code varchar(100) not null,
	customer_id int not null,
	amount decimal(18,2) not null default 0,
	total_qty int not null default 0,
	is_checkout boolean not null,
	is_deleted boolean not null default false,
	create_by int not null,
	create_date timestamp not null default now(),
	update_by int,
	update_date timestamp
);

SELECT * FROM Tbl_T_Order_Header;

ALTER TABLE tbl_t_order_header
	ADD CONSTRAINT uq_trx_code UNIQUE(trx_code);


-- USE [XPOS329]
-- GO

-- /****** Object:  Table [dbo].[Tbl_T_Order_Header]    Script Date: 09/10/2023 16:28:48 ******/
-- SET ANSI_NULLS ON
-- GO

-- SET QUOTED_IDENTIFIER ON
-- GO

-- CREATE TABLE [dbo].[Tbl_T_Order_Header](
-- 	[id] [int] IDENTITY(1,1) NOT NULL,
-- 	[trx_code] [varchar](100) NOT NULL,
-- 	[customer_id] [int] NOT NULL,
-- 	[amount] [decimal](18, 2) NOT NULL,
-- 	[total_qty] [int] NOT NULL,
-- 	[is_checkout] [bit] NOT NULL,
-- 	[is_deleted] [bit] NOT NULL,
-- 	[create_by] [int] NOT NULL,
-- 	[create_date] [datetime] NOT NULL,
-- 	[update_by] [int] NULL,
-- 	[update_date] [datetime] NULL,
--  CONSTRAINT [PK_Tbl_T_Order_Header] PRIMARY KEY CLUSTERED 
-- (
-- 	[id] ASC
-- )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
-- ) ON [PRIMARY]
-- GO

-- ALTER TABLE [dbo].[Tbl_T_Order_Header] ADD  DEFAULT ((0)) FOR [is_deleted]
-- GO

-- ALTER TABLE [dbo].[Tbl_T_Order_Header] ADD  DEFAULT (getdate()) FOR [create_date]
-- GO

-- ALTER TABLE [dbo].[Tbl_T_Order_Header] ADD  DEFAULT (0) FOR [amount]
-- GO

-- ALTER TABLE [dbo].[Tbl_T_Order_Header] ADD  DEFAULT (0) FOR [total_qty]
-- GO

