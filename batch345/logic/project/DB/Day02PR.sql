-- CREATE DATABASE "DB_Entertainer";

CREATE TABLE artis (
    kd_artis char(4) PRIMARY KEY not null,
    nm_artis varchar(64) not null,
    jk varchar(10) not null,
    bayaran BIGINT not null,
    award int2 not null,
    negara varchar(3)
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

DROP TABLE artis;

INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara)
VALUES
    ('A001', 'ROBERT DOWNEY JR', 'PRIA', 5000000000, 2, 'AS'),
    ('A002', 'ANGELINA JOLIE', 'WANITA', 700000000, 1, 'AS'),
    ('A003', 'JACKIE CHAN', 'PRIA', 200000000, 7, 'HK'),
    ('A004', 'JOE TASLIM', 'PRIA', 350000000, 1, 'ID'),
    ('A005', 'CHELSEA ISLAN', 'WANITA', 300000000, 0, 'ID');
