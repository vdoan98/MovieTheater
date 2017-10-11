import java.io.File;
import java.util.ArrayList;

public class InvoiceReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FlatFileReader fileReader = new FlatFileReader();
		
		System.out.println("Individual Invoice Detail Reports");
		System.out.println("============================================");
		
		ArrayList<Invoice> invoiceArray = fileReader.readInvoices();
		
		for (int i = 0; i < invoiceArray.size(); i++){
			System.out.print("Invoice " + invoiceArray.get(i).getCode() + "\n");
			System.out.println("============================");
			System.out.println("Salesperson: " + invoiceArray.get(i).getSalePerson().getLastName() + ", " +
							    invoiceArray.get(i).getSalePerson().getFirstName());
			System.out.println("Customer Info: ");
			System.out.println("  " + invoiceArray.get(i).getCustomer().getName() + " " + 
							   invoiceArray.get(i).getCustomer().getCustomerCode());
			System.out.println("  [" + invoiceArray.get(i).getCustomer().getType() + "]");
			System.out.println("  " + invoiceArray.get(i).getCustomer().getContact().getLastName() + 
							   ", " + invoiceArray.get(i).getCustomer().getContact().getFirstName());
		}
	}

}
