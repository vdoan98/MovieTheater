package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

//import com.datacontainers.Person;
import com.thoughtworks.xstream.XStream;

public class XMLWriter {

	public void xmlConverter(List<Person> persons, List<Customer> customers, List<Product> products) {
		XStream  xstream = new XStream();
		
        File xmlPersonsOutput = new File("data/Persons.xml");
        File xmlCustomersOutput = new File("data/Customers.xml");
		File xmlProductsOutput = new File("data/Products.xml");
		
		PrintWriter xmlPrintWriter = null;
		
		xstream.alias("person", Person.class); 
		xstream.alias("customer", Customer.class); 
		xstream.alias("product", Product.class); 
		
		try {
			xmlPrintWriter = new PrintWriter(xmlPersonsOutput);
			xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(Person aPerson : persons) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aPerson);
			xmlPrintWriter.write(personOutput);
			xmlPrintWriter.flush();
		}
		try {
			xmlPrintWriter = new PrintWriter(xmlCustomersOutput);
			xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Customer aCustomer : customers) {
			// Use toXML method to convert Customer object into a String
			String customerOutput = xstream.toXML(aCustomer);
			xmlPrintWriter.write(customerOutput + "\n");
			xmlPrintWriter.flush();
		}
		try {
			xmlPrintWriter = new PrintWriter(xmlProductsOutput);
			xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Product aProduct : products) {
			// Use toXML method to convert Product object into a String
			String productOutput = xstream.toXML(aProduct);
			xmlPrintWriter.write(productOutput + "\n"); 
			xmlPrintWriter.flush();
		}
		
		xmlPrintWriter.close();	
	}
	
}
