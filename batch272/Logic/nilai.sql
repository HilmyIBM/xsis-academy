--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

-- Started on 2021-12-02 17:14:04 WIB

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16501)
-- Name: nilai; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nilai (
    id integer NOT NULL,
    kode_mahasiswa character(5),
    kode_ujian character(5),
    nilai numeric(8,2)
);


ALTER TABLE public.nilai OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16499)
-- Name: nilai_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nilai_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nilai_id_seq OWNER TO postgres;

--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 214
-- Name: nilai_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nilai_id_seq OWNED BY public.nilai.id;


--
-- TOC entry 2870 (class 2604 OID 16504)
-- Name: nilai id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nilai ALTER COLUMN id SET DEFAULT nextval('public.nilai_id_seq'::regclass);


--
-- TOC entry 3005 (class 0 OID 16501)
-- Dependencies: 215
-- Data for Name: nilai; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.nilai (id, kode_mahasiswa, kode_ujian, nilai) FROM stdin;
1	M004 	U0001	90.00
2	M001 	U0001	80.00
3	M002 	U0003	85.00
4	M004 	U0002	95.00
5	M005 	U0005	70.00
\.


--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 214
-- Name: nilai_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.nilai_id_seq', 5, true);


--
-- TOC entry 2872 (class 2606 OID 16506)
-- Name: nilai nilai_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nilai
    ADD CONSTRAINT nilai_pkey PRIMARY KEY (id);


--
-- TOC entry 2873 (class 2606 OID 16507)
-- Name: nilai fk_kode_mahasiswa; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nilai
    ADD CONSTRAINT fk_kode_mahasiswa FOREIGN KEY (kode_mahasiswa) REFERENCES public.mahasiswa(kode_mahasiswa);


--
-- TOC entry 2874 (class 2606 OID 16512)
-- Name: nilai fk_kode_ujian; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nilai
    ADD CONSTRAINT fk_kode_ujian FOREIGN KEY (kode_ujian) REFERENCES public.ujian(kode_ujian);


-- Completed on 2021-12-02 17:14:04 WIB

--
-- PostgreSQL database dump complete
--

