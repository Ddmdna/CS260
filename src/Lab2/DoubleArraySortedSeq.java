package Lab2;
// File: DoubleArraySortedSeq.java

//Questions:
//How to check if I have enough memory to create a new array in java?
//Basically, how would I detect that an OutOfMemoryError exception will occur?
//Try-catch?

public class DoubleArraySortedSeq implements Cloneable
{
   // Invariant of the DoubleArraySeq class:
   //   1. The number of elements in the sequences is in the instance variable 
   //      manyItems.
   //   2. For an empty sequence (with no elements), we do not care what is 
   //      stored in any of data; for a non-empty sequence, the elements of the
   //      sequence are stored in data[0] through data[manyItems-1], and we
   //      don’t care what’s in the rest of data.
   //   3. If there is a current element, then it lies in data[currentIndex];
   //      if there is no current element, then currentIndex equals manyItems. 
   private double[] data;
   private int manyItems;
   private int currentIndex; 
   
   /**
   * Initialize an empty sequence with an initial capacity of 10.  Note that the add
   * method works efficiently (without needing more memory) until this capacity is reached.
   * @param - none
   * @postcondition
   *   This sequence is empty and has an initial capacity of 10.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[10].
   **/   
   public DoubleArraySortedSeq()
   {
	   data = new double[10];
	   currentIndex = 0;
	   manyItems = 0;
	   
	   //Postconditions
	   assert manyItems == 0 : "Postcondition: manyItems == 0";
	   assert data.length == 10 : "Postcondition: data.length == 10"; 
   }
     

   /**
   * Initialize an empty sequence with a specified initial capacity. Note that the add
   * method works efficiently (without needing more memory) until this capacity is reached.
   * @param initialCapacity
   *   the initial capacity of this sequence
   * @precondition
   *   initialCapacity is non-negative.
   * @postcondition
   *   This sequence is empty and has the given initial capacity.
   * @exception IllegalArgumentException
   *   Indicates that initialCapacity is negative.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[initialCapacity].
   **/   
   public DoubleArraySortedSeq(int initialCapacity)
   {
	   //Precondition
	   if(initialCapacity <= 0)
	   {
		   throw new IllegalArgumentException("Precondition: initialCapacity > 0");
	   }
	   
	   data = new double[initialCapacity];
	   currentIndex = 0;
	   manyItems = 0;
	   
	   //Postconditions
	   assert manyItems == 0 : "Postcondition: manyItems == 0";
	   assert data.length > 0 : "Postcondition: data.length > 0";
   }
        
 
   /**
   * Add a new element to this sequence, after the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there were
   *   other elements, then the new element is placed such that the elements are
   *   ordered from smallest to largest.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void add(double element)
   {
	   //Make more space if needed
	   if(data.length == manyItems)
	   {
		  double[] largerData = new double[data.length * 2];
		  for(int i = 0; i < data.length; i++)
		  {
			  largerData[i] = data[i];
		  }
		  data = largerData;
	   } 
		   
	   //search for the correct index to insert a new element
	   //The index should be the first location where the stored
	   //element is greater than the new element
	   int index = manyItems;
	   for(int i = 0; i < manyItems; i++)
	   {
		   if(element < data[i])
			   {
			   		index = i;
			   		break;
			   }
	   }
	   
	   //Move all elements over one
	   for(int i = manyItems; i > index; i--)
	   {
		   data[i] = data[i-1];
	   }
	   
	   //Add the new element into the correct spot
	   data[index] = element;
	   
	   //Update current index
	   currentIndex = index;
	   
	   //Update element count
	   manyItems++;

	   //Postconditions
	   //Should I assert the postcondition that the list is sorted with a method?
	   //How to assert that I've added the new element?
   }
   
   
   /**
   * Place the contents of another sequence into this sequence.
   * @param merge
   *   a sequence whose contents will be merged into this sequence
   * @precondition
   *   The parameter, merge, is not null. 
   * @postcondition
   *   The elements from merge have been placed into
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the merge is also unchanged.
   * @exception NullPointerException
   *   Indicates that merge is null. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this sequence.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/
   public void addAll(DoubleArraySortedSeq merge)
   {
	   //Precondition
	   if(merge == null)
	   {
			throw new NullPointerException("Precondition: merge != null");
	   }
	   
	   for(int i = 0; i < merge.manyItems; i++)
	   {
		   this.add(merge.data[i]);
	   }
	   
	   //Postconditions
	   //should I assert these postconditions? Seems redundant for the most part.
   }   
   
   
   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true. 
   * @postcondition
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   advance may not be called.
   **/
   public void advance()
   {
	   //Precondition
	   if(isCurrent() != true)
	   {
		   throw new IllegalStateException("Precondition: isCurrent() == true");
	   }
	   
	   currentIndex++;
	   
	   //Postconditions
	   //if statement assert postconditions?
   }
   
   
   /**
   * Generate a copy of this sequence.
   * @param - none
   * @return
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public DoubleArraySortedSeq clone()
   {  // Clone a DoubleArraySeq object.
	   DoubleArraySortedSeq answer;
      
      try
      {
         answer = (DoubleArraySortedSeq) super.clone();
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
      
      answer.data = data.clone();
      
      return answer;
   }
   

   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * @param s1
   *   the first of two sequences
   * @param s2
   *   the second of two sequences
   * @precondition
   *   Neither s1 nor s2 is null.
   * @return
   *   a new sequence that has the elements of s1 followed by the
   *   elements of s2 (with no current element)
   * @exception NullPointerException.
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new sequence.
   * @note
   *   An attempt to create a sequence with a capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/   
   public static DoubleArraySortedSeq catenation(DoubleArraySortedSeq s1, DoubleArraySortedSeq s2)
   {
	   //Preconditions
	   if(s1 == null)
	   {
		   throw new NullPointerException("Precondition: s1 != null");
	   }
	   
	   if(s2 == null)
	   {
		   throw new NullPointerException("Precondition: s2 != null");
	   }
	   
	   //Create a new object large enough to hold all elements from each sequence
	   DoubleArraySortedSeq newSeq = new DoubleArraySortedSeq(s1.size() + s2.size());
	   
	   //Add all elements from s1
	   //Assume they are in order...
	   for(int i = 0; i < s1.size(); i++){
		   newSeq.add(s1.data[i]);
	   }
	   
	   //Add all elements from s2
	   //Use the method add() to ensure elements are added in the correct order
	   for(int i = 0; i < s2.size(); i++){
		   newSeq.add(s2.data[i]);
	   }
	   
	   return newSeq;
   }


   /**
   * Change the current capacity of this sequence.
   * @param minimumCapacity
   *   the new capacity for this sequence
   * @postcondition
   *   This sequence's capacity has been changed to at least minimumCapacity.
   *   If the capacity was already at or greater than minimumCapacity,
   *   then the capacity is left unchanged.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: new double[minimumCapacity].
   **/
   public void ensureCapacity(int minimumCapacity)
   {
      if(minimumCapacity > getCapacity())
      {
    	  double[] largerData = new double[minimumCapacity];
    	  for(int i = 0; i < size(); i++)
    	  {
    		  largerData[i] = data[i];
    	  }
    	  data = largerData;
      }
      
      //Postcondition
      assert data.length >= minimumCapacity : "Postcondition: data.length >= minimumCapacity";
   }

   
   /**
   * Accessor method to get the current capacity of this sequence. 
   * The add method works efficiently (without needing
   * more memory) until this capacity is reached.
   * @param - none
   * @return
   *   the current capacity of this sequence
   **/
   public int getCapacity()
   {
      return data.length;
   }


