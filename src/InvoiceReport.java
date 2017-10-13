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
			System.out.printf("%-8.8s %-35.35s %-20.20s %-10.10s %-10.10s %-10.10s %-10.10s %-10.10s\n", invoiceArray.get(i).getCode(), 
					(invoiceArray.get(i).getCustomer().getName() + "[" + invoiceArray.get(i).getCustomer().getType() + "]"), 
					(invoiceArray.get(i).getSalePerson().getLastName() + ", " + invoiceArray.get(i).getSalePerson().getFirstName()),
					"$" + df.format(invoiceArray.get(i).getSubTotal()), "$" + df.format(((Customer)invoiceArray.get(i).getCustomer()).getAdditionalFee())
					, "$" + df.format(invoiceArray.get(i).getTax()), "$" + df.format(((Customer)invoiceArray.get(i).getCustomer()).getDiscount()), 
					"$" + df.format(invoiceArray.get(i).getTotal()));
			totalSubTotal += invoiceArray.get(i).getSubTotal();
			totalFee += invoiceArray.get(i).getFee();
			totalTaxes += invoiceArray.get(i).getTax();
			totalDiscount += invoiceArray.get(i).getDiscount();
			totalTotal += invoiceArray.get(i).getTotal();
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

			Product tempProduct;
			
			totalSubTotal = 0;
			totalTaxes = 0;
			totalTotal = 0;
			double studentDiscount = 0;
			Boolean hasTicket = false;

			for (int j = 0; j < invoiceArray.get(i).getProducts().size(); j++) {
				tempProduct = invoiceArray.get(i).getProducts().get(j);
				if (tempProduct.getType() == 'S') {
					// SeasonPass
					System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
							((SeasonPass) tempProduct).getProductCode(), 
							"SeasonPass - " + ((SeasonPass) tempProduct).getName(),
							"$" + df.format(((SeasonPass) tempProduct).getTotal()), 
							"$" + df.format(((SeasonPass) tempProduct).computeTax()),
							"$" + df.format(((SeasonPass) tempProduct).computeTotal())
							);
					System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", "  ",
							"(" + tempProduct.getAmount() + " units @ $" + df.format(((SeasonPass) tempProduct).getCost()) + "/unit) ",
							"", "", "");
					totalSubTotal += ((SeasonPass) tempProduct).getTotal();
					totalTaxes += ((SeasonPass) tempProduct).computeTax();
					totalTotal += ((SeasonPass) tempProduct).computeTotal();
					studentDiscount += ((SeasonPass) tempProduct).studentDiscount();
					hasTicket = true;
				} else if (tempProduct.getType() == 'M') {
					// MovieTicket
					if (((MovieTicket) tempProduct).getTime().getDayOfWeek() == 2 || ((MovieTicket) tempProduct).getTime().getDayOfWeek() == 5){
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((MovieTicket) tempProduct).getProductCode(),
								"MovieTicket " + ((MovieTicket) tempProduct).getMovieName() +
								" @ "+((MovieTicket) tempProduct).getAddress().getStreet() ,
								"$" + df.format(((MovieTicket) tempProduct).getTotal()),
								"$" + df.format(((MovieTicket) tempProduct).computeTax()),
								"$" + df.format(((MovieTicket) tempProduct).computeTotal())
								);
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n",
								"", dtfOut.print(((MovieTicket) tempProduct).getTime()) + " (" + tempProduct.getAmount() + " units @ $"
								+ df.format(((MovieTicket) tempProduct).getPricePerUnit()) + "/unit - Tue/Thu 7% off)",
								"", "", "");
						totalSubTotal += ((MovieTicket) tempProduct).getTotal();
						totalTaxes += ((MovieTicket) tempProduct).computeTax();
						totalTotal += ((MovieTicket) tempProduct).computeTotal();
						studentDiscount += ((MovieTicket) tempProduct).studentDiscount();
						hasTicket = true;
					}else {
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((MovieTicket) tempProduct).getProductCode(),
								"MovieTicket " + ((MovieTicket) tempProduct).getMovieName() + 
								" @ "+((MovieTicket) tempProduct).getAddress().getStreet(),
								"$" + df.format(((MovieTicket) tempProduct).getTotal()),
								"$" + df.format(((MovieTicket) tempProduct).computeTax()),
								"$" + df.format(((MovieTicket) tempProduct).computeTotal())
								);
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n",
								"", dtfOut.print(((MovieTicket) tempProduct).getTime()) + " (" + tempProduct.getAmount() + " units @ $"
								+ df.format(((MovieTicket) tempProduct).getPricePerUnit()) + "/unit)",
								"", "", "");
						totalSubTotal += ((MovieTicket) tempProduct).getTotal();
						totalTaxes += ((MovieTicket) tempProduct).computeTax();
						totalTotal += ((MovieTicket) tempProduct).computeTotal();
						studentDiscount += ((MovieTicket) tempProduct).studentDiscount();
						hasTicket = true;
					}

				} else if (invoiceArray.get(i).getProducts().get(j).getType() == 'P') {
					// ParkingPass
					if (hasTicket){
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((ParkingPass) tempProduct).getProductCode(),
								"ParkingPass " + ((ParkingPass) tempProduct).getTicket() + 
								" (" + tempProduct.getAmount() + " units @ $" + 
								((ParkingPass) tempProduct).getParkingFee() + "/unit with " +  
								((ParkingPass) tempProduct).getTicketAmount() +" free)", 
								"$" + df.format(((ParkingPass) tempProduct).getTotal()),
								"$" + df.format(((ParkingPass) tempProduct).computeTax()), 
								"$" + df.format(((ParkingPass) tempProduct).computeTotal()));
					}else{
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((ParkingPass) tempProduct).getProductCode(),
								"ParkingPass" + 
								" (" + tempProduct.getAmount() + " units @ $" + 
								((ParkingPass) tempProduct).getParkingFee() + "/unit)", 
								"$" + df.format(((ParkingPass) tempProduct).getTotal()),
								"$" + df.format(((ParkingPass) tempProduct).computeTax()), 
								"$" + df.format(((ParkingPass) tempProduct).computeTotal()));
					}
					
					totalSubTotal += ((ParkingPass) tempProduct).getTotal();
					totalTaxes += ((ParkingPass) tempProduct).computeTax();
					totalTotal += ((ParkingPass) tempProduct).computeTotal();
					studentDiscount += ((ParkingPass) tempProduct).studentDiscount();

				} else if (invoiceArray.get(i).getProducts().get(j).getType() == 'R') {
					// Refreshment
					double rTotal = 0;
					double rTax = 0;
					double rComputeTotal = 0;
					
					if (hasTicket){

						
						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((Refreshment) tempProduct).getProductCode(),
								((Refreshment) tempProduct).getName() + " (" + tempProduct.getAmount() + " units @ $" + 
								df.format(((Refreshment) tempProduct).getPrice()) + "/unit with 5% off)",
								"$" + df.format(((Refreshment) tempProduct).getTotal() - ((Refreshment) tempProduct).getDiscount()),
								"$" + df.format(((Refreshment) tempProduct).computeTax() - ((Refreshment) tempProduct).getDiscountTax()),
								"$" + df.format(((Refreshment) tempProduct).getTotal() - ((Refreshment) tempProduct).getDiscount() + ((Refreshment) tempProduct).computeTax() - ((Refreshment) tempProduct).getDiscountTax()));
					}else{

						System.out.printf("%-8.8s %-70.70s %-10.10s %-10.10s %-10.10s\n", 
								((Refreshment) tempProduct).getProductCode(),
								((Refreshment) tempProduct).getName() + " (" + tempProduct.getAmount() + " units @ $" + 
								df.format(((Refreshment) tempProduct).getPrice()) + "/unit)",
								"$" + df.format(((Refreshment) tempProduct).getTotal()),
								"$" + df.format(((Refreshment) tempProduct).computeTax()),
								"$" + df.format(((Refreshment) tempProduct).getTotal() + ((Refreshment) tempProduct).computeTax()));
					}
					
					totalSubTotal += ((Refreshment) tempProduct).getTotal();
					totalTaxes += ((Refreshment) tempProduct).computeTax();
					totalTotal += ((Refreshment) tempProduct).computeTotal();
					studentDiscount += ((Refreshment) tempProduct).studentDiscount();
				}
			}
			invoiceArray.get(i).setDiscount(studentDiscount);
			System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", "", "", 
					  "===============================",
					  "===============================",
					  "===============================");
			System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", "SUBTOTAL", "", 
					  "$" + df.format(totalSubTotal),
					  "$" + df.format(totalTaxes),
					  "$" + df.format(totalTotal));
			
			if (invoiceArray.get(i).getCustomer().getType().charAt(0) == 'S'){
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"DISCOUNT ( 8%  STUDENT & NO TAX)",
						"", "", "",
						"$-" + df.format(studentDiscount)
						);
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"ADDITIONAL FEE (Student)",
						"", "", "",
						"$" + df.format(6.75)
						);
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"TOTAL",
						"", "", "",
						"$" + df.format(totalTotal - totalTaxes - studentDiscount + 6.75)
						);
			}else{
				System.out.printf("%-38.38s %-40.40s %-10.10s %-10.10s %-10.10s\n", 
						"TOTAL",
						"", "", "",
						"$" + df.format(totalTotal)
						);
			}
			
			System.out.println();
			System.out.println();
		}
	}

}
