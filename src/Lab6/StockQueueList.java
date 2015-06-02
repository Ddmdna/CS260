package Lab6;

import java.util.Iterator;
import java.util.LinkedList;

public class StockQueueList {
	private LinkedList<StockQueue> queueList;
	private int currentHeadIndex;
	private int size;
	
	public StockQueueList(){
		currentHeadIndex = -1;
		size = 0;
		queueList = new LinkedList<StockQueue>();
	}
	
	public void add(StockQueue stockQueue){
		if(size == 0) 
			currentHeadIndex = 0;
		size++;
		queueList.add(stockQueue);
	}
	
	
	//move head left or right
	//Requires size to be greater than 0
	public boolean shiftStockQueue(int amount){
		boolean result = true;
		if(size == 0)
			result = false;
		else
		{
			//head will be from 0 to size - 1
			//how to mod with negatives...
			while(amount != 0)
			{
				if(amount > 0)
				{
					currentHeadIndex++;
					amount--;
					if(currentHeadIndex == size)
						currentHeadIndex = 0;
				}else
				{
					currentHeadIndex--;
					amount++;
					if(currentHeadIndex == -1)
						currentHeadIndex = size - 1;
				}
			}
		}
		return result;
	}
	
	//getStockQueueAtIndexFromHead
	//requires size to be greater than 0
	//Requires index to be less or equal to size
	//precondition queueList is not empty
	public StockQueue getStockQueueAtIndexFromHead(int offset){
		if(size == 0) throw new IllegalStateException("Can't get a stockQueue when size is 0");
		StockQueue stockQueue = queueList.get((currentHeadIndex + offset) % size);
		return stockQueue;
	}
	
	//Search for stock
	//return a -1 if stockQueue was not found
	public int findIndexOfStockQueue(String stockName){
		int index = -1;
		int count = 0;
		for(Iterator<StockQueue> i = queueList.iterator(); i.hasNext(); count++) 
		{
		    StockQueue item = i.next();
		    if(item.getStockName().compareTo(stockName) == 0)
		    	index = count;
		}
		return index;
	}
	
	//returns the index 
	public int getCurrentHeadIndex(){
		return currentHeadIndex;
	}
	
	//set the index
	//Post condition is an if
	//If I enforce the precondition then the post wont be an if
	public void setCurrentHeadIndex(int index){
		if(index >= 0 && index < size)
			currentHeadIndex = index;
	}
	
	public int getSize(){
		return size;
	}

	public StockQueue getAtIndex(int index) {
		return queueList.get(index);
	}
	
	public void remove(int index){
		queueList.remove(index);
		size--;
	}
}
