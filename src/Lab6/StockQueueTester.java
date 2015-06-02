package Lab6;

public class StockQueueTester {
UnitTester tester;
	
	public StockQueueTester(){
		tester = new UnitTester();
		
		//Run tests
		tester.addNote("constructor tests...\n");
		constructor_Tests(tester);
		tester.addNote("addStock tests...\n");
		addStock_Tests(tester);
		tester.addNote("removeStock tests...\n");
		removeStock_Tests(tester);
		tester.addNote("hasStock tests...\n");
		hasStock_Tests(tester);
	}

	public String getResults() {
		return tester.getResults();
	}

	public boolean hasFailed() {
		return tester.hasFailed();
	}
	
	//constructor tests
	private static void constructor_Tests(UnitTester tester){
		int quantity = 1;
		double price = 0.0;
		String name = "name";
		
		Stock stock = new Stock(quantity, price);
		
		StockQueue stockQueue;
		stockQueue = new StockQueue(name, stock);
		
		//test name was set
		tester.test(stockQueue.getStockName().compareTo(name) == 0, true);
		
		//test totalQuantity was set
		tester.test(stockQueue.hasStock(), true);
		
		//test topPrice was set
		tester.test(stockQueue.getTopPrice(), price);
	}
	
	//addStock
	private static void addStock_Tests(UnitTester tester){
		int quantity1 = 1;
		int quantity2 = 2;
		int quantity3 = 3;
		double price1 = 1.1;
		double price2 = 2.2;
		double price3 = 3.3;
		Stock stock1 = new Stock(quantity1,price1);
		Stock stock2 = new Stock(quantity2,price2);
		Stock stock3 = new Stock(quantity3,price3);
		String name1 = "name1";
		
		StockQueue stockQueue = new StockQueue(name1, stock1);
		
		//Add a stock
		stockQueue.addStock(stock2);
		tester.test(stockQueue.getTotalQuantity(), quantity1 + quantity2);
		tester.test(stockQueue.getTopPrice(), price1);
		
		//Add a stock after removing a stock
		stockQueue.removeStock(quantity1 + quantity2);
		stockQueue.addStock(stock2);
		tester.test(stockQueue.getTotalQuantity(), quantity2);
		tester.test(stockQueue.getTopPrice(), price2);
		
		//Add two more stock
		stockQueue.addStock(stock1);
		stockQueue.addStock(stock3);
		tester.test(stockQueue.getTotalQuantity(), quantity1 + quantity2 + quantity3);
		tester.test(stockQueue.getTopPrice(), price2);
	}
	
	//removeStock
	private static void removeStock_Tests(UnitTester tester){
		int quantity1 = 1;
		int quantity2 = 2;
		int quantity3 = 3;
		double price1 = 1.1;
		double price3 = 3.3;
		Stock stock1 = new Stock(quantity1,price1);
		Stock stock3 = new Stock(quantity3,price3);
		String name1 = "name1";
		
		StockQueue stockQueue = new StockQueue(name1, stock1);
		
		//Remove stock leaving a zero quantity
		stockQueue.removeStock(quantity1);
		tester.test(stockQueue.getTotalQuantity(), 0);
		tester.test(stockQueue.getTopPrice(), Double.POSITIVE_INFINITY);
		
		//Remove stock leaving a non-zero quantity
		stockQueue.addStock(stock3);
		stockQueue.removeStock(quantity2);
		tester.test(stockQueue.getTotalQuantity(), quantity1);
		tester.test(stockQueue.getTopPrice(), price3);
	}
	
	//hasStock
	private static void hasStock_Tests(UnitTester tester){
		int quantity0 = 0;
		int quantity1 = 1;
		double price0 = 0.0;
		double price1 = 1.1;
		Stock stock0 = new Stock(quantity0,price0);
		Stock stock1 = new Stock(quantity1,price1);
		String name0 = "name0";
		
		StockQueue stockQueue;
		
		//Test with a zero quantity
		 stockQueue = new StockQueue(name0, stock0);
		 tester.test(stockQueue.hasStock(), false);
		 
		//Test with a non-zero quantity
		 stockQueue = new StockQueue(name0, stock1);
		 tester.test(stockQueue.hasStock(), true);
	}
}
