package main;
import org.joda.time.DateTime;

public interface Product {

	String getProductCode();

	void setProductCode(String productCode);

	char getType();
	
	void setType(char type);
	
	int getAmount();

	void setAmount(int amount);
	
	boolean isOverStartDate(DateTime date);
	

	//Get tax rate for each product. Different products have different tax rate
	public abstract double getTax();
	//Compute total price before tax
	public abstract double totalBeforeTax();
	//Compute tax amount
	public abstract double computeTax();
	//Add tax and total price before tax to get total price 
	public abstract double computeTotal();
	//Compute discount for student. 
	public abstract double studentDiscount();
	
	public abstract double getDiscount();
	
	
	

}
