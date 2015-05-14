package Lab6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Driver {
	public static void main(String[] args)
	{
		//File with starting stock data
		String testFileName = "TestStockData.File";
		File testFile;
		FileInputStream testFileInputStream;
		
		Timeline myTimeline = null;
		
		//Create a shop
		StockQueueList shop = new StockQueueList();
		
		//Create a player
		Player user = new Player(30000.0);
		
		//Create the timeline
		try {
			testFile = new File(testFileName);
			testFileInputStream = new FileInputStream(testFile);
			myTimeline = new Timeline(testFileInputStream, 10, 30);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Create the stock game
		StockGame game = new StockGame(shop, user, myTimeline);

		//Run the game
		game.run();
	}
}
