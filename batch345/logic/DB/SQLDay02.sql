CREATE database DB_Entertainer;

create table artis (
id serial primary key,
kd_artis varchar(100),
nm_artis varchar(100),
jk varchar (100),
bayaran BIGINT,
award integer,
negara varchar(100)
);

alter table artis 
alter column bayaran type bigint;

create table filim (
id serial primary key,
kd_filim varchar(10),
nm_filim varchar(55),
genre varchar(55),
artis varchar (55),
produser varchar(55),
pendapatan integer,
nominasi integer
);

alter table filim 
alter column pendapatan type bigint;

create table produser (
id serial primary key,
kd_produser varchar(50),
nm_produser varchar(50),
international varchar(50)
);

create table negara (
id serial primary key,
kd_negara varchar(100),
nm_negara varchar(100)
);


create table genre (
id serial primary key,
kd_genre varchar(50),
nm_genre varchar(50)
);

insert into artis (kd_artis, nm_artis, jk, bayaran, award, negara)
values 
	('A001' , 'ROBERT DOWNEY JR', 'Pria' , 1000000000 , 2 , 'AS'),
	('A002' , 'ANGELINA JOLIE', 'WANITA' , 7000000000 , 1 , 'AS'),
	('A003' , 'JACKIE CHAN' , 'PRIA', 2000000000 , 7 , 'HK'),
	('A004' , 'JOE TASLIM' , 'PRIA', 3500000000 , 1 , 'ID'),
	('A005' , 'CHELSEA ISLAN',  'WANITA', 3000000000 , 0 , 'ID');

insert into filim (kd_filim, nm_filim, genre, artis, produser, pendapatan, nominasi)
values
	('F001' , 'IRON MAN' , 				'G001' , 'A001' , 'PD01' , 2000000000 , 3 ),
	('F002' , 'IRON MAN 2' , 			'G001' , 'A001' , 'PD01' , 18000000000,  2),
	('F003' , 'IRON MAN 3' , 			'G001' , 'A001' , 'PD01' , 12000000000,  0),
	('F004' , 'AVANGER CIVIL WAR' , 	'G001' , 'A001' , 'PD01' , 20000000000,  1),
	('F005' , 'SUPERMAN HOME COMING' , 	'G001' , 'A001' , 'PD01' , 13000000000,  0),
	('F006' , 'THE RAID' , 				'G001' , 'A004' , 'PD03' , 8000000000,  5 ),
	('F007' , 'FAST & FURIOUS' , 		'G001' , 'A004' , 'PD05' , 8300000000,  2 ),
	('F008' , 'HABIBIE DAN AINUN' , 	'G004' , 'A005' , 'PD03' , 6700000000,  4 ),
	('F009' , 'POLICE STORY' , 			'G001' , 'A003' , 'PD02' , 7000000000,  3 ),
	('F010' , 'POLICE STORY' , 			'G001' , 'A003' , 'PD02' , 7100000000,  1 ),
	('F011' , 'POLICE STORY 2' , 		'G001' , 'A003' , 'PD02' , 6150000000,  0 ),
	('F012' , 'RUSH HOUR' , 			'G003' , 'A003' , 'PD05' , 6950000000,  2 ),
	('F013' , 'KUNGFU PANDA' , 			'G003' , 'A003' , 'PD05' , 9230000000,  5 );

insert into produser (kd_produser, nm_produser, international)
values 
	('P001', 'MARVEL', 'YA'),
	('P002', 'HONGKONG CINEMA', 'YA'),
	('P003', 'RAPI FILM', 'TIDAK'),
	('P004', 'PARKIT', 'TIDAK'),
	('P005', 'PARAMOUNT PICTURE', 'YA');

insert into negara (kd_negara, nm_negara)
values 
	('AS', 'AMERIKA SERIKAT'),
	('HK', 'HONGKONG'),
	('ID', 'INDONESIA'),
	('IN', 'INDIA');

insert into genre (kd_genre, nm_genre)
values
	('G001', 'ACTION'),
	('G002', 'HORROR'),
	('G003', 'COMEDY'),
	('G004', 'DRAMA'),
	('G005', 'THRILLER'),
	('G006', 'FICTION');

--no 1 jumlah pendapatan produser marvel

SELECT 
    produser.nm_produser AS produser_name, 
    SUM(filim.pendapatan) AS total_pendapatan
FROM 
    filim
INNER JOIN 
    produser 
ON 
    filim.produser = produser.kd_produser
WHERE 
    produser.nm_produser = 'MARVEL'
GROUP BY 
    produser.nm_produser;

--no 2 filim tanpa nominasi

SELECT
	nm_filim as nama_filim, nominasi as total_nominasi
FROM
	filim
WHERE
	nominasi = 0;

--no 3 filim dengan awalan huruf p

SELECT
	nm_filim as nama_filim
FROM
	filim
WHERE nm_filim like 'P%';

--no 4 filim dengan huruf terakhir y

SELECT
	nm_filim as nama_filim
FROM
	filim
WHERE nm_filim like '%Y';

--no 5 filim yang mengandung huruf d
SELECT
	nm_filim as nama_filim
FROM
	filim
WHERE nm_filim like '%D%';

--no 6 menampilkan nama filim dan artis
SELECT
	filim.nm_filim as nama_filim,
	artis.nm_artis as nama_artis
FROM
	artis
INNER JOIN
	filim
ON
	filim.artis = artis.kd_artis;





