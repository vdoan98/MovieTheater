import java.util.List;

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
		// Write Person ArrayList and Customer ArrayList into a Json file
		JsonWriter jWriter = new JsonWriter();
		jWriter.jsonConverter(personList, customerList);
		// // Write Person ArrayList into an XML file
		// XMLWriter xmlWriter = new XMLWriter();
		// xmlWriter.xmlConverter(personList);
	}
}
