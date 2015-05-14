package Lab6;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Timeline {
	private Stack<Day> dayStack; //This looks like overhead
	private int currentDay; //Updates at the start of each day
	private int numberOfDays;
	public Timeline(InputStream dataStream, int numberOfStocks, int numberOfDays){
		currentDay = 0;
		this.numberOfDays = numberOfDays;
	
		Day tempDay;
		String[] stockNameList;
		double[] stockPriceList;
		int currentDay;
		Scanner dataScanner;
		String dataBlob;
		String dataLine;
		
		//Used to interpret and get input
		Pattern stockNamePattern;
		Pattern stockPricePattern;
		Matcher stockNameMatcher;
		Matcher stockPriceMatcher;
		
		//Copy the string from the dataStream
		dataBlob = convertStreamToString(dataStream);
		
		//Try to close the data stream
		try {
			dataStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Set a scanner to the data
		dataScanner = new Scanner(dataBlob);

		//Create a list of stock names
		stockNameList = new String[numberOfStocks];
		
		//Prepare the stockNamePattern
		stockNamePattern = Pattern.compile("^\\(([A-Z-]+)\\) ");
		
		//Fill the stockNameList by getting input from dataScanner
		for(int i = 0; i < numberOfStocks; i++){
			//Get the next line from the scanner
			dataLine = dataScanner.nextLine();
			
			//Set the stockNameMatcher to the dataLine
			stockNameMatcher = stockNamePattern.matcher(dataLine);
			
			//Find and add the stock name  to stockNameList
			stockNameMatcher.find(); //Should I throw an error if it doesn't work???
			stockNameList[i] =  stockNameMatcher.group(1);
		}
		
		//Get the stock prices for the current day
		for(currentDay = numberOfDays; currentDay > 0; currentDay--){
			//Reset the dataScanner
			dataScanner = new Scanner(dataBlob);
			
			//Create a new array for the stock prices of the current day
			stockPriceList = new double[numberOfDays];
			
			//update the pattern to find the price of stocks for the current day
			stockPricePattern = Pattern.compile("\\(" + currentDay + "\\)<([0-9]*\\.?[0-9]+)>");
			
			//Add each price to the array for the current day
			for(int i = 0; i < numberOfStocks; i++){
				//Get the next line from the scanner
				dataLine = dataScanner.nextLine();
				
				//Set the stockPriceMatcher to the dataLine
				stockPriceMatcher = stockPricePattern.matcher(dataLine);
				
				//Find and add the stock price to stockPriceList
				stockPriceMatcher.find(); //Should I throw an error if it doesn't work???
				stockPriceList[i] =  Double.parseDouble(stockPriceMatcher.group(1));
			}
			//Create a day for the current day
			tempDay = new Day(stockNameList, stockPriceList, numberOfStocks);
			
			//Push the current day onto the stack
			dayStack.push(tempDay);
		}
		//End that resource leak..
		dataScanner.close();
	}
	
	//http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
	//http://stackoverflow.com/users/43151/pavel-repin
	private String convertStreamToString(java.io.InputStream is) {
	    @SuppressWarnings("resource") //What?
		Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
	public boolean hasNextDay(){
		return dayStack.isEmpty();
	}
	
	public Day getNextDay(){
		return dayStack.pop();
	}
	
	public int getCurrentDay(){
		return currentDay;
	}
	
	public int getNumberOfDays(){
		return numberOfDays;
	}
	
}
