package Lab6;

public class PlayerTester {
UnitTester tester;
	
	public PlayerTester(){
		tester = new UnitTester();
		
		//Run tests
		tester.addNote("constructor tests...\n");
		constructor_Tests(tester);
		tester.addNote("buyStock tests...\n");
		buyStock_Tests(tester);
		tester.addNote("sellStock tests...\n");
		sellStock_Tests(tester);
	}

	public String getResults() {
		return tester.getResults();
	}

	public boolean hasFailed() {
		return tester.hasFailed();
	}
	
	//constructor tests
	private static void constructor_Tests(UnitTester tester){
		double initialCapitalMin = Double.MAX_VALUE;
		double initialCapitalZero = 0.0;
		double initialCapitalMax = Double.MAX_VALUE;

		Player player;
		
		//Test min capital
		player = new Player(initialCapitalMin);
		tester.test(player.getCurrentCapital(), initialCapitalMin);
		tester.test(player.getProfit(), 0.0);
		
		//Test zero capital
		player = new Player(initialCapitalZero);
		tester.test(player.getCurrentCapital(), initialCapitalZero);
		tester.test(player.getProfit(), 0.0);
		
		//Test max capital
		player = new Player(initialCapitalMax);
		tester.test(player.getCurrentCapital(), initialCapitalMax);
		tester.test(player.getProfit(), 0.0);
	}
	
	//buyStock tests
	private static void buyStock_Tests(UnitTester tester){
		Double initialCapital = 1000.0;
		String name1 = "name1";
		String name2 = "name2";
		int quantity1 = 10;
		int quantity2 = 20;
		double price1 = 1.0;
		double price2 = 2.0;
		
		int index;
		int setQuantity;
		double setPrice;
		Player player = new Player(initialCapital);
		
		//Test on unmatched stockQueue
		player.buyStock(name1, quantity1, price1);
		index = player.getMyStockQueueList().findIndexOfStockQueue(name1);
		setQuantity = player.getMyStockQueueList().getAtIndex(index).getTotalQuantity();
		tester.test(quantity1, setQuantity);
		tester.test(initialCapital - (price1 * quantity1), player.getCurrentCapital());
		
		//Test on matched stockQueue
		player.buyStock(name1, quantity1, price1);
		index = player.getMyStockQueueList().findIndexOfStockQueue(name1);
		setQuantity = player.getMyStockQueueList().getAtIndex(index).getTotalQuantity();
		tester.test(quantity1*2, setQuantity);
		tester.test(initialCapital - (price1 * quantity1)*2, player.getCurrentCapital());
		
		//Test adding zero quantity
		player.buyStock(name1, 0, price1);
		index = player.getMyStockQueueList().findIndexOfStockQueue(name1);
		setQuantity = player.getMyStockQueueList().getAtIndex(index).getTotalQuantity();
		tester.test(quantity1*2, setQuantity);
		tester.test(initialCapital - (price1 * quantity1)*2, player.getCurrentCapital());
	
		//Test adding a unique stock
		player.buyStock(name2, quantity2, price2);
		index = player.getMyStockQueueList().findIndexOfStockQueue(name2);
		setQuantity = player.getMyStockQueueList().getAtIndex(index).getTotalQuantity();
		tester.test(quantity2, setQuantity);
		tester.test(initialCapital - (price1 * quantity1)*2 - price2 * quantity2, player.getCurrentCapital());
	}
	
	//sellStock tests
	private static void sellStock_Tests(UnitTester tester){
		Double initialCapital = 1000.0;
		String name1 = "name1";
		String name2 = "name2";
		int quantity1 = 10;
		int quantity2 = 20;
		double price1 = 1.0;
		double price2 = 2.0;
		
		int index;
		int setQuantity;
		double setPrice;
		Player player = new Player(initialCapital);
		
		player.buyStock(name1, quantity1, price1);
		player.buyStock(name2, quantity2, price2);
		
		//Test on unmatched stockQueue
		tester.test(player.sellStock("unmatched", quantity1, price1), false);
		
		//Test on matched stockQueue without selling too much
		tester.test(player.sellStock(name1, quantity1, price1), true);
		
		//Test on matched stockQueue with selling too much
		player.buyStock(name1, quantity1, price1);
		tester.test(player.sellStock(name1, quantity1*10, price1), false);
		index = player.getMyStockQueueList().findIndexOfStockQueue(name1);
		setQuantity = player.getMyStockQueueList().getAtIndex(index).getTotalQuantity();
		tester.test(setQuantity, 10);
		tester.test(initialCapital - (price1 * quantity1) - (price2 * quantity2), player.getCurrentCapital());
		
		//Test adding zero quantity
		player.buyStock(name1, 0, price1);
		index = player.getMyStockQueueList().findIndexOfStockQueue(name1);
		setQuantity = player.getMyStockQueueList().getAtIndex(index).getTotalQuantity();
		tester.test(setQuantity, 10);
		tester.test(initialCapital - (price1 * quantity1) - (price2 * quantity2), player.getCurrentCapital());
	}
}
