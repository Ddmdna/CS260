package Lab6;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class StockGame {
	private final int MAX = Integer.MAX_VALUE;
	StockGameView view;
	StockQueueList shop;
	Player user;
	Timeline myTimeline;
	boolean running;
	
	final int WIDTH = 800;
	final int HEIGHT = 600;
	public StockGame(StockQueueList shop, Player user, Timeline myTimeline){
		this.shop = shop;
		this.user = user;
		this.myTimeline = myTimeline;
		initializeShop(this.myTimeline.getNextDay());
		view = new StockGameView(shop, user, myTimeline, WIDTH, HEIGHT);
		running = false;
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
	
	public void run() {
		//running = false;

		//game loop
		while(view != null){

			
			//while there are more days
			//update view
			//
			
			//output results
			
		}
		//end
		//create a popup with final stats?
		JFrame resultFrame = new JFrame();
		JOptionPane.showMessageDialog(resultFrame,
				"End Results",
				"Profit: " + user.getProfit(),
				JOptionPane.INFORMATION_MESSAGE);
	}
}
