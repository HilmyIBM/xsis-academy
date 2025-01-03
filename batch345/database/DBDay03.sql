CREATE TABLE
    tb_karyawan (
        id BIGINT PRIMARY KEY NOT NULL,
        nip VARCHAR(50) NOT NULL,
        nama_depan VARCHAR(50) NOT NULL,
        nama_belakang VARCHAR(50) NOT NULL,
        jenis_kelamin VARCHAR(50) NOT NULL,
        agama VARCHAR(50) NOT NULL,
        tempat_lahir VARCHAR(50) NOT NULL,
        tgl_lahir DATE,
        alamat VARCHAR(50) NOT NULL,
        pendidikan_terakhir VARCHAR(50) NOT NULL,
        tgl_masuk DATE
    );

CREATE TABLE
    tb_divisi (
        id BIGINT PRIMARY KEY NOT NULL,
        kd_divisi VARCHAR(50) NOT NULL,
        nama_divisi VARCHAR(50) NOT NULL
    );

CREATE TABLE
    tb_jabatan (
        id BIGINT PRIMARY KEY NOT NULL,
        kd_jabatan VARCHAR(50) NOT NULL,
        nama_jabatan VARCHAR(50) NOT NULL,
        gaji_pokok NUMERIC NOT NULL,
        tunjangan_jabatan NUMERIC NOT NULL
    );

CREATE TABLE
    tb_pekerjaan (
        id BIGINT PRIMARY KEY NOT NULL,
        nip VARCHAR(50) NOT NULL,
        kd_jabatan VARCHAR(50) NOT NULL,
        kd_divisi VARCHAR(50) NOT NULL,
        tunjangan_kinerja NUMERIC NOT NULL,
        kota_penempatan VARCHAR(50) NOT NULL
    );

DROP TABLE tb_karyawan;

DROP TABLE tb_divisi;

DROP TABLE tb_jabatan;

DROP TABLE tb_pekerjaan;

INSERT INTO
    tb_karyawan (
        id,
        nip,
        nama_depan,
        nama_belakang,
        jenis_kelamin,
        agama,
        tempat_lahir,
        tgl_lahir,
        alamat,
        pendidikan_terakhir,
        tgl_masuk
    )
VALUES
    (
        1,
        '001',
        'Hamidi',
        'Samsudin',
        'Pria',
        'Islam',
        'Sukabumi',
        '1977-04-21',
        'Jl. Sudirman No.12',
        'S1 Teknik Mesin',
        '2015-12-07'
    ),
    (
        3,
        '003',
        'Paul',
        'Christian',
        'Pria',
        'Kristen',
        'Ambon',
        '1980-05-27',
        'Jl. Veteran No.4',
        'S1 Pendidikan Geografi',
        '2014-01-12'
    ),
    (
        2,
        '002',
        'Ghandi',
        'Wamida',
        'Wanita',
        'Islam',
        'Palu',
        '1992-01-12',
        'Jl. Rambutan No.22',
        'SMA Negeri 02 Palu',
        '2014-12-01'
    );

INSERT INTO
    tb_divisi (id, kd_divisi, nama_divisi)
VALUES
    (1, 'GD', 'Gudang'),
    (2, 'HRD', 'HRD'),
    (3, 'KU', 'Keuangan'),
    (4, 'UM', 'Umum');

INSERT INTO
    tb_jabatan (
        id,
        kd_jabatan,
        nama_jabatan,
        gaji_pokok,
        tunjangan_jabatan
    )
VALUES
    (1, 'MGR', 'Manager', 5500000, 1500000),
    (2, 'OB', 'Office Boy', 1900000, 200000),
    (3, 'ST', 'Staff', 3000000, 750000),
    (4, 'WMGR', 'Wakil Manager', 4000000, 1200000);

INSERT INTO
    tb_pekerjaan (
        id,
        nip,
        kd_jabatan,
        kd_divisi,
        tunjangan_kinerja,
        kota_penempatan
    )
VALUES
    (1, '001', 'ST', 'KU', 750000, 'Cianjur'),
    (2, '002', 'OB', 'UM', 350000, 'Sukabumi'),
    (3, '003', 'MGR', 'HRD', 1500000, 'Sukabumi');

WITH
    Gaji_Detail AS (
        SELECT
            k.nip,
            CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
            j.nama_jabatan,
            d.nama_divisi,
            j.gaji_pokok,
            j.tunjangan_jabatan,
            (
                j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
            ) AS total_gaji,
            ROUND(
                (
                    j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
                ) * 0.05,
                2
            ) AS pajak,
            (
                j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
            ) - ROUND(
                (
                    j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
                ) * 0.05,
                2
            ) AS gaji_bersih,
            p.kota_penempatan,
            k.jenis_kelamin
        FROM
            tb_karyawan k
            JOIN tb_pekerjaan p ON k.nip = p.nip
            JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
            JOIN tb_divisi d ON p.kd_divisi = d.kd_divisi
    )
