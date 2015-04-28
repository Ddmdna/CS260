package Lab1;
/*
 * Name: Drew Hamm
 * Class: CS260 Spring 2015
 * Date: 4/6/2015
 * Program Name: StatisticianDriver.java
 * Program Description: Driver program to work with my Statistician class.
 * Pseudocode:
 * 	1. Test each of the methods for the Statistician class.
 * 	2. Add 10 objects
 * 	3. Call each of the methods belonging to the Statistician class. 
 * 	4. Add another 10 objects
 * 	5. Again call each of the methods belonging to the Statistician class. 
 */

public class StatisticianDriver {
	public static void main(String[] args)
	{
		//First, run tests for each method of the Statistician class
		if(unitTest()) throw new RuntimeException("Error in unitTest()");
		
		//Create a class object then proceed to meet lab requirements
		Statistician stat = new Statistician();
		
		//Add ten values
		stat.add(1.1);
		stat.add(-2.2);
		stat.add(3.3);
		stat.add(-4.4);
		stat.add(0.0);
		stat.add(999.9);
		stat.add(-999.9);
		stat.add(50.5);
		stat.add(.001);
		stat.add(-.001);
		
		//Print all values from the Statistician class
		System.out.println("Sum: " + stat.getSum());
		System.out.println("Length: " + stat.getLength());
		System.out.println("Min: " + stat.getMin());
		System.out.println("Max: " + stat.getMax());
	
		System.out.println("");
		
		//Add ten more values
		stat.add(.55);
		stat.add(5.8);
		stat.add(-20.0);
		stat.add(78.0);
		stat.add(-2998.0);
		stat.add(3333.333);
		stat.add(1.0);
		stat.add(2.0);
		stat.add(.00801);
		stat.add(-.88889);
		
		//Print all values from the Statistician class
		System.out.println("Sum: " + stat.getSum());
		System.out.println("Length: " + stat.getLength());
		System.out.println("Min: " + stat.getMin());
		System.out.println("Max: " + stat.getMax());
		
	}

	//Tests each method of the class Statistician
	public static boolean unitTest(){
		boolean error = false;
		
		UnitTester tester = new UnitTester();
		tester.setTolerance(.1); //How much tolerance do I really need?
		
		Statistician stat = new Statistician();
		
		//Test add() and getSum()
		stat.add(7.7);
		stat.add(-1.237);
		tester.test(stat.getSum(), 6.463);
		
		//Test getLength()
		tester.test(stat.getLength(), 2);
		
		//Test getMin()
		tester.test(stat.getMin(), -1.237);
		
		//Test getMax()
		tester.test(stat.getMax(), 7.7);
		
		//Test getLast()
		tester.test(stat.getLast(), -1.237);
		
		if(error = tester.getError()){
			System.out.print(tester);
		}
		return error;
	}
}