public class ParkingPass extends Product {
	private double parkingFee;
	private String ticket;
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



	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0.04;
	}

	@Override
	public double computeTotal(char type) {
		// TODO Auto-generated method stub
		double total = 0;
		if (type == 'S'){
			
		}else if (type == 'G'){
			
		}
		return total;
	}



	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public double computeTax() {
		// TODO Auto-generated method stub
		return 0;
	}

}
