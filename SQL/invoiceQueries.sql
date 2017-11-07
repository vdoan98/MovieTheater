/*--1*/
/*A query to retrieve the major fields for every person.*/
SELECT p.*, e.email_address, a.*
FROM Person AS p JOIN PersonAddress AS pa ON p.person_id = pa.person_id
JOIN Address AS a ON pa.address_id = a.address_id LEFT JOIN Emails AS e ON p.person_id=e.person_id;

/*--2*/
/*A query to add an email to a specific person.*/
/*INSERT INTO Emails(person_id, person_code, email_address)
	VALUES (2,'hstuck2', 'hstucknew@gmail.com');
    
/*--3*/
/*A query to change the address of a theater in a movie ticket record.*/
/*UPDATE Address
SET street = '29 Grove Avenue', city = 'Lincoln', state = 'NE', zip = '68720', country = 'USA' 
WHERE address_id = (SELECT address_id FROM MovieTicket WHERE product_id = 2);

/*--4*/
/*A query (or series of queries) to remove a given movie ticket record.*/
/*DELETE FROM MovieTicket
WHERE movie_id = 2;

/*--5*/
/*A query to get all the products in a particular invoice.*/
SELECT p.product_code,p.product_type,p.product_name, pi.invoice_id
FROM Product AS p JOIN ProductInvoice AS pi ON p.product_id=pi.product_id
LEFT JOIN Invoice AS i ON i.invoice_id=pi.invoice_id
WHERE i.invoice_id=1
ORDER BY p.product_type;

/*--6*/
/*A query to get all the invoices of a particular customer.*/
SELECT i.*, c.customer_id, c.customer_code
FROM Invoice AS i JOIN Customers AS c ON i.invoice_id=c.invoice_id
WHERE c.customer_code = 'C001';

/*--7*/
/*A query that “adds” a particular product to a particular invoice.*/
/*INSERT INTO Invoice(invoice_code,person_code,sale_id,sale_date) VALUES
	('INV005','606s',5,'2016-10-30');
    
/*--8*/
/*A query to find the total of all per-unit costs of all movie-tickets*/
SELECT SUM(p.price) AS 'Total Cost', p.product_type AS 'Type', p.product_amount AS 'Amount'
FROM Product AS p JOIN MovieTicket AS m ON p.product_id=m.product_id
WHERE p.product_type = 'M';

/*--9*/
/*A query to find the total number of movie-tickets sold on a particular date.*/
SELECT SUM(p.product_amount) AS '# Tickets'
FROM Product AS p JOIN MovieTicket AS m ON p.product_id=m.product_id
JOIN ProductInvoice AS pi ON p.product_id=pi.product_id
JOIN Invoice AS i ON i.invoice_id=pi.invoice_id
WHERE i.sale_date = '2016-12-05';

/*--10-*/
/*A query to find the total number of invoices for every salesperson.*/
SELECT COUNT(i.invoice_id) AS '# Invoices',s.sale_id AS 'Salesperson ID', p.person_code AS 'Person Code', p.first_name AS 'First Name', p.last_name AS 'Last Name'
FROM Invoice AS i JOIN SalePerson AS s ON i.invoice_id=s.invoice_id
JOIN Person AS p ON s.person_id=p.person_id
WHERE p.person_code = 'nctis';

/*--11*/
/*A query to find the total number of invoices for a particular movie ticket.*/ 
SELECT COUNT(i.invoice_id) AS '# Invoices', p.product_name
FROM Product AS p JOIN ProductInvoice AS pi ON p.product_id=pi.product_id
JOIN Invoice AS i ON i.invoice_id=pi.invoice_id
WHERE p.product_name = 'The Witch';

/*--12/
/*A query to find the total revenue generated (excluding fees and taxes) on a particular date from all movie-tickets*/
SELECT SUM(p.price) AS 'Total Revenue', i.sale_date AS 'Date'
FROM Product AS p JOIN ProductInvoice AS pi ON p.product_id=pi.product_id
JOIN Invoice AS i ON i.invoice_id=pi.invoice_id
WHERE p.product_type = 'M'
AND i.sale_date = '2016-12-05';

/*--13/
/*A query to find the total quantities sold (excluding fees and taxes) of each category of services (parking-passes and refreshments) in all the existing invoices.*/
SELECT p.product_type AS 'Type', p.product_amount AS 'Amount'
FROM Product AS p
WHERE product_type = 'M'
OR product_type = 'R'
OR product_type = 'S'
OR product_type =  'P' 
GROUP BY p.product_type;
/*--14/
/*A query to detect invalid data in invoices as follows. 
Write a query to find any invoice that includes multiple instances of the same ticket.*/