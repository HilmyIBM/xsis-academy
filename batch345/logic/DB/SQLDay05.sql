--a. Informasi nama sales yang memiliki order lebih dari 1.
SELECT S.NAME, COUNT(O) AS TOTAL_ORDERS
FROM SALESPERSON AS S
    INNER JOIN ORDERS AS O ON S.ID = O.SALESPERSON_ID
GROUP BY S.NAME
HAVING COUNT(O)>1;

--b. Informasi nama sales yang total amount ordernya di atas 1000.						
SELECT SALESPERSON.NAME, SUM(ORDERS.AMOUNT) TOTAL_ORDERS
FROM SALESPERSON 
    INNER JOIN ORDERS ON SALESPERSON.ID = ORDERS.SALESPERSON_ID
GROUP BY SALESPERSON.NAME
HAVING SUM(ORDERS.AMOUNT) > 1000;

--c. Informasi nama sales, umur, gaji dan total amount order yang tahun ordernya >= 2020 dan data ditampilan berurut sesuai dengan umur (ascending).									
SELECT
FROM SALES

--d. Carilah rata-rata total amount masing-masing sales
--urutkan dari hasil yg paling besar								
