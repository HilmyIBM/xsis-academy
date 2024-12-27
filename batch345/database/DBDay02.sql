CREATE TABLE artis (
    kd_artis VARCHAR(100) PRIMARY KEY NOT NULL,
    nm_artis VARCHAR(100) NOT NULL,
    jk VARCHAR(100) NOT NULL,
    bayaran INTEGER NOT NULL,
    award INTEGER NOT NULL,
    negara VARCHAR(100) NOT NULL
);

INSERT INTO artis(kd_artis, nm_artis, jk, bayaran, award, negara) VALUES
('A001', 'ROBERT DOWNEY JR', 'PRIA', 1000000000, 2, 'AS'),
('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
('A004', 'JOE TASLIM', 'PRIA', 350000000, 1, 'ID'),
('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID');

SELECT * FROM artis;

CREATE TABLE film (
    kd_film VARCHAR(10) PRIMARY KEY NOT NULL,
    nm_film VARCHAR(55) NOT NULL,
    genre VARCHAR(55) NOT NULL,
    artis VARCHAR(55) NOT NULL,
    produser VARCHAR(55) NOT NULL,
    pendapatan INTEGER NOT NULL,
    nominasi INTEGER NOT NULL
);

DROP TABLE film;

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
('F013', 'KUNGFU PANDA', 'G003', 'A003', 'PD05', 923000000, 5);

CREATE TABLE produser (
    kd_produser VARCHAR(50) PRIMARY KEY NOT NULL,
    nm_produser VARCHAR(50) NOT NULL,
    international VARCHAR(50) NOT NULL
);

INSERT INTO produser (kd_produser, nm_produser, international) VALUES
('PD01', 'MARVEL', 'YA'),
('PD02', 'HONGKONG CINEMA', 'YA'),
('PD03', 'RAPI FILM', 'TIDAK'),
('PD04', 'PARKIT', 'TIDAK'),
('PD05', 'PARAMOUNT PICTURE', 'YA');

CREATE TABLE negara (
    kd_negara VARCHAR(100) PRIMARY KEY NOT NULL,
    nm_negara VARCHAR(100) NOT NULL
);

INSERT INTO negara (kd_negara, nm_negara) VALUES
('AS', 'AMERIKA SERIKAT'),
('HK', 'HONGKONG'),
('ID', 'INDONESIA'),
('IN', 'INDIA');

CREATE TABLE genre (
    kd_genre VARCHAR(50) PRIMARY KEY NOT NULL,
    nm_genre VARCHAR(50) NOT NULL
);

INSERT INTO genre (kd_genre, nm_genre) VALUES
('G001', 'ACTION'),
('G002', 'HORROR'),
('G003', 'COMEDY'),
('G004', 'DRAMA'),
('G005', 'THRILLER'),
('G006', 'FICTION');

SELECT * FROM genre;

-- No 1
SELECT p.nm_produser, SUM(f.pendapatan) FROM produser p
JOIN film f ON p.kd_produser = f.produser
WHERE p.nm_produser = 'MARVEL'
GROUP BY p.nm_produser;

-- No 2
SELECT nm_film, nominasi FROM film
WHERE nominasi=0;

-- No 3
SELECT nm_film FROM film
WHERE nm_film ILIKE 'p%';

-- No 4
SELECT nm_film FROM film
WHERE nm_film ILIKE '%y';

-- No 5
SELECT nm_film FROM film
WHERE nm_film ILIKE '%d%';

-- No 6
SELECT f.nm_film, a.nm_artis FROM FILM f
JOIN artis a ON f.artis = a.kd_artis;

-- No 7
SELECT f.nm_film, a.negara FROM film f
JOIN artis a ON f.artis = a.kd_artis
WHERE a.negara='HK';

-- No 8
SELECT f.nm_film, n.nm_negara FROM film f
JOIN artis a ON f.artis = a.kd_artis JOIN negara n ON a.negara = n.kd_negara
WHERE n.nm_negara NOT ILIKE '%o%';

-- No 9
SELECT a.nm_artis FROM artis a
WHERE NOT EXISTS (
    SELECT f.kd_film
    FROM film f
    WHERE f.artis = a.kd_artis
);

-- No 10
SELECT DISTINCT a.nm_artis, g.nm_genre FROM artis a
JOIN film f ON a.kd_artis = f.artis JOIN genre g ON f.genre = g.kd_genre
WHERE g.nm_genre = 'DRAMA';

-- No 11
SELECT DISTINCT a.nm_artis, g.nm_genre FROM artis a
JOIN film f ON a.kd_artis = f.artis JOIN genre g ON f.genre = g.kd_genre
WHERE g.nm_genre = 'ACTION';

-- No 12
SELECT n.kd_negara, n.nm_negara, COUNT(f.kd_film) AS jumlah_film FROM negara n
LEFT JOIN artis a ON n.kd_negara = a.negara LEFT JOIN film f ON a.kd_artis=f.artis
GROUP BY n.kd_negara
ORDER BY n.nm_negara ASC;

-- No 13
SELECT f.nm_film FROM film f
JOIN produser p ON f.produser = p.kd_produser
WHERE p.international = 'YA'
ORDER BY f.genre;

-- No 14
SELECT p.nm_produser, COUNT(f.kd_film) AS jumlah_film FROM produser p
LEFT JOIN film f ON p.kd_produser = f.produser
GROUP BY p.nm_produser
ORDER BY p.nm_produser ASC;

CREATE DATABASE DB_HR;
