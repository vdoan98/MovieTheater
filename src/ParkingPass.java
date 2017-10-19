import org.joda.time.DateTime;

public class ParkingPass implements Product {
	
	private String productCode;
	private char type;
	private int amount;
	private double parkingFee;
	private String ticket = "";
	private int ticketAmount;
	private double price;


	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * @param productCode
	 * @param type
	 * @param parkingFee
	 */
	public ParkingPass(String productCode, char type, double parkingFee) {
		this.productCode = productCode;
		this.type = type;
		this.parkingFee = parkingFee;
	}

	/**
	 * @param productCode
	 * @param type
	 * @param parkingFee
	 * @param amount
	 */
	public ParkingPass(String productCode, char type, double parkingFee, int amount) {
		this.productCode = productCode;
		this.type = type;
		this.parkingFee = parkingFee;
		this.amount = amount;
	}
	
	public ParkingPass(ParkingPass oldProduct) {
		this.productCode = oldProduct.productCode;
		this.type = oldProduct.type;
		this.parkingFee = oldProduct.parkingFee;
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
	 * @return the parkingFee
	 */
	public double getParkingFee() {
		return this.parkingFee;
	}

	/**
	 * @param parkingFee
	 *            the parkingFee to set
	 */
	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the ticketAmount
	 */
	public int getTicketAmount() {
		return ticketAmount;
	}

	/**
	 * @param ticketAmount
	 *            the ticketAmount to set
	 */
	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	
	public int getFreeAmount(){
		int amount = 0;
		if (this.getAmount() < this.getTicketAmount()){
			amount = this.getAmount();
		}else{
			amount = this.getTicketAmount();
		}
		return amount;
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
		double total = 0;

		if (this.getAmount() <= this.getTicketAmount()) {
			total = 0;
		} else {
			total = (this.getParkingFee() * this.getAmount()) - (this.getTicketAmount() * this.getParkingFee());
		}

		return total;
	}

	@Override
	public double computeTax() {
		// TODO Auto-generated method stub
		double total = 0;
		if (this.getAmount() <= this.getTicketAmount()) {
			total = 0;
		} else {
			total = (this.getParkingFee() * this.getTax() * this.getAmount())
					- (this.getTicketAmount() * this.getTax() * this.getParkingFee());
		}

		return total;
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

	@Override
	public boolean isOverStartDate(DateTime date) {
		// TODO Auto-generated method stub
		return false;
	}

}
