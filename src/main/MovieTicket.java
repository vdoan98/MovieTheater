package main;
import java.sql.Date;

import org.joda.time.DateTime;

public class MovieTicket implements Product {
	
	private String productCode;
	private char type;
	private int amount;
	private String movieName;
	private DateTime time;
	private Address address; //TODO:Address class
	private String screenNo;
	private double pricePerUnit;
	private int ticketAmount;
	/** 
	 * @param productCode
	 * @param type
	 * @param movieName
	 * @param address
	 * @param screenNo
	 * @param pricePerUnit
	 */
	public MovieTicket(String productCode, char type, DateTime time, String movieName, Address address, String screenNo,
			double pricePerUnit) {
		this.productCode = productCode;
		this.type = type;
		this.time = time;
		this.movieName = movieName;
		this.address = address;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
	}


	/**
	 * @param productCode
	 * @param type
	 * @param movieName
	 * @param time
	 * @param address
	 * @param screenNo
	 * @param pricePerUnit
	 * @param amount
	 */
	public MovieTicket(String productCode, char type, String movieName, DateTime time, Address address, String screenNo,
			double pricePerUnit, int amount) {
		this.productCode = productCode;
		this.type = type;
		this.movieName = movieName;
		this.movieName = movieName;
		this.time = time;
		this.address = address;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
		this.ticketAmount = amount;
	}
	
	public MovieTicket(MovieTicket oldProduct){
		this.productCode = oldProduct.productCode;
		this.type = oldProduct.type;
		this.movieName = oldProduct.movieName;
		this.movieName = oldProduct.movieName;
		this.time = new DateTime(oldProduct.time);
		this.address = new Address(oldProduct.address);
		this.screenNo = oldProduct.screenNo;
		this.pricePerUnit = oldProduct.pricePerUnit;
		this.ticketAmount = oldProduct.amount;
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
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


	/**
	 * @return the ticketAmount
	 */
	public int getTicketAmount() {
		return ticketAmount;
	}


	/**
	 * @param ticketAmount the ticketAmount to set
	 */
	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}


	/**
	 * @return the time
	 */
	public DateTime getTime() {
		return this.time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(DateTime time) {
		this.time = time;
	}
	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return this.movieName;
	}
	/**
	 * @param movieName the movieName to set
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return this.address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return the screenNo
	 */
	public String getScreenNo() {
		return screenNo;
	}
	/**
	 * @param screenNo the screenNo to set
	 */
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	/**
	 * @return the pricePerUnit
	 */
	public double getPricePerUnit() {
		double price = 0;
		if (time.getDayOfWeek() == 2 || time.getDayOfWeek() == 4){
			price = this.pricePerUnit * (1-0.07);
		}else {
			price = this.pricePerUnit;
		}
		return price;
	}
	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0.06;
	}

	@Override
	public double computeTotal() {
		// TODO Auto-generated method stub
		return this.totalBeforeTax() + this.computeTax();
	}



	@Override
	public double totalBeforeTax() {
		// TODO Auto-generated method stub
		return this.getPricePerUnit() * this.getAmount();
	}


	@Override
	public double computeTax() {
		// TODO Auto-generated method stub
		return this.getPricePerUnit() * this.getTax() * this.getAmount();
	}


	@Override
	public double studentDiscount() {
		// TODO Auto-generated method stub
		//Students don't pay tax and get 8% discounts
		return this.totalBeforeTax() * 0.08 + this.computeTax();
	}


	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		//Discount for Tuesday and Thursday 
		return this.getPricePerUnit() * 0.07 * this.getAmount();
	}


	@Override
	public boolean isOverStartDate(DateTime date) {
		// TODO Auto-generated method stub
		return false;
	}




}
