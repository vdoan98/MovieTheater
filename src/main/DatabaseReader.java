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

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				address = new Address(rs.getString("a.street"), rs.getString("a.city"), rs.getString("a.state"),
						rs.getString("a.zip"), rs.getString("a.country"));
				person = new Person(rs.getString("p.person_code"), rs.getString("p.last_name"),
						rs.getString("p.first_name"), address);
				String id = rs.getString("p.person_code");
				String query2 = "SELECT email_address FROM (Person AS p JOIN Emails AS e ON p.person_id = e.person_id) WHERE p.person_code = ?";
				ps = conn.prepareStatement(query2);
				ps.setString(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					person.addEmail(rs.getString("email_address"));
				}
				personList.addToEnd(person);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return personList;

	}

	public LinkedList readCustomer() {

		Connection conn = DatabaseInfo.getConnection();

		Customer customer;

		// public Customer(String customerCode, char type, Person contact, String name,
		// Address address)
		String query = "SELECT p.person_code, c.customer_code, c.customer_type FROM (Person AS p JOIN Customers AS c ON p.person_id = c.person_id)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				for (int i = 0; i < this.readPersons().getSize(); i++) {
					if (((Person) this.readPersons().getObject(i)).getPersonCode()
							.compareToIgnoreCase(rs.getString("p.person_code")) == 0) {
						if (rs.getString("c.customer_type").charAt(0) == 'S') {
							customer = new Student(rs.getString("c.customer_code"),
									rs.getString("c.customer_type").charAt(0),
									((Person) this.readPersons().getObject(i)),
									((Person) this.readPersons().getObject(i)).getFirstName()
									+ ((Person) this.readPersons().getObject(i)).getLastName(),
									((Person) this.readPersons().getObject(i)).getAddress());
							customerList.addToEnd(customer);
						} else if (rs.getString("c.customer_type").charAt(0) == 'G') {
							customer = new General(rs.getString("c.customer_code"),
									rs.getString("c.customer_type").charAt(0),
									((Person) this.readPersons().getObject(i)),
									((Person) this.readPersons().getObject(i)).getFirstName()
									+ ((Person) this.readPersons().getObject(i)).getLastName(),
									((Person) this.readPersons().getObject(i)).getAddress());
							customerList.addToEnd(customer);
						}

					}
				}
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return customerList;
	}

	public LinkedList readProducts() {

		// TODO: Replace with QUERY to get all information from database.
		Connection conn = DatabaseInfo.getConnection();

		SeasonPass season;
		MovieTicket ticket;
		ParkingPass parking;
		Refreshment refresh;
		Product product;

		String query = "SELECT p.product_code, p.product_type, p.product_name, p.price, p.product_amount,s.start_date,s.end_date FROM (Product AS p JOIN SeasonPass AS s ON p.product_id=s.product_id)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTimeFormatter formatTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
			while (rs.next()) {
				for (int i = 0; i < this.readProducts().getSize(); i++) {
					if (((Product) this.readProducts().getObject(i)).getProductCode()
							.compareToIgnoreCase(rs.getString("p.product_code")) == 0) {
						if (rs.getString("p.product_type").charAt(0) == 'S') {
							String seasonStartDate = rs.getString("s.start_date");
							String seasonEndDate = rs.getString("s.end_date");
							DateTime start_date = formatTime.parseDateTime(seasonStartDate);
							DateTime end_date = formatTime.parseDateTime(seasonEndDate);
							product = new SeasonPass(rs.getString("p.product_code"),
									rs.getString("p.product_type").charAt(0), rs.getString("p.product_name"),
									start_date, end_date, rs.getDouble("p.price"));
							productList.addToEnd(product);
							// Refreshment(String productCode, char type, String name, double price, int
							// amount)
						} else if (rs.getString("p.product_type").charAt(0) == 'R') {
							product = new Refreshment(rs.getString("p.product_code"),
									rs.getString("p.product_type").charAt(0), rs.getString("p.product_name"),
									rs.getDouble("p.product_price"), rs.getInt("p.product_amount"));
							customerList.addToEnd(product);
							// String productCode, char type, DateTime time, String movieName, Address
							// address, String screenNo,
							// double pricePerUnit
						} else if (rs.getString("p.product_type").charAt(0) == 'M') {
							String movieTime = rs.getString("m.movie_time");
							DateTime movie_time = formatTime.parseDateTime(movieTime);
							product = new MovieTicket(rs.getString("p.product_code"),
									rs.getString("p.product_type").charAt(0), movie_time,
									rs.getString("p.product_name"),
									((MovieTicket) this.readProducts().getObject(i)).getAddress(),
									rs.getString("m.screen_no"), rs.getDouble("p.product_amount"));
							customerList.addToEnd(product);
						} else if (rs.getString("p.product_type").charAt(0) == 'P') {
							product = new ParkingPass(rs.getString("p.product_code"),
									rs.getString("p.product_type").charAt(0), rs.getDouble("p.price"));
							customerList.addToEnd(product);
						}
					}
				}
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return productList;

	}

	public LinkedList readInvoices() {


		Connection conn = DatabaseInfo.getConnection();

		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		//Invoke readPersons() and readCustomer() before invoice run 
		//Prevent personList and customerList size from being 0
		this.readPersons();
		this.readCustomer();
		this.readProducts();

		Invoice invoice;

		String query = "SELECT i.invoice_code, c.customer_code FROM (Invoice as i JOIN Customers AS c ON i.invoice_id = c.invoice_id";

		ArrayList<Product> totalProducts ;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				totalProducts = new ArrayList<Product>();
				Customer customer;
				for(int i; i < this.readCustomer().getSize(); i++) {
					if(((Customer)this.readCustomer().getObject(i)).getCustomerCode().compareToIgnoreCase(rs.getString("c.customer_code"))==0){
						customer = ((Customer)this.readCustomer().getObject(i));
					}
				}
				invoice = new Invoice(rs.getString("i.invoice_code"), customer);
				String invoiceCode = rs.getString("i.invoice_code");
				String query2 = "SELECT p.person_code FROM (SalePerson AS sp JOIN Person AS p ON sp.person_id = p.person_id) WHERE sp.invoice_id = ?";
				ps = conn.prepareStatement(query2);
				ps.setString(1, invoiceCode);
				rs = ps.executeQuery();

				for (int i; i < this.readPersons().getSize(); i++) {
					if(((Person)this.readPersons().getObject(i)).getPersonCode().compareToIgnoreCase(rs.getString("p.person_code"))==0) {
						invoice.setSalePerson((Person)this.readPersons().getObject(i));
					}
				}


				String query3 = "SELECT i.sale_date, p.product_code FROM(Invoice AS i JOIN ProductInvoice AS pi ON i.invoice_id = pi.invoice_id) JOIN Product AS p ON pi.product_id = p.product_id WHERE invoice_id = (SELECT invoice_id FROM Invoice WHERE invoice_code = ?)";
				ps = conn.prepareStatement(query3);
				ps.setString(1, invoiceCode);
				rs = ps.executeQuery();
				while(rs.next()) {

					DateTime date = formatter.parseDateTime(rs.getString("i.sale_date"));
					invoice.setDate(date);
					String query4;
					for (int i = 0; i < this.readProducts().getSize(); i++) {
						if (rs.getString("p.product_code").compareToIgnoreCase(((Product)this.readProducts().getObject(i)).getProductCode() )==0) {
							String productCode = rs.getString("p.product_code");
							if (this.readProducts().getObject(i) instanceof MovieTicket){
								//public MovieTicket(String productCode, char type, DateTime time, String movieName, Address address, String screenNo,
								//double pricePerUnit)
								query4 = "SELECT product_amount FROM Product WHERE product_code = ?";
								ps = conn.prepareStatement(query4);
								ps.setString(1, productCode);
								rs = ps.executeQuery();
								MovieTicket tempProduct = new MovieTicket((MovieTicket)this.readProducts().getObject(i));
								tempProduct.isOverStartDate(date);
								tempProduct.setAmount(rs.getInt("product_amount"));
								totalProducts.add(tempProduct);
							}else if (this.readProducts().getObject(i) instanceof SeasonPass){
								SeasonPass tempProduct = new SeasonPass((SeasonPass)this.readProducts().getObject(i));
								tempProduct.isOverStartDate(date);
								tempProduct.setAmount(rs.getInt("product_amount"));
								totalProducts.add(tempProduct);
							}else if (this.readProducts().getObject(i) instanceof Refreshment){
								Refreshment tempProduct = new Refreshment((Refreshment)this.readProducts().getObject(i));
								tempProduct.setAmount(rs.getInt("product_amount"));
								tempProduct.isOverStartDate(date);
								totalProducts.add(tempProduct);
							}else if(this.readProducts().getObject(i) instanceof ParkingPass){
								ParkingPass tempProduct = new ParkingPass((ParkingPass)this.readProducts().getObject(i));
								tempProduct.setAmount(rs.getInt("product_amount")); 
								totalProducts.add(tempProduct);
							}
							break;
						}
					}
				}
				invoice.setProducts(totalProducts);
				invoiceList.addToEnd(invoice);
			}


		}catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}


		

		return invoiceList;
	}

}
