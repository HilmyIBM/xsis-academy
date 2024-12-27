--DML: Memanipulasi Data yang tersimpan di Database--
--DML Create Data
INSERT INTO coba01
VALUES
(1, 'Coba 01', 'Coba yang ke-01');

INSERT INTO pelanggan
VALUES
(1, 'Pelanggan 01', 'Alamat si Pelanggan 01', 'pelanggan01@email.net');

INSERT INTO pelanggan
(alamat, nama, email)
VALUES
('Alamat si Pelanggan 02', 'Pelanggan 02', 'pelanggan02@email.net');

INSERT INTO pelanggan
(alamat, nama, email)
VALUES
('Alamat si Pelanggan 03', 'Pelanggan 03', 'pelanggan03@email.net'),
('Alamat si Pelanggan 04', 'Pelanggan 04', 'pelanggan04@email.net');

--Duplicate Table
--CREATE Table
CREATE TABLE coba_bak (
    id INTEGER,
    nama VARCHAR(20) NOT NULL,
    deskripsi VARCHAR(255),
    CONSTRAINT pk_coba PRIMARY KEY (id)
);
INSERT INTO coba_bak
    SELECT id, nama, deskripsi
    FROM coba01;


--DML Read Data
select * from coba01;
select * from coba_bak;
SELECT * FROM "cobaPertama";
select * from pelanggan;

