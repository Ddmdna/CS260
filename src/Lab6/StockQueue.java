package Lab6;

import java.util.LinkedList;

//What happens when the LinkedList is empty?
//Do I remove the overall object?
public class StockQueue {
	private String stockName;
	private int totalQuantity;
	private double topPrice;
	private LinkedList<Stock> queue;
	
	public StockQueue(String stockName, Stock stock){
		this.stockName = stockName;
		totalQuantity = stock.getQuantity();
		topPrice = stock.getPrice();
		queue = new LinkedList<Stock>();
		queue.add(stock);
	}
	
	//add
	//Update totalQuantity
	public void addStock(Stock stock){
		totalQuantity += stock.getQuantity();
		if(queue.isEmpty())
			topPrice = stock.getPrice();
		queue.add(stock);
	}
	
	//remove
	//Update topPrice
	//Can't remove more than stocks that are available
	//Calculate the cost of stock removed?
	//return 0.0 if no stock was removed
	public double removeStock(int quantityToRemove){
		double capitalOfRemovedStock = 0.0;
		int topQuantity;
		double tempPrice;
		
		//Remove stock if there is enough in the queue
		if(quantityToRemove >= totalQuantity)
		{
			//Update totalQuantity
			totalQuantity -= quantityToRemove;
			
			//Remove stock until we meet our goal
			while(quantityToRemove > 0)
			{
				//Get the top stocks data
				topQuantity = queue.peek().getQuantity();
				tempPrice = queue.peek().getPrice();
				
				//Remove each whole stock that fits within our goal amount
				if(topQuantity <= quantityToRemove)
				{
					quantityToRemove -= topQuantity;
					capitalOfRemovedStock += tempPrice * topQuantity;
					queue.pop();
				}else //remove from the stock up to our goal amount
				{
					topQuantity -= quantityToRemove;
					capitalOfRemovedStock += tempPrice * quantityToRemove;
					queue.peek().setQuantity(topQuantity);
				}
			}
			//Update topPrice
			if(!queue.isEmpty())
				topPrice = queue.peek().getPrice();
			else
				topPrice = Double.POSITIVE_INFINITY; //hahahaha
		}
		return capitalOfRemovedStock;
	}
	
	public boolean hasStock(){
		return totalQuantity > 0;
	}
	
	public String getStockName(){
		return stockName;
	}
	
	public int getTotalQuantity(){
		return totalQuantity;
	}
	
	public double getTopPrice(){
		return topPrice;
	}
	
}
