package main;

public class Student extends Customer{

	public Student(String customerCode, char type, Person contact, String name, Address address) {
		super(customerCode, type, contact, name, address);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return 0.08;
	}

	@Override
	public double getAdditionalFee() {
		// TODO Auto-generated method stub
		return 6.75;
	}

}
