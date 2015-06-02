package Lab6;

public class DayTester {
UnitTester tester;
	
	public DayTester(){
		tester = new UnitTester();
		
		//Run tests
		tester.addNote("constructor tests...\n");
		constructor_Tests(tester);
	}

	public String getResults() {
		return tester.getResults();
	}

	public boolean hasFailed() {
		return tester.hasFailed();
	}
	
	//constructor tests
	private static void constructor_Tests(UnitTester tester){
		
		String[] stockNameList1 = {"name1", "name2", "name3"};
		double[] stockPriceList1 = {11.1, 22.2, 33.3};
		int numberOfStocks = 3;
		
		Day day = new Day(stockNameList1, stockPriceList1, numberOfStocks);
		
		//Test stockNameList
		tester.test(day.getStockName().compareTo(stockNameList1[0]), 0);
		day.next();
		tester.test(day.getStockName().compareTo(stockNameList1[1]), 0);
		day.next();
		tester.test(day.getStockName().compareTo(stockNameList1[2]), 0);
		
		//Test stockPriceList
		day.resetIndex();
		tester.test(day.getStockPrice(), stockPriceList1[0]);
		day.next();
		tester.test(day.getStockPrice(), stockPriceList1[1]);
		day.next();
		tester.test(day.getStockPrice(), stockPriceList1[2]);
	}
	
	private static void resetIndex(UnitTester tester){
		String[] stockNameList1 = {"name1", "name2", "name3"};
		double[] stockPriceList1 = {11.1, 22.2, 33.3};
		int numberOfStocks = 3;
		
		Day day = new Day(stockNameList1, stockPriceList1, numberOfStocks);
		
		//Test stockNameList
		tester.test(day.getStockName().compareTo(stockNameList1[0]), 0);
		day.next();
		day.resetIndex();
		tester.test(day.getStockName().compareTo(stockNameList1[0]), 0);
		
		//Test stockPriceList
		tester.test(day.getStockPrice(), stockPriceList1[0]);
		day.next();
		day.resetIndex();
		tester.test(day.getStockPrice(), stockPriceList1[0]);
	}
	
	private static void hasNext(UnitTester tester){
		String[] stockNameList1 = {"name1", "name2", "name3"};
		double[] stockPriceList1 = {11.1, 22.2, 33.3};
		int numberOfStocks = 3;
		
		Day day = new Day(stockNameList1, stockPriceList1, numberOfStocks);
		int count = 0;
		while(day.hasNext()){
			count++;
			day.next();
		}
		tester.test(count, numberOfStocks-1);
	}
	
	private static void getStockName(UnitTester tester){
		String[] stockNameList1 = {"name1", "name2", "name3"};
		double[] stockPriceList1 = {11.1, 22.2, 33.3};
		int numberOfStocks = 3;
		
		Day day = new Day(stockNameList1, stockPriceList1, numberOfStocks);
		while(day.hasNext()){
			int i = 0;
			tester.test(day.getStockName().compareTo(stockNameList1[i]), 0);
			day.next();
			i++;
		}
	}
	
	private static void getStockPrice(UnitTester tester){
		String[] stockNameList1 = {"name1", "name2", "name3"};
		double[] stockPriceList1 = {11.1, 22.2, 33.3};
		int numberOfStocks = 3;
		
		Day day = new Day(stockNameList1, stockPriceList1, numberOfStocks);
		while(day.hasNext()){
			int i = 0;
			tester.test(day.getStockPrice(), stockPriceList1[i]);
			day.next();
			i++;
		}
	}
}
