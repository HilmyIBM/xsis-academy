--//JOIN Tables//--
select kd_pengarang, gaji, 'Staff' as status from tblgaji where gaji<1000000
UNION
select kd_pengarang, gaji, 'Supervisor' as jabatan from tblgaji where gaji >= 1000000 and gaji < 1500000
UNION
select kd_pengarang, gaji, 'Manager' as posisi from tblgaji where gaji>=1500000;


--//DATE and TIME//--
CREATE TABLE timestamp_demo (
    ts TIMESTAMP,
    tstz TIMESTAMPTZ
);
SELECT * FROM TIMESTAMP_DEMO;

SHOW TIMEZONE;
SET timezone='Asia/Jakarta';
SET timezone='America/Chicago';

SELECT NOW();
select current_timestamp;
select timeofday();

SELECT NOW()::DATE;
select to_char(now()::date, 'dd/mm/yyyy');
select to_char(now()::date, 'dd Month yyyy');

select * from employees;

DELETE FROM employees
WHERE employee_id=10;

INSERT INTO employees
(employee_id, last_name,  first_name,  title,  title_of_courtesy,  birth_date,  hire_date,  address,  city,  region,  postal_code,  country,  home_phone,  extension,  photo,  notes,  reports_to,  photo_path)
VALUES
(10, 'Khan', 'Sharukh', 'Sales Representative', 'Mr.', '1966/01/27', '11/15/1994', '7 Houndstooth Rd.', 'London', NULL, 'WG2 7LT', 'UK', '(71) 555-4444', '452', NULL, 'Sharukh has a BA degree in English from Bollywood.  He is fluent in French and German.', 5, 'http://accweb/emmployees/davolio.bmp')

select first_name, last_name, now() today, hire_date,
    NOW() - hire_date day_diff,
    EXTRACT(YEAR FROM NOW())-EXTRACT(YEAR FROM hire_date) year_diff,
    EXTRACT(YEAR FROM AGE(NOW(), hire_date)) work_age
from employees;
