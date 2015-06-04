package Final;

public class PersonTester extends UnitTester{
	public PersonTester(){
		//Run tests
		addNote("Tests for Person\n");
		addNote("constructor tests...\n");
		constructorAttributeInitialization();
	}
	
	private void constructorAttributeInitialization(){
		Integer id = 0;
		String fName = "fName";
		String lName = "lName";
		int age = 0;
		
		Person person = new Person(id, fName, lName, age);
		test(person.getID(), id);
		test(person.getFirstName(), fName);
		test(person.getLastName(), lName);
		test(person.getAge(), age);
	}
}
