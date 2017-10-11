public class Refreshment extends Product {
	private String name;
	private double price;


	/**
	 * @param productCode
	 * @param type
	 * @param name
	 * @param price
	 * @param amount
	 */
	public Refreshment(String productCode, char type, String name, double price, int amount) {
		super(productCode, type, amount);
		this.name = name;
		this.price = price;
	}

	/**
	 * @param productCode
	 * @param type
	 * @param name
	 * @param price
	 */
	public Refreshment(String productCode, char type, String name, double price) {
		super(productCode, type);
		this.name = name;
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * @param price
	 *            the price to set
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
	public double computeTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
