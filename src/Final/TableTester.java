package Final;

public class TableTester extends UnitTester{
	Table<Integer, Integer> table;
	
	public TableTester(){
		//Run tests
		addNote("Tests for Table\n");
		addNote("constructor tests...\n");
		constructorTest();
		addNote("containsKey tests...\n");
		containsKeyTest();
		addNote("get tests...\n");
		getTest();
		addNote("put tests...\n");
		putTest();
		addNote("remove tests...\n");
		removeTest();
	}
	
	private void constructorTest(){
		Boolean zeroValidCapacity = true;
		Boolean negativeValidCapacity = true;
		try{
			table = new Table<Integer, Integer>(0);
		}
		catch(IllegalArgumentException e){
			zeroValidCapacity = false;
		}
		
		try{
			table = new Table<Integer, Integer>(-1);
		}
		catch(IllegalArgumentException e){
			negativeValidCapacity = false;
		}
		
		test(zeroValidCapacity, false);
		test(negativeValidCapacity, false);
	}
	
	private void containsKeyTest(){
		Integer keyGood = 1;
		Integer keyBad = 2;
		Integer data = 5;
		table = new Table<Integer, Integer>(1);
		table.put(keyGood.hashCode(), data);
		test(table.containsKey(keyGood.hashCode()), true);
		test(table.containsKey(keyBad.hashCode()), false);
	}
	
	private void getTest(){
		Integer keyGood = 1;
		Integer keyBad = 2;
		Integer data = 5;
		table = new Table<Integer, Integer>(1);
		table.put(keyGood.hashCode(), data);
		test(table.get(keyGood.hashCode()).equals(data), true);
		test(table.get(keyBad.hashCode()) == null, true);
	}
	
	private void putTest(){
		Integer keyGood = 1;
		Integer keyBad = 2;
		Integer data = 5;
		table = new Table<Integer, Integer>(1);
		table.put(keyGood.hashCode(), data);
		test(table.get(keyGood.hashCode()).equals(data), true);
		test(table.get(keyBad.hashCode()) == null, true);
	}
	
	private void removeTest(){
		Integer keyGood = 1;
		Integer keyBad = 2;
		Integer data = 5;
		table = new Table<Integer, Integer>(1);
		table.put(keyGood.hashCode(), data);
		test(table.remove(keyBad.hashCode()) == null, true);
		test(table.remove(keyGood.hashCode()).equals(data), true);
		test(table.get(keyGood.hashCode()) == null, true);
	}
}
