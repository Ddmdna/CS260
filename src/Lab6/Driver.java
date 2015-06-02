package Lab6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args)
	{
		//Test core classes
		String testResults;
		
		//Stock
		testResults = "Starting tests for Stock:\n";
		StockTester stockTester = new StockTester();
		testResults += stockTester.getResults();
		System.out.println(testResults);
		pauseOnFail(stockTester.hasFailed());
		
		//StockQueue
		testResults = "Starting tests for StockQueue:\n";
		StockQueueTester stockQueueTester = new StockQueueTester();
		testResults += stockQueueTester.getResults();
		System.out.println(testResults);
		pauseOnFail(stockQueueTester.hasFailed());
		
		//StockQueueList
		testResults = "Starting tests for StockQueueList:\n";
		StockQueueListTester stockQueueListTester = new StockQueueListTester();
		testResults += stockQueueListTester.getResults();
		System.out.println(testResults);
		pauseOnFail(stockQueueListTester.hasFailed());
		
		//Player
		testResults = "Starting tests for Player:\n";
		PlayerTester playerTester = new PlayerTester();
		testResults += playerTester.getResults();
		System.out.println(testResults);
		pauseOnFail(playerTester.hasFailed());
		
		//Day
		testResults = "Starting tests for Day:\n";
		DayTester dayTester = new DayTester();
		testResults += dayTester.getResults();
		System.out.println(testResults);
		pauseOnFail(dayTester.hasFailed());
		
		//File with starting stock data
		String testFileName = "TestStockData";
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
		
		//Create the stock game (runs when created)
		StockGame game = new StockGame(shop, user, myTimeline);

	}
	
	public static void pauseOnFail(boolean hasFailed){
		if(hasFailed){
			System.out.println("A test has failed\n");
			System.out.println("Enter a key to continue...\n");
			Scanner scan = new Scanner(System.in);
			scan.next();
		}
	}
}
