package phaseII;

public class Refreshment extends Product {
	private String name;
	private String cost;
	
	public Refreshment(String productCode, String type, String name, String cost) {
		super(productCode, type);
		this.name = name;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setCost(String cost) {
		this.cost = cost;
	}
	
	@Override	
	public double getSubtotal(int quantity) {
		return quantity*Double.parseDouble(cost);
	}
	
	@Override
	public double getTax() {
		return .06;
	}
		
	@Override
	public double getDiscount() {
		//5% if the invoice also has a ticket
		return .05;
	}
	
	@Override
	public double getCost() {
		return Double.parseDouble(cost);
	}
	
	@Override
	public String toString(int quantity) {
		return "Refreshment "+ getName() + " (" + quantity + " units @ $"+ getCost() +")";
	}
	
}
