CREATE DATABASE db_entertainer

CREATE TABLE artis(
    kd_artis VARCHAR(100) PRIMARY KEY NOT NULL,
    nm_artis VARCHAR(100) NOT NULL,
    jk VARCHAR(100) NOT NULL,
    bayaran INTEGER NOT NULL,
    award INTEGER NOT NULL,
    negara VARCHAR(100) NOT NULL
);
CREATE TABLE film(
    kd_film VARCHAR(10) NOT NULL,
    nm_film  VARCHAR(55) NOT NULL,
    genre VARCHAR(55) NOT NULL,
    artis VARCHAR(55) NOT NULL,
    produser  VARCHAR(55) NOT NULL,
    pendapatan INTEGER NOT NULL,
    nominasi INTEGER NOT NULL
);
CREATE TABLE produser(
    kd_produser VARCHAR(50) PRIMARY KEY NOT NULL,
    nm_produser VARCHAR(50) NOT NULL,
    international VARCHAR(50) NOT NULL
);

CREATE TABLE negara(
    kd_negara VARCHAR(100) PRIMARY KEY NOT NULL,
    nm_negara VARCHAR(100) NOT NULL
);
CREATE TABLE genre(
    kd_genre VARCHAR(50) PRIMARY KEY NOT NULL,
    nm_genre VARCHAR(50) NOT NULL
);


DROP TABLE artis;
DROP TABLE film;
DROP TABLE produser;
DROP TABLE negara;
DROP TABLE genre;
SELECT *
FROM information_schema.columns
WHERE table_name = ''




INSERT INTO artis(kd_artis, nm_artis, jk, bayaran, award, negara) VALUES
('A001', 'ROBERT DOWNEY JR', 'PRIA', 1000000000, 2, 'AS'),
('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
('A004', 'JOE TASLIM', 'PRIA', 350000000, 1, 'ID'),
('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID');

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

INSERT INTO produser (kd_produser, nm_produser, international)
VALUES
('PD01', 'MARVEL', 'YA'),
('PD02', 'HONGKONG CINEMA', 'YA'),
('PD03', 'RAFI FILM', 'TIDAK'),
('PD04', 'PARKIT', 'TIDAK'),
('PD05', 'PARAMOUNT PICTURE', 'YA');

INSERT INTO negara (kd_negara, nm_negara)
VALUES
('AS', 'AMERIKA SERIKAT'),
('HK', 'HONGKONG'),
('ID', 'INDONESIA'),
('IN', 'INDIA');

INSERT INTO genre (kd_genre, nm_genre)
VALUES
('G001', 'ACTION'),
('G002', 'HORROR'),
('G003', 'COMEDY'),
('G004', 'DRAMA'),
('G005', 'THRILLER'),
('G006', 'FICTION');

SELECT * FROM film
SELECT * FROM artis

-- No. 1
SELECT nm_produser, SUM(f.pendapatan)
FROM produser pd
JOIN film f
ON f.produser = pd.kd_produser
GROUP BY nm_produser
HAVING nm_produser LIKE 'MARVEL';

-- No. 2
SELECT nm_film, nominasi
FROM film
WHERE nominasi = 0;

-- No. 3
SELECT nm_film
FROM film
WHERE nm_film LIKE 'P%'

-- No. 4
SELECT nm_film
FROM film
WHERE nm_film LIKE '%Y'

-- No. 5
SELECT nm_film
FROM film
WHERE nm_film LIKE '%D%'

-- No. 6
SELECT f.nm_film, a.nm_artis
FROM film f
JOIN artis a
ON a.kd_artis = f.artis

-- No. 7
SELECT f.nm_film, a.negara
FROM film f
JOIN artis a
ON a.kd_artis = f.artis
WHERE a.negara LIKE 'HK'

-- No. 8
SELECT f.nm_film, n.nm_negara
FROM film f
JOIN artis a
ON a.kd_artis = f.artis
JOIN negara n
ON n.kd_negara = a.negara
WHERE n.nm_negara NOT LIKE '%O%'

-- No. 9
SELECT a.nm_artis
FROM artis a
WHERE a.kd_artis NOT IN(SELECT artis FROM film);

-- No. 10
SELECT a.nm_artis, g.nm_genre
FROM film f
JOIN artis a ON a.kd_artis = f.artis
JOIN genre g ON g.kd_genre = f.genre
WHERE g.nm_genre LIKE 'DRAMA'

-- No. 11
SELECT a.nm_artis, g.nm_genre
FROM film f
JOIN artis a ON a.kd_artis = f.artis
JOIN genre g ON g.kd_genre = f.genre
WHERE g.nm_genre LIKE 'ACTION'
GROUP BY a.nm_artis, g.nm_genre

-- No. 12
SELECT n.kd_negara, n.nm_negara, COUNT(f.artis)
FROM negara n
LEFT JOIN artis a ON a.negara = n.kd_negara
LEFT JOIN film f ON a.kd_artis = f.artis
GROUP BY n.kd_negara
ORDER BY n.nm_negara

SELECT nm_film
FROM film f
JOIN produser p ON p.kd_produser = f.produser
WHERE p.international LIKE 'YA'

SELECT p.nm_produser, COUNT(f.produser)
FROM film f
RIGHT JOIN produser p ON p.kd_produser = f.produser
GROUP BY p.nm_produser
ORDER BY p.nm_produser

