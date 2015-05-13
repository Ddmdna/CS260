package Lab4;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
/*
 * Eclipse doesn't detect this generic class to initially run JUnit
 * However, If I remove the <E> it will detect it.
 * After I have JUnit running in the side window, I can add the <E> back
 * and simply click rerun test from the JUnit window.
 */
@SuppressWarnings("rawtypes")
public class ArrayBagTest extends ArrayBag {

	@Test
	public void testArrayBag() {
		ArrayBag<String> chores = new ArrayBag<String>();
		assertEquals(10, chores.getCapacity());
		assertEquals(0, chores.size());
	}

	@Test
	public void testArrayBagInt() {
		int initialCapacity = 20;
		ArrayBag<String> chores = new ArrayBag<String>(initialCapacity);
		assertEquals(initialCapacity, chores.getCapacity());
		assertEquals(0, chores.size());
		//Do I need to test that an exception is thrown when the bag is 
		//given an initialCapacity < 0?
	}

	@Test
	public void testAdd() {
		int initialCapacity = 2;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		String first = "first";
		bag.add(first);
		//Test adding a single element
		assertEquals(first, bag.grab());
		
		String middle = "middle";
		String last = "last";
		bag.add(middle);
		bag.add(last);
		
		//Test that the array grew
		assertEquals(6, bag.getCapacity());
		
		//Test that adding worked even after growing
		Iterator it = bag.iterator();
		assertEquals(first, it.next());
		assertEquals(middle, it.next());
		assertEquals(last, it.next());
		
		//Test adding a null
		bag = new ArrayBag<String>(initialCapacity);
		bag.add(null);
		assertEquals(null, bag.grab());
		
	}

	@Test
	public void testAddAll() {
		int initialCapacity = 3;
		ArrayBag<String> bag1 = new ArrayBag<String>(initialCapacity);
		ArrayBag<String> bag2 = new ArrayBag<String>(initialCapacity);
		String first = "first";
		String middle = "middle";
		bag2.add(first);
		bag2.add(middle);
		
		//Test Adding to an empty list under capacity
		bag1.addAll(bag2);
		Iterator it = bag1.iterator();
		assertEquals(first, it.next());
		assertEquals(middle, it.next());
		
		bag2 = new ArrayBag<String>(initialCapacity);
		String one = "one";
		String two = "two";
		String three = "three";
		bag2.add(one);
		bag2.add(two);
		bag2.add(three);
		
		//Test adding bags to a non empty list that puts it over its initialCapacity
		bag1.addAll(bag2);
		it = bag1.iterator();
		assertEquals(first, it.next());
		assertEquals(middle, it.next());
		assertEquals(one, it.next());
		assertEquals(two, it.next());
		assertEquals(three, it.next());
	}

	@Test
	public void testAddMany() {
		int initialCapacity = 2;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		String first = "first";
		String middle = "middle";
		String last = "last";
		
		//Test adding multiple elements to a empty list that puts it beyond its initial capacity
		bag.addMany(first, middle, last);
		Iterator it = bag.iterator();
		assertEquals(first, it.next());
		assertEquals(middle, it.next());
		assertEquals(last, it.next());
	}

	@Test
	public void testClone() {
		int initialCapacity = 3;
		ArrayBag<String> bag1 = new ArrayBag<String>(initialCapacity);
		ArrayBag<String> bag2;
		String first = "first";
		String middle = "middle";
		String last = "last";
		
		bag1.add(first);
		bag1.add(middle);
		
		//Test clone
		bag2 = bag1.clone();
		Iterator it1 = bag1.iterator();
		Iterator it2 = bag2.iterator();
		assertEquals(it2.next(), it1.next());
		assertEquals(it2.next(), it1.next());
		assertEquals(bag2.size(), bag1.size());
		bag2.add(last);
		assertEquals(2, bag1.size());
		assertEquals(3, bag2.size());
		
	}

	@Test
	public void testCountOccurrences() {
		ArrayBag<String> bag = new ArrayBag<String>();
		String one = "one";
		String two = "two";
		String three = "three";
		
		bag.add(one);
		bag.add(two);
		bag.add(two);
		bag.add(three);
		bag.add(three);
		bag.add(three);
		bag.add(null);
		bag.add(null);
		bag.add(null);
		bag.add(null);
		
		assertEquals(1, bag.countOccurrences(one));
		assertEquals(2, bag.countOccurrences(two));
		assertEquals(3, bag.countOccurrences(three));
		assertEquals(4, bag.countOccurrences(null));
		
	}

