CREATE DATABASE dbentertainer;

CREATE TABLE artis
(kdartis VARCHAR(100) PRIMARY KEY NOT NULL,
namaartis VARCHAR(100) NOT NULL,
jk VARCHAR(100) NOT NULL,
bayaran INTEGER NOT NULL,
award INTEGER NOT NULL,
negara VARCHAR(100) NOT NULL);

CREATE TABLE film
(kdfilm VARCHAR(10) PRIMARY KEY NOT NULL,
namafilm VARCHAR(55) NOT NULL,
genre VARCHAR(55) NOT NULL,
artis VARCHAR(55) NOT NULL,
produser VARCHAR(55) NOT NULL,
pendapatan INTEGER NOT NULL,
nominal INTEGER NOT NULL);

CREATE TABLE produser
(kdproduser VARCHAR(50) PRIMARY KEY NOT NULL,
namaproduser VARCHAR(50) NOT NULL,
international VARCHAR(50) NOT NULL);

CREATE TABLE negara
(kdnegara VARCHAR(100) PRIMARY KEY NOT NULL,
namanegara VARCHAR(100) NOT NULL);

CREATE TABLE genre(
    kdgenre VARCHAR(50) PRIMARY KEY NOT NULL,
    namagenre VARCHAR(50) NOT NULL);

INSERT INTO artis
VALUES('A001','Robert Downer JR','Pria',000000000,2,'AS'),
      ('A002','Angelina Jolie','Wanita',700000000,1,'AS'),
      ('A003','Jackie Chan','Pria',200000000,7,'HK'),
      ('A004','Joe Taslim','Pria',350000000,1,'ID'),
      ('A005','Chelsea Islan','Wanita',300000000,0,'ID');

UPDATE artis SET bayaran=300000000
WHERE kdartis='A001';
SELECT * from artis;

INSERT INTO film
VALUES ('F001','Iron Man','G001','A001','PD01',2000000000,3),
       ('F002','Iron Man 2','G001','A001','PD01','1800000000',2),
       ('F003','Iron Man 3','G001','A001','PD01','1200000000',0),
       ('F004','Avenger Civil War','G001','A001','PD01','2000000000',1),
       ('F005','Spider Man Home Coming','G001','A001','PD01',1300000000,0),
       ('F006','The Raid','G001','A004','PD03',800000000,5),
       ('F007','Fast & Furious','G001','A004','PD05',830000000,2),
       ('F008','Habibie dan Ainun','G004','A005','PD03',670000000,4),
       ('F009','Police Story','G001','A003','PD02',700000000,3),
       ('F010','Police Story 2','G001','A003','PD02',710000000,1),
       ('F011','Police Story 3','G001','A003','PD02',615000000,0),
       ('F012','Rush Hour','G003','A003','PD05',695000000,2),
       ('F013','Kungfu Panda','G003','A003','PD05',923000000,5);

INSERT INTO produser
VALUES ('PD01','Marvel','YA'),
       ('PD02','Hongkong Cinema','YA'),
       ('PD03','Rapi Film','Tidak'),
       ('PD04','Parkit','Tidak'),
       ('PD05','Paramount Picture','YA')

INSERT INTO negara
VALUES ('AS','Amerika Serikat'),
       ('HK','Hongkong'),
       ('ID','Indonesia'),
       ('IN','India');

INSERT INTO genre
VALUES ('G001','Action'),
       ('G002','Horror'),
       ('G003','Comedy'),
       ('G004','Drama'),
       ('G005','Thriller'),
       ('G006','Fiction');

--No.1
SELECT namaproduser,SUM(pendapatan) 
from produser JOIN film on produser.kdproduser=film.produser
WHERE namaproduser='Marvel' GROUP BY namaproduser;

--no.2
SELECT namafilm,nominasi from film WHERE nominasi=0;

--no.3
SELECT namafilm from film WHERE namafilm like 'P%';

--no.4
SELECT namafilm from film WHERE namafilm like '%y';

--no.5
SELECT namafilm from film WHERE namafilm like '%d%';

--no.6
SELECT namafilm,namaartis from film join artis on film.artis=artis.kdartis;

--no.7
SELECT namafilm,negara from film join artis on film.artis=artis.kdartis
WHERE negara='HK';

--no.8
SELECT namafilm,namanegara from film join artis on film.artis=artis.kdartis
join negara on artis.negara=negara.kdnegara
WHERE namanegara not LIKE '%o%';

--no.9
SELECT namaartis from artis Left join film on artis.kdartis=film.artis
WHERE namafilm is NULL;

--no.10
SELECT namaartis,namagenre from artis join film on artis.kdartis=film.artis
join genre on genre.kdgenre=film.genre
WHERE namagenre='Drama';

--no.11
SELECT DISTINCT namaartis,namagenre from artis join film on artis.kdartis=film.artis
join genre on genre.kdgenre=film.genre
WHERE namagenre='Action';

--no.12
SELECT kdnegara,namanegara,COUNT(namafilm) from artis FULL OUTER join film on artis.kdartis=film.artis
FULL OUTER join negara on artis.negara=negara.kdnegara GROUP BY kdnegara;

--no.13
SELECT namafilm from film join produser on film.produser=produser.kdproduser
WHERE international='YA';

--no.14
SELECT namaproduser,COUNT(namafilm) from film FULL OUTER join produser on film.produser=produser.kdproduser
GROUP BY namaproduser;