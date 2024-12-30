-- DDL CREATE DATABASE AND TABLE
CREATE DATABASE DBPenerbit;

CREATE TABLE "tblPengarang"
(
    ID             SERIAL PRIMARY KEY NOT NULL,
    "Kd_Pengarang" varchar(7)         NOT NULL,
    "Nama"         varchar(30)        NOT NULL,
    "Alamat"       varchar(80)        NOT NULL,
    "Kota"         varchar(15)        NOT NULL,
    "Kelamin"      varchar(1)         NOT NULL
);

CREATE TABLE "tblGaji"
(
    ID             SERIAL PRIMARY KEY NOT NULL,
    "Kd_Pengarang" varchar(7)         NOT NULL,
    "Nama"         varchar(30)        NOT NULL,
    "Gaji"         decimal            NOT NULL
);

-- DML INSERT DATA

INSERT INTO "tblPengarang" ("Kd_Pengarang", "Nama", "Alamat", "Kota", "Kelamin")
VALUES ('P0001', 'Ashadi', 'Jl. Beo 25', 'Yogya', 'p'),
       ('P0002', 'Rian', 'Jl. Solo 123', 'Yogya', 'p'),
       ('P0003', 'Suwadi', 'Jl. Semangka 13', 'Bandung', 'p'),
       ('P0004', 'Siti', 'Jl. Durian 15', 'Solo', 'W'),
       ('P0005', 'Amir', 'Jl. Gajah 33', 'Kudus', 'p'),
       ('P0006', 'Suparman', 'Jl. Harimau 25', 'Jakarta', 'p'),
       ('P0007', 'Jaja', 'Jl. Singa 7', 'Bandung', 'p'),
       ('P0008', 'Saman', 'Jl. Naga 12', 'Yogya', 'p'),
       ('P0009', 'Anwar', 'Jl. Tidar 6A', 'Magelang', 'p'),
       ('P0010', 'Fatmawati', 'Jl. Renjana 4', 'Bogor', 'W');

INSERT INTO "tblGaji" ("Kd_Pengarang", "Nama", "Gaji")
VALUES ('P0002', 'Rian', 600000),
       ('P0005', 'Amir', 700000),
       ('P0004', 'Siti', 500000),
       ('P0003', 'Suwadi', 1000000),
       ('P0010', 'Fatmawati', 600000),
       ('P0008', 'Saman', 750000);

-- DML SELECT
SELECT *
FROM "tblGaji";

SELECT *
FROM "tblPengarang";

-- NO 1 --
SELECT COUNT("tblPengarang"."Kd_Pengarang") as "Jumlah Pengarang"
FROM "tblPengarang";

-- NO 2 --
SELECT COUNT(CASE WHEN tp."Kelamin" = 'p' THEN 1 END) AS "Perempuan",
       count(CASE WHEN tp."Kelamin" = 'W' THEN 1 END) AS "Laki - Laki"
FROM "tblPengarang" as tP;

-- NO 3 --
SELECT tP."Kota", count(tp."Kota") as "Jumlah"
from "tblPengarang" as tP
GROUP BY tP."Kota";

-- NO 4 --
SELECT tP."Kota", count(tP."Kota") as "Jumlah"
from "tblPengarang" as tP
GROUP BY tP."Kota"
HAVING count(tP."Kota") > 1;

-- NO 5 --
SELECT max(t."Kd_Pengarang") as "Terbesar",
       min(t."Kd_Pengarang") as "Terkeci zl"
FROM "tblPengarang" as t;

-- NO 6 --
SELECT max(g."Gaji") as "Terbesar",
       min(g."Gaji") as "Terkecil"
FROM "tblGaji" as g;

-- NO 7 --
SELECT g."Nama",
       g."Gaji"
FROM "tblGaji" as g
WHERE g."Gaji" > 600000;
-- NO 8 --
SELECT sum(g."Gaji") as "Jumlah Gaji"
FROM "tblGaji" as g;
-- NO 9 --
SELECT SUM(g."Gaji") as "Total Gaji",
       tp."Kota"
FROM
    "tblGaji" as g
        JOIN "tblPengarang" tP on g."Kd_Pengarang" = tP."Kd_Pengarang"
GROUP BY tp."Kota";

-- NO 10 --
SELECT *
FROM "tblPengarang" as tP
WHERE tp."Kd_Pengarang" between 'P0003' and 'P0006';

-- NO 11 --
SELECT *
FROM "tblPengarang" as tP
WHERE tp."Kota" IN ('Yogya', 'Solo', 'Magelang');

-- NO 12 --
SELECT *
FROM "tblPengarang" as tP
WHERE tp."Kota" NOT IN ('Yogya', 'Solo', 'Magelang');
-- NO 13 --
SELECT *
FROM "tblPengarang" as tP
WHERE tP."Nama" LIKE 'A%'
  AND tP."Nama" LIKE '%i'
  AND tP."Nama" LIKE '__a%'
  AND tP."Nama" NOT LIKE '%n';

-- NO 14 --
SELECT *
FROM "tblGaji" as g
         JOIN "tblPengarang" tP on g."Kd_Pengarang" = tP."Kd_Pengarang";

-- NO 15 --
SELECT SUM(g."Gaji") as "Total Gaji",
       tP."Kota"
FROM "tblGaji" as g
         JOIN "tblPengarang" tP on g."Kd_Pengarang" = tP."Kd_Pengarang"
GROUP BY tP."Kota"
HAVING sum(g."Gaji") < 1000000;

-- NO 16 --
ALTER TABLE "tblPengarang"
    ALTER COLUMN "Kelamin" TYPE varchar(10);

-- NO 17 --
ALTER TABLE "tblPengarang"
    ADD COLUMN "Gelar" varchar(12);

-- NO 18 --
UPDATE "tblPengarang"
SET "Alamat" = 'Jl. Cendrawasih 65',
    "Kota"   = 'Pekanbaru'
WHERE "Nama" = 'Rian';

CREATE VIEW vwPengarang AS
SELECT tP."Kd_Pengarang",
       tP."Nama",
       tP."Kota",
       tG."Gaji"
FROM "tblPengarang" as tP
         JOIN "tblGaji" tG on tP."Kd_Pengarang" = tG."Kd_Pengarang";

SELECT *
FROM vwPengarang;


DROP TABLE "tblGaji";
DROP TABLE "tblPengarang";
DROP VIEW vwPengarang;

SELECT "tblGaji"."Nama", "tblGaji"."Gaji"
FROM "tblGaji" WHERE "Gaji" = ()