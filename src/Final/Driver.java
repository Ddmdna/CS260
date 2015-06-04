package Final;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/*
 * 
*/
/******************************************************************************
* A <CODE>Driver</CODE> is my implementation to solve the final requirements for CS 260
* @author Drew Hamm
* @version
*   June 8, 2015
******************************************************************************/
public class Driver {
	/**
	* Pseudocode:
	* 1) create a hash table with capacity of 51 to store person objects.
	* 2) open the data file.
	* 3) read from the file the data needed to create person objects.
	* 4) create person objects from file data and add them to the hash table.
	* 5) print each occurrence of collision when adding person objects.
	* 6) print the entire hash table.
	* 7) search for several objects that are in the table.
	* 8) search for several objects not in the table.
	**/
	public static void main(String[] args)
	{
		//Run tests
		Boolean failed = false;
		System.out.println("Running tests:");
		PersonTester personTests = new PersonTester();
		System.out.println(personTests);
		if(personTests.hasFailed()){
			failed = true;
			System.out.println("Error occured in PersonTester");
		}
		TableTester tableTests = new TableTester();
		System.out.println(tableTests);
		if(tableTests.hasFailed()){
			failed = true;
			System.out.println("Error occured in TableTester");
		}
		if(failed){
			System.out.println("Tests unsuccessful\n");
		}
		else{
			System.out.println("Tests successful\n");
		}
		
		//The classes we will be working with
		Vector<Person> people = new Vector<Person>(30);
		Table<Integer, Person> table = new Table<Integer, Person>(51);
		
		//File with starting people data
		String testFileName = "TestPersonData";
		
		//Create a scanner with access to the test file
		Scanner scan;
		scan = getScannerToFileContentString(testFileName);
		
		//If the file was successfully opened
		//Create a new Person object from the formatted file data and add it to people
		if(scan != null){
			while(scan.hasNext()){
				people.add(new Person(scan.nextInt(), scan.next(), scan.next(), scan.nextInt()));
			}
		}
		
		//Put each person into table
		for(Person person : people){
			table.put(person.getID().hashCode(), person);
		}
		
		//Output the table contents
		System.out.println("After reading from the file, the table contains the following:");
		System.out.println(table);
		
		//Search the table for several objects that may or may not be in the table
		//Valid id:   (1001 - 1030)
		//Invalid id: (1031 - )
		//Above known from test file...
		System.out.println("\nNow lets try searching for varius IDs...");
		for(Integer id = 1025; id < 1035; id++) //just a random amount of checks
		{
			Person tempPerson;
			System.out.println("Searching for a person with id: " + id);
			tempPerson = table.get(id.hashCode());
			
			if(tempPerson!= null){
				System.out.println("Found: (" + tempPerson + ")");
			}
			else{
				System.out.println("Person not found...");
			}
		}
	}

	/**
	* From http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file* 
	* Gets a scanner to a string created from the contents of a file
	* @param fileName
	*   the non-null name of a valid file
	* <dt><b>Precondition:</b><dd>
	*   fileName must be non-null and valid
	* @return
	* 	A scanner to a string containing the contents of a file.
	* 	Or a Null scanner
	* @exception FileNotFoundException
	*   Indicates that the file was not found
	* @exception IOException
	*   Indicates that an io exception occurred
	**/
	public static Scanner getScannerToFileContentString(String fileName){
		Scanner scan = null;
		
		//Uses the try-with-resources Statement
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        String content = sb.toString();
	        scan = new Scanner(content);
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scan;
	}
}
