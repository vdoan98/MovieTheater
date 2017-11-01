GRANT ALL PRIVILEGES ON invoiceQueries.* TO 'cse156'@'cse.unl.edu' WITH GRANT OPTION;
/*--Writing Queries for search;*/;
/*-- 1*/
SELECT p.PersonCode, PersonFirstName, PersonLastName, e.EmailAddress, Street, City, State, Zip, Country 
FROM ((Person AS p JOIN PersonAddress AS pa ON p.PersonID = pa.PersonID) 
JOIN Address as a ON pa.AddressID = a.AddressID) LEFT JOIN Emails as e ON p.PersonID = e.PersonID;
/*--2*/
INSERT INTO Emails (PersonID, EmailAddress)
	VALUES (1,'hstucknew@gmail.com');

/*--3*/
UPDATE 	Address
SET 		Street = '29 Grove Avenue', City = ' ', State = 'OK', Zip = '73750', Country = 'USA' 
WHERE AddressID = (SELECT AddressID FROM MovieTicket WHERE ProductID = 2)  ;

/*--4*/
DELETE FROM MovieTicket WHERE MovieTicket.ProductCode = '5089';
/*--Added Movie back in table*/
INSERT INTO MovieTicket(ProductCode, ProductName, MovieTime, AddressID, ScreenNo, Price) VALUES
('5089', 'Room', '2016-09-15 12:30', 21, '13F', 15.00);


/*--5*/
/*SELECT	ProductCode, ProductName 
FROM 	ProductInvoice AS p JOIN SeasonPass AS s ON p.ProductCode = s.ProductCode
JOIN		MovieTicket AS m ON p.ProductCode = m.ProductCode
JOIN 		Refreshment AS r ON p.ProductCode = r.ProductCode
JOIN 		ParkingPass AS pp	ON p.ProductCode = pp.ProductCode
WHERE 	p.ProductCode = " ";
*/
/*--6*/
SELECT InvoiceCode FROM (Invoice AS i JOIN Customers AS c ON i.CustomerID = c.CustomerID) WHERE CustomerCode = 'C001';

/*--7*/
SET FOREIGN_KEY_CHECKS=0;
INSERT INTO ProductInvoice(MovieCode, InvoiceID,Amount)
VALUES ('5090', (SELECT InvoiceID FROM Invoice WHERE InvoiceCode = 'INV001'), 3);
SET FOREIGN_KEY_CHECKS=1;

/*--8*/
SELECT	SUM(Price) AS TotalPrice FROM MovieTicket;


/*--9*/
SELECT	SUM(p.Amount) AS TotalSold 
FROM		(Invoice AS i JOIN ProductInvoice AS p ON i.InvoiceID = p.InvoiceID)
WHERE	i.InvoiceDate = '2016-12-05' AND p.MovieCode IS NOT NULL;

/*--10*/
SELECT COUNT(InvoiceID) AS Total_Invoices
FROM (Invoice AS i JOIN SalePerson AS s ON i.SalePersonID = s.SalePersonID)
JOIN Person AS p ON s.PersonID = p.PersonID
WHERE p.PersonCode = 'nctis';

/*--11*/
SELECT MovieCode, COUNT(InvoiceID) AS Total_Invoices 
FROM ProductInvoice 
WHERE MovieCode = '5089';

/*--12*/
SELECT SUM(Price), MovieCode, InvoiceDate
FROM (ProductInvoice AS p JOIN MovieTicket AS m ON p.MovieCode = m.ProductCode)
JOIN Invoice AS i ON p.InvoiceID = i.InvoiceID
WHERE i.InvoiceDate =  '2016-12-05';

/*--13*/
SELECT (SELECT SUM(Amount) FROM ProductInvoice WHERE ParkingCode IS NOT NULL) AS ParkingPassSold, 
(SELECT SUM(Amount) FROM ProductInvoice WHERE RefreshmentCode IS NOT NULL) AS RefreshmentSold;

/*--14*/
SELECT InvoiceCode FROM (Invoice AS i JOIN ProductInvoice AS p ON i.InvoiceID = p.InvoiceID) WHERE MovieCode IS NOT NULL HAVING (count(*) > 2);

/*--15*/