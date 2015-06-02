package Lab6;

import java.util.LinkedList;

public class StockQueueListTester {
	UnitTester tester;
	
	public StockQueueListTester(){
		tester = new UnitTester();
		
		//Run tests
		tester.addNote("constructor tests...\n");
		constructor_Tests(tester);
		tester.addNote("add tests...\n");
		add_Tests(tester);
		tester.addNote("shiftStockQueue tests...\n");
		shiftStockQueue_Tests(tester);
		tester.addNote("getStockQueueAtIndexFromHead tests...\n");
		getStockQueueAtIndexFromHead_Tests(tester);
		tester.addNote("findIndexOfStockQueue tests...\n");
		findIndexOfStockQueue_Tests(tester);
		tester.addNote("setgetCurrentHeadIndex tests...\n");
		setgetCurrentHeadIndex_Tests(tester);
		tester.addNote("getSize tests...\n");
		getSize_Tests(tester);
		tester.addNote("getAtIndex tests...\n");
		getAtIndex_Tests(tester);
		tester.addNote("remove tests...\n");
		remove_Tests(tester);
	}

	public String getResults() {
		return tester.getResults();
	}

	public boolean hasFailed() {
		return tester.hasFailed();
	}
	
	//constructor tests
	private static void constructor_Tests(UnitTester tester){
		int currentHeadIndex = -1;
		int size = 0;
		StockQueueList stockQueueList = new StockQueueList();
		
		tester.test(stockQueueList.getCurrentHeadIndex() , currentHeadIndex);
		tester.test(stockQueueList.getSize() , size);
		//test that the QueueList attribute isn't null?
	}
	
	private void remove_Tests(UnitTester tester) {
		StockQueueList stockQueueList;

		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		Stock stock3 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		StockQueue temp3 = new StockQueue(name3, stock3);
		
		//Test remove from the beginning
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		stockQueueList.remove(0);
		tester.test(stockQueueList.getSize(), 2);
		tester.test(stockQueueList.getAtIndex(0) == temp1, false);
		
		//Test remove from the middle
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		stockQueueList.remove(1);
		tester.test(stockQueueList.getSize(), 2);
		tester.test(stockQueueList.getAtIndex(1) == temp2, false);
		
		//Test remove from the end
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		stockQueueList.remove(2);
		tester.test(stockQueueList.getSize(), 2);
		tester.test(stockQueueList.getAtIndex(1) == temp3, false);
	}

	private void getAtIndex_Tests(UnitTester tester) {
		StockQueueList stockQueueList;

		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		Stock stock3 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		StockQueue temp3 = new StockQueue(name3, stock3);
		
		//Test getAtIndex
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		
		//From the beginning
		tester.test(stockQueueList.getAtIndex(0) == temp1, true);
		
		//From the middle
		tester.test(stockQueueList.getAtIndex(1) == temp2, true);
		
		//From the end
		tester.test(stockQueueList.getAtIndex(2) == temp3, true);
	}

	private void getSize_Tests(UnitTester tester) {
		StockQueueList stockQueueList;

		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		Stock stock3 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		StockQueue temp3 = new StockQueue(name3, stock3);
		
		//Test getSize initially
		stockQueueList = new StockQueueList();
		tester.test(stockQueueList.getSize(), 0);
		
		//After adding 1
		stockQueueList.add(temp1);
		tester.test(stockQueueList.getSize(), 1);
		
		//After adding several
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		tester.test(stockQueueList.getSize(), 3);
		
		//After removing 1
		stockQueueList.remove(0);
		tester.test(stockQueueList.getSize(), 2);
		
		//After removing several
		stockQueueList.remove(0);
		stockQueueList.remove(0);
		tester.test(stockQueueList.getSize(), 0);
	}

	private void setgetCurrentHeadIndex_Tests(UnitTester tester) {
		StockQueueList stockQueueList;
		int beginning = 0;
		int middle = 1;
		int end = 2;
		
		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		Stock stock3 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		StockQueue temp3 = new StockQueue(name3, stock3);
		
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		
		//set and get it from the beginning
		stockQueueList.setCurrentHeadIndex(beginning);
		tester.test(stockQueueList.getCurrentHeadIndex(), beginning);
		
		//set and get it from the middle
		stockQueueList.setCurrentHeadIndex(middle);
		tester.test(stockQueueList.getCurrentHeadIndex(), middle);
		
		//set and get it at the end
		stockQueueList.setCurrentHeadIndex(end);
		tester.test(stockQueueList.getCurrentHeadIndex(), end);
	}

