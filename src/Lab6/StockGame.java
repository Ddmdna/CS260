package Lab6;

public class StockGame {
	private final int MAX = Integer.MAX_VALUE;
	StockGameView view;
	StockQueueList shop;
	Player user;
	Timeline myTimeline;
	
	public StockGame(StockQueueList shop, Player user, Timeline myTimeline){
		this.shop = shop;
		this.user = user;
		this.myTimeline = myTimeline;
		initializeShop(this.myTimeline.getNextDay());
		view = new StockGameView(shop, user, myTimeline);
	}

	private void initializeShop(Day day) {
		StockQueue tempStockQueue;
		String tempStockName;
		Stock tempStock;
		
		while(day.hasNext()){
			tempStockName = day.getStockName();
			tempStock = new Stock(MAX, day.getStockPrice());
			tempStockQueue = new StockQueue(tempStockName, tempStock);
			shop.add(tempStockQueue);
			day.next();
		}
	}

	public void addView(StockGameView view) {
		this.view = view;
	}

	public void run() {
		//update view

		//while there are more days
		//update view
		//
		
		//output results
		
		//end
		
	}
}
