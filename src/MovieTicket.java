
public class MovieTicket extends Product {
	private String movieName;
	private Address address; //TODO:Address class
	private int screenNo;
	private double pricePerUnit;
	/**
	 * @param productCode
	 * @param type
	 * @param movieName
	 * @param address
	 * @param screenNo
	 * @param pricePerUnit
	 */
	public MovieTicket(String productCode, char type, String movieName, Address address, int screenNo,
			double pricePerUnit) {
		super(productCode, type);
		this.movieName = movieName;
		this.address = address;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
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
	public int getScreenNo() {
		return screenNo;
	}
	/**
	 * @param screenNo the screenNo to set
	 */
	public void setScreenNo(int screenNo) {
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