SELECT
    nama_lengkap,
    nama_jabatan,
    total_gaji AS gaji_tunjangan
FROM
    Gaji_Detail
WHERE
    (gaji_pokok + tunjangan_jabatan) < 5000000;

SELECT
    nama_lengkap,
    nama_jabatan,
    nama_divisi,
    total_gaji,
    pajak,
    gaji_bersih
FROM
    Gaji_Detail
WHERE
    jenis_kelamin = 'Pria'
    AND kota_penempatan <> 'Sukabumi';

-- No 1
SELECT
    CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
    j.nama_jabatan,
    (j.gaji_pokok + j.tunjangan_jabatan) AS gaji_tunjangan
FROM
    tb_karyawan k
    JOIN tb_pekerjaan p ON k.nip = p.nip
    JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
WHERE
    (j.gaji_pokok + j.tunjangan_jabatan) < 5000000;

-- No 2
SELECT
    CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
    j.nama_jabatan,
    d.nama_divisi,
    (
        j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
    ) AS total_gaji,
    (
        (
            j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
        ) * 0.05
    ) AS pajak,
    (
        j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
    ) - (
        (
            j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
        ) * 0.05
    ) AS gaji_bersih
FROM
    tb_karyawan k
    JOIN tb_pekerjaan p ON k.nip = p.nip
    JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
    JOIN tb_divisi d ON p.kd_divisi = d.kd_divisi
WHERE
    k.jenis_kelamin = 'Pria'
    AND p.kota_penempatan <> 'Sukabumi';

-- No 3
SELECT
    k.nip,
    CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
    j.nama_jabatan,
    d.nama_divisi,
    (
        (
            (
                j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
            ) * 7
        ) * 0.25
    ) AS bonus
FROM
    tb_karyawan k
    JOIN tb_pekerjaan p ON k.nip = p.nip
    JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
    JOIN tb_divisi d ON p.kd_divisi = d.kd_divisi
ORDER BY
    k.nip;

-- No 4
SELECT
    CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
    j.nama_jabatan,
    d.nama_divisi,
    (
        j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
    ) AS total_gaji,
    (
        (
            j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
        ) * 0.05
    ) AS infak
FROM
    tb_karyawan k
    JOIN tb_pekerjaan p ON k.nip = p.nip
    JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
    JOIN tb_divisi d ON p.kd_divisi = d.kd_divisi
WHERE
    j.kd_jabatan = 'MGR';

-- No 5
SELECT
    CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
    j.nama_jabatan,
    k.pendidikan_terakhir,
    2000000 AS tunjangan_pendidikan,
    (j.gaji_pokok + j.tunjangan_jabatan + 2000000) AS total_gaji
FROM
    tb_karyawan k
    JOIN tb_pekerjaan p ON k.nip = p.nip
    JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
    JOIN tb_divisi d ON p.kd_divisi = d.kd_divisi
WHERE
    k.pendidikan_terakhir LIKE 'S1%';

-- No 6
SELECT
    k.nip,
    CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
    j.nama_jabatan,
    d.nama_divisi,
    CASE
        WHEN j.kd_jabatan = 'MGR' THEN (
            j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
        ) * 7 * 0.25
        WHEN j.kd_jabatan = 'ST' THEN (
            j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
        ) * 5 * 0.25
        ELSE (
            j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja
        ) * 2 * 0.25
    END AS bonus
FROM
    tb_karyawan k
    JOIN tb_pekerjaan p ON k.nip = p.nip
    JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
    JOIN tb_divisi d ON p.kd_divisi = d.kd_divisi;

-- No 7
ALTER TABLE tb_karyawan ADD CONSTRAINT unique_nip UNIQUE (nip);

-- No 8
CREATE INDEX idx_karyawan_nip ON tb_karyawan (nip);

-- No 9
SELECT CONCAT ( k.nama_depan, ' ',
        CASE
            WHEN k.nama_belakang LIKE 'W%' THEN UPPER(k.nama_belakang)
            ELSE k.nama_belakang
        END) AS nama_lengkap
FROM tb_karyawan k;

-- No 10
SELECT
    k.nip,
    CONCAT (k.nama_depan, ' ', k.nama_belakang) AS nama_lengkap,
    k.tgl_masuk,
    j.nama_jabatan,
    d.nama_divisi,
    (j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja) AS total_gaji,
    ((j.gaji_pokok + j.tunjangan_jabatan + p.tunjangan_kinerja) * 0.1) AS bonus,
    EXTRACT(YEAR FROM AGE ('2022-12-31', k.tgl_masuk)) AS lama_bekerja
FROM
    tb_karyawan k
    JOIN tb_pekerjaan p ON k.nip = p.nip
    JOIN tb_jabatan j ON p.kd_jabatan = j.kd_jabatan
    JOIN tb_divisi d ON p.kd_divisi = d.kd_divisi
WHERE EXTRACT(YEAR FROM AGE ('2022-12-31', k.tgl_masuk)) >= 8;

CREATE DATABASE DB_Univ_XA;