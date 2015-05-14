package Lab6;

public class Day {
	//Thought about making this static but string pool should help...
	private String[] stockNameList;
	private double[] stockPriceList;
	private int numberOfStocks;
	private int index;
	public Day(String[] stockNameList, double[] stockPriceList, int numberOfStocks){
		this.stockNameList = stockNameList;
		this.stockPriceList = stockPriceList;
		this.numberOfStocks = numberOfStocks;
		index = 0;
	}
	
	
	public void resetIndex(){
		index = 0;
	}
	
	public boolean hasNext(){
		return index < numberOfStocks;
	}
	
	public String getStockName(){
		return stockNameList[index];
	}
	
	public double getStockPrice(){
		return stockPriceList[index];
	}
	
	public void next(){
		index++;
	}
}
