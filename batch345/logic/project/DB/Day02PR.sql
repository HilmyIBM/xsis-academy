DROP DATABASE "DB_Entertainer";

CREATE DATABASE "DB_Entertainer";
--
CREATE TABLE negara
(
    kd_negara char(3) primary key not null,
    nm_negara varchar(64)         not null
);

CREATE TABLE genre
(
    kd_genre char(4) primary key not null,
    nm_genre varchar(24)         not null
);

CREATE TABLE produser
(
    kd_produser   char(4) primary key not null,
    nm_produser   varchar(64)         not null,
    international varchar(5)          not null
);

CREATE TABLE artis
(
    kd_artis char(4) PRIMARY KEY not null,
    nm_artis varchar(64)         not null,
    jk       varchar(10)         not null,
    bayaran  BIGINT              not null,
    award    int2                not null,
    negara   varchar(3) references negara (kd_negara)
);


CREATE TABLE film
(
    kd_film    char(4) primary key not null,
    nm_film    varchar(128)        not null,
    genre      char(4)             not null references genre (kd_genre) ,
    artis      char(4)             not null references artis (kd_artis),
    produser   char(4)             not null references produser (kd_produser),
    pendapatan integer,
    nominasi   integer
);

INSERT INTO genre (kd_genre, nm_genre)
VALUES ('G001', 'ACTION'),
       ('G002', 'HORROR'),
       ('G003', 'COMEDY'),
       ('G004', 'DRAMA'),
       ('G005', 'THRILLER'),
       ('G006', 'FICTION');

INSERT INTO negara (kd_negara, nm_negara)
VALUES ('AS', 'AMERIKA SERIKAT'),
       ('HK', 'HONGKONG'),
       ('ID', 'INDONESIA'),
       ('IN', 'INDIA');

INSERT INTO produser (kd_produser, nm_produser, international)
VALUES ('P001', 'MARVEL', 'YA'),
       ('P002', 'HONGKONG CINEMA', 'YA'),
       ('P003', 'RAPI FILM', 'TIDAK'),
       ('P004', 'PARKIT', 'TIDAK'),
       ('P005', 'PARAMOUNT PICTURE', 'YA');

INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara)
VALUES ('A001', 'ROBERT DOWNEY JR', 'PRIA', 5000000000, 2, 'AS'),
       ('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
       ('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
       ('A004', 'JOE TASLIM', 'PRIA', 350000000, 1, 'ID'),
       ('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID');

INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi)
VALUES ('P001', 'IRON MAN', 'G001', 'A001', 'P001', 2000000000, 3),
       ('P002', 'IRON MAN 2', 'G001', 'A001', 'P001', 1800000000, 2),
       ('P003', 'IRON MAN 3', 'G001', 'A001', 'P001', 1200000000, 0),
       ('P004', 'AVANGER : CIVIL WAR', 'G001', 'A001', 'P001', 2000000000, 1),
       ('P005', 'SPIDERMAN : HOME COMING', 'G001', 'A001', 'P001', 1200000000, 0),
       ('P006', 'SPIDERMAN : HOME COMING', 'G001', 'A001', 'P001', 1200000000, 0),
       ('P007', 'FAST & FURIOUS', 'G001', 'A004', 'P005', 830000000, 2),
       ('P008', 'HABIBIE DAN AINUN', 'G004', 'A005', 'P003', 670000000, 4),
       ('P009', 'POLICE STORY', 'G001', 'A003', 'P002', 700000000, 3),
       ('P010', 'POLICE STORY 2', 'G001', 'A003', 'P002', 710000000, 1),
       ('P011', 'POLICE STORY 3', 'G001', 'A003', 'P002', 615000000, 0),
       ('P012', 'RUSH HOUR', 'G003', 'A003', 'P005', 695000000, 2),
       ('P013', 'KUNGFU PANDA', 'G003', 'A003', 'P005', 923000000, 5);


------------------- Task -------------------

-- NO 1
SELECT p.nm_produser, SUM(f.pendapatan)
FROM produser p JOIN film f on p.kd_produser = f.produser
WHERE p.nm_produser = 'MARVEL'
GROUP BY p.nm_produser;

-- NO 2
SELECT nm_film, nominasi
FROM film
WHERE nominasi < 1;

-- NO 3
SELECT film.nm_film
FROM film
WHERE nm_film LIKE 'P%';

-- NO 4
SELECT film.nm_film
FROM film
WHERE nm_film LIKE '%Y';

-- NO 5
SELECT film.nm_film
FROM film
WHERE nm_film LIKE '%D%';

-- NO 6
SELECT f.nm_film, a.nm_artis
FROM film f
JOIN artis a on a.kd_artis = f.artis
ORDER BY a.nm_artis DESC;

-- NO 7
SELECT f.nm_film, a.negara
FROM film f
JOIN artis a on a.kd_artis = f.artis
JOIN negara n on n.kd_negara = a.negara
WHERE n.kd_negara = 'HK';

-- NO 8
SELECT f.nm_film, n.nm_negara
FROM film f
         JOIN artis a on a.kd_artis = f.artis
         JOIN negara n on n.kd_negara = a.negara
WHERE n.nm_negara NOT ILIKE '%o%';

-- NO 9
SELECT a.nm_artis
FROM film f
    RIGHT JOIN artis a on a.kd_artis = f.artis
WHERE f.artis is NULL;

-- NO 10

SELECT a.nm_artis, g.nm_genre
FROM film f
    JOIN genre g on g.kd_genre = f.genre
    JOIN artis a on a.kd_artis = f.artis
WHERE g.nm_genre = 'DRAMA';

-- NO 11
SELECT a.nm_artis, g.nm_genre
FROM film f
         JOIN genre g on g.kd_genre = f.genre
         JOIN artis a on a.kd_artis = f.artis
WHERE g.nm_genre = 'ACTION'
GROUP BY a.nm_artis, g.nm_genre;

-- NO 12
SELECT
    g.kd_negara,
    g.nm_negara,
    count(f) as jumlah_film
FROM negara g
         FULL OUTER JOIN artis a on g.kd_negara = a.negara
         LEFT JOIN film f on a.kd_artis = f.artis
group by g.kd_negara, g.nm_negara;

SELECT
    n.nm_negara,
    n.kd_negara,
    count(f.kd_film) as jumlah_film
FROM film f
    INNER JOIN artis a on f.artis = a.kd_artis
    RIGHT JOIN negara n on n.kd_negara = a.negara
group by n.kd_negara;

-- NO 13
SELECT
    f.nm_film
FROM
    film f
    INNER JOIN produser p on p.kd_produser = f.produser
WHERE p.international = 'YA';

-- NO 14
SELECT
    p.nm_produser,
    count(f.produser)
FROM
    film f
        RIGHT JOIN produser p on p.kd_produser = f.produser
GROUP BY p.nm_produser;

CREATE DATABASE northwind