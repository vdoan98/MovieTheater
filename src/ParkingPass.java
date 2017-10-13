public class ParkingPass extends Product {
	private double parkingFee;
	private String ticket;
	private int ticketAmount = 0;
	private double price = 25.0;

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}



	/**
	 * @param ticket the ticket to set
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
		super(productCode, type);
		this.parkingFee = parkingFee;
	}



	/**
	 * @param productCode
	 * @param type
	 * @param parkingFee
	 * @param amount
	 */
	public ParkingPass(String productCode, char type, double parkingFee, int amount) {
		super(productCode, type, amount);
		this.parkingFee = parkingFee;
	}


	/**
	 * @return the parkingFee
	 */
	public double getParkingFee() {
		return this.parkingFee;
	}

	/**
	 * @param parkingFee the parkingFee to set
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
	 * @param price the price to set
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
	 * @param ticketAmount the ticketAmount to set
	 */
	public void setTicketAmount(int ticketAmount) {
		this.ticketAmount = ticketAmount;
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
		double priceTax = this.getPrice() + this.getPrice() * this.getTax() ;
		if (this.getAmount() <= this.getTicketAmount()){
			total = (priceTax * this.getAmount()) - (this.getAmount() * priceTax);
		}else{
			total = (priceTax * this.getAmount()) - (this.ticketAmount * priceTax);
		}

		return total;
	}



	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		double total = 0;
		if (this.getAmount() <= this.getTicketAmount()){
			total = (this.getPrice() * this.getAmount()) - (this.getAmount() * this.getPrice());
		}else{
			total = (this.getPrice() * this.getAmount()) - (this.ticketAmount * this.getPrice());
		}


		return total;
	}



	@Override
	public double computeTax() {
		// TODO Auto-generated method stub
		double total = 0;
		if (this.getAmount() <= this.getTicketAmount()){
			total = (this.getPrice() * this.getTax() * this.getAmount()) - (this.getAmount() * this.getTax() * this.getPrice());
		}else{
			total = (this.getPrice() * this.getTax() * this.getAmount()) - (this.ticketAmount * this.getTax() * this.getPrice());
		}

		return total;
	}



	@Override
	public double studentDiscount() {
		// TODO Auto-generated method stub
		double total = 0;
		if(this.getAmount() <= this.getTicketAmount()){
			total = (this.getPrice() - this.getPrice() * 0.08) * this.getAmount();
		}else{
			total = (this.getPrice() - this.getPrice() * 0.08) * this.ticketAmount;
		}
		return total;
	}

}
