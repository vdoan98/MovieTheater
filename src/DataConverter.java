import java.lang.reflect.Type;
import java.util.List;

import org.joda.time.DateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

//import com.fileWriter.XMLWriter;

public class DataConverter {

	public static void main(String[] args) {

		// Create a FlatFileReader object
		FlatFileReader fr = new FlatFileReader();

		/*
		 * fr Reads data from the flat file; Creates Person & Customer objects; and
		 * Stores Person & Customer objects in a Person ArrayList & Customer ArrayList
		 */
		List<Person> personList = fr.readPersons();
		List<Customer> customerList = fr.readCustomer();
		List<Product> productList = fr.readProducts();
		List<Invoice> invoiceList = fr.readInvoices();
		// Write Person ArrayList and Customer ArrayList into a Json file
		JsonWriter jWriter = new JsonWriter();
		jWriter.jsonConverter(personList, customerList, productList);
		// // Write Person ArrayList into an XML file
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.xmlConverter(personList, customerList, productList);
	}
	
}
