public class Refreshment extends Product {
	private String name;
	private double price;

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
		return name;
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
		return price;
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
		return 0;
	}

	@Override
	public double computeTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
