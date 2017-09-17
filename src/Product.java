public class Product {
	private String productCode;
	private char type;
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
	
	
	
	

}
