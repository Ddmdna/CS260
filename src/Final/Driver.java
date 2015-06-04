package Final;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Final project components:
 * Code properties: 
 * 	-readable
 * 	-maintainable
 * 	-validation
 * 	-functional decomposition
 * 	-reusable
 * 	-automation
 * 	-source decomposition
 * Features: 
 * 	-Problem description
 * 	-Design (including ADT being used, plan for structure, and any variations used)
 * 	-Tests (on each method and each important aspect of a structure)
 * 	-working solution.
*/

public class Driver {
	public static void main(String[] args)
	{
		

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
