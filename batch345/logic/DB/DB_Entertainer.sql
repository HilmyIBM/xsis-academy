-- CREATE DATABASE DB_Entertainer;

CREATE TABLE artis (
	kd_artis varchar(5) NOT NULL,
	nm_artis varchar(100) NOT NULL,
	jk varchar(10) NOT NULL,
	bayaran money NOT NULL,
	award int,
	negara varchar(100),
	CONSTRAINT pk_artis PRIMARY KEY (kd_artis)
);

INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara) VALUES(N'A001', 'ROBERT DOWNEY JR', 'PRIA', 1000000000.0000, 2, 'AS');
INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara) VALUES(N'A002', 'ANGELINA JOLIE', 'WANITA', 700000000.0000, 1, 'AS');
INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara) VALUES(N'A003', 'JACKIE CHAN', 'PRIA', 200000000.0000, 7, 'HK');
INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara) VALUES(N'A004', 'JOE TASLIM', 'PRIA', 350000000.0000, 1, 'ID');
INSERT INTO artis (kd_artis, nm_artis, jk, bayaran, award, negara) VALUES(N'A005', 'CHELSEA ISLAN', 'WANITA', 300000000.0000, 0, 'ID');

CREATE TABLE film (
	kd_film varchar(5) NOT NULL,
	nm_film varchar(100) NOT NULL,
	genre varchar(5),
	artis varchar(5),
	produser varchar(5),
	pendapatan money DEFAULT 0 NULL,
	nominasi int DEFAULT 0 NULL,
    CONSTRAINT pk_film PRIMARY KEY (kd_film)
);

INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F001', 'IRON MAN', 'G001', 'A001', 'PD01', 2000000000.0000, 3);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F002', 'IRON MAN 2', 'G001', 'A001', 'PD01', 1800000000.0000, 2);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F003', 'IRON MAN 3', 'G001', 'A001', 'PD01', 1200000000.0000, 0);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F004', 'AVENGER: CIVIL WAR', 'G001', 'A001', 'PD01', 2000000000.0000, 1);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F005', 'SPIDERMAN HOME COMING', 'G001', 'A001', 'PD01', 1300000000.0000, 0);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F006', 'THE RAID', 'G001', 'A004', 'PD03', 800000000.0000, 5);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F007', 'FAST & FURIOUS', 'G001', 'A004', 'PD05', 830000000.0000, 2);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F008', 'HABIBIE DAN AINUN', 'G004', 'A005', 'PD03', 670000000.0000, 4);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F009', 'POLICE STORY', 'G001', 'A003', 'PD02', 700000000.0000, 3);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F010', 'POLICE STORY 2', 'G001', 'A003', 'PD02', 710000000.0000, 1);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F011', 'POLICE STORY 3', 'G001', 'A003', 'PD02', 615000000.0000, 0);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F012', 'RUSH HOUR', 'G003', 'A003', 'PD05', 695000000.0000, 2);
INSERT INTO film (kd_film, nm_film, genre, artis, produser, pendapatan, nominasi) VALUES(N'F013', 'KUNGFU PANDA', 'G003', 'A003', 'PD05', 923000000.0000, 5);

CREATE TABLE genre (
	kd_genre varchar(5) NOT NULL,
	nm_genre varchar(50) NOT NULL,
	CONSTRAINT PK_genre PRIMARY KEY (kd_genre)
);

INSERT INTO genre (kd_genre, nm_genre) VALUES('G001', 'ACTION');
INSERT INTO genre (kd_genre, nm_genre) VALUES('G002', 'HORROR');
INSERT INTO genre (kd_genre, nm_genre) VALUES('G003', 'COMEDY');
INSERT INTO genre (kd_genre, nm_genre) VALUES('G004', 'DRAMA');
INSERT INTO genre (kd_genre, nm_genre) VALUES('G005', 'THRILLER');
INSERT INTO genre (kd_genre, nm_genre) VALUES('G006', 'FICTION');

CREATE TABLE negara (
	kd_negara varchar(5) NOT NULL,
	nm_negara varchar(100) NOT NULL,
	CONSTRAINT PK_negara PRIMARY KEY (kd_negara)
);

INSERT INTO negara (kd_negara, nm_negara) VALUES('AS', 'AMERIKA SERIKAT');
INSERT INTO negara (kd_negara, nm_negara) VALUES('HK', 'HONGKONG');
INSERT INTO negara (kd_negara, nm_negara) VALUES('ID', 'INDONESIA');
INSERT INTO negara (kd_negara, nm_negara) VALUES('IN', 'INDIA');

CREATE TABLE produser (
	kd_produser varchar(5) NOT NULL,
	nm_produser varchar(100),
	international varchar(5),
	CONSTRAINT PK_produser PRIMARY KEY (kd_produser)
);

INSERT INTO produser (kd_produser, nm_produser, international) VALUES('PD01', 'MARVEL', 'YA');
INSERT INTO produser (kd_produser, nm_produser, international) VALUES('PD02', 'HONGKONG CINEMA', 'YA');
INSERT INTO produser (kd_produser, nm_produser, international) VALUES('PD03', 'RAPI FILM', 'TIDAK');
INSERT INTO produser (kd_produser, nm_produser, international) VALUES('PD04', 'PARKIT', 'TIDAK');
INSERT INTO produser (kd_produser, nm_produser, international) VALUES('PD05', 'PARAMOUNT PICTURE', 'YA');
