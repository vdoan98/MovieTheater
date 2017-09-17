import org.joda.time.*;


public class SeasonPass extends Product {
	private String name;
	private DateTime startDate;
	private DateTime endDate;
	private double cost;
	
	/**
	 * @param productCodes
	 * @param type
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param cost
	 */
	public SeasonPass(String productCode, char type, String name, DateTime startDate, DateTime endDate, double cost) {
		super(productCode, type);
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
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
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * @return the startDate
	 */
	public DateTime getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public DateTime getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
