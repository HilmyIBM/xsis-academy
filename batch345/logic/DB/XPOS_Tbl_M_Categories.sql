CREATE DATABASE xpos345;

CREATE TABLE Tbl_M_Categories(
	id SERIAL NOT NULL,
	category_name varchar(100) NOT NULL,
	description text NULL,
	is_deleted BOOLEAN default FALSE,
	create_by int NOT NULL,
	create_date TIMESTAMP DEFAULT now(),
	update_by int NULL,
	update_date TIMESTAMP NULL,
 CONSTRAINT PK_Tbl_M_Categories PRIMARY KEY(id)
);

INSERT INTO Tbl_M_Categories
(category_name, description, create_by)
VALUES
('Makanan', 'Kategori Makanan', 1);

SELECT * FROM Tbl_M_Categories;

ALTER TABLE Tbl_M_Categories
	ALTER COLUMN description
		DROP NOT NULL;

    select
        c1_0.id,
        c1_0.category_name,
        c1_0.create_by,
        c1_0.create_date,
        c1_0.is_deleted,
        c1_0.description,
        c1_0.update_by,
        c1_0.update_date
    from
        tbl_m_categories c1_0
    where
        upper(c1_0.category_name) like upper('%kategori%') escape '\'
        or upper(c1_0.description) like upper('%kategori%') escape '\'
        and c1_0.is_deleted=false