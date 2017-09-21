import org.joda.time.DateTime;

public class MovieTicket extends Product {
	private String movieName;
	private DateTime time;
	private Address address; //TODO:Address class
	private String screenNo;
	private double pricePerUnit;
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
	 * @return the time
	 */
	public DateTime getTime() {
		return time;
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
		return movieName;
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
		return address;
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
		return pricePerUnit;
	}
	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	
	

}
