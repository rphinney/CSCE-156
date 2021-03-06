package phaseII;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Invoice {
	
	
//--------------------------------------------------------------
	
	
	public Product findProductByID(String productCode) {
		ProductExporter pr = new ProductExporter();
		List<Product> productList = pr.readProduct();
	
		for (Product p: productList) {
			if(productCode.equals(p.getProductCode())) {
				return p;
			}
		}
		return null;
	}
	
	public Person findPersonByID(String salespersonCode) {
		PersonFileReader fr = new PersonFileReader();
		List<Person> personList = fr.readPersons();
	
		for (Person p: personList) {
			if(salespersonCode.equals(p.getPersonCode())) {
				return p;
			}
		}
		return null;
	}
	
	public Customer findCustomerByID(String customerCode) {
		CustomerReader cr = new CustomerReader();
		List<Customer> customerList = cr.readCustomer();
	
		for (Customer c: customerList) {
			if(customerCode.equals(c.getCustomerCode())) {
				return c;
			}
		}
		return null;
	}

	//----------------------------------------------------------------------------------------------		
	public ArrayList<indInvoice> readInvoice() {
	Scanner sc = null;
			
			 try{
				sc = new Scanner(new File("data/Invoices.dat"));
				sc.nextLine(); // reads the number of records from the first line
				
				// This Invoice array list stores the Invoice objects 
				ArrayList<indInvoice> invoiceList = new ArrayList<indInvoice>();
				
				
				
				
				while(sc.hasNext()) {
					String line = sc.nextLine(); // reads each line starting from 2nd line
					String data[] = line.split(";"); // tokenizes the line and stores in a string array 
					int length = data.length;
					String invoiceCode = data[0];
					String customerCode = data[1];
					String salespersonCode = data[2];
					String date = data[3];
					ArrayList<Product> productInvList	= new ArrayList<Product>();
					ArrayList<String> quantity = new ArrayList<String>();
					ArrayList<String> ticketCode = new ArrayList<String>();
					for (int i = 0; i<length-4; i++ ) {
						
						String products[] = data[i+4].split(",");
						
		
					
						for (int j = 0; j< products.length; j++) {
							String productData[] = products[j].split(":");
							String productCode = productData[0];
							quantity.add(productData[1]);
							if (productData.length == 3) {
								 ticketCode.add(productData[2]);
							}else { 
								ticketCode.add("-999");
							}
							
							Product invproduct = findProductByID(productCode);
							productInvList.add(invproduct);
						}
						
					}
					Customer customer = findCustomerByID(customerCode);
					Person salesperson = findPersonByID(salespersonCode);
					indInvoice invoice = new indInvoice(invoiceCode,customer,salesperson,date,productInvList,quantity,ticketCode);
					invoiceList.add(invoice);
					
				}
				sc.close();
				return invoiceList;
					
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}	
	}
				
	
}
