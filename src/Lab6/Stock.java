package Lab6;

//Invariants of this class
//Quantity > 0
//Price > 0.0
public class Stock {
	private int quantity;
	private double price;
	public Stock(int quantity, double price){
		this.quantity = quantity;
		this.price = price;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public double getPrice(){
		return price;
	}
}
