package main;
import org.joda.time.DateTime;

public class Refreshment implements Product {
	
	private String productCode;
	private char type;
	private int amount;
	private String name;
	private double price;
	private boolean hasTicket;


	/**
	 * @param productCode
	 * @param type
	 * @param name
	 * @param price
	 * @param amount
	 */
	public Refreshment(String productCode, char type, String name, double price, int amount) {
		this.productCode = productCode;
		this.type = type;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	/**
	 * @param productCode
	 * @param type
	 * @param name
	 * @param price
	 */
	public Refreshment(String productCode, char type, String name, double price) {
		this.productCode = productCode;
		this.type = type;
		this.name = name;
		this.price = price;
	}
	
	public Refreshment (Refreshment oldProduct) {
		// TODO Auto-generated method stub
		this.productCode = oldProduct.productCode;
		this.type = oldProduct.type;
		this.name = oldProduct.name;
		this.price = oldProduct.price;
		this.amount = oldProduct.amount;
	}
	

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
	 * @return the amount
	 */
	public int getAmount() {
		return this.amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the hasTicket
	 */
	public boolean isHasTicket() {
		return hasTicket;
	}

	/**
	 * @param hasTicket the hasTicket to set
	 */
	public void setHasTicket(boolean hasTicket) {
		this.hasTicket = hasTicket;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void hasTicket(boolean hasTicket){
		this.hasTicket = hasTicket;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0.04;
	}


	@Override
	public double computeTotal() {
		// TODO Auto-generated method stub
		return this.totalBeforeTax() + this.computeTax();
	}

	@Override
	public double totalBeforeTax() {
		// TODO Auto-generated method stub
		return this.getPrice() * this.getAmount() - this.getDiscount();
	}

	@Override
	public double computeTax() {
		// TODO Auto-generated method stub
		return this.totalBeforeTax() * this.getTax();
	}

	@Override
	public double studentDiscount() {
		// TODO Auto-generated method stub
		return this.totalBeforeTax() * 0.08 + this.computeTax();
	}

	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		
		//If refreshment is purchased with a ticket, 5% discount
		double discount = 0;
		if(this.isHasTicket()){
			discount = this.getPrice() * 0.05 * this.getAmount();
		}else if(!this.isHasTicket()) {
			discount = 0;
		}
		return discount;
	}

	@Override
	public boolean isOverStartDate(DateTime date) {
		// TODO Auto-generated method stub
		return false;
	}

	


}
