create table genre (
	kd_genre VARCHAR(50) primary key,
	nama_genre VARCHAR(50) not null
);

create table negara (
	kd_negara VARCHAR(100) primary key,
	nama_negara VARCHAR(100) not null
);

create table artis (
	kd_artis VARCHAR(100) primary key,
	nama_artis VARCHAR(100) not null,
	jenis_kelamin VARCHAR(100) not null,
	bayaran INTEGER not null,
	award INTEGER not null,
	kd_negara VARCHAR(100) references negara(kd_negara)
);


create table produser (
	kd_produser VARCHAR(50) primary key,
	nama_produser VARCHAR(50) not null,
	international varchar(50) not null
);

create table film (
	kd_film VARCHAR(10) primary key,
	nama_film VARCHAR(55) not null,
	kd_genre VARCHAR(50) references genre(kd_genre),
	kd_artis VARCHAR(100) references artis(kd_artis),
	kd_produser VARCHAR(50) references produser(kd_produser),
	pendapatan INTEGER not null,
	nominasi INTEGER not null
);


insert into negara (kd_negara, nama_negara)
	values
		('AS', 'AMERIKA SERIKAT'),
		('HK', 'HONGKONG'),
		('ID', 'INDONESIA'),
		('IN', 'INDIA');

insert into genre (kd_genre, nama_genre)
	values
		('G001', 'ACTION'),
		('G002', 'HORROR'),
		('G003', 'COMEDY'),
		('G004', 'DRAMA'),
		('G005', 'THRILLER');


insert into artis (kd_artis, nama_artis, jenis_kelamin, bayaran, award, kd_negara)
	values
		('A001', 'ROBERT DOWNEY JR', 'PRIA', 1000000000, 2, 'AS'),
		('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
		('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
		('A004', 'JOE TASLIM', 'PRIA', 350000000, 1, 'ID'),
		('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID');

insert into produser (kd_produser, nama_produser, international)
	values
		('PD01', 'MARVEL', 'YA'),
		('PD02', 'HONGKONG CINEMA', 'YA'),
		('PD03', 'RAPI FILM', 'TIDAK'),
		('PD04', 'PARKIT', 'TIDAK'),
		('PD05', 'PARAMOUNT PICTURE', 'YA');

insert into film (kd_film, nama_film, kd_genre, kd_artis , kd_produser, pendapatan, nominasi)
	values
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
		('F012', 'RUSH HOUR', 'G001', 'A003', 'PD05', 695000000, 2),
		('F013', 'KUNGFU PANDA', 'G001', 'A003', 'PD05', 923000000, 5);

select * from produser;
select * from artis;
select * from film;
select * from genre;
select * from negara;

drop table if exists produser, film, artis, genre, negara;


-- TASK --

-- No. 1 --
SELECT produser.nama_produser, SUM(film.pendapatan) AS pendapatan 
FROM
	produser JOIN film ON produser.kd_produser = film.kd_produser 
WHERE 
    produser.nama_produser = 'MARVEL'
GROUP BY 
    produser.nama_produser;

-- No. 2 --
SELECT nama_film, nominasi from film where nominasi = 0;

-- No. 4 --
SELECT nama_film from film where LOWER(nama_film) like '%y';

-- No. 5 --
SELECT nama_film from film where LOWER(nama_film) like '%d%';

-- No. 6 --
SELECT nama_film, nama_artis
FROM
	film JOIN artis on film.kd_artis = artis.kd_artis;

-- No. 7 --
SELECT nama_film, nama_negara
FROM
	film JOIN negara on film.kd_negara

-- No. 7 --
SELECT nama_film, kd_negara
FROM film
JOIN artis ON film.kd_artis = artis.kd_artis
WHERE artis.kd_negara = 'HK';

-- 8


-- 9
SELECT nama_artis
FROM artis
LEFT JOIN film ON artis.kd_artis = film.kd_artis
WHERE film.kd_film IS NULL;

-- 10

SELECT artis.nama_artis, genre.nama_genre
FROM artis
JOIN film ON artis.kd_artis = film.kd_artis
JOIN genre ON film.kd_genre = genre.kd_genre
WHERE genre.nama_genre = 'DRAMA';

-- 11

SELECT DISTINCT artis.nama_artis, genre.nama_genre
FROM artis
JOIN film ON artis.kd_artis = film.kd_artis
JOIN genre ON film.kd_genre = genre.kd_genre
WHERE genre.nama_genre = 'ACTION';

-- 12

SELECT negara.nama_negara, COUNT(film.kd_film) AS jumlah_film
FROM negara
LEFT JOIN artis ON negara.kd_negara = artis.kd_negara
LEFT JOIN film ON artis.kd_artis = film.kd_artis
GROUP BY negara.nama_negara
ORDER BY negara.nama_negara;

-- 13
SELECT nama_film
FROM film 
INNER JOIN produser
ON film.kd_produser = produser.kd_produser
where produser.international = 'YA';

SELECT produser.nama_produser, COUNT(film.kd_film) AS jumlah_film
FROM produser	
LEFT JOIN film ON produser.kd_produser = film.kd_produser
GROUP BY produser.nama_produser;