package Lab4;
/*
 * Name: Drew Hamm
 * Class: CS260 Spring 2015
 * Date: 4/27/2015
 * Program Name: Driver.java
 * Program Description: Driver program to work with my ArrayBag class.
 * Pseudocode:
 *  1. Create the bag of strings to hold individual chores
 * 	1. Open ChoreList.txt
 * 	2. Read chores from a file(line by line), and add them to the chores array bag
 * 	3. Print the list of chores
 * 	4. Loop until sentinel value of 0 is input
 * 		4a. Present user with option menu
 * 			(1) add an item to the list of chores; 
 * 			(2) ask how many chores are in the list; 
 * 			(3) print the list of chores to the screen; 
 * 			(4) delete an item form the list; 
 * 			(0) exit the program.
 * 		4b. Get user input
 * 		4c. Go to menu method
 *  5. Save the chores currently in the ArrayBag to ChoreList.txt
 * 	?. Test each method of my ArrayBag class
 * 
 * At the moment my JUnit tests need to be ran separately.
 * I've been looking into using ANT build through eclipse but I havn't set that up yet
 * Using ANT seems to automate the tests and should let me output results at the start
 * of the application. Not sure what best practices are...
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Driver {
	public static void main(String[] args)
	{
		Result result;
		System.out.println("Starting Tests...");
		
		//Run through DriverTest tests and note the success
		result = JUnitCore.runClasses(DriverTest.class);
	    for (Failure fail : result.getFailures()) {
	        System.out.println(fail.toString());
	    }
	    if (result.wasSuccessful()) {
	        System.out.println("All tests finished successfully...");
	    }
		
	  //Run through ArrayBagTest tests and note the success
	    result = JUnitCore.runClasses(ArrayBagTest.class);
	    for (Failure fail : result.getFailures()) {
	        System.out.println(fail.toString());
	    }
	    if (result.wasSuccessful()) {
	        System.out.println("All tests finished successfully...");
	    }
	    System.out.println("Tests Finished\n");
		//File to be accessed
		final String CHORE_LIST_FILE_NAME = "ChoreList.txt";
		
		//Create the bag of strings to hold individual chores
		ArrayBag<String> chores = new ArrayBag<String>();
		
		//Read chores from a file(line by line), and add them to the chores array bag
		loadBagFromFile(chores, CHORE_LIST_FILE_NAME);
		
		//Print the list of chores
		printArrayBag(chores);
		
		//Menu
		char input = 'z';
		while(input != '0')
		{
			//Present user with option menu that asks for input
			outputMenu();
			
			//Get user input
			input = getChar(System.in);
			
			//Should I include a test around this to check that the switch is working correctly?
			//Go to menu method
			switch(input){
				case '1': //lets user add a chore to the bad by entering a string
					System.out.println("Please input a chore to be added...");
					chores.add(getString(System.in));
					break;
				case '2': //Prints the current number of chores in the bag
					System.out.println("There are (" + chores.size() + ") chores in the list.");
					break;
				case '3': //Prints each chore in the bag
					printArrayBag(chores);
					break;
				case '4': //Lets user remove a chore by entering a matching string
					System.out.println("Please input the chore to be removed...");
					chores.remove(getString(System.in));
					break;
				case '0': //Just ends the loop
			}
		}
		
		//Save the chores currently in the ArrayBag to ChoreList.txt
		saveBagToFile(chores, CHORE_LIST_FILE_NAME);
		
		//Exit message
		System.out.println("End of program");
		
		//Exit: Not really sure if this is necessary... 
		System.exit(0);
	}


	/**
	* Print the contents of this bag
	 * @param <E>
	* @param bag
	*   a bag whose contents will be printed
	* @precondition
	*   The parameter, bag, is not null.
	* @exception NullPointerException
	*   Indicates that bag is null. 
	**/
	public static <E> void printArrayBag(ArrayBag<E> bag){
		//Check precondition
		if(bag==null) throw new NullPointerException("bag was null");
		
		//Loop through the bag and print the data for each element
		Iterator<E> it = bag.iterator();
        while(it.hasNext()) {
            Object cur = it.next();
            System.out.println(cur);
        }
	}
	
	/**
	* Open a file, read each line and add each read line to bag
	* @param bag
	*   A bag that will have Strings added to it
	*  @param fileName
	*   The name of a file that contains Strings that describe chores
	* @precondition
	*   The parameter, bag, is not null.
	* @precondition
	*   The parameter, fileName, is not null.
	* @exception NullPointerException
	*   Indicates that bag is null. 
	* @exception FileNotFoundException
	*   Indicates that we could not open the file
	* @note
	* 	Do I need ArrayBag<String> vs ArrayBag<Object>. 
	* 	I think I'm forced to use <String> in this implementation
	**/
	public static void loadBagFromFile(ArrayBag<String> bag, String fileName){
		//Check preconditions
		if(bag==null) throw new NullPointerException("bag was null");
		if(fileName==null) throw new NullPointerException("fileName was null");
		
		//Load the file and add each line as a chore for bag
		Scanner fileScan;
		try 
		{
			fileScan = new Scanner(new File(fileName));
			while(fileScan.hasNext()){
				bag.add(fileScan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Prints the menu options and asks for input
	**/
	private static void outputMenu() {
		System.out.println("The following options are avalable:");
		System.out.println("(1) Add an item to the list of chores");
		System.out.println("(2) Print the total number of chores that are in the list.");
		System.out.println("(3) Print the list of chores to the screen.");
		System.out.println("(4) Delete an item from the list.");
		System.out.println("(0) Exit the program.");
		System.out.println("Please enter a character...");
	}
	
	/**
	* Accepts input from user and returns the first character
	* @param in
	*   InputStream used for Scanner
	* @precondition
	*   The parameter, in, is not null.
	* @return
	* 	a char is returned that is the first character of input
	* 	' ' is returned if no input is given
	* @note
	* 	Not sure what happens if user input is too large. Probably still works...
	**/
	private static char getChar(InputStream in) {
		//Check preconditions
		if(in==null) throw new NullPointerException("InputStream in, is null");
				
		Scanner scan = new Scanner(in);
		String temp = scan.nextLine();
		char c;
		if(temp.length() != 0)
			c = temp.charAt(0);
		else
			c = ' ';
		return c;
	}
	
	/**
	* Accepts input from user and returns a string
	* @param in
	*   InputStream used for Scanner
	* @precondition
	*   The parameter, in, is not null.
	* @return
	* 	A String that was input by the user
	**/
	private static String getString(InputStream in) {
		//Check preconditions
		if(in==null) throw new NullPointerException("InputStream in, is null");
		
		Scanner scan = new Scanner(in);
		String s = scan.nextLine();
		return s;
	}
	
	/**
	* Open a file and write each chore from the bag
	* @param bag
	*   A bag that will have Strings read from it
	* @param fileName
	*   The name of a file that will have Strings written to it
	* @precondition
	*   The parameter, bag, is not null.
	* @precondition
	*   The parameter, fileName, is not null.
	* @exception NullPointerException
	*   Indicates that bag is null. 
	* @exception FileNotFoundException
	*   Indicates that we could not open the file
	**/
	private static void saveBagToFile(ArrayBag<String> bag, String fileName)
	{
		if(bag==null) throw new NullPointerException("bag was null");
		if(fileName==null) throw new NullPointerException("fileName was null");
		
		PrintWriter writer;
		try {
			//load the file to be written to
			writer = new PrintWriter(fileName, "UTF-8");
			
			//Write each bag string to the file
			Iterator<String> it = bag.iterator();
	        while(it.hasNext()) {
	            Object cur = it.next();
	            writer.println(cur);
	        }
	        //Close the file
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
