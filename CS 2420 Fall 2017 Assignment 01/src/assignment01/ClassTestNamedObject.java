package assignment01;

import java.awt.Color;

public class ClassTestNamedObject {

	/* This main method exercises all the methods derived from the NamedObject class.
	 * NamedObjects will be made in order to test several cases that may or not 
	 * overlap with the JUnit test cases. 
	 */
	public static void main(String[] args) {

		//Creating new NamedObject and testing getName method
		NamedObject firstNamedObjectToTest = new NamedObject("John", Color.blue);
		String nameOfChild = firstNamedObjectToTest.getName();

		//Pairing John with its object with getObject method
		System.out.println(nameOfChild + " " + firstNamedObjectToTest.getObject());
		//Resetting John's name to Tom with setName method
		firstNamedObjectToTest.setName("Tom");
		String newNameOfChild = firstNamedObjectToTest.getName();
		System.out.println(newNameOfChild + " " + firstNamedObjectToTest.getObject());

		/*Testing the chain method and unchain method
		 * As expected, the array I have created and chaining it, its 
		 * contents will be extracted with unchained method and the 
		 * new array's contents should replicate the initial array's contents.
		 */
		String[] listOfAnimals = {"Squirrel", "Cow", "Goose", "Panda"};
		NamedObject animals = NamedObject.chain(listOfAnimals);
		//Testing the hashCode method, it should populate a random integer.
		System.out.println(animals.hashCode());
		System.out.println(animals.toString());
		String[] copyOfListOfAnimals = (NamedObject.unchain(animals));

		for(int n = 0; n < copyOfListOfAnimals.length; n++)
		{
			System.out.print(copyOfListOfAnimals[n] + " ");
		}

		System.out.println("");
		//Testing equality of NamedObjects with .equals method and hashCode method
		NamedObject compareThisObject = new NamedObject("Ok", "Yes");
		NamedObject compareThisObject2 = new NamedObject("Ok", "Yes");

		if(compareThisObject.hashCode() == compareThisObject2.hashCode())
		{
			System.out.println("These NamedObjects have the same hashCode: " + compareThisObject.hashCode());
		}

		if(compareThisObject.equals(compareThisObject2))
		{
			System.out.println("Hurray, these objects are equal!");
		}
	}

}
