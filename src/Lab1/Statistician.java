package Lab1;
/*
 * I don't have the book at the moment so I'll do a simple version to start with
 * based on the class lecture.
 */

public class Statistician {
	double last, min, max, sum; 
	int length;

	public Statistician(){
		sum = 0.0; 
		length = 0;
	}

	//Add a value and update attributes
	public void add(double value){
		sum += value;
		
		if(length == 0){
			min = max = value;
		}
		else{
			if(value < min) min = value;
			else if(value > max) max = value;
		}
		last = value;
		length++;
	}
	
	//return sum
	public double getSum(){
		return sum;
	}
	
	//return length
	public int getLength(){
		return length;
	}
	
	//return min if at least one value has been input or returns an error
	public double getMin(){
		if(length == 0){
			throw new RuntimeException("No values have been added.");
		}
		return min;
	}
	
	//return max if at least one value has been input or returns an error
	public double getMax(){
		if(length == 0){
			throw new RuntimeException("No values have been added.");
		}
		return max;
	}
	
	//return last if at least one value has been input or returns an error
	public double getLast(){
		if(length == 0){
			throw new RuntimeException("No values have been added.");
		}
		return last;
	}
}