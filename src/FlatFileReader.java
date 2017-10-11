import java.io.*;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class FlatFileReader {

	ArrayList<Person> personList = new ArrayList<Person>();
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	ArrayList<Product> productList = new ArrayList<Product>();
	ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
	
	

	public ArrayList<Person> readPersons() {

		Scanner sc = null;
		// This Person ArrayList stores the Person objects
		String data[];
		String email;

		try {
			sc = new Scanner(new File("data/Persons.dat"));
			sc.nextLine(); // reads the number of records from the first line

			while (sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				line.trim();
				data = line.split(";"); // splits the line and stores in a string array

				data[1].trim();
				String nameArray[] = data[1].split(",");

				// Creates an Address object
				data[2].trim();
				String addressArray[] = data[2].split(",");
				Address address = new Address(addressArray[0], addressArray[1], addressArray[2], addressArray[3],
						addressArray[4]);

				Person person;

				// Check if there's email information. If not, create person object without
				// email.
				if (data.length == 4) {
					email = data[3];
					person = new Person(data[0], nameArray[0], nameArray[1], address, email);
				} else {
					person = new Person(data[0], nameArray[0], nameArray[1], address);
				}
				// Adds the Person object into Person ArrayList
				personList.add(person);
			}

			sc.close();
			return personList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception le) {
			le.printStackTrace();
			return null;
		}
	}

	public ArrayList<Customer> readCustomer() {
		Scanner sc = null;
		// This Customer ArrayList stores the Customer objects
		String data[];
		try {
			sc = new Scanner(new File("data/Customers.dat"));
			sc.nextLine(); // reads the number of records from the first line

			while (sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				line.trim();
				data = line.split(";"); // splits the line and stores in a string array

				int code = 0;

				for (int i = 0; i < personList.size(); i++) {
					if (data[2].compareTo(personList.get(i).getPersonCode()) == 0) {
						code = i;
					}
				}

				if (data[1].charAt(0) == 'S') {
					Student customer = new Student(data[0], data[1].charAt(0), personList.get(code), data[3],
							personList.get(code).getAddress());
					// Adds the Person object into Person ArrayList
					customerList.add(customer);
				} else if (data[1].charAt(0) == 'G') {
					General customer = new General(data[0], data[1].charAt(0), personList.get(code), data[3],
							personList.get(code).getAddress());
					// Adds the Person object into Person ArrayList
					customerList.add(customer);
				}

			}

			sc.close();
			return customerList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Product> readProducts() {
		Scanner sc = null;
		String data[];
		try {
			sc = new Scanner(new File("data/Products.dat"));
			sc.nextLine();

			while (sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				line.trim();
				data = line.split(";"); // splits the line and stores in a string array

				SeasonPass season;
				MovieTicket ticket;
				ParkingPass parking;
				Refreshment refresh;
				String addressArray[];
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				DateTimeFormatter formatTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

				switch (data[1].charAt(0)) {
				case 'S':
					season = new SeasonPass(data[0], data[1].charAt(0), data[2], formatter.parseDateTime(data[3]),
							formatter.parseDateTime(data[4]), Double.parseDouble(data[5]));
					productList.add(season);
					break;
				case 'M':
					data[4].trim();
					addressArray = data[4].split(",");
					Address address = new Address(addressArray[0], addressArray[1], addressArray[2], addressArray[3],
							addressArray[4]);
					ticket = new MovieTicket(data[0], data[1].charAt(0), formatTime.parseDateTime(data[2]), data[3],
							address, data[5], Double.parseDouble(data[6]));
					productList.add(ticket);
					break;
				case 'P':
					parking = new ParkingPass(data[0], data[1].charAt(0), Double.parseDouble(data[2]));
					productList.add(parking);
					break;
				case 'R':
					refresh = new Refreshment(data[0], data[1].charAt(0), data[2], Double.parseDouble(data[3]));
					productList.add(refresh);
					break;

				}

			}

			sc.close();
			return productList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception le) {
			le.printStackTrace();
			return null;
		}

	}
	
	public ArrayList<Invoice> readInvoices() {

		Scanner sc = null;
		// This Person ArrayList stores the Person objects
		String data[];
		String product;
		
		//Invoke readPersons() and readCustomer() before invoice run 
		//Prevent personList and customerList size from being 0
		this.readPersons();
		this.readCustomer();

		try {
			sc = new Scanner(new File("data/Invoices.dat"));
			sc.nextLine(); // reads the number of records from the first line

			while (sc.hasNext()) {
				String line = sc.nextLine(); // reads each line starting from 2nd line
				line.trim();
				data = line.split(";"); // splits the line and stores in a string array
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

				String code = data[0];
				DateTime date = formatter.parseDateTime(data[3]);
				String productArray[] = data[4].split(",");
				ArrayList<Product> totalProducts = new ArrayList<Product>();

				

				for (int i = 0; i < productArray.length; i++) {
					String tempArray[] = productArray[i].split(":");
					for (int j = 0; j < this.readProducts().size(); j++) {
						if (tempArray[0].compareToIgnoreCase(this.readProducts().get(j).getProductCode()) == 0) {	
							totalProducts.add(this.readProducts().get(j));
							break;
						}
					}
				}

				System.out.println(totalProducts);
				

				Invoice invoice = new Invoice (code, date, totalProducts);
				
				System.out.println(customerList.size());
		
				
				//checkCustomer 
				for (int i = 0; i < this.readCustomer().size(); i++) {
					if(((this.readCustomer().get(i).getCustomerCode()).compareToIgnoreCase(data[1])) == 0) {
						invoice.setCustomer(this.readCustomer().get(i));
						break;
					}
				}
				

				//check the sale person
				for (int i = 0; i < personList.size(); i++) {
					if(((this.readPersons().get(i).getPersonCode()).compareToIgnoreCase(data[2])) == 0) {
						invoice.setSalePerson(this.readPersons().get(i));
						break;
					}
				}

				invoiceList.add(invoice);
			}

			sc.close();
			return invoiceList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception le) {
			le.printStackTrace();
			return null;
		}
	}

}
