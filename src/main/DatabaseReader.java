package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ceg.ext.InvoiceData;

public class DatabaseReader {

	InvoiceData allInfo = new InvoiceData();

	LinkedList personList = new LinkedList();
	LinkedList customerList = new LinkedList();
	LinkedList productList = new LinkedList();
	LinkedList invoiceList = new LinkedList();

	public LinkedList readPersons() {



		//TODO: replace with JDBC
		while () {
			
			//TODO: Replace with construction of Address based on database
			Address address = new Address();

			Person person;

			// Check if there's email information. If not, create person object without
			// email. 
			if (data.length == 4) {
				email = data[3];
				//TODO: Construciton of Person based on database informaiton
				person = new Person();
			} else {
				person = new Person();
			}
			// Adds the Person object into Person ArrayList
		
			personList.addToEnd(person);

		}

		return personList;


	}

	public LinkedList readCustomer() {
		
		//TODO: Replace with QUERY to get all information from database
		while () {

			int code = 0;

			for (int i = 0; i < personList.getSize(); i++) {
				if (data[2].compareTo(((Person)personList.getObject(i)).getPersonCode()) == 0) {
					code = i;
				}
			}

			if (data[1].charAt(0) == 'S') {
				//TODO: Creation of new student based on information in database. 
				Student customer = new Student();
				// Adds the Person object into Person ArrayList

				customerList.addToEnd(customer);
			} else if (data[1].charAt(0) == 'G') {
				//TODO: Construction of new customer based on database.
				General customer = new General();
				// Adds the Person object into Person ArrayList

				customerList.addToEnd(customer);
			}

		}

		sc.close();
		return customerList;


	}

	public LinkedList readProducts() {


		//TODO: Replace with QUERY to get all information from database. 
		while () {

			SeasonPass season;
			MovieTicket ticket;
			ParkingPass parking;
			Refreshment refresh;

			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTimeFormatter formatTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

			switch (data[1].charAt(0)) {
			case 'S':
				//TODO: Construction of new SeasonPass based on database 
				season = new SeasonPass();

				productList.addToEnd(season);
				break;
			case 'M':
				//TODO: Construction of new Address based on database.
				Address address = new Address();
				//TODO: Construction of new MovieTicket based on database. 
				ticket = new MovieTicket();
				
				productList.addToEnd(ticket);
				break;
			case 'P':
				//TODO: Construction of new ParkingPass based on database
				parking = new ParkingPass();

				productList.addToEnd(parking);
				break;
			case 'R':
				//TODO: Construction of new Refreshment based on database 
				refresh = new Refreshment();
				
				productList.addToEnd(refresh);;
				break;

			}

		}


		return productList;


	}

	public LinkedList readInvoices() {


		//Invoke readPersons() and readCustomer() before invoice run 
		//Prevent personList and customerList size from being 0
		this.readPersons();
		this.readCustomer();

		


			while () {
			
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

				//TODO: Read in information for invoices. 
				LinkedList totalProducts = new LinkedList();

				
				
				invoiceList.addToEnd(invoice);
			}

			return invoiceList;
	}

}
