package Lab5;

import java.util.Scanner;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/******************************************************************************
* Name: Drew Hamm
* Class: CS260 Spring 2015
* Date: 5/8/2015
* Program Name: Driver.java
* Program Description: Driver program to convert user input infix expressions to postfix expressions
*
* @note
* 	Pseudocode:
* 	1. Create a SimpleExpression object
* 	2. Loop until user decides to quit inputting expressions
* 	3. Ask user for input
* 	4. If input does not equals exit condition
* 		a. Give input to SimpleExpression object
*	 		1. Evaluate expression
* 				a. If valid
* 					1. Convert to postfix and output
* 					2. If the expressions does not contain variables
* 						a. Solve and output results
* 				b. If not valid
* 					1. Tell user that the expression was not valid
* 	5. Exit the program
*  
* 	At the moment my JUnit tests need to be ran separately.
* 	I've been looking into using ANT build through eclipse but I havn't set that up yet
* 	Using ANT seems to automate the tests and should let me output results at the start
* 	of the application. Not sure what best practices are...
*
* @author Drew Hamm 
* 	<A HREF="mailto:hammdre@gmail.com"> (hammdre@gmail.com) </A>
*
* @version
*   May 8, 2015
******************************************************************************/

public class Driver {
	public static void main(String[] args)
	{	
		//Start message
		System.out.println("The program will attempt to meet the requirements of Lab5.");
		
		
		Result result;
		System.out.println("Starting Tests");
		
		//Run through DriverTest tests and note the success
		JUnitCore core = new JUnitCore();
		myRunListener runListener = new myRunListener();
		core.addListener(runListener);
		result = core.run(SimpleExpressionTest.class);
	    for (Failure fail : result.getFailures()) {
	        System.out.println(fail.toString());
	    }
	    if (result.wasSuccessful()) {
	        System.out.println("All tests were successful");
	    }
	    System.out.println("Testing Finished\n");
		
		
		//Create a SimpleExpression object
		SimpleExpression expression = new SimpleExpression();
		
		//Create a Scanner object and a String object to get input
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		String quit = "0";
		//Loop until user decides to quit inputting expressions
		while(!input.equals(quit)) //Need to double check with \n and \r
		{
			//Ask the user to input a valid arithmetic expression in infix notation. 
			System.out.println("Input a valid arithmetic expression seperated by spaces.");
			System.out.println("Input \"0\" to quit.");
			
			//Get input from user and check that it does not equal the exit condition
			input = scan.nextLine();
			
			//If input was null set it to a space
			if(input == null)
				input = " ";
			
			if(!input.equals(quit))  //This looks like bad practice since I'm checking at while() anyways...
			//If input does not equals exit condition
			{
				//Give input to SimpleExpression object
				expression.setInfixExpression(input); // Might just need to change this to accept without extra space...
				
				//Ensure the correct format by making necessary corrections
				//A white space at the end of the input
				//One and only one whitespace between elements
				expression.ensureFormat();
				
				//Try to convert to postfix
				expression.convertInfixToPostfix();
				
				//If the expression entered is well formed
				if(expression.isValid())
				{
					//Output converted expression(should be in postfix now)
					System.out.println("\nSucessfully converted from infix to postix");
					
					//If the expressions does not contain variables
					if(!expression.hasVariables())
					{
						//Solve the expression and output the result
						System.out.println(expression + "= " + expression.solvePostFix());
					}
					else
					{
						System.out.println(expression);
					}
					System.out.println();
				//If the expression entered is not well formed
				}else
				{
					//Issue an error message telling the user that their input is in error
					System.out.println("Error when trying to convert from infix to postfix.");
					System.out.println("Check your input and try again.");
				}
			}
		}
		//Exit message
		System.out.println("End of program");
				
		//Exit: Not really sure if this is necessary... 
		System.exit(0);
	}
	
	
}

class myRunListener extends RunListener{
	/**
	 * Called before any tests have been run.
	 **/
	public void testRunStarted(Description description)	throws java.lang.Exception
	{
		System.out.println("Number of testcases to execute : " + description.testCount());
	}

	/**
	 *  Called when all tests have finished
	 **/
	public void testRunFinished(Result result) throws java.lang.Exception
	{
		System.out.println("Number of testcases executed : " + result.getRunCount());
	}

	/**
	 *  Called when an atomic test is about to be started.
	 **/
	public void testStarted(Description description) throws java.lang.Exception
	{
		System.out.println("Starting execution of test case : "+ description.getMethodName());
	}

	/**
	 *  Called when an atomic test has finished, whether the test succeeds or fails.
	 **/
	public void testFinished(Description description) throws java.lang.Exception
	{
		System.out.println("Finished execution of test case : "+ description.getMethodName());
	}

}
