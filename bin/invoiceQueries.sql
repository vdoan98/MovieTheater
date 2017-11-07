/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

SHOW WARNINGS;
DROP TABLE IF EXISTS ProductInvoice;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS PersonAddress;
DROP TABLE IF EXISTS Emails;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS SalePerson;
DROP TABLE IF EXISTS Invoice;
DROP TABLE IF EXISTS SeasonPass;
DROP TABLE IF EXISTS MovieTicket;
DROP TABLE IF EXISTS Refreshment;
DROP TABLE IF EXISTS ParkingPass;
DROP TABLE IF EXISTS PersonAddress;
DROP TABLE IF EXISTS Person;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Person(
	PersonID int(11) NOT NULL AUTO_INCREMENT,
	PersonCode varchar(30) NOT NULL DEFAULT '000',
    PersonFirstName varchar(30) DEFAULT NULL,
    PersonLastName varchar(30) DEFAULT NULL,
    PRIMARY KEY (PersonID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Person DISABLE KEYS */;
INSERT INTO Person(PersonCode, PersonFirstName, PersonLastName) VALUES 
			('0327m', 'Trommler', 'Florian'), ('hstuck2', 'Sherrer', 'Rose'), ('35fh', 'Riley', 'Roxana'),
			('fads', 'Ward', 'Melissa'), ('7df8', 'Lessard', 'Helen'), ('7457j', 'Pray', 'James'),
            ('k54l', 'Watkins', 'Nancy'), ('402', 'Smith', 'Zoe'), ('35po', 'Bradley', 'Vicki'),
            ('321na', 'Steward', 'Mark'), ('rbee', 'Weiss', 'Charles'), ('34nj', 'Delaney', 'Alice'),
            ('606s', 'Thomas', 'Jeffery'), ('3knj', 'Hamlin', 'Valerie'), ('68zc', 'Marcantel', 'Abraham'),
            ('nctis', 'Moench', 'Bernd'), ('58ht', 'Faust', 'Ute'), ('sawin', 'Neudort', 'Lisa');
/*!40000 ALTER TABLE Person ENABLE KEYS */;


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Address(
	AddressID int(11) NOT NULL AUTO_INCREMENT,
    Street varchar(50) DEFAULT NULL,
    City varchar(50) DEFAULT NULL,
    State varchar(30) DEFAULT NULL,
    Zip int(11) DEFAULT NULL,
    Country varchar(30) DEFAULT NULL,
    PRIMARY KEY (AddressID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Address DISABLE KEYS */;
INSERT INTO Address(Street, City, State, Zip, Country) VALUES 
			('229 Grove Avenue', 'Kingfisher', 'OK', '73750', 'USA'), ('994 Blair Court', 'Blairstown', 'MO', '64726', 'USA'),
            ('637 Devils Hill Road', 'Jackson', 'MS', '39201', 'USA'), ('4139 Harter Street', 'Urbana', 'OH', '43078', 'USA'),
            ('4476 Cedar Street', 'Mount Ida', 'AR', '71957', 'USA'), ('4153 Ferrell Street', 'Callaway', 'MN', '56521', 'USA'),
            ('543 Butternut Lane', 'Renault', 'IL', '62279', 'USA'), ('1262 Ashford Drive', 'Mc Lean', 'VA', '22102', 'USA'),
            ('3402 Kelly Street', 'Charlotte', 'NC', '28206', 'USA'), ('1408 Dovetail Estates', 'Ringwood', 'OK', '73768','USA'),
            ('1594 Hurry Street', 'Stuarts Drat', 'VA', '24477', 'USA'), ('3409 Pointe Lane', 'Deerfield Beach', 'FL', '33442', 'USA'),
            ('2823 Garfield Road', 'Peoria', 'IL', '61614', 'USA'), ('2207 August Lane', 'Shreveport', 'LA', '71101', 'USA'),
            ('1721 Rosebud Avenue', 'Pine Bluff', 'AR', '71601', 'USA'), ('767 Honeysuckle Lane', 'Portland', 'WA', '97232', 'USA'),
            ('813 Benson Park Drive', 'Newcastle', 'OK', '73065', 'USA'), ('942 Hope Street', 'Plano', 'TX', '75074', 'USA'),
            ('1101 P St', 'Lincoln', 'NE', '68504', 'USA'), ('1881 Nelm Street', 'Beltsville', 'VA', '20705', 'USA'),
            ('2210 White Oak Drive', 'Overland Park', 'MO', '64110', 'USA');
/*!40000 ALTER TABLE Address ENABLE KEYS */;            

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE PersonAddress(
	PersonAddressID int(11) NOT NULL AUTO_INCREMENT,
    PersonCode varchar(30) NOT NULL DEFAULT '000',
    AddressID int(11) DEFAULT NULL ,
    PersonID int(11) DEFAULT NULL,
    PRIMARY KEY (PersonAddressID),
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID) ,
    FOREIGN KEY (AddressID) REFERENCES Address(AddressID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE PersonAddress DISABLE KEYS */;
INSERT INTO PersonAddress(PersonCode, AddressID) VALUES 
			(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8),
            (9, 9), (10, 10), (11, 11), (12, 12), (13, 13), (14, 14), (15, 15), (16, 16),
            (17, 17), (18, 18);
/*!40000 ALTER TABLE PersonAddress ENABLE KEYS */;    


/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Emails(
	EmailAddressID int(11) NOT NULL AUTO_INCREMENT,
    PersonID int(11) DEFAULT NULL,
    EmailAddress varchar(50) DEFAULT NULL ,
    PRIMARY KEY (EmailAddressID),
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Emails DISABLE KEYS */;
INSERT INTO Emails(PersonID, EmailAddress) VALUES 
			(1, 'tflor@gmail.com'), (1, 'tromm12@yahoo.com'),
            (2, 'rosey1@unl.edu'), (2, 'rosesherrer@gmail.com'),
            (3, 'roxy123@gmail.com'), (3, 'riley1994@yahoo.com'),
            (4, 'mward@twc.net'), (4, 'melissaw87@yahoo.com'), (4, 'melward@unl.edu'),
            (5, 'lesshel@windstream.net'), (5, 'helen98@gmail.com'),
            (6, 'pray4james@gmail.com'), (6, 'jamespray@unl.edu'), 
            (7, 'wawawatkins@gmail.com'),
            (9, 'vickibradley@armyspy.com'), (9, 'vbradley@gmail.com'),
            (10, 'dovetailestates@windtream.net'), (10, 'markstew@twc.net'),
            (11, 'seisssnow@yahoo.com'), (11, 'weissschnee@hotmail.com'),
            (12, 'alicedel@gmail.com'), (12, 'delaney92@yahoo.com'),
            (13, 'jefferyt165@gmail.com'), (13, 'jefferythomas@unl.edu'),
            (14, 'valerieham@gmail.com'), (14, 'valerihamlin@yahoo.com'),
            (16, 'berndone@tw.net'), (16, 'berndmunch@gmail.com'), (16, 'berndmoench@unl.edu'),
            (17, 'uteute@twc.net'),
            (18, 'lisaluna1@gmail.com'), (18, 'lisaneudort14@yahoo.com');
/*!40000 ALTER TABLE Emails ENABLE KEYS */;    

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Customers(
	CustomerID int(11) NOT NULL AUTO_INCREMENT,
	CustomerCode varchar(30) DEFAULT NULL,
    PersonAddressID int(11) ,
    CustomerType varchar(30) DEFAULT NULL ,
    PRIMARY KEY (CustomerID),
    FOREIGN KEY (PersonAddressID) REFERENCES PersonAddress(PersonAddressID) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Customers DISABLE KEYS */;
INSERT INTO Customers(CustomerCode, PersonAddressID, CustomerType) VALUES 
			('C001', 2, 'S'), ('C002', 6, 'G'), ('C003', 18, 'G'), ('C004', 13, 'S'), ('C005', 1, 'S'),
            ('C006', 11, 'G'), ('C007', 8, 'S'), ('C008', 16, 'G');
/*!40000 ALTER TABLE Customers ENABLE KEYS */;  

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE SalePerson(
	SalePersonID int(11) NOT NULL AUTO_INCREMENT,
    PersonID int(11) DEFAULT NULL,
    PRIMARY KEY (SalePersonID),
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE SalePerson DISABLE KEYS */;
INSERT INTO SalePerson(PersonID) VALUES 
			(2), (18), (11), (17);
/*!40000 ALTER TABLE SalePerson ENABLE KEYS */;  

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Invoice(
	InvoiceID int(11) NOT NULL AUTO_INCREMENT,
	InvoiceCode varchar(30) NOT NULL,
    CustomerID int(11) NOT NULL,
    SalePersonID int(11) NOT NULL,
    PRIMARY KEY (InvoiceID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (SalePersonID) REFERENCES SalePerson(SalePersonID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Invoice DISABLE KEYS */;
INSERT INTO Invoice(InvoiceCode, CustomerID, SalePersonID) VALUES 
			('INV001', 1, 1),
            ('INV002', 2, 2),
            ('INV003', 3, 3),
            ('INV004', 4, 4);
/*!40000 ALTER TABLE Invoice ENABLE KEYS */;  

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE SeasonPass(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30) UNIQUE NOT NULL,
    ProductName varchar(30) DEFAULT NULL ,
    StartDate varchar(30) DEFAULT NULL,
    EndDate varchar(30) DEFAULT NULL,
    Price float(11) DEFAULT NULL,
    PRIMARY KEY (ProductID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE SeasonPass DISABLE KEYS */;
INSERT INTO SeasonPass(ProductCode, ProductName, StartDate, EndDate, Price) VALUES 
			('c67g', 'Holiday Special', '2016-11-25', '2017-01-10', 150.00),
            ('9sd0', 'Autumn Special', '2016-09-22', '2016-12-25', 65.00),
            ('pro2', '1 Month Plan', '2016-10-07', '2016-11-07', 40.00);
/*!40000 ALTER TABLE SeasonPass ENABLE KEYS */; 


CREATE TABLE MovieTicket(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30) UNIQUE NOT NULL,
    ProductName varchar(30) DEFAULT NULL,
    MovieTime varchar(30),
    AddressID int(11),
	ScreenNo varchar(10) DEFAULT NULL,
    Price float(11) DEFAULT -99999,
    PRIMARY KEY (ProductID),
    FOREIGN KEY (AddressID) REFERENCES Address(AddressID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE MovieTicket DISABLE KEYS */;
INSERT INTO MovieTicket(ProductCode, ProductName, MovieTime, AddressID, ScreenNo, Price) VALUES
			('586a', 'The Witch', '2016-12-21 15:10', 20, '3A', 11.50),
            ('5089', 'Room', '2016-09-15 12:30', 21, '13F', 15.00),
            ('4v5a', 'Tangerine', '2016-12-10 10:00', 22, '32B', 15.50);
/*!40000 ALTER TABLE MovieTicket ENABLE KEYS */; 

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Refreshment(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30) UNIQUE NOT NULL,
    ProductName varchar(30) DEFAULT NULL,
    Price float(11),
    PRIMARY KEY (ProductID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Refreshment DISABLE KEYS */;
INSERT INTO Refreshment(ProductCode, ProductName, Price)  VALUES
			('ga69', 'Cotton Candy', 3.99), ('85sf', 'Nachos', 3.50), ('saf9', 'Bacon Cheeseburger', 7.00),
            ('6v76', 'Coca Cola-20oz', 4.99);		
/*!40000 ALTER TABLE Refreshment ENABLE KEYS */;             

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ParkingPass(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30) UNIQUE NOT NULL,
    MovieTicketCode varchar(30) DEFAULT NULL,
	SeasonPassCode varchar(30) DEFAULT NULL,
    Price float(11) DEFAULT -9999,
    PRIMARY KEY (ProductID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE ParkingPass DISABLE KEYS */;
INSERT INTO ParkingPass(ProductCode, PRICE)  VALUES
			('87df', 30.00), ('08ss', 20.00), ('157h', 55.00), ('rcx3', 80.00), ('28bb', 40.00);
/*!40000 ALTER TABLE ParkingPass ENABLE KEYS */; 

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ProductInvoice(
	ProductInvoiceID int(11) NOT NULL AUTO_INCREMENT,
    MovieCode varchar(30) DEFAULT NULL,
    SeasonCode varchar(30) DEFAULT NULL,
    RefreshmentCode varchar(30) DEFAULT NULL,
    ParkingCode varchar(30) DEFAULT NULL,
    InvoiceID int(11) DEFAULT NULL,
    Amount int(11) DEFAULT NULL,
    PRIMARY KEY (ProductInvoiceID),
    FOREIGN KEY (InvoiceID) REFERENCES Invoice(InvoiceID),
    FOREIGN KEY (MovieCode) REFERENCES MovieTicket(ProductCode),
    FOREIGN KEY (SeasonCode) REFERENCES SeasonPass(ProductCode),
    FOREIGN KEY (RefreshmentCode) REFERENCES Refreshment(ProductCode),
    FOREIGN KEY (ParkingCode) REFERENCES ParkingCode(ProductCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE ProductInvoice DISABLE KEYS */;
INSERT INTO ProductInvoice(MovieCode,InvoiceID,Amount) VALUES
			('586a', 1, 1), ('586a', 2, 1), ('5089',2,30), ('586a', 3, 5);
INSERT INTO ProductInvoice(SeasonCode,InvoiceID,Amount) VALUES
			('c67g',2,24),('4v5a',3,5),('9sd0',3,5);
INSERT INTO ProductInvoice(RefreshmentCode,InvoiceID,Amount) VALUES
			('ga69',1,2), ('6v76',1,4),('saf9',2,20), ('ga69',3,10), ('4v5a',4,2);
INSERT INTO ProductInvoice(MovieCode, ParkingCode,InvoiceID,Amount) VALUES
			('586a','rcx3',1, 1),('586a','87df',2,30),(NULL, '157h',4,5);
/*!40000 ALTER TABLE ProductInvoice ENABLE KEYS */;  

SELECT * FROM Person;
SELECT * FROM ProductInvoice;

/*--Writing Queries for search;*/;
/*-- 1*/
SELECT p.PersonCode, PersonFirstName, PersonLastName, EmailAddress, a.Street, a.State, a.Zip, a.Country
	FROM(Person AS p JOIN Emails AS e ON p.PersonID = e.PersonID)
	JOIN (Address AS a JOIN PersonAddress AS pa ON a.AddressID = pa.AddressID) ON p.PersonID = pa.PersonID;
/*--2*/
INSERT INTO Emails (PersonID, EmailAddress)
	VALUES (1,'hstucknew@gmail.com');

/*--3*/
UPDATE 	Address
SET 		Street = '29 Grove Avenue', State = 'OK', Zip = '73750', Country = 'USA' 
WHERE	(MovieTicket.AddressID = Address.AddressID);


/*--4*/
DELETE FROM MovieTicket WHERE MovieTicket.ProductCode = '5089';

/*--5*/
SELECT	ProductCode, ProductName 
FROM 	ProductInvoice AS p JOIN SeasonPass AS s ON p.ProductCode = s.ProductCode
JOIN		MovieTicket AS m ON p.ProductCode = m.ProductCode
JOIN 		Refreshment AS r ON p.ProductCode = r.ProductCode
JOIN 		ParkingPass AS pp	ON p.ProductCode = pp.ProductCode
WHERE 	p.ProductCode = " ";

/*--6*/
SELECT InvoiceCode FROM Invoice WHERE CustomerCode = "    ";

/*--7*/
INSERT INTO 	ProductInvoice 
VALUES 				(ProductInvoiceID, ProductCode, InvoiceCode, Amount);

/*--8*/
SELECT	SUM(Price) AS TotalPrice FROM MovieTicket;


/*--9*/
SELECT	SUM(Amount) AS TotalSold 
FROM		(ProductInvoice AS p JOIN MovieTicket AS m ON p.ProductCode = m.ProductCode)
WHERE	m.Time = "   ";



 