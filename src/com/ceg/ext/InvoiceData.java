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

		//TODO: Need to add query to drop foreign keys from other tables. 
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
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		Address a = new Address(street, city, state, zip, country);
		Person p = new Person(personCode, firstName, lastName, a);
		String query1 = "INSERT INTO "; //Insert into Address table
		String query2 = "INSERT INTO "; //Insert into Person table 

		PreparedStatement ps = conn.prepareStatement(query1);
		ResultSet rs = ps.executeQuery();
		ps = conn.prepareStatement(query2);
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
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();


	}

	/**
	 * 4. Method that removes every customer record from the database
	 * @throws SQLException 
	 */
	public static void removeAllCustomers() throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Need to add query to drop foreign keys from other tables
		String query = "DELETE FROM Customers";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();

	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {}

	/**
	 * 5. Removes all product records from the database
	 * @throws SQLException 
	 */
	public static void removeAllProducts() throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create Query to drop foreign keys from other tables
		String query = "DELETE FROM Products";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();

	}

	/**
	 * 6. Adds an movieTicket record to the database with the provided data.
	 * @throws SQLException 
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,String state, String zip, String country, String screenNo, double pricePerUnit) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();

	}

	/**
	 * 7. Adds a seasonPass record to the database with the provided data.
	 * @throws SQLException 
	 */
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,	double cost) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();

	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 * @throws SQLException 
	 */
	public static void addParkingPass(String productCode, double parkingFee) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 9. Adds a refreshment record to the database with the provided data.
	 * @throws SQLException 
	 */
	public static void addRefreshment(String productCode, String name, double cost) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 10. Removes all invoice records from the database
	 * @throws SQLException 
	 */
	public static void removeAllInvoices() throws SQLException {
		Connection conn = DatabaseInfo.getConnection();


		//TODO: Add query to drop foreign keys from other tables 
		String query = "DELETE FROM Invoices";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 * @throws SQLException 
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 * @throws SQLException 
	 */

	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity) throws SQLException {

		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/*
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 * NOTE: ticketCode may be null
	 * @throws SQLException 
	 */
	public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

	/**
	 * 15. Adds a particular refreshment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity. 
	 * @throws SQLException 
	 */
	public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) throws SQLException {
		Connection conn = DatabaseInfo.getConnection();

		//TODO: Create query 
		String query = "";

		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		rs.next();
		conn.close();
	}

}
