import java.io.*;
import java.util.*;

public class FlatFileReader {

	public ArrayList<Person> readPersons() {

		Scanner sc = null;
		// This Person ArrayList stores the Person objects
		ArrayList<Person> personList = new ArrayList<Person>();
		String data[] ;
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
				
				//Check if there's email information. If not, create person object without email.
				if(data.length == 4){
					email = data[3];
					person = new Person(data[0], nameArray[0], nameArray[1], address, email);
				}else{
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
		} finally{
			
		}
		
		
	}
	
}