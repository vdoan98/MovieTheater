import java.sql.Date;

import org.joda.time.DateTime;

public class MovieTicket extends Product {
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
		super(productCode, type);
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
		super(productCode, type, amount);
		this.movieName = movieName;
		this.time = time;
		this.address = address;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
		this.ticketAmount = amount;
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
		return this.pricePerUnit;
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
		return 0.04;
	}

	@Override
	public double computeTotal() {
		// TODO Auto-generated method stub
		double total = 0;
//		if (time.getDayOfWeek() == 2 || time.getDayOfWeek() == 4){
//			total = (this.getPricePerUnit() + this.getPricePerUnit() * this.getTax() - this.getPricePerUnit() * 0.07) * this.getAmount();
//		}else {
//			total = (this.getPricePerUnit() + this.getPricePerUnit() * this.getTax()) * this.getAmount();
//		}
		total = this.getTotal() + this.computeTax();

		return total;
	}



	@Override
	public double getTotal() {
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
		return (this.getPricePerUnit() - this.getPricePerUnit() * 0.08) * this.getAmount();
	}




}
