import org.joda.time.DateTime;

public class Invoice {
	private String code; 
	private DateTime date;
	private Customer customer;
	private Person salePerson;
	/**
	 * @param code
	 * @param date
	 * @param customer
	 * @param salePerson
	 */
	public Invoice(String code, DateTime date, Customer customer, Person salePerson) {
		super();
		this.code = code;
		this.date = date;
		this.customer = customer;
		this.salePerson = salePerson;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the salePerson
	 */
	public Person getSalePerson() {
		return salePerson;
	}
	/**
	 * @param salePerson the salePerson to set
	 */
	public void setSalePerson(Person salePerson) {
		this.salePerson = salePerson;
	} 
	
	
	
}