	private void findIndexOfStockQueue_Tests(UnitTester tester) {
		StockQueueList stockQueueList;
		int beginning = 0;
		int middle = 1;
		int end = 2;
		
		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		Stock stock3 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		StockQueue temp3 = new StockQueue(name3, stock3);
		
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		
		//Find index of stock at beginning
		tester.test(stockQueueList.findIndexOfStockQueue(name1) == beginning, true);
		
		//Find index of stock at middle
		tester.test(stockQueueList.findIndexOfStockQueue(name2) == middle, true);
		
		//Find index of stock at end
		tester.test(stockQueueList.findIndexOfStockQueue(name3) == end, true);
		
		//Return -1 if stock not found with a non-empty set
		stockQueueList.remove(0);
		tester.test(stockQueueList.findIndexOfStockQueue(name1) == -1, true);
		
		//Return -1 if stock not found with an empty set
		stockQueueList.remove(0);
		stockQueueList.remove(0);
		tester.test(stockQueueList.findIndexOfStockQueue(name1) == -1, true);
	}

	private void getStockQueueAtIndexFromHead_Tests(UnitTester tester) {
		StockQueueList stockQueueList;
		int beginning = 0;
		int middle = 1;
		int end = 2;
		
		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		Stock stock3 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		StockQueue temp3 = new StockQueue(name3, stock3);
		
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		
		//getStockQueueAtIndexFromHead from beginning
		tester.test(stockQueueList.getStockQueueAtIndexFromHead(beginning) == temp1, true);
		
		//getStockQueueAtIndexFromHead from middle
		tester.test(stockQueueList.getStockQueueAtIndexFromHead(middle) == temp2, true);
		
		//getStockQueueAtIndexFromHead from end
		tester.test(stockQueueList.getStockQueueAtIndexFromHead(end) == temp3, true);
		
		//getStockQueueAtIndexFromHead from beginning by looping through the list
		tester.test(stockQueueList.getStockQueueAtIndexFromHead(beginning + end) == temp3, true);
	}

	private void shiftStockQueue_Tests(UnitTester tester) {
		StockQueueList stockQueueList;
		int beginning = 0;
		int middle = 1;
		int end = 2;
		
		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		Stock stock3 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		String name3 = "name3";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		StockQueue temp3 = new StockQueue(name3, stock3);
		
		stockQueueList = new StockQueueList();
		stockQueueList.add(temp1);
		stockQueueList.add(temp2);
		stockQueueList.add(temp3);
		
		//Shift by a number greater than size
		stockQueueList.shiftStockQueue(stockQueueList.getSize() + 1);
		tester.test(stockQueueList.getCurrentHeadIndex(), 1);
		
		//Shift by 1
		stockQueueList.shiftStockQueue(1);
		tester.test(stockQueueList.getCurrentHeadIndex(), 2);
		
		//Shift by 0
		stockQueueList.shiftStockQueue(0);
		tester.test(stockQueueList.getCurrentHeadIndex(), 2);
		
		//Shift by -1
		stockQueueList.shiftStockQueue(-1);
		tester.test(stockQueueList.getCurrentHeadIndex(), 1);
		
		//Shift by a number less than -size
		stockQueueList.shiftStockQueue(-(stockQueueList.getSize() + 1));
		tester.test(stockQueueList.getCurrentHeadIndex(), 0);
	}

	private void add_Tests(UnitTester tester) {
		StockQueueList stockQueueList;
		int beginning = 0;
		int middle = 1;
		
		Stock stock1 = new Stock(0, 0);
		Stock stock2 = new Stock(0, 0);
		
		String name1 = "name1";
		String name2 = "name2";
		
		StockQueue temp1 = new StockQueue(name1, stock1);
		StockQueue temp2 = new StockQueue(name2, stock2);
		
		stockQueueList = new StockQueueList();
		
		//Add from an empty list
		stockQueueList.add(temp1);
		tester.test(stockQueueList.getSize(), 1);
		tester.test(stockQueueList.getCurrentHeadIndex(), 0);
		tester.test(stockQueueList.getAtIndex(0) == temp1, true);
		
		//Add from a non-empty list
		stockQueueList.add(temp2);
		tester.test(stockQueueList.getSize(), 2);
		tester.test(stockQueueList.getCurrentHeadIndex(), 0);
		tester.test(stockQueueList.getAtIndex(1) == temp2, true);
		
		//Add from an empty list that was once non-empty after changing the currentHeadIndex
		stockQueueList.shiftStockQueue(1);
		stockQueueList.remove(0);
		stockQueueList.remove(0);
		stockQueueList.add(temp1);
		tester.test(stockQueueList.getSize(), 1);
		tester.test(stockQueueList.getCurrentHeadIndex(), 0);
		stockQueueList.add(temp2);
		tester.test(stockQueueList.getSize(), 2);
		tester.test(stockQueueList.getCurrentHeadIndex(), 0);
		tester.test(stockQueueList.getAtIndex(0) == temp1, true);
		tester.test(stockQueueList.getAtIndex(1) == temp2, true);
	}
}
