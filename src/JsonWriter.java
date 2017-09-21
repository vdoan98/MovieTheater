import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.lang.Object;
import java.lang.reflect.Type;

//import com.datacontainers.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

//import DateTime
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class JsonWriter {

	public void jsonConverter(List<Person> persons, List<Customer> customers, List<Product> products) {
		
		Gson gson = new GsonBuilder().registerTypeAdapter(DateTime.class, serializer).setPrettyPrinting().create();
		
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
			jsonPrintWriter.flush();
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
			jsonPrintWriter.flush();
		}
		try {
			jsonPrintWriter = new PrintWriter(jsonProductsOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Product aProduct : products) {
			String productOutput = gson.toJson(aProduct);
			//Problem: Gson has default for DateTime object representation. 
			jsonPrintWriter.write(productOutput + "\n");
			jsonPrintWriter.flush();
		}
		jsonPrintWriter.close();
	}
	
	//Function to override Gson default DateTime representation
	private static JsonSerializer<DateTime> serializer = new JsonSerializer<DateTime>(){
		public JsonElement serialize(DateTime dateTime, Type typeOfSrc, JsonSerializationContext context){
			String dtString = dateTime.toString("yyyy-MM-dd HH:mm");
			return new JsonPrimitive(dtString);
		}
	};
	
}


