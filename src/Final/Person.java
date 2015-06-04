package Final;

/******************************************************************************
* A <CODE>Person</CODE> is a model of a person.
* @author Drew Hamm
* @version
*   June 8, 2015
******************************************************************************/
public class Person {
	private Integer id; //To use hashCode() on...
	private String firstName;
	private String lastName;
	private int age;

	
	/**
	* Initialize a Person with a specified values.
	* @param id
	*   used for the key
	* @param firstName
	*   the first name of the person
	* @param lastName
	*   the last name of the person
	* @param age
	*   the age of the person
	*  Postcondition:
	*   All attributes are initialized
	**/
	public Person(Integer id, String firstName, String lastName, int age){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	
	/** Retrieves firstName
	* @return
	*   The String firstName
	**/
	public String getFirstName(){
		return firstName;
	}
	
	
	/** Retrieves lastName
	* @return
	*   The String lastName
	**/
	public String getLastName(){
		return lastName;
	}
	
	
	/** Retrieves age
	* @return
	*   The int age
	**/
	public int getAge(){
		return age;
	}
	
	
	/** Retrieves id
	* @return
	*   The Integer id
	**/
	public Integer getID(){
		return id;
	}
	/**
	* Outputs the key and data of each object stored in the table
	* @return
	* 	A formatted string containing the key and data for each object in the table
	**/
	public String toString(){
		return "ID: " + id + "\tFirst: " + firstName + "\tLast: " + lastName + "\tAge: " + age;
	}
}
