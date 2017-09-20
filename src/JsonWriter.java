import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.lang.Object;

//import com.datacontainers.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {

	public void jsonConverter(List<Person> persons, List<Customer> customers, List<Product> products) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonPersonsOutput = new File("data/Persons.json");
		File jsonCustomersOutput = new File("data/Customers.json");
		File jsonProductsOutput = new File("data/Products.json");
		PrintWriter jsonPrintWriter = null;

		try {
			jsonPrintWriter = new PrintWriter(jsonPersonsOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Person aPerson : persons) {
			// Use toJson method to convert Person object into a String
			String personOutput = gson.toJson(aPerson);
			jsonPrintWriter.write(personOutput + "\n");
		}
		try {
			jsonPrintWriter = new PrintWriter(jsonCustomersOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Customer aCustomer : customers) {
			// Use toJson method to convert Customer object into a String
			String customerOutput = gson.toJson(aCustomer);
			jsonPrintWriter.write(customerOutput + "\n");
		}
		try {
			jsonPrintWriter = new PrintWriter(jsonProductsOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Product aProduct : products) {
			String productOutput = gson.toJson(aProduct);
			jsonPrintWriter.write(productOutput + "\n"); 
		}
		jsonPrintWriter.close();
	}
}
