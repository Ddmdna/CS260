package Final;

//File: Table.java from the package edu.colorado.collections
//Complete documentation is available from the Table link in:
//http://www.cs.colorado.edu/~main/docs

/******************************************************************************
* A <CODE>Table</CODE> is an open-address hash table with a fixed capacity.
* This implementation will attempt to make use of double hashing.
*
* @invariant The number of items in the table is in the instance variable manyItems.
* 
* @invariant The preferred location for an element with a given key is at index hash(key).
* 	If a collision occurs, then next-Index is used to search forward to find the next open 
* 	address. When an open address is found at an index i, then the element itself is placed 
* 	in data[i] and the element's key is placed at keys[i].
* @invariant An index i that is not currently used has data[i] and key[i] set to null.
* 
* @invariant If an index i has been used at some point (now or in the past), then 
* 	hasBeenUsed[i] is true; otherwise it is false.

* <dt><b>Java Source Code for this class:</b><dd>
*   <A HREF="../../../../edu/colorado/collections/Table.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/collections/Table.java
*   </A>
*
* @author Drew Hamm
* 	from the work of Michael Main 
* @version
*   June 8, 2015
******************************************************************************/
public class Table<K, E>
{
	/**
	* Indicates the number of elements stored in the table
	**/
	private int manyItems;
	/**
	* An array to store the key for each data object
	**/
	private Object[ ] keys;
	/**
	* An array to store each data object
	**/
	private Object[ ] data;
	/**
	* An array that marks the index as true if it has held a key/data pair at one point
	**/
	private boolean[ ] hasBeenUsed;   
	
