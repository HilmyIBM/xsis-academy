CREATE DATABASE "DB_Entertainer";
--
CREATE TABLE artis (
    kd_artis char(4) PRIMARY KEY not null,
    nm_artis varchar(64) not null,
    jk varchar(10) not null,
    bayaran BIGINT not null,
    award int2 not null,
    negara varchar(3),
    CONSTRAINT fk_negara
                   FOREIGN KEY (negara)
                   REFERENCES negara(kd_negara)
);

CREATE TABLE produser (
    kd_produser char(4) primary key not null,
    nm_produser varchar(64) not null,
    international varchar(5) not null
);

CREATE TABLE negara (
    kd_negara char(3) primary key not null,
    nm_negara varchar(64) not null
);

CREATE TABLE genre (
    kd_genre char(4) primary key not null,
    nm_genre varchar(24) not null
);

CREATE TABLE film(
    kd_film char(4) primary key not null,
    nm_film varchar(128) not null,
    genre char(4) not null,
    artis char(4) not null,
    produser char(4) not null,
    pendapatan integer,
    nominasi integer,
    CONSTRAINT fk_genre
                 FOREIGN KEY (genre)
                 REFERENCES genre (kd_genre),
    CONSTRAINT fk_artis
                 FOREIGN KEY (artis)
                 REFERENCES artis (kd_artis),
    CONSTRAINT fk_produser
                 FOREIGN KEY (produser)
                 REFERENCES produser (kd_produser)
);

INSERT INTO genre (kd_genre, nm_genre)
VALUES
    ('G001', 'ACTION'),
    ('G002', 'HORROR'),
    ('G003', 'COMEDY'),
    ('G004', 'DRAMA'),
    ('G005', 'THRILLER'),
    ('G006', 'FICTION');

INSERT INTO negara (kd_negara, nm_negara)
VALUES
    ('AS', 'AMERIKA SERIKAT'),
    ('HK', 'HONGKONG'),
    ('ID', 'INDONESIA'),
    ('IN', 'INDIA');

INSERT INTO produser (kd_produser, nm_produser, international)
VALUES
    ('P001', 'MARVEL', 'YA'),
    ('P002', 'HONGKONG CINEMA', 'YA'),
    ('P003', 'RAPI FILM', 'TIDAK'),
    ('P004', 'PARKIT', 'TIDAK'),
    ('P005', 'PARAMOUNT PICTURE', 'YA');

INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara)
VALUES
    ('A001', 'ROBERT DOWNEY JR', 'PRIA', 5000000000, 2, 'AS'),
    ('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
    ('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
    ('A004', 'JOE TASLIM', 'PRIA', 350000000, 1, 'ID'),
    ('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID');

INSERT INTO public.film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi)
VALUES
    ('P001', 'IRON MAN', 'G001', 'A001', 'P001', 2000000000, 3),
    ('P002', 'IRON MAN 2', 'G001', 'A001', 'P001', 1800000000, 2),
    ('P003', 'IRON MAN 3', 'G001', 'A001', 'P001', 1200000000, 0),
    ('P004', 'AVANGER : CIVIL WAR', 'G001', 'A001', 'P001', 2000000000, 1),
    ('P005', 'SPIDERMAN : HOME COMING', 'G001', 'A001', 'P001', 1200000000, 0),
    ('P005', 'SPIDERMAN : HOME COMING', 'G001', 'A001', 'P001', 1200000000, 0),
    ('P007', 'FAST & FURIOUS', 'G001', 'A004', 'P005', 830000000, 2),
    ('P008', 'HABIBIE DAN AINUN', 'G004', 'A005', 'P003', 670000000, 4),
    ('P009', 'POLICE STORY', 'G001', 'A003', 'P002', 700000000, 3),
    ('P010', 'POLICE STORY 2', 'G001', 'A003', 'P002', 710000000, 1),
    ('P011', 'POLICE STORY 3', 'G001', 'A003', 'P002', 615000000, 0),
    ('P012', 'RUSH HOUR', 'G003', 'A003', 'P005', 695000000, 2),
    ('P013', 'KUNGFU PANDA', 'G003', 'A003', 'P005', 923000000, 5);


------------------- Task -------------------

-- NO 1