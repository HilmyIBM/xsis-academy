CREATE DATABASE "DB_PTXA"

--create table contact_person
CREATE TABLE "Biodata" (
    id BIGINT PRIMARY KEY NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(30),
    dob TIMESTAMP,
    pob VARCHAR(50),
    address VARCHAR(255),
    gender VARCHAR(1)
)


