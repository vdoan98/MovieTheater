
public class Person {

	private String personCode;
	private String firstName;
	private String lastName;
	private Address address;
	
	

	/**
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param address
	 */
	public Person(String personCode, String firstName, String lastName, Address address) {
		super();
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	/**
	 * @return the personCode
	 */
	public String getPersonCode() {
		return personCode;
	}

	/**
	 * @param personCode the personCode to set
	 */
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
