-- CREATE OR REPLACE FUNCTION ufn_gaji_pajak()
-- RETURNS TABLE(
--     nama_lengkap VARCHAR(100),
--     nama_jabatan VARCHAR(100),
--     nama_divisi VARCHAR(100),
--     total_gaji NUMERIC,
--     pajak NUMERIC,
--     gaji_bersih NUMERIC
-- )
-- AS $$
-- DECLARE persen_pajak NUMERIC := 0.005;
-- BEGIN
--     RETURN QUERY SELECT
--         CONCAT(K.nama_depan, ' ', K.nama_belakang) nama_lengkap,
--         J.nama_jabatan, D.nama_divisi,
--         ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) AS total_gaji,
--         ufn_pajak(ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja), persen_pajak) AS pajak,
--         ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) - (ufn_total_gaji(J.gaji_pokok, J.tunjangan_jabatan, P.tunjangan_kinerja) * persen_pajak) AS gaji_bersih
--     FROM
--         tb_karyawan K
--         INNER JOIN tb_pekerjaan P ON K.nip=P.nip
--         INNER JOIN tb_jabatan J ON P.kode_jabatan=J.kd_jabatan
--         INNER JOIN tb_divisi D ON P.kode_divisi=D.kd_divisi
--     WHERE
--         K.jenis_kelamin='Pria'
--         AND P.kota_penempatan NOT IN ('Sukabumi');
-- END;
-- $$
-- LANGUAGE plpgsql;
-- SELECT * FROM ufn_gaji_pajak();

CREATE FUNCTION ufn_pajak(total_gaji NUMERIC, persen_pajak NUMERIC)
RETURNS NUMERIC AS $pajak$
DECLARE pajak NUMERIC;
BEGIN
    SELECT (total_gaji * persen_pajak) INTO pajak;
    RETURN pajak;
END;
$pajak$ LANGUAGE plpgsql;

create function testing(total_gaji numeric, persen_pajak numeric) {
    
}

CREATE TRIGGER update_salary AFTER UPDATE ON employees FOR EACH ROW EXECUTE FUNCTION ufn_pajak();
