/*
 * Write a program that uses a bag of strings to keep track of a list of chores you have to accomplish today. 
 * The user of the program can request several services: (1) add an item to the list of chores ; (2) ask 
 * how many chores are in the list; (3) print the list of chores to the screen; (4) delete an item form the list; 
 * (5) exit the program. If you know how to read and write strings form a file, then have the program obtain 
 * its initial list of chores from a file. When the program ends, it should write all unfinished chores back 
 * to this file.
 */
/*
 * 1) Create a file that contains a list of at least 6 different chores.
 * 2) Your driver program should read all of the chores from the file, put
 * the chores into a bag of strings and then print the list of chores.
 * 3) Present the user with a menu of options to choose from as listed in the
 * textbook: Add a chore, ask how many chores in list, print list of
 * chores, delete an item from the list and exit program.
 * 4) Your program should continue presenting the menu until the user
 * chooses to quit.
 * 5) Once the user chooses to quit, write the remaining list of chores to the
 * file
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args)
	{
		final String CHORE_LIST_FILE_NAME = "ChoreList.txt";
		
		
		ArrayBag<String> chores = new ArrayBag<String>();
		
		
		//Read chores from a file and add them to the chores array bag
		//Should I check to see that the files are of the correct format?
		Scanner fileScan;
		try 
			{
			fileScan = new Scanner(new File(CHORE_LIST_FILE_NAME));
			while(fileScan.hasNext()){
				chores.add(fileScan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
