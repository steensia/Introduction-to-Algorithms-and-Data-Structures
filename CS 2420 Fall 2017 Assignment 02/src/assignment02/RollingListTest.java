package assignment02;

import static org.junit.Assert.*;

import org.junit.Test;

public class RollingListTest {

	@Test(expected =  IndexOutOfBoundsException.class)
	public void testGetElementOnAnEmptyArray() 
	{
		RollingList list = new RollingList(1.2f, 1);
		list.getElement(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetElementOnAnArrayOfTwoElements()
	{
		RollingList list = new RollingList(1.1f, 2);
		list.prepend(2);
		list.prepend(3.2);
		list.getElement(2);
	}

	@Test(expected =  IndexOutOfBoundsException.class)
	public void testSetElementOnAnEmptyArray() 
	{
		RollingList list = new RollingList(1.2f, 1);
		list.setElement(1, 2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetElementOnAnArrayOfThreeElements()
	{
		RollingList list = new RollingList(1.1f, 2);
		list.prepend(2);
		list.prepend(3.2);
		list.append(3.4);
		list.setElement(3, 2);
	}
	
	@Test
	public void testGetElementOnFourthElementInAnArrayOfFourElements()
	{
		RollingList list = new RollingList(2.3f, 4);
		list.prepend(2);
		list.prepend(3);
		list.prepend(5);
		list.append(7);
		
		double result = list.getElement(3);
		
		assertEquals(7.0, result);

	}
}
