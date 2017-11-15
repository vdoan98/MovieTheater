package main;

import java.sql.*;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ceg.ext.DatabaseInfo;
import com.ceg.ext.InvoiceData;

public class DatabaseReader {

	InvoiceData allInfo = new InvoiceData();

	LinkedList personList = new LinkedList();
	LinkedList customerList = new LinkedList();
	LinkedList productList = new LinkedList();
	LinkedList invoiceList = new LinkedList();

	public LinkedList readPersons() {

		Connection conn = DatabaseInfo.getConnection();

		Person person;
		Address address;

		String query = "SELECT p.person_id, p.person_code, p.last_name, p.first_name, a.street, a.city, a.state, a.zip, a.country FROM ((Person AS p JOIN PersonAddress AS pa ON p.person_id = pa.person_id) JOIN Address AS a ON pa.address_id = a.address_id)";

		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				address = new Address(rs.getString("a.street"), rs.getString("a.city"), rs.getString("a.state"), rs.getString("a.zip"), rs.getString("a.country"));
				person = new Person(rs.getString("p.person_code"), rs.getString("p.last_name"), rs.getString("p.first_name"), address);
				String id = rs.getString("p.person_code");
				String query2 = "SELECT email_address FROM (Person AS p JOIN Emails AS e ON p.person_id = e.person_id) WHERE p.person_code = ?";
				ps = conn.prepareStatement(query2);
				ps.setString(1, id);
				rs = ps.executeQuery();
				while(rs.next()){
					person.addEmail(rs.getString("email_address"));
				}
				personList.addToEnd(person);
			}
			rs.close();

		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return personList;


	}

	public LinkedList readCustomer() {

		Connection conn = DatabaseInfo.getConnection();

		Customer customer;

		//public Customer(String customerCode, char type, Person contact, String name, Address address) 
		String query = "SELECT p.person_code, c.customer_code, c.customer_type FROM (Person AS p JOIN Customers AS c ON p.person_id = c.person_id)";

		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				for (int i = 0; i < this.readPersons().getSize(); i++){
					if(((Person)this.readPersons().getObject(i)).getPersonCode().compareToIgnoreCase(rs.getString("p.person_code")) == 0){
						if (rs.getString("c.customer_type").charAt(0) == 'S'){
							customer = new Student(rs.getString("c.customer_code"), rs.getString("c.customer_type").charAt(0),
									((Person)this.readPersons().getObject(i)), ((Person)this.readPersons().getObject(i)).getFirstName() + ((Person)this.readPersons().getObject(i)).getLastName(),
									((Person)this.readPersons().getObject(i)).getAddress());
							customerList.addToEnd(customer);
						}else if (rs.getString("c.customer_type").charAt(0) == 'G'){
							customer = new General(rs.getString("c.customer_code"), rs.getString("c.customer_type").charAt(0),
									((Person)this.readPersons().getObject(i)), ((Person)this.readPersons().getObject(i)).getFirstName() + ((Person)this.readPersons().getObject(i)).getLastName(),
									((Person)this.readPersons().getObject(i)).getAddress());
							customerList.addToEnd(customer);
						}



					}
				}
			}
			rs.close();

		}catch (SQLException e){
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}


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
