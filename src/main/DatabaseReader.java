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
				ResultSet rs2 = ps.executeQuery();

				while (rs2.next()) {
					person.addEmail(rs2.getString("email_address"));
				}
				rs2.close();
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
		LinkedList tempPerson = this.readPersons();
		Customer customer = null;

		// public Customer(String customerCode, char type, Person contact, String name,
		// Address address)
		String query = "SELECT p.person_code, c.customer_code, c.customer_type FROM (Person AS p JOIN Customers AS c ON p.person_id = c.person_id)";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int code = 0;

				for (int i = 0; i < tempPerson.getSize(); i++) {
					if (((Person) tempPerson.getObject(i)).getPersonCode()
							.compareToIgnoreCase(rs.getString("p.person_code")) == 0) {
						code = i;
					}
				}


				if (((Person) tempPerson.getObject(code)).getPersonCode()
						.compareToIgnoreCase(rs.getString("p.person_code")) == 0) {
					if (rs.getString("c.customer_type").charAt(0) == 'S') {
						customer = new Student(rs.getString("c.customer_code"),
								'S', ((Person) tempPerson.getObject(code)),
								((Person) tempPerson.getObject(code)).getFirstName() + " "
										+ ((Person) tempPerson.getObject(code)).getLastName(),
										((Person) tempPerson.getObject(code)).getAddress());
						customerList.addToEnd(customer);
					} else if (rs.getString("c.customer_type").charAt(0) == 'G') {
						customer = new General(rs.getString("c.customer_code"),
								'G', ((Person) tempPerson.getObject(code)),
								((Person) tempPerson.getObject(code)).getFirstName() + " "
										+ ((Person) tempPerson.getObject(code)).getLastName(),
										((Person) tempPerson.getObject(code)).getAddress());
						customerList.addToEnd(customer);
						
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


		Product product = null;
		Address address = null;
		//String query = ("SELECT p.*, s.* FROM (Product AS p JOIN SeasonPass AS s ON p.product_id = s.product_id");
		String query = "SELECT product_code, product_type, product_name, price FROM Product";

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
			DateTimeFormatter formatTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
			while (rs.next()) {
				if (rs.getString("product_type").charAt(0) == 'S') {
					String query2 = "SELECT s.start_date, s.end_date FROM (Product AS p JOIN SeasonPass as s ON p.product_id = s.product_id) WHERE p.product_code = ?";
					ps = conn.prepareStatement(query2);
					ps.setString(1, rs.getString("product_code"));
					ResultSet rs2 = ps.executeQuery();
					if(rs2.next()) {
						String seasonStartDate = rs2.getString("s.start_date");
						String seasonEndDate = rs2.getString("s.end_date");
						DateTime start_date = formatter.parseDateTime(seasonStartDate);
						DateTime end_date = formatter.parseDateTime(seasonEndDate);
						product = new SeasonPass(rs.getString("product_code"), rs.getString("product_type").charAt(0),
								rs.getString("product_name"), start_date, end_date, rs.getDouble("price"));
						productList.addToEnd(product);
					}
					
					rs2.close();
				} else if (rs.getString("product_type").charAt(0) == 'R') {
					product = new Refreshment(rs.getString("product_code"), 'R',
							rs.getString("product_name"), rs.getDouble("price"));
					productList.addToEnd(product);
				} else if (rs.getString("product_type").charAt(0) == 'M') {
					String query3 = "SELECT a.street, a.city, a.state, a.zip, a.country, a.address_id, m.address_id FROM (Address AS a JOIN MovieTicket AS m ON a.address_id = m.address_id) JOIN Product AS p ON p.product_id = m.product_id WHERE p.product_code = ? ";
					ps = conn.prepareStatement(query3);
					ps.setString(1, rs.getString("product_code"));
					ResultSet rs3 = ps.executeQuery();
					if(rs3.next()) {
						address = new Address(rs3.getString("a.street"), rs3.getString("a.city"), rs3.getString("a.state"),
								rs3.getString("a.zip"), rs3.getString("a.country"));
						
					}
					
					String query4 = "SELECT m.movie_time, m.screen_no FROM (Product AS p JOIN MovieTicket AS m ON p.product_id = m.product_id) WHERE p.product_code = ?";
					ps = conn.prepareStatement(query4);
					ps.setString(1, rs.getString("product_code"));
					ResultSet rs4 = ps.executeQuery();
					if(rs4.next()) {
						String movieTime = rs4.getString("m.movie_time");
						DateTime movie_time = formatTime.parseDateTime(movieTime);
						product = new MovieTicket(rs.getString("product_code"), 'M',
								movie_time, rs.getString("product_name"), address, rs4.getString("m.screen_no"),
								0);
						((MovieTicket) product).setPricePerUnit(rs.getDouble("price"));
						productList.addToEnd(product);
					}
					
					
					rs3.close();
					rs4.close();
				} else if (rs.getString("product_type").charAt(0) == 'P') {
					product = new ParkingPass(rs.getString("product_code"), 'P',
							rs.getDouble("price"));
					productList.addToEnd(product);
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
		// Invoke readPersons() and readCustomer() before invoice run
		// Prevent personList and customerList size from being 0
		LinkedList tempPerson = this.readPersons();
		LinkedList tempCustomer = this.readCustomer();
		LinkedList tempProducts = this.readProducts();

		Invoice invoice;

		String query = "SELECT i.invoice_code, c.customer_code FROM (Invoice AS i JOIN Customers AS c ON i.invoice_id = c.invoice_id)";

		ArrayList<Product> totalProducts;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				totalProducts = new ArrayList<Product>();
				invoice = new Invoice(rs.getString("i.invoice_code"));
				for (int i = 0; i < tempCustomer.getSize(); i++) {
					if (((Customer) tempCustomer.getObject(i)).getCustomerCode()
							.compareToIgnoreCase(rs.getString("c.customer_code")) == 0) {
						invoice.setCustomer(((Customer) tempCustomer.getObject(i)));
					}
				}

				String invoiceCode = rs.getString("i.invoice_code");
				String query2 = "SELECT p.person_code FROM ((SalePerson AS sp JOIN Person AS p ON sp.person_id = p.person_id) JOIN Invoice AS i) WHERE i.invoice_code = ?";

				ps = conn.prepareStatement(query2);
				ps.setString(1, invoiceCode);
				ResultSet rs2 = ps.executeQuery();
				if(rs2.next()) {
					for (int i = 0; i < tempPerson.getSize(); i++) {
						if (((Person) tempPerson.getObject(i)).getPersonCode()
								.compareToIgnoreCase(rs2.getString("p.person_code")) == 0) {
							invoice.setSalePerson((Person) tempPerson.getObject(i));
						}
					}
				}
				rs2.close();


				String query3 = "SELECT p.product_id, i.sale_date, p.product_code FROM(Invoice AS i JOIN ProductInvoice AS pi ON i.invoice_id = pi.invoice_id) JOIN Product AS p ON pi.product_id = p.product_id WHERE i.invoice_id = (SELECT invoice_id FROM Invoice WHERE invoice_code = ?)";

				ps = conn.prepareStatement(query3);
				ps.setString(1, invoiceCode);
				ResultSet rs3 = ps.executeQuery();
				while (rs3.next()) {
					DateTime date = formatter.parseDateTime(rs3.getString("i.sale_date"));
					invoice.setDate(date);
					String query4;
					for (int i = 0; i < tempProducts.getSize(); i++) {
						String productCode = rs3.getString("p.product_code");
						if (productCode.compareToIgnoreCase(((Product) tempProducts.getObject(i)).getProductCode()) == 0) {
							String productID = rs3.getString("p.product_id");
							if (tempProducts.getObject(i) instanceof MovieTicket) {
								// public MovieTicket(String productCode, char type, DateTime time, String
								// movieName, Address address, String screenNo,
								// double pricePerUnit)
								query4 = "SELECT pi.product_amount FROM (ProductInvoice AS pi JOIN Product AS p ON pi.product_id = p.product_id) WHERE p.product_code = ?";
								ps = conn.prepareStatement(query4);
								ps.setString(1, productCode);
								ResultSet rs4 = ps.executeQuery();
								MovieTicket tempProduct = new MovieTicket((MovieTicket) tempProducts.getObject(i));
								tempProduct.isOverStartDate(date);
								if(rs4.next()) {
									tempProduct.setAmount(rs4.getInt("pi.product_amount"));
								}
								totalProducts.add(tempProduct);
								rs4.close();
							} else if (tempProducts.getObject(i) instanceof SeasonPass) {
								query4 = "SELECT pi.product_amount FROM (ProductInvoice AS pi JOIN Product AS p ON pi.product_id = p.product_id) WHERE p.product_code = ?";
								//								query4 = "SELECT product_amount FROM Product WHERE product_code = ?";
								ps = conn.prepareStatement(query4);
								ps.setString(1, productCode);
								ResultSet rs4 = ps.executeQuery();
								SeasonPass tempProduct = new SeasonPass((SeasonPass) tempProducts.getObject(i));
								tempProduct.isOverStartDate(date);
								if(rs4.next()) {
									tempProduct.setAmount(rs4.getInt("pi.product_amount"));
								}
								totalProducts.add(tempProduct);
								rs4.close();
							} else if (tempProducts.getObject(i) instanceof Refreshment) {
								query4 = "SELECT pi.product_amount FROM (ProductInvoice AS pi JOIN Product AS p ON pi.product_id = p.product_id) WHERE p.product_code = ?";
								ps = conn.prepareStatement(query4);
								ps.setString(1, productCode);
								ResultSet rs4 = ps.executeQuery();
								Refreshment tempProduct = new Refreshment((Refreshment) tempProducts.getObject(i));
								if(rs4.next()) {
									tempProduct.setAmount(rs4.getInt("pi.product_amount"));
								}
								tempProduct.isOverStartDate(date);
								totalProducts.add(tempProduct);
								rs4.close();
							} else if (tempProducts.getObject(i) instanceof ParkingPass) {
								query4 = "SELECT pi.product_amount FROM (ProductInvoice AS pi JOIN Product AS p ON pi.product_id = p.product_id) WHERE p.product_code = ?";
								ps = conn.prepareStatement(query4);
								ps.setString(1, productCode);
								ResultSet rs4 = ps.executeQuery();
								String query5 = "SELECT pi.ticket_amount FROM (Product AS p JOIN ProductInvoice as pi ON p.product_id = pi.product_id) WHERE p.product_id = ?";
								ps = conn.prepareStatement(query5);
								ps.setString(1, productID);
								ResultSet rs5 = ps.executeQuery();
								ParkingPass tempProduct = new ParkingPass((ParkingPass) tempProducts.getObject(i));
								if(rs4.next()) {
									tempProduct.setAmount(rs4.getInt("pi.product_amount"));
								}
								if(rs5.next()) {
									tempProduct.setTicketAmount(rs5.getInt("pi.ticket_amount"));
								}

								totalProducts.add(tempProduct);
								rs4.close();
								rs5.close();

							}
							break;
						}

					}
				}
				invoice.setProducts(totalProducts);
				invoiceList.addToEnd(invoice);
			}
			rs.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return invoiceList;
	}

}
