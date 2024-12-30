CREATE database DB_Entertainer;

create tabel artis (
id serial primary key,
kd_artis varchar(100),
nm_artis varchar(100),
jk varchar (100),
bayaran integer,
award integer,
negara varchar(100)
);

create tabel filim (
id serial primary key,
kd_filim varchar(10),
nm_filim varchar(55),
genre varchar(55),
artis varchar (55),
produser varchar(55),
pendapatan integer,
nominasi integer
);

create table negara (
id serial primary key,
kd_negara varchar(50),
nm_negara varchar(50)
);

create table produser (
id serial primary key,
kd_produser varchar(100),
nm_produser varchar(100)
);

create table genre (
id serial primary key,
kd_genre varchar(50),
nm_genre varchar(50)
);

insert into artis (kd_artis, nm_artis, jk, bayaran, award, negara)
values (
	('A001' , 'ROBERT DOWNEY JR' , 1000000000 , 2 , 'AS'),
	('A002' , 'ANGELINA JOLIE' , 7000000000 , 1 , 'AS'),
	('A003' , 'JACKIE CHAN' , 2000000000 , 7 , 'HK'),
	('A004' , 'JOE TASLIM' , 3500000000 , 1 , 'ID'),
	('A005' , 'CHELSEA ISLAN' , 3000000000 , 0 , 'ID'),
);

insert into filim (kd_artis, nm_artis, jk, bayaran, award, negara)
values (
	('F001' , 'IRON MAN' , 				'G001' , 'A001' , 'PD01' , '20000000000' , '' ),
	('F002' , 'IRON MAN 2' , 			'G001' , 'A001' , 'PD01' , 18000000000' , '' ),
	('F003' , 'IRON MAN 3' , 			'G001' , 'A001' , 'PD01' , '000000000' , '' ),
	('F004' , 'AVANGER CIVIL WAR' , 	'G001' , 'A001' , 'PD01' , '000000000' , '' ),
	('F005' , 'SUPERMAN HOME COMING' , 	'G001' , 'A001' , 'PD01' , '000000000' , '' ),
	('F006' , 'THE RAID' , 				'G001' , 'A004' , 'PD03' , '000000000' , '' ),
	('F007' , 'FAST & FURIOUS' , 		'G001' , 'A004' , 'PD05' , '000000000' , '' ),
	('F008' , 'HABIBIE DAN AINUN' , 	'G004' , 'A005' , 'PD03' , '000000000' , '' ),
	('F009' , 'POLICE STORY' , 			'G001' , 'A003' , 'PD02' , '000000000' , '' ),
	('F010' , 'POLICE STORY' , 			'G001' , 'A003' , 'PD02' , '000000000' , '' ),
	('F011' , 'POLICE STORY 2' , 		'G001' , 'A003' , 'PD02' , '000000000' , '' ),
	('F012' , 'RUSH HOUR' , 			'G003' , 'A003' , 'PD05' , '000000000' , '' ),
	('F013' , 'KUNGFU PANDA' , 			'G003' , 'A003' , 'PD05' , '000000000' , '' )
);

insert into artis (kd_artis, nm_artis, jk, bayaran, award, negara)
values (
	
)

insert into artis (kd_artis, nm_artis, jk, bayaran, award, negara)
values (
	
)

insert into artis (kd_artis, nm_artis, jk, bayaran, award, negara)
values (
	
)

insert into artis (kd_artis, nm_artis, jk, bayaran, award, negara)
values (
	
)

insert into artis (kd_artis, nm_artis, jk, bayaran, award, negara)
values (
	
)





