import java.util.ArrayList;

import org.joda.time.DateTime;

public class Invoice {
	private String code; 
	private DateTime date;
	private Customer customer;
	private Person salePerson;
	ArrayList<Product> products;
	/**
	 * @param code
	 * @param date
	 * @param customer
	 * @param salePerson
	 * @param products
	 */
	public Invoice(String code, Customer customer, Person salePerson, DateTime date, ArrayList<Product> products) {
		super();
		this.code = code;
		this.date = date;
		this.customer = customer;
		this.salePerson = salePerson;
		this.products = products;
	}
	
	
	/**
	 * @param code
	 * @param date
	 * @param products
	 */
	public Invoice(String code, DateTime date, ArrayList<Product> products) {
		super();
		this.code = code;
		this.date = date;
		this.products = products;
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


	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}


	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public double getSubTotal(){
		double total = 0;
		for (int i = 0; i < this.products.size(); i++){
			total += this.products.get(i).getTotal();
		}
		return total;
	}
	
	public double getFee(){
		return 0.0;
	}
	
	public double getTotal(){
		double total = 0;
		for (int i = 0; i < this.products.size(); i++){
			total += this.products.get(i).computeTotal(this.getCustomer().getType().charAt(0));
		}
		return total;
	}
	
	public double getTax(){
		double total = 0;
		for (int i = 0; i < this.products.size(); i++){
			total += this.products.get(i).computeTax();
		}
		return total;
	}
	
	public double getDiscount(){
		double discount = 0;
		for (int i = 0; i < this.products.size(); i++){
			discount += this.products.get(i).getTotal();
		}
		return -1 * (this.getTotal() - discount);
	}
	
	
	
	
}
