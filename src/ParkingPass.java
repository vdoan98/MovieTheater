public class ParkingPass extends Product {
	private double parkingFee;
	private String ticket;

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

	@Override
	public double getTax(char type) {
		// TODO Auto-generated method stub
		double tax = 0;
		if (type == 'S'){
			tax = 0.0;
		}else if (type == 'G'){
			tax = 0.04;
		}
		return tax;
	}

	@Override
	public double computeTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
