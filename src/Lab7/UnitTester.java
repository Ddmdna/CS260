package Lab7;
/*
 * I plan to use this to simplify testing...
 * 
 * Should I accept values as objects or individual data types?
 */
public class UnitTester {
	private double tolerance;
	private String resultMessage;
	private int testCount;
	private boolean hasError;
	UnitTester(){
		tolerance = 0.1;
		resultMessage = new String("");
		testCount = 0;
		hasError = false;
	}
	
	public void setTolerance(double tolerance){
		this.tolerance = tolerance;
	}
	
	public boolean hasFailed(){
		return hasError;
	}
	
	public void addNote(String note){
		resultMessage+= note;
	}
	
	public void test(boolean actualValue, boolean expectedValue){
		testCount++;
		
		resultMessage = resultMessage.concat("Test: (" + testCount + 
		") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
		
		if(actualValue != expectedValue){
			hasError = true;
		}
	}
	
	public void test(char actualValue, char expectedValue){
		testCount++;
		
		resultMessage = resultMessage.concat("Test: (" + testCount + 
		") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
		
		if(actualValue != expectedValue){
			hasError = true;
		}
	}
	
	
	public void test(int actualValue, int expectedValue){
		testCount++;
		
		resultMessage = resultMessage.concat("Test: (" + testCount + 
		") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
		
		if(actualValue != expectedValue){
			hasError = true;
		}
	}
	
	public void test(float actualValue, float expectedValue){
		testCount++;
		
		resultMessage = resultMessage.concat("Test: (" + testCount + 
		") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
		
		if(actualValue > (expectedValue + tolerance) || actualValue < (expectedValue - tolerance)){
			hasError = true;
		}
	}
	
	public void test(double actualValue, double expectedValue){
		testCount++;
		
		resultMessage = resultMessage.concat("Test: (" + testCount + 
		") Expected: (" + expectedValue + ") Actual: (" + actualValue + ")\n");
		
		if(actualValue > (expectedValue + tolerance) || actualValue < (expectedValue - tolerance)){
			hasError = true;
		}
	}
	
	public String getResults(){
		return resultMessage;
	}
	public String toString(){
		return resultMessage;
	}
}