   /**
   * Accessor method to get the current element of this sequence. 
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @return
   *   the current element of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   getCurrent may not be called.
   **/
   public double getCurrent()
   {
	   //Precondition
	   if(isCurrent() != true)
	   {
		   throw new IllegalStateException("Precondition: isCurrent() == true");
	   }
	   
	   return data[currentIndex];
   }


   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * getCurrent method. 
   * @param - none
   * @return
   *   true (there is a current element) or false (there is no current element at the moment)
   **/
   public boolean isCurrent()
   {
      boolean result = false;
      if(manyItems > 0 && currentIndex < manyItems) result = true;
      return result;
   }
              
   /**
   * Remove the current element from this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @postcondition
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   removeCurrent may not be called. 
   **/
   public void removeCurrent()
   {
	   //Precondition
	   if(isCurrent() != true)
	   {
		   throw new IllegalStateException("Precondition: isCurrent() == true");
	   }
	   
	   for(int i = currentIndex; i < manyItems; i++)
	   {
			data[i] = data[i+1];
	   }
	   manyItems--;
	   
	   //Postconditions
	   //Check that the current element has been removed from this sequence?
	   //Check that following element (if there is one) is now the new current element?
	   //If there was no following element, then there is now no current element?
   }
                 
   
   /**
   * Determine the number of elements in this sequence.
   * @param - none
   * @return
   *   the number of elements in this sequence
   **/ 
   public int size()
   {
      return manyItems;
   }
   
   
   /**
   * Set the current element at the front of this sequence.
   * @param - none
   * @postcondition
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/ 
   public void start()
   {
      currentIndex = 0;
      
      //Postconditions
      assert currentIndex == 0 : "Postcondition: currentIndex == 0";
      //The front element of this sequence is now the current element (but 
      //if this sequence has no elements at all, then there is no current element).
   }
   
   
   /**
   * Reduce the current capacity of this sequence to its actual size (i.e., the
   * number of elements it contains).
   * @param - none
   * @postcondition
   *   This sequence's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity. 
   **/
   public void trimToSize()
   {
      double[] trimmedArray;
      
      if (data.length != manyItems)
      {
         trimmedArray = new double[manyItems];
         System.arraycopy(data, 0, trimmedArray, 0, manyItems);
         data = trimmedArray;
      }
      
      //Postcondition
      assert data.length == manyItems : "Postcondition: data.length == manyItems";
   }
   
   
   /**
   * Search the sequence for a value
   * @param value
   *   the first of two sequences
   * @return
   *   True if the sequence contain the value
   *   False if the sequence does not contain the value
   * @exception NullPointerException.
   *   Indicates that the argument is null.
   * @note
   *   There may be more than one of the same values within the sequence.
   **/ 
   public boolean search(double value)
   {
      boolean result = false;
      
      for(int i = 0; i < size(); i++){
    	  if(data[i] == value)	result = true;
      }
      
      return result;
   }
}
           