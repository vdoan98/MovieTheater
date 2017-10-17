import org.joda.time.*;


public class SeasonPass implements Product {
	private String productCode;
	private char type;
	private int amount;
	private String name;
	private DateTime startDate;
	private DateTime endDate;
	private double cost;
	private boolean isOver;
	private int dayLeft;


	/**
	 * @param productCodes
	 * @param type
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param cost
	 */
	public SeasonPass(String productCode, char type, String name, DateTime startDate, DateTime endDate, double cost) {
		this.productCode = productCode;
		this.type = type;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
	}



	/**
	 * @param productCode
	 * @param type
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param cost
	 * @param amount
	 */
	public SeasonPass(String productCode, char type, String name, DateTime startDate, DateTime endDate, double cost,
			int amount) {
		this.productCode = productCode;
		this.type = type;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.amount = amount;
	}
	
	public SeasonPass(SeasonPass oldProduct) {
		this.productCode = oldProduct.productCode;
		this.type = oldProduct.type;
		this.name = oldProduct.name;
		this.startDate = new DateTime (oldProduct.startDate);
		this.endDate = new DateTime (oldProduct.endDate);
		this.cost = oldProduct.cost;
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
		return amount;
	}



	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
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
		return this.cost;
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
		return this.startDate;
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
		return this.endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public boolean isOverStartDate(DateTime date){
		if (date.getDayOfMonth() > this.startDate.getDayOfMonth()){
			this.isOver = true;
			this.dayLeft = this.endDate.getDayOfMonth() - date.getDayOfMonth();
		}else{
			this.isOver = false;
		}
		return this.isOver;
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
		double totalDate = 0;
		double total = 0;
		if (this.isOver){
			totalDate = this.getEndDate().getDayOfMonth() - this.getStartDate().getDayOfMonth();
			total = (this.getCost() / totalDate * this.dayLeft) * this.getAmount()  + (8 * this.getAmount()) ;
		}else {
			total = this.getCost()  * this.getAmount()  + (8 * this.getAmount()) ;
		}
		return total ;
	}



	@Override
	public double computeTax() {
		// TODO Auto-generated method stub
		return (this.totalBeforeTax() * this.getTax()) ;
	}



	@Override
	public double studentDiscount() {
		// TODO Auto-generated method stub
		return this.totalBeforeTax() * 0.08 + this.computeTax();
	}



	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return 0;
	}


}
