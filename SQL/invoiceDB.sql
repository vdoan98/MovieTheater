set foreign_key_checks=0;
DROP TABLE IF EXISTS MovieTicket;
DROP TABLE IF EXISTS ProductInvoice;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS SalePerson;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Invoice;
DROP TABLE IF EXISTS PersonAddress;
DROP TABLE IF EXISTS Address;
DROP TABLE IF EXISTS Emails;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS SeasonPass;
DROP TABLE IF EXISTS Refreshments;
DROP TABLE IF EXISTS ParkingPass;

CREATE TABLE PersonAddress(
	person_id INT(11) NOT NULL DEFAULT '0',
    address_id INT(11) NOT NULL DEFAULT '0',
	PRIMARY KEY (person_id, address_id),
    FOREIGN KEY(address_id) REFERENCES Address(address_id),
    FOREIGN KEY(person_id) REFERENCES Person(person_id)
);
ALTER TABLE PersonAddress CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO PersonAddress(person_id, address_id) VALUES 
			(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8),
            (9, 9), (10, 10), (11, 11), (12, 12), (13, 13), (14, 14), (15, 15), (16, 16),
            (17, 17), (18, 18);

CREATE TABLE Person (
    person_id INT(11) NOT NULL AUTO_INCREMENT,
    person_code VARCHAR(30) NOT NULL DEFAULT '000',
    first_name VARCHAR(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    last_name VARCHAR(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    PRIMARY KEY (person_id)
);
ALTER TABLE Person CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO Person(person_code, first_name, last_name) VALUES 
			('0327m', 'Trommler', 'Florian'), ('hstuck2', 'Sherrer', 'Rose'), ('35fh', 'Riley', 'Roxana'),
			('fads', 'Ward', 'Melissa'), ('7df8', 'Lessard', 'Helen'), ('7457j', 'Pray', 'James'),
            ('k54l', 'Watkins', 'Nancy'), ('402', 'Smith', 'Zoe'), ('35po', 'Bradley', 'Vicki'),
            ('321na', 'Steward', 'Mark'), ('rbee', 'Weiss', 'Charles'), ('34nj', 'Delaney', 'Alice'),
            ('606s', 'Thomas', 'Jeffery'), ('3knj', 'Hamlin', 'Valerie'), ('68zc', 'Marcantel', 'Abraham'),
            ('nctis', 'Moench', 'Bernd'), ('58ht', 'Faust', 'Ute'), ('sawin', 'Neudort', 'Lisa');

CREATE TABLE Address(
	address_id INT(11) NOT NULL AUTO_INCREMENT,
	street VARCHAR(30) NOT NULL DEFAULT '',
    city VARCHAR(30) NOT NULL DEFAULT '',
    state VARCHAR(30) NOT NULL DEFAULT '',
    zip VARCHAR(30) NOT NULL DEFAULT '',
    country VARCHAR(30) NOT NULL DEFAULT '',
    PRIMARY KEY(address_id)
);
ALTER TABLE Address CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO Address(street,city,state,zip,country) VALUES
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
            
CREATE TABLE Customers(
	customer_id INT NOT NULL AUTO_INCREMENT,
    customer_code VARCHAR(30) DEFAULT NULL,
    customer_type VARCHAR(30) DEFAULT NULL,
    person_id INT(11) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
    invoice_id INT(11) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
    /*person_code VARCHAR(30) NOT NULL DEFAULT '000',*/
    PRIMARY KEY(customer_id),
    FOREIGN KEY (person_id) REFERENCES Person(person_id),
    FOREIGN KEY (invoice_id) REFERENCES Invoice(invoice_id)
);
ALTER TABLE Customers CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO Customers(customer_code, person_id, customer_type, invoice_id) VALUES
			('C001', 2, 'S',1), ('C002', 6, 'G',2), ('C003', 18, 'G',3), ('C004', 13, 'S',4), ('C005', 1, 'S',5),
            ('C006', 11, 'G',6), ('C007', 8 ,'S',7), ('C008', 16, 'G',8);
            
CREATE TABLE SalePerson (
    sale_id INT NOT NULL AUTO_INCREMENT,
    person_id INT(30) NOT NULL,
    invoice_id INT(30) NOT NULL,
    PRIMARY KEY (sale_id),
    FOREIGN KEY (person_id)
        REFERENCES Person (person_id),
    FOREIGN KEY (invoice_id)
        REFERENCES Invoice (invoice_id)
);
ALTER TABLE SalePerson CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO SalePerson(person_id,invoice_id) VALUES
	(2,1), (18,2), (11,3), (16,4);
    
CREATE TABLE Emails(
	email_id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL,
    person_code VARCHAR(30) NOT NULL DEFAULT '000',
    email_address VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (email_id),
    FOREIGN KEY (person_id) REFERENCES Person(person_id)
);
ALTER TABLE Emails CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO Emails(person_id,person_code,email_address) VALUES
			(1, '0327m', 'tflor@gmail.com'), (1, '0327m','tromm12@yahoo.com'),
            (2, 'hstuck2', 'rosey1@unl.edu'), (2, 'hstuck2', 'rosesherrer@gmail.com'),
            (3, '35fh', 'roxy123@gmail.com'), (3, '35fh', 'riley1994@yahoo.com'),
            (4, 'fads','mward@twc.net'), (4, 'fads', 'melissaw87@yahoo.com'), (4, 'fads','melward@unl.edu'),
            (5, '7df8','lesshel@windstream.net'), (5, '7df8','helen98@gmail.com'),
            (6, '7457j','pray4james@gmail.com'), (6, '7457j', 'jamespray@unl.edu'), 
            (7, 'k541','wawawatkins@gmail.com'),
            (9, '35po','vickibradley@armyspy.com'), (9, '35p0', 'vbradley@gmail.com'),
            (10, '321na','dovetailestates@windtream.net'), (10, '321na', 'markstew@twc.net'),
            (11, 'rbee','seisssnow@yahoo.com'), (11, 'rbee', 'weissschnee@hotmail.com'),
            (12, '34nj','alicedel@gmail.com'), (12, '34nj','delaney92@yahoo.com'),
            (13, '606s','jefferyt165@gmail.com'), (13, '606s','jefferythomas@unl.edu'),
            (14, '3knj','valerieham@gmail.com'), (14, '3knj', 'valerihamlin@yahoo.com'),
            (16, 'nctis', 'berndone@tw.net'), (16, 'nctis','berndmunch@gmail.com'), (16, 'nctis','berndmoench@unl.edu'),
            (17, '58ht','uteute@twc.net'),
            (18, 'sawin','lisaluna1@gmail.com'), (18, 'sawin','lisaneudort14@yahoo.com');
            
CREATE TABLE Invoice(
	invoice_id INT NOT NULL AUTO_INCREMENT,
    invoice_code VARCHAR(30) DEFAULT NULL,
    person_code VARCHAR(30) DEFAULT NULL,
    sale_id VARCHAR(30) DEFAULT NULL,
    sale_date VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (invoice_id)
);
ALTER TABLE Invoice CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO Invoice(invoice_code,person_code,sale_id,sale_date) VALUES
			('INV001', 'hstuck2', 1,'2016-12-03'),
            ('INV002', 'sawin', 2,'2016-11-10'),
            ('INV003', 'rbee', 3,'2016-12-05'),
            ('INV004', 'nctis', 4,'2016-11-16');

CREATE TABLE Product(
	product_id INT(11) NOT NULL AUTO_INCREMENT,
    product_code VARCHAR(30) DEFAULT NULL,
    product_type VARCHAR(30) DEFAULT NULL,
    product_name VARCHAR(30) DEFAULT NULL,
    product_amount INT(10) DEFAULT NULL,
    price FLOAT(30) DEFAULT NULL,
    PRIMARY KEY (product_id)
);
ALTER TABLE Product CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO Product(product_code, product_type, product_name,product_amount,price) VALUES
	('87df','P','Parking Pass',1,30.00),
	('ga69','R','Cotton Candy',1,3.99),
	('85sf','R','Nachos',30,3.50),
	('586a','M','The Witch',5,11.50),
	('08ss','P','Parking Pass',24,20.00),
	('c67g','S','Holiday Special',5,150.00),
	('157h','P','Parking Pass',5,55.00),
	('9sd0','S','Autumn Special',2,65.00),
	('5089','M','Room',4,15.00),
	('saf9','R','Bacon Cheeseburger',20,7.00),
	('4v5a','M','Tangerine',10,15.50),
	('pro2','S','1 Month Plan',2,40.00),
	('rcx3','P','Parking Pass',1,80.00),
	('6v76','R','Coca Cola-20oz',30,4.99),
	('28bb','P','Parking Pass',5,40.00);
    
CREATE TABLE ProductInvoice(
	product_invoice_id INT(11) NOT NULL AUTO_INCREMENT,
	invoice_id INT NOT NULL DEFAULT '0',
    product_id INT NOT NULL DEFAULT '0',
    
    PRIMARY KEY (product_invoice_id),
    FOREIGN KEY (invoice_id) REFERENCES Invoice(invoice_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
INSERT INTO ProductInvoice(product_id, invoice_id) VALUES
	(4,1),(4,2),(9,2),(4,3), 
    (6,2), (11,3), (8,3), 
    (2,1),(14,1),(10,2),(2,3),(11,4),
	(13,1),(1,2),(7,4);

CREATE TABLE MovieTicket(
	movie_id INT NOT NULL AUTO_INCREMENT,
    product_id INT(11) NOT NULL,
    movie_time VARCHAR(30) DEFAULT NULL,
    address_id INT NOT NULL DEFAULT '0',
    screen_no INT(10) DEFAULT NULL,
    PRIMARY KEY (movie_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id),
    FOREIGN KEY (address_id) REFERENCES Address(address_id)
);
ALTER TABLE MovieTicket CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO MovieTicket(product_id,movie_time,address_id,screen_no) VALUES
			(4,'2016-12-21 15:10', 20, '3A'),
            (9,'2016-09-15 12:30', 21, '13F'),
            (11,'2016-12-10 10:00', 22, '32B');

CREATE TABLE SeasonPass(
	season_id INT(11) NOT NULL AUTO_INCREMENT,
    product_id INT(11) NOT NULL,
    start_date VARCHAR(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    end_date VARCHAR(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
    PRIMARY KEY (season_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
ALTER TABLE SeasonPass CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO SeasonPass(product_id,start_date,end_date) VALUES
	(6, '2016-11-25', '2017-01-10'),
	(8, '2016-09-22', '2016-12-25'),
	(12,'2016-10-07', '2016-11-07');

CREATE TABLE Refreshments(
	refreshment_id INT(11) NOT NULL AUTO_INCREMENT,
    product_id INT(11) NOT NULL,
    PRIMARY KEY (refreshment_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
ALTER TABLE Refreshments CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO Refreshments(product_id)  VALUES
	(2), (3), (10), (14);
    
CREATE TABLE ParkingPass(
	parking_id INT(11) NOT NULL AUTO_INCREMENT,
    product_id INT(11) NOT NULL,
    PRIMARY KEY(parking_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
ALTER TABLE ParkingPass CONVERT TO CHARACTER SET utf8 COLLATE utf8_unicode_ci;
INSERT INTO ParkingPass(product_id) VALUES
(1), (5), (7), (13), (15);
DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees(
	ID INT(11) NOT NULL AUTO_INCREMENT,
	First_Name VARCHAR(30) DEFAULT NULL,
    Last_Name VARCHAR(30) DEFAULT NULL,
    salary DOUBLE DEFAULT NULL,
    PRIMARY KEY (ID)
    );
    INSERT INTO Employees(First_Name,Last_Name,salary) VALUES
	('John','Doe',12345);
	


