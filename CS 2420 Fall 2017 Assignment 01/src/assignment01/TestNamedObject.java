package assignment01;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TestNamedObject {

	//getName Method Test:	
	@Test
	public void testGetNameAccessor() 
	{
		NamedObject cowNamedObject = new NamedObject("cow", Color.black);
		String result = cowNamedObject.getName();

		assertEquals("cow", result);
	}

	//getObject Method Test:
	@Test
	public void testGetObjectAccessor() 
	{
		NamedObject blackColorNamedObject = new NamedObject("cow", Color.black);
		Object result = blackColorNamedObject.getObject();

		assertEquals(Color.black, result);
	}
	
	//setName Method Tests:
	@Test(expected = IllegalArgumentException.class)
	public void testStringInputIsNull()
	{
		NamedObject redCar = new NamedObject("car", Color.red);
		redCar.setName(null);
	}

	@Test
	public void testSetNameMutator() 
	{
		NamedObject cowToMonkeyNamedObject = new NamedObject("cow", Color.black);
		cowToMonkeyNamedObject.setName("monkey");
		String result = cowToMonkeyNamedObject.getName();

		assertEquals("monkey", result);
	}

	//equals Method Test:
	@Test
	public void testEqualsOfTheSameObjects()
	{
		int result = 0;

		NamedObject firstYellowNamedObject = new NamedObject("equal", Color.yellow);
		NamedObject secondYellowNamedObject = new NamedObject("equal", Color.yellow);
		if (firstYellowNamedObject.equals(secondYellowNamedObject))
		{
			result = 1;
		}
		assertEquals(1, result);
	}
	
	//hashCode Method Tests:
	@Test
	public void testHashCodeOfANamedObjectWithNullAsItsObject()
	{
		NamedObject hasNullObject = new NamedObject("Cool", null);

		int expectedHashCode = hasNullObject.getName().hashCode();
		int result = hasNullObject.hashCode();

		assertEquals(expectedHashCode, result);
	}

	@Test
	public void testHashCodeEqualityOfTheSameObjects()
	{
		NamedObject firstNamedObject = new NamedObject("same", Color.cyan);
		NamedObject secondNamedObject = new NamedObject("same", Color.cyan);

		int result = 0;

		if(firstNamedObject.hashCode() == secondNamedObject.hashCode())
		{
			{
				result = 1;
			}
		}
		assertEquals(1, result);
	}

	//Chain Method Tests:

	@Test(expected = NullPointerException.class)
	public void testChainMethodIfTheArrayIsNull()
	{
		String[] nullArray = null;
		NamedObject.chain(nullArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testChainMethodIfTheArrayIsEmpty()
	{
		String[] emptyArray = {};
		NamedObject.chain(emptyArray);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testChainMethodSeeIfContentsOfArrayContainsNull()
	{
		String[] containsNullArray = {"a", "b", "c", null};
		NamedObject.chain(containsNullArray);
	}

	//Unchained Method Tests:

	@Test(expected = ClassCastException.class)
	public void testUnchainedMethodCheckIfChainIsNotANamedObject()
	{
		NamedObject notNamedObject = new NamedObject("A", 4);
		NamedObject.unchain(notNamedObject);
	}

	@Test
	public void testUnchainedMethodReturnEmptyArrayIfChainIsNull()
	{
		NamedObject nullChain= null;
		String[] resultArray = NamedObject.unchain(nullChain);
		int result = 0;
		int lengthIsZero = 1;

		if(resultArray.length == 0)
		{
			lengthIsZero = 0;
		}
		assertEquals(lengthIsZero, result);
	}

	@Test
	public void testUnchainedMethodIfChainIsAnEmptyString()
	{
		String[] emptyStringForChain = {""};
		NamedObject chainTest = NamedObject.chain(emptyStringForChain);
		String[] resultOfArray = NamedObject.unchain(chainTest);
		int result = 1;
		int lengthIsOne = 0;

		if(resultOfArray.length == 1)
		{
			lengthIsOne = 1;
		}
		assertEquals(lengthIsOne, result);

	}
}

