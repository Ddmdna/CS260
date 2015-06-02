package Lab6;

//Will end up being either the user or the house
public class Player {
	StockQueueList myStockQueueList;
	double initialCapital;
	double currentCapital;
	double profit;
	
	public Player(double initialCapital){
		this.initialCapital = initialCapital;
		currentCapital = initialCapital;
		profit = 0.0;
		myStockQueueList = new StockQueueList();
	}
	
	//buy stock
	//Tries to find a matching stockQueue to add stock to
	//Creates a new stockQueue if necessary
	//Returns the capital spent
	//This might be useful for a person holding the stock...
	public void buyStock(String stockName, int quantity, double price){
		int index = myStockQueueList.findIndexOfStockQueue(stockName);
		
		if(index == -1) //Create a new StockQueue
			myStockQueueList.add(new StockQueue(stockName, new Stock(quantity, price)));
		else //add to the old StockQueue
			myStockQueueList.getAtIndex(index).addStock(new Stock(quantity, price));
		
		//Lost capital
		updateMoney(-(quantity * price));
	}
	
	//sell stock
	//Returns the capital gained
	//This might be useful for a person holding the stock...
	public boolean sellStock(String stockName, int quantity, double price){
		boolean stockSold = false;
		
		int index = myStockQueueList.findIndexOfStockQueue(stockName);
		if(index != -1) //Remove stock from the index that does not equal -1
		{
			if(myStockQueueList.getAtIndex(index).getTotalQuantity() >= quantity){
				//Gained capital
				updateMoney(quantity * price);
			}
			if(myStockQueueList.getAtIndex(index).removeStock(quantity) > 0.0)
				stockSold = true;
			if(myStockQueueList.getAtIndex(index).getTotalQuantity() == 0){
				myStockQueueList.remove(index);
			}
		}
		return stockSold;
	}
	
	private void updateMoney(double capitalGained){
		currentCapital += capitalGained;
		profit = currentCapital - initialCapital;
	}
	
	public double getProfit(){
		return profit;
	}
	
	public StockQueueList getMyStockQueueList(){
		return myStockQueueList;
	}
	
	public double getCurrentCapital(){
		return currentCapital;
	}
}
