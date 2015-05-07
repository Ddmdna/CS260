package Lab4;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class DriverTest extends Driver {
	
	@Test
	public void test_loadBagFromFile() {
		//fail("Not yet implemented");
		//Put something into a stream
		//Load from stream into the ArrayBag
		//check results
		
		//Not sure the best way to go about testing with the file system
		//The difficulty lies in opening a file by string name...
	}
	
	@Test
	public void test_printArrayBag(){
		//Change the stream that the function prints to
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			PrintStream ps = new PrintStream(baos,true,"utf-8");
			System.setOut(ps);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fail("Failed to set up PrintStream to a ByteArrayOutputStream");
		}

		//Set up preconditions to test zero chores
		ArrayBag<String> chores = new ArrayBag<String>();
		
		//Test zero chores
		printArrayBag(chores);
		String content = baos.toString();
		assertEquals("", content);
		
		//Set up preconditions to test several chores
		String first = "first";
		String middle = "middle";
		String last = "last";
		chores.add(first);
		chores.add(middle);
		chores.add(last);
		
		//Test several chores
		printArrayBag(chores);
		content = baos.toString();
		assertEquals(first + "\r\n" + middle + "\r\n" + last + "\r\n", content);
		
		//Revert the print stream to System.out
		System.setOut(System.out);
	}
	
	@Test
	public void test_getChar(){
		//fail("Not yet implemented");
		//Will need to set up a temp InputStream?
		//Test single character
		//Test multiple characters
		//Test an input that is too large
	}
	
	@Test
	public void test_getString(){
		//fail("Not yet implemented");
		//Will need to set up a temp InputStream?
		//Test single character
		//Test multiple characters
		//Test an input that is too large
	}
	
	@Test
	public void test_saveBagToFile(){
		//fail("Not yet implemented");
		//Would I need to change SaveBagToFile to saving to a stream?
		//Then I could read from the string to ensure correct output
	}
}
