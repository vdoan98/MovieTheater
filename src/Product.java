public abstract class Product {
	private String productCode;
	private char type;
	private int amount;
	/**
	 * @param productCode
	 * @param type
	 */
	public Product(String productCode, char type) {
		super();
		this.productCode = productCode;
		this.type = type;
	}
	
	
	/**
	 * @param productCode
	 * @param type
	 * @param amount
	 */
	public Product(String productCode, char type, int amount) {
		super();
		this.productCode = productCode;
		this.type = type;
		this.amount = amount;
	}


	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return this.productCode;
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
		return this.type;
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


	public abstract double getTax(char type);
	public abstract double computeTotal();
	
	

}
