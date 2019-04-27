package assignment01;

/**
 * This class establishes a NamedObject in which a user can create a name and any object. 
 * It consists of basic Accessors such as getName() and getObject() to get the values
 * initialized in the constructor and a Mutator setName() to modify a NamedObject's name.  
 * In addition, this class overrides .equals and hashCode() methods of the Object class
 * to specialize comparison or receive the respective integer for a NamedObject.
 * Lastly, this class implements the chain and unchain methods which work similarly to
 * a linked list, so that the user is able to organize data of NamedObjects and have
 * a string of data returned corresponding to them. 
 *
 * @author  Steen Sia
 * @version August 31, 2017
 */

public class NamedObject 
{

	/* Private Instance Fields:
	 * String name - name of the string to be created in the NamedObject constructor
	 * Object object - object class that creates a new object in the NamedObject constructor
	 */

	private String name;
	private Object object;

	/** Constructs a NamedObject from a given name and object. 
	 * 	The name must not be null, but the object may be null.
	 * 
	 * @param name - Any non-null String
	 * @param object - Any object
	 * @throws IllegalArgumentException - if the name is null
	 * 
	 */

	/* this.name refers to the instance variable of type String to be created by the user for the NamedObject class
	 * this.object refers to the instance variable of type Object to be created by the user for the NamedObject class
	 * if the String is null, throw an IllegalArgumentException to notify the user and the user
	 */

	public NamedObject(String name, Object object) 
	{
		this.name = name;
		this.object = object;

		if (this.name == null)
		{
			throw new IllegalArgumentException();
		}
	}
	/** Returns the name of this NamedObject.
	 * 
	 * @return the name of this NamedObject.
	 */

	// An Accessor method that simply returns to the user the name of type String of this NamedObject

	public String getName()
	{
		return name;	
	}

	/** Returns the object stored in this NamedObject.
	 * 
	 * @return the object stored in this NamedObject.
	 */

	// An Accessor method that simply returns to the user the object of type Object of this NamedObject


	public Object getObject(){
		return object;		
	}

	/**Changes the name of this named object to a new name. 
	 * The new name must not be null.
	 * 
	 * @param newName - a non-null String
	 * @throws IllegalArgumentException - if the new name is null
	 */

	/* A Mutator method that allows the user to modify the current String name to another String of the user's choice
	 * If the new String is empty or null, the user is thrown an exception to notify the error.
	 */

	public void setName(String newName) {
		name = newName;
		if (name == null)		{
			throw new IllegalArgumentException();
		}
	}

	/**Returns true if this NamedObject and the other NamedObject have the same names and objects (using .equals). 
	 * Null enclosed objects are only considered equal to other null object references.
	 * 
	 * @Override equals in class.java.lang.Object
	 * @return true if this NamedObject has the same name and object as the other NamedObject
	 */

	/* This method allows the user to compare one NamedObject to another
	 * It will return true if the first NamedObject's name and object match the other NamedObject's name and object
	 * Otherwise, return false to notify the user that the NamedObjects are different
	 */

	public boolean equals(Object other) 
	{
		if (!(other instanceof NamedObject))
		{
			return false;
		}

		NamedObject objectToCompare = (NamedObject) other;

		if(name.equals(objectToCompare.name) && object.equals(objectToCompare.object))
		{
			return true;
		}

		else
			return false;
	}



	/**Returns a hash value for this NamedObject. 
	 * This method is guaranteed to be consistent with equals: Two equal NamedObjects will always produce equal hash values.
	 * There are no guarantees about the hash values of unequal NamedObjects.
	 * 
	 * @Override hashCode in class java.lang.Object
	 * @return the hash value for this object
	 */

	// Returns the sum of the String name's and Object object's hashCode.
	public int hashCode() 
	{
		if(object == null)
			return name.hashCode();
		else
			return name.hashCode() + object.hashCode();
	}

	/**Given an array of non-null Strings, this method will return a chain of NamedObjects containing the Strings as Names.
	 * This method creates one NamedObject per String, using each String in the array as the Name for each NamedObject.
	 * Each NamedObject will store another NamedObject in its enclosed object field such that the NamedObject with the name (data[n]) 
	 * will have as it's enclosed object the NamedObject with the name (data[n+1]). 
	 * The very last NamedObject with the name (data[data.length-1]) will have a null enclosed object.
	 * The returned NamedObject will have the name (data[0]) and will be chained to the rest of the NamedObjects.
	 * 
	 * @param data - An non-empty array of non-null Strings
	 * @return a chain of named objects whose names match the Strings in data
	 * @throws IllegalArgumentException - if the data array is empty or a String is null
	 * @throws NullPointerException - if the data array is null
	 */

	/*The chain method acts as a linked list where it chains each object.
	 * First, exceptions are thrown in order to ensure that the user must abide
	 * to the contract and enter valid inputs.  
	 * The chain begins counting at the end of the list and works its way down.
	 * The user will expect the chain to contain the index 0 of the array attached
	 * to the rest of the elements.
	 */
	public static NamedObject chain(String[] data) 
	{
		if(data == null)
			throw new NullPointerException();

		if (data.length == 0)
			throw new IllegalArgumentException();

		NamedObject current = new NamedObject(data[data.length-1], null);

		for (int n = data.length-2; n >= 0; n--)
		{	
			if(data[n] == null)
			throw new IllegalArgumentException();
			current = new NamedObject(data[n], current);
		}

		return current;

	}

	/**This method extracts and returns the names stored in a chain of NamedObjects. 
	 * Each NamedObject in the chain must have an object that is another NamedObject, or null (to end the chain). 
	 * The returned array will have one element per NamedObject in the chain.
	 * The elements will contain the names from this chain in the order that they appear in the chain.
	 * (The name extracted from the parameter 'chain' will be in the 0th array position, etc.) 
	 * If 'chain' is null, a zero-length array is returned.
	 * 
	 * @param chain - A NamedObject chained to 0 or more other chained NamedObjects
	 * @return an array of Strings corresponding to the names in the chain
	 * @throws ClassCastException - if the chain does not contain only NamedObjects
	 */

	/*The unchain method is designed to extract all the objects in the NamedObject chain 
	 * and transfer them into a array of strings.  First, exceptions are thrown to ensure that
	 * chain only has NamedObjects to extract from.  Then, we must have two loops, the first loop
	 * to count the number of elements in chain and the second loop to populate the array. The names
	 * will be put from the beginning(index 0, 1, 2 etc.) Once the elements are transferred to the array,
	 * we are able to return it to the user.
	 */

	public static String[] unchain(NamedObject chain)
	{
		String[] emptyArray = new String [0];
		if (chain == null)
			return emptyArray;

		if (!(chain instanceof NamedObject))
			throw new ClassCastException();		

		int counter = 0;
		NamedObject current = chain;

		while (current != null)
		{
			if (!(chain instanceof NamedObject))
				throw new ClassCastException();

			counter++;
			current = (NamedObject) current.object;
		}

		String[] unchainedNames = new String [counter];

		current = chain;
		for(int n = 0; n < counter ; n++)
		{			
			
			unchainedNames[n] = current.name;	
			current = (NamedObject) current.object;	
		}
		
		return unchainedNames;

	}	
}
