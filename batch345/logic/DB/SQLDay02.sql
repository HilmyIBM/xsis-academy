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

SELECT id, nama, deskripsi
INTO coba_bak_2
FROM coba01;


--DML Read Data
select * from coba01;
select * from coba_bak;
SELECT * FROM "cobaPertama";
select * from pelanggan;

--1. Menampilkan jumlah pendapatan produser marvel secara keseluruhan											
select * from produser;
select * from film;

SELECT P.nm_produser, SUM(f.pendapatan) jml_pedapatan
from produser P INNER JOIN film F
    on P.kd_produser = F.produser
where lower(P.nm_produser)='marvel'
group by P.nm_produser;

--2. Menampilkan nama film dan nominasi yang tidak mendapatkan nominasi											
SELECT nm_film, nominasi
FROM film
WHERE nominasi=0;

--6. Menampilkan nama film dan artis						
SELECT film.nm_film as nama_film, artis.nm_artis as nama_artis
FROM artis INNER JOIN film
    ON artis.kd_artis = film.artis;

--7. Menampilkan nama film yang artisnya berasal dari negara hongkong
select * from film;
select * from artis;
select * from negara;

select * from produser;
select * from genre;

SELECT F.nm_film, A.negara
FROM film F
    INNER JOIN artis A ON F.artis=A.kd_artis
    INNER JOIN negara N ON A.negara=N.kd_negara 
WHERE
    lower(N.nm_negara)='hongkong';

--8. Menampilkan nama film yang artisnya bukan berasal dari negara yang mengandung huruf 'o'
SELECT F.nm_film, N.nm_negara
FROM film F
    INNER JOIN artis A ON F.artis=A.kd_artis
    INNER JOIN negara N ON A.negara=N.kd_negara
WHERE lower(N.nm_negara) NOT LIKE '%o%';

--9. Menampilkan nama artis yang tidak pernah bermain film
SELECT A.nm_artis, F.kd_film
FROM artis A
    LEFT JOIN film F ON A.kd_artis=F.artis
WHERE F.kd_film IS NULL;

--10. Menampilkan nama artis yang bermain film dengan genre drama
SELECT artis.nm_artis, genre.nm_genre
FROM artis
    INNER JOIN film ON artis.kd_artis=film.artis
    INNER JOIN genre ON film.genre=genre.kd_genre
WHERE genre.nm_genre='DRAMA';

--11. Menampilkan nama artis yang bermain film dengan genre Action
SELECT artis.nm_artis, genre.nm_genre
FROM artis
    INNER JOIN film ON artis.kd_artis=film.artis
    INNER JOIN genre ON film.genre=genre.kd_genre
WHERE genre.nm_genre='ACTION'
GROUP BY artis.nm_artis, genre.nm_genre;

--12. Menampilkan data negara dengan jumlah filmnya
SELECT negara.kd_negara, negara.nm_negara, COUNT(film.*) as jumlah_film
FROM artis
    INNER JOIN film ON film.artis=artis.kd_artis
    RIGHT OUTER JOIN negara ON artis.negara=negara.kd_negara
GROUP BY negara.kd_negara
ORDER BY negara.nm_negara;

--13. Menampilkan nama film yang skala internasional
SELECT film.nm_film
FROM film 
    INNER JOIN produser ON film.produser=produser.kd_produser
WHERE produser.international='YA';

--14. Menampilkan jumlah film dari masing2 produser
SELECT P.nm_produser, COUNT(F.kd_film) as jml_film
FROM produser P
    LEFT JOIN film F ON P.kd_produser=F.produser
GROUP BY P.nm_produser
ORDER BY P.nm_produser;
