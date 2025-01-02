CREATE DATABASE DB_Entertainer;

--setup table artis
CREATE TABLE artis(
    kd_artis VARCHAR(100) PRIMARY KEY,
    nm_artis VARCHAR(100),
    jk VARCHAR(100),
    bayaran BIGINT,
    award INTEGER,
    negara VARCHAR(100) REFERENCES negara(kd_negara)
);

DROP TABLE artis;

INSERT INTO artis(kd_artis, nm_artis, jk, bayaran, award, negara)
VALUES ('A001', 'ROBERT DOWNEY JR', 'PRIA', 3000000000, 2, 'AS'),
 ('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
 ('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
 ('A004', 'JOE TASLIM', 'PRIA', 350000000, 1, 'ID'),
 ('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID');

SELECT * FROM artis;

SELECT * FROM artis;


drop TABLE film;
--setup table film
CREATE TABLE film(
    kd_film VARCHAR(10) PRIMARY KEY,
    nm_film VARCHAR(55),
    genre VARCHAR(55) REFERENCES genre(kd_genre),
    artis VARCHAR(55) REFERENCES artis(kd_artis),
    produser VARCHAR(55) REFERENCES produser(kd_produser),
    pendapatan BIGINT,
    nominasi INTEGER
);

DROP TABLE film;

INSERT INTO film(kd_film, nm_film, genre, artis, produser, pendapatan, nominasi)
VALUES ('F001', 'IRON MAN', 'G001', 'A001', 'PD01', 200000000000, 3),
 ('F002', 'IRON MAN 2', 'G001', 'A001', 'PD01', 180000000000, 2),
 ('F003', 'IRON MAN 3', 'G001', 'A001', 'PD01', 120000000000, 0),
 ('F004', 'AVENGER CIVIL WAR', 'G001', 'A001', 'PD01', 200000000000, 1),
 ('F005', 'SPIDERMAN HOME COMING', 'G001', 'A001', 'PD01', 130000000000, 0),
 ('F006', 'THE RAID', 'G001', 'A004', 'PD03', 80000000000, 5),
 ('F007', 'FAST & FURIOUS', 'G001', 'A004', 'PD05', 83000000000, 2),
 ('F008', 'HABIBIE DAN AINUN', 'G004', 'A005', 'PD03', 67000000000, 4),
 ('F009', 'POLICE STORY', 'G001', 'A003', 'PD02', 70000000000, 3),
 ('F010', 'POLICE STORY 2', 'G001', 'A003', 'PD02', 71000000000, 1),
 ('F011', 'POLICE STORY 3', 'G001', 'A003', 'PD02', 61500000000, 0),
 ('F012', 'RUSH HOUR', 'G003', 'A003', 'PD05', 69500000000, 2),
 ('F013', 'KUNGFU PANDA', 'G003', 'A003', 'PD05', 92300000000, 5);

SELECT * FROM PUBLIC.film;


--setup table produser

CREATE TABLE produser(
    kd_produser VARCHAR(50) PRIMARY KEY,
    nm_produser VARCHAR(50),
    international VARCHAR(50)
);

INSERT INTO produser(kd_produser, nm_produser, international)
VALUES ('PD01', 'MARVEL', 'YA'),
 ('PD02', 'HONGKONG CINEMA', 'YA'),
 ('PD03', 'RAPI FILM', 'TIDAK'),
 ('PD04', 'PARKIT', 'TIDAK'),
 ('PD05', 'PARAMOUNT PICTURE', 'YA');

SELECT * FROM public.produser

drop TABLE produser;

--setup table negara

CREATE TABLE negara(
    kd_negara VARCHAR(100) PRIMARY KEY,
    nm_negara VARCHAR(100)
);

INSERT INTO negara(kd_negara, nm_negara)
VALUES ('AS', 'AMERIKA SERIKAT'),
 ('HK', 'HONGKONG'),
 ('ID', 'INDONESIA'),
 ('IN', 'INDIA');

SELECT * FROM public.negara



--setup table genre

CREATE TABLE genre(
    kd_genre VARCHAR(50) PRIMARY KEY,
    nm_genre VARCHAR(50)
);

INSERT INTO genre(kd_genre, nm_genre)
VALUES ('G001', 'ACTION'),
 ('G002', 'HORROR'),
 ('G003', 'COMEDY'),
 ('G004', 'DRAMA'),
 ('G005', 'THRILLER'),
 ('G006', 'FICTION');


SELECT * FROM public.genre


--start soal

--no 1
SELECT prod.nm_produser, SUM(film.pendapatan) AS total_pendapatan
FROM produser AS prod
INNER JOIN film AS film ON film.produser = prod.kd_produser
WHERE film.produser = 'PD01'
GROUP BY prod.nm_produser;


--no 2
SELECT nm_film, nominasi FROM film 
WHERE nominasi = 0;


--no 3
SELECT nm_film FROM film
WHERE nm_film LIKE 'P%';


--no 4
SELECT nm_film FROM film
WHERE nm_film LIKE '%Y';


--no 5
SELECT nm_film FROM film
WHERE UPPER(nm_film) LIKE '%D%';

--no 6
SELECT film.nm_film, artis.nm_artis
FROM film as film 
INNER JOIN artis as artis ON film.artis = artis.kd_artis;

--no 7
SELECT film.nm_film AS "Nama Film", artis.negara AS "Nama Negara"
FROM film as film 
INNER JOIN artis as artis ON film.artis = artis.kd_artis
WHERE artis.negara = 'HK';

--no 8
SELECT film.nm_film AS "Nama Film", negara.nm_negara AS "Nama Negara"
FROM film
INNER JOIN artis ON film.artis = artis.kd_artis
INNER JOIN negara ON artis.negara = negara.kd_negara
WHERE negara.nm_negara NOT LIKE '%O%';


--no 9
SELECT artis.nm_artis AS "Nama Artis"
FROM artis LEFT JOIN film on film.artis = artis.kd_artis
WHERE film.kd_film is NULL;
--left join untuk ambil irisan kiri (artis) dan cek yang ga ada corresponding data di kanan


--no 10
SELECT artis.nm_artis, genre.nm_genre
FROM artis 
INNER JOIN film on film.artis = artis.kd_artis
INNER JOIN genre on genre.kd_genre = film.genre
WHERE genre.nm_genre = 'DRAMA';


--no 11
SELECT artis.nm_artis, genre.nm_genre
FROM artis 
INNER JOIN film on film.artis = artis.kd_artis
INNER JOIN genre on genre.kd_genre = film.genre
WHERE genre.nm_genre = 'ACTION'
GROUP BY artis.nm_artis, genre.nm_genre;


--no 12
SELECT negara.kd_negara, negara.nm_negara, COUNT (film.*) AS "jumlah_Film"
FROM artis
INNER JOIN film ON film.artis = artis.kd_artis
RIGHT OUTER JOIN negara ON artis.negara = negara.kd_negara -- kalo pake full, misal ada null di sisi kiri nnti ke show
GROUP BY negara.kd_negara
ORDER BY negara.nm_negara;

--no 13
SELECT film.nm_film
FROM film
INNER JOIN produser ON film.produser = produser.kd_produser
WHERE produser.international = 'YA';


--no 14
SELECT produser.nm_produser, COUNT(film.*)
FROM film
FULL OUTER JOIN produser ON film.produser = produser.kd_produser
GROUP BY produser.nm_produser
ORDER BY produser.nm_produser;