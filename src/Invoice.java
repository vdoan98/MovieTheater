import java.util.ArrayList;

import org.joda.time.DateTime;

public class Invoice {
	private String code; 
	private DateTime date;
	private Customer customer;
	private Person salePerson;
	ArrayList<Product> products;
	private double discount;
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
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
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
			total += this.products.get(i).totalBeforeTax();
		}
		return total;
	}
	
	public double getFee(){
		double fee = 0;
		if(this.getCustomer() instanceof Student){
			fee = 6.75;
		}else if(this.getCustomer() instanceof General){
			fee = 0;
		}
		return fee;
	}
	
	public double getTotal(){
		double total = 0;
		for (int i = 0; i < this.products.size(); i++){
			total += this.products.get(i).computeTotal();
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
			if(this.getCustomer().getType().charAt(0) == 'S'){
				discount += this.products.get(i).getDiscount(); 
				discount += this.products.get(i).studentDiscount();
			}else if (this.getCustomer().getType().charAt(0) == 'G'){
				discount += 0;
			}
		}
		return -1 * discount;
	}
	
	public double getStudentDiscount(){
		double discount = 0;
		for (int i = 0; i < this.products.size(); i++){
			discount += this.products.get(i).studentDiscount();
		}
		return -1 * discount;
		
	}
	
	
	
	
}
