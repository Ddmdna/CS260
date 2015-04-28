package Lab2;
/*
 * I plan to use this to simplify testing...
 *
 * Should I accept values as objects or individual data types?
 */
public class UnitTester {
	private double tolerance;
	private String errorMessage;
	private int testCount;
	private boolean error;
	UnitTester(){
		tolerance = 0.0;
		errorMessage = new String("");
		testCount = 0;
		error = false;
	}

	public void setTolerance(double tolerance){
		this.tolerance = tolerance;
	}

	public boolean getError(){
		return error;
	}

	public void test(boolean actualValue, boolean expectedValue){
		testCount++;
		if(actualValue != expectedValue){
			errorMessage = errorMessage.concat("Test: (" + testCount + ") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
			error = true;
		}
	}

	public void test(char actualValue, char expectedValue){
		testCount++;
		if(actualValue != expectedValue){
			errorMessage = errorMessage.concat("Test: (" + testCount + ") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
			error = true;
		}
	}

	public void test(int actualValue, int expectedValue){
		testCount++;
		if(actualValue != expectedValue){
			errorMessage = errorMessage.concat("Test: (" + testCount + ") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
			error = true;
		}
	}

	public void test(float actualValue, float expectedValue){
		testCount++;
		if(actualValue > (expectedValue + tolerance) || actualValue < (expectedValue - tolerance)){
			errorMessage = errorMessage.concat("Test: (" + testCount + ") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
			error = true;
		}
	}

	public void test(double actualValue, double expectedValue){
		testCount++;
		if(actualValue > (expectedValue + tolerance) || actualValue < (expectedValue - tolerance)){
			errorMessage = errorMessage.concat("Test: (" + testCount + ") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
			error = true;
		}
	}

	public String toString(){
		return errorMessage;
	}
}