	/**
	* Initialize an empty table with a specified capacity.
	* @param <CODE>capacity</CODE>
	*   the capacity for this new open-address hash table
	* <dt><b>Postcondition:</b><dd>
	*   This table is empty and has the specified capacity.
	* @exception OutOfMemoryError
	*   Indicates insufficient memory for the specified capacity. 
	**/
	public Table(int capacity)
	{
	   // The manyItems instance variable is automatically set to zero.
	   // which is the correct initial value. The three arrays are allocated to
	   // be the specified capacity. The boolean array is automatically
	   // initialized to false, and the other two arrays are automatically
	   // initialized to all null.
	   if (capacity <= 0)
	      throw new IllegalArgumentException("Capacity is negative");
	   keys = new Object[capacity];
	   data = new Object[capacity];
	   hasBeenUsed = new boolean[capacity];
	}
	
	
	/**
	* Determines whether a specified key is in this table.
	* @param <CODE>key</CODE>
	*   the non-null key to look for
	* <dt><b>Precondition:</b><dd>
	*   <CODE>key</CODE> cannot be null.
	* @return
	*   <CODE>true</CODE? (if this table contains an object with the specified 
	*   key); <CODE>false</CODE> otherwise. Note that <CODE>key.equals( )</CODE>
	*   is used to compare the <CODE>key</CODE> to the keys that are in the 
	*   table.
	* @exception NullPointerException
	*   Indicates that <CODE>key</CODE> is null.
	**/
	public boolean containsKey(K key)
	{
	   return findIndex(key) != -1;
	}
	   
	
	/** Retrieves an object for a specified key.
	* @param <CODE>key</CODE>
	*   the non-null key to look for
	* <dt><b>Precondition:</b><dd>
	*   <CODE>key</CODE> cannot be null.
	* @return
	*   a reference to the object with the specified <CODE>key</CODE (if this 
	*   table contains an such an object);  null otherwise. Note that 
	*   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
	*   to the keys that are in the table.
	* @exception NullPointerException
	*   Indicates that <CODE>key</CODE> is null.
	**/
	@SuppressWarnings("unchecked")
	public E get(K key)
	{
	   int index = findIndex(key);
	   
	   if (index == -1)
	      return null;
	   else
		  return (E) data[index];
	}
	
	
	/**
	* Add a new element to this table, using the specified key.
	* @param <CODE>key</CODE>
	*   the non-null key to use for the new element
	* @param <CODE>element</CODE>
	*   the new element that’s being added to this table
	* <dt><b>Precondition:</b><dd>
	*   If there is not already an element with the specified <CODE>key</CODE>,
	*   then this table’s size must be less than its capacity 
	*   (i.e., <CODE>size() < capacity()</CODE>). Also, neither <CODE>key</CODE>
	*   nor </CODE>element</CODE> is null.
	* <dt><b>Postcondition:</b><dd>
	*   If this table already has an object with the specified <CODE>key</CODE>,
	*   then that object is replaced by </CODE>element</CODE>, and the return 
	*   value is a reference to the replaced object. Otherwise, the new 
	*   </CODE>element</CODE> is added with the specified <CODE>key</CODE>
	*   and the return value is null.
	* @exception IllegalStateException
	*   Indicates that there is no room for a new object in this table.
	* @exception NullPointerException
	*   Indicates that <CODE>key</CODE> or <CODE>element</CODE> is null.   
	**/
	@SuppressWarnings("unchecked")
	public E put(K key, E element)
	{
	   int index = findIndex(key);
	   E answer;
	   int count = 0;
	   if (index != -1)
	   {  // The key is already in the table.
		  answer = (E) data[index];
	      data[index] = element;
	      return answer;
	   }
	   else if (manyItems < data.length)
	   {  // The key is not yet in this Table.
	      index = hash1(key);
	      while (keys[index] != null){
	    	  System.out.println("Collision(" + ++count + ")");
	    	  index = nextIndex(index, key);
	      }
	      keys[index] = key;
	      data[index] = element;
	      hasBeenUsed[index] = true;
	      manyItems++;
	      return null;
	   }
	   else
	   {  // The table is full.
	      throw new IllegalStateException("Table is full.");
	   }
	}
	   
	
	/**
	* Removes an object for a specified key.
	* @param <CODE>key</CODE>
	*   the non-null key to look for
	* <dt><b>Precondition:</b><dd>
	*   <CODE>key</CODE> cannot be null.
	* <dt><b>Postcondition:</b><dd>
	*   If an object was found with the specified </CODE>key</CODE>, then that
	*   object has been removed from this table and a copy of the removed object
	*   is returned; otherwise, this table is unchanged and the null reference
	*   is returned.  Note that 
	*   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
	*   to the keys that are in the table.
	* @exception NullPointerException
	*   Indicates that </CODE>key</CODE> is null.
	**/
	@SuppressWarnings("unchecked")
	public E remove(K key)
	{
	   int index = findIndex(key);
	   E answer = null;
	   
	   if (index != -1)
	   {
		 answer = (E) data[index];
	      keys[index] = null;
	      data[index] = null;
		 manyItems--;
	   }
	   
	   return answer;
	}
	
	
	/**
	* Finds the next index in the array for the given key
	* @param <CODE>key</CODE>
	*   the non-null key to find an index for
	* <dt><b>Precondition:</b><dd>
	*   <CODE>key</CODE> cannot be null.
	* <dt><b>Postcondition:</b><dd>
	*   If the specified key is found in the table, then the return
	*   value is the index of the specified key. Otherwise, the return value is -1.
	* @return
	* 	The value of the index of the specified key if found else -1.
	* @exception NullPointerException
	*   Indicates that </CODE>key</CODE> is null.
	**/
	private int findIndex(K key)
	{
	   int count = 0;
	   int i = hash1(key);
	   
	   while (count < data.length && hasBeenUsed[i])
	   {
	      if (key.equals(keys[i]))
	         return i;
	      count++;
	      i = nextIndex(i, key);
	   }
	   
	   return -1;
	}
	
	
	/**
	* Creates an initial hash to be used with a key in relation to the data array
	* @param <CODE>key</CODE>
	*   the non-null key to find an index for
	* <dt><b>Precondition:</b><dd>
	*   <CODE>key</CODE> cannot be null.
	* <dt><b>Postcondition:</b><dd>
	*   The index is calculated as the remainder when the absolute value of the key’s 
	*   hash code is divided by the size of the table’s arrays.
	* @return
	* 	A value that is a valid index of the table’s arrays
	* @exception NullPointerException
	*   Indicates that </CODE>key</CODE> is null.
	**/
	private int hash1(K key)
	{
	   return Math.abs(key.hashCode( )) % data.length;
	}
	
	
	/**
	* Creates a hash to be used with a key in relation to the data array
	* @param <CODE>key</CODE>
	*   the non-null key to find an index for
	* <dt><b>Precondition:</b><dd>
	*   <CODE>key</CODE> cannot be null.
	* <dt><b>Postcondition:</b><dd>
	*   The index is calculated as the remainder when the absolute value of the key’s 
	*   hash code is divided by the size of the table’s arrays.
	* @return
	* 	A value that is a valid index of the table’s arrays
	* @exception NullPointerException
	*   Indicates that </CODE>key</CODE> is null.
	**/
	private int hash2(K key)
	{
	   return 1 + (Math.abs(key.hashCode( )) % (data.length-2));
	}
	
	
	/**
	* Gets the next valid index in the data array. Loops when necessary
	* @param i
	*   the non-null index to increment from
	* @param <CODE>key</CODE>
	*   the non-null key to use in indexing
	* <dt><b>Precondition:</b><dd>
	*   i must be greater than -1 and less than data.length.
	* <dt><b>Postcondition:</b><dd>
	*   The index is a valid location within the data array.
	* @return
	* 	A value that is the modulus of data.length for a hash step from the initial index
	* @exception IllegalStateException
	*   Indicates that i is < 0 or > data.length.
	**/
	private int nextIndex(int i, K key)
	{
		return (i + hash2(key)) % data.length;
	}
	
	
	/**
	* Outputs the key and data of each object stored in the table
	* @return
	* 	A formatted string containing the key and data for each object in the table
	**/
	public String toString(){
		String result = "";
		for(int i = 0; i < data.length; i++){
			if(data[i] != null){
				result+= "("+i+")Key: " + keys[i].toString() + "  = \t" + data[i].toString() + "\n";
			}
		}
		return result;
	}
}  