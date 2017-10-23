DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person`(
	`PersonCode` varchar(30) DEFAULT NULL,
    `PersonFirstName` varchar(30) DEFAULT NULL,
    `PersonLastName` varchar(30) DEFAULT NULL,
    PRIMARY KEY (`PersonCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address`(
	`AddressID` int(11) NOT NULL AUTO_INCREMENT,
    `Street` varchar(30) DEFAULT NULL,
    `State` varchar(30) DEFAULT NULL,
    `Zip` int(11) DEFAULT NULL,
    `Country` varchar(30) DEFAULT NULL,
    PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `PersonAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PersonAddress`(
	`PersonAddressID` int(11) NOT NULL AUTO_INCREMENT,
    `PersonCode` varchar(30) ,
    `AddressID` int(11) ,
    PRIMARY KEY (`PersonAddressID`),
    FOREIGN KEY (`PersonCode`) REFERENCES Person(`PersonCode`) ,
    FOREIGN KEY (`AddressID`) REFERENCES Address(`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Emails`(
	`EmailAddressID` int(11) NOT NULL AUTO_INCREMENT,
    `PersonCode`varchar(30) ,
    `EmailAddress` varchar(50) DEFAULT NULL ,
    PRIMARY KEY (`EmailAddressID`),
    FOREIGN KEY (`PersonCode`) REFERENCES Person(`PersonCode`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customers`(
	`CustomerCode` varchar(30) DEFAULT NULL,
    `PersonAddressID` int(11) ,
    `Type` varchar(30) DEFAULT NULL ,
    `Type` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`CustomerCode`),
    FOREIGN KEY (`PersonAddressID`) REFERENCES PersonAddress(`PersonAddressID`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `SalePerson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SalePerson`(
	`SalePersonID` int(11) NOT NULL AUTO_INCREMENT,
    `PersonCode` int(11) ,
    PRIMARY KEY (`SalePersonID`),
    FOREIGN KEY (`PersonCode`) REFERENCES Person(`PersonCode`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Invoice`(
	`InvoiceCode` varchar(30)  NOT NULL,
    `CustomerCode` varchar(30) ,
    `SalePersonID` int(11),
    PRIMARY KEY (`InvoiceCode`),
    FOREIGN KEY (`CustomerCode`) REFERENCES Customers(`CustomerCode`),
    FOREIGN KEY (`SalePersonID`) REFERENCES SalePerson(`SalePersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `SeasonPass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SeasonPass`(
	`ProductCode` varchar(30)  NOT NULL,
    `ProductName` varchar(30) DEFAULT NULL ,
    `StartDate` varchar(30) DEFAULT NULL,
    `EndDate` varchar(30) DEFAULT NULL,
    `Price` float(11) DEFAULT NULL,
    PRIMARY KEY (`ProductCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `MovieTicket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MovieTicket`(
	`ProductCode` varchar(30)  NOT NULL,
    `ProductName` varchar(30) DEFAULT NULL,
    `Time` varchar(30),
    `AddressID` int(11),
    `ScreenNo` int(11) DEFAULT NULL,
    PRIMARY KEY (`ProductCode`),
    FOREIGN KEY (`AddressID`) REFERENCES Address(`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `Refreshment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Refreshment`(
	`ProductCode` varchar(30)  NOT NULL,
    `ProductName` varchar(30) DEFAULT NULL,
    `Price` float(11),
    PRIMARY KEY (`ProductCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `ParkingPass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ParkingPass`(
	`ProductCode` varchar(30)  NOT NULL,
    `MovieTicketCode` varchar(30) DEFAULT NULL,
	`SeasonPassCode` varchar(30) DEFAULT NULL,
    PRIMARY KEY (`ProductCode`),
    FOREIGN KEY (`MovieTicketCode`) REFERENCES MovieTicket(`ProductCode`),
    FOREIGN KEY (`SeasonPassCode`) REFERENCES SeasonPass(`ProductCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


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
SET 		Street = "   ", State = "   ", Zip = "   ", Country = "   " 
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


/*--7*/

/*--8*/

/*--9*/




 