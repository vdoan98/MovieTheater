package com.ceg.ext;

import java.sql.*;

import com.ceg.ext.DatabaseInfo;
import main.Person;
import main.Address;

/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Donot change any method signatures or the package name.
 * 
 */

public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Need to add query to drop foreign keys from other tables.
		String query = "DELETE FROM Person";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();

	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city,
			String state, String zip, String country) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		Address a = new Address(street, city, state, zip, country);
		Person p = new Person(personCode, firstName, lastName, a);
		String personQuery = "INSERT INTO Person(personCode,firstName,lastName)" + "VALUES(?,?,?)"; // Insert into
																									// Address table
		String addressQuery = "INSERT INTO Address(street,city,state,zip,country)" + "VALUES(?,?,?,?,?)"; // Insert into
																											// Person
																											// table

		PreparedStatement ps = conn.prepareStatement(personQuery);
		ps.setString(1, personCode);
		ps.setString(2, firstName);
		ps.setString(3, lastName);
		ResultSet rs = ps.executeQuery();
		ps = conn.prepareStatement(addressQuery);
		ps.setString(1, street);
		ps.setString(2, city);
		ps.setString(3, state);
		ps.setString(4, country);
		rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 * @throws SQLException
	 */
	public static void addEmail(String personCode, String email) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		String query = "INSERT INTO Emails(person_id, person_code, email_address) "
				+ "VALUES (SELECT person_id FROM Person WHERE person_code = ?) ";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, personCode);
		ps.setString(2, email);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();

	}

	/**
	 * 4. Method that removes every customer record from the database
	 * 
	 * @throws SQLException
	 */
	public static void removeAllCustomers() throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Need to add query to drop foreign keys from other tables
		try {
			String query = "DELETE FROM Customers";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException s) {
			System.out.println("Cannot delete all rows in table.");
			s.printStackTrace();
		}
	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,
			String name, String street, String city, String state, String zip, String country) {
	}

	/**
	 * 5. Removes all product records from the database
	 * 
	 * @throws SQLException
	 */
	public static void removeAllProducts() throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create Query to drop foreign keys from other tables
		try {
			String query = "DELETE FROM Product";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException s) {
			System.out.println("Cannot delete all rows in table.");
			s.printStackTrace();
		}
	}

	/**
	 * 6. Adds an movieTicket record to the database with the provided data.
	 * 
	 * @throws SQLException
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,
			String state, String zip, String country, String screenNo, double pricePerUnit) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String ticketQuery = "INSERT INTO MovieTicket(product_id,movie_time,address_id,screen_no, product_code,movie_name,price)"
				+ "VALUES (?,?,?,?)";
		String addressQuery = "INSERT INTO Address(street,city,state,zip,country)" + "VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(ticketQuery);
		ps.setInt(1, 16);
		ps.setString(2, dateTime);
		ps.setInt(3, 9);
		ps.setString(4, screenNo);
		ps.setString(5, productCode);
		ps.setString(6, movieName);
		ps.setDouble(7, pricePerUnit);
		ps.execute();

		ps = conn.prepareStatement(addressQuery);
		ps.setString(1, street);
		ps.setString(2, city);
		ps.setString(3, zip);
		ps.setString(4, country);
		ps.execute();
		conn.close();

	}

	/**
	 * 7. Adds a seasonPass record to the database with the provided data.
	 * 
	 * @throws SQLException
	 */
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,
			double cost) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "INSERT INTO SeasonPass(product_id,start_date,end_date, product_code,product_name, price)"
				+ "VALUES(?,?,?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, 1);
		ps.setString(2, seasonStartDate);
		ps.setString(3, seasonEndDate);
		ps.setString(4, productCode);
		ps.setString(5, name);
		ps.setDouble(6, cost);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();

	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 * 
	 * @throws SQLException
	 */
	public static void addParkingPass(String productCode, double parkingFee) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "INSERT INTO ParkingPass(product_id, product_code,price)" + "VALUES(?,?,?)";

		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, 1);
		ps.setString(2, productCode);
		ps.setDouble(3, parkingFee);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 9. Adds a refreshment record to the database with the provided data.
	 * 
	 * @throws SQLException
	 */
	public static void addRefreshment(String productCode, String name, double cost) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "INSERT INTO Refreshment(product_id,product_code,product_name,price)" + "VALUES(?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, 1);
		ps.setString(2, productCode);
		ps.setString(3, name);
		ps.setDouble(4, cost);
		ps.executeUpdate();

		conn.close();
	}

	/**
	 * 10. Removes all invoice records from the database
	 * 
	 * @throws SQLException
	 */
	public static void removeAllInvoices() throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Add query to drop foreign keys from other tables
		try {
			String query = "DELETE FROM Invoice";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();

		} catch (SQLException s) {
			System.out.println("Cannot delete all rows in table.");
			s.printStackTrace();
		}
	}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 * 
	 * @throws SQLException
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate)
			throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "INSERT INTO Invoice(invoice_code,person_code,sale_id,sale_date)" + "VALUES(?,?,?,?)";

		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, invoiceCode);
		ps.setString(2, customerCode);
		ps.setString(3, salesPersonCode);
		ps.setString(4, invoiceDate);
		ps.executeUpdate();

		conn.close();
	}

	/**
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of units
	 * 
	 * @throws SQLException
	 */

	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity)
			throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "INSERT INTO MovieTicket(TODO)" + "VALUES(?,?,?,?) WHERE ";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/*
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity)
			throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of quantity. NOTE: ticketCode may be null
	 * 
	 * @throws SQLException
	 */
	public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode)
			throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 15. Adds a particular refreshment (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with the
	 * given number of quantity.
	 * 
	 * @throws SQLException
	 */
	public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity)
			throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		// TODO: Create query
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

}
