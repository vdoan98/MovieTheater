package main;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class InvoiceReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//FlatFileReader fileReader = new FlatFileReader();
		DatabaseReader dataReader = new DatabaseReader();
		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MMM dd,yyyy hh:mm");
		DecimalFormat df = new DecimalFormat("0.00");
		LinkedList invoiceArray = dataReader.readInvoices();
		
		
		Product tempProduct;
		Boolean hasTicket = false;
		for (int i = 0; i < invoiceArray.getSize(); i++) {
			for (int j = 0; j < ((Invoice)invoiceArray.getObject(i)).getProducts().size(); j++) {
				tempProduct = ((Invoice)invoiceArray.getObject(i)).getProducts().get(j);
				if (tempProduct instanceof SeasonPass) {
					hasTicket = true;
				} else if (tempProduct instanceof MovieTicket) {
					hasTicket = true;
				} else if (tempProduct instanceof ParkingPass) {
					
				} else if (tempProduct instanceof Refreshment) {
					if (hasTicket){
						((Refreshment) tempProduct).setHasTicket(true);
					}else if (!hasTicket){
						((Refreshment) tempProduct).setHasTicket(false);
					}
				}
			}
			hasTicket = false;
			
		}

		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		System.out.printf("%-8.8s %-35.35s %-20.20s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s\n", "Invoice", "Customer"
				, "Saleperson", "Subtotal", "Fees", "Taxes", "Discount", "Total");
		double totalSubTotal = 0;
		double totalFee = 0;
		double totalTaxes = 0;
		double totalDiscount = 0;
		double totalTotal = 0;
		for (int i = 0; i < invoiceArray.getSize(); i++) {
			if(((Invoice)invoiceArray.getObject(i)).getCustomer().getType().charAt(0) == 'S'){
				System.out.printf("%-8.8s %-35.35s %-20.20s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s\n", ((Invoice)invoiceArray.getObject(i)).getCode(), 
						(((Invoice) invoiceArray.getObject(i)).getCustomer().getName() + "[" + ((Invoice)invoiceArray.getObject(i)).getCustomer().getType() + "]"), 
						(((Invoice)invoiceArray.getObject(i)).getSalePerson().getLastName() + ", " + ((Invoice)invoiceArray.getObject(i)).getSalePerson().getFirstName()),
						"$" + df.format(((Invoice)invoiceArray.getObject(i)).getSubTotal()), "$" + df.format(((Invoice)invoiceArray.getObject(i)).getCustomer().getAdditionalFee())
						, "$" + df.format(((Invoice)invoiceArray.getObject(i)).getTax()), "$" + df.format(((Invoice)invoiceArray.getObject(i)).getStudentDiscount()), 
						"$" + df.format(((Invoice)invoiceArray.getObject(i)).getTotal() + ((Invoice)invoiceArray.getObject(i)).getStudentDiscount() + 6.75));
				totalSubTotal += ((Invoice)invoiceArray.getObject(i)).getSubTotal();
				totalFee += ((Invoice)invoiceArray.getObject(i)).getFee();
				totalTaxes += ((Invoice)invoiceArray.getObject(i)).getTax();
				totalDiscount += ((Invoice)invoiceArray.getObject(i)).getStudentDiscount();
				totalTotal += ((Invoice)invoiceArray.getObject(i)).getTotal() + ((Invoice)invoiceArray.getObject(i)).getStudentDiscount() + 6.75;
				
			}else if (((Invoice)invoiceArray.getObject(i)).getCustomer().getType().charAt(0) == 'G'){
				System.out.printf("%-8.8s %-35.35s %-20.20s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s\n", ((Invoice)invoiceArray.getObject(i)).getCode(), 
						(((Invoice)invoiceArray.getObject(i)).getCustomer().getName() + "[" + ((Invoice)invoiceArray.getObject(i)).getCustomer().getType() + "]"), 
						(((Invoice)invoiceArray.getObject(i)).getSalePerson().getLastName() + ", " + ((Invoice)invoiceArray.getObject(i)).getSalePerson().getFirstName()),
						"$" + df.format(((Invoice)invoiceArray.getObject(i)).getSubTotal()), "$" + df.format(((Invoice)invoiceArray.getObject(i)).getCustomer().getAdditionalFee())
						, "$" + df.format(((Invoice)invoiceArray.getObject(i)).getTax()), "$" + df.format(((Invoice)invoiceArray.getObject(i)).getDiscount()), 
						"$" + df.format(((Invoice)invoiceArray.getObject(i)).getTotal()));
				totalSubTotal += ((Invoice)invoiceArray.getObject(i)).getSubTotal();
				totalFee += ((Invoice)invoiceArray.getObject(i)).getFee();
				totalTaxes += ((Invoice)invoiceArray.getObject(i)).getTax();
				totalDiscount += ((Invoice)invoiceArray.getObject(i)).getDiscount();
				totalTotal += ((Invoice)invoiceArray.getObject(i)).getTotal() + ((Invoice)invoiceArray.getObject(i)).getDiscount();
			}
			
		}
		System.out.println("==========================================================="+
				"===========================================================");
		System.out.printf("%-8.8s %-35.35s %-20.20s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s\n",
				"TOTALS" , "", "", "$" + df.format(totalSubTotal),  "$" + df.format(totalFee),
				"$" + df.format(totalTaxes), "$" + df.format(totalDiscount), "$" + df.format(totalTotal));


		System.out.println("\n\n\n\n");


		System.out.println("Individual Invoice Detail Reports");
		System.out.println("============================================");

		

		for (int i = 0; i < invoiceArray.getSize(); i++) {
			System.out.print("Invoice " + ((Invoice)invoiceArray.getObject(i)).getCode() + "\n");
			System.out.println("============================");
			System.out.println("Salesperson: " + ((Invoice)invoiceArray.getObject(i)).getSalePerson().getLastName() + ", "
					+ ((Invoice)invoiceArray.getObject(i)).getSalePerson().getFirstName());
			System.out.println("Customer Info: ");
			System.out.println("  " + ((Invoice)invoiceArray.getObject(i)).getCustomer().getName() + " "
					+ ((Invoice)invoiceArray.getObject(i)).getCustomer().getCustomerCode());
			System.out.println("  [" + ((Invoice)invoiceArray.getObject(i)).getCustomer().getType() + "]");
			System.out.println("  " + ((Invoice)invoiceArray.getObject(i)).getCustomer().getContact().getLastName() + ", "
					+ ((Invoice)invoiceArray.getObject(i)).getCustomer().getContact().getFirstName());
			System.out.println("  " + ((Invoice)invoiceArray.getObject(i)).getCustomer().getContact().getAddress().toString());
			System.out.println("----------------------------");
			System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", "Code", "Item", "Sub Total", "Tax", "Total");


	
	
			hasTicket = false;

			for (int j = 0; j < ((Invoice)invoiceArray.getObject(i)).getProducts().size(); j++) {
				tempProduct = ((Invoice)invoiceArray.getObject(i)).getProducts().get(j);
				if (tempProduct.getType() == 'S') {
					// SeasonPass
					System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
							tempProduct.getProductCode(), 
							"SeasonPass - " + ((SeasonPass) tempProduct).getName(),
							"$" + df.format(tempProduct.totalBeforeTax()), 
							"$" + df.format(tempProduct.computeTax()),
							"$" + df.format(tempProduct.computeTotal())
							);
					System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", "  ",
							"(" + tempProduct.getAmount() + " units @ $" + df.format(((SeasonPass) tempProduct).getCost()) + "/unit + $8 fee/unit) ",
							"", "", "");
					hasTicket = true;
				} else if (tempProduct.getType() == 'M') {
					// MovieTicket
					if (((MovieTicket) tempProduct).getTime().getDayOfWeek() == 2 || ((MovieTicket) tempProduct).getTime().getDayOfWeek() == 5){
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((MovieTicket) tempProduct).getProductCode(),
								"MovieTicket " + ((MovieTicket) tempProduct).getMovieName() +
								" @ "+((MovieTicket) tempProduct).getAddress().getStreet() ,
								"$" + df.format( tempProduct.totalBeforeTax()),
								"$" + df.format(tempProduct.computeTax()),
								"$" + df.format(tempProduct.computeTotal())
								);
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n",
								"", dtfOut.print(((MovieTicket) tempProduct).getTime()) + " (" + tempProduct.getAmount() + " units @ $"
										+ df.format(((MovieTicket) tempProduct).getPricePerUnit()) + "/unit - Tue/Thu 7% off)",
										"", "", "");
						hasTicket = true;
					}else {
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								tempProduct.getProductCode(),
								"MovieTicket " + ((MovieTicket) tempProduct).getMovieName() + 
								" @ "+((MovieTicket) tempProduct).getAddress().getStreet(),
								"$" + df.format(tempProduct.totalBeforeTax()),
								"$" + df.format(tempProduct.computeTax()),
								"$" + df.format(tempProduct.computeTotal())
								);
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n",
								"", dtfOut.print(((MovieTicket) tempProduct).getTime()) + " (" + tempProduct.getAmount() + " units @ $"
										+ df.format(((MovieTicket) tempProduct).getPricePerUnit()) + "/unit)",
										"", "", "");
						hasTicket = true;
					}

				} else if (((Invoice)invoiceArray.getObject(i)).getProducts().get(j).getType() == 'P') {
					// ParkingPass
					if (hasTicket){
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								tempProduct.getProductCode(),
								"ParkingPass " + ((ParkingPass) tempProduct).getTicket() + 
								" (" + tempProduct.getAmount() + " units @ $" + 
								((ParkingPass) tempProduct).getParkingFee() + "/unit with " +  
								((ParkingPass) tempProduct).getFreeAmount() +" free)", 
								"$" + df.format(tempProduct.totalBeforeTax()),
								"$" + df.format(tempProduct.computeTax()), 
								"$" + df.format(((ParkingPass) tempProduct).computeTotal()));
					}else{
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((ParkingPass) tempProduct).getProductCode(),
								"ParkingPass" + 
										" (" + tempProduct.getAmount() + " units @ $" + 
										((ParkingPass) tempProduct).getParkingFee() + "/unit)", 
										"$" + df.format(tempProduct.totalBeforeTax()),
										"$" + df.format(tempProduct.computeTax()), 
										"$" + df.format(tempProduct.computeTotal()));
					}


				} else if (((Invoice)invoiceArray.getObject(i)).getProducts().get(j).getType() == 'R') {
					// Refreshment

					if (hasTicket){

						((Refreshment) tempProduct).setHasTicket(true);
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								tempProduct.getProductCode(),
								((Refreshment) tempProduct).getName() + " (" + tempProduct.getAmount() + " units @ $" + 
										df.format(((Refreshment) tempProduct).getPrice()) + "/unit with 5% off)",
										"$" + df.format(tempProduct.totalBeforeTax()),
										"$" + df.format(tempProduct.computeTax()),
										"$" + df.format(tempProduct.computeTotal()));
					}else if (!hasTicket){
						((Refreshment) tempProduct).setHasTicket(false);
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((Refreshment) tempProduct).getProductCode(),
								((Refreshment) tempProduct).getName() + " (" + tempProduct.getAmount() + " units @ $" + 
										df.format(((Refreshment) tempProduct).getPrice()) + "/unit)",
										"$" + df.format(tempProduct.totalBeforeTax()),
										"$" + df.format(tempProduct.computeTax()),
										"$" + df.format(tempProduct.computeTotal()));
					}


				}
			}
			System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", "", "", 
					"===============================",
					"===============================",
					"===============================");
			System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", "SUBTOTAL", "", 
					"$" + df.format(((Invoice)invoiceArray.getObject(i)).getSubTotal()),
					"$" + df.format(((Invoice)invoiceArray.getObject(i)).getTax()),
					"$" + df.format(((Invoice)invoiceArray.getObject(i)).getTotal()));

			if (((Invoice)invoiceArray.getObject(i)).getCustomer().getType().charAt(0) == 'S'){
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"DISCOUNT ( 8%  STUDENT & NO TAX)",
						"", "", "",
						"$" + df.format(((Invoice)invoiceArray.getObject(i)).getStudentDiscount())
						);
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"ADDITIONAL FEE (Student)",
						"", "", "",
						"$" + df.format(6.75)
						);
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"TOTAL",
						"", "", "",
						"$" + df.format(((Invoice)invoiceArray.getObject(i)).getTotal() + ((Invoice)invoiceArray.getObject(i)).getStudentDiscount() + 6.75)
						);
			}else{
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"TOTAL",
						"", "", "",
						"$" + df.format(((Invoice)invoiceArray.getObject(i)).getTotal())
						);
			}

			System.out.println();
			System.out.println();
			
			
		}
		
	}

}
