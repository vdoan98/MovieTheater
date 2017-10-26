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


DROP TABLE IF EXISTS Person;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Person(
	PersonID int(11) NOT NULL AUTO_INCREMENT,
	PersonCode varchar(30) DEFAULT NULL,
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


DROP TABLE IF EXISTS Address;
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
            ('3402 Kelly Street', 'Charlotte', 'NC', '28206', 'USA'), ('1408 Dovetail Estates', 'Ringwood', 'OK', '73768'),
            ('1594 Hurry Street', 'Stuarts Drat', 'VA', '24477', 'USA'), ('3409 Pointe Lane', 'Deerfield Beach', 'FL', '33442', 'USA'),
            ('2823 Garfield Road', 'Peoria', 'IL', '61614', 'USA'), ('2207 August Lane', 'Shreveport', 'LA', '71101', 'USA'),
            ('1721 Rosebud Avenue', 'Pine Bluff', 'AR', '71601', 'USA'), ('767 Honeysuckle Lane', 'Portland', 'WA', '97232', 'USA'),
            ('813 Benson Park Drive', 'Newcastle', 'OK', '73065', 'USA'), ('942 Hope Street', 'Plano', 'TX', '75074', 'USA'),
            ('1101 P St', 'Lincoln', 'NE', '68504', 'USA'), ('1881 Nelm Street', 'Beltsville', 'VA', '20705', 'USA'),
            ('2210 White Oak Drive', 'Overland Park', 'MO', '64110', 'USA');
/*!40000 ALTER TABLE Address ENABLE KEYS */;            


DROP TABLE IF EXISTS PersonAddress;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE PersonAddress(
	PersonAddressID int(11) NOT NULL AUTO_INCREMENT,
    PersonCode varchar(30) ,
    AddressID int(11) ,
    PRIMARY KEY (PersonAddressID),
    FOREIGN KEY (PersonCode) REFERENCES Person(PersonCode) ,
    FOREIGN KEY (AddressID) REFERENCES Address(AddressID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE PersonAddress DISABLE KEYS */;
INSERT INTO PersonAddress(PersonCode, AddressID) VALUES 
			('0327m', 1), ('hstuck2', 2), ('35fh', 3), ('fads', 4), ('7df8', 5), ('7457j', 6), ('k54l', 7), ('402', 8),
            ('35po', 9), ('321na', 10), ('rbee', 11), ('34nj', 12), ('606s', 13), ('3knj', 14), ('68zc', 15), ('nctis', 16),
            ('58ht', 17), ('sawin', 18);
/*!40000 ALTER TABLE PersonAddress ENABLE KEYS */;    


DROP TABLE IF EXISTS Emails;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Emails(
	EmailAddressID int(11) NOT NULL AUTO_INCREMENT,
    PersonCode varchar(30) ,
    EmailAddress varchar(50) DEFAULT NULL ,
    PRIMARY KEY (EmailAddressID),
    FOREIGN KEY (PersonCode) REFERENCES Person(PersonCode) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Emails DISABLE KEYS */;
INSERT INTO Emails(PersonCode, EmailAddress) VALUES 
			('0327m', 'tflor@gmail.com'), ('0327m', 'tromm12@yahoo.com'),
            ('hstuck2', 'rosey1@unl.edu'), ('hstuck2', 'rosesherrer@gmail.com'),
            ('35fh', 'roxy123@gmail.com'), ('35fh', 'riley1994@yahoo.com'),
            ('fads', 'mward@twc.net'), ('fads', 'melissaw87@yahoo.com'), ('fads', 'melward@unl.edu'),
            ('7df8', 'lesshel@windstream.net'), ('7df8', 'helen98@gmail.com'),
            ('7457j', 'pray4james@gmail.com'), ('7457j', 'jamespray@unl.edu'), 
            ('k54l', 'wawawatkins@gmail.com'),
            ('35po', 'vickibradley@armyspy.com'), ('35po', 'vbradley@gmail.com'),
            ('321na', 'dovetailestates@windtream.net'), ('321na', 'markstew@twc.net'),
            ('rbee', 'seisssnow@yahoo.com'), ('rbee', 'weissschnee@hotmail.com'),
            ('34nj', 'alicedel@gmail.com'), ('34nj', 'delaney92@yahoo.com'),
            ('606s', 'jefferyt165@gmail.com'), ('606s', 'jefferythomas@unl.edu'),
            ('3knj', 'valerieham@gmail.com'), ('3knj', 'valerihamlin@yahoo.com'),
            ('nctis', 'berndone@tw.net'), ('nctis', 'berndmunch@gmail.com'), ('nctis', 'berndmoench@unl.edu'),
            ('58ht', 'uteute@twc.net'),
            ('sawin', 'lisaluna1@gmail.com'), ('sawin', 'lisaneudort14@yahoo.com');
/*!40000 ALTER TABLE Emails ENABLE KEYS */;    

DROP TABLE IF EXISTS Customers;
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

DROP TABLE IF EXISTS SalePerson;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE SalePerson(
	SalePersonID int(11) NOT NULL AUTO_INCREMENT,
    PersonCode varchar(30) ,
    PRIMARY KEY (SalePersonID),
    FOREIGN KEY (PersonCode) REFERENCES Person(PersonCode) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE SalePerson DISABLE KEYS */;
INSERT INTO SalePerson(PersonCode) VALUES 
			('hstuck2'), ('sawin'), ('rbee'), ('nctis');
/*!40000 ALTER TABLE SalePerson ENABLE KEYS */;  

DROP TABLE IF EXISTS Invoice;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Invoice(
	InvoiceID int(11) NOT NULL AUTO_INCREMENT,
	InvoiceCode varchar(30)  NOT NULL,
    CustomerCode varchar(30) ,
    SalePersonID int(11),
    PRIMARY KEY (InvoiceID),
    FOREIGN KEY (CustomerCode) REFERENCES Customers(CustomerCode),
    FOREIGN KEY (SalePersonID) REFERENCES SalePerson(SalePersonID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE Invoice DISABLE KEYS */;
INSERT INTO Invoice(InvoiceCode, CustomerCode, SalePersonID) VALUES 
			('INV001', 'C001', 1),
            ('INV002', 'C002', 2),
            ('INV003', 'C003', 3),
            ('INV004', 'C004', 4);
/*!40000 ALTER TABLE Invoice ENABLE KEYS */;  

DROP TABLE IF EXISTS SeasonPass;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE SeasonPass(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30)  NOT NULL,
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

DROP TABLE IF EXISTS MovieTicket;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE MovieTicket(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30)  NOT NULL,
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
            ('4v5a', 'Tangerine', '2016-12-10 10:00', '32B', 15.50);
/*!40000 ALTER TABLE MovieTheater ENABLE KEYS */; 

DROP TABLE IF EXISTS Refreshment;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE Refreshment(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30)  NOT NULL,
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


DROP TABLE IF EXISTS ParkingPass;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE ParkingPass(
	ProductID int(11) NOT NULL AUTO_INCREMENT,
	ProductCode varchar(30)  NOT NULL,
    MovieTicketCode varchar(30) DEFAULT NULL,
	SeasonPassCode varchar(30) DEFAULT NULL,
    Price float(11) DEFAULT -9999,
    PRIMARY KEY (ProductID),
    FOREIGN KEY (MovieTicketCode) REFERENCES MovieTicket(ProductCode),
    FOREIGN KEY (SeasonPassCode) REFERENCES SeasonPass(ProductCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE ParkingPass DISABLE KEYS */;
INSERT INTO ParkingPass(ProductCode, PRICE)  VALUES
			('87df', 30.00), ('08ss', 20.00), ('157h', 55.00), ('rcx3', 80.00), ('28bb', 40.00);
/*!40000 ALTER TABLE ParkingPass ENABLE KEYS */;  

SELECT * FROM Person;


/*--Writing Queries for search;*/;
/*-- 1*/
SELECT p.PersonCode, PersonFirstName, PersonLastName, 
				EmailAddress, a.Street, a.State, a.Zip, a.Country 
                FROM (Person AS p JOIN Emails AS e ON p.PersonCode = e.PersonCode) 
                JOIN (Address AS a JOIN pa AS PersonAddress ON a.AddressID = pa.AddressID) 
                ON p.PersonCode = pa.PersonCode;
/*--2*/
INSERT INTO Emails VALUES (PersonCode, NewEmail);

/*--3*/
UPDATE 	Address
SET 		Street = "229 Grove Avenue", State = "OK", Zip = "73750", Country = "USA" 
WHERE	(MovieTicket.AddressID = Address.AddressID);


/*--4*/
DELETE FROM MovieTicket WHERE MovieTicket.ProductCode = "   ";

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



 