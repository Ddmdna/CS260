package Lab2;
/*
 * CS260 Lab 2
 * Drew Hamm
 * Driver program to work with my DoubleArraySortedSeq class
 * 
 * First, Test each of the methods for the DoubleArraySortedSeq class
 * Next, add a method to search the list for a specific value
 * Finally, test the new method
 */
public class DoubleArraySortedSeqDriver {
	public static void main(String[] args)
	{
		//Test each of the methods for the DoubleArraySortedSeq class
		if(unitTest()) throw new RuntimeException("Error in unitTest()");
				
	}

	//Tests each method of the class Statistician
	public static boolean unitTest(){
		boolean error = false;

		UnitTester tester = new UnitTester();
		tester.setTolerance(.01);
		//(Tolerance == 0.01) How much tolerance do I really need?
		
		
		//Test DoubleArraySortedSeq()
		//Test size()
		//Test getCapacity()
		DoubleArraySortedSeq myArray1 = new DoubleArraySortedSeq();
		
		tester.test(myArray1.size(), 0);
		tester.test(myArray1.getCapacity(), 10);
		
		//Test DoubleArraySortedSeq(int initialCapacity)
		myArray1 = new DoubleArraySortedSeq(7);
		
		tester.test(myArray1.size(), 0);
		tester.test(myArray1.getCapacity(), 7);
		
		
		//Test isCurrent()
		tester.test(myArray1.isCurrent(), false);
		
		//Test isCurrent()
		//Test add(double element)
		//Test getCurrent()
		myArray1.add(3.4);
		myArray1.add(-12.5);
		myArray1.add(1.6);
		myArray1.add(57.8);
		myArray1.add(-2009.3);
		myArray1.add(1.11);
		myArray1.add(7.8);
		myArray1.add(-9.3);
		myArray1.add(13.11);
		
		tester.test(myArray1.isCurrent(), true);
		tester.test(myArray1.size(), 9);
		tester.test(myArray1.getCapacity(), 14);
		tester.test(myArray1.getCurrent(), 13.11);
		
		//Test addAll(DoubleArraySortedSeq merge)
		DoubleArraySortedSeq myArray2 = new DoubleArraySortedSeq(3);
		myArray2.add(1.2);
		myArray2.add(10.4);
		myArray2.add(100.6);
		myArray1.addAll(myArray2);
		
		tester.test(myArray1.size(), 12);
		tester.test(myArray1.getCapacity(), 14);
		tester.test(myArray1.getCurrent(), 100.6);
		
		//Test start()
		//Test removeCurrent()
		myArray1.start();
		myArray1.removeCurrent();
		
		tester.test(myArray1.getCurrent(), -12.5);
		
		//Test advance()
		myArray1.advance();
		
		tester.test(myArray1.getCurrent(), -9.3);
		
		//Test clone()
		myArray2 = myArray1.clone();
		myArray2.add(-100.9);
		myArray2.add(-67.9);
		myArray2.add(-77.9);
		
		tester.test(myArray2.size() - myArray1.size(), 3);
		tester.test(myArray2.getCapacity() == myArray1.getCapacity(), true);
		tester.test(myArray2.getCurrent(), -77.9);
		tester.test(myArray1.getCurrent(), -9.3);
		
		//Test catenation(DoubleArraySortedSeq s1, DoubleArraySortedSeq s2)
		myArray1 = myArray1.catenation(myArray1, myArray2);
		myArray1.start();
		
		tester.test(myArray1.getCurrent(), -100.9);
		
		//Test ensureCapacity(int minimumCapacity)
		myArray1.ensureCapacity(50);
		
		tester.test(myArray1.getCapacity() >= 50, true);
		
		//Test trimToSize()
		myArray1.trimToSize();
		
		tester.test(myArray1.getCapacity(), myArray1.size());
		
		//Test search(double value)
		tester.test(myArray1.search(-77.9), true);
		tester.test(myArray1.search(-87.9), false);
		
		//Print test results
		if(error = tester.getError()){
			System.out.print("Errors Found\n");
		}
		else
		{
			System.out.print("No Errors Found\n");
			myArray1.start();
			
			System.out.println("The current DoubleArraySortedSeq object: (myArray1)");
			System.out.println("Size: " + myArray1.size());
			System.out.println("Capacity: " + myArray1.getCapacity());
			for(int i = 0; i < myArray1.size(); i++)
			{
				System.out.println("Index (" + i + "): " + myArray1.getCurrent());
				myArray1.advance();
			}
		}
		
		System.out.print(tester);
		
		return error;
	}
}