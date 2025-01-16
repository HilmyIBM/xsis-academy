-- create TBL_M_Customer
DROP TABLE IF EXISTS tbl_m_customer;
create table TBL_M_Customer(
	id serial primary key not null,
	name varchar(50) not null,
	email varchar(50) not null,
	password varchar(100) not null,
	address text not null,
	phone varchar(15) not null,
	role_id int,
	is_deleted boolean not null default false,
	create_by int not null,
	create_date timestamp default now(),
	update_by int,
	update_date timestamp
);

UPDATE tbl_m_customer
	SET is_deleted = FALSE
where id=1;

INSERT INTO Tbl_M_Customer (name, email, password, address, phone, create_by)
VALUES
	('coba', 'coba@gmail.com', 'coba123', 'jl.coba', '088877776666', 1)
;

SELECT * FROM tbl_m_customer;

ALTER TABLE tbl_m_customer
	ADD CONSTRAINT uq_tbl_m_customer UNIQUE(email, phone);

-- USE [XPOS329]
-- GO

-- /****** Object:  Table [dbo].[Tbl_M_Customer]    Script Date: 09/10/2023 16:25:59 ******/
-- SET ANSI_NULLS ON
-- GO

-- SET QUOTED_IDENTIFIER ON
-- GO

-- CREATE TABLE [dbo].[Tbl_M_Customer](
-- 	[id] [int] IDENTITY(1,1) NOT NULL,
-- 	[name] [varchar](50) NOT NULL,
-- 	[email] [varchar](50) NOT NULL,
-- 	[password] [varchar](100) NOT NULL,
-- 	[address] [varchar](max) NOT NULL,
-- 	[phone] [varchar](15) NULL,
-- 	[role_id] [int] NULL,
-- 	[is_deleted] [bit] NOT NULL,
-- 	[create_by] [int] NOT NULL,
-- 	[create_date] [datetime] NOT NULL,
-- 	[update_by] [int] NULL,
-- 	[update_date] [datetime] NULL,
--  CONSTRAINT [PK_Tbl_M_Customer] PRIMARY KEY CLUSTERED 
-- (
-- 	[id] ASC
-- )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
-- ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
-- GO

-- ALTER TABLE [dbo].[Tbl_M_Customer] ADD  DEFAULT ((0)) FOR [is_deleted]
-- GO

-- ALTER TABLE [dbo].[Tbl_M_Customer] ADD  DEFAULT (getdate()) FOR [create_date]
-- GO
