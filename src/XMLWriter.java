import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import org.joda.time.DateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
//import com.datacontainers.Person;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class XMLWriter {

	public void xmlConverter(List<Person> persons, List<Customer> customers, List<Product> products) {
		XStream  xstream = new XStream();
		
		String dateFormat = "yyyy-MM-dd";
		String timeFormat = "HH:mm";
		String[] format = {timeFormat};
		xstream.registerConverter(new DateConverter(dateFormat, format));
		
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
		}
		try {
			xmlPrintWriter = new PrintWriter(xmlCustomersOutput);
			xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Customer aCustomer : customers) {
			// Use toJson method to convert Customer object into a String
			String customerOutput = xstream.toXML(aCustomer);
			xmlPrintWriter.write(customerOutput + "\n");
		}
		try {
			xmlPrintWriter = new PrintWriter(xmlProductsOutput);
			xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Product aProduct : products) {
			String productOutput = xstream.toXML(aProduct);
			//Problem: Gson has default for DateTime object representation. 
			xmlPrintWriter.write(productOutput + "\n"); 
		}
		
		xmlPrintWriter.close();	
	}
	
}
