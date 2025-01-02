drop database "DB_Univ_XA";

create database "DB_Univ_XA";

create table mahasiswa(
    nim char(3) primary key not null ,
    nama varchar(50) not null,
    jenis_kelamin char(1) not null,
    alamat varchar(100) not null
);

insert into mahasiswa values
                          ('101', 'Arif', 'L', 'Jl, Kenangan'),
                          ('102', 'Budi', 'L', 'Jl. Jombang'),
                          ('103', 'Wati', 'P', 'Jl. Surabaya'),
                          ('104', 'Ika', 'P', 'Jl. Jombang'),
                          ('105', 'Tono', 'L', 'Jl. Jakarta'),
                          ('106', 'Iwan', 'L', 'Jl.Bandung'),
                          ('107', 'Sari', 'P', 'Jl. Malang');

create table matakuliah(
    kode_mk char(6) primary key not null,
    nama_mk varchar(50) not null,
    sks numeric not null ,
    semester numeric not null
);

insert into matakuliah values
                           ('PTI447', 'Praktikum Basis Data', 1, 3),
                           ('TIK342', 'Praktikum Baisi Data', 1, 3),
                           ('PTI333', 'Basis Data Terdistribusi', 3, 5),
                           ('TIK123', 'Jaringan Komputer', 2, 5),
                           ('TIK333', 'Sistem Operasi', 3, 5),
                           ('PTI123', 'Grafik Multimedia', 3, 5),
                           ('PTI777', 'Sistem Informasi', 2, 3);

create table ambil_mk (
    nim char(3) not null,
    kode_mk char(6) not null
);

insert into ambil_mk
values
    ('101', 'PTI447'),
    ('103', 'TIK333'),
    ('104', 'PTI333'),
    ('104', 'PTI777'),
    ('111', 'PTI123'),
    ('123', 'PTI999');

----------------- PR -----------------


-- NO 1
SELECT
    m.nama,
    m2.nama_mk
FROM
    ambil_mk am
        JOIN mahasiswa m on am.nim = m.nim
        JOIN matakuliah m2 on am.kode_mk = m2.kode_mk;

-- NO 2 ???
SELECT
    m.nim,
    m.nama,
    m.jenis_kelamin,
    m.alamat
FROM
    ambil_mk am
        RIGHT JOIN mahasiswa m on am.nim = m.nim
WHERE am.nim is NULL;

-- NO 3
SELECT
    count(m.jenis_kelamin) as jml,
    m.jenis_kelamin
FROM
    ambil_mk am
        RIGHT JOIN mahasiswa m on am.nim = m.nim
WHERE am.nim is NULL
group by m.jenis_kelamin;

-- NO 4
SELECT
    m.nim,
    m.nama,
    m2.kode_mk,
    m2.nama_mk
FROM
    ambil_mk am
        JOIN mahasiswa m on am.nim = m.nim
        JOIN matakuliah m2 on am.kode_mk = m2.kode_mk;

-- NO 5
SELECT
    m.nim,
    m.nama,
    sum(m2.sks) as jml_sks
FROM
    ambil_mk am
        JOIN mahasiswa m on am.nim = m.nim
        JOIN matakuliah m2 on am.kode_mk = m2.kode_mk
GROUP BY m.nim, m.nama
HAVING sum(m2.sks) > 4 AND sum(m2.sks) < 10;

--  NO 6
SELECT
    *
FROM
    ambil_mk am
        RIGHT JOIN matakuliah m on am.kode_mk = m.kode_mk
        LEFT JOIN mahasiswa m2 on am.nim = m2.nim
WHERE m2.nim IS NULL;