	@Test
	public void testEnsureCapacity() {
		int initialCapacity = 3;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		bag.ensureCapacity(initialCapacity);
		assertEquals(3, bag.getCapacity());
		
		bag.ensureCapacity(0);
		assertEquals(3, bag.getCapacity());
		
		bag.ensureCapacity(4);
		assertEquals(4, bag.getCapacity());
		
	}

	@Test
	public void testGetCapacity() {
		int initialCapacity = 5;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		assertEquals(initialCapacity, bag.getCapacity());
	}

	@Test
	public void testGrab() {
		int initialCapacity = 3;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		String first = "first";
		String middle = "middle";
		String last = "last";
		
		//Randomly grabs the only bag
		bag.add(first);
		assertEquals(first, bag.grab());
		
		//Randomly grabs one of the bags
		bag.add(middle);
		bag.add(last);
		String all = first+middle+last;
		String result = bag.grab();
		assertEquals(true, all.contains(result));
		
		//Randomly grabs a different one of the bags
		String temp = bag.grab();
		while(result.equals(temp))
		{
			temp = bag.grab();
		}
		
		assertEquals(false, result.equals(temp));
	}

	@Test
	public void testRemove() {
		int initialCapacity = 3;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		String first = "first";
		bag.add(first);
		assertEquals(1, bag.countOccurrences(first));
		assertEquals(1, bag.size());
		//Test remove one bag
		bag.remove(first);
		assertEquals(0, bag.countOccurrences(first));
		assertEquals(0, bag.size());
		
		//Test, try to remove a bag not present
		bag.remove(first);
		assertEquals(0, bag.countOccurrences(first));
		assertEquals(0, bag.size());
		String second = "second";
		bag.add(second);
		bag.remove(first);
		assertEquals(0, bag.countOccurrences(first));
		assertEquals(1, bag.countOccurrences(second));
		assertEquals(1, bag.size());
		
		//Test remove a null
		bag.remove(null);
		assertEquals(1, bag.size());
	}

	@Test
	public void testSize() {
		int initialCapacity = 3;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		
		//Test empty bag size
		assertEquals(0, bag.size());
		
		//Test non empty bag size
		String first = "first";
		bag.add(first);
		assertEquals(1, bag.size());
	}

	@Test
	public void testTrimToSize() {
		int initialCapacity = 3;
		ArrayBag<String> bag = new ArrayBag<String>(initialCapacity);
		String first = "first";
		String middle = "middle";
		String last = "last";
		
		//Test trimToSize does nothing when size = capacity
		bag.add(first);
		bag.add(middle);
		bag.add(last);
		bag.trimToSize();
		assertEquals(3, bag.size());
		assertEquals(1, bag.countOccurrences(first));
		assertEquals(1, bag.countOccurrences(middle));
		assertEquals(1, bag.countOccurrences(last));
		
		//Test trimtToSize() shrinks the capacity when its greater than size
		bag.add(first);
		bag.add(first);
		bag.trimToSize();
		assertEquals(5, bag.size());
		assertEquals(3, bag.countOccurrences(first));
		assertEquals(1, bag.countOccurrences(middle));
		assertEquals(1, bag.countOccurrences(last));
	}

	@Test
	public void testUnion() {
		//Again, not sure if I should test for exceptions...
		int initialCapacity = 3;
		ArrayBag<String> bag1 = new ArrayBag<String>(initialCapacity);
		ArrayBag<String> bag2 = new ArrayBag<String>(initialCapacity);
		ArrayBag<String> bag3 = new ArrayBag<String>(initialCapacity);
		String one = "one";
		String two = "two";
		String three = "three";
		
		bag2.add(one);
		bag3.add(two);
		bag3.add(three);
		bag1 = bag1.union(bag2, bag3);
		
		assertEquals(3, bag1.size());
		assertEquals(6, bag1.getCapacity());
		assertEquals(1, bag1.countOccurrences(one));
		assertEquals(1, bag1.countOccurrences(two));
		assertEquals(1, bag1.countOccurrences(three));
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
		//Test that this does not return a null?
		//I would probably need to test the class in a separate file...
	}

}
