package Lab6;

public class StockTester {
	UnitTester tester;
	
	public StockTester(){
		tester = new UnitTester();
		
		//Run tests
		constructor_Tests(tester);
		setgetQuantity_Tests(tester);
		setgetPrice_Tests(tester);
	}

	public String getResults() {
		return tester.getResults();
	}

	public boolean hasFailed() {
		return tester.hasFailed();
	}
	
	//constructor tests
	private static void constructor_Tests(UnitTester tester){
		int quantityMin = Integer.MIN_VALUE;
		int quantityZero = 0;
		int quantityMax = Integer.MAX_VALUE;
		
		double priceMin = Double.MIN_VALUE;
		double priceZero = 0.0;
		double prizeMax = Double.MAX_VALUE;
		
		Stock stock;
		
		//test min
		stock = new Stock(quantityMin, priceMin);
		tester.test(stock.getQuantity(), quantityMin);
		tester.test(stock.getPrice(), priceMin);
		
		//test zero
		stock = new Stock(quantityZero, priceZero);
		tester.test(stock.getQuantity(), quantityZero);
		tester.test(stock.getPrice(), priceZero);
		
		//test max
		stock = new Stock(quantityMax, prizeMax);
		tester.test(stock.getQuantity(), quantityMax);
		tester.test(stock.getPrice(), prizeMax);
	}
	
	//setQuantity tests
	private static void setgetQuantity_Tests(UnitTester tester){
		Stock stock = new Stock(0, 0.0);
		int quantityMin = Integer.MIN_VALUE;
		int quantityZero = 0;
		int quantityMax = Integer.MAX_VALUE;
		
		stock.setQuantity(quantityMin);
		tester.test(stock.getQuantity(), quantityMin);
		stock.setQuantity(quantityZero);
		tester.test(stock.getQuantity(), quantityZero);
		stock.setQuantity(quantityMax);
		tester.test(stock.getQuantity(), quantityMax);
	}
	
	//setPrice and getPrice tests
	private static void setgetPrice_Tests(UnitTester tester){
		Stock stock = new Stock(0, 0.0);
		double priceMin = Double.MIN_VALUE;
		double priceZero = 0.0;
		double prizeMax = Double.MAX_VALUE;
		
		stock.setPrice(priceMin);
		tester.test(stock.getPrice(), priceMin);
		stock.setPrice(priceZero);
		tester.test(stock.getPrice(), priceZero);
		stock.setPrice(prizeMax);
		tester.test(stock.getPrice(), prizeMax);
	}
}
