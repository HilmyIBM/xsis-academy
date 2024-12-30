CREATE DATABASE db_entertainer;

CREATE TABLE artis(
    kd_artis VARCHAR(100) PRIMARY KEY,
    nm_artis VARCHAR(100),
    jk VARCHAR(100),
    bayaran INTEGER,
    award INTEGER,
    negara VARCHAR(100)
);

CREATE TABLE produser(
    kd_produser VARCHAR(50) PRIMARY KEY,
    nm_produser VARCHAR(50),
    international VARCHAR(50)
);


CREATE TABLE negara(
    kd_negara VARCHAR(100) PRIMARY KEY,
    nm_negara VARCHAR(100)
);

CREATE TABLE genre(
    kd_genre VARCHAR(50) PRIMARY KEY,
    nm_genre VARCHAR(50)
);

CREATE TABLE film(
    kd_film VARCHAR(10) PRIMARY KEY,
    nm_film VARCHAR(55),
    genre VARCHAR(55),
    artis VARCHAR(55),
    produser VARCHAR(55),
    pendapatan INTEGER,
    nominasi INTEGER
);

INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara)
VALUES
    ('A001', 'ROBERT DOWNEY JR', 'PRIA', 1000000000, 2, 'AS'),
    ('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
    ('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
    ('A004', 'JOE TASLIM', 'PRIA', 350000000, 0, 'ID'),
    ('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID')
;

INSERT INTO produser (kd_produser, nm_produser, international)
VALUES
    ('PD01', 'MARVEL', 'YA'),
    ('PD02', 'HONGKONG CINEMA', 'YA'),
    ('PD03', 'RAFI FILM', 'TIDAK'),
    ('PD04', 'PARKIT', 'TIDAK'),
    ('PD05', 'PARAMOUNT PICTURE', 'YA')
;

INSERT INTO negara (kd_negara, nm_negara)
VALUES
    ('AS', 'AMERIKA SERIKAT'),
    ('HK', 'HONGKONG'),
    ('ID', 'INDONESIA'),
    ('IN', 'INDIA')
;

INSERT INTO genre (kd_genre, nm_genre)
VALUES
    ('G001', 'ACTION'),
    ('G002', 'HORROR'),
    ('G003', 'COMEDY'),
    ('G004', 'DRAMA'),
    ('G005', 'THRILLER'),
    ('G006', 'FICTION')
;

INSERT INTO film(kd_film, nm_film, genre, artis, produser, pendapatan, nominasi)
VALUES
    ('F001', 'IRON MAN', 'G001', 'A001', 'PD01', 2000000000, 3),
    ('F002', 'IRON MAN 2', 'G001', 'A001', 'PD01', 1800000000, 2),
    ('F003', 'IRON MAN 3', 'G001', 'A001', 'PD01', 1200000000, 0),
    ('F004', 'AVENGER: CIVIL WAR', 'G001', 'A001', 'PD01', 2000000000, 1),
    ('F005', 'SPIDERMAN HOME COMING', 'G001', 'A001', 'PD01', 1300000000, 0),
    ('F006', 'THE RAID', 'G001', 'A004', 'PD03', 800000000, 5),
    ('F007', 'FAST & FURIOUS', 'G001', 'A004', 'PD05', 830000000, 2),
    ('F008', 'HABIBIE DAN AINUN', 'G004', 'A005', 'PD03', 670000000, 4),
    ('F009', 'POLICE STORY', 'G001', 'A003', 'PD02', 700000000, 3),
    ('F010', 'POLICE STORY 2', 'G001', 'A003', 'PD02', 710000000, 1),
    ('F011', 'POLICE STORY 3', 'G001', 'A003', 'PD02', 615000000, 0),
    ('F012', 'RUSH HOUR', 'G003', 'A003', 'PD05', 695000000, 2),
    ('F013', 'KUNGFU PANDA', 'G003', 'A003', 'PD05', 923000000, 5)
;


-- 1
SELECT p.nm_produser, SUM(f.pendapatan) as pendapatan
FROM produser p JOIN film f 
ON p.kd_produser = f.produser
WHERE p.nm_produser = 'MARVEL'
GROUP BY p.nm_produser;

-- 2
SELECT nm_film, nominasi FROM film
WHERE nominasi = 0;

-- 3
SELECT nm_film FROM film
WHERE nm_film LIKE 'P%';

-- 4
SELECT nm_film FROM film
WHERE nm_film LIKE '%Y';

-- 5
SELECT nm_film FROM film
WHERE nm_film LIKE '%D%';

-- 6
SELECT a.nm_film, b.nm_artis
FROM film a join artis b 
ON a.artis = b.kd_artis;

-- 7
SELECT a.nm_film AS nama_film, c.kd_negara AS negara
FROM film a
JOIN artis b ON a.artis = b.kd_artis
JOIN negara c ON b.negara = c.kd_negara
WHERE b.negara = 'HK';

-- 8
SELECT a.nm_film AS nama_film, c.nm_negara AS negara
FROM film a
JOIN artis b ON a.artis = b.kd_artis
JOIN negara c ON b.negara = c.kd_negara
WHERE c.nm_negara NOT LIKE '%O%';

-- 9
-- SELECT b.nm_artis
-- FROM film a join artis b 
-- ON a.artis = b.kd_artis
-- GROUP BY b.nm_artis
-- WHERE NOT IN FILM;

SELECT b.nm_artis
FROM artis b
WHERE b.kd_artis NOT IN 
    (SELECT a.artis FROM film a)
GROUP BY b.nm_artis;

-- 10
SELECT b.nm_artis AS nm_artis, c.nm_genre AS genre
FROM film a
JOIN artis b ON a.artis = b.kd_artis
JOIN genre c ON a.genre = c.kd_genre
WHERE c.nm_genre = 'DRAMA';

-- 11
SELECT b.nm_artis AS nm_artis, c.nm_genre AS genre
FROM film a
JOIN artis b ON a.artis = b.kd_artis
JOIN genre c ON a.genre = c.kd_genre
WHERE c.nm_genre = 'ACTION'
GROUP BY b.nm_artis, c.nm_genre;

-- 12
-- SELECT c.kd_negara AS kd_negara, c.nm_negara AS nm_negara, COUNT(*) AS jumlah_film
-- FROM film a
-- JOIN artis b ON a.artis = b.kd_artis
-- JOIN negara c ON b.negara = c.kd_negara
-- GROUP BY c.kd_negara;

SELECT c.kd_negara AS kd_negara, c.nm_negara AS nm_negara, 
       COUNT(a.kd_film) AS jumlah_film
FROM negara c
LEFT JOIN artis b ON b.negara = c.kd_negara
LEFT JOIN film a ON a.artis = b.kd_artis
GROUP BY c.kd_negara, c.nm_negara
ORDER BY c.kd_negara;

-- 13
SELECT a.nm_film
FROM film a 
JOIN produser b ON a.produser = b.kd_produser
WHERE international = 'YA';

-- 14
SELECT b.nm_produser, COUNT(a.produser)
FROM film a
RIGHT JOIN produser b on a.produser = b.kd_produser
GROUP BY b.nm_produser;


-- SELECT *
-- INTO film_bak
-- FROM film;

CREATE DATABASE db_hr;

SELECT * FROM pg_database;


