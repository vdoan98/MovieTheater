import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class InvoiceReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FlatFileReader fileReader = new FlatFileReader();
		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MMM dd,yyyy hh:mm");
		DecimalFormat df = new DecimalFormat("0.00");
		ArrayList<Invoice> invoiceArray = fileReader.readInvoices();
		
		
		Product tempProduct;
		Boolean hasTicket = false;
		for (int i = 0; i < invoiceArray.size(); i++) {
			for (int j = 0; j < invoiceArray.get(i).getProducts().size(); j++) {
				tempProduct = invoiceArray.get(i).getProducts().get(j);
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
				hasTicket = false;
			}
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
		for (int i = 0; i < invoiceArray.size(); i++) {
			if(invoiceArray.get(i).getCustomer().getType().charAt(0) == 'S'){
				System.out.printf("%-8.8s %-35.35s %-20.20s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s\n", invoiceArray.get(i).getCode(), 
						(invoiceArray.get(i).getCustomer().getName() + "[" + invoiceArray.get(i).getCustomer().getType() + "]"), 
						(invoiceArray.get(i).getSalePerson().getLastName() + ", " + invoiceArray.get(i).getSalePerson().getFirstName()),
						"$" + df.format(invoiceArray.get(i).getSubTotal()), "$" + df.format(invoiceArray.get(i).getCustomer().getAdditionalFee())
						, "$" + df.format(invoiceArray.get(i).getTax()), "$" + df.format(invoiceArray.get(i).getStudentDiscount()), 
						"$" + df.format(invoiceArray.get(i).getTotal() + invoiceArray.get(i).getStudentDiscount() + 6.75));
				totalSubTotal += invoiceArray.get(i).getSubTotal();
				totalFee += invoiceArray.get(i).getFee();
				totalTaxes += invoiceArray.get(i).getTax();
				totalDiscount += invoiceArray.get(i).getStudentDiscount();
				totalTotal += invoiceArray.get(i).getTotal() + invoiceArray.get(i).getDiscount()+6.75;
				
			}else if (invoiceArray.get(i).getCustomer().getType().charAt(0) == 'G'){
				System.out.printf("%-8.8s %-35.35s %-20.20s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s\n", invoiceArray.get(i).getCode(), 
						(invoiceArray.get(i).getCustomer().getName() + "[" + invoiceArray.get(i).getCustomer().getType() + "]"), 
						(invoiceArray.get(i).getSalePerson().getLastName() + ", " + invoiceArray.get(i).getSalePerson().getFirstName()),
						"$" + df.format(invoiceArray.get(i).getSubTotal()), "$" + df.format(invoiceArray.get(i).getCustomer().getAdditionalFee())
						, "$" + df.format(invoiceArray.get(i).getTax()), "$" + df.format(invoiceArray.get(i).getDiscount()), 
						"$" + df.format(invoiceArray.get(i).getTotal()));
				totalSubTotal += invoiceArray.get(i).getSubTotal();
				totalFee += invoiceArray.get(i).getFee();
				totalTaxes += invoiceArray.get(i).getTax();
				totalDiscount += invoiceArray.get(i).getDiscount();
				totalTotal += invoiceArray.get(i).getTotal() + invoiceArray.get(i).getDiscount();
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

		

		for (int i = 0; i < invoiceArray.size(); i++) {
			System.out.print("Invoice " + invoiceArray.get(i).getCode() + "\n");
			System.out.println("============================");
			System.out.println("Salesperson: " + invoiceArray.get(i).getSalePerson().getLastName() + ", "
					+ invoiceArray.get(i).getSalePerson().getFirstName());
			System.out.println("Customer Info: ");
			System.out.println("  " + invoiceArray.get(i).getCustomer().getName() + " "
					+ invoiceArray.get(i).getCustomer().getCustomerCode());
			System.out.println("  [" + invoiceArray.get(i).getCustomer().getType() + "]");
			System.out.println("  " + invoiceArray.get(i).getCustomer().getContact().getLastName() + ", "
					+ invoiceArray.get(i).getCustomer().getContact().getFirstName());
			System.out.println("  " + invoiceArray.get(i).getCustomer().getContact().getAddress().toString());
			System.out.println("----------------------------");
			System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", "Code", "Item", "Sub Total", "Tax", "Total");


	
	
			hasTicket = false;

			for (int j = 0; j < invoiceArray.get(i).getProducts().size(); j++) {
				tempProduct = invoiceArray.get(i).getProducts().get(j);
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

				} else if (invoiceArray.get(i).getProducts().get(j).getType() == 'P') {
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


				} else if (invoiceArray.get(i).getProducts().get(j).getType() == 'R') {
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
					"$" + df.format(invoiceArray.get(i).getSubTotal()),
					"$" + df.format(invoiceArray.get(i).getTax()),
					"$" + df.format(invoiceArray.get(i).getTotal()));

			if (invoiceArray.get(i).getCustomer().getType().charAt(0) == 'S'){
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"DISCOUNT ( 8%  STUDENT & NO TAX)",
						"", "", "",
						"$" + df.format(invoiceArray.get(i).getStudentDiscount())
						);
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"ADDITIONAL FEE (Student)",
						"", "", "",
						"$" + df.format(6.75)
						);
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"TOTAL",
						"", "", "",
						"$" + df.format(invoiceArray.get(i).getTotal() + invoiceArray.get(i).getStudentDiscount() + 6.75)
						);
			}else{
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"TOTAL",
						"", "", "",
						"$" + df.format(invoiceArray.get(i).getTotal())
						);
			}

			System.out.println();
			System.out.println();
			
			
		}
		
	}

}
