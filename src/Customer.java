
public class Customer {
	
	/*
	 * This class is the constructor class for the Customer object.
	 * Children classes include StudentCustomer and GeneralCustomer
	 * 
	 */
	
	private String customerCode;
	private char type;
	private Person contact;
	private String name;
	private Address address;
	

	/**
	 * @param customerCode
	 * @param type
	 * @param contact
	 * @param name
	 * @param address
	 */
	public Customer(String customerCode, char type, Person contact, String name, Address address) {
		super();
		this.customerCode = customerCode;
		this.type = type;
		this.contact = contact;
		this.name = name;
		this.address = address;
	}



	/**
	 * @return the customerCode
	 */
	public String getCustomerCode() {
		return customerCode;
	}



	/**
	 * @param customerCode the customerCode to set
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}



	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}



	/**
	 * @return the contact
	 */
	public Person getContact() {
		return contact;
	}



	/**
	 * @param contact the contact to set
	 */
	public void setContact(Person contact) {
		this.contact = contact;